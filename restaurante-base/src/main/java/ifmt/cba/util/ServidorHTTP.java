package ifmt.cba.util;

import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class ServidorHTTP {
  public static HttpServer getServerHTTP() {

    String BASE_URI = "http://localhost:8080/";

    // cria uma configuracao que estabelece o caminho base para os recursos JAX_RS,
    // neste caso, pacote ifmt.cba.servico
    final ResourceConfig rc = new ResourceConfig().packages("ifmt.cba.servico");

    // cria e devolva uma instancia do servidor http grizzly
    // expoe os recursos/webservice Jersey a partir de BASE_URI
    return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
  }
}
