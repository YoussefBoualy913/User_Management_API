package com.usermanagement;

import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args) throws Exception {

        File baseDir = new File("target/tomcat-work").getAbsoluteFile();
        new File(baseDir, "webapps").mkdirs();

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir(baseDir.getAbsolutePath());

        File war = Path.of("target", "User_Management_API.war").toAbsolutePath().toFile();
        if (!war.isFile()) {
            throw new IllegalStateException("WAR introuvable : " + war.getAbsolutePath());
        }

        tomcat.getConnector();
        tomcat.addWebapp("/User_Management_API", war.getAbsolutePath());

        tomcat.start();
        System.out.println("Application : http://localhost:8080/User_Management_API/");
        tomcat.getServer().await();
    }
}