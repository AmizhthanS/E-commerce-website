package project;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ping")
public class PingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");

        // Set the response headers
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");
        response.setHeader("Access-Control-Allow-Origin", "*");

        // Generate and send continuous responses
        while (true) {
            String message = "Ping";
            response.getWriter().write("data: " + message + "\n\n");
            response.flushBuffer();

            try {
                TimeUnit.SECONDS.sleep(1); // Delay between responses
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
