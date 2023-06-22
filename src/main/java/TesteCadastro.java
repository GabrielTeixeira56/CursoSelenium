import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {
	private WebDriver driver = new ChromeDriver();
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	public void assertacao(String texto) {
		page.cadastrar();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals(texto, alerta.getText());
	}
	 
	@Test
	public void deveRealizarCadastro() {
		page.setNome("Gabriel");
		page.setSobrenome("Teixeira");
		page.setSexoMasculino();
		page.setComidaFavoritaPizza();
		page.setEscolaridade("Superior");
		page.setEsporte("Karate");
		page.cadastrar();
		
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Gabriel", page.obterNomeCadastro());
		Assert.assertEquals("Teixeira", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Pizza", page.obterComidaCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Karate", page.obterEsporteCadastro());
				
		/*
		//Campo nome
		dsl.escrever("elementosForm:nome", "Gabriel");
		Assert.assertEquals("Gabriel", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		
		//Campo sobrenome
		dsl.escrever("elementosForm:sobrenome", "Teixeira");
		Assert.assertEquals("Teixeira", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));
		
		//Selecionar sexo
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		
		//Seleciona comida favorita
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

		//Seleciona escolaridade
		dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
		
		//WebElement elementoEscolaridade = driver.findElement(By.id("elementosForm:escolaridade"));
		//Select comboEscolaridade = new Select(elementoEscolaridade);
		//comboEscolaridade.selectByVisibleText("Superior");
		
		Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));
		
		//Seleciona esportes
		WebElement elementoEsporte = driver.findElement(By.id("elementosForm:esportes"));
		Select comboEsporte = new Select(elementoEsporte);
		comboEsporte.selectByVisibleText("Karate");
		
		List<WebElement> opcoes = comboEsporte.getAllSelectedOptions();
		
		boolean encontrouEsporteNaLista = false;
		for(WebElement opcao: opcoes) {
			if(opcao.getText().equals("Karate")) {
				encontrouEsporteNaLista = true;
				break;
			}
		}
		Assert.assertTrue(encontrouEsporteNaLista);
		
		//Cadastrar os Dados
		dsl.clicarBotao("elementosForm:cadastrar");
		
		
		//Assert do resultado retornado
		//Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.validarResultadoStartsWith("resultado","Cadastrado!"));
		Assert.assertTrue(dsl.validarResultadoEndsWith("descNome","Gabriel"));
		Assert.assertTrue(dsl.validarResultadoEndsWith("descSobrenome", "Teixeira"));
		Assert.assertTrue(dsl.validarResultadoEndsWith("descSexo", "Masculino"));
		Assert.assertTrue(dsl.validarResultadoEndsWith("descComida", "Pizza"));
		Assert.assertTrue(dsl.validarResultadoEndsWith("descEscolaridade", "superior"));
		Assert.assertTrue(dsl.validarResultadoEndsWith("descEsportes", "Karate"));
		*/
	}
	/*
	@Test
	public void deveValidarNomeObrigatorio() {
		assertacao("Nome eh obrigatorio");
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		page.setNome("Nome de teste");
		assertacao("Sobrenome eh obrigatorio");
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		page.setNome( "Nome de teste");
		page.setSobrenome("Sobrenome de teste");
		//dsl.escrever("elementosForm:nome", "Nome de teste");
		//dsl.escrever("elementosForm:sobrenome", "Sobrenome de teste");
		
		assertacao("Sexo eh obrigatorio");
	}
	
	@Test
	public void deveValidarComidaVegetarianos() {
		page.setNome("Nome de teste");
		page.setSobrenome("Sobrenome de teste");
		page.setSexoFeminino();
		page.setComidaFavoritaCarne();
		page.setComidaFavoritaVegetariano();
		
		assertacao("Tem certeza que voce eh vegetariano?");
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		page.setNome("Nome de teste");
		page.setSobrenome("Sobrenome de teste");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		page.setEsporte("Karate", "O que eh esporte?" );

		assertacao("Voce faz esporte ou nao?");
	}*/
}
