package gibiteca.view;

import gibiteca.model.Titulo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TituloFormDialog extends JDialog {

    private JTextField txtNome;
    private JTextField txtEditora;
    private JTextField txtAutor;
    private boolean confirmado = false;
    private Titulo titulo;

    public TituloFormDialog(Window owner, Titulo existente) {
        super(owner, "Cadastro de Título", ModalityType.APPLICATION_MODAL);
        this.titulo = existente;
        initUI();
        if (existente != null) {
            preencherCampos(existente);
        }
    }

    private void initUI() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(380, 220);
        setLocationRelativeTo(getOwner());

        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(12, 12, 0, 12));
        form.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        form.add(txtNome);
        form.add(new JLabel("Editora:"));
        txtEditora = new JTextField();
        form.add(txtEditora);
        form.add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        form.add(txtAutor);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnSalvar = new JButton("Salvar");
        botoes.add(btnCancelar);
        botoes.add(btnSalvar);

        add(form, BorderLayout.CENTER);
        add(botoes, BorderLayout.SOUTH);

        btnCancelar.addActionListener(e -> {
            confirmado = false;
            dispose();
        });
        btnSalvar.addActionListener(e -> onSalvar());

        // Atalhos
        getRootPane().setDefaultButton(btnSalvar);
        getRootPane().registerKeyboardAction(
                (ActionEvent e) -> dispose(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW
        );
    }

    private void preencherCampos(Titulo t) {
        txtNome.setText(t.getNome());
        txtEditora.setText(t.getEditora());
        txtAutor.setText(t.getAutor());
    }

    private void onSalvar() {
        if (txtNome.getText().trim().isEmpty() ||
            txtEditora.getText().trim().isEmpty() ||
            txtAutor.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (titulo == null) titulo = new Titulo();
        titulo.setNome(txtNome.getText().trim());
        titulo.setEditora(txtEditora.getText().trim());
        titulo.setAutor(txtAutor.getText().trim());
        confirmado = true;
        dispose();
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public Titulo getTitulo() {
        return confirmado ? titulo : null;
    }
}
