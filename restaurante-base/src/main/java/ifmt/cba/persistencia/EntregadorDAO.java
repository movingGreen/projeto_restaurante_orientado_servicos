package ifmt.cba.persistencia;

import java.util.List;

import ifmt.cba.entity.Cliente;
import ifmt.cba.entity.Entregador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class EntregadorDAO extends DAO<Entregador>{
    
    public EntregadorDAO(EntityManager entityManager) throws PersistenciaException {
		super(entityManager);
	}

    public Entregador buscarPorCodigo(int codigo) throws PersistenciaException {

        Entregador entregador = null;

        try {
            entregador = this.entityManager.find(Entregador.class, codigo);
        } catch (Exception ex) {
            throw new PersistenciaException("Erro na selecao por codigo - " + ex.getMessage());
        }
        return entregador;
    }

    public Entregador buscarPorCPF(String CPF) throws PersistenciaException {
        Entregador entregador = null;
        try {
            Query query = this.entityManager
                    .createQuery("SELECT e FROM Entregador e WHERE UPPER(e.CPF) = :pCPF");
            query.setParameter("pCPF", CPF.toUpperCase().trim());
            entregador = (Entregador) query.getSingleResult();
        } catch (Exception ex) {
            throw new PersistenciaException("Erro na selecao por CPF - " + ex.getMessage());
        }
        return entregador;
    }

    @SuppressWarnings("unchecked")
    public List<Entregador> buscarPorParteNome(String nome) throws PersistenciaException {
        List<Entregador> listaEntregador;
        try {
            Query query = this.entityManager
                    .createQuery("SELECT e FROM Entregador e WHERE UPPER(e.nome) LIKE :pNome ORDER BY e.nome");
            query.setParameter("pNome", "%" + nome.toUpperCase().trim() + "%");
            listaEntregador = query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Erro na selecao por parte do nome - " + ex.getMessage());
        }
        return listaEntregador;
    }

}
