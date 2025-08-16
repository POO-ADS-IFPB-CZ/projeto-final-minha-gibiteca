package gibiteca.controller;

import gibiteca.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemController {
    private List<Personagem> personagens;

    public PersonagemController() {
        this.personagens = new ArrayList<>();
    }

    public void adicionar(Personagem p) {
        personagens.add(p);
    }

    public void editar(int id, String novoNome, String novaAparicao) {
        for (Personagem p : personagens) {
            if (p.getId() == id) {
                p.setNome(novoNome);
                p.setPrimeiraAparicao(novaAparicao);
                break;
            }
        }
    }

    public void excluir(int id) {
        personagens.removeIf(p -> p.getId() == id);
    }

    public List<Personagem> listar() {
        return personagens;
    }
}
