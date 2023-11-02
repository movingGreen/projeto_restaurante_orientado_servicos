package ifmt.cba.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import ifmt.cba.dto.EstadoPedidoDTO;
import ifmt.cba.dto.PedidoDTO;
import ifmt.cba.entity.Pedido;
import ifmt.cba.persistencia.PedidoDAO;
import ifmt.cba.persistencia.PersistenciaException;

public class PedidoNegocio {

    private ModelMapper modelMapper;
	private PedidoDAO pedidoDAO;

	public PedidoNegocio(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
		this.modelMapper = new ModelMapper();
	}

	public void inserir(PedidoDTO pedidoDTO) throws NegocioException {

		Pedido pedido = this.toEntity(pedidoDTO);
		String mensagemErros = pedido.validar();

		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}

		try {
			pedidoDAO.beginTransaction();
			pedidoDAO.incluir(pedido);
			pedidoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			pedidoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir pedido - " + ex.getMessage());
		}
	}

	public void alterar(PedidoDTO pedidoDTO) throws NegocioException {

		Pedido pedido = this.toEntity(pedidoDTO);
		String mensagemErros = pedido.validar();
		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}
		try {
			if (pedidoDAO.buscarPorCodigo(pedido.getCodigo()) == null) {
				throw new NegocioException("Nao existe esse pedido");
			}
			pedidoDAO.beginTransaction();
			pedidoDAO.alterar(pedido);
			pedidoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			pedidoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao alterar pedido - " + ex.getMessage());
		}
	}

	public void excluir(PedidoDTO pedidoDTO) throws NegocioException {

		Pedido pedido = this.toEntity(pedidoDTO);
		try {
			if (pedidoDAO.buscarPorCodigo(pedido.getCodigo()) == null) {
				throw new NegocioException("Nao existe esse pedido");
			}
			pedidoDAO.beginTransaction();
			pedidoDAO.excluir(pedido);
			pedidoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			pedidoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao excluir pedido - " + ex.getMessage());
		}
	}

    public List<PedidoDTO> pesquisaPorDataProducao(LocalDate dataInicial, LocalDate dataFinal) throws NegocioException {
		try {
			return this.toDTOAll(pedidoDAO.buscarPorDataPedido(dataInicial, dataFinal));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar pedido por data - " + ex.getMessage());
		}
	}

	public List<PedidoDTO> pesquisaPorEstado(EstadoPedidoDTO estado) throws NegocioException {
		try {
			return this.toDTOAll(pedidoDAO.buscarPorEstado(null));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar pedido pelo estado - " + ex.getMessage());
		}
	}

	public PedidoDTO pesquisaCodigo(int codigo) throws NegocioException {
		try {
			return this.toDTO(pedidoDAO.buscarPorCodigo(codigo));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar ordem de producao pelo codigo - " + ex.getMessage());
		}
	}

	public List<PedidoDTO> toDTOAll(List<Pedido> listaPedido) {
		List<PedidoDTO> listaDTO = new ArrayList<PedidoDTO>();

		for (Pedido pedido : listaPedido) {
			listaDTO.add(this.toDTO(pedido));
		}
		return listaDTO;
	}

	public PedidoDTO toDTO(Pedido pedido) {
		return this.modelMapper.map(pedido, PedidoDTO.class);
	}

	public Pedido toEntity(PedidoDTO pedidoDTO) {
		return this.modelMapper.map(pedidoDTO, Pedido.class);
	}
}
