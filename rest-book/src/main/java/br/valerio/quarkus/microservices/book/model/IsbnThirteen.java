package br.valerio.quarkus.microservices.book.model;

import javax.json.bind.annotation.JsonbProperty;

public class IsbnThirteen {
	
	@JsonbProperty("isbn_13")
	public String isbn13;
}
