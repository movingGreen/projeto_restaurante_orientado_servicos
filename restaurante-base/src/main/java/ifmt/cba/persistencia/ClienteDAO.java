package ifmt.cba.persistencia;

import java.util.List;

import ifmt.cba.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ClienteDAO extends DAO<Cliente> {

    public ClienteDAO(EntityManager entityManager) throws PersistenciaException {
        super(entityManager);
    }

    public Cliente buscarPorCodigo(int codigo) throws PersistenciaException {

        Cliente cliente = null;

        try {
            cliente = this.entityManager.find(Cliente.class, codigo);
        } catch (Exception ex) {
            throw new PersistenciaException("Erro na selecao por codigo - " + ex.getMessage());
        }
        return cliente;
    }

    public Cliente buscarPorCPF(String CPF) throws PersistenciaException {
        Cliente cliente = null;
        try {
            Query query = this.entityManager
                    .createQuery("SELECT c FROM Cliente c WHERE UPPER(c.CPF) = :pCPF");
            query.setParameter("pCPF", CPF.toUpperCase().trim());
            cliente = (Cliente) query.getSingleResult();
        } catch (Exception ex) {
            throw new PersistenciaException("Erro na selecao por CPF - " + ex.getMessage());
        }
        return cliente;
    }

    @SuppressWarnings("unchecked")
    public List<Cliente> buscarPorParteNome(String nome) throws PersistenciaException {
        List<Cliente> listaCliente;
        try {
            Query query = this.entityManager
                    .createQuery("SELECT c FROM Cliente c WHERE UPPER(c.nome) LIKE :pNome ORDER BY c.nome");
            query.setParameter("pNome", "%" + nome.toUpperCase().trim() + "%");
            listaCliente = query.getResultList();
        } catch (Exception ex) {
            throw new PersistenciaException("Erro na selecao por parte do nome - " + ex.getMessage());
        }
        return listaCliente;
    }

}
