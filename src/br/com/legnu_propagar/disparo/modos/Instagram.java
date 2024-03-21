package br.com.legnu_propagar.disparo.modos;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
																																										
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import br.com.legnu_propagar.dao.DaoRotina;
import br.com.legnu_propagar.disparo.Disparo.Modo;
import br.com.legnu_propagar.disparo.Disparo.Quem;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.model.Rotina_de_Disparo;

public class Instagram {
	
	public static br.com.legnu_propagar.disparo.Disparo.Status iniciarDirect(Actions act, WebDriver driver, String nome, boolean grupo, List<Mensagens> m,
			Integer id, Rotina_de_Disparo ID_Rotina, Modo modo, Quem quem, Clientes cliente, boolean primeiro) {
		
		
			if(iniciaPesquisar(act, driver, nome, grupo, m, id, ID_Rotina, modo, quem, cliente, primeiro)) {					
				if(br.com.legnu_propagar.disparo.util.envio.instagram(driver, m, act, ID_Rotina, modo, quem)) {
				}else{return br.com.legnu_propagar.disparo.Disparo.Status.FALHOU;}	
			} else {return br.com.legnu_propagar.disparo.Disparo.Status.NAO_ACHOU;}		
		return br.com.legnu_propagar.disparo.Disparo.Status.ENVIADO;
		
	}

	public static boolean iniciaPesquisar(Actions act, WebDriver driver, String nome, boolean grupo, List<Mensagens> m,
			Integer id, Rotina_de_Disparo ID_Rotina, Modo modo, Quem quem, Clientes cliente, boolean primeiro) {
		WebElement aux;
		try {

			Thread.sleep(10000);

			Robot robot = new Robot();
			for (int i = 0; i < 100; i++) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.delay(1);
			}
			robot.keyRelease(KeyEvent.VK_TAB);

			Thread.sleep(2000);

			for (int o = 0; o < 8000; o++) {
				if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, driver.findElement(By.cssSelector("svg[aria-label='Nova mensagem']"))
						.findElement(By.xpath("parent::*[1]")).findElement(By.xpath("parent::*[1]")))) {
					break;
				}
				Thread.sleep(1);
			}

			Thread.sleep(2000);

			for (int o = 0; o < 8000; o++) {
				if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, driver.findElement(By.cssSelector("input[placeholder='Pesquisar...']"))
						.findElement(By.xpath("parent::*[1]")).findElement(By.xpath("parent::*[1]")))) {
					break;
				}
				Thread.sleep(1);
			}

			Thread.sleep(500);

			for (int n = 0; n <= 100; n++) {
				Thread.sleep(1);
				act.sendKeys(Keys.DELETE).perform();
			}

			for (int n = 0; n <= 100; n++) {
				Thread.sleep(1);
				act.sendKeys(Keys.BACK_SPACE).perform();
															}

			Thread.sleep(2000);

			br.com.legnu_propagar.util.Disparo.sendKeys(nome, act);

			Thread.sleep(5000);
			
			for(int i = 1; i <= 10; i++) {
				aux = driver.findElement(By.cssSelector("div.x1dm5mii:nth-child("+ i +") > div:nth-child("+ i +")"));
				if(aux.getText().contains(nome)) {
					aux.click();
					break;
				}					
			}			

			List<WebElement> lista = driver.findElements(By.cssSelector("div[role='button']"));
			
			for(int i = (lista.size() - 1); i > 0; i--) {
				if(lista.get(i).getText().equals("Bate-papo")) {
					lista.get(i).click();
					break;
				}
			}

			Thread.sleep(2000);

		} catch (Exception e) {
			return false;
		}
		
		return true;

	}
	
}
