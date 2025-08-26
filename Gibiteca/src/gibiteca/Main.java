package gibiteca;

import gibiteca.view.TelaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Opcional: ping visual para confirmar que a GUI está subindo
            // JOptionPane.showMessageDialog(null, "Iniciando Minha Gibiteca...");

            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}

