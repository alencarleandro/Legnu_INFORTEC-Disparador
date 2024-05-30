package br.com.legnu_propagar.disparo.util;

import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import br.com.legnu_propagar.dao.DaoConfiguracao;
import br.com.legnu_propagar.dao.DaoRotina;
import br.com.legnu_propagar.disparo.Disparo.Modo;
import br.com.legnu_propagar.disparo.Disparo.Quem;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.model.Rotina_de_Disparo;

public class envio {

	public static boolean whatsapp(WebDriver driver, List<Mensagens> m, Actions act, Rotina_de_Disparo ID_Rotina,
			Modo modo, Quem quem) {
		try {

			if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[data-animate-modal-popup='true']")) {
				driver.findElement(By.cssSelector("button")).click();
			}

			for (int i = 0; i < m.size(); i++) {

				if (!m.get(i).getMidia().isEmpty()) {

					// Achou pegou - Anexo
					Thread.sleep(2000);

					if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='Anexar']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='Anexar']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='Clip']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='Clip']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='Attach']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='Attach']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='anexar']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='anexar']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='clip']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='clip']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='attach']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='attach']")))) {
								break;
							}
							Thread.sleep(1);
						}

					}

					// Achou mandou - Anexo

					Thread.sleep(2000);

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.existe(driver,
								"input[accept='image/*,video/mp4,video/3gpp,video/quicktime']")) {
							driver.findElement(
									By.cssSelector("input[accept='image/*,video/mp4,video/3gpp,video/quicktime']"))
									.sendKeys(m.get(i).getMidia());
							break;
						}
						Thread.sleep(1);
					}

					// Achou Clickou - Mensagem

					Thread.sleep(12000);

					if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[title='Digite uma mensagem']")) {
						driver.findElement(By.cssSelector("div[title='Digite uma mensagem']")).click();
					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label=\"Adicione uma legenda\"]")) {
						driver.findElement(By.cssSelector("div[aria-label=\"Adicione uma legenda\"]")).click();
					}

					// Achou Mandou - Mensagem

					Thread.sleep(6000);
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudo(), act);

					// Enviou - Mensagem_Com_Midia

					Thread.sleep(8000);

					if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
							driver.findElement(By.cssSelector("span[data-icon='send']")))) {
					}

				} else {

					// Achou Clickou - Mensagem

					Thread.sleep(4000);

					if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[title='Digite uma mensagem']")) {
						driver.findElement(By.cssSelector("div[title='Digite uma mensagem']")).click();
					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label=\"Adicione uma legenda\"]")) {
						driver.findElement(By.cssSelector("div[aria-label=\"Adicione uma legenda\"]")).click();
					}
					// Achou Mandou - Mensagem

					Thread.sleep(2000);
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudo(), act);

					Thread.sleep(8000);

					act.sendKeys(Keys.ENTER).perform();

				}

				if (!m.get(i).getArquivo().isBlank()) {

					// Achou pegou - Anexo

					Thread.sleep(2000);

					if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='Anexar']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='Anexar']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='Clip']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='Clip']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='Attach']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='Attach']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='anexar']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='anexar']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='clip']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='clip']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='attach']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='attach']")))) {
								break;
							}
							Thread.sleep(1);
						}

					}

					// Achou mandou - Anexo

					Thread.sleep(2000);

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.existe(driver,
								"input[accept='image/*,video/mp4,video/3gpp,video/quicktime']")) {
							driver.findElement(
									By.cssSelector("input[accept='image/*,video/mp4,video/3gpp,video/quicktime']"))
									.sendKeys(m.get(i).getArquivo());
							break;
						}
						Thread.sleep(1);
					}

					// Achou Clickou - Mensagem

					if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[title='Digite uma mensagem']")) {
						driver.findElement(By.cssSelector("div[title='Digite uma mensagem']")).click();
					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label=\"Adicione uma legenda\"]")) {
						driver.findElement(By.cssSelector("div[aria-label=\"Adicione uma legenda\"]")).click();
					}

					// Achou Mandou - Mensagem

					Thread.sleep(2000);
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoArquivo(), act);

					// Enviou - Mensagem_Com_Arquivo

					Thread.sleep(2000);
					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
								driver.findElement(By.cssSelector("span[data-icon='send']")))) {
							break;
						}
						Thread.sleep(1);
					}

				}

				// Enquete

				if (!m.get(i).getTituloEnquete().isBlank() && !m.get(i).getConteudoEnquete_1().isBlank()
						&& !m.get(i).getConteudoEnquete_2().isBlank()) {

					// Achou pegou - Anexo

					Thread.sleep(2000);

					if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='Anexar']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='Anexar']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='Clip']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='Clip']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='Attach']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='Attach']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='anexar']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='anexar']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='clip']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='clip']")))) {
								break;
							}
							Thread.sleep(1);
						}

					} else if (br.com.legnu_propagar.util.Disparo.existe(driver, "div[aria-label='attach']")) {

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
									driver.findElement(By.cssSelector("div[aria-label='attach']")))) {
								break;
							}
							Thread.sleep(1);
						}

					}

					// Achou Clickou - Enquete

					List<WebElement> lista = null;

					Thread.sleep(2000);

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.existe(driver, "li")) {
							lista = driver.findElements(By.cssSelector("li"));
							break;
						}
						Thread.sleep(1);
					}

					Thread.sleep(2000);

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.existe(driver, lista.get(o))
								&& lista.get(o).getText().equals("Enquete")) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, lista.get(o))) {
								break;
							}
						}
						Thread.sleep(1);
					}

					// Achou Clickou_Escreveu - Campo

					if (br.com.legnu_propagar.util.Disparo.existe(driver,
							"[style='padding-top: 8px; padding-bottom: 5px;' ]")) {

						List<WebElement> opcoes = driver
								.findElements(By.cssSelector("[style='padding-top: 8px; padding-bottom: 5px;' ]"));

						Thread.sleep(2000);

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(0))) {
								break;
							}
							Thread.sleep(1);
						}

						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getTituloEnquete(), act);
						Thread.sleep(2000);

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(1))) {
								break;
							}
							Thread.sleep(1);
						}

						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoEnquete_1(), act);
						Thread.sleep(2000);

						for (int o = 0; o < 8000; o++) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(2))) {
								break;
							}
							Thread.sleep(1);
						}

						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoEnquete_2(), act);

						if (((!m.get(i).getConteudoEnquete_3().isEmpty() || !m.get(i).getConteudoEnquete_3().isBlank())
								&& (opcoes.size() >= 3))
								&& br.com.legnu_propagar.util.Disparo.existe(driver,
										"[style='padding-top: 8px; padding-bottom: 5px;' ]")) {

							opcoes = driver
									.findElements(By.cssSelector("[style='padding-top: 8px; padding-bottom: 5px;' ]"));

							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(3))) {
									break;
								}
								Thread.sleep(1);
							}

							br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoEnquete_3(), act);
						}

						if (((!m.get(i).getConteudoEnquete_4().isEmpty() || !m.get(i).getConteudoEnquete_4().isBlank())
								&& (opcoes.size() >= 4))
								&& br.com.legnu_propagar.util.Disparo.existe(driver,
										"[style='padding-top: 8px; padding-bottom: 5px;' ]")) {
							opcoes = driver
									.findElements(By.cssSelector("[style='padding-top: 8px; padding-bottom: 5px;' ]"));
							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(4))) {
									break;
								}
								Thread.sleep(1);
							}

							br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoEnquete_4(), act);
						}

						if (((!m.get(i).getConteudoEnquete_5().isEmpty() || !m.get(i).getConteudoEnquete_5().isBlank())
								&& (opcoes.size() >= 5))
								&& br.com.legnu_propagar.util.Disparo.existe(driver,
										"[style='padding-top: 8px; padding-bottom: 5px;' ]")) {
							opcoes = driver
									.findElements(By.cssSelector("[style='padding-top: 8px; padding-bottom: 5px;' ]"));
							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(5))) {
									break;
								}
								Thread.sleep(1);
							}

							br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoEnquete_5(), act);
						}

						if (((!m.get(i).getConteudoEnquete_6().isEmpty() || !m.get(i).getConteudoEnquete_6().isBlank())
								&& (opcoes.size() >= 6))
								&& br.com.legnu_propagar.util.Disparo.existe(driver,
										"[style='padding-top: 8px; padding-bottom: 5px;' ]")) {
							opcoes = driver
									.findElements(By.cssSelector("[style='padding-top: 8px; padding-bottom: 5px;' ]"));
							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(6))) {
									break;
								}
								Thread.sleep(1);
							}

							br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoEnquete_6(), act);
						}

						if (((!m.get(i).getConteudoEnquete_7().isEmpty() || !m.get(i).getConteudoEnquete_7().isBlank())
								&& (opcoes.size() >= 7))
								&& br.com.legnu_propagar.util.Disparo.existe(driver,
										"[style='padding-top: 8px; padding-bottom: 5px;' ]")) {
							opcoes = driver
									.findElements(By.cssSelector("[style='padding-top: 8px; padding-bottom: 5px;' ]"));
							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(7))) {
									break;
								}
								Thread.sleep(1);
							}

							br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoEnquete_7(), act);
						}

						if (((!m.get(i).getConteudoEnquete_8().isEmpty() || !m.get(i).getConteudoEnquete_8().isBlank())
								&& (opcoes.size() >= 8))
								&& br.com.legnu_propagar.util.Disparo.existe(driver,
										"[style='padding-top: 8px; padding-bottom: 5px;' ]")) {
							opcoes = driver
									.findElements(By.cssSelector("[style='padding-top: 8px; padding-bottom: 5px;' ]"));
							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(8))) {
									break;
								}
								Thread.sleep(1);
							}

							br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoEnquete_8(), act);
						}

						if (((!m.get(i).getConteudoEnquete_9().isEmpty() || !m.get(i).getConteudoEnquete_9().isBlank())
								&& (opcoes.size() >= 9))
								&& br.com.legnu_propagar.util.Disparo.existe(driver,
										"[style='padding-top: 8px; padding-bottom: 5px;' ]")) {
							opcoes = driver
									.findElements(By.cssSelector("[style='padding-top: 8px; padding-bottom: 5px;' ]"));
							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(9))) {
									break;
								}
								Thread.sleep(1);
							}

							br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoEnquete_9(), act);
						}

						if (((!m.get(i).getConteudoEnquete_10().isEmpty()
								|| !m.get(i).getConteudoEnquete_10().isBlank()) && (opcoes.size() >= 10))
								&& br.com.legnu_propagar.util.Disparo.existe(driver,
										"[style='padding-top: 8px; padding-bottom: 5px;' ]")) {
							opcoes = driver
									.findElements(By.cssSelector("[style='padding-top: 8px; padding-bottom: 5px;' ]"));
							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, opcoes.get(10))) {
									break;
								}
								Thread.sleep(1);
							}

							br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoEnquete_10(), act);
						}

						if (m.get(i).isVariasRespostas()) {

						} else {

							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
										driver.findElement(
												By.cssSelector("input[aria-label='Ativar ou desativar configurações']"))
												.findElement(By.xpath("parent::*[1]")))) {
									break;
								}
								Thread.sleep(1);
							}

						}

						Thread.sleep(8000);

						if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
								driver.findElement(By.cssSelector("span[data-icon='send']")))) {
						}

						Thread.sleep(8000);

						if (br.com.legnu_propagar.util.Disparo.existe(driver, "button[title='Mostrar votos']")) {

							List<WebElement> mens = driver
									.findElements(By.cssSelector("button[title='Mostrar votos']"));

							Robot robot = new Robot();
							robot.mouseMove(
									mens.get(mens.size() - 1).findElement(By.xpath("child::*[1]")).getLocation().x,
									mens.get(mens.size() - 1).findElement(By.xpath("child::*[1]")).getLocation().y);

							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
										driver.findElement(By.cssSelector("div[aria-label='Menu de contexto']")))) {
									break;
								}
								Thread.sleep(1);
							}

							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
										driver.findElement(By.cssSelector("div[aria-label='Editar etiqueta']")))) {
									break;
								}
								Thread.sleep(1);
							}

							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
										driver.findElement(By.cssSelector("button[title='Etiquetar itens']")))) {
									break;
								}
								Thread.sleep(1);
							}

							Thread.sleep(2000);

							for (int o = 0; o < 8000; o++) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
										driver.findElement(By.cssSelector("span[title='ENQUETES']")))) {
									break;
								}
								Thread.sleep(1);
							}

							mens = driver.findElements(By.cssSelector("button"));

							for (int o = 0; o < mens.size(); o++) {
								if (mens.get(o).getText().equals("Salvar")) {
									if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, mens.get(o))) {
										break;
									}
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public static boolean mensager(WebDriver driver, List<Mensagens> m, Actions act, Rotina_de_Disparo ID_Rotina,
			Modo modo, Quem quem) {
		try {
			for (int i = 0; i < m.size(); i++) {
				if (!m.get(i).getMidia().isEmpty()) {

					Thread.sleep(2000);

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.existe(driver,
								driver.findElement(By.cssSelector("input[type='file']")))) {
							driver.findElement(By.cssSelector("input[type='file']")).sendKeys(m.get(i).getMidia());
							break;
						}
						Thread.sleep(1);
					}

					Thread.sleep(2000);

					if (m.get(i).getConteudo().isEmpty()) {
						act.sendKeys(Keys.ENTER).perform();
					}
				}

				if (!m.get(i).getConteudo().isEmpty()) {

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
								driver.findElement(By.cssSelector("div[aria-label='Mensagem']")))) {
							break;
						}
						Thread.sleep(1);
					}

					Thread.sleep(Integer.parseInt("3000"));
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudo(), act, "");
					Thread.sleep(Integer.parseInt("3000"));
					act.sendKeys(Keys.ENTER).perform();
					Thread.sleep(Integer.parseInt("3000"));
				}

				if (!m.get(i).getArquivo().isEmpty()) {

					Thread.sleep(2000);

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.existe(driver,
								driver.findElement(By.cssSelector("input[type='file']")))) {
							driver.findElement(By.cssSelector("input[type='file']")).sendKeys(m.get(i).getArquivo());
							break;
						}
						Thread.sleep(1);
					}
					Thread.sleep(2000);

					if (m.get(i).getConteudo().isEmpty()) {
						act.sendKeys(Keys.ENTER).perform();
					}
				}

				if (!m.get(i).getConteudo().isEmpty()) {

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
								driver.findElement(By.cssSelector("div[aria-label='Mensagem']")))) {
							break;
						}
						Thread.sleep(1);
					}

					Thread.sleep(Integer.parseInt("3000"));
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoArquivo(), act, "");
					Thread.sleep(Integer.parseInt("3000"));
					act.sendKeys(Keys.ENTER).perform();
				}
			}

			driver.get("https://www.facebook.com/messages/");

			Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(3).getConf2()));

		} catch (Exception e) {
			return false;
		}

		return true;

	}

	public static boolean facebook(WebDriver driver, List<Mensagens> m, Actions act, Rotina_de_Disparo ID_Rotina,
			Modo modo, Quem quem) {
		try {

			List<WebElement> lista;

			for (int o = 0; o < m.size(); o++) {

				if (!m.get(o).getConteudo().isEmpty() || !m.get(o).getMidia().isEmpty()) {

					lista = driver.findElements(By.cssSelector("div[role='button']"));

					for (int i = (lista.size() - 1); i >= 0; i--) {
						if (lista.get(i).isDisplayed() && lista.get(i).isEnabled()
								&& (lista.get(i).getText().equals("Escreva algo...")
										|| lista.get(i).getText().equals("Crie uma publicação aberta…"))) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, lista.get(i))) {
								break;
							}
						}
					}

					Thread.sleep(5000);

					lista = driver.findElements(By.cssSelector("div[role='presentation']"));

					for (int i = (lista.size() - 1); i >= 0; i--) {
						if (lista.get(i).isDisplayed() && lista.get(i).isEnabled()
								&& (lista.get(i).getText().equals("Escreva algo...")
										|| lista.get(i).getText().equals("Crie uma publicação aberta…"))) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, lista.get(i))) {
								break;
							}
						}
					}

					Thread.sleep(5000);
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudo(), act, "");

					Thread.sleep(5000);

					if (!m.get(o).getMidia().isEmpty()) {
						if (!br.com.legnu_propagar.util.Disparo.existe(driver,
								"input[accept=\"image/*,image/heif,image/heic,video/*,video/mp4,video/x-m4v,video/x-matroska,.mkv\"]")) {
							driver.findElement(By.cssSelector("div[aria-label=\"Foto/vídeo\"]")).click();
						}

						driver.findElement(By.cssSelector(
								"input[accept=\"image/*,image/heif,image/heic,video/*,video/mp4,video/x-m4v,video/x-matroska,.mkv\"]"))
								.sendKeys(m.get(o).getMidia());
						Thread.sleep(5000);
					}

					driver.findElement(By.cssSelector("div[aria-label='Publicar']")).click();

					Thread.sleep(20000);
				}

				if (!m.get(o).getTituloEnquete().isEmpty() && !m.get(o).getConteudoEnquete_1().isEmpty()
						&& !m.get(o).getConteudoEnquete_2().isEmpty()) {
					lista = driver.findElements(By.cssSelector("span"));

					for (int i = (lista.size() - 1); i >= 0; i--) {
						if (lista.get(i).isDisplayed() && lista.get(i).isEnabled()
								&& (lista.get(i).getText().equals("Enquete"))) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, lista.get(i))) {
								break;
							}
						}
					}
					Thread.sleep(5000);

					lista = driver.findElements(By.cssSelector("div[role='presentation']"));

					for (int i = (lista.size() - 1); i >= 0; i--) {
						if (lista.get(i).isDisplayed() && lista.get(i).isEnabled()
								&& (lista.get(i).getText().equals("Escreva algo...")
										|| lista.get(i).getText().equals("Crie uma publicação aberta…"))) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, lista.get(i))) {
								break;
							}
						}
					}

					Thread.sleep(5000);
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getTituloEnquete(), act, "");

					Thread.sleep(5000);
					driver.findElement(By.cssSelector("label[aria-label=\"Opção 1\"]")).click();
					Thread.sleep(5000);
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudoEnquete_1(), act, "");

					Thread.sleep(5000);
					driver.findElement(By.cssSelector("label[aria-label=\"Opção 2\"]")).click();
					Thread.sleep(5000);
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudoEnquete_2(), act, "");

					if (!m.get(o).getConteudoEnquete_3().isEmpty()) {
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("label[aria-label=\"Opção 3\"]")).click();
						Thread.sleep(5000);
						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudoEnquete_3(), act, "");
					}

					if (!m.get(o).getConteudoEnquete_4().isEmpty()) {
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("div[aria-label=\"Adicionar opção\"]")).click();
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("label[aria-label=\"Opção 4\"]")).click();
						Thread.sleep(5000);
						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudoEnquete_4(), act, "");
					}

					if (!m.get(o).getConteudoEnquete_5().isEmpty()) {
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("div[aria-label=\"Adicionar opção\"]")).click();
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("label[aria-label=\"Opção 5\"]")).click();
						Thread.sleep(5000);
						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudoEnquete_5(), act, "");
					}

					if (!m.get(o).getConteudoEnquete_6().isEmpty()) {
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("div[aria-label=\"Adicionar opção\"]")).click();
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("label[aria-label=\"Opção 6\"]")).click();
						Thread.sleep(5000);
						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudoEnquete_6(), act, "");
					}

					if (!m.get(o).getConteudoEnquete_7().isEmpty()) {
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("div[aria-label=\"Adicionar opção\"]")).click();
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("label[aria-label=\"Opção 7\"]")).click();
						Thread.sleep(5000);
						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudoEnquete_7(), act, "");
					}

					if (!m.get(o).getConteudoEnquete_8().isEmpty()) {
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("div[aria-label=\"Adicionar opção\"]")).click();
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("label[aria-label=\"Opção 8\"]")).click();
						Thread.sleep(5000);
						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudoEnquete_8(), act, "");
					}

					if (!m.get(o).getConteudoEnquete_9().isEmpty()) {
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("div[aria-label=\"Adicionar opção\"]")).click();
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("label[aria-label=\"Opção 9\"]")).click();
						Thread.sleep(5000);
						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudoEnquete_9(), act, "");
					}

					if (!m.get(o).getConteudoEnquete_10().isEmpty()) {
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("div[aria-label=\"Adicionar opção\"]")).click();
						Thread.sleep(5000);
						driver.findElement(By.cssSelector("label[aria-label=\"Opção 10\"]")).click();
						Thread.sleep(5000);
						br.com.legnu_propagar.util.Disparo.sendKeys(m.get(o).getConteudoEnquete_10(), act, "");
					}

					Thread.sleep(5000);
					driver.findElement(By.cssSelector("div[aria-label=\"Opções de enquete\"]")).click();

					lista = driver.findElements(By.cssSelector("span"));

					for (int i = (lista.size() - 1); i >= 0; i--) {
						if (lista.get(i).isDisplayed() && lista.get(i).isEnabled()
								&& (lista.get(i).getText().equals("Permitir que qualquer pessoa adicione opções"))) {
							if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, lista.get(i))) {
								break;
							}
						}
					}
					Thread.sleep(5000);

					if (!m.get(o).isVariasRespostas()) {
						lista = driver.findElements(By.cssSelector("span"));

						for (int i = (lista.size() - 1); i >= 0; i--) {
							if (lista.get(i).isDisplayed() && lista.get(i).isEnabled() && (lista.get(i).getText()
									.equals("Permitir que as pessoas escolham várias respostas"))) {
								if (br.com.legnu_propagar.util.Disparo.Clicavel(driver, lista.get(i))) {
									break;
								}
							}
						}
						Thread.sleep(5000);
					}

					driver.findElement(By.cssSelector("div[aria-label='Publicar']")).click();

					Thread.sleep(20000);
				}

			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean instagram(WebDriver driver, List<Mensagens> m, Actions act, Rotina_de_Disparo ID_Rotina,
			Modo modo, Quem quem) {
		try {
			for (int i = 0; i < m.size(); i++) {

				if (!m.get(i).getMidia().isEmpty()) {

					Thread.sleep(2000);

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.existe(driver,
								driver.findElement(By.cssSelector("input[type='file']")))) {
							driver.findElement(By.cssSelector("input[type='file']")).sendKeys(m.get(i).getMidia());
							break;
						}
						Thread.sleep(1);
					}

					Thread.sleep(2000);

					if (m.get(i).getConteudo().isEmpty()) {
						act.sendKeys(Keys.ENTER).perform();
					}
				}

				if (!m.get(i).getConteudo().isEmpty()) {

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
								driver.findElement(By.cssSelector("div[aria-label='Mensagem']")))) {
							break;
						}
						Thread.sleep(1);
					}

					Thread.sleep(Integer.parseInt("3000"));
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudo(), act, "");
					Thread.sleep(Integer.parseInt("3000"));
					act.sendKeys(Keys.ENTER).perform();
					Thread.sleep(Integer.parseInt("3000"));
				}

				if (!m.get(i).getArquivo().isEmpty()) {

					Thread.sleep(2000);

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.existe(driver,
								driver.findElement(By.cssSelector("input[type='file']")))) {
							driver.findElement(By.cssSelector("input[type='file']")).sendKeys(m.get(i).getArquivo());
							break;
						}
						Thread.sleep(1);
					}

					Thread.sleep(2000);

					if (m.get(i).getConteudoArquivo().isEmpty()) {
						act.sendKeys(Keys.ENTER).perform();
					}
				}

				if (!m.get(i).getConteudo().isEmpty()) {

					for (int o = 0; o < 8000; o++) {
						if (br.com.legnu_propagar.util.Disparo.Clicavel(driver,
								driver.findElement(By.cssSelector("div[aria-label='Mensagem']")))) {
							break;
						}

						Thread.sleep(1);
					}

					Thread.sleep(Integer.parseInt("3000"));
					br.com.legnu_propagar.util.Disparo.sendKeys(m.get(i).getConteudoArquivo(), act, "");
					Thread.sleep(Integer.parseInt("3000"));
					act.sendKeys(Keys.ENTER).perform();
				}

			}

			Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(4).getConf2()));

		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
