package ifmt.cba.persistencia;

import java.time.LocalDate;
import java.util.List;

import ifmt.cba.dto.EstadoPedidoDTO;
import ifmt.cba.entity.Pedido;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class PedidoDAO extends DAO<Pedido>{
    
    public PedidoDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}

	public Pedido buscarPorCodigo(int codigo) throws PersistenciaException {

		Pedido pedido = null;

		try {
			pedido = this.entityManager.find(Pedido.class, codigo);
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por codigo - " + ex.getMessage());
		}
		return pedido;
	}

    @SuppressWarnings("unchecked")
	public List<Pedido> buscarPorEstado(EstadoPedidoDTO estado) throws PersistenciaException {
		List<Pedido> listaOrdemProducao = null;
		try {
			Query query = this.entityManager
					.createQuery("SELECT p FROM Pedido p WHERE UPPER(p.estado) = :pEstado");
			query.setParameter("pEstado", listaOrdemProducao);
			listaOrdemProducao = query.getResultList();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por estado - " + ex.getMessage());
		}
		return listaOrdemProducao;
	}

    @SuppressWarnings("unchecked")
	public List<Pedido> buscarPorDataPedido(LocalDate dataInicial, LocalDate dataFinal) throws PersistenciaException {
		List<Pedido> listaPedido = null;
		try {
			Query query = this.entityManager
					.createQuery("SELECT p FROM Pedido p WHERE p.dataPedido >= :pDataInicial AND p.dataPedido <= :pDataFinal");
			query.setParameter("pDataInicial", dataInicial);
            query.setParameter("pDataFinal", dataFinal);
			listaPedido = query.getResultList();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por data de pedido - " + ex.getMessage());
		}
		return listaPedido;
	}

}
