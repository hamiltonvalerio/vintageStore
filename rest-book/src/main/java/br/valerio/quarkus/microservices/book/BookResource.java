package br.valerio.quarkus.microservices.book;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
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
    @Operation(
    		summary = "Creates a new book",
    		description = "Create a book with an ISBN number.")
    @Retry(
    		maxRetries = 3,
    		delay = 3000)
    @Fallback(fallbackMethod = "fallbackOnCreatingABook")
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
    
    public Response fallbackOnCreatingABook(String title, String author, int yearOfPublicationb, String genre) throws FileNotFoundException {
    	Book book = new Book();
    	book.isbn13 = "Will be set later";
    	book.title = title;
    	book.author = author;
    	book.yearOfPublication = yearOfPublicationb;
    	book.genre = genre;
    	book.creationDate = Instant.now();
    	saveBookOnDisk(book);
    	logger.info("Book saved on disk: "+book);
    	return Response.status(206).entity(book).build();
    }

	private void saveBookOnDisk(Book book) throws FileNotFoundException {
		String bookJson = JsonbBuilder.create().toJson(book);
		try(PrintWriter out = new PrintWriter("book-" + Instant.now().toEpochMilli() + ".json")){
			out.println(bookJson);
		}
		
	}
}