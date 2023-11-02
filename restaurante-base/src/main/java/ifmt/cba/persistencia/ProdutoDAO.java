package ifmt.cba.persistencia;

import java.util.List;
import ifmt.cba.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ProdutoDAO extends DAO<Produto>{
    
    public ProdutoDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}

	public Produto buscarPorCodigo(int codigo) throws PersistenciaException {

		Produto produto = null;

		try {
			produto = this.entityManager.find(Produto.class, codigo);
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por codigo - " + ex.getMessage());
		}
		return produto;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> buscarPorGrupoAlimentar(int codigoGrupo) throws PersistenciaException {
		List<Produto> listaProduto;
		try {
			Query query = this.entityManager
					.createQuery("SELECT p FROM Produto p WHERE p.grupoAlimentar.codigo = :codgrupo ORDER BY p.nome");
			query.setParameter("codgrupo", codigoGrupo);
			listaProduto = query.getResultList();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por grupo alimentar - " + ex.getMessage());
		}
		return listaProduto;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> buscarPorParteNome(String nome) throws PersistenciaException {
		List<Produto> listaProduto;
		try {
			Query query = this.entityManager
					.createQuery("SELECT p FROM Produto p WHERE UPPER(p.nome) LIKE :pNome ORDER BY p.nome");
			query.setParameter("pNome", "%" + nome.toUpperCase().trim() + "%");
			listaProduto = query.getResultList();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por parte do nome - " + ex.getMessage());
		}
		return listaProduto;
	}
}
