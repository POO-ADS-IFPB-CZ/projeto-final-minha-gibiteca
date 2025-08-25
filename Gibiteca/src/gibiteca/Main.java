package gibiteca; // Garanta que o pacote está correto

import gibiteca.model.Titulo;
import gibiteca.persistence.TituloDAO;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- Executando teste do TituloDAO ---");

        TituloDAO dao = new TituloDAO();

        // --- Teste de gravação ---
        System.out.println("Criando títulos de teste para salvar...");
        List<Titulo> listaParaSalvar = new ArrayList<>();
        // Usando timestamps como IDs únicos para o teste
        listaParaSalvar.add(new Titulo(System.currentTimeMillis(), "Turma da Mônica", "Mauricio de Sousa", "Mauricio de Sousa"));
        listaParaSalvar.add(new Titulo(System.currentTimeMillis() + 1, "Batman: Ano Um", "DC Comics", "Frank Miller"));

        dao.salvar(listaParaSalvar);
        System.out.println("Títulos de teste salvos com sucesso!");

        // --- Teste de leitura ---
        List<Titulo> titulosLidos = dao.carregar();
        System.out.println("\n--- Títulos carregados do arquivo: ---");
        for (Titulo t : titulosLidos) {
            System.out.println("ID: " + t.getId() + ", Nome: " + t.getNome());
        }
        System.out.println("--- Teste finalizado. ---");
    }
}