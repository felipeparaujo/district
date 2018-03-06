package felipe.district.techtest.Handlers.Tasks.Methods;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import felipe.district.techtest.Entity.Task;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public abstract class MethodHandler {
    HttpExchange httpExchange;

    MethodHandler(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
    }

    public abstract HashMap<Integer, Task> handle(HashMap<Integer, Task> data) throws IOException;

    void throw404() throws IOException
    {
        String response = "<h1>404 Not Found</h1>No context found for request";
        this.sendResponse(404, response);
    }

    void sendResponse(int code, String response, String mimeType) throws IOException {
        Headers h = this.httpExchange.getResponseHeaders();
        h.set("Content-Type", mimeType);

        this.sendResponse(code, response);
    }

    void sendResponse(int code, String response) throws IOException {
        this.httpExchange.sendResponseHeaders(code, response.length());
        OutputStream os = this.httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }


}
