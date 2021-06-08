package org.hillel.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class ViewController {

    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public void view(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String file = req.getParameter("fileParam");
        session.setAttribute("token", file);
        resp.sendRedirect("/download");
    }
}
