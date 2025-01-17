package br.com.legnu_propagar.disparo.modos;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import br.com.legnu_propagar.disparo.Disparo.Modo;
import br.com.legnu_propagar.disparo.Disparo.Quem;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.model.Rotina_de_Disparo;

public class Gmail {
	public static br.com.legnu_propagar.disparo.Disparo.Status iniciarGmail(Actions act, WebDriver driver, String nome, boolean grupo, List<Mensagens> m,
			Integer id, Rotina_de_Disparo ID_Rotina, Modo modo, Quem quem, Clientes cliente, boolean primeiro) {		
		
			if(iniciaPesquisar(act, driver, nome, grupo, m, id, ID_Rotina, modo, quem, cliente, primeiro)) {
			} else {return br.com.legnu_propagar.disparo.Disparo.Status.NAO_ACHOU;}	
			
		return br.com.legnu_propagar.disparo.Disparo.Status.ENVIADO;
		
	}

	public static boolean iniciaPesquisar(Actions act, WebDriver driver, String nome, boolean grupo, List<Mensagens> m,
			Integer id, Rotina_de_Disparo ID_Rotina, Modo modo, Quem quem, Clientes cliente, boolean primeiro) {
		
		try {

			Thread.sleep(10000);

			List<WebElement> lista =  driver.findElements(By.cssSelector("img[aria-label='Salvar e fechar']"));
			
			while(lista.size() > 0) {
				lista.get(0).click();		
				lista =  driver.findElements(By.cssSelector("img[aria-label='Salvar e fechar']"));
				Thread.sleep(3000);
			}

			Robot robot = new Robot();
			for (int i = 0; i < 100; i++) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.delay(1);
			}
			robot.keyRelease(KeyEvent.VK_TAB);

			Thread.sleep(2000);
			
			lista =  driver.findElements(By.cssSelector("div[role='button']"));
			
			for(int i = 0; i < lista.size(); i++) {
				if(lista.get(i).getText().equals("Escrever")) {
					lista.get(i).click();
					break;
				}				
			}
			
	
			Thread.sleep(2000);
			
			driver.findElement(By.cssSelector("input[aria-label='Destinatários']")).click();
			br.com.legnu_propagar.util.Disparo.sendKeys(nome, act);		
			Thread.sleep(2000);
			act.sendKeys(Keys.ENTER).perform();					
			Thread.sleep(2000);
			act.sendKeys(Keys.TAB).perform();			
			Thread.sleep(2000);			   
			driver.findElement(By.cssSelector("input[aria-label='Assunto']")).click();
			br.com.legnu_propagar.util.Disparo.sendKeys(m.get(0).getTitulo(), act);
			
			Thread.sleep(2000);
			   
			driver.findElement(By.cssSelector("div[aria-label='Corpo da mensagem']")).click();
			br.com.legnu_propagar.util.Disparo.sendKeys("Escrevendo", act);			
			
			Thread.sleep(3000);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			
			js.executeScript(
					"document.querySelectorAll(\"div[aria-label='Corpo da mensagem']\")[0].innerHTML = \""
							+ m.get(0).getConteudo().replace("\"", "\\\"").replace("\n", "")+"\"");

			Thread.sleep(5000);
			
			driver.findElement(By.cssSelector("div[aria-label='Enviar ‪(Ctrl-Enter)‬']")).click();			
	
			
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}
}
