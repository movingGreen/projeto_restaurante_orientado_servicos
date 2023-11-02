package ifmt.cba.persistencia;

import java.util.List;
import ifmt.cba.entity.GrupoAlimentar;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


public class GrupoAlimentarDAO extends DAO<GrupoAlimentar> {

	public GrupoAlimentarDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}

	public GrupoAlimentar buscarPorCodigo(int codigo) throws PersistenciaException {

		GrupoAlimentar grupoAlimentar = null;

		try {
			grupoAlimentar = this.entityManager.find(GrupoAlimentar.class, codigo);
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por codigo - " + ex.getMessage());
		}
		return grupoAlimentar;
	}

	@SuppressWarnings("unchecked")
	public List<GrupoAlimentar> buscarPorParteNome(String nome) throws PersistenciaException {
		List<GrupoAlimentar> listaGrupoAlimentar;
		try {
			Query query = this.entityManager
					.createQuery("SELECT ga FROM GrupoAlimentar ga WHERE UPPER(ga.nome) LIKE :pNome ORDER BY ga.nome");
			query.setParameter("pNome", "%" + nome.toUpperCase().trim() + "%");
			listaGrupoAlimentar = query.getResultList();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por parte do nome - " + ex.getMessage());
		}
		return listaGrupoAlimentar;
	}
}
