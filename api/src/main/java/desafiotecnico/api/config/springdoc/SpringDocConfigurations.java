package desafiotecnico.api.config.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Desafio técnico NEKI")
                        .version("1.0.0")
                        .description(
                                "API Rest para o desafio técnico da NEKI, contendo as funcionalidades de registro e login utilizando JWT, CRUD de skills, além de poder associar skills a um determinado usuário, editar o level da skill e deletar a skill do usuário.")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact()
                                .name("Gustavo Mariano")
                                .email("gustavomariano2001@hotmail.com")
                                .url("https://github.com/GustavMariano"))
                );
    }
}
