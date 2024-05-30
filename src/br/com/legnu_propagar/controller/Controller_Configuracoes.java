package br.com.legnu_propagar.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.legnu_propagar.dao.DaoConfiguracao;
import br.com.legnu_propagar.main.Main;
import br.com.legnu_propagar.main.Main.Tela;
import br.com.legnu_propagar.util.Alerta;
import br.com.legnu_propagar.util.Alerta.acao;
import br.com.legnu_propagar.util.Alerta.genero;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class Controller_Configuracoes implements Initializable {
	/***
	 * Variaveis
	 *******************************************************************************************************************************************************************************************************************/
	private static double xOffset = 0;
	private static double yOffset = 0;

	@FXML
	public ImageView imgDefault;
	@FXML
	public ImageView imgAplicar;

	@FXML
	public TextField txtGeckoDriver;
	@FXML
	public TextField txtFirefox;

	@FXML
	private TextField txtConf1_W;
	@FXML
	private TextField txtConf2_W;
	@FXML
	private TextField txtConf3_W;
	@FXML
	private TextField txtConf4_W;
	@FXML
	private TextField txtConf5_W;
	@FXML
	private TextField txtConf6_W;
	@FXML
	private TextField txtConf7_W;
	@FXML
	private TextField txtConf8_W;
	@FXML
	private TextField txtConf9_W;
	@FXML
	private TextField txtConf10_W;
	@FXML
	private TextField txtConf11_W;
	@FXML
	private TextField txtConf12_W;
	@FXML
	private TextField txtConf13_W;
	@FXML
	private TextField txtConf14_W;
	@FXML
	private TextField txtConf15_W;
	@FXML
	private TextField txtConf16_W;
	@FXML
	private TextField txtConf17_W;
	@FXML
	private TextField txtConf18_W;
	@FXML
	private TextField txtConf19_W;
	@FXML
	private TextField txtConf20_W;
	@FXML
	private TextField txtConf21_W;
	@FXML
	private TextField txtConf22_W;
	@FXML
	private TextField txtConf23_W;
	@FXML
	private TextField txtConf24_W;
	@FXML
	private TextField txtConf25_W;
	@FXML
	private TextField txtConf26_W;
	@FXML
	private TextField txtConf27_W;
	@FXML
	private TextField txtConf28_W;
	@FXML
	private TextField txtConf29_W;
	@FXML
	private TextField txtConf30_W;
	
	@FXML
	private TextField txtConf1_F;
	@FXML
	private TextField txtConf2_F;
	@FXML
	private TextField txtConf3_F;
	@FXML
	private TextField txtConf4_F;
	@FXML
	private TextField txtConf5_F;
	@FXML
	private TextField txtConf6_F;
	@FXML
	private TextField txtConf7_F;
	@FXML
	private TextField txtConf8_F;
	@FXML
	private TextField txtConf9_F;
	@FXML
	private TextField txtConf10_F;
	@FXML
	private TextField txtConf11_F;
	@FXML
	private TextField txtConf12_F;
	@FXML
	private TextField txtConf13_F;
	@FXML
	private TextField txtConf14_F;
	@FXML
	private TextField txtConf15_F;
	@FXML
	private TextField txtConf16_F;
	@FXML
	private TextField txtConf17_F;
	@FXML
	private TextField txtConf18_F;
	@FXML
	private TextField txtConf19_F;
	@FXML
	private TextField txtConf20_F;
	@FXML
	private TextField txtConf21_F;
	@FXML
	private TextField txtConf22_F;
	@FXML
	private TextField txtConf23_F;
	@FXML
	private TextField txtConf24_F;
	@FXML
	private TextField txtConf25_F;
	@FXML
	private TextField txtConf26_F;
	@FXML
	private TextField txtConf27_F;
	@FXML
	private TextField txtConf28_F;
	@FXML
	private TextField txtConf29_F;
	@FXML
	private TextField txtConf30_F;

	/***
	 * Inicialização
	 *******************************************************************************************************************************************************************************************************************/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initFields();
	}

	/***
	 * Ações
	 *******************************************************************************************************************************************************************************************************************/

	public void initFields() {
		txtGeckoDriver.setText(DaoConfiguracao.pegarConfiguracao(1).getExecutavelGecko());
		txtFirefox.setText(DaoConfiguracao.pegarConfiguracao(1).getExecutavelFirefox());

		txtConf1_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf1());
		txtConf2_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf2());
		txtConf3_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf3());
		txtConf4_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf4());
		txtConf5_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf5());
		txtConf6_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf6());
		txtConf7_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf7());
		txtConf8_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf8());
		txtConf9_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf9());
		txtConf10_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf10());
		txtConf11_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf11());
		txtConf12_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf12());
		txtConf13_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf13());
		txtConf14_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf14());
		txtConf15_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf15());
		txtConf16_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf16());
		txtConf17_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf17());
		txtConf18_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf18());
		txtConf19_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf19());
		txtConf20_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf20());
		txtConf21_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf21());
		txtConf22_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf22());
		txtConf23_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf23());
		txtConf24_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf24());
		txtConf25_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf25());
		txtConf26_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf26());
		txtConf27_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf27());
		txtConf28_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf28());
		txtConf29_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf29());
		txtConf30_W.setText(DaoConfiguracao.pegarConfiguracao(2).getConf30());
		
		txtConf1_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf1());
		txtConf2_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf2());
		txtConf3_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf3());
		txtConf4_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf4());
		txtConf5_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf5());
		txtConf6_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf6());
		txtConf7_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf7());
		txtConf8_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf8());
		txtConf9_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf9());
		txtConf10_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf10());
		txtConf11_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf11());
		txtConf12_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf12());
		txtConf13_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf13());
		txtConf14_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf14());
		txtConf15_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf15());
		txtConf16_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf16());
		txtConf17_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf17());
		txtConf18_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf18());
		txtConf19_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf19());
		txtConf20_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf20());
		txtConf21_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf21());
		txtConf22_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf22());
		txtConf23_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf23());
		txtConf24_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf24());
		txtConf25_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf25());
		txtConf26_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf26());
		txtConf27_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf27());
		txtConf28_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf28());
		txtConf29_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf29());
		txtConf30_F.setText(DaoConfiguracao.pegarConfiguracao(3).getConf30());
	}

	public void giroParaDireita(ImageView img) {
		RotateTransition rotate = new RotateTransition();

		rotate.setNode(img);
		rotate.setDuration(Duration.millis(500));
		rotate.setCycleCount(1);
		rotate.setByAngle(-360);
		rotate.play();

		ScaleTransition scale = new ScaleTransition();

		scale.setNode(img);
		scale.setDuration(Duration.millis(400));
		scale.setCycleCount(2);
		scale.setByX(0.2);
		scale.setByY(0.2);
		scale.setAutoReverse(true);
		scale.play();
	}

	public void aumento(ImageView img) {
		ScaleTransition scale = new ScaleTransition();

		scale.setNode(img);
		scale.setDuration(Duration.millis(300));
		scale.setCycleCount(2);
		scale.setByX(0.2);
		scale.setByY(0.2);
		scale.setAutoReverse(true);
		scale.play();
	}

	/***
	 * Eventos
	 *******************************************************************************************************************************************************************************************************************/

	@FXML
	public void voltar(MouseEvent event) {
		Main.setScreen(Tela.PRINCIPAL);
	}

	@FXML
	public void Default(MouseEvent event) {
		giroParaDireita(imgDefault);

		txtConf1_W.setText("10");
		txtConf2_W.setText("10");
		txtConf3_W.setText("");
		txtConf4_W.setText("5");
		txtConf5_W.setText("");
		txtConf6_W.setText("5");
		txtConf7_W.setText("");
		txtConf8_W.setText("5");
		txtConf9_W.setText("");
		txtConf10_W.setText("");
		txtConf11_W.setText("");
		txtConf12_W.setText("");
		txtConf13_W.setText("150");
		txtConf14_W.setText("10");
		txtConf15_W.setText("");
		txtConf16_W.setText("5");
		txtConf17_W.setText("10");
		txtConf18_W.setText("");
		txtConf19_W.setText("");
		txtConf20_W.setText("");
		txtConf21_W.setText("");
		txtConf22_W.setText("");
		txtConf23_W.setText("");
		txtConf24_W.setText("");
		txtConf25_W.setText("");
		txtConf26_W.setText("");
		txtConf27_W.setText("");
		txtConf28_W.setText("");
		txtConf29_W.setText("");
		txtConf30_W.setText("");
		
		 /*txtPriSleepInicio.setText("300000");
         txtPriSleepPesquisa.setText("3000");
         txtPriPesquisa.setText("input[aria-label='Pesquisar grupos']");
         txtPriSleepCatch.setText("3000");
         txtPriMidiaClick.setText("div[role='button']");
         txtPriSleepMensagens.setText("3000");
         txtPriMensagem.setText("");
         txtPriSleepSend.setText("5000");
         txtPriSend.setText("div[aria-label='Publicar']");
         txtPriSleepFinish.setText("3000");
         txtVar1.setText("div[aria-label='Mensagem']");
         txtVar2.setText("input[aria-label='Pesquisar no Messenger']");
         txtVar3.setText("input[type='file']");
         txtVar4.setText("input[accept=\"image/*,image/heif,image/heic,video/*,video/mp4,video/x-m4v,video/x-matroska,.mkv\"]");
         txtVar5.setText("7000");
         txtVar6.setText("document.querySelector(\"p.xat24cr > span:nth-child(1)\").firstChild.data = '");
         txtVar7.setText("9");
         txtVar8.setText("10000");
         txtVar9.setText("a[role='presentation']");
         txtVar10.setText("div[role='presentation']");*/
		
		txtConf1_F.setText("5");
		txtConf2_F.setText("10");
		txtConf3_F.setText("");
		txtConf4_F.setText("5");
		txtConf5_F.setText("");
		txtConf6_F.setText("5");
		txtConf7_F.setText("");
		txtConf8_F.setText("5");
		txtConf9_F.setText("");
		txtConf10_F.setText("");
		txtConf11_F.setText("");
		txtConf12_F.setText("");
		txtConf13_F.setText("");
		txtConf14_F.setText("10");
		txtConf15_F.setText("");
		txtConf16_F.setText("");
		txtConf17_F.setText("20");
		txtConf18_F.setText("");
		txtConf19_F.setText("");
		txtConf20_F.setText("");
		txtConf21_F.setText("");
		txtConf22_F.setText("");
		txtConf23_F.setText("");
		txtConf24_F.setText("");
		txtConf25_F.setText("");
		txtConf26_F.setText("");
		txtConf27_F.setText("");
		txtConf28_F.setText("");
		txtConf29_F.setText("");
		txtConf30_F.setText("");
	}

	public void Aplicar(MouseEvent event) {
		aumento(imgAplicar);

		DaoConfiguracao.editarConfiguracao("", "", txtConf1_W.getText(), txtConf2_W.getText(), txtConf3_W.getText(),
				txtConf4_W.getText(), txtConf5_W.getText(), txtConf6_W.getText(), txtConf7_W.getText(),
				txtConf8_W.getText(), txtConf9_W.getText(), txtConf10_W.getText(), txtConf11_W.getText(),
				txtConf12_W.getText(), txtConf13_W.getText(), txtConf14_W.getText(), txtConf15_W.getText(),
				txtConf16_W.getText(), txtConf17_W.getText(), txtConf18_W.getText(), txtConf19_W.getText(),
				txtConf20_W.getText(), txtConf21_W.getText(), txtConf22_W.getText(), txtConf23_W.getText(),
				txtConf24_W.getText(), txtConf25_W.getText(), txtConf26_W.getText(), txtConf27_W.getText(),
				txtConf28_W.getText(), txtConf29_W.getText(), txtConf30_W.getText(), 2);
		
		DaoConfiguracao.editarConfiguracao("", "", txtConf1_F.getText(), txtConf2_F.getText(), txtConf3_F.getText(),
				txtConf4_F.getText(), txtConf5_F.getText(), txtConf6_F.getText(), txtConf7_F.getText(),
				txtConf8_F.getText(), txtConf9_F.getText(), txtConf10_F.getText(), txtConf11_F.getText(),
				txtConf12_F.getText(), txtConf13_F.getText(), txtConf14_F.getText(), txtConf15_F.getText(),
				txtConf16_F.getText(), txtConf17_F.getText(), txtConf18_F.getText(), txtConf19_F.getText(),
				txtConf20_F.getText(), txtConf21_F.getText(), txtConf22_F.getText(), txtConf23_F.getText(),
				txtConf24_F.getText(), txtConf25_F.getText(), txtConf26_F.getText(), txtConf27_F.getText(),
				txtConf28_F.getText(), txtConf29_F.getText(), txtConf30_F.getText(), 3);

		Alerta.alertaSucesso("Configuração", genero.A, acao.EDITAR);
	}

	@FXML
	public void PegarExecutavel(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Executavel");
		File file = fileChooser.showOpenDialog(Main.getStage().getScene().getWindow());

		if (file != null) {
			txtFirefox.setText(file.getPath());
		} else {
			System.out.println("error");
		}

		DaoConfiguracao.editarConfiguracao(txtFirefox.getText(), txtGeckoDriver.getText(), null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, 1);
	}

	@FXML
	public void PegarDriver(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Driver");
		File file = fileChooser.showOpenDialog(Main.getStage().getScene().getWindow());

		if (file != null) {
			txtGeckoDriver.setText(file.getPath());
		} else {
			System.out.println("error");
		}

		DaoConfiguracao.editarConfiguracao(txtFirefox.getText(), txtGeckoDriver.getText(), null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, 1);

	}

}
