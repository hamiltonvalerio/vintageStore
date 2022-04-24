package br.valerio.quarkus.microservices.book.documents;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.ExternalDocumentation;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@ApplicationPath("/")
@OpenAPIDefinition(
		info = @Info(
				title = "Book Microservices", 
				description = "This microservice generates books", 
				version = "1.0", 
				contact = @Contact(
						name = "hamiltonvalerio", 
						url = "https://github.com/hamiltonvalerio")),
		externalDocs = @ExternalDocumentation(url = "https://github.com/hamiltonvalerio/vintageStore", description = "Microservice tests"),
		tags = {
				@Tag(name = "api", description = "Public API"),
				@Tag(name = "books", description = "Books")
		}
		
)
public class BookMicroservices extends Application {

}
