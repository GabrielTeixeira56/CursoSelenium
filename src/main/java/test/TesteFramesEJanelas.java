package test;
import static core.DriverFactory.getDriver;
import static core.DriverFactory.killDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import core.DSL;


public class TesteFramesEJanelas {
	private DSL dsl;

	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		killDriver();
	}
	
	//TODO: Refatorar este bloco
	@Test
	public void deveInteragirComFrames() {
		//getDriver().switchTo().frame("frame1");
		dsl.entrarFrame("frame1");
		
		dsl.clicarBotao("frameButton");
		Alert alerta = getDriver().switchTo().alert();
		String mensagem = alerta.getText();
		Assert.assertEquals("Frame OK!", mensagem);
		alerta.accept();
		
		dsl.sairFrame();
		
		dsl.escrever("elementosForm:nome", mensagem);
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String mensagem = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", mensagem);
	}
	
	@Test
	public void deveInteragirComJanelas() {
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escreverBy(By.tagName("textarea"), "Deu certo?");
		getDriver().close();
		dsl.trocarJanela("");
		dsl.escreverBy(By.tagName("textarea"), "E agora?");
	}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		dsl.clicarBotao("buttonPopUpHard");
		dsl.trocarJanela((String)getDriver().getWindowHandles().toArray()[1]);
		dsl.escreverBy(By.tagName("textarea"), "Deu certo?");
		dsl.trocarJanela((String)getDriver().getWindowHandles().toArray()[0]);
		dsl.escreverBy(By.tagName("textarea"), "E agora?");
	}

}
