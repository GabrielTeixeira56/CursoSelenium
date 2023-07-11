package test;
import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.DSL;

public class TesteSincronismo {
	private DSL dsl;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	@Test
	public void deveIUtilizarEsperaFixa() throws InterruptedException{
		dsl.clicarBotao("buttonDelay");
		
		//espera fixa
		Thread.sleep(3000);
		dsl.escrever("novoCampo", "Deu certo?"); 	
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException{
		dsl.clicarBotao("buttonDelay");
		
		//Espera Implícita
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		dsl.escrever("novoCampo", "Deu certo?");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
	}
	
	@Test
	public void deveIUtilizarEsperaExplicita() throws InterruptedException{
		dsl.clicarBotao("buttonDelay");
		
		//espera Explícita
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Deu certo?"); 	
	}
}
