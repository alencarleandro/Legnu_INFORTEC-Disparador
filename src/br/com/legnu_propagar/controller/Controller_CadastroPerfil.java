package br.com.legnu_propagar.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.legnu_propagar.dao.DaoCategoria;
import br.com.legnu_propagar.dao.DaoClientes;
import br.com.legnu_propagar.dao.DaoProfiles;
import br.com.legnu_propagar.main.Main;
import br.com.legnu_propagar.main.Main.Tela;
import br.com.legnu_propagar.model.Categoria;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Profiles;
import br.com.legnu_propagar.util.Alerta;
import br.com.legnu_propagar.util.Alerta.acao;
import br.com.legnu_propagar.util.Alerta.genero;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller_CadastroPerfil implements Initializable {

	/***
	 * Variaveis
	 *******************************************************************************************************************************************************************************************************************/
	private static double xOffset = 0;
	private static double yOffset = 0;

	@FXML
	private TableView<Profiles> tbPerfil;
	@FXML
	private TableColumn<Profiles, Integer> tbPerfil_ID;
	@FXML
	private TableColumn<Profiles, String> tbPerfil_TITULO;
	@FXML
	private TableColumn<Profiles, String> tbPerfil_CAMINHO;

	@FXML
	private TextField txtPerfil;
	@FXML
	private TextField txtCaminho;
	@FXML
	private TextField txtPesquisar;

	@FXML
	private Label lblID;

	@FXML
	private Button btnAdicionarEditar;
	@FXML
	private Button btnRemover;

	/***
	 * Inicialização
	 *******************************************************************************************************************************************************************************************************************/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTable(DaoProfiles.gerarTabela());
		Limpar();
	}

	/***
	 * Ações
	 *******************************************************************************************************************************************************************************************************************/
	public void initTable(List<Profiles> lista) {
		tbPerfil_ID.setCellValueFactory(new PropertyValueFactory("id"));
		tbPerfil_TITULO.setCellValueFactory(new PropertyValueFactory("titulo"));
		tbPerfil_CAMINHO.setCellValueFactory(new PropertyValueFactory("path"));
		tbPerfil.setItems(FXCollections.observableArrayList(lista));
	}

	private void Limpar() {
		txtPerfil.setText("");
		txtCaminho.setText("");
		btnAdicionarEditar.setText("Adicionar");
		btnRemover.setDisable(true);
		initTable(DaoProfiles.gerarTabela());
	}

	/***
	 * Eventos
	 *******************************************************************************************************************************************************************************************************************/

	@FXML
	public void Pesquisar(KeyEvent event) {
		initTable(DaoProfiles.gerarTabelaPorTitulo(txtPesquisar.getText()));
	}

	@FXML
	public void adicionar_Editar(ActionEvent event) {
		try {
			if (!txtPerfil.getText().isBlank() || !txtPerfil.getText().isEmpty() || !txtCaminho.getText().isBlank()
					|| !txtCaminho.getText().isEmpty()) {
				if (btnAdicionarEditar.getText().equals("Adicionar")) {
					DaoProfiles.inserirProfile(txtPerfil.getText(), txtCaminho.getText());
					Limpar();
					Alerta.alertaSucesso("Perfil", genero.O, acao.ADICIONAR);
				} else {
					DaoProfiles.editarProfile(Integer.parseInt(lblID.getText()), txtPerfil.getText(),
							txtCaminho.getText());
					Limpar();
					Alerta.alertaSucesso("Perfil", genero.O, acao.EDITAR);
				}
			} else {
				Alerta.alertaVazio("Perfil , Caminho");
			}
		} catch (Exception e) {
			// TODO: handle exception
			Alerta.alertaVazio("Perfil , Caminho");
			Limpar();
		}
	}

	@FXML
	public void remover(ActionEvent event) {
		try {
			if (!btnRemover.isDisable()) {
				DaoProfiles.deletarProfile(Integer.parseInt(lblID.getText()));
				Limpar();
				Alerta.alertaSucesso("Perfil", genero.O, acao.REMOVER);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Alerta.alertaVazio("nome");
			Limpar();
		}
	}

	@FXML
	public void setarCampos(MouseEvent event) {
		if (tbPerfil.getSelectionModel().getSelectedItem() != null) {
			lblID.setText(String.valueOf(tbPerfil.getSelectionModel().getSelectedItem().getId()));
			txtPerfil.setText(tbPerfil.getSelectionModel().getSelectedItem().getTitulo());
			txtCaminho.setText(tbPerfil.getSelectionModel().getSelectedItem().getPath());

			btnAdicionarEditar.setText("Editar");
			btnRemover.setDisable(false);
		}
	}

	@FXML
	public void voltar(MouseEvent event) {
		Main.setScreen(Tela.PRINCIPAL);
	}
}
