package ifmt.cba.servico;

import java.util.ArrayList;
import java.util.List;

import ifmt.cba.dto.EntregadorDTO;
import ifmt.cba.negocio.EntregadorNegocio;
import ifmt.cba.persistencia.EntregadorDAO;
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

@Path("/entregadorServico")
public class EntregadorServico {
  private static EntregadorNegocio entregadorNegocio;
  private static EntregadorDAO entregadorDAO;

  static {
    try {
      entregadorNegocio = new EntregadorNegocio(entregadorDAO);
    } catch (Exception e) {

    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response inserir(EntregadorDTO entregadorDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      entregadorNegocio.inserir(entregadorDTO);
      retorno = "Entregador adicionado com sucesso!";
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
  public Response alterar(EntregadorDTO entregadorDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      entregadorNegocio.alterar(entregadorDTO);
      retorno = "Entregador alterado com sucesso!";
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
  public Response remover(EntregadorDTO entregadorDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      entregadorNegocio.excluir(entregadorDTO);
      retorno = "Entregador removido com sucesso!";
      resposta = Response.ok();
    } catch (Exception ex) {
      retorno = ex.getMessage();
      resposta = Response.serverError();
    }

    resposta.entity(retorno);
    return resposta.build();
  }

  @GET
  @Path("/pesquisarPorNome")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response pesquisarPorEstado(String nomeEntregador) {
    ResponseBuilder resposta;

    try {
      List retorno = entregadorNegocio.pesquisaParteNome(nomeEntregador);
      resposta = Response.ok(retorno);
    } catch (Exception ex) {
      String retorno = ex.getMessage();
      resposta = Response.serverError();
      resposta.entity(retorno);
    }

    return resposta.build();
  }

  @GET
  @Path("/pesquisarPorCodigo")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response pesquisarPorCodigo(Integer codigoEntregador) {
    ResponseBuilder resposta;

    try {
      EntregadorDTO retorno = entregadorNegocio.pesquisaCodigo(codigoEntregador);
      resposta = Response.ok(retorno);
    } catch (Exception ex) {
      String retorno = ex.getMessage();
      resposta = Response.serverError();
      resposta.entity(retorno);
    }

    return resposta.build();
  }

}
