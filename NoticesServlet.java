package com.learning.hello;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;


import com.learning.hello.contoller.NoticesController;

/**
 * Servlet implementation class NoticesServlet
 */

@WebServlet("/notice")
public class NoticesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private NoticesController nc = new NoticesController();
    private TemplateEngine templateEngine;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = getServletContext();
        templateEngine = createTemplateEngine(servletContext);
    }

    private TemplateEngine createTemplateEngine(ServletContext servletContext) {
        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setServletContext(servletContext);
        return new TemplateEngineBuilder().setTemplateResolver(templateResolver).build();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Notice> notices = nc.getNotices(); // You need to implement this method in NoticesController
        WebContext ctx = new WebContext(request, response, getServletContext(), request.getLocale());
        ctx.setVariable("notices", notices);

        templateEngine.process("notices", ctx, response.getWriter());
    }

	
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
	}

}
