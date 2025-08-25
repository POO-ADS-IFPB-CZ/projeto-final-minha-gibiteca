package gibiteca.view;

import gibiteca.model.Titulo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TituloFormDialog extends JDialog {

    private Titulo titulo;
    private boolean confirmado = false;

    private final JTextField txtNome;
    private final JTextField txtEditora;
    private final JTextField txtAutor;

    public TituloFormDialog(Frame owner, String tituloJanela, Titulo tituloExistente) {
        super(owner, tituloJanela, true);

        txtNome = new JTextField(20);
        txtEditora = new JTextField(20);
        txtAutor = new JTextField(20);

        if (tituloExistente != null) {
            txtNome.setText(tituloExistente.getNome());
            txtEditora.setText(tituloExistente.getEditora());
            txtAutor.setText(tituloExistente.getAutor());
            this.titulo = tituloExistente;
        }

        setLayout(new BorderLayout());
        setSize(350, 200);
        setLocationRelativeTo(owner);

        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 5, 5));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelCampos.add(new JLabel("Nome:"));
        panelCampos.add(txtNome);
        panelCampos.add(new JLabel("Editora:"));
        panelCampos.add(txtEditora);
        panelCampos.add(new JLabel("Autor:"));
        panelCampos.add(txtAutor);

        add(panelCampos, BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnCancelar);
        add(panelBotoes, BorderLayout.SOUTH);

        btnSalvar.addActionListener(e -> {
            if (txtNome.getText().trim().isEmpty() ||
                    txtEditora.getText().trim().isEmpty() ||
                    txtAutor.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (titulo == null) {
                titulo = new Titulo(System.currentTimeMillis(),
                        txtNome.getText().trim(),
                        txtEditora.getText().trim(),
                        txtAutor.getText().trim());
            } else {
                titulo.setNome(txtNome.getText().trim());
                titulo.setEditora(txtEditora.getText().trim());
                titulo.setAutor(txtAutor.getText().trim());
            }
            confirmado = true;
            dispose();
        });

        btnCancelar.addActionListener(e -> {
            titulo = null;
            dispose();
        });
    }

    public Titulo getTitulo() {
        return confirmado ? titulo : null;
    }
}
