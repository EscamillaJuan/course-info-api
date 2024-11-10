package com.escamilla.courseinfo.server;

import com.escamilla.courseinfo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;

public class CourseServer {

    static {
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();
    }

    private static final Logger LOG = LoggerFactory.getLogger(CourseServer.class);
    private static final String BASE_URI = loadProperties().getProperty("course-info.uri");

    public static void main(String... args) {
        String databaseFile = loadProperties().getProperty("course-info.database");;
        LOG.info("Starting HTTP server {} with database {}", BASE_URI, databaseFile);
        CourseRepository courseRepository = CourseRepository.openCourseRepository(databaseFile);
        ResourceConfig config = new ResourceConfig().register(new CourseResource(courseRepository));
        GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    private static Properties loadProperties() {
        try (InputStream propertiesStream =
                CourseServer.class.getResourceAsStream("/server.properties")) {
            Properties properties = new Properties();
            properties.load(propertiesStream);
            return properties;
        } catch (IOException e) {
            throw new IllegalStateException("Could not load properties");
        }
    }
}
