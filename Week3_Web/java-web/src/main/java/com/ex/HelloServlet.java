package com.ex;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    private NameService nameService;
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("This will be called for every single request.");
//        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.setContentType("text/plain");
//        resp.getWriter().write("Hello, Java Servlets");
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("This will be called eventually, when the servlet is destroyed.");
//    }
//
    @Override
    public void init(ServletConfig configuration) throws ServletException {
        System.out.println(configuration.getInitParameter("Favorite_Show"));
        System.out.println(configuration.getServletContext().getInitParameter("Favorite_Color"));
        getServletContext().setAttribute("Favorite_Food", "food");
        nameService = new NameService(); // this is not testable
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameId = req.getParameter("id");
        System.out.println(nameId);
        Greeting greeting = new Greeting();
        greeting.setMessage("Hello");
        if(nameId == null) {
            greeting.setReciever("Java Servlets");
        } else {
            int id = Integer.parseInt(nameId);
            String name = nameService.getName(id);
            greeting.setReciever(name);
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("application/json");
        resp.getWriter().write(new ObjectMapper().writeValueAsString(greeting));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
