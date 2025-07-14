package br.com.rocketskills.petlov;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;

//classe que irá definir a modelagem de teste
class PontoDoacao {

	String nome;
	String email;
	String cep;
	String numero;
	String complemento;
	String pets;

	/// um método com o mesmo nome da classe para se torar um construtor ou seja, ao
	/// ativa a classe, o metodo é executado automaticamente.
	public PontoDoacao(String nome, String email, String cep, String numero, String complemento, String pets) {

		this.nome = nome;
		this.email = email;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.pets = pets;
	}
}

// classe para teste
class Cadastro {

	private void submeteFormulario(PontoDoacao ponto) {
		$("input[placeholder='Nome do ponto de doação']").setValue(ponto.nome);
		$("input[placeholder='E-mail']").setValue(ponto.email);
		$("input[placeholder='CEP']").setValue(ponto.cep);
		$("input[value='Buscar CEP']").click();
		$("input[placeholder='Número']").setValue(ponto.numero);
		$("input[placeholder='Complemento']").setValue(ponto.complemento);
		$(By.xpath("//span[text()=\"" + ponto.pets + "\"]/..")).click();
		$("button[class='button-register']").click();
	}

	private void acessarFormulario(PontoDoacao ponto) {

		open("https://petlov.vercel.app/signup");
		$("h1").shouldHave(text("Cadastro de ponto de doação"));

	}
	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void dadosCorretos() {

		// criação da variavel ponto do tipo "PontoDoacao" instanciando a classe
		// PontoDoacao e passando seus valores
		PontoDoacao ponto = new PontoDoacao(
				"Estação Pet",
				"moniquearjm@gmail.com",
				"58030280",
				"55",
				"Casa",
				"Cachorros");

		
		acessarFormulario(ponto);

		submeteFormulario(ponto);

		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		$("#success-page p").shouldHave(text(target));

	}

	@Test
	@DisplayName("Não deve cadastrar com email inválido")
	void emailInvalid() {
		
		//criação da variavel ponto do tipo "PontoDoacao" instanciando a classe PontoDoacao e passando seus valores
		PontoDoacao ponto = new PontoDoacao(
			"Estação Pet2",
			"moniquearjm$gmail.com",
			"58030280",
			"55",
			"Casa",
			"Gatos"
		);

		acessarFormulario(ponto);

		open("https://petlov.vercel.app/signup");
		$("h1").shouldHave(text("Cadastro de ponto de doação"));

		submeteFormulario(ponto);
		
		String target = "Informe um email válido";
		$("span[class='alert-error']").shouldHave(text(target));
		
	}
}
