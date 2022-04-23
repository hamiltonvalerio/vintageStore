package br.valerio.quarkus.microservices.number;

import java.time.Instant;
import java.util.Random;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.logging.Logger;

import br.valerio.quarkus.microservices.number.model.IsbnNumber;

@Path("/api/numbers")
@Tag(name = "Numer REST Endpoint")
public class NumberResource {
	
	@Inject
	Logger logger; 

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Generates book numbers")
    public IsbnNumber generateIsbnNumbers() {
        IsbnNumber isbnNumber = new IsbnNumber();
        isbnNumber.isbn10 = "10-" + new Random().nextInt(100_000);
        isbnNumber.isbn13 = "13-" + new Random().nextInt(100_000_000);
        isbnNumber.generationDate = Instant.now();
        logger.info("Numbers generated "+ isbnNumber);
        return isbnNumber;
    }
}