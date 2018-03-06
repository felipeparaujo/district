package felipe.district.techtest.Handlers.Tasks.Methods;

import com.sun.net.httpserver.HttpExchange;
import felipe.district.techtest.Entity.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class PostHandler extends MethodHandler {
    public PostHandler(HttpExchange httpExchange) {
        super(httpExchange);
    }

    public HashMap<Integer, Task> handle(HashMap<Integer, Task> data) throws IOException {
        String[] route = this.getRoute();

        if (route.length == 1) {
            data = this.handleCreateTask(data);
        } else {
            this.throw404();
        }

        return data;
    }

    private HashMap<Integer, Task> handleCreateTask(HashMap<Integer, Task> data) throws IOException {
        try {
            InputStreamReader isr = new InputStreamReader(this.httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            // Parse json

            this.sendResponse(201, "Created");
        } catch (UnsupportedEncodingException exception) {
            this.throw400();
        }

        return data;
    }
}
