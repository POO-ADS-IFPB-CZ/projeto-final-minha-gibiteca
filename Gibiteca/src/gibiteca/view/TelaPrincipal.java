package gibiteca.view;

import gibiteca.controller.TituloController;
import gibiteca.model.Titulo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TelaPrincipal extends JFrame {

    private final TituloController controller;
    private final DefaultListModel<Titulo> listModel;
    private final JList<Titulo> lstTitulos;
    private final JTextField txtFiltro;

    public TelaPrincipal() {
        super("Minha Gibiteca");
        this.controller = new TituloController();
        this.listModel = new DefaultListModel<>();
        this.lstTitulos = new JList<>(listModel);
        this.txtFiltro = new JTextField();
        initUI();
        carregarLista();
    }

    private void initUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(560, 480);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        // Painel superior com filtro
        JPanel top = new JPanel(new BorderLayout(8, 8));
        top.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));
        top.add(new JLabel("Filtrar (nome/editora/autor):"), BorderLayout.WEST);
        top.add(txtFiltro, BorderLayout.CENTER);
        JButton btnAplicarFiltro = new JButton("Aplicar");
        top.add(btnAplicarFiltro, BorderLayout.EAST);
        add(top, BorderLayout.NORTH);

        // Lista central
        add(new JScrollPane(lstTitulos), BorderLayout.CENTER);

        // Botões inferiores
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAtualizar = new JButton("Atualizar");
        botoes.add(btnCadastrar);
        botoes.add(btnEditar);
        botoes.add(btnExcluir);
        botoes.add(btnAtualizar);
        add(botoes, BorderLayout.SOUTH);

        btnCadastrar.addActionListener(e -> cadastrar());
        btnEditar.addActionListener(e -> editar());
        btnExcluir.addActionListener(e -> excluir());
        btnAtualizar.addActionListener(e -> carregarLista());
        btnAplicarFiltro.addActionListener(e -> aplicarFiltro());
        txtFiltro.addActionListener(e -> aplicarFiltro()); // Enter no campo aplica também
    }

    private void carregarLista() {
        listModel.clear();
        List<Titulo> titulos = controller.listarTodos();
        for (Titulo t : titulos) {
            listModel.addElement(t);
        }
    }

    private void aplicarFiltro() {
        String q = txtFiltro.getText();
        if (q == null) q = "";
        String qq = q.toLowerCase(Locale.ROOT).trim();

        List<Titulo> filtrados = controller.listarTodos().stream()
                .filter(t -> {
                    String n = t.getNome() == null ? "" : t.getNome();
                    String e = t.getEditora() == null ? "" : t.getEditora();
                    String a = t.getAutor() == null ? "" : t.getAutor();
                    String cat = (n + " " + e + " " + a).toLowerCase(Locale.ROOT);
                    return cat.contains(qq);
                })
                .collect(Collectors.toList());

        listModel.clear();
        for (Titulo t : filtrados) {
            listModel.addElement(t);
        }
    }

    private void cadastrar() {
        TituloFormDialog dialog = new TituloFormDialog(this, null);
        dialog.setVisible(true);
        if (dialog.isConfirmado()) {
            controller.adicionar(dialog.getTitulo());
            aplicarFiltro(); // recarrega com filtro atual
        }
    }

    private void editar() {
        Titulo selecionado = lstTitulos.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um título.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        TituloFormDialog dialog = new TituloFormDialog(this, selecionado);
        dialog.setVisible(true);
        if (dialog.isConfirmado()) {
            controller.atualizar(dialog.getTitulo());
            aplicarFiltro();
        }
    }

    private void excluir() {
        Titulo selecionado = lstTitulos.getSelectedValue();
        if (selecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um título.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int resp = JOptionPane.showConfirmDialog(this, "Excluir \"" + selecionado.getNome() + "\""?",
                "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (resp == JOptionPane.YES_OPTION) {
            controller.remover(selecionado);
            aplicarFiltro();
        }
    }
}
