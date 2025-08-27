package gibiteca.view;

import gibiteca.controller.PersonagemController;
import gibiteca.model.Personagem;

import javax.swing.*;
import java.awt.*;

public class TelaPersonagem extends JFrame {
    private PersonagemController controller;

    private JTextField campoId, campoNome, campoAparicao;
    private JTextArea areaLista;

    public TelaPersonagem(PersonagemController controller) {
        this.controller = controller;

        setTitle("Gerenciar Personagens");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        // Campos
        campoId = new JTextField(5);
        campoNome = new JTextField(15);
        campoAparicao = new JTextField(15);
        JButton botaoAdicionar = new JButton("Adicionar");
        JButton botaoListar = new JButton("Listar");
        areaLista = new JTextArea(10, 30);

        // Ações
        botaoAdicionar.addActionListener(e -> {
            int id = Integer.parseInt(campoId.getText());
            String nome = campoNome.getText();
            String aparicao = campoAparicao.getText();
            controller.adicionar(new Personagem(id, nome, aparicao));
            JOptionPane.showMessageDialog(this, "Personagem adicionado!");
        });

        botaoListar.addActionListener(e -> {
            areaLista.setText("");
            for (Personagem p : controller.listar()) {
                areaLista.append(p.toString() + "\n");
            }
        });

        // Layout
        add(new JLabel("ID:"));
        add(campoId);
        add(new JLabel("Nome:"));
        add(campoNome);
        add(new JLabel("Primeira Aparição:"));
        add(campoAparicao);
        add(botaoAdicionar);
        add(botaoListar);
        add(new JScrollPane(areaLista));

        setVisible(true);
    }
}
