package br.valerio.quarkus.microservices.book;

import java.time.Instant;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import br.valerio.quarkus.microservices.book.model.Book;
import br.valerio.quarkus.microservices.book.proxy.NumberProxy;

@Path("/api/books")
@Tag(name = "Book REST endpoint")
public class BookResource {
	
	@RestClient
	NumberProxy proxy;

	@Inject
    Logger logger;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(summary = "Creates a new book")
    public Response createABook(@FormParam("title") String title, @FormParam("author") String author, @FormParam("year") int yearOfPublicationb, @FormParam("genre") String genre) {
    	Book book = new Book();
    	book.isbn13 = proxy.generateIsbnNumbers().isbn13;
    	book.title = title;
    	book.author = author;
    	book.yearOfPublication = yearOfPublicationb;
    	book.genre = genre;
    	book.creationDate = Instant.now();
    	logger.info("Book created: "+book);
    	return Response.status(201).entity(book).build();
    }
}