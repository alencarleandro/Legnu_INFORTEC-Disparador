package br.com.legnu_propagar.disparo.modos;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import br.com.legnu_propagar.dao.DaoConfiguracao;
import br.com.legnu_propagar.dao.DaoRotina;
import br.com.legnu_propagar.disparo.Disparo.Modo;
import br.com.legnu_propagar.disparo.Disparo.Quem;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Grupos;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.model.Rotina_de_Disparo;
import br.com.legnu_propagar.util.Disparo;

public class Whatsapp {
	
	public static br.com.legnu_propagar.disparo.Disparo.Status iniciarWhatsapp(Actions act, WebDriver driver,String nome, boolean grupo, List<Mensagens> m, Integer id, Rotina_de_Disparo ID_Rotina, Modo modo,
			Quem quem, Clientes cliente, Grupos group, boolean primeiro) {
		
		if(apagaPesquisa(act, driver, nome, grupo, m, id, ID_Rotina, modo, quem, cliente, group, primeiro)) {
			if(iniciaPesquisar(act, driver, nome, grupo, m, id, ID_Rotina, modo, quem, cliente, group, primeiro)) {
				if(escritaPesquisa(act, driver, nome, grupo, m, id, ID_Rotina, modo, quem, cliente, group, primeiro)){					
					if(validaPesquisa(act, driver, nome, grupo, m, id, ID_Rotina, modo, quem, cliente, group, primeiro)){						
						if(br.com.legnu_propagar.disparo.util.envio.whatsapp(driver, m, act, ID_Rotina, modo, quem)) {
						}else{return br.com.legnu_propagar.disparo.Disparo.Status.FALHOU;}						
					} else {return br.com.legnu_propagar.disparo.Disparo.Status.NAO_ACHOU;}					
				} else {return br.com.legnu_propagar.disparo.Disparo.Status.NAO_ACHOU;}
			} else {return br.com.legnu_propagar.disparo.Disparo.Status.NAO_ACHOU;}
		} else {return br.com.legnu_propagar.disparo.Disparo.Status.NAO_ACHOU;}
		
		return br.com.legnu_propagar.disparo.Disparo.Status.ENVIADO;
		
	}
	
	public static boolean apagaPesquisa(Actions act, WebDriver driver, String nome, boolean grupo, List<Mensagens> m,
			Integer id, Rotina_de_Disparo ID_Rotina, Modo modo, Quem quem, Clientes cliente, Grupos group,
			boolean primeiro) {
		try {			
			Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(2).getConf2()));
			Robot robot = new Robot();
			for (int i = 0; i < 100; i++) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.delay(1);
			}
			robot.keyRelease(KeyEvent.VK_TAB);

			caixaDePesquisa(driver);

			for (int n = 0; n <= 100; n++) {
				Thread.sleep(1);
				act.sendKeys(Keys.DELETE).perform();
			}

			for (int n = 0; n <= 100; n++) {
				Thread.sleep(1);
				act.sendKeys(Keys.BACK_SPACE).perform();
			}

		} catch (Exception e) {
				return false;
		}

		return true;
	}
	
	public static boolean iniciaPesquisar(Actions act, WebDriver driver, String nome, boolean grupo, List<Mensagens> m, Integer id,
			Rotina_de_Disparo ID_Rotina, Modo modo,	Quem quem, Clientes cliente, Grupos group, boolean primeiro) {
		try {
			
			caixaDePesquisa(driver);

			br.com.legnu_propagar.util.Disparo.sendKeys("Pesquisando", act);
			
			
		} catch (Exception e) {
			if(primeiro) {
				return false;
			}else {
				return false;
			}
		}
		
		return true;
	}	
	
	public static boolean escritaPesquisa(Actions act, WebDriver driver,String nome, boolean grupo, List<Mensagens> m, 
			Integer id, Rotina_de_Disparo ID_Rotina, Modo modo,	Quem quem, Clientes cliente, Grupos group, boolean primeiro) {
		/* Pesquisa - escrita */
		try {
			Thread.sleep(3000);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			if (br.com.legnu_propagar.util.Disparo.existe(driver, "p.selectable-text")) {
				js.executeScript(
						"document.querySelector(\"span.selectable-text\").childNodes[0].data = '"
								+ nome + "';");
				js.executeScript("document.querySelector('[id=\"pane-side\"]').scroll(0,0);");
			} 

			Thread.sleep(3000);
		} catch (Exception e) {
			if(primeiro) {
				return false;
			}else {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean validaPesquisa(Actions act, WebDriver driver,String nome, boolean grupo, List<Mensagens> m, Integer id, Rotina_de_Disparo ID_Rotina, Modo modo,
			Quem quem, Clientes cliente, Grupos group, boolean primeiro) {
		try {
			if (grupo == true) {
				List<WebElement> lista = driver.findElements(By.cssSelector("span[title='" + nome + "']"));
				int vezes = 0;

				for (int i = 0; i < lista.size(); i++) {
					if (i == lista.size()) {
						if (vezes <= lista.size()) {
							i = 0;
							vezes = vezes + 1;
						} else {
							return false;
						}
					}

					if (lista.get(i).isDisplayed() && lista.get(i).isEnabled()) {
						if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, lista.get(i))) {
							act.keyDown(Keys.PAGE_UP).perform();
							Thread.sleep(5000);
							act.keyUp(Keys.PAGE_UP).perform();

							Thread.sleep(3000);

							if (br.com.legnu_propagar.util.Disparo.existe(driver, "span[aria-label='Você:']")) {
							} else {
								return true;
							}
						}
					}

				}
								
			} else {

				List<WebElement> lista = driver.findElements(By.cssSelector("span[title='" + nome + "']"));
				
				for (int i = 0; i < lista.size(); i++) {
					if (lista.get(i).isDisplayed() && lista.get(i).isEnabled()) {
						if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, lista.get(i))) {
							return true;
						}
					}
				}
			}
			

		} catch (Exception e) {
			return false;
		}
		
		return false;
	}	
	
	public static void caixaDePesquisa(WebDriver driver) throws InterruptedException {			
			Thread.sleep(3000);

			if (br.com.legnu_propagar.util.Disparo.existe(driver, "p.selectable-text")) {
				if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
						driver.findElement(By.cssSelector("p.selectable-text")))) {
				}
			}

			Thread.sleep(2000);
	}
}
