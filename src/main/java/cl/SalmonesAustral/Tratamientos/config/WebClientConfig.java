package cl.SalmonesAustral.Tratamientos.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

 
 
@Configuration

public class WebClientConfig {

    //ms de jaula
   // @Value("${jaulas.service.url}")
    //private String jaulasUrl;

    @Bean("jaulasWebClient")
    public WebClient jaulasWebClient() {
        return WebClient.builder()
            .baseUrl("https://microserviciojaulas.onrender.com/api/v1/jaulas")
            .build();
    }

    //ms de cosecha
    //@Value("${cosecha.service.url}")
    //private String cosechaUrl;

    @Bean("cosechaWebClient")
    public WebClient cosechaWebClient() {
        return WebClient.builder()
            .baseUrl("https://microserviciocosecha.onrender.com/api/v1/cosecha")
            .build();
    }
}