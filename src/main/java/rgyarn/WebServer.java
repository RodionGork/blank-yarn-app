package rgyarn;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class WebServer {

    Server server;

    public void run() throws Exception {
        server = createBasicServer();
        server.setHandler(new CustomHandler());
        server.start();
        server.join();
    }
    
    private Server createBasicServer() {
        return new Server(8080);
    }
    
    public class CustomHandler extends AbstractHandler {

        @Override
        public void handle(String s, Request baseReq, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
            if (req.getMethod().equalsIgnoreCase("POST")) {
                try {
                    System.exit(0);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            baseReq.setHandled(true);
            String path = req.getPathInfo().replaceFirst(".*\\/", "");
            if (path.equals("")) {
                resp.setContentType("text/html");
                serveStaticFile(resp, "index.html");
            } else if (path.endsWith(".js")) {
                resp.setContentType("application/javascript");
                serveStaticFile(resp, path);
            }
        }

        private void serveStaticFile(HttpServletResponse resp, String name) {
            resp.setStatus(HttpServletResponse.SC_OK);
            //resp.getWriter().println(String.format("<h1>%s</h1>", name));
        }
    }

}
