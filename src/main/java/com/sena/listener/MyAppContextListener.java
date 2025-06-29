package com.sena.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

@WebListener
public class MyAppContextListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            System.out.println("✅ App shutting down. Cleaning MySQL abandoned connections...");
            AbandonedConnectionCleanupThread.checkedShutdown();
        } catch (Exception e) {
            System.err.println("⚠️ Failed to shutdown AbandonedConnectionCleanupThread.");
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("🟢 App started.");
    }
}
