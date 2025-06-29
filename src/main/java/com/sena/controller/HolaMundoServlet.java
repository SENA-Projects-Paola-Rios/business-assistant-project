package com.sena.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class HolaMundoServlet extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
         LOGGER.info("✅ 🟢 Probando started.");
        resp.setContentType("text/plain");
        resp.getWriter().write("¡Hola Mundo desde javax.servlet en Tomcat siii!");
    }
}
