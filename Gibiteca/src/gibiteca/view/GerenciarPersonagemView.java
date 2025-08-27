package gibiteca.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class GerenciarPersonagemView extends JFrame {

    private JTextField txtID, txtNome, txtPrimeiraAparicao;
    private JButton btnSalvar, btnEditar, btnExcluir, btnListar;
    private JTable tabelaPersonagens;

    public GerenciarPersonagemView() {
        super("Gerenciamento de Personagens");
        // Aplicação do Look and Feel para uma interface mais agradável[cite: 81, 82].
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Dados do Personagem"));
        
        txtID = new JTextField();
        txtID.setEditable(false);
        txtNome = new JTextField(20);
        txtPrimeiraAparicao = new JTextField(20);

        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(txtID);
        panelFormulario.add(new JLabel("Nome:"));
        panelFormulario.add(txtNome);
        panelFormulario.add(new JLabel("Primeira Aparição:"));
        panelFormulario.add(txtPrimeiraAparicao);
        
        JPanel panelBotoes = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnEditar = new JButton("Editar");
        btnExcluir = new JButton("Excluir");
        btnListar = new JButton("Listar Todos");

        panelBotoes.add(btnSalvar);
        panelBotoes.add(btnEditar);
        panelBotoes.add(btnExcluir);
        panelBotoes.add(btnListar);

        tabelaPersonagens = new JTable(new DefaultTableModel(new Object[]{"ID", "Nome", "Primeira Aparição"}, 0));
        JScrollPane scrollPane = new JScrollPane(tabelaPersonagens);

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotoes, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public String getNome() {
        return txtNome.getText();
    }

    public String getPrimeiraAparicao() {
        return txtPrimeiraAparicao.getText();
    }
    
    public int getPersonagemSelecionadoID() {
        int linha = tabelaPersonagens.getSelectedRow();
        if (linha != -1) {
            return (int) tabelaPersonagens.getValueAt(linha, 0); 
        }
        return -1;
    }
    
    public void limparFormulario() {
        txtID.setText("");
        txtNome.setText("");
        txtPrimeiraAparicao.setText("");
    }
    
    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
    
    public void atualizarTabela(DefaultTableModel model) {
        tabelaPersonagens.setModel(model);
    }
    
    public void addSalvarListener(ActionListener listener) {
        btnSalvar.addActionListener(listener);
    }
    
    public void addEditarListener(ActionListener listener) {
        btnEditar.addActionListener(listener);
    }

    public void addExcluirListener(ActionListener listener) {
        btnExcluir.addActionListener(listener);
    }
    
    public void addListarListener(ActionListener listener) {
        btnListar.addActionListener(listener);
    }
}