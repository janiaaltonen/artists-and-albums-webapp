package launch;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

/**
 * Launch adapted from Heroku devcenter's example
 * 
 * https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat
 * https://github.com/heroku/devcenter-embedded-tomcat
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // Web-sovelluksen julkisten tiedostojen sijainti:
        String webappDirPath = new File("src/main/webapp/").getAbsolutePath();

        // Tomcat-palvelin, joka huolehtii HTTP-liikenteestä:
        Tomcat tomcat = new Tomcat();

        // Asetetaan Tomcatin HTTP-portti. Jos "PORT" löytyy ympäristömuuttujista,
        // käytetään sitä. Muussa tapauksessa portti 8080:
        String webPort = System.getenv().getOrDefault("PORT", "8080");
        tomcat.setPort(Integer.valueOf(webPort));

        // Luodaan Connector-olio, joka kuuntelee asettamaamme porttia:
        tomcat.getConnector();

        // Tomcatin asetukset:
        StandardContext ctx = (StandardContext) tomcat.addWebapp("/", webappDirPath);

        // Käännettyjen Java-koodien sijainnit "WEB-INF/classes"-hakemiston lisäksi:
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        // Asetetaan UTF-8 -merkistö HTTP-pyyntöihin ja -vastauksiin:
        ctx.setRequestCharacterEncoding("utf-8");
        ctx.setResponseCharacterEncoding("utf-8");

        // Käynnistetään palvelin ja odotetaan yhteyksiä:
        tomcat.start();
        tomcat.getServer().await();
    }
}