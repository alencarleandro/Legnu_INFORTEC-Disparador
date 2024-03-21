package br.com.legnu_propagar.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;

import br.com.legnu_propagar.main.Main;
import br.com.legnu_propagar.main.Main.Tela;
import javafx.animation.FadeTransition;
import javafx.application.Preloader;
import javafx.scene.Parent;

public class Controller_Introduxcao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);

			Main.setScreen(Tela.LOGIN);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
