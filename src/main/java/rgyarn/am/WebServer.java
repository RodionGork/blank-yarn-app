package rgyarn.am;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class WebServer {

    private final Log log = LogFactory.getLog(getClass());

    private Server server;
    
    private static final HashMap<String, String> TYPES = new HashMap() {
        {
            put("html", "text/html");
            put("js", "application/javascript");
            put("png", "image/png");
        }
    };

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
            baseReq.setHandled(true);
            String path = req.getPathInfo().replaceFirst("^\\/", "");
            if (path.equals("")) {
                serveStaticFile(resp, "index.html");
            } else if (path.matches(".*\\.(?:js|png|jpg|css)$")) {
                serveStaticFile(resp, path);
            } else if (path.startsWith("api/")) {
                serveApi(resp, path.replace("api/", "").split("\\/"));
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Resource Not Found");
            }
        }

        private void serveApi(HttpServletResponse resp, String[] params) throws IOException {
            log.info("Serving API: " + String.join(", ", params));
            if (params[0].equals("shutdown")) {
                scheduleShutdown();
            }
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("<h1>Good Bye!</h1>");
        }
        
        private void scheduleShutdown() {
            Timer timer = new Timer();
            TimerTask shutdownTask = new TimerTask() {
                public void run() {
                    //System.exit(0);
                    try {
                        server.stop();
                        timer.cancel();
                        timer.purge();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
               }
            };
            timer.schedule(shutdownTask, 1000L);
        }
        
        private void serveStaticFile(HttpServletResponse resp, String name) throws IOException {
            log.debug("Serving static: " + name);
            String type = TYPES.get(name.replaceFirst(".*\\.", ""));
            InputStream input = getClass().getClassLoader().getResourceAsStream("static/" + name);
            if (input == null || type == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("File Not Found");
                return;
            }
            input = new BufferedInputStream(input);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while (true) {
                int b = input.read();
                if (b < 0) {
                    break;
                }
                bos.write(b);
            }
            input.close();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType(type);
            if (bos.size() > 0) {
                resp.setContentLength(bos.size());
                bos.writeTo(resp.getOutputStream());
            }
        }
    }

}
