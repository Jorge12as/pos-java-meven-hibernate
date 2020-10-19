package posjavamevenhibernate.posjavamevenhibernate;

import java.util.List;

import org.junit.Test;

import Dao.DaoGeneric;
import Model.TelefoneUser;
import Model.UsuarioPessoa;

public class AppTest {

	@Test
	public void HibernateUl() {
		// HibernatUltil.getEntityMeneger();

		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		UsuarioPessoa pessoa = new UsuarioPessoa();

		pessoa.setIdade(66);
		pessoa.setNome("FABIANA GUIMARAÊS");
		pessoa.setSobreNome("DAS DORES");
		pessoa.setLogin("pedrosa");
		pessoa.setSenha("12345");
		pessoa.setEmail("guimaraes.ce@gmail.com");

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
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa where nome like '%s'")
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}

	}

	@Test
	@SuppressWarnings("unchecked")
	public void testQueryListOrdenada() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createQuery("from UsuarioPessoa order by id")
				.setMaxResults(4).getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testeQueryPorParameter() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager()
				.createQuery("from UsuarioPessoa where nome = :nome or " + "sobrenome = :sobrenome")
				.setParameter("nome", "JORGE").setParameter("sobrenome", "DOS SANTOS").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	// METODO QUE RETORNA A SOMA DE TODAS AS IDADES DOS USUARIOS NO BASE
	@Test
	public void testSomaQueryIdade() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();

		Long somaIdade = (Long) daoGeneric.getEntityManager().createQuery("select sum(u.idade) from UsuarioPessoa u")
				.getSingleResult();

		System.out.println("A soma de todas as idades é ---> " + somaIdade);

	}

	@Test
	public void listarUsuarioPorNamedQuery() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.todos")
				.getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void listarUsuarioPorNamedQueryPorParametro() {
		DaoGeneric<UsuarioPessoa> daoGeneric = new DaoGeneric<UsuarioPessoa>();
		List<UsuarioPessoa> list = daoGeneric.getEntityManager().createNamedQuery("UsuarioPessoa.buscaPorParamet")
				.setParameter("nome", "ALISON PEDROSA").getResultList();

		for (UsuarioPessoa usuarioPessoa : list) {
			System.out.println(usuarioPessoa);
		}
	}

	// GRAVANDO OS TELEFONES NA TABELA TELEFONE
	@Test
	public void gravarTelefones() {

		DaoGeneric daoGeneric = new DaoGeneric();

		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisarPoId(8L, UsuarioPessoa.class);
		TelefoneUser telefoneUser = new TelefoneUser();

		telefoneUser.setNumero("(98)3256-2121)");
		telefoneUser.setTipo("CASA");
		telefoneUser.setUsuarioPessa(pessoa);

		daoGeneric.salvar(telefoneUser);

	}

	@Test
	public void consultarTelefonesPorParamt() {
		
		DaoGeneric daoGeneric = new DaoGeneric();
		UsuarioPessoa pessoa = (UsuarioPessoa) daoGeneric.pesquisarPoId(1L, UsuarioPessoa.class);

		for (TelefoneUser fone : pessoa.getTelefoneUsers()) {
			System.out.println("Id: " +fone.getUsuarioPessa().getId());
			System.out.println("Numero: " +fone.getNumero());
			System.out.println("Tipo: " +fone.getTipo());			
			System.out.println("Nome: "+fone.getUsuarioPessa().getNome());
			System.out.println("------------------------------------------------------");
		}

	}

}
