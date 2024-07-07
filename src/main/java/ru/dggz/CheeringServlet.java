package ru.dggz;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CheeringServlet extends HttpServlet {
    private CheeringManager manager;

    public CheeringServlet(CheeringManager passedManager){
        manager = passedManager;
    }

    public CheeringServlet(){}

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.manager = new CheeringManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.getWriter().println(manager.getRandomPhrase());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        String phrase = request.getParameter("phrase");
        response.getWriter().println(manager.addPhrase(phrase));
    }
}
