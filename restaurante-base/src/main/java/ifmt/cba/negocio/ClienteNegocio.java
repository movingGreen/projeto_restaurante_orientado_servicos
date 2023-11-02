package ifmt.cba.negocio;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import ifmt.cba.dto.ClienteDTO;
import ifmt.cba.entity.Cliente;
import ifmt.cba.persistencia.ClienteDAO;
import ifmt.cba.persistencia.PersistenciaException;

public class ClienteNegocio {

    private ModelMapper modelMapper;
	private ClienteDAO clienteDAO;

	public ClienteNegocio(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
		this.modelMapper = new ModelMapper();
	}

	public void inserir(ClienteDTO clienteDTO) throws NegocioException {

		Cliente cliente = this.toEntity(clienteDTO);
		String mensagemErros = cliente.validar();

		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}

		try {
			if (clienteDAO.buscarPorCPF(cliente.getCPF()) != null) {
				throw new NegocioException("Ja existe esse cliente");
			}
			clienteDAO.beginTransaction();
			clienteDAO.incluir(cliente);
			clienteDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			clienteDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir o cliente - " + ex.getMessage());
		}
	}

	public void alterar(ClienteDTO clienteDTO) throws NegocioException {

		Cliente cliente = this.toEntity(clienteDTO);
		String mensagemErros = cliente.validar();
		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}
		try {
			if (clienteDAO.buscarPorCodigo(cliente.getCodigo()) == null) {
				throw new NegocioException("Nao existe esse cliente");
			}
			clienteDAO.beginTransaction();
			clienteDAO.alterar(cliente);
			clienteDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			clienteDAO.rollbackTransaction();
			throw new NegocioException("Erro ao alterar o cliente - " + ex.getMessage());
		}
	}

	public void excluir(ClienteDTO clienteDTO) throws NegocioException {

		Cliente cliente = this.toEntity(clienteDTO);
		try {
			if (clienteDAO.buscarPorCodigo(cliente.getCodigo()) == null) {
				throw new NegocioException("Nao existe esse cliente");
			}
			clienteDAO.beginTransaction();
			clienteDAO.excluir(cliente);
			clienteDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			clienteDAO.rollbackTransaction();
			throw new NegocioException("Erro ao excluir o cliente - " + ex.getMessage());
		}
	}

	public List<ClienteDTO> pesquisaParteNome(String parteNome) throws NegocioException {
		try {
			return this.toDTOAll(clienteDAO.buscarPorParteNome(parteNome));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar cliente pelo nome - " + ex.getMessage());
		}
	}

	public ClienteDTO pesquisaCodigo(int codigo) throws NegocioException {
		try {
			return this.toDTO(clienteDAO.buscarPorCodigo(codigo));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar cliente pelo codigo - " + ex.getMessage());
		}
	}

	public List<ClienteDTO> toDTOAll(List<Cliente> listaCliente) {
		List<ClienteDTO> listaDTO = new ArrayList<ClienteDTO>();

		for (Cliente cliente : listaCliente) {
			listaDTO.add(this.toDTO(cliente));
		}
		return listaDTO;
	}

	public ClienteDTO toDTO(Cliente cliente) {
		return this.modelMapper.map(cliente, ClienteDTO.class);
	}

	public Cliente toEntity(ClienteDTO clienteDTO) {
		return this.modelMapper.map(clienteDTO, Cliente.class);
	}
}
