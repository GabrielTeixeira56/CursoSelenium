import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	public void escrever(String idCampo, String texto) {
		driver.findElement(By.id(idCampo)).clear();
		driver.findElement(By.id(idCampo)).sendKeys(texto);
	}
	
	public void escreverBy(By by, String texto) {
		//driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}
	
	public String obterValorCampo(String idCampo) {
		return driver.findElement(By.id(idCampo)).getAttribute("value");
	}
	
	/*public void limparCampo(String id) {
		driver.findElement(By.id(id)).clear();
	}*/
	
	public void clicarRadio(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public boolean isRadioMarcado(String id) {
		return driver.findElement(By.id(id)).isSelected();
	}
	
	public void selecionarCombo(String id, String valor) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		//combo.selectByIndex(2);
		//combo.selectByValue("superior");
		combo.selectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id) {
		WebElement element = driver.findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public void clicarBotao(String id) {
		driver.findElement(By.id(id)).click();
	}
	
	public void clicarLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	
	public String obterTexto(By by) {
		 return driver.findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		 return obterTexto(By.id(id));
	}
	
	public boolean validarResultadoStartsWith(String id, String texto) {
		return driver.findElement(By.id(id)).getText().startsWith(texto);
	}
	
	public boolean validarResultadoEndsWith(String id, String texto) {
		return driver.findElement(By.id(id)).getText().endsWith(texto);
	}
	
	public void entrarFrame(String nomeFrame) {
		driver.switchTo().frame(nomeFrame);
	}
	
	public void sairFrame() {
		driver.switchTo().defaultContent();
	}
	
	public String alertaObterTextoEAceita() {
		Alert alerta = driver.switchTo().alert();
		String mensagem = alerta.getText();
		alerta.accept();
		return mensagem;
	}
	
	public void trocarJanela(String id) {
		driver.switchTo().window(id);
	}
	
	//JS
	public Object executarJS(String comando, Object... parametros) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(comando, parametros);		
		
		/*js.executeScript("alert('Testando JS via Selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via JS'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");*/
	}
	
	//navegando entre tabelas
	public void clicarBotaoTabela(String colunaBusca,String valor, String colunaBotao, String idTabela) {
		
		//procurar coluna do registro
		WebElement tabela = driver.findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar a coluna do botão
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

		//clicar no botão da celula encontrada
		WebElement celula = driver.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
	}

	private int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		
		return idLinha;
	}

	private int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		
		for(int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
}
