package posjavamevenhibernate.posjavamevenhibernate;

import java.util.List;

import org.junit.Test;

import Dao.DaoGeneric;
import Model.UsuarioPessoa;

public class AppTest {

	@Test
	public void HibernateUl() {
		// HibernatUltil.getEntityMeneger();

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setIdade(54);
		pessoa.setNome("JANDIRA MARIA");
		pessoa.setSobreNome("DOS SANTOS");
		pessoa.setLogin("pedrosa");
		pessoa.setSenha("12345");
		pessoa.setEmail("pedrosa.santos@gmail.com");

		daoGeneric.salvar(pessoa);
	}

	@Test
	public void testeBuscar() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setId(2L);

		pessoa = daoGeneric.pesquisar(pessoa);
		System.out.println(pessoa);

	}

	@Test
	public void testeDeletPoId() {// DELETA POR ID

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa = daoGeneric.pesquisarPoId(5L, UsuarioPessoa.class);
		daoGeneric.deletePoId(pessoa);
		System.out.println("Registro deletado com sucesso!");
	}

	@Test
	public void testeUpadete() {

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setId(3L);
		pessoa.setIdade(99);
		pessoa.setNome("MARIA MIGUELINA");
		pessoa.setSenha("123");
		pessoa.setEmail("maria.@gmail.com");
		pessoa = daoGeneric.updateMerge(pessoa);

		System.out.println(pessoa);

	}

	@Test
	public void listaRegistros() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.listar(UsuarioPessoa.class);

		for (UsuarioPessoa usuarioPessoa : list) {

			System.out.println(usuarioPessoa);
			System.out.println(
					"-------------------------------------------------------------------------------------------");
		}
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testQueryList() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome like '%s'").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}

	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testQueryListOrdenada() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().
				createQuery("from UsuarioPessoa order by id")
				.setMaxResults(4)
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}

	}
}
