package ifmt.cba.servico;

import java.util.ArrayList;
import java.util.List;

import ifmt.cba.dto.ClienteDTO;
import ifmt.cba.negocio.ClienteNegocio;
import ifmt.cba.persistencia.ClienteDAO;
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

@Path("/cliente")
public class ClienteServico {
  private static ClienteNegocio clienteNegocio;
  private static ClienteDAO clienteDAO;

  static {
    try {
      clienteNegocio = new ClienteNegocio(clienteDAO);
    } catch (Exception e) {

    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response inserir(ClienteDTO clienteDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      clienteNegocio.inserir(clienteDTO);
      retorno = "Cliente adicionado com sucesso!";
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
  public Response alterar(ClienteDTO clienteDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      clienteNegocio.alterar(clienteDTO);
      retorno = "Cliente alterado com sucesso!";
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
  public Response removerCliente(ClienteDTO clienteDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      clienteNegocio.excluir(clienteDTO);
      retorno = "Cliente removido com sucesso!";
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
      List retorno = clienteNegocio.pesquisaParteNome(parteNomeString);
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
  public Response pesquisarPorCodigo(Integer codigoCliente) {

    ResponseBuilder resposta;

    try {
      ClienteDTO retorno = clienteNegocio.pesquisaCodigo(codigoCliente);
      resposta = Response.ok(retorno);
    } catch (Exception ex) {
      String retorno = ex.getMessage();
      resposta = Response.serverError();
      resposta.entity(retorno);
    }

    return resposta.build();
  }

}