package felipe.district.techtest.Handlers.Tasks.Methods;

import com.sun.net.httpserver.HttpExchange;
import felipe.district.techtest.Entity.Task;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;

public class GetHandler extends MethodHandler{
    public GetHandler(HttpExchange httpExchange) {
        super(httpExchange);
    }

    public HashMap<Integer, Task> handle(HashMap<Integer, Task> data) throws IOException {
        URI uri = this.httpExchange.getRequestURI();
        String[] route = uri.toString().split("/");
        route = Arrays.stream(route)
                .filter(s -> (s != null && s.length() > 0))
                .toArray(String[]::new);

        System.out.println(Arrays.toString(route));
        if (route.length == 1) {
            this.handleGetAllTasks(data);
        } else if (route.length == 2) {
            try {
                int id = Integer.parseInt(route[1]);
                this.handleGetTask(id, data);
            } catch (NumberFormatException exception) {
                this.sendResponse(400, "Invalid ID");
            }
        } else {
            this.throw404();
        }

        return data;
    }

    private void handleGetAllTasks(HashMap<Integer, Task> data) throws IOException {
        StringBuilder response = new StringBuilder();
        response.append("[");

        for (Task task : data.values()) {
            response.append(task.toJson());
            response.append(", ");
        }

        response.append("]");

        this.sendResponse(200, response.toString(), "application/json");
    }

    private void handleGetTask(int id, HashMap<Integer, Task> data) throws IOException {
        Task task = data.get(id);

        if (task == null) {
            this.sendResponse(404, "Task not found");
        } else {
            this.sendResponse(200, task.toJson(), "application/json");
        }
    }
}
