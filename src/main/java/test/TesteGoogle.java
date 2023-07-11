package test;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
//import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {
	
	@Test
	public void teste() {
		//Caso não houvesse criado a variavel de ambiente
		//System.setProperty("webdriver.gecko.driver", "caminho onde esta o arquivo");
		//System.setProperty("webdriver.chrome.driver", "caminho onde esta o arquivo");
		
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		
		//Para setar onde o navegador irá abrir durante a execução do teste
		//driver.manage().window().setPosition(new Point(100, 100));
		
		//Setando o tamanho do navegador
		driver.manage().window().setSize(new Dimension(1366, 720));

		//Iniciar o navegador mazimizado
		//driver.manage().window().maximize();
		
		driver.get("https://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();
	}
}
