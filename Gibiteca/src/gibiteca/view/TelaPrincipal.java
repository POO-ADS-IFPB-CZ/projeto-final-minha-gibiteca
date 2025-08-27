package gibiteca.view;


import gibiteca.controller.TituloController;
import gibiteca.model.Titulo;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private final TituloController controller;
    private final JTable tabela;
    private final DefaultTableModel modelo;
    private final JTextField txtPesquisa;

    public TelaPrincipal() {
        super("📚 Gibiteca - Títulos");
        this.controller = new TituloController();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Painel de pesquisa
        JPanel painelPesquisa = new JPanel(new BorderLayout(8, 8));
        painelPesquisa.setBackground(Color.WHITE);
        painelPesquisa.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        txtPesquisa = new JTextField();
        painelPesquisa.add(new JLabel("Pesquisar:"), BorderLayout.WEST);
        painelPesquisa.add(txtPesquisa, BorderLayout.CENTER);
        JButton btnBuscar = criarBotao("Buscar");
        painelPesquisa.add(btnBuscar, BorderLayout.EAST);
        add(painelPesquisa, BorderLayout.NORTH);

        // Tabela
        String[] colunas = {"ID", "Nome", "Editora", "Autor"};
        modelo = new DefaultTableModel(colunas, 0);
        tabela = new JTable(modelo);
        tabela.setRowHeight(28);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabela.setGridColor(new Color(220, 220, 220));
        tabela.setSelectionBackground(new Color(222, 59, 81));
        tabela.setSelectionForeground(Color.WHITE);
        JTableHeader header = tabela.getTableHeader();
        header.setBackground(new Color(180, 30, 52));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        // Painel de botões
        JPanel painelBotoes = new JPanel(new GridLayout(1, 3, 15, 0));
        painelBotoes.setBackground(Color.WHITE);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        JButton btnCadastrar = criarBotao("Cadastrar");
        JButton btnEditar = criarBotao("Editar");
        JButton btnExcluir = criarBotao("Excluir");
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        add(painelBotoes, BorderLayout.SOUTH);

        carregarTabela();

        // Ações
        btnBuscar.addActionListener(e -> buscarPorNome());
        btnExcluir.addActionListener(e -> excluirSelecionado());
        btnCadastrar.addActionListener(e -> abrirFormulario(null));
        btnEditar.addActionListener(e -> editarSelecionado());
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(180, 30, 52));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return btn;
    }

    private void carregarTabela() {
        modelo.setRowCount(0);
        List<Titulo> lista = controller.listarTodos();
        for (Titulo t : lista) {
            modelo.addRow(new Object[]{t.getId(), t.getNome(), t.getEditora(), t.getAutor()});
        }
    }

    private void buscarPorNome() {
        String termo = txtPesquisa.getText().trim().toLowerCase();
        modelo.setRowCount(0);
        List<Titulo> lista = controller.listarTodos();
        for (Titulo t : lista) {
            if (t.getNome().toLowerCase().contains(termo)) {
                modelo.addRow(new Object[]{t.getId(), t.getNome(), t.getEditora(), t.getAutor()});
            }
        }
    }

    private void abrirFormulario(Titulo existente) {
        TituloFormDialog dialog = new TituloFormDialog(this, existente);
        dialog.setVisible(true);
        if (dialog.isConfirmado()) {
            if (existente == null) {
                controller.adicionar(dialog.getTitulo());
            } else {
                controller.atualizar(dialog.getTitulo());
            }
            carregarTabela();
        }
    }

    private void editarSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um título para editar.");
            return;
        }
        long id = (long) tabela.getValueAt(linha, 0);
        List<Titulo> lista = controller.listarTodos();
        Titulo existente = null;
        for (Titulo t : lista) {
            if (t.getId() == id) {
                existente = t;
                break;
            }
        }
        abrirFormulario(existente);
    }

    private void excluirSelecionado() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um título para excluir.");
            return;
        }
        long id = (long) tabela.getValueAt(linha, 0);
        List<Titulo> lista = controller.listarTodos();
        Titulo existente = null;
        for (Titulo t : lista) {
            if (t.getId() == id) {
                existente = t;
                break;
            }
        }
        int resp = JOptionPane.showConfirmDialog(
            this,
            "Excluir \"" + existente.getNome() + "\"?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        if (resp == JOptionPane.YES_OPTION) {
            controller.remover(existente);
            carregarTabela();
        }
    }
}