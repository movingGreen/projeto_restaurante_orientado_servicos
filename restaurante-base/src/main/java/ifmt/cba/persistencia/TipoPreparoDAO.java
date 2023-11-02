package ifmt.cba.persistencia;

import java.util.List;
import ifmt.cba.entity.TipoPreparo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class TipoPreparoDAO extends DAO<TipoPreparo>{

    public TipoPreparoDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}

	public TipoPreparo buscarPorCodigo(int codigo) throws PersistenciaException {

		TipoPreparo tipoPreparo = null;

		try {
			tipoPreparo = this.entityManager.find(TipoPreparo.class, codigo);
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por codigo - " + ex.getMessage());
		}
		return tipoPreparo;
	}

	public TipoPreparo buscarPorDescricao(String descricao) throws PersistenciaException {
		TipoPreparo tipoPreparo = null;
		try {
			Query query = this.entityManager
					.createQuery("SELECT tp FROM TipoPreparo tp WHERE UPPER(tp.descricao) = :pDesc");
			query.setParameter("pDesc", descricao.toUpperCase().trim());
			tipoPreparo = (TipoPreparo) query.getSingleResult();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por descricao - " + ex.getMessage());
		}
		return tipoPreparo;
	}

	@SuppressWarnings("unchecked")
	public List<TipoPreparo> buscarPorParteDescricao(String descricao) throws PersistenciaException {
		List<TipoPreparo> listaGrupoAlimentar;
		try {
			Query query = this.entityManager
					.createQuery("SELECT tp FROM TipoPreparo tp WHERE UPPER(tp.descricao) LIKE :pDesc ORDER BY tp.descricao");
			query.setParameter("pDesc", "%" + descricao.toUpperCase().trim() + "%");
			listaGrupoAlimentar = query.getResultList();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por parte da descricao - " + ex.getMessage());
		}
		return listaGrupoAlimentar;
	}
}
