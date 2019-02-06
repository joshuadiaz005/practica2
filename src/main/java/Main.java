import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import spark.Spark;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.staticFiles;

public class Main {

    public static void main(String[] args) {

        staticFiles.location("/plantillas");

        final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/");

        ArrayList<Estudiante> listaDeEstudiantes = new ArrayList<>();

        Spark.get("/", (request, response) -> {
            Template plantillaPaginaInicio = configuration.getTemplate("plantillas/index.ftl");
            StringWriter writer = new StringWriter();

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("nombre", "Joshua");
            plantillaPaginaInicio.process(attributes, writer);
            return writer;
        });
    }
}
