package felipe.district.techtest.Handlers.Tasks.Methods;

import com.sun.net.httpserver.HttpExchange;
import felipe.district.techtest.Entity.Task;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class PostHandler extends MethodHandler {
    public PostHandler(HttpExchange httpExchange) {
        super(httpExchange);
    }

    public HashMap<Integer, Task> handle(HashMap<Integer, Task> data) throws IOException {
        String response = "post";

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();

        return data;
    }
}
