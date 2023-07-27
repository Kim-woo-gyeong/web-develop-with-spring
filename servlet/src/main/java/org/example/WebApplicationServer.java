package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServer {
    private static Logger logger = LoggerFactory.getLogger(WebApplicationServer.class);
    public static void main(String[] args) throws Exception {
        // 내장 톰캣
        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());

        // CalculateServlet 등록
        Context ctx = tomcat.addContext("/", new File(webappDirLocation).getAbsolutePath());
        Wrapper servletWrapper = Tomcat.addServlet(ctx, "CalculateServlet", new CalculateServlet());
        servletWrapper.addMapping("/calculate");

        logger.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }
}
