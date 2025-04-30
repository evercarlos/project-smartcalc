package com.tec.smartcalc.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API-REST SMART CALC",
                version = "1.0",

                description = "Api Rest para Calculo de Porcentaje",

                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                ),

                contact = @Contact(
                        name = "tec.ocm",
                        url = "tec.com",
                        email = "ecr@gmail.com")
        )
)
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", description = "Autenticación" +
        "tipo Bearer API-TC", scheme = "bearer")
public class OpenApicConfig {


}
