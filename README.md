# JavalinMustacheWithStaticFolder

## Main - Javalin + Mustache and Static Folder (JS Scripts, CSS, Images...)

```java
package apresentacao;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinMustache;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author iapereira
 */
public class Main {

    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.fileRenderer(new JavalinMustache());
            config.staticFiles.add("/static", Location.CLASSPATH); // Serves files from src/main/resources/static
            // config.staticFiles.add(staticFiles -> {
            // staticFiles.hostedPath = "/"; // change to host files on a subpath, like
            // '/assets'
            // staticFiles.directory = "/static_files"; // the directory where your files
            // are located;
            // staticFiles.location = Location.CLASSPATH; // Location.CLASSPATH (jar) or
            // Location.EXTERNAL (file system)
            // staticFiles.precompress = false; // if the files should be pre-compressed and
            // cached in memory (optimization)
            // staticFiles.aliasCheck = null; // you can configure this to enable symlinks
            // (= ContextHandler.ApproveAliases())
            // staticFiles.headers = Map.of(...); // headers that will be set for the files
            // staticFiles.skipFileFunction = req -> false; // you can use this to skip
            // certain files in the dir, based on the HttpServletRequest
            // staticFiles.mimeTypes.add(mimeType, ext); // you can add custom mimetypes for
            // extensions
            // });
        }).start(7070);

        app.get("/", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("pageTitle", "My Awesome Page");
            model.put("userName", "Alice");
            model.put("items", List.of("Apple", "Banana", "Cherry")); // A list of items
            ctx.render("/templates/index.html", model);
        });

        app.get("/formulario", ctx -> {
            ctx.render("/templates/formulario.html");
        });

        app.post("/formulario", ctx -> {
            ctx.html(ctx.formParam("texto"));
        });

    }

}

```

## Post

```java
Javalin app = Javalin.create().start(7070);
app.post("/submit-form", ctx -> {
    String username = ctx.formParam("username");
    String email = ctx.formParam("email");
    // ... process parameters
    ctx.result("Form data received: " + username + ", " + email);
});
```

## Others Examples

```java
import io.javalin.Javalin;

public class JavalinParamsExample {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7070);

        // Path parameter example
        app.get("/users/{id}", ctx -> {
            long userId = ctx.pathParamAsClass(Long.class, "id").get();
            ctx.result("User ID: " + userId);
        });

        // Query parameter example
        app.get("/search", ctx -> {
            String query = ctx.queryParam("query");
            int limit = ctx.queryParamAsClass(Integer.class, "limit").getOrDefault(10);
            ctx.result("Search query: " + query + ", Limit: " + limit);
        });

        // Form parameter example
        app.post("/submit-form", ctx -> {
            String username = ctx.formParam("username");
            ctx.result("Submitted username: " + username);
        });

        // Body parameter (JSON) example
        app.post("/api/data", ctx -> {
            MyData data = ctx.bodyAsClass(MyData.class);
            ctx.result("Received data: " + data.getValue());
        });
    }

    public static class MyData {
        private String value;

        public String getValue() {
            return value;
        }
    }
}
```

## Mustache Examples

```html
<!DOCTYPE html>
<html>
<head>
    <title>{{pageTitle}}</title>
</head>
<body>
    <h1>Welcome, {{userName}}!</h1>
    <p>Your favorite color is: {{favoriteColor}}</p>
    <ul>
        {{#items}}
        <li>{{.}}</li>
        {{/items}}
    </ul>
</body>
</html>
```

```java
import io.javalin.Javalin;
import io.javalin.rendering.FileRenderer; // Import this
import io.javalin.rendering.JavalinRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            // Enable static file serving
            config.staticFiles.add("/public");
        }).start(7070);

        // Register Mustache with the file extension ".mustache"
        JavalinRenderer.registerMustache(); // Registers .mustache templates

        app.get("/", ctx -> {
            // Create the data model
            Map<String, Object> model = new HashMap<>();
            model.put("pageTitle", "My Awesome Page");
            model.put("userName", "Alice");
            model.put("items", List.of("Apple", "Banana", "Cherry")); // A list of items

            // Render the template, passing the model
            ctx.render("index.mustache", model);
        });
    }
}
```
