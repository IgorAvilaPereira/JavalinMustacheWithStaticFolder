# JavalinMustacheWithStaticFolder


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
