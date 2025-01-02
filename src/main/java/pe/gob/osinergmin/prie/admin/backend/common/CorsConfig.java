package pe.gob.osinergmin.prie.admin.backend.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
                // Descomentar y ajustar las siguientes líneas según sea necesario
                // registry.addMapping("/api/**").allowedOrigins("http://localhost:4200/");
                // registry.addMapping("/**").allowedOrigins("https://sisseg.cloudcomputing.com.pe");
            }
        };
    }
}
