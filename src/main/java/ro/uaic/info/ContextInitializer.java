package ro.uaic.info;

import org.eclipse.jetty.plus.webapp.EnvConfiguration;
import org.eclipse.jetty.plus.webapp.PlusConfiguration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.servlet.ServletContainer;


public class ContextInitializer
{
    public static void main( String[] args ) throws Exception {

        Server server = new Server(8080);
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");

        EnvConfiguration jettyEnv = new EnvConfiguration();
        jettyEnv.setJettyEnvXml(ContextInitializer.class.getResource("/webapp/WEB-INF/jetty-env.xml").toURI().toURL());
        webAppContext.setConfigurations(new Configuration[]{jettyEnv, new PlusConfiguration()});

        String resourceLocation = ContextInitializer.class.getResource("/webapp").toString();
        webAppContext.setResourceBase(resourceLocation);

        ServletHolder servletHolder = webAppContext.addServlet(ServletContainer.class, "/api/*");
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "ro.uaic.info.service");

        server.setHandler(webAppContext);

        server.start();
        server.join();
    }
}
