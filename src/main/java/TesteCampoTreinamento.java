import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
//import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	private WebDriver driver = new ChromeDriver();
	private DSL dsl;

	@Before
	public void inicializa() {
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void testeTextField() {
		dsl.escrever("elementosForm:nome", "Gabriel");
		Assert.assertEquals("Gabriel", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() {
		dsl.escrever("elementosForm:sugestoes", "Texto em área");
		Assert.assertEquals("Texto em área", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveInteragirComCheckbox() {
		dsl.clicarRadio("elementosForm:comidaFavorita:1");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:comidaFavorita:1"));
	}
	
	@Test
	public void deveInteragirComCombo() {
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		
		//Verificando o tamanho da lista
		Assert.assertEquals(8, options.size());
		
		//Lógica para validar se um valor esta no combo(lista)
		boolean encontrou = false;
		for(WebElement option: options) {
			if(option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	@Test
	public void deveVerificarValoresCombosMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		//A função Select serve para selecionar um elemento dentro de uma lista de elementos.
		Select combo = new Select(element);

		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());
		
		//Caso deseje desmarcar algo
		//combo.deselectByVisibleText("TEXTO");
	}
	
	@Test
	public void deveIteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");
		WebElement botao = driver.findElement(By.id("buttonSimple"));		
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
	}
	
	@Test
	//@Ignore //Usar esta anotação impedirá que este teste seja executa, bom para evitar falso positivo.
	public void deveInteragirComLinks() {
		dsl.clicarLink("Voltar");
		
		//Falha o teste de propósito, a ideia é não deixar um teste sem assert dar um falso positivo
		//Assert.fail();
		
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextoNaPagina() {		
		//Opção pouco performática
		//Assert.assertTrue(driver.findElement(By.tagName("body"))
		//			.getText().contains("Campo de Treinamento"));
		
	
		Assert.assertEquals("Campo de Treinamento", 
				dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
				dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void escreverEValidarNome() {
		dsl.escrever("elementosForm:nome", "Gabriel");
		Assert.assertEquals("Gabriel", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome", "Teixeira");
		Assert.assertEquals("Teixeira", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	/***** JavaScript *****/
	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('Testando JS via Selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via JS'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	 
	@Test
	public void deveClicarBotaoTabela(){
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
	
}
