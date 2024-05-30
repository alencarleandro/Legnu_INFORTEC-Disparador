package br.com.legnu_propagar.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import br.com.legnu_propagar.dao.DaoVale;
import br.com.legnu_propagar.main.Main;
import br.com.legnu_propagar.main.Main.Tela;
import br.com.legnu_propagar.model.Categoria;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Email;
import br.com.legnu_propagar.model.Telefone;
import br.com.legnu_propagar.model.Endereco;

import java.awt.Desktop;
import java.net.URI;

import javax.swing.JOptionPane;

public class Controller_Login {

	@FXML
	private TextField txtSenhaMes;

	@FXML
	private static ImageView btnCloseApp;

	@FXML
	private static Button btnEntrar;

	@FXML
	private ImageView btnMaximizar;

	@FXML
	private static Hyperlink linkSenha;

	@FXML
	private static AnchorPane anchor;

	private static double xOffset = 0;
	private static double yOffset = 0;

	public void fecharApp(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	public void verificar(ActionEvent event) {
		if (txtSenhaMes != null) {
			if (txtSenhaMes.getText().equals(DaoVale.PegarVale())) {
				Main.setScreen(Tela.PRINCIPAL);
				return;
			}
		}
		JOptionPane.showMessageDialog(null,
				"Senha incorreta, tente novamente ou entre em contato com o nosso suporte (+55 31 97357-3354)");

	}

	@FXML
	public void abrirLink(ActionEvent event) {
		try {
			URI link = new URI(
					"https://wa.me/5531982352599?text=Estou+com+dificuldade+de+acesso+ao+meu+Programa+%22LEGNU%22%2C+preciso+de+ajuda+do+suporte.");
			Desktop.getDesktop().browse(link);
		} catch (Exception erro) {
			System.out.println(erro);
		}
	}

	@FXML
	public void salvarEstado(MouseEvent event) {
		xOffset = event.getSceneX();
		yOffset = event.getSceneY();
	}

	@FXML
	public void mover(MouseEvent event) {
		Main.getStage().setX(event.getScreenX() - xOffset);
		Main.getStage().setY(event.getScreenY() - yOffset);
	}

	@FXML
	public void tamanho(MouseEvent event) {
		if (Main.getStage().isMaximized()) {
			Main.getStage().setMaximized(false);
			Main.getStage().centerOnScreen();
			btnMaximizar
					.setImage(new Image(getClass().getResource("/br/com/legnu_propagar/util/imagens/maximizar (1).png").toExternalForm()));

		} else {
			Main.getStage().setMaximized(true);
			Main.getStage().centerOnScreen();
			btnMaximizar.setImage(new Image(getClass().getResource("/br/com/legnu_propagar/util/imagens/minimizar.png").toExternalForm()));
		}

	}

	@FXML
	public void minimizar(MouseEvent event) {
		Main.getStage().setIconified(true);
	}
}
