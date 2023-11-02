package ifmt.cba.persistencia;

import java.time.LocalDate;
import java.util.List;

import ifmt.cba.dto.EstadoOrdemProducaoDTO;
import ifmt.cba.entity.OrdemProducao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class OrdemProducaoDAO extends DAO<OrdemProducao>{
    
    public OrdemProducaoDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}

	public OrdemProducao buscarPorCodigo(int codigo) throws PersistenciaException {

		OrdemProducao ordemProducao = null;

		try {
			ordemProducao = this.entityManager.find(OrdemProducao.class, codigo);
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por codigo - " + ex.getMessage());
		}
		return ordemProducao;
	}

    @SuppressWarnings("unchecked")
	public List<OrdemProducao> buscarPorEstado(EstadoOrdemProducaoDTO estado) throws PersistenciaException {
		List<OrdemProducao> listaOrdemProducao = null;
		try {
			Query query = this.entityManager
					.createQuery("SELECT op FROM OrdemProducao op WHERE UPPER(op.estado) = :pEstado");
			query.setParameter("pEstado", listaOrdemProducao);
			listaOrdemProducao = query.getResultList();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por estado - " + ex.getMessage());
		}
		return listaOrdemProducao;
	}

    @SuppressWarnings("unchecked")
	public List<OrdemProducao> buscarPorDataProducao(LocalDate dataInicial, LocalDate dataFinal) throws PersistenciaException {
		List<OrdemProducao> listaOrdemProducao = null;
		try {
			Query query = this.entityManager
					.createQuery("SELECT op FROM OrdemProducao op WHERE op.dataProducao >= :pDataInicial AND op.dataProducao <= :pDataFinal");
                query.setParameter("pDataInicial", dataInicial);
                query.setParameter("pDataFinal", dataFinal);
			listaOrdemProducao = query.getResultList();
		} catch (Exception ex) {
			throw new PersistenciaException("Erro na selecao por data de producao - " + ex.getMessage());
		}
		return listaOrdemProducao;
	}

}
