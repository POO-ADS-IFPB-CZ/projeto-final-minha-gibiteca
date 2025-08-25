package gibiteca.view;

import gibiteca.controller.TituloController;
import gibiteca.model.Titulo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    private final TituloController controller;
    private final DefaultListModel<Titulo> listaModel;
    private final JList<Titulo> listaTitulos;

    public TelaPrincipal() {
        controller = new TituloController();

        setTitle("Gibiteca - Títulos de HQ");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Lista
        listaModel = new DefaultListModel<>();
        listaTitulos = new JList<>(listaModel);
        listaTitulos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(listaTitulos);

        // Botões
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");

        JPanel panelBotoes = new JPanel();
        panelBotoes.add(btnCadastrar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnExcluir);

        add(scroll, BorderLayout.CENTER);
        add(panelBotoes, BorderLayout.SOUTH);

        // Carrega títulos iniciais
        atualizarLista();

        // Ações
        btnCadastrar.addActionListener(e -> {
            TituloFormDialog dialog = new TituloFormDialog(this, "Cadastrar Título", null);
            dialog.setVisible(true);
            Titulo novo = dialog.getTitulo();
            if (novo != null) {
                controller.cadastrar(novo);
                atualizarLista();
            }
        });

        btnEditar.addActionListener(e -> {
            Titulo selecionado = listaTitulos.getSelectedValue();
            if (selecionado == null) {
                JOptionPane.showMessageDialog(this, "Selecione um título para editar.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            TituloFormDialog dialog = new TituloFormDialog(this, "Editar Título", selecionado);
            dialog.setVisible(true);
            Titulo editado = dialog.getTitulo();
            if (editado != null) {
                controller.atualizar(editado);
                atualizarLista();
            }
        });

        btnExcluir.addActionListener(e -> {
            Titulo selecionado = listaTitulos.getSelectedValue();
            if (selecionado == null) {
                JOptionPane.showMessageDialog(this, "Selecione um título para excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir \"" + selecionado.getNome() + "\"?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                controller.excluir(selecionado);
                atualizarLista();
            }
        });
    }

    private void atualizarLista() {
        listaModel.clear();
        for (Titulo t : controller.listarTodos()) {
            listaModel.addElement(t);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}
