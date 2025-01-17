package br.com.legnu_propagar.disparo;

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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;

import br.com.legnu_propagar.dao.DaoConfiguracao;
import br.com.legnu_propagar.dao.DaoOcorrencia;
import br.com.legnu_propagar.dao.DaoRotina;
import br.com.legnu_propagar.disparo.Disparo.Modo;
import br.com.legnu_propagar.disparo.Disparo.Quem;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Grupos;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.model.Profiles;
import br.com.legnu_propagar.model.Rotina_de_Disparo;
import br.com.legnu_propagar.util.Alerta;

public class Disparo {

	private static List<Grupos> listaGrupos;
	private static List<Clientes> listaClientes;
	private static List<Mensagens> listaMensagens;
	
	private static WebDriver driver;
	private static Actions act;

	public static void disparar(Quem quem, Modo modo, List<Grupos> grupos, List<Clientes> clientes,
			List<Mensagens> mensagens, Profiles perfil) {
		
		listaGrupos = grupos;
		listaClientes = clientes;
		listaMensagens = mensagens;
		
		List<Clientes> listaPendentesClientes = new ArrayList<Clientes>();
		List<Grupos> listaPendentesGrupos = new ArrayList<Grupos>();

		/**
		 * Configuração do Navegador*
		 */

		JOptionPane.showMessageDialog(null, "Disparo iniciado clicke no OK e aguarde.");

		System.setProperty("webdriver.gecko.driver", DaoConfiguracao.pegarConfiguracao(1).getExecutavelGecko());
		FirefoxOptions options = new FirefoxOptions();

		options.setBinary(DaoConfiguracao.pegarConfiguracao(1).getExecutavelFirefox());

		File firefoxProfileFolder = new File(perfil.getPath());
		FirefoxProfile profile = new FirefoxProfile(firefoxProfileFolder);
		profile.setAcceptUntrustedCertificates(true);
		options.setProfile(profile);

		/**
		 * Configuração do Driver && Lista de Mensagens selecionadas*
		 */

		driver = new FirefoxDriver(options);
		act = new Actions(driver);
		
		Status status;
		
		if (modo.equals(Modo.WHATSAPP)) {
			driver.get("https://web.whatsapp.com/");
			br.com.legnu_propagar.util.Disparo.descerScroll(driver);
		} else if (modo.equals(Modo.FACEBOOK)) {

			if (quem.equals(Quem.GRUPOS)) {
				driver.get("https://www.facebook.com/groups/");
			} else {
				driver.get("https://www.facebook.com/messages/");
			}

			try {
				Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(3).getConf1()));
			} catch (Exception e) {
			}

		} else if (modo.equals(Modo.INSTAGRAM)) {
			driver.get("https://www.instagram.com/direct/");							
			try {
				Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(4).getConf1()));
			} catch (Exception e) {
			}
		} else if (modo.equals(Modo.EMAIL)) {
			driver.get("https://mail.google.com/mail");
			try {
				Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(5).getConf1()));
			} catch (Exception e) {
			}			
		}

		DaoRotina.inserirRotina("Inicio da Rotina: ");
		
		/*
		 * MODO WHATSAPP
		 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		if (modo.equals(Modo.WHATSAPP)) {

			DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(),
					(DaoRotina.pegarUltimo().getRotina() + "\nOperando no WhatsApp."));

			if (quem.equals(Quem.CLIENTES)) {
				
				for (int i = 0; i < listaClientes.size(); i++) {
					
							DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n\nRotina de: " + listaClientes.get(i).getNome()));		
						
							status = br.com.legnu_propagar.disparo.modos.Whatsapp.iniciarWhatsapp(act, driver, listaClientes.get(i).getNome(),false,
							listaMensagens, listaClientes.get(i).getId(), DaoRotina.pegarUltimo(), modo, quem, listaClientes.get(i), null, true);
							br.com.legnu_propagar.util.Disparo.UltimoEnvio(false, listaClientes.get(i).getId(), DaoRotina.pegarUltimo());			
							
							try {
								Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(2).getConf2()));
							} catch (Exception e) {}
							
							if(status.equals(Status.ENVIADO)) {
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio com Sucesso!"));		
							}else if(status.equals(Status.FALHOU)){
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio Falhou!\n\n"));	
								listaPendentesClientes.add(listaClientes.get(i));
							}else if(status.equals(Status.NAO_ACHOU)) {
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Não Encotrado."));	
								listaPendentesClientes.add(listaClientes.get(i));
							}
				}
				
				for (int i = 0; i < listaPendentesClientes.size(); i++) {					
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n\nRotina repetida de: " + listaPendentesClientes.get(i).getNome()));		
						
							status = br.com.legnu_propagar.disparo.modos.Whatsapp.iniciarWhatsapp(act, driver, listaPendentesClientes.get(i).getNome(),false,
							listaMensagens, listaPendentesClientes.get(i).getId(), DaoRotina.pegarUltimo(), modo, quem, listaPendentesClientes.get(i), null, false);
							br.com.legnu_propagar.util.Disparo.UltimoEnvio(false, listaPendentesClientes.get(i).getId(), DaoRotina.pegarUltimo());			
							
							try {
								Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(2).getConf2()));
							} catch (Exception e) {}
							
							if(status.equals(Status.ENVIADO)) {
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio repetido com Sucesso!"));	
							}else if(status.equals(Status.FALHOU)){
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio repetido Falhou!\n\n"));	
							}else if(status.equals(Status.NAO_ACHOU)) {
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Repetido Não Encotrado."));	
							}
							
							if(!(status.equals(Status.ENVIADO))) {
								DaoOcorrencia.inserirOcorrencia(listaPendentesClientes.get(i).getId(), "Cliente", listaPendentesClientes.get(i).getNome());
							}
				}
				
			} else {								
				for (int i = 0; i < listaGrupos.size(); i++) {					
								
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n\nRotina de: " + listaGrupos.get(i).getNome()));		
						
							status = br.com.legnu_propagar.disparo.modos.Whatsapp.iniciarWhatsapp(act, driver, listaGrupos.get(i).getNome(),true,
									listaMensagens, listaGrupos.get(i).getId(), DaoRotina.pegarUltimo(), modo, quem, null, listaGrupos.get(i), true);
							br.com.legnu_propagar.util.Disparo.UltimoEnvio(true, listaGrupos.get(i).getId(), DaoRotina.pegarUltimo());			

							try {
								Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(2).getConf2()));
							} catch (Exception e) {}
							
							if(status.equals(Status.ENVIADO)) {
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio com Sucesso!"));		
							}else if(status.equals(Status.FALHOU)){
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio Falhou!\n\n"));
								listaPendentesGrupos.add(listaGrupos.get(i));
							}else if(status.equals(Status.NAO_ACHOU)) {
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Não Encotrado."));	
								listaPendentesGrupos.add(listaGrupos.get(i));
							}
					
				}
				
				for (int i = 0; i < listaPendentesGrupos.size(); i++) {
							DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n\nRotina repetida de: " + listaPendentesGrupos.get(i).getNome()));		
						
							status = br.com.legnu_propagar.disparo.modos.Whatsapp.iniciarWhatsapp(act, driver, listaGrupos.get(i).getNome(),
									true, listaMensagens, listaPendentesGrupos.get(i).getId(), DaoRotina.pegarUltimo(), modo, quem, null, listaPendentesGrupos.get(i), false);
							br.com.legnu_propagar.util.Disparo.UltimoEnvio(true, listaPendentesGrupos.get(i).getId(), DaoRotina.pegarUltimo());			

							try {
								Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(2).getConf2()));
							} catch (Exception e) {}
							
							if(status.equals(Status.ENVIADO)) {
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio repetido com Sucesso!"));	
							}else if(status.equals(Status.FALHOU)){
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio repetido Falhou!\n\n"));	
							}else if(status.equals(Status.NAO_ACHOU)) {
								DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Repetido Não Encotrado."));	
							}
							
							if(!(status.equals(Status.ENVIADO))) {
								DaoOcorrencia.inserirOcorrencia(listaPendentesGrupos.get(i).getId(), "Grupo", listaPendentesGrupos.get(i).getNome());
							}
					
				}

			}

		/*
		* MODO FACEBOOK
		* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		} else if (modo.equals(Modo.FACEBOOK)) {
			if (quem.equals(Quem.CLIENTES)) {
				
				for (int i = 0; i < listaClientes.size(); i++) {
					
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n\nRotina de: " + listaClientes.get(i).getNome()));		
				
					status = br.com.legnu_propagar.disparo.modos.Mensager.iniciarMensager(act, driver, listaClientes.get(i).getNome(),
							false, listaMensagens, listaClientes.get(i).getId(), DaoRotina.pegarUltimo(), modo, quem, listaClientes.get(i), true);
					
					br.com.legnu_propagar.util.Disparo.UltimoEnvio(false, listaClientes.get(i).getId(), DaoRotina.pegarUltimo());			
					
					try {
						Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(3).getConf2()));
					} catch (Exception e) {}
					
					if(status.equals(Status.ENVIADO)) {
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio com Sucesso!"));		
					}else if(status.equals(Status.FALHOU)){
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio Falhou!\n\n"));	
						listaPendentesClientes.add(listaClientes.get(i));
					}else if(status.equals(Status.NAO_ACHOU)) {
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Não Encotrado."));	
						listaPendentesClientes.add(listaClientes.get(i));
					}
				}
				
				for (int i = 0; i < listaPendentesClientes.size(); i++) {					
						
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n\nRotina repetida de: " + listaPendentesClientes.get(i).getNome()));		
					
						status = br.com.legnu_propagar.disparo.modos.Mensager.iniciarMensager(act, driver, listaPendentesClientes.get(i).getNome(),
								false, listaMensagens, listaPendentesClientes.get(i).getId(), DaoRotina.pegarUltimo(), modo, quem, listaPendentesClientes.get(i), false);
						br.com.legnu_propagar.util.Disparo.UltimoEnvio(false, listaPendentesClientes.get(i).getId(), DaoRotina.pegarUltimo());			
						
						try {
							Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(3).getConf2()));
						} catch (Exception e) {}
						
						if(status.equals(Status.ENVIADO)) {
							DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio repetido com Sucesso!"));	
						}else if(status.equals(Status.FALHOU)){
							DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio repetido Falhou!\n\n"));	
						}else if(status.equals(Status.NAO_ACHOU)) {
							DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Repetido Não Encotrado."));	
						}
						
						if(!(status.equals(Status.ENVIADO))) {
							DaoOcorrencia.inserirOcorrencia(listaPendentesClientes.get(i).getId(), "Cliente", listaPendentesClientes.get(i).getNome());
						}
				}
				
				
			} else {
				
				for (int i = 0; i < listaGrupos.size(); i++) {
					
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n\nRotina de: " + listaGrupos.get(i).getNome()));		
				
					status = br.com.legnu_propagar.disparo.modos.Facebook.iniciarFacebook(act, driver, listaGrupos.get(i).getNome(),
							true, listaMensagens, listaGrupos.get(i).getId(), DaoRotina.pegarUltimo(), modo, quem, listaGrupos.get(i), true);
					
					br.com.legnu_propagar.util.Disparo.UltimoEnvio(false, listaGrupos.get(i).getId(), DaoRotina.pegarUltimo());	
					
					if(br.com.legnu_propagar.disparo.modos.Facebook.iniciarFacebook(act, driver, listaGrupos.get(i).getNome(),
							true, listaMensagens, listaGrupos.get(i).getId(), DaoRotina.pegarUltimo(), modo, quem, listaGrupos.get(i), true).equals(Status.ENVIADO)) {
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio com Sucesso!"));		
					}else if(status.equals(Status.FALHOU)){
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio Falhou!\n\n"));	
						listaPendentesGrupos.add(listaGrupos.get(i));
					}else if(status.equals(Status.NAO_ACHOU)) {
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Não Encotrado."));	
						listaPendentesGrupos.add(listaGrupos.get(i));
					}
					
					driver.get("https://www.facebook.com/groups/");
					
					try {
						Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(3).getConf2()));
					} catch (Exception e) {}

				}
				
				for (int i = 0; i < listaPendentesGrupos.size(); i++) {					
					
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n\nRotina repetida de: " + listaPendentesGrupos.get(i).getNome()));		
				
					status = br.com.legnu_propagar.disparo.modos.Facebook.iniciarFacebook(act, driver, listaPendentesGrupos.get(i).getNome(),
							false, listaMensagens, listaPendentesGrupos.get(i).getId(), DaoRotina.pegarUltimo(), modo, quem, listaPendentesGrupos.get(i), false);
					
					br.com.legnu_propagar.util.Disparo.UltimoEnvio(false, listaPendentesGrupos.get(i).getId(), DaoRotina.pegarUltimo());			
										
					if(status.equals(Status.ENVIADO)) {
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio repetido com Sucesso!"));	
					}else if(status.equals(Status.FALHOU)){
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio repetido Falhou!\n\n"));	
					}else if(status.equals(Status.NAO_ACHOU)) {
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Repetido Não Encotrado."));	
					}
					
					if(!(status.equals(Status.ENVIADO))) {
						DaoOcorrencia.inserirOcorrencia(listaPendentesGrupos.get(i).getId(), "Grupo", listaPendentesGrupos.get(i).getNome());
					}
					
					driver.get("https://www.facebook.com/groups/");
					
					try {
						Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(3).getConf2()));
					} catch (Exception e) {}
				}
				
			}
		
		/*
		 * MODO INSTAGRAM
		 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		} else if (modo.equals(Modo.INSTAGRAM)) {
			
				for (int i = 0; i < listaClientes.size(); i++) {
										
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n\nRotina de: " + listaClientes.get(i).getNome()));		
					
					status = br.com.legnu_propagar.disparo.modos.Instagram.iniciarDirect(act, driver, listaClientes.get(i).getNome(), 
							false,listaMensagens, listaClientes.get(i).getId(),	DaoRotina.pegarUltimo(), modo, quem, listaClientes.get(i), true);
					br.com.legnu_propagar.util.Disparo.UltimoEnvio(false, listaClientes.get(i).getId(), DaoRotina.pegarUltimo());			
					
					try {
						Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(4).getConf2()));
					} catch (Exception e) {}
					
					if(status.equals(Status.ENVIADO)) {
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio com Sucesso!"));		
					}else if(status.equals(Status.FALHOU)){
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio Falhou!\n\n"));	
						listaPendentesClientes.add(listaClientes.get(i));
					}else if(status.equals(Status.NAO_ACHOU)) {
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Não Encotrado."));	
						listaPendentesClientes.add(listaClientes.get(i));
					}
					
				}	
				
				for (int i = 0; i < listaPendentesClientes.size(); i++) {
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(),(DaoRotina.pegarUltimo().getRotina() + "\n\nRotina repetida de: " + listaPendentesClientes.get(i).getNome()));
										
					status = br.com.legnu_propagar.disparo.modos.Instagram.iniciarDirect(act, driver, listaPendentesClientes.get(i).getNome(),
							false, listaMensagens, listaPendentesClientes.get(i).getId(),DaoRotina.pegarUltimo(), modo, quem, listaPendentesClientes.get(i), false);
					
					br.com.legnu_propagar.util.Disparo.UltimoEnvio(false, listaPendentesClientes.get(i).getId(), DaoRotina.pegarUltimo());			
					
					try {
						Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(4).getConf2()));
					} catch (Exception e) {}
					
					if(status.equals(Status.ENVIADO)) {
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio repetido com Sucesso!"));		
					}else if(status.equals(Status.FALHOU)){
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio repetido Falhou!\n\n"));	
					}else if(status.equals(Status.NAO_ACHOU)) {
						DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Repetido Não Encotrado."));	
					}
					
					if(!(status.equals(Status.ENVIADO))) {
						DaoOcorrencia.inserirOcorrencia(listaPendentesClientes.get(i).getId(), "Cliente", listaPendentesClientes.get(i).getNome());
					}
				}
		/*
		 * MODO EMAIL
		 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
		} else if (modo.equals(Modo.EMAIL)) {
			
			for (int i = 0; i < listaClientes.size(); i++) {
									
				DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n\nRotina de: " + listaClientes.get(i).getNome()));		
				
				status = br.com.legnu_propagar.disparo.modos.Gmail.iniciarGmail(act, driver, listaClientes.get(i).getNome(), 
						false,listaMensagens, listaClientes.get(i).getId(),	DaoRotina.pegarUltimo(), modo, quem, listaClientes.get(i), true);
				br.com.legnu_propagar.util.Disparo.UltimoEnvio(false, listaClientes.get(i).getId(), DaoRotina.pegarUltimo());			
				
				try {
					Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(5).getConf2()));
				} catch (Exception e) {}
				
				if(status.equals(Status.ENVIADO)) {
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio com Sucesso!"));		
				}else if(status.equals(Status.FALHOU)){
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio Falhou!\n\n"));	
					listaPendentesClientes.add(listaClientes.get(i));
				}else if(status.equals(Status.NAO_ACHOU)) {
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Não Encotrado."));	
					listaPendentesClientes.add(listaClientes.get(i));
				}
				
			}	
			
			for (int i = 0; i < listaPendentesClientes.size(); i++) {
				DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(),(DaoRotina.pegarUltimo().getRotina() + "\n\nRotina repetida de: " + listaPendentesClientes.get(i).getNome()));
									
				status = br.com.legnu_propagar.disparo.modos.Gmail.iniciarGmail(act, driver, listaPendentesClientes.get(i).getNome(),
						false, listaMensagens, listaPendentesClientes.get(i).getId(),DaoRotina.pegarUltimo(), modo, quem, listaPendentesClientes.get(i), false);
				
				br.com.legnu_propagar.util.Disparo.UltimoEnvio(false, listaPendentesClientes.get(i).getId(), DaoRotina.pegarUltimo());			
				
				try {
					Thread.sleep(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(5).getConf2()));
				} catch (Exception e) {}
				
				if(status.equals(Status.ENVIADO)) {
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n E@§ Envio repetido com Sucesso!"));		
				}else if(status.equals(Status.FALHOU)){
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n F@§ Envio repetido Falhou!\n\n"));	
				}else if(status.equals(Status.NAO_ACHOU)) {
					DaoRotina.editarRotina(DaoRotina.pegarUltimo().getId(), (DaoRotina.pegarUltimo().getRotina()+"\n N@§ Repetido Não Encotrado."));	
				}
				
				if(!(status.equals(Status.ENVIADO))) {
					DaoOcorrencia.inserirOcorrencia(listaPendentesClientes.get(i).getId(), "Cliente", listaPendentesClientes.get(i).getNome());
				}
			}
		}	
		Alerta.alertaDisparoFinalizado();
	}

	public static enum Status {
		ENVIADO, NAO_ACHOU, FALHOU
	}
	
	public static enum Modo {
		WHATSAPP, FACEBOOK, INSTAGRAM, EMAIL
	}

	public static enum Quem {
		CLIENTES, GRUPOS
	}
}
