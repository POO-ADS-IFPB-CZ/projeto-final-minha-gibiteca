package gibiteca.controller;

import gibiteca.model.Titulo;
import gibiteca.persistence.TituloDAO;

import java.util.ArrayList;
import java.util.List;

public class TituloController {

    private final TituloDAO dao;
    private final List<Titulo> titulosCache; // Mantém a lista carregada em memória

    public TituloController() {
        dao = new TituloDAO();
        titulosCache = new ArrayList<>(dao.carregar());
    }

    // Retorna todos os títulos
    public List<Titulo> listarTodos() {
        return new ArrayList<>(titulosCache);
    }

    // Cadastrar um novo título
    public void cadastrar(Titulo titulo) {
        titulosCache.add(titulo);
        dao.salvar(titulosCache);
    }

    // Atualizar um título existente
    public void atualizar(Titulo titulo) {
        for (int i = 0; i < titulosCache.size(); i++) {
            if (titulosCache.get(i).getId() == titulo.getId()) {
                titulosCache.set(i, titulo);
                dao.salvar(titulosCache);
                return;
            }
        }
    }

    // Excluir um título
    public void excluir(Titulo titulo) {
        titulosCache.removeIf(t -> t.getId() == titulo.getId());
        dao.salvar(titulosCache);
    }

    // Buscar por ID (opcional, pode ser útil)
    public Titulo buscarPorId(long id) {
        return titulosCache.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }
}