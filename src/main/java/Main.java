import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;
import spark.Spark;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.staticFiles;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args) {

        staticFiles.location("/plantillas");
        enableDebugScreen();

        final Configuration configuration = new Configuration(new Version(2, 3, 0));
        configuration.setClassForTemplateLoading(Main.class, "/");

        ArrayList<Estudiante> lista = new ArrayList<>();

        Spark.get("/", (request, response) -> {
            Template plantillaPaginaInicio = configuration.getTemplate("plantillas/index.ftl");
            StringWriter writer = new StringWriter();

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("tamano", lista.size() > 0);
            attributes.put("lista", lista);
            plantillaPaginaInicio.process(attributes, writer);
            return writer;
        });

        Spark.get("/agregar", (request, response) -> {
            Template plantillaPaginaInicio = configuration.getTemplate("plantillas/agregar.ftl");
            StringWriter writer = new StringWriter();

            Map<String, Object> attributes = new HashMap<>();
            plantillaPaginaInicio.process(attributes, writer);
            return writer;
        });

        Spark.get("/visualizar/:orden", (request, response) -> {
            Template plantillaPaginaInicio = configuration.getTemplate("plantillas/visualizar.ftl");
            StringWriter writer = new StringWriter();

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("estudiante", lista.get(Integer.parseInt(request.params("orden"))));
            plantillaPaginaInicio.process(attributes, writer);
            return writer;
        });

        Spark.post("/agregarpost", (request, response) -> {
            String matricula = request.queryParams("matricula");
            String nombre = request.queryParams("nombre");
            String apellido = request.queryParams("apellido");
            String telefono = request.queryParams("telefono");

            lista.add(new Estudiante(Integer.parseInt(matricula), nombre, apellido, telefono));

            response.redirect("/");
            return "";
        });

        Spark.get("/eliminar/:orden", (request, response) -> {
            StringWriter writer = new StringWriter();
            lista.remove(Integer.parseInt(request.params("orden")));

            response.redirect("/");
            return writer;
        });

        Spark.get("/modificar/:orden", (request, response) -> {
            Template plantillaPaginaInicio = configuration.getTemplate("plantillas/modificar.ftl");
            StringWriter writer = new StringWriter();

            Map<String, Object> attributes = new HashMap<>();
            attributes.put("estudiante", lista.get(Integer.parseInt(request.params("orden"))));
            plantillaPaginaInicio.process(attributes, writer);
            return writer;
        });

        Spark.post("/modificarpost", (request, response) -> {
            StringWriter writer = new StringWriter();
            try {
                String matricula = request.queryParams("matricula");
                String nombre = request.queryParams("nombre");
                String apellido = request.queryParams("apellido");
                String telefono = request.queryParams("telefono");
                Estudiante estudiante = new Estudiante(Integer.parseInt(matricula), nombre, apellido, telefono);
                for (Estudiante estudianteAModificar: lista)
                {
                    if(estudiante.getMatricula() == estudiante.getMatricula())
                    {
                        estudianteAModificar.setNombre(estudiante.getNombre());
                        estudianteAModificar.setApellido(estudiante.getApellido());
                        estudianteAModificar.setMatricula(estudiante.getMatricula());
                        estudianteAModificar.setTelefono(estudiante.getTelefono());
                        break;
                    }
                }
                response.redirect("/");
            }catch (Exception e){
                e.printStackTrace();
                response.redirect("/");
            }
            return writer;
        });


    }
}
