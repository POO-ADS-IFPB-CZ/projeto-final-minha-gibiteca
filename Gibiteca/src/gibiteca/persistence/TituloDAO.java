package gibiteca.persistence;

import gibiteca.model.Titulo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TituloDAO {

    //Define o nome do arquivo que guarda os dados. Sera criado na raiz do projeto.
    private static final String FILE_PATH = "titulos.csv";
    private final Path path;

    public TituloDAO() {
        this.path = Paths.get(FILE_PATH);
    }

    /**
     * Carrega os titulos do CSV.
     *
     * @return uma lista de títulos.
     */
    public List<Titulo> carregar() {
        List<Titulo> titulos = new ArrayList<>();

        // Se o arquivo existir, lê os dados.
        if (Files.exists(path)) {
            try {
                List<String> linhas = Files.readAllLines(path);
                for (String linha : linhas) {
                    // Separa os dados de cada linha pelo delimitador ';'
                    String[] dados = linha.split(";");
                    if (dados.length == 4) {
                        long id = Long.parseLong(dados[0]);
                        String nome = dados[1];
                        String editora = dados[2];
                        String autor = dados[3];
                        titulos.add(new Titulo(id, nome, editora, autor));
                    }
                }
            } catch (IOException e) {
                // Caso de erro na leitura.
                System.err.println("Erro ao carregar títulos do arquivo: " + e.getMessage());
            }
        }
        return titulos;
    }

    /**
     * Salva a lista de Títulos no arquivo CSV, sobrescrevendo o conteúdo anterior.
     *
     * @param titulos A lista de títulos a ser salva.
     */
    public void salvar(List<Titulo> titulos) {
        List<String> linhas = new ArrayList<>();
        // Converte cada objeto Titulo em uma lista formulada para CSV.
        for (Titulo titulo : titulos) {
            String linha = String.format("%d;%s;%s;%s",
                    titulo.getId(),
                    titulo.getNome(),
                    titulo.getEditora(),
                    titulo.getAutor());
            linhas.add(linha);
        }
        try {
            // Escreve todas as linhas no arquivo de uma só vez.
            Files.write(path, linhas);
        } catch (IOException e) {
            System.err.println("Erro so salvar títulos no arquivo: " + e.getMessage());
        }
    }
}

