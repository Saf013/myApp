package org.hillel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
public class DownloadController {

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("token");
        if (token == null ) {
            req.getRequestDispatcher("/WEB-INF/view/jsp/view.jsp").forward(req, resp);
        }
        if (token.equals("cat.jpg")) {
            ServletOutputStream outputStream = resp.getOutputStream();
            byte[] array = Files.readAllBytes(Path.of("/home/vitalik/IdeaProjects/myApp/src/main/webapp/WEB-INF/files/" + token));
            resp.setContentType("image/jpeg");
            resp.setHeader("Content-disposition", "attachment; filename=cat.jpg");
            outputStream.write(array);
            outputStream.flush();
            outputStream.close();
        } else {
            throw new IllegalArgumentException("File not found");
        }
    }
}
