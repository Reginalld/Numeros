package br.com.calculos;

import br.com.calculos.service.NumeroService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculosApplicationTests {

	@Test
	public void testeMetodoSomar(){
		NumeroService numero = new NumeroService();
		var soma = numero.calculaMedia();
		Assertions.assertEquals(3, soma);

	}

}
