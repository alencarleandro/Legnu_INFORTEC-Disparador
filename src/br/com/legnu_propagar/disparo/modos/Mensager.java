package br.com.legnu_propagar.disparo.modos;

import java.awt.Robot;
import java.awt.event.KeyEvent;
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
import br.com.legnu_propagar.model.Grupos;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.model.Rotina_de_Disparo;

public class Mensager {

	public static br.com.legnu_propagar.disparo.Disparo.Status iniciarMensager(Actions act, WebDriver driver,
			String nome, boolean grupo, List<Mensagens> m, Integer id, Rotina_de_Disparo ID_Rotina, Modo modo,
			Quem quem, Clientes cli, boolean primeiro) {

		if (pesquisa(act, driver, nome, grupo, m, id, ID_Rotina, modo, quem, cli, primeiro)) {
			if (br.com.legnu_propagar.disparo.util.envio.mensager(driver, m, act, ID_Rotina, modo, quem)) {
			} else {return br.com.legnu_propagar.disparo.Disparo.Status.FALHOU;}
		} else {return br.com.legnu_propagar.disparo.Disparo.Status.NAO_ACHOU;}

		return br.com.legnu_propagar.disparo.Disparo.Status.ENVIADO;

	}

	public static boolean pesquisa(Actions act, WebDriver driver, String nome, boolean grupo, List<Mensagens> m,
			Integer id, Rotina_de_Disparo ID_Rotina, Modo modo, Quem quem, Clientes cli, boolean primeiro) {

		boolean certo = false;

		/* Pesquisa */
		try {

			Robot robot = new Robot();
			for (int i = 0; i < 100; i++) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.delay(1);
			}
			robot.keyRelease(KeyEvent.VK_TAB);

			Thread.sleep(2000);

			for (int o = 0; o < 8000; o++) {
				if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
						driver.findElement(By.cssSelector("input[aria-label='Pesquisar no Messenger']")))) {
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

			br.com.legnu_propagar.util.Disparo.sendKeys(nome, act, nome);
			Thread.sleep(5000);

			/* Validação */
			List<WebElement> lista = driver.findElements(By.cssSelector("a[role='presentation']"));

			for (int i = 0; i <= lista.size(); i++) {
				if (nome.equals(lista.get(i).getText())) {
					if (lista.get(i).isDisplayed() && lista.get(i).isEnabled()) {
						if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, lista.get(i))) {
							certo = true;
							i = 100000000;
						}
					}
				}
			}

			Thread.sleep(5000);

			if (certo) {
				br.com.legnu_propagar.util.Disparo.UltimoEnvio(grupo, id, ID_Rotina);
				br.com.legnu_propagar.disparo.util.envio.mensager(driver, m, act, ID_Rotina, modo, quem);
			}

		} catch (Exception e) {
			return false;
		}

		return true;

	}

}
