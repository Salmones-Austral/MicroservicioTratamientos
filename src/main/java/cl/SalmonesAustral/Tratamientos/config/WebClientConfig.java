package cl.SalmonesAustral.Tratamientos.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

 
 
@Configuration

public class WebClientConfig {

    //ms de jaula
    @Value("${jaulas.service.url}")
    private String jaulasUrl;

    @Bean("jaulasWebClient")
    public WebClient jaulasWebClient(WebClient.Builder webClientBuilder) {
        return WebClient.builder()
            .baseUrl(jaulasUrl)
            .build();
    }

    //ms de cosecha
    @Value("${cosecha.service.url}")
    private String cosechaUrl;

    @Bean("cosechaWebClient")
    public WebClient cosechaWebClient(WebClient.Builder webClientBuilder) {
        return WebClient.builder()
            .baseUrl(cosechaUrl)
            .build();
    }
}