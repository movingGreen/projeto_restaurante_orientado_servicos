package ifmt.cba.negocio;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import ifmt.cba.dto.TipoPreparoDTO;
import ifmt.cba.entity.TipoPreparo;
import ifmt.cba.persistencia.PersistenciaException;
import ifmt.cba.persistencia.TipoPreparoDAO;

public class TipoPreparoNegocio {

    private ModelMapper modelMapper;
	private TipoPreparoDAO tipoPreparoDAO;
	
	public TipoPreparoNegocio(TipoPreparoDAO tipoPreparoDAO) throws NegocioException {

		this.tipoPreparoDAO = tipoPreparoDAO;
		this.modelMapper = new ModelMapper();
	}

	public void inserir(TipoPreparoDTO tipoPreparoDTO) throws NegocioException {

		TipoPreparo tipoPreparo = this.toEntity(tipoPreparoDTO);
		String mensagemErros = tipoPreparo.validar();

		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}

		try {
			// não pode existir outro com a mesma descricao
			if (tipoPreparoDAO.buscarPorDescricao(tipoPreparo.getDescricao()) != null) {
				throw new NegocioException("Ja existe esse tipo de preparo");
			}
			tipoPreparoDAO.beginTransaction();
			tipoPreparoDAO.incluir(tipoPreparo);
			tipoPreparoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			tipoPreparoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir o tipo de preparo - " + ex.getMessage());
		}
	}

	public void alterar(TipoPreparoDTO tipoPreparoDTO) throws NegocioException {

		TipoPreparo tipoPreparo = this.toEntity(tipoPreparoDTO);
		String mensagemErros = tipoPreparo.validar();
		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}
		try {
			// deve existir para ser alterado
			if (tipoPreparoDAO.buscarPorCodigo(tipoPreparo.getCodigo()) == null) {
				throw new NegocioException("Nao existe esse tipo de preparo");
			}
			tipoPreparoDAO.beginTransaction();
			tipoPreparoDAO.alterar(tipoPreparo);
			tipoPreparoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			tipoPreparoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao alterar o tipo de preparo - " + ex.getMessage());
		}
	}

	public void excluir(TipoPreparoDTO tipoPreparoDTO) throws NegocioException {

		TipoPreparo tipoPreparo = this.toEntity(tipoPreparoDTO);
		try {
			tipoPreparoDAO.beginTransaction();
			tipoPreparoDAO.excluir(tipoPreparo);
			tipoPreparoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			tipoPreparoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao excluir o tipo de preparo - " + ex.getMessage());
		}
	}

	public List<TipoPreparoDTO> pesquisaParteDescricao(String parteDesc) throws NegocioException {
		try {

			return this.toDTOAll(tipoPreparoDAO.buscarPorParteDescricao(parteDesc));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar tipo de preparo pela descricao - " + ex.getMessage());
		}
	}

	public TipoPreparoDTO pesquisaCodigo(int codigo) throws NegocioException {
		try {
			return this.toDTO(tipoPreparoDAO.buscarPorCodigo(codigo));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar tipo preparo pelo codigo - " + ex.getMessage());
		}
	}

	public List<TipoPreparoDTO> toDTOAll(List<TipoPreparo> listaTipoPreparo) {
		List<TipoPreparoDTO> listDTO = new ArrayList<TipoPreparoDTO>();

		for (TipoPreparo tipoPreparo : listaTipoPreparo) {
			listDTO.add(this.toDTO(tipoPreparo));
		}
		return listDTO;
	}

	public TipoPreparoDTO toDTO(TipoPreparo tipoPreparo) {
		return this.modelMapper.map(tipoPreparo, TipoPreparoDTO.class);
	}

	public TipoPreparo toEntity(TipoPreparoDTO tipoPreparoDTO) {
		return this.modelMapper.map(tipoPreparoDTO, TipoPreparo.class);
	}

}
