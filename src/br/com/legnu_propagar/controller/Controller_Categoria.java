package br.com.legnu_propagar.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.legnu_propagar.dao.DaoCategoria;
import br.com.legnu_propagar.model.Categoria;
import br.com.legnu_propagar.util.Alerta;
import br.com.legnu_propagar.util.Alerta.acao;
import br.com.legnu_propagar.util.Alerta.genero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Controller_Categoria implements Initializable {

	/***
	 * Variaveis
	 *******************************************************************************************************************************************************************************************************************/

	private static double xOffset = 0;
	private static double yOffset = 0;

	@FXML
	private TableView<Categoria> tbCategoria;
	@FXML
	private TableColumn<Categoria, Integer> tbCategoria_ID;
	@FXML
	private TableColumn<Categoria, String> tbCategoria_CATEGORIA;

	@FXML
	private TextField txtCategoria;
	@FXML
	private Label lblId;
	@FXML
	private Button btnAdicionar_Editar;
	@FXML
	private Button btnRemover;

	/***
	 * Inicialização
	 *******************************************************************************************************************************************************************************************************************/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTable();
	}

	/***
	 * Ações
	 *******************************************************************************************************************************************************************************************************************/

	public void initTable() {
		tbCategoria_ID.setCellValueFactory(new PropertyValueFactory("id"));
		tbCategoria_CATEGORIA.setCellValueFactory(new PropertyValueFactory("nome"));
		tbCategoria.setItems(FXCollections.observableArrayList(DaoCategoria.gerarTabela()));
	}

	private void Limpar() {
		txtCategoria.setText("");
		btnAdicionar_Editar.setText("Adicionar");
		btnRemover.setDisable(true);
		initTable();
	}

	/***
	 * Eventos
	 *******************************************************************************************************************************************************************************************************************/

	@FXML
	public void adicionar_Editar(ActionEvent event) {
		try {
			if (!txtCategoria.getText().isBlank() || !txtCategoria.getText().isEmpty()) {
				if (btnAdicionar_Editar.getText().equals("Adicionar")) {
					DaoCategoria.inserirCategoria(txtCategoria.getText());
					Limpar();
					Alerta.alertaSucesso("Categoria", genero.A, acao.ADICIONAR);
				} else {
					DaoCategoria.editarCategoria(Integer.parseInt(lblId.getText()), txtCategoria.getText());
					Limpar();
					Alerta.alertaSucesso("Categoria", genero.A, acao.EDITAR);
				}
			} else {
				Alerta.alertaVazio("nome");
			}
		} catch (Exception e) {
			// TODO: handle exception
			Alerta.alertaVazio("nome");
			Limpar();
		}
	}

	@FXML
	public void remover(ActionEvent event) {
		try {
			if (!btnRemover.isDisable()) {
				DaoCategoria.deletarCategoria(Integer.parseInt(lblId.getText()));
				Limpar();
				Alerta.alertaSucesso("Categoria", genero.A, acao.REMOVER);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Alerta.alertaVazio("nome");
			Limpar();
		}
	}

	@FXML
	public void setarCampos(MouseEvent event) {
		if (tbCategoria.getSelectionModel().getSelectedItem() != null) {
			lblId.setText(String.valueOf(tbCategoria.getSelectionModel().getSelectedItem().getId()));
			txtCategoria.setText(tbCategoria.getSelectionModel().getSelectedItem().getNome());

			btnAdicionar_Editar.setText("Editar");
			btnRemover.setDisable(false);
		}
	}

	@FXML
	public void salvarEstado(MouseEvent event) {
		xOffset = event.getSceneX();
		yOffset = event.getSceneY();
	}

	@FXML
	public void mover(MouseEvent event) {
		try {
			Controller_CadastroCliente.getStage().setX(event.getScreenX() - xOffset);
			Controller_CadastroCliente.getStage().setY(event.getScreenY() - yOffset);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			Controller_CadastroGrupo.getStage().setX(event.getScreenX() - xOffset);
			Controller_CadastroGrupo.getStage().setY(event.getScreenY() - yOffset);
		}
	}

	@FXML
	public void dispensar(MouseEvent event) {
		try {
			Controller_CadastroCliente.getStage().close();
		} catch (Exception e) {
		} finally {
			Controller_CadastroGrupo.getStage().close();
		}

	}

}
