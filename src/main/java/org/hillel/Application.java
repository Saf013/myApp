package org.hillel;

import org.hillel.config.*;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class Application implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootConfig = new AnnotationConfigWebApplicationContext();
        rootConfig.register(RootConfig.class, SecurityConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootConfig));

        AnnotationConfigWebApplicationContext jspContext = new AnnotationConfigWebApplicationContext();
        jspContext.register(WebJspConfig.class);

        ServletRegistration.Dynamic jspServlet = servletContext.addServlet("jspServlet",
                new DispatcherServlet(jspContext));
        jspServlet.addMapping("/view");

        AnnotationConfigWebApplicationContext tlContext = new AnnotationConfigWebApplicationContext();
        tlContext.register(WebTLConfig.class);

        ServletRegistration.Dynamic tlServlet = servletContext.addServlet("tlServlet",
                new DispatcherServlet(tlContext));
        tlServlet.addMapping("/tl", "/tl/*");

        AnnotationConfigWebApplicationContext dlContext = new AnnotationConfigWebApplicationContext();
        dlContext.register(DownloadConfig.class);
        ServletRegistration.Dynamic dServlet = servletContext.addServlet("downloadServlet", new DispatcherServlet(dlContext));
        dServlet.addMapping("/download");

        servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, true, "/*");
    }
}
