package ifmt.cba.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import ifmt.cba.dto.EstadoOrdemProducaoDTO;
import ifmt.cba.dto.OrdemProducaoDTO;
import ifmt.cba.entity.OrdemProducao;
import ifmt.cba.persistencia.OrdemProducaoDAO;
import ifmt.cba.persistencia.PersistenciaException;

public class OrdemProducaoNegocio {
    
    private ModelMapper modelMapper;
	private OrdemProducaoDAO ordemProducaoDAO;

	public OrdemProducaoNegocio(OrdemProducaoDAO ordemProducaoDAO) {
		this.ordemProducaoDAO = ordemProducaoDAO;
		this.modelMapper = new ModelMapper();
	}

	public void inserir(OrdemProducaoDTO ordemProducaoDTO) throws NegocioException {

		OrdemProducao ordemProducao = this.toEntity(ordemProducaoDTO);
		String mensagemErros = ordemProducao.validar();

		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}

		try {
			ordemProducaoDAO.beginTransaction();
			ordemProducaoDAO.incluir(ordemProducao);
			ordemProducaoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			ordemProducaoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao incluir ordem de producao - " + ex.getMessage());
		}
	}

	public void alterar(OrdemProducaoDTO ordemProducaoDTO) throws NegocioException {

		OrdemProducao ordemProducao = this.toEntity(ordemProducaoDTO);
		String mensagemErros = ordemProducao.validar();
		if (!mensagemErros.isEmpty()) {
			throw new NegocioException(mensagemErros);
		}
		try {
			if (ordemProducaoDAO.buscarPorCodigo(ordemProducao.getCodigo()) == null) {
				throw new NegocioException("Nao existe essa ordem de producao");
			}
			ordemProducaoDAO.beginTransaction();
			ordemProducaoDAO.alterar(ordemProducao);
			ordemProducaoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			ordemProducaoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao alterar ordem de producao - " + ex.getMessage());
		}
	}

	public void excluir(OrdemProducaoDTO ordemProducaoDTO) throws NegocioException {

		OrdemProducao ordemProducao = this.toEntity(ordemProducaoDTO);
		try {
			if (ordemProducaoDAO.buscarPorCodigo(ordemProducao.getCodigo()) == null) {
				throw new NegocioException("Nao existe essa ordem de producao");
			}
			ordemProducaoDAO.beginTransaction();
			ordemProducaoDAO.excluir(ordemProducao);
			ordemProducaoDAO.commitTransaction();
		} catch (PersistenciaException ex) {
			ordemProducaoDAO.rollbackTransaction();
			throw new NegocioException("Erro ao excluir ordem de producao - " + ex.getMessage());
		}
	}

    public List<OrdemProducaoDTO> pesquisaPorDataProducao(LocalDate dataInicial, LocalDate dataFinal) throws NegocioException {
		try {
			return this.toDTOAll(ordemProducaoDAO.buscarPorDataProducao(dataInicial, dataFinal));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar ordem de producao por data de producao - " + ex.getMessage());
		}
	}

	public List<OrdemProducaoDTO> pesquisaPorEstado(EstadoOrdemProducaoDTO estado) throws NegocioException {
		try {
			return this.toDTOAll(ordemProducaoDAO.buscarPorEstado(estado));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar ordem de producao pelo estado - " + ex.getMessage());
		}
	}

	public OrdemProducaoDTO pesquisaCodigo(int codigo) throws NegocioException {
		try {
			return this.toDTO(ordemProducaoDAO.buscarPorCodigo(codigo));
		} catch (PersistenciaException ex) {
			throw new NegocioException("Erro ao pesquisar ordem de producao pelo codigo - " + ex.getMessage());
		}
	}

	public List<OrdemProducaoDTO> toDTOAll(List<OrdemProducao> listaOrdemProducao) {
		List<OrdemProducaoDTO> listaDTO = new ArrayList<OrdemProducaoDTO>();

		for (OrdemProducao ordemProducao : listaOrdemProducao) {
			listaDTO.add(this.toDTO(ordemProducao));
		}
		return listaDTO;
	}

	public OrdemProducaoDTO toDTO(OrdemProducao ordemProducao) {
		return this.modelMapper.map(ordemProducao, OrdemProducaoDTO.class);
	}

	public OrdemProducao toEntity(OrdemProducaoDTO ordemProducaoDTO) {
		return this.modelMapper.map(ordemProducaoDTO, OrdemProducao.class);
	}
}
