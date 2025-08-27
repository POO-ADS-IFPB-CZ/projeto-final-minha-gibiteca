
package gibiteca.controller;

import gibiteca.model.Titulo;
import gibiteca.persistence.TituloDAO;
import java.util.ArrayList;
import java.util.List;

public class TituloController {
	private final TituloDAO dao;
	private final List<Titulo> cache;

	public TituloController() {
		this.dao = new TituloDAO();
		this.cache = new ArrayList<>(dao.carregar());
	}

	public List<Titulo> listarTodos() {
		return new ArrayList<>(cache); // cópia defensiva
	}

	public void adicionar(Titulo t) {
		cache.add(t);
		dao.salvar(cache);
	}

	public void atualizar(Titulo t) {
		for (int i = 0; i < cache.size(); i++) {
			if (cache.get(i).getId() == t.getId()) {
				cache.set(i, t);
				dao.salvar(cache);
				return;
			}
		}
		// se não encontrou pelo id, atualiza pelo nome (fallback)
		for (int i = 0; i < cache.size(); i++) {
			if (safeEquals(cache.get(i).getNome(), t.getNome())) {
				t.setId(cache.get(i).getId());
				cache.set(i, t);
				dao.salvar(cache);
				return;
			}
		}
	}

	public void excluir(long id) {
		cache.removeIf(x -> x.getId() == id);
		dao.salvar(cache);
	}

	public void remover(Titulo t) {
		cache.removeIf(x -> x.getId() == t.getId() || (x.getId() == 0 && safeEquals(x.getNome(), t.getNome())));
		dao.salvar(cache);
	}

	private static boolean safeEquals(String a, String b) {
		if (a == null) return b == null;
		return a.equals(b);
	}
}
