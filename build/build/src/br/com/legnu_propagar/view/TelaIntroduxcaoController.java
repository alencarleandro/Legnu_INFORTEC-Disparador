package br.com.legnu_propagar.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import br.com.legnu_propagar.view.TelaIntroduxcaoController;

//br.com.legnu_propagar.view.TelaIntroduxcaoController

public class TelaIntroduxcaoController extends Application {

	@FXML
	private ProgressBar pbIntroducao;

	@FXML
	private AnchorPane pane;

	@FXML
	void makeProgress() {
		try {
		while(pbIntroducao.getProgress() < 1) {
			Thread.sleep(20);
		pbIntroducao.setProgress(pbIntroducao.getProgress() + 0.01);
		pbIntroducao.applyCss();
		pbIntroducao.requestFocus();
		pbIntroducao.requestLayout();
		System.out.println(pbIntroducao.getProgress());
		}
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@FXML
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(TelaIntroduxcaoController.class.getResource("TelaIntroduxcao.fxml"));
			Scene scene = new Scene(fxmlLoader.load());

			primaryStage.initStyle(StageStyle.TRANSPARENT);
			scene.setFill(Color.TRANSPARENT);

			primaryStage.setScene(scene);

			primaryStage.show();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public static void main(String[] args) throws InterruptedException {
		launch(args);				
	}

}
