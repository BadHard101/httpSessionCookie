package boost.brain.youtube.http.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import java.io.IOException;

@WebServlet("/servlet")
public class HttpSessionServlet extends HttpServlet {

    private static final String NAME = "name";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession httpSession = req.getSession();
        if(httpSession == null){
            resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return;
        }


        String name = req.getParameter(NAME);
        if(!StringUtils.isEmpty(name)){
            httpSession.setAttribute(NAME, name);
            resp.getWriter().println("New name have been received - " + name);
            return;
        }

        Object nameAttribute = httpSession.getAttribute(NAME);
        if(!(nameAttribute instanceof String)){
            resp.getWriter().println("There is no saved name");
            return;
        }

        String currentName = (String) nameAttribute;
        resp.getWriter().println("Current name: " + currentName);
    }
}
