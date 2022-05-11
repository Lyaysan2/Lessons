package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import ru.itis.dto.BookDto;
import ru.itis.dto.CommentsDto;
import ru.itis.dto.UserDto;
import ru.itis.models.Book;
import ru.itis.services.CommentsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {

    private CommentsService commentsService;

    @Override
    public void init(ServletConfig servletConfig) {
        ServletContext servletContext = servletConfig.getServletContext();
        ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
        this.commentsService = springContext.getBean(CommentsService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String email = (String) request.getSession().getAttribute("email");
        UserDto userDto = commentsService.getUserByEmail(email);

        Book book = (Book) request.getSession().getAttribute("book");

        CommentsDto form = CommentsDto.builder()
                .user(userDto)
                .book(BookDto.from(book))
                .text(request.getParameter("text"))
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .build();

        CommentsDto createdComment = commentsService.addComments(form);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), createdComment);
        response.setContentType("application/json");
    }
}
