package br.valerio.quarkus.microservices.number.model;

import java.time.Instant;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Several ISBN formats")
public class IsbnNumber {

	@JsonbProperty("isbn_10")
	@Schema(required = true)
	public String isbn10;
	
	@JsonbProperty("isbn_13")
	@Schema(required = true)
	public String isbn13;
	
	@JsonbTransient
	public Instant generationDate;

	@Override
	public String toString() {
		return "IsbnNumber [isbn10=" + isbn10 + ", isbn13=" + isbn13 + ", generationDate=" + generationDate + "]";
	}

}
