package br.com.legnu_propagar.controller;

import br.com.legnu_propagar.main.Main;
import br.com.legnu_propagar.main.Main.Tela;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Controller_Principal {

	@FXML
	public void btn_1x1(MouseEvent event) {
		Main.setScreen(Tela.DISPARO);
	}

	@FXML
	public void btn_1x2(MouseEvent event) {
		Main.setScreen(Tela.ROTINA);
	}

	@FXML
	public void btn_1x3(MouseEvent event) {
		Main.setScreen(Tela.CADASTRO_CLIENTE);
	}

	@FXML
	public void btn_2x1(MouseEvent event) {
		Main.setScreen(Tela.CADASTRO_GRUPO);
	}

	@FXML
	public void btn_2x2(MouseEvent event) {
		Main.setScreen(Tela.CADASTRO_PERFIL);
	}

	@FXML
	public void btn_2x3(MouseEvent event) {
		Main.setScreen(Tela.CADASTRO_MENSAGEM);
	}
}
