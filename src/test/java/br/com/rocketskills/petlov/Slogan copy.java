package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

class Slogan {

	@Test
	@DisplayName("Deve exibir o slogan do site")
	void sloganTest() {
		///driver= variavel padrão do selenium / Instancia um novo driver para o navegador Chrome
		WebDriver driver = new ChromeDriver();
		// Define um tempo máximo de 2 segundos para o Selenium encontrar elementos na página
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		// Acessa a página-alvo do teste
		driver.get("https://petlov.vercel.app");
		// Localiza o elemento <h1> que contém o slogan e armazena na variável title
		WebElement title = driver.findElement(By.cssSelector("h1"));
		// Cria um objeto de espera explícita com timeout de 2 segundos
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// Aguarda até que o elemento title esteja visível na página
		wait.until(d -> title.isDisplayed());

		// Verifica se o texto do elemento <h1> corresponde ao esperado
		assertEquals("Conectando corações, mudando vidas!", title.getText(), "Verificando o Slogan");
		// Fecha o navegador após a execução do teste
		driver.close();
	}
}
