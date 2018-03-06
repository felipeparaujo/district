package felipe.district.techtest.Handlers.Tasks.Methods;

import com.sun.net.httpserver.HttpExchange;
import felipe.district.techtest.Entity.Task;

import java.io.IOException;
import java.util.HashMap;

public class GetHandler extends MethodHandler{
    public GetHandler(HttpExchange httpExchange) {
        super(httpExchange);
    }

    public HashMap<Integer, Task> handle(HashMap<Integer, Task> data) throws IOException {
        String[] route = this.getRoute();

        if (route.length == 1) {
            this.handleGetAllTasks(data);
        } else if (route.length == 2) {
            try {
                int id = Integer.parseInt(route[1]);
                this.handleGetTask(id, data);
            } catch (NumberFormatException exception) {
                this.throw400();
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

        // delete comma and space
        response = response.deleteCharAt(response.length() - 1);
        response = response.deleteCharAt(response.length() - 1);

        response.append("]");

        System.out.println(response.toString());

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
