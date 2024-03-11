package com.example.openapi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@SecurityScheme(
		name = "MB auth",
		type = SecuritySchemeType.OAUTH2,
		flows = @OAuthFlows(
				clientCredentials = @OAuthFlow(
						tokenUrl = "${app.openapi.tokenUrl}",
						scopes = {
								@OAuthScope(name = "openid", description = "(default scope)"),
								@OAuthScope(name = "email"),
								@OAuthScope(name = "profile"),
								@OAuthScope(name = "address"),
								@OAuthScope(name = "phone"),
						}
				)
		)
)
@Configuration
public class OpenAPIConfig {

	@Value("${app.openapi.dev-url}")
	private String devUrl;

	@Value("${app.openapi.int-url}")
	private String intUrl;

	@Value("${app.openapi.prod-url}")
	private String prodUrl;

	@Bean
	public OpenAPI myOpenAPI() {
		return new OpenAPI()
				.specVersion(SpecVersion.V31)
				.info(apiEndpointsInfo())
				.servers(apiServerList());
	}

	private Info apiEndpointsInfo() {
		return new Info()
				.contact(new Contact().name("WiPPSnext").email("wippsnext@nttdata.com").url("https://www.nttdata.com"))
				.description("Demo API exposing endpoints to manage dummy data.")
				.license(new License().name("MIT License").url("https://choosealicense.com/licenses/mit/"))
				.termsOfService("https://www.nttdata.com/global/en/info/term-of-use")
				.title("Demo API")
				.version("2.3.1");
	}

	private List<Server> apiServerList() {
		return List.of(
				new Server().url("http://localhost:8080").description("Server URL in Local environment"),
				new Server().url(devUrl).description("Server URL in Development environment"),
				new Server().url(intUrl).description("Server URL in Integration environment"),
				new Server().url(prodUrl).description("Server URL in Production environment")
		);
	}

}