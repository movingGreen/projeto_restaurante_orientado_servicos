package ifmt.cba.servico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ifmt.cba.dto.EstadoPedidoDTO;
import ifmt.cba.dto.PedidoDTO;
import ifmt.cba.negocio.PedidoNegocio;
import ifmt.cba.persistencia.PedidoDAO;
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

@Path("/pedido")
public class PedidoServico {
  private static PedidoNegocio pedidoNegocio;
  private static PedidoDAO pedidoDAO;

  static {
    try {
      pedidoNegocio = new PedidoNegocio(pedidoDAO);
    } catch (Exception e) {

    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response inserir(PedidoDTO pedidoDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      pedidoNegocio.inserir(pedidoDTO);
      retorno = "Pedido adicionado com sucesso!";
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
  public Response alterar(PedidoDTO pedidoDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      pedidoNegocio.alterar(pedidoDTO);
      retorno = "Pedido alterado com sucesso!";
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
  public Response remover(PedidoDTO pedidoDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      pedidoNegocio.excluir(pedidoDTO);
      retorno = "Pedido removido com sucesso!";
      resposta = Response.ok();
    } catch (Exception ex) {
      retorno = ex.getMessage();
      resposta = Response.serverError();
    }

    resposta.entity(retorno);
    return resposta.build();
  }

  @GET
  @Path("/pesquisarPorDataProducao")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response pesquisarPorDataProducao(String dataInicial, String dataFinal) {
    ResponseBuilder resposta;

    try {
      LocalDate localDateInicial = LocalDate.parse(dataInicial);
      LocalDate localDateFinal = LocalDate.parse(dataFinal);

      List retorno = pedidoNegocio.pesquisaPorDataProducao(localDateFinal, localDateInicial);
      resposta = Response.ok(retorno);
    } catch (Exception ex) {
      String retornoErro = ex.getMessage();
      resposta = Response.serverError();
      resposta.entity(retornoErro);
    }

    return resposta.build();
  }

  @GET
  @Path("/pesquisarPorEstado")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response pesquisarPorEstado(EstadoPedidoDTO estadoPedido) {
    ResponseBuilder resposta;

    try {
      List retorno = pedidoNegocio.pesquisaPorEstado(estadoPedido);
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
  public Response pesquisarPorCodigo(Integer codigoPedido) {
    ResponseBuilder resposta;

    try {
      PedidoDTO retorno = pedidoNegocio.pesquisaCodigo(codigoPedido);
      resposta = Response.ok(retorno);
    } catch (Exception ex) {
      String retorno = ex.getMessage();
      resposta = Response.serverError();
      resposta.entity(retorno);
    }

    return resposta.build();
  }

}
