package org.generation.WebProject.security;

//The web application is based on Spring MVC (package that we have already installed as the dependencies
//It is a package/module in the Spring framework that deals with the model-view-controller pattern
//Model-View-Controller pattern
//1)Deal with the View component first -> Spring MVC provides the functionality to
//route http request to their respective template (html).

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //because we have implemented WebMvcConfigurer, so we can do our own implementation of the method
    //on the WebMvcConfigurer interface

    @Value("${image.folder}")
    private String imageFolder;   //now imageFolder variable the value = productimages

    public void addViewControllers(ViewControllerRegistry registry) {
        //Map the browser's URL to a specific View (HTML) inside resources/templates directory
        //We can register view that creates a direct mapping between the URL and the view name (.html)
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/aboutus").setViewName("aboutus");
        registry.addViewController("/products").setViewName("products");
        registry.addViewController("/productform").setViewName("productform");

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("index");
    }

    //expose the images, js, css resources to the external client (browser) so that they can access the
    //necessary files to display
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(0);


        //expose the productimages folder to allow external client to access the files from the server
        Path uploadDir = Paths.get(imageFolder);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        registry.addResourceHandler("/" + imageFolder + "/**")
                .addResourceLocations("file:" + uploadPath + "/")
                .setCachePeriod(0);
    }
}
