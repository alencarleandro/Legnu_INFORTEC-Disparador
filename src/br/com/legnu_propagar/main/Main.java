package br.com.legnu_propagar.main;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.controller.Controller_Introduxcao;
import br.com.legnu_propagar.dao.DaoCategoria;
import br.com.legnu_propagar.dao.DaoClientes;
import br.com.legnu_propagar.dao.DaoConfiguracao;
import br.com.legnu_propagar.dao.DaoGrupos;
import br.com.legnu_propagar.dao.DaoMensagens;
import br.com.legnu_propagar.dao.DaoOcorrencia;
import br.com.legnu_propagar.dao.DaoProfiles;
import br.com.legnu_propagar.dao.DaoRotina;
import br.com.legnu_propagar.dao.DaoTelefone;
import br.com.legnu_propagar.dao.DaoVale;
import br.com.legnu_propagar.util.busca.PegarDatasUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	private static Stage stage;
	private static double xOffset = 0;
	private static double yOffset = 0;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Main.stage = stage;
	}

	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;

		DaoCategoria.criarSchemaInterno();
		DaoClientes.criarSchemaInterno();
		DaoTelefone.criarSchemaInterno();
		DaoVale.criarSchemaExterno();
		DaoProfiles.criarSchemaInterno();
		DaoGrupos.criarSchemaInterno();
		DaoMensagens.criarSchemaInterno();
		DaoConfiguracao.criarSchemaInterno();
		DaoRotina.criarSchemaInterno();
		DaoOcorrencia.criarSchemaInterno();

		// primaryStage.initStyle(StageStyle.TRANSPARENT);
		setScreen(Tela.LOGIN);
		primaryStage.getIcons()
				.add(new Image(getClass().getResource("/br/com/legnu_propagar/util/imagens/LegnuIcon.png").toExternalForm()));
		primaryStage.show();																				
	}

	public static enum Tela {
		INTRO, LOGIN, PRINCIPAL, CADASTRO_CLIENTE, CADASTRO_MENSAGEM, CADASTRO_PERFIL, CADASTRO_GRUPO, DISPARO,
		CONFIGURAÇOES,ROTINA;
	}

	public static void setScreen(Tela src) {
		try {
			Parent fxml = null;

			switch (src) {
			case INTRO:
				fxml = FXMLLoader.load(Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaIntroduxcao.fxml"));
				break;
			case LOGIN:
				fxml = FXMLLoader.load(Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaLogin.fxml"));
				stage.setTitle("Legnu's Propagar - Tela Login");
				break;
			case PRINCIPAL:
				fxml = FXMLLoader.load(Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaPrincipal.fxml"));
				stage.setTitle("Legnu's Propagar - Tela Principal");
				break;
			case DISPARO:
				fxml = FXMLLoader.load(Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaDisparo.fxml"));
				stage.setTitle("Legnu's Propagar - Tela de Disparo");
				break;
			case CADASTRO_GRUPO:
				fxml = FXMLLoader.load(Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaCadastroGrupo.fxml"));
				stage.setTitle("Legnu's Propagar - Tela de Grupos");
				break;
			case CADASTRO_PERFIL:
				fxml = FXMLLoader.load(Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaCadastroPerfil.fxml"));	
				stage.setTitle("Legnu's Propagar - Tela de Perfis");
				break;
			case CADASTRO_MENSAGEM:
				fxml = FXMLLoader.load(Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaCadastroMensagem.fxml"));
				stage.setTitle("Legnu's Propagar - Tela de Mensagens");
				break;
			case CADASTRO_CLIENTE:
				fxml = FXMLLoader.load(Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaCadastroCliente.fxml"));
				stage.setTitle("Legnu's Propagar - Tela de Clientes");
				break;
			case CONFIGURAÇOES:
				fxml = FXMLLoader.load(Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaConfiguracoes.fxml"));
				stage.setTitle("Legnu's Propagar - Tela de Configurações");
				break;
			case ROTINA:
				fxml = FXMLLoader.load(Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaRotina.fxml"));
				stage.setTitle("Legnu's Propagar - Tela da Rotina");
				break;
			}

			if (stage.isMaximized()) {
				stage.setScene(new Scene(fxml));
				stage.setMaximized(false);
				stage.setMaximized(true);
			} else {
				stage.setScene(new Scene(fxml));
			}

			if (src == Tela.INTRO) {
				stage.getScene().setFill(Color.TRANSPARENT);
			}

		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Erro ao trocar de tela: " + e);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
