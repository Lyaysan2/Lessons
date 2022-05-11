package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.models.FileInfo;
import ru.itis.models.Teacher;
import ru.itis.repositories.TeachersRepository;
import ru.itis.services.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/sign-up")
public class SignUpServlet extends HttpServlet {

    private FilesService filesService;
    private TeachersRepository teachersRepository;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.teachersRepository = springContext.getBean(TeachersRepository.class);
        this.filesService = springContext.getBean(FilesService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String password = request.getParameter("password");

        Integer avatar_id = 0;

        Part part = request.getPart("file");
        if (! (part.getSize() == 0)){
            FileInfo fileInfo = filesService.saveFileToStorage(part.getInputStream(),
                    part.getSubmittedFileName(),
                    part.getContentType(),
                    part.getSize());
            avatar_id = fileInfo.getId();
        }
//        response.sendRedirect("/files/" + fileInfo.getId());

        teachersRepository.save(new Teacher(null, firstName, lastName, email, subject, password, avatar_id));

        response.sendRedirect("/sign-in");
    }
}
