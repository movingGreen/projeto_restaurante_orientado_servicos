package ifmt.cba.negocio;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import ifmt.cba.dto.CardapioDTO;
import ifmt.cba.entity.Cardapio;
import ifmt.cba.persistencia.CardapioDAO;
import ifmt.cba.persistencia.PersistenciaException;

public class CardapioNegocio {
    private ModelMapper modelMapper;
	private CardapioDAO cardapioDAO;

	public CardapioNegocio(CardapioDAO cardapioDAO) {
		this.cardapioDAO = cardapioDAO;
		this.modelMapper = new ModelMapper();
	}

	public void inserir(CardapioDTO cardapioDTO) throws NegocioException {

		Cardapio cardapio = this.toEntity(cardapioDTO);
		String mensagemErros = cardapio.validar();

		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}

		try {
			if (cardapioDAO.buscarPorNome(cardapio.getNome()) != null) {
				throw new NegocioException("Ja existe esse cardapio");
			}
			
			cardapioDAO.beginTransaction();
			cardapioDAO.incluir(cardapio);
			cardapioDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			cardapioDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir o cardapio - " + ex.getMessage());
		}
	}

	public void alterar(CardapioDTO cardapioDTO) throws NegocioException {

		Cardapio cardapio = this.toEntity(cardapioDTO);
		String mensagemErros = cardapio.validar();
		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}
		try {
			if (cardapioDAO.buscarPorCodigo(cardapio.getCodigo()) == null) {
				throw new NegocioException("Nao existe esse cardapio");
			}
			cardapioDAO.beginTransaction();
			cardapioDAO.alterar(cardapio);
			cardapioDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			cardapioDAO.rollbackTransaction();
			throw new NegocioException("Erro ao alterar o cardapio - " + ex.getMessage());
		}
	}

	public void excluir(CardapioDTO cardapioDTO) throws NegocioException {

		Cardapio cardapio = this.toEntity(cardapioDTO);
		try {
			if (cardapioDAO.buscarPorCodigo(cardapio.getCodigo()) == null) {
				throw new NegocioException("Nao existe esse cardapio");
			}
			cardapioDAO.beginTransaction();
			cardapioDAO.excluir(cardapio);
			cardapioDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			cardapioDAO.rollbackTransaction();
			throw new NegocioException("Erro ao excluir o cardapio - " + ex.getMessage());
		}
	}

	public CardapioDTO pesquisaPorNome(String nome) throws NegocioException {
		try {
			return this.toDTO(cardapioDAO.buscarPorNome(nome));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar cardapio pelo nome - " + ex.getMessage());
		}
	}

	public CardapioDTO pesquisaCodigo(int codigo) throws NegocioException {
		try {
			return this.toDTO(cardapioDAO.buscarPorCodigo(codigo));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar cardapio pelo codigo - " + ex.getMessage());
		}
	}

	public List<CardapioDTO> toDTOAll(List<Cardapio> listaCardapio) {
		List<CardapioDTO> listDTO = new ArrayList<CardapioDTO>();

		for (Cardapio cardapio : listaCardapio) {
			listDTO.add(this.toDTO(cardapio));
		}
		return listDTO;
	}

	public CardapioDTO toDTO(Cardapio cardapio) {
		return this.modelMapper.map(cardapio, CardapioDTO.class);
	}

	public Cardapio toEntity(CardapioDTO cardapioDTO) {
		return this.modelMapper.map(cardapioDTO, Cardapio.class);
	}
}
