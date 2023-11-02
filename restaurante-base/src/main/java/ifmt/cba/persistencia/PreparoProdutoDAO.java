package ifmt.cba.persistencia;

import java.util.List;

import ifmt.cba.entity.PreparoProduto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class PreparoProdutoDAO extends DAO<PreparoProduto> {

    public PreparoProdutoDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}

	public PreparoProduto buscarPorCodigo(int codigo) throws PersistenciaException {

		PreparoProduto preparoProduto = null;

		try {
			preparoProduto = this.entityManager.find(PreparoProduto.class, codigo);
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por codigo - " + ex.getMessage());
		}
		return preparoProduto;
	}

	@SuppressWarnings("unchecked")
	public List<PreparoProduto> buscarPorProduto(int codigoProduto) throws PersistenciaException {
		List<PreparoProduto> listaProduto;
		try {
			Query query = this.entityManager
					.createQuery("SELECT pp FROM PreparoProduto pp WHERE pp.produto.codigo = :codproduto");
			query.setParameter("codproduto", codigoProduto);
			listaProduto = query.getResultList();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por produto - " + ex.getMessage());
		}
		return listaProduto;
	}

    @SuppressWarnings("unchecked")
	public List<PreparoProduto> buscarPorTipoPreparo(int codigoTipoPreparo) throws PersistenciaException {
		List<PreparoProduto> listaProduto;
		try {
			Query query = this.entityManager
					.createQuery("SELECT pp FROM PreparoProduto pp WHERE pp.tipoPreparo.codigo = :codtipo");
			query.setParameter("codtipo", codigoTipoPreparo);
			listaProduto = query.getResultList();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por tipo preparo - " + ex.getMessage());
		}
		return listaProduto;
	}

	public PreparoProduto buscarPorProdutoETipoPreparo(int codigoProduto, int codigoTipoPreparo) throws PersistenciaException {
		PreparoProduto preparoProduto;
		try {
			Query query = this.entityManager
					.createQuery("SELECT pp FROM PreparoProduto pp WHERE pp.produto.codigo = :codproduto AND pp.tipoPreparo.codigo = :codtipo");
			query.setParameter("codproduto", codigoProduto);
            query.setParameter("codtipo", codigoTipoPreparo);
			preparoProduto = (PreparoProduto) query.getSingleResult();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por produto e tipo preparo - " + ex.getMessage());
		}
		return preparoProduto;
	}

}
