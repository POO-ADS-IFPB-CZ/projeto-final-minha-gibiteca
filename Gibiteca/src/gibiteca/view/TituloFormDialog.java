package gibiteca.view;

import gibiteca.model.Titulo;
import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class TituloFormDialog extends JDialog {
    private final JTextField txtNome;
    private final JTextField txtEditora;
    private final JTextField txtAutor;
    private Optional<Titulo> resultado = Optional.empty();
    private boolean confirmado = false;

    public TituloFormDialog(JFrame parent, Titulo existente) {
        super(parent, true);
        setTitle(existente == null ? "Cadastrar Título" : "Editar Título");
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        painel.add(new JLabel("Nome:"));
        txtNome = new JTextField(existente != null ? existente.getNome() : "");
        painel.add(txtNome);
        painel.add(new JLabel("Editora:"));
        txtEditora = new JTextField(existente != null ? existente.getEditora() : "");
        painel.add(txtEditora);
        painel.add(new JLabel("Autor:"));
        txtAutor = new JTextField(existente != null ? existente.getAutor() : "");
        painel.add(txtAutor);
        add(painel, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);
        add(painelBotoes, BorderLayout.SOUTH);

        btnSalvar.addActionListener(e -> salvar(existente));
        btnCancelar.addActionListener(e -> {
            resultado = Optional.empty();
            confirmado = false;
            dispose();
        });
    }

    private void salvar(Titulo existente) {
        String nome = txtNome.getText().trim();
        String editora = txtEditora.getText().trim();
        String autor = txtAutor.getText().trim();
        if (nome.isEmpty() || editora.isEmpty() || autor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }
        if (existente == null) {
            resultado = Optional.of(new Titulo(System.currentTimeMillis(), nome, editora, autor));
        } else {
            existente.setNome(nome);
            existente.setEditora(editora);
            existente.setAutor(autor);
            resultado = Optional.of(existente);
        }
        confirmado = true;
        dispose();
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public Titulo getTitulo() {
        return resultado.orElse(null);
    }
}
