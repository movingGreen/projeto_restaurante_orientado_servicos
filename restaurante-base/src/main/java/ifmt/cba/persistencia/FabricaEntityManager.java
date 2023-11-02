package ifmt.cba.persistencia;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class FabricaEntityManager {

	private static EntityManagerFactory emf_producao;
	private static EntityManagerFactory emf_teste;
	public static final String UNIDADE_PRODUCAO = "restaurante_producao";
	public static final String UNIDADE_TESTE = "restaurante_teste";

	private FabricaEntityManager() {

	}

	public static EntityManager getEntityManagerProducao() {
		if (emf_producao == null) {
			emf_producao = Persistence.createEntityManagerFactory(UNIDADE_PRODUCAO);
		}
		return emf_producao.createEntityManager();
	}

	public static EntityManager getEntityManagerTeste() {
		if (emf_teste == null) {
			emf_teste = Persistence.createEntityManagerFactory(UNIDADE_TESTE);
		}
		return emf_teste.createEntityManager();
	}
}
