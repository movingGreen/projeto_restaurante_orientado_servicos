package ifmt.cba;

// import ifmt.cba.dto.GrupoAlimentarDTO;
// import ifmt.cba.dto.ProdutoDTO;
// import ifmt.cba.negocio.GrupoAlimentarNegocio;
// import ifmt.cba.negocio.NegocioException;
// import ifmt.cba.negocio.ProdutoNegocio;
// import ifmt.cba.persistencia.FabricaEntityManager;
// import ifmt.cba.persistencia.GrupoAlimentarDAO;
// import ifmt.cba.persistencia.PersistenciaException;
// import ifmt.cba.persistencia.ProdutoDAO;

import java.io.IOException;

import org.glassfish.grizzly.http.server.HttpServer;

import ifmt.cba.util.ServidorHTTP;

public class App {
    public static void main(String[] args) {

        // try {
        // GrupoAlimentarDAO grupoAlimentarDAO = new GrupoAlimentarDAO(
        // FabricaEntityManager.getEntityManagerProducao());
        // ProdutoDAO produtoDAO = new
        // ProdutoDAO(FabricaEntityManager.getEntityManagerProducao());
        // GrupoAlimentarNegocio grupoAlimentarNegocio = new
        // GrupoAlimentarNegocio(grupoAlimentarDAO, produtoDAO);
        // ProdutoNegocio produtoNegocio = new ProdutoNegocio(produtoDAO);

        // GrupoAlimentarDTO grupoDTO = new GrupoAlimentarDTO();
        // grupoDTO.setNome("Carboidrato");
        // grupoAlimentarNegocio.inserir(grupoDTO);

        // grupoDTO = grupoAlimentarNegocio.pesquisaParteNome("Carboidrato").get(0);

        // ProdutoDTO produtoDTO = new ProdutoDTO();
        // produtoDTO.setNome("Arroz");
        // produtoDTO.setCustoUnidade(2.0f);
        // produtoDTO.setEstoque(100);
        // produtoDTO.setValorEnergetico(20);
        // produtoDTO.setGrupoAlimentar(grupoDTO);
        // produtoNegocio.inserir(produtoDTO);

        // } catch (PersistenciaException | NegocioException e) {
        // e.printStackTrace();
        // }

        try {
            HttpServer servidor = ServidorHTTP.getServerHTTP();
            System.out.println("-----------------------------------------------------------------r");
            System.out.println("Web Services Jersey Publicados - Pressione qualquer tecla para encerrar");

            // espera uma tecla ser pressionada
            System.in.read();
            servidor.shutdownNow();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
