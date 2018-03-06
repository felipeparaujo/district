package felipe.district.techtest;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;

import com.sun.net.httpserver.HttpServer;
import felipe.district.techtest.Entity.Task;
import felipe.district.techtest.Handlers.Tasks.TasksHandler;

public class Main {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Task> data = new HashMap<>();
        data.put(0, new Task(0, "Get Milk", "We ran out of milk!", new Date()));
        data.put(1, new Task(0, "Get Eggs", "Let's bake a cake", new Date()));

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/tasks", new TasksHandler(data));
        server.setExecutor(null);
        server.start();
    }
}
