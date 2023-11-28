package ifmt.cba.servico;

import java.util.List;

import ifmt.cba.dto.GrupoAlimentarDTO;
import ifmt.cba.negocio.GrupoAlimentarNegocio;
import ifmt.cba.persistencia.GrupoAlimentarDAO;
import ifmt.cba.persistencia.ProdutoDAO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

@Path("/grupoAlimentar")
public class GrupoAlimentarServico {
  private static GrupoAlimentarNegocio grupoAlimentarNegocio;
  private static GrupoAlimentarDAO grupoAlimentarDAO;
  private static ProdutoDAO produtoDAO;

  static {
    try {
      grupoAlimentarNegocio = new GrupoAlimentarNegocio(grupoAlimentarDAO, produtoDAO);
    } catch (Exception e) {
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response inserir(GrupoAlimentarDTO grupoAlimentarDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      grupoAlimentarNegocio.inserir(grupoAlimentarDTO);
      retorno = "Grupo alimentar adicionado com sucesso!";
      resposta = Response.ok();
    } catch (Exception ex) {
      retorno = ex.getMessage();
      resposta = Response.serverError();
    }

    resposta.entity(retorno);
    return resposta.build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response alterar(GrupoAlimentarDTO grupoAlimentarDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      grupoAlimentarNegocio.alterar(grupoAlimentarDTO);
      retorno = "Grupo alimentar alterado com sucesso!";
      resposta = Response.ok();
    } catch (Exception ex) {
      retorno = ex.getMessage();
      resposta = Response.serverError();
    }

    resposta.entity(retorno);
    return resposta.build();
  }

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response remover(GrupoAlimentarDTO grupoAlimentarDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      grupoAlimentarNegocio.excluir(grupoAlimentarDTO);
      retorno = "Grupo alimentar removido com sucesso!";
      resposta = Response.ok();
    } catch (Exception ex) {
      retorno = ex.getMessage();
      resposta = Response.serverError();
    }

    resposta.entity(retorno);
    return resposta.build();
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response pesquisar(String parteNomeString) {
    ResponseBuilder resposta;

    try {
      List retorno = grupoAlimentarNegocio.pesquisaParteNome(parteNomeString);
      resposta = Response.ok(retorno);
    } catch (Exception ex) {
      String retornoErro = ex.getMessage();
      resposta = Response.serverError();
      resposta.entity(retornoErro);
    }

    return resposta.build();
  }

}
