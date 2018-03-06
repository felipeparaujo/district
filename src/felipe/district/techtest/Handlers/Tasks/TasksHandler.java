package felipe.district.techtest.Handlers.Tasks;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import felipe.district.techtest.Entity.Task;
import felipe.district.techtest.Handlers.Tasks.Methods.DeleteHandler;
import felipe.district.techtest.Handlers.Tasks.Methods.GetHandler;
import felipe.district.techtest.Handlers.Tasks.Methods.MethodHandler;
import felipe.district.techtest.Handlers.Tasks.Methods.PostHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class TasksHandler implements HttpHandler {
    HashMap<Integer, Task> data;

    public TasksHandler(HashMap<Integer, Task> data) {
        this.data = data;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String method = httpExchange.getRequestMethod();

        MethodHandler handler;
        if ("get".equalsIgnoreCase(method)) {
            handler = new GetHandler(httpExchange);
        } else if ("post".equalsIgnoreCase(method)) {
            handler = new PostHandler(httpExchange);
        } else if ("delete".equalsIgnoreCase(method)) {
            handler = new DeleteHandler(httpExchange);
        } else {
            // Invalid method, respond with 405
            String response = "Method not allowed";

            httpExchange.sendResponseHeaders(405, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();

            return;
        }

        this.data = handler.handle(this.data);
    }
}
