package ifmt.cba.servico;

import java.util.ArrayList;
import java.util.List;

import ifmt.cba.dto.CardapioDTO;
import ifmt.cba.negocio.CardapioNegocio;
import ifmt.cba.persistencia.CardapioDAO;
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

@Path("/cardapio")
public class CardapioServico {
  private static CardapioNegocio cardapioNegocio;
  private static CardapioDAO cardapioDAO;

  static {
    try {
      cardapioNegocio = new CardapioNegocio(cardapioDAO);
    } catch (Exception e) {

    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response inserir(CardapioDTO cardapioDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      cardapioNegocio.inserir(cardapioDTO);
      retorno = "Cardapio adicionado com sucesso!";
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
  public Response alterar(CardapioDTO cardapioDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      cardapioNegocio.alterar(cardapioDTO);
      retorno = "Cardapio alterado com sucesso!";
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
  public Response remover(CardapioDTO cardapioDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      cardapioNegocio.excluir(cardapioDTO);
      retorno = "Cardapio removido com sucesso!";
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
  public Response pesquisarPorNome(String parteNomeString) {
    ResponseBuilder resposta;

    try {
      CardapioDTO retorno = cardapioNegocio.pesquisaPorNome(parteNomeString);
      resposta = Response.ok(retorno);
    } catch (Exception ex) {
      String retornoErro = ex.getMessage();
      resposta = Response.serverError();
      resposta.entity(retornoErro);
    }

    return resposta.build();
  }

  @GET
  @Path("/pesquisarPorCodigo")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response pesquisarPorCodigo(Integer codigoCardapio) {
    ResponseBuilder resposta;

    try {
      CardapioDTO retorno = cardapioNegocio.pesquisaCodigo(codigoCardapio);
      resposta = Response.ok(retorno);
    } catch (Exception ex) {
      String retorno = ex.getMessage();
      resposta = Response.serverError();
      resposta.entity(retorno);
    }

    return resposta.build();
  }

}
