package ifmt.cba.servico;

import java.util.ArrayList;
import java.util.List;

import ifmt.cba.dto.TipoPreparoDTO;
import ifmt.cba.negocio.TipoPreparoNegocio;
import ifmt.cba.persistencia.TipoPreparoDAO;
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

@Path("/tipoPreparo")
public class TipoPreparoServico {
  private static TipoPreparoNegocio tipoPreparoNegocio;
  private static TipoPreparoDAO tipoPreparoDAO;

  static {
    try {
      tipoPreparoNegocio = new TipoPreparoNegocio(tipoPreparoDAO);
    } catch (Exception e) {

    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response inserir(TipoPreparoDTO tipoPreparoDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      tipoPreparoNegocio.inserir(tipoPreparoDTO);
      retorno = "Tipo de preparo adicionado com sucesso!";
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
  public Response alterar(TipoPreparoDTO tipoPreparoDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      tipoPreparoNegocio.alterar(tipoPreparoDTO);
      retorno = "Tipo de preparo alterado com sucesso!";
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
  public Response remover(TipoPreparoDTO tipoPreparoDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      tipoPreparoNegocio.excluir(tipoPreparoDTO);
      retorno = "Tipo de preparo removido com sucesso!";
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

      List retorno = tipoPreparoNegocio.pesquisaParteDescricao(parteNomeString);
      resposta = Response.ok(retorno);
    } catch (Exception ex) {
      String retornoErro = ex.getMessage();
      resposta = Response.serverError();
      resposta.entity(retornoErro);
    }

    return resposta.build();
  }

}
