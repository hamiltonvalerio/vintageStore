package br.valerio.quarkus.microservices.book;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.valerio.quarkus.microservices.book.model.IsbnThirteen;
import br.valerio.quarkus.microservices.book.proxy.NumberProxy;
import io.quarkus.test.Mock;

@Mock
@RestClient
public class MockNumberProxy implements NumberProxy{

	@Override
	public IsbnThirteen generateIsbnNumbers() {
		IsbnThirteen isbnThirteen = new IsbnThirteen();
		isbnThirteen.isbn13 = "13-mock";
		return isbnThirteen;
	}

}
