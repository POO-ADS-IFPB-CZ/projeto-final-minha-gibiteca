package gibiteca;

import gibiteca.controller.PersonagemController;
import gibiteca.view.TelaPersonagem;
import gibiteca.model.Personagem;

import javax.swing.SwingUtilities;

public class MainPersonagem {

    // Método público que inicializa a tela de personagem
    public static void abrirTelaPersonagem() {
        PersonagemController controller = new PersonagemController();
        new TelaPersonagem(controller);
    }

    public static void main(String[] args) {
        // Configura o Look and Feel do sistema
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Inicializa a tela na thread de eventos do Swing
        SwingUtilities.invokeLater(() -> abrirTelaPersonagem());
    }
}
