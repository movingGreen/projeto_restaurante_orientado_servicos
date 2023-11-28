package ifmt.cba.negocio;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import ifmt.cba.dto.ProdutoDTO;
import ifmt.cba.entity.Produto;
import ifmt.cba.persistencia.PersistenciaException;
import ifmt.cba.persistencia.ProdutoDAO;

public class ProdutoNegocio {
	private ModelMapper modelMapper;
	private ProdutoDAO produtoDAO;

	public ProdutoNegocio(ProdutoDAO produtoDAO) {
		this.produtoDAO = produtoDAO;
		this.modelMapper = new ModelMapper();
	}

	public void inserir(ProdutoDTO produtoDTO) throws NegocioException {

		Produto produto = this.toEntity(produtoDTO);
		String mensagemErros = produto.validar();

		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}

		try {
			if (!produtoDAO.buscarPorParteNome(produto.getNome()).isEmpty()) {
				throw new NegocioException("Ja existe esse produto");
			}
			produtoDAO.beginTransaction();
			produtoDAO.incluir(produto);
			produtoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			produtoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir o produto - " + ex.getMessage());
		}
	}

	public void alterar(ProdutoDTO produtoDTO) throws NegocioException {

		Produto produto = this.toEntity(produtoDTO);
		String mensagemErros = produto.validar();
		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}
		try {
			if (produtoDAO.buscarPorCodigo(produto.getCodigo()) == null) {
				throw new NegocioException("Nao existe esse produto");
			}
			produtoDAO.beginTransaction();
			produtoDAO.alterar(produto);
			produtoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			produtoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao alterar o produto - " + ex.getMessage());
		}
	}

	public void excluir(ProdutoDTO produtoDTO) throws NegocioException {

		Produto produto = this.toEntity(produtoDTO);
		try {
			if (produtoDAO.buscarPorCodigo(produto.getCodigo()) == null) {
				throw new NegocioException("Nao existe esse produto");
			}
			produtoDAO.beginTransaction();
			produtoDAO.excluir(produto);
			produtoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			produtoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao excluir o produto - " + ex.getMessage());
		}
	}

	
	public List<ProdutoDTO> pesquisaParteNome(String parteNome) throws NegocioException {
		try {
			return this.toDTOAll(produtoDAO.buscarPorParteNome(parteNome));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar produto pelo nome - " + ex.getMessage());
		}
	}

	public ProdutoDTO pesquisaCodigo(int codigo) throws NegocioException {
		try {
			return this.toDTO(produtoDAO.buscarPorCodigo(codigo));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar produto pelo codigo - " + ex.getMessage());
		}
	}

	public List<ProdutoDTO> toDTOAll(List<Produto> listaProduto) {
		List<ProdutoDTO> listaDTO = new ArrayList<ProdutoDTO>();

		for (Produto produto : listaProduto) {
			listaDTO.add(this.toDTO(produto));
		}
		return listaDTO;
	}

	public ProdutoDTO toDTO(Produto produto) {
		return this.modelMapper.map(produto, ProdutoDTO.class);
	}

	public Produto toEntity(ProdutoDTO produtoDTO) {
		return this.modelMapper.map(produtoDTO, Produto.class);
	}
}
