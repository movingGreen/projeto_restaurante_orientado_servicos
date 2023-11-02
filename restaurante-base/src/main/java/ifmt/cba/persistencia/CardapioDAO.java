package ifmt.cba.persistencia;

import ifmt.cba.entity.Cardapio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class CardapioDAO extends DAO<Cardapio>{

    public CardapioDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}

	public Cardapio buscarPorCodigo(int codigo) throws PersistenciaException {

		Cardapio cardapio = null;

		try {
			cardapio = this.entityManager.find(Cardapio.class, codigo);
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por codigo - " + ex.getMessage());
		}
		return cardapio;
	}

	public Cardapio buscarPorNome(String nome) throws PersistenciaException {
		Cardapio cardapio = null;
		try {
			Query query = this.entityManager
					.createQuery("SELECT c FROM Cardapio c WHERE UPPER(c.nome) = :pNome");
			query.setParameter("pNome", nome.toUpperCase().trim());
			cardapio = (Cardapio) query.getSingleResult();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por nome - " + ex.getMessage());
		}
		return cardapio;
	}
}
