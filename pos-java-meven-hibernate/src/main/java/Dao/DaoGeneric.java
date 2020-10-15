package Dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import posjavamevenhibernate.posjavamevenhibernate.HibernatUltil;

public class DaoGeneric<E> {

	private EntityManager entityMeneger = HibernatUltil.getEntityMeneger();

	public void salvar(E entidade) {
		EntityTransaction transaction = entityMeneger.getTransaction();
		transaction.begin(); // Inicia a Transação
		entityMeneger.persist(entidade); // Salva os dados na base de dados
		transaction.commit();

	}

	public E updateMerge(E entidade) {// SALVA OU ATUALIZA
		EntityTransaction transaction = entityMeneger.getTransaction();
		transaction.begin(); // Inicia a Transação
		E entidadeSalva = entityMeneger.merge(entidade); // Salva e atualiza os dados na base de dados
		transaction.commit();

		return entidadeSalva;
	}

	@SuppressWarnings("unchecked")
	public E pesquisar(E entidade) {

		Object id = HibernatUltil.getPrimaryKey(entidade);
		E e = (E) entityMeneger.find(entidade.getClass(), id);
		return e;
	}

	public E pesquisarPoId(Long id, Class<E> entidade) {

		E e = (E) entityMeneger.find(entidade, id);

		return e;
	}

	public void deletePoId(E entidade) {

		Object id = HibernatUltil.getPrimaryKey(entidade);
		EntityTransaction transation = entityMeneger.getTransaction();
		transation.begin();

		entityMeneger
				.createNativeQuery(
						"delete from " + entidade.getClass().getSimpleName().toLowerCase() + " where id = " + id)
				.executeUpdate(); // FAZ O DELETE SO REGISTRO
		transation.commit(); // GRAVA A AUTERAÇÃO NO BANCO
	}

	public List<E> listar(Class<E> entidade) { // RETORNA UMA LISTA DE REGISTRO

		EntityTransaction transaction = entityMeneger.getTransaction();
		transaction.begin();

		List<E> lista = entityMeneger.createQuery("from " + entidade.getName()).getResultList();

		transaction.commit();

		return lista;

	}

	public EntityManager getEntityManager() {
		return entityMeneger;

	}
}
