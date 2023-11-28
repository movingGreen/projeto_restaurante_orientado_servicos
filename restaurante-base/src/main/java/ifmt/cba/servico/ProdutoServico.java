package ifmt.cba.servico;

import java.util.List;

import ifmt.cba.dto.ProdutoDTO;
import ifmt.cba.negocio.ProdutoNegocio;
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

@Path("/produto")
public class ProdutoServico {
  private static ProdutoNegocio produtoNegocio;
  private static ProdutoDAO produtoDAO;

  static {
    try {
      produtoNegocio = new ProdutoNegocio(produtoDAO);
    } catch (Exception e) {
    }
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response inserir(ProdutoDTO produtoDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      produtoNegocio.inserir(produtoDTO);
      retorno = "Produto adicionado com sucesso!";
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
  public Response alterar(ProdutoDTO produtoDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      produtoNegocio.alterar(produtoDTO);
      retorno = "Produto alterado com sucesso!";
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
  public Response remover(ProdutoDTO produtoDTO) {
    String retorno;
    ResponseBuilder resposta;

    try {
      produtoNegocio.excluir(produtoDTO);
      retorno = "Produto removido com sucesso!";
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

      List retorno = produtoNegocio.pesquisaParteNome(parteNomeString);
      resposta = Response.ok(retorno);
    } catch (Exception ex) {
      String retornoErro = ex.getMessage();
      resposta = Response.serverError();
      resposta.entity(retornoErro);
    }

    return resposta.build();
  }

}
