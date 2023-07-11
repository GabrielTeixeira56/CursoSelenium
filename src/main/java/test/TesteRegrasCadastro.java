package test;
import static core.DriverFactory.getDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import core.BaseTest;
import page.CampoTreinamentoPage;


@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest{
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	
	@Parameter(value=1)
	public String sobrenome;
	
	@Parameter(value=2)
	public String sexo;
	
	@Parameter(value=3)
	public List<String> comidas;
	
	@Parameter(value=4)
	public String[] esportes;
	
	@Parameter(value=5)
	public String mensagemRetorno;
	
	@Before
	public void inicializa() {
		getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
	}
	
//	@After
//	public void finaliza() {
//		killDriver();
//	}
	
//	public void assertacao(String texto) {
//		page.cadastrar();
//		Alert alerta = driver.switchTo().alert();
//		Assert.assertEquals(texto, alerta.getText());
//	}
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"", "", "", Arrays.asList(), new String[] {}, "Nome eh obrigatorio"},
			{"Gabriel", "", "", Arrays.asList(), new String[] {}, "Sobrenome eh obrigatorio"},
			{"Gabriel", "Teixeira", "", Arrays.asList(), new String[] {}, "Sexo eh obrigatorio"},
			{"Gabriel", "Teixeira", "Masculino", Arrays.asList("Carne","Vegetariano"), new String[] {}, "Tem certeza que voce eh vegetariano?"},
			{"Gabriel", "Teixeira", "Masculino", Arrays.asList("Carne"), new String[] {"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"},

		});
	}
	
	@Test
	public void deveValidarRegras() {
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		}else if(sexo.equals("Feminino")){
			page.setSexoFeminino();
		}
		
		if(comidas.contains("Carne")) page.setComidaFavoritaCarne();
		if(comidas.contains("Pizza")) page.setComidaFavoritaPizza();
		if(comidas.contains("Vegetariano")) page.setComidaFavoritaVegetariano();
		
		page.setEsporte(esportes);

//		Assert.assertEquals("Voce faz esporte ou nao?", dsl.obterTexto());
//		assertacao(mensagemRetorno);
	}
}
