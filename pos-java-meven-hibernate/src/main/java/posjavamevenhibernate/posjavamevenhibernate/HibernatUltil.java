package posjavamevenhibernate.posjavamevenhibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernatUltil {

	public static EntityManagerFactory factory = null;

	static {
		init();
	}

	private static void init() {

		try {

			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("pos-java-meven-hibernate");
			}
		} catch (Exception e) {
			System.out.println("Erro ao conectar com a base de dados! "+ e.getMessage());
			e.printStackTrace();
		}
	}

	public static EntityManager getEntityMeneger() { // *Prover a parte de Persistenceia*/

		return factory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entity) {				
		
		return factory.getPersistenceUnitUtil().getIdentifier(entity); /* Retorna a primary key*/
		
		
	}
}
