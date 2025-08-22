package apresentacao;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinMustache;
import static io.javalin.rendering.template.TemplateUtil.model;

/**
 *
 * @author iapereira
 */
public class Main {

    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.fileRenderer(new JavalinMustache());       
            config.staticFiles.add("/static", Location.CLASSPATH); // Serves files from src/main/resources/static            
//            config.staticFiles.add(staticFiles -> {
//                staticFiles.hostedPath = "/";                   // change to host files on a subpath, like '/assets'
//                staticFiles.directory = "/static_files";              // the directory where your files are located;
//                staticFiles.location = Location.CLASSPATH;      // Location.CLASSPATH (jar) or Location.EXTERNAL (file system)
//                staticFiles.precompress = false;                // if the files should be pre-compressed and cached in memory (optimization)
//                staticFiles.aliasCheck = null;                  // you can configure this to enable symlinks (= ContextHandler.ApproveAliases())
//                staticFiles.headers = Map.of(...);              // headers that will be set for the files
//                staticFiles.skipFileFunction = req -> false;    // you can use this to skip certain files in the dir, based on the HttpServletRequest
//                staticFiles.mimeTypes.add(mimeType, ext);       // you can add custom mimetypes for extensions
//              });            
        }).start(7070);
        
        app.get("/", ctx -> {
            ctx.render("/templates/index.html", model("action", "John"));
        });

        /*
        app.get("/output", ctx -> {
            ctx.json("oi");
        });
         */
       
    }

}
