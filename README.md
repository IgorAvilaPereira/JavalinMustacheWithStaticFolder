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
