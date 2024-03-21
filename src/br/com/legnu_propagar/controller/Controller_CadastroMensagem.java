package br.com.legnu_propagar.controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.dao.DaoMensagens;
import br.com.legnu_propagar.main.Main;
import br.com.legnu_propagar.main.Main.Tela;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.util.Alerta;
import br.com.legnu_propagar.util.Alerta.acao;
import br.com.legnu_propagar.util.Alerta.genero;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class Controller_CadastroMensagem implements Initializable {

	/***
	 * Variaveis
	 *******************************************************************************************************************************************************************************************************************/
	private static double xOffset = 0;
	private static double yOffset = 0;

	@FXML
	private Button btnAdicionar;
	@FXML
	private Button btnEditar;
	@FXML
	private Button btnRemover;
	@FXML
	private Button btnAtualizar;

	@FXML
	private TextField txtPesquisar;
	@FXML
	private TextField txtTitulo;
	@FXML
	private TextField txtMidia;
	@FXML
	private TextField txtArquivo;

	@FXML
	private TextArea taConteudo;
	@FXML
	private TextArea taConteudoArquivo;

	@FXML
	private ImageView imgMidia;

	@FXML
	private TableView<Mensagens> tbMensagens;
	@FXML
	private TableColumn<Mensagens, Integer> tbMensagens_ID;
	@FXML
	private TableColumn<Mensagens, String> tbMensagens_TITULO;

	@FXML
	private TextField txtTituloEnquete;
	@FXML
	private TextField txtPrimeiraOpcaoEnquete;
	@FXML
	private TextField txtSegundaOpcaoEnquete;
	@FXML
	private TextField txtTerceiraOpcaoEnquete;
	@FXML
	private TextField txtQuartaOpcaoEnquete;
	@FXML
	private TextField txtQuintaOpcaoEnquete;
	@FXML
	private TextField txtSextaOpcaoEnquete;
	@FXML
	private TextField txtSetimaOpcaoEnquete;
	@FXML
	private TextField txtOitavaOpcaoEnquete;
	@FXML
	private TextField txtNonaOpcaoEnquete;
	@FXML
	private TextField txtDecimaOpcaoEnquete;

	@FXML
	private RadioButton rbAtivo;
	@FXML
	private RadioButton rbInativo;

	@FXML
	private Label lblID;

	final ToggleGroup ativo = new ToggleGroup();

	/***
	 * Inicialização
	 *******************************************************************************************************************************************************************************************************************/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTable(DaoMensagens.gerarTabela());
		Limpar();


		rbAtivo.setToggleGroup(ativo);
		rbInativo.setToggleGroup(ativo);
	}

	/***
	 * Ações
	 *******************************************************************************************************************************************************************************************************************/

	public void initTable(List<Mensagens> lista) {
		tbMensagens_ID.setCellValueFactory(new PropertyValueFactory<Mensagens, Integer>("id"));
		tbMensagens_TITULO.setCellValueFactory(new PropertyValueFactory<Mensagens, String>("titulo"));
		tbMensagens.setItems(FXCollections.observableArrayList(lista));
	}

	private void Limpar() {
		txtArquivo.setText("");
		txtMidia.setText("");
		txtTitulo.setText("");
		taConteudo.setText("");
		taConteudoArquivo.setText("");

		txtTituloEnquete.setText("");
		txtPrimeiraOpcaoEnquete.setText("");
		txtSegundaOpcaoEnquete.setText("");
		txtTerceiraOpcaoEnquete.setText("");
		txtQuartaOpcaoEnquete.setText("");
		txtQuintaOpcaoEnquete.setText("");
		txtSextaOpcaoEnquete.setText("");
		txtSetimaOpcaoEnquete.setText("");
		txtOitavaOpcaoEnquete.setText("");
		txtNonaOpcaoEnquete.setText("");
		txtDecimaOpcaoEnquete.setText("");

		lblID.setText("");
		imgMidia.setImage(new Image(
				getClass().getResource("/br/com/legnu_propagar/util/imagens/Logo - Legnu 's INFORTEC - ORIGINAL.png").toExternalForm()));

		btnAdicionar.setDisable(false);
		btnEditar.setDisable(true);
		btnRemover.setDisable(true);

		initTable(DaoMensagens.gerarTabela());
	}

	private void midia() {
		File file = new File(txtMidia.getText());

		try {
			imgMidia.setImage(new Image(file.toURI().toURL().toExternalForm()));
		} catch (Exception e) {
		}
	}

	/***
	 * Eventos
	 *******************************************************************************************************************************************************************************************************************/

	@FXML
	public void PegarMidia(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Midia");
		File file = fileChooser.showOpenDialog(Main.getStage().getScene().getWindow());

		if (file != null) {
			// pickUpPathField it's your TextField fx:id
			txtMidia.setText(file.getPath());
			midia();

		} else {
			System.out.println("error"); // or something else
		}
	}

	@FXML
	public void PegarArquivo(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Arquivo");
		File file = fileChooser.showOpenDialog(Main.getStage().getScene().getWindow());

		if (file != null) {
			// pickUpPathField it's your TextField fx:id
			txtArquivo.setText(file.getPath());

		} else {
			System.out.println("error"); // or something else
		}
	}

	@FXML
	public void Pesquisar(KeyEvent event) {
		initTable(DaoMensagens.gerarTabelaPorTitulo(txtPesquisar.getText()));
	}

	@FXML
	public void adicionar(ActionEvent event) {
		try {
			if (!txtTitulo.getText().isBlank() || !txtTitulo.getText().isEmpty()) {

				boolean ativo;

				if (rbAtivo.isSelected()) {
					ativo = true;
				} else {
					ativo = false;
				}

				DaoMensagens.inserirMensagen(txtTitulo.getText(), txtMidia.getText(), txtArquivo.getText(),
						taConteudo.getText(), taConteudoArquivo.getText(), txtTituloEnquete.getText(),
						txtPrimeiraOpcaoEnquete.getText(), txtSegundaOpcaoEnquete.getText(),
						txtTerceiraOpcaoEnquete.getText(), txtQuartaOpcaoEnquete.getText(),
						txtQuintaOpcaoEnquete.getText(), txtSextaOpcaoEnquete.getText(),
						txtSetimaOpcaoEnquete.getText(), txtOitavaOpcaoEnquete.getText(), txtNonaOpcaoEnquete.getText(),
						txtDecimaOpcaoEnquete.getText(), ativo);

				Limpar();
				Alerta.alertaSucesso("Mensagem", genero.A, acao.ADICIONAR);

			} else {
				Alerta.alertaVazio("titulo");
			}
		} catch (Exception e) {
			// TODO: handle exception
			Alerta.alertaVazio("titulo");
			Limpar();
		}
	}

	@FXML
	public void editar(ActionEvent event) {
		try {
			if (!txtTitulo.getText().isBlank() || !txtTitulo.getText().isEmpty()) {

				boolean ativo;

				if (rbAtivo.isSelected()) {
					ativo = true;
				} else {
					ativo = false;
				}

				DaoMensagens.editarMensagen(Integer.parseInt(lblID.getText()), txtTitulo.getText(), txtMidia.getText(),
						txtArquivo.getText(), taConteudo.getText(), taConteudoArquivo.getText(),
						txtTituloEnquete.getText(), txtPrimeiraOpcaoEnquete.getText(), txtSegundaOpcaoEnquete.getText(),
						txtTerceiraOpcaoEnquete.getText(), txtQuartaOpcaoEnquete.getText(),
						txtQuintaOpcaoEnquete.getText(), txtSextaOpcaoEnquete.getText(),
						txtSetimaOpcaoEnquete.getText(), txtOitavaOpcaoEnquete.getText(), txtNonaOpcaoEnquete.getText(),
						txtDecimaOpcaoEnquete.getText(), ativo);

				Limpar();
				Alerta.alertaSucesso("Mensagem", genero.A, acao.EDITAR);

			} else {
				Alerta.alertaVazio("titulo");
			}
		} catch (Exception e) {
			// TODO: handle exception
			Alerta.alertaVazio("titulo");
			Limpar();
		}
	}

	@FXML
	public void remover(ActionEvent event) {
		try {
			if (!btnRemover.isDisable()) {

				DaoMensagens.deletarMensagen(Integer.parseInt(lblID.getText()));

				Limpar();
				Alerta.alertaSucesso("Mensagem", genero.A, acao.REMOVER);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Alerta.alertaVazio("titulo");
			Limpar();
		}
	}

	@FXML
	public void atualizar(ActionEvent event) {
		Limpar();
	}

	@FXML
	public void setarCampos(MouseEvent event) {
		if (tbMensagens.getSelectionModel().getSelectedItem() != null) {
			lblID.setText(String.valueOf(tbMensagens.getSelectionModel().getSelectedItem().getId()));
			Mensagens m = DaoMensagens.pegarPorID(Integer.parseInt(lblID.getText()));

			txtArquivo.setText(m.getArquivo());
			txtMidia.setText(m.getMidia());
			txtTitulo.setText(m.getTitulo());
			taConteudo.setText(m.getConteudo());
			taConteudoArquivo.setText(m.getConteudoArquivo());

			txtTituloEnquete.setText(m.getTituloEnquete());
			txtPrimeiraOpcaoEnquete.setText(m.getConteudoEnquete_1());
			txtSegundaOpcaoEnquete.setText(m.getConteudoEnquete_2());
			txtTerceiraOpcaoEnquete.setText(m.getConteudoEnquete_3());
			txtQuartaOpcaoEnquete.setText(m.getConteudoEnquete_4());
			txtQuintaOpcaoEnquete.setText(m.getConteudoEnquete_5());
			txtSextaOpcaoEnquete.setText(m.getConteudoEnquete_6());
			txtSetimaOpcaoEnquete.setText(m.getConteudoEnquete_7());
			txtOitavaOpcaoEnquete.setText(m.getConteudoEnquete_8());
			txtNonaOpcaoEnquete.setText(m.getConteudoEnquete_9());
			txtDecimaOpcaoEnquete.setText(m.getConteudoEnquete_10());

			btnAdicionar.setDisable(true);
			btnEditar.setDisable(false);
			btnRemover.setDisable(false);

			File file = new File(txtMidia.getText());

			try {
				imgMidia.setImage(new Image(file.toURI().toURL().toExternalForm()));
			} catch (Exception e) {
			}
		}

	}

	@FXML
	public void voltar(MouseEvent event) {
		Main.setScreen(Tela.PRINCIPAL);
	}
}
