package ru.itis.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.dto.SignUpForm;
import ru.itis.models.FileInfo;
import ru.itis.services.FilesService;
import ru.itis.services.SignUpService;

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
@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;
    private FilesService filesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.signUpService = applicationContext.getBean(SignUpService.class);
        this.filesService = applicationContext.getBean(FilesService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/templates/signUp.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        Integer avatarId;

        if (part.getSize() == 0){
            avatarId = 0;
        } else{
            FileInfo file = filesService.upload(part.getSubmittedFileName(), part.getSize(), part.getContentType(),
                    part.getInputStream());
            avatarId = file.getId();
        }

        try{
            SignUpForm signUpForm = SignUpForm.builder()
                    .firstName(request.getParameter("firstName"))
                    .lastName(request.getParameter("lastName"))
                    .age(Integer.valueOf(request.getParameter("age")))
                    .city(request.getParameter("city"))
                    .email(request.getParameter("email"))
                    .password(request.getParameter("password"))
                    .avatarId(avatarId)
                    .build();

            this.signUpService.signUp(signUpForm);
        } catch(NumberFormatException e){
            response.sendRedirect("/signUp?error");
            return;
        }

        response.sendRedirect("/signIn");
    }
}
