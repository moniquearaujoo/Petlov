package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class CadastroSelenium {

	///transforma em propriedade para a variável ser acessada mesmo fora da classe instaciada
	WebDriver driver;

	@BeforeEach
	void start() {
		///driver= variavel padrão do selenium / Instancia um novo driver para o navegador Chrome
		driver = new ChromeDriver();
		// Define um tempo máximo de 2 segundos para o Selenium encontrar elementos na página
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@AfterEach
	void finish() {
		driver.close();
	}

	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void createPoint() {
		
		// Acessa a página-alvo do teste
		driver.get("https://petlov.vercel.app/signup");

		// Localiza o elemento <h1> que contém o slogan e armazena na variável title
		WebElement title = driver.findElement(By.cssSelector("h1"));
		// Cria um objeto de espera explícita com timeout de 2 segundos
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// Aguarda até que o elemento title esteja visível na página
		wait.until(d -> title.isDisplayed());

		// Verifica se o texto do elemento <h1> corresponde ao esperado
		assertEquals("Cadastro de ponto de doação", title.getText(), "Verificando o título");
		
		WebElement name = driver.findElement(By.cssSelector("input[placeholder='Nome do ponto de doação']"));
		name.sendKeys("Monique Point");
		
		WebElement email = driver.findElement(By.cssSelector("input[placeholder='E-mail']"));
		email.sendKeys("moniquearjm@gmail.com");
		
		WebElement cep = driver.findElement(By.cssSelector("input[placeholder='CEP']"));
		cep.sendKeys("58030280");

		WebElement cepButton = driver.findElement(By.cssSelector("input[value='Buscar CEP']"));
		cepButton.click();
		
		WebElement number = driver.findElement(By.cssSelector("input[placeholder='Número']"));
		number.sendKeys("55");

		WebElement complement = driver.findElement(By.cssSelector("input[placeholder='Complemento']"));
		complement.sendKeys("Casa");
		
		driver.findElement(By.xpath("//span[text()=\"Cachorros\"]/..")).click();
		
		WebElement signupButton = driver.findElement(By.cssSelector("button[class='button-register']"));
		signupButton.click();
		
		WebElement description = driver.findElement(By.cssSelector("#success-page p"));

		Wait<WebDriver> waitResults = new WebDriverWait(driver, Duration.ofSeconds(2));

		waitResults.until(d -> description.isDisplayed());

		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		assertEquals(target, description.getText(), "Verificando a descrição da página de sucesso");
		
	}
}
