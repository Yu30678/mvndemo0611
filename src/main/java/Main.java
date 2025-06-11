

import com.sun.net.httpserver.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.net.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8010"));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/hello", new HelloHandler());
        // 啟動伺服器
        server.setExecutor(null); // 默認執行緒
        server.start();
        System.out.println("✅ Server started on port " + port);
    }

    static class HelloHandler implements HttpHandler {
        @Override /*  */
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello";
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers",
                    "Content-Type, ngrok-skip-browser-warning");
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "POST, GET, OPTIONS");

            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=utf-8");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, bytes.length);
            exchange.getResponseBody().write(bytes);
            exchange.getResponseBody().close();
        }
    }
}