import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteSincronismo {
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		dsl.escrever("novoCampo", "Deu certo?");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
	}
	
	@Test
	public void deveIUtilizarEsperaExplicita() throws InterruptedException{
		dsl.clicarBotao("buttonDelay");
		
		//espera Explícita
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Deu certo?"); 	
	}
}
