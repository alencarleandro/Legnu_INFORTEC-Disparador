package br.com.legnu_propagar.util;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import br.com.legnu_propagar.dao.DaoConfiguracao;
import br.com.legnu_propagar.dao.DaoRotina;
import br.com.legnu_propagar.disparo.Disparo.Modo;
import br.com.legnu_propagar.disparo.Disparo.Quem;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Grupos;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.model.Profiles;
import br.com.legnu_propagar.model.Rotina_de_Disparo;
import br.com.legnu_propagar.util.Alerta;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.legnu_propagar.dao.DaoClientes;
import br.com.legnu_propagar.dao.DaoConfiguracao;
import br.com.legnu_propagar.dao.DaoGrupos;
import br.com.legnu_propagar.dao.DaoRotina;
import br.com.legnu_propagar.disparo.Disparo.Modo;
import br.com.legnu_propagar.disparo.Disparo.Quem;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Grupos;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.model.Rotina_de_Disparo;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class Disparo {

	public static void UltimoEnvio(boolean grupo, Integer id, Rotina_de_Disparo ID_Rotina) {
		/* Ultimo Envio */
		try {
			if (grupo == true) {
				DaoGrupos.AtualizarUltimoEnvio(id);
			} else {
				DaoClientes.AtualizarUltimoEnvio(id);
			}
			DaoRotina.editarRotina(ID_Rotina.getId(), (ID_Rotina.getRotina() + "\nUltimoEnvio com Sucesso!"));
		} catch (Exception e) {
			DaoRotina.editarRotina(ID_Rotina.getId(), (ID_Rotina.getRotina() + "\n F@§ UltimoEnvio Falhou!\n\n" + e));
			return;
		}
	}

	public static boolean existe(WebDriver driver, String Path) {
		try {
			driver.findElement(By.cssSelector(Path));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean existe(WebDriver driver, WebElement element) {
		try {
			if (element != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean Clicavel(WebDriver driver, WebElement element) {
		try {
			element.click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void sendKeys(String keys, Actions act) {
		try {
			for (int i = 0; i < keys.length(); i++) {
				if (String.valueOf(keys.charAt(i)).equals("\n")) {
					act.keyDown(Keys.SHIFT).perform();
					act.sendKeys(Keys.ENTER).perform();
					act.keyUp(Keys.SHIFT).perform();
				} else {
					act.sendKeys(String.valueOf(keys.charAt(i))).perform();
					
				}
			}		
		} catch (Exception e) {
		}
	}

	public static void sendKeys(String keys, Actions act, String face) {
		try {
			for (int i = 0; i < keys.length(); i++) {
				if (String.valueOf(keys.charAt(i)).equals("\n")) {
					act.keyDown(Keys.SHIFT).perform();
					act.sendKeys(Keys.ENTER).perform();
					act.keyUp(Keys.SHIFT).perform();
				} else {
					act.sendKeys(String.valueOf(keys.charAt(i))).perform();
				}
				Thread.sleep(400);
			}
		} catch (Exception e) {
		}
	}

	public static void descerScroll(WebDriver driver) {
		try {

			int forScroll;
			Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(2).getConf1()));
			JavascriptExecutor js = (JavascriptExecutor) driver;

			for (forScroll = 1; forScroll <= 400000; forScroll = forScroll + 150) {
				js.executeScript("document.querySelector('[id=\"pane-side\"]').scroll(0," + forScroll + ");");
			}

			for (forScroll = 400000; forScroll >= 0; forScroll = forScroll - 150) {
				js.executeScript("document.querySelector('[id=\"pane-side\"]').scroll(0," + forScroll + ");");
			}

			for (forScroll = 1; forScroll <= 500000; forScroll = forScroll + 150) {
				js.executeScript("document.querySelector('[id=\"pane-side\"]').scroll(0," + forScroll + ");");
			}

		} catch (org.openqa.selenium.JavascriptException e) {
			JOptionPane.showMessageDialog(null, "Scroll não encontrado, aumente o tempo (SleepInicio)");
			System.out.println("Scroll: " + e);
			driver.quit();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Decida error Linha: " + e.getStackTrace() + "\n\n" + e);
			System.out.println("Scroll: " + e);
		}
	}

}
