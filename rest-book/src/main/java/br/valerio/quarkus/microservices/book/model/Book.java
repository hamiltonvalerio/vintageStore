package br.valerio.quarkus.microservices.book.model;

import java.time.Instant;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbProperty;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "A Book")
public class Book {
	
	@JsonbProperty("isbn_13")
	@Schema(required = true)
	public String isbn13;
	
	@Schema(required = true)
	public String title;
	
	@Schema(required = true)
	public String author;
	
	@Schema(required = true)
	@JsonbProperty("year_of_publication")
	public int yearOfPublication;
	
	@Schema(required = true)
	public String genre;
	
	@Schema(implementation = String.class, format = "date")
	@JsonbDateFormat("yyyy/MM/dd")
	@JsonbProperty("creation_date")
	public Instant creationDate;
	
	@Override
	public String toString() {
		return "Book [isbn13=" + isbn13 + ", title=" + title + ", author=" + author + ", yearOfPublication="
				+ yearOfPublication + ", genre=" + genre + ", creationDate=" + creationDate + "]";
	}
	
	
}
