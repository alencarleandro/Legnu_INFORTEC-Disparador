package br.com.legnu_propagar.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.com.legnu_propagar.dao.DaoClientes;
import br.com.legnu_propagar.dao.DaoGrupos;
import br.com.legnu_propagar.dao.DaoOcorrencia;
import br.com.legnu_propagar.dao.DaoRotina;
import br.com.legnu_propagar.dao.DaoTelefone;
import br.com.legnu_propagar.main.Main;
import br.com.legnu_propagar.main.Main.Tela;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.model.Ocorrencia;
import br.com.legnu_propagar.model.Rotina_de_Disparo;
import br.com.legnu_propagar.util.Alerta;
import br.com.legnu_propagar.util.Alerta.acao;
import br.com.legnu_propagar.util.Alerta.genero;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Controller_Rotina implements Initializable {

	/***
	 * Variaveis
	 *******************************************************************************************************************************************************************************************************************/

	@FXML
	private TableView<Rotina_de_Disparo> tbRotina;
	@FXML
	private TableColumn<Rotina_de_Disparo, Integer> tbRotina_ID;

	@FXML
	private TableView<Ocorrencia> tbClientes;
	@FXML
	private TableColumn<Ocorrencia, Integer> tbClientes_ID;
	@FXML
	private TableColumn<Ocorrencia, String> tbClientes_NOME;

	@FXML
	private TableView<Ocorrencia> tbGrupos;
	@FXML
	private TableColumn<Ocorrencia, Integer> tbGrupos_ID;
	@FXML
	private TableColumn<Ocorrencia, String> tbGrupos_NOME;

	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtTipo;
	@FXML
	private TextField txtIdOcorrencia;

	@FXML
	private TextArea taRotina;
	@FXML
	private Label lblEnvios;
	@FXML
	private Label lblNaoEncontrados;
	@FXML
	private Label lblErros;

	/***
	 * Inicialização
	 *******************************************************************************************************************************************************************************************************************/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTable(DaoRotina.gerarTabela());
	}

	/***
	 * Ações
	 *******************************************************************************************************************************************************************************************************************/

	public void initTable(List<Rotina_de_Disparo> lista) {
		tbRotina_ID.setCellValueFactory(new PropertyValueFactory<Rotina_de_Disparo, Integer>("id"));
		tbRotina.setItems(FXCollections.observableArrayList(lista));

		tbClientes_ID.setCellValueFactory(new PropertyValueFactory<Ocorrencia, Integer>("fk_idOcorrido"));
		tbClientes_NOME.setCellValueFactory(new PropertyValueFactory<Ocorrencia, String>("ocorrencia"));
		tbClientes.setItems(FXCollections.observableArrayList(DaoOcorrencia.gerarOcorrenciasDeClientes()));

		tbGrupos_ID.setCellValueFactory(new PropertyValueFactory<Ocorrencia, Integer>("fk_idOcorrido"));
		tbGrupos_NOME.setCellValueFactory(new PropertyValueFactory<Ocorrencia, String>("ocorrencia"));
		tbGrupos.setItems(FXCollections.observableArrayList(DaoOcorrencia.gerarOcorrenciasDeGrupos()));

	}

	public void limpar() {
		txtId.setText("");
		txtIdOcorrencia.setText("");
		txtNome.setText("");
		txtTipo.setText("");
	}

	/***
	 * Eventos
	 *******************************************************************************************************************************************************************************************************************/

	@FXML
	public void voltar(MouseEvent event) {
		Main.setScreen(Tela.PRINCIPAL);
	}

	@FXML
	public void remover(ActionEvent event) {
		try {
			DaoRotina.deletarRotina();
			initTable(DaoRotina.gerarTabela());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	public void editarOcorrencia(ActionEvent event) {
		try {
			if (!txtIdOcorrencia.getText().isEmpty()) {
				if (txtTipo.getText().equals("Cliente")) {
					DaoClientes.editarCliente(Integer.parseInt(txtId.getText()), txtNome.getText());
				} else {
					DaoGrupos.editarGrupo(Integer.parseInt(txtId.getText()), txtNome.getText());
				}

				DaoOcorrencia.deletarOcorrencia(Integer.parseInt(txtIdOcorrencia.getText()));
				initTable(DaoRotina.gerarTabela());
				limpar();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	public void limparOcorrencias(ActionEvent event) {
		try {
			DaoOcorrencia.limparOcorrencias();
			initTable(DaoRotina.gerarTabela());
			limpar();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	public void escolherCliente(MouseEvent event) {
		if (tbClientes.getSelectionModel().getSelectedItem() != null) {
			txtId.setText(String.valueOf(tbClientes.getSelectionModel().getSelectedItem().getFk_idOcorrido()));
			txtTipo.setText(String.valueOf(tbClientes.getSelectionModel().getSelectedItem().getTipo()));
			txtNome.setText(String.valueOf(tbClientes.getSelectionModel().getSelectedItem().getOcorrencia()));
			txtIdOcorrencia.setText(String.valueOf(tbClientes.getSelectionModel().getSelectedItem().getId()));
		}
	}

	@FXML
	public void escolherGrupo(MouseEvent event) {
		if (tbClientes.getSelectionModel().getSelectedItem() != null) {
			txtId.setText(String.valueOf(tbGrupos.getSelectionModel().getSelectedItem().getFk_idOcorrido()));
			txtTipo.setText(String.valueOf(tbGrupos.getSelectionModel().getSelectedItem().getTipo()));
			txtNome.setText(String.valueOf(tbGrupos.getSelectionModel().getSelectedItem().getOcorrencia()));
			txtIdOcorrencia.setText(String.valueOf(tbGrupos.getSelectionModel().getSelectedItem().getId()));
		}
	}

	@FXML
	public void setarCampos(MouseEvent event) {
		try {
			if (tbRotina.getSelectionModel().getSelectedItem() != null) {
				taRotina.setText(String.valueOf(tbRotina.getSelectionModel().getSelectedItem().getRotina()));

				int quant = 0;

				String[] arrayString = taRotina.getText().replaceAll("\n", " ").split(" ");

				for (int i = 0; i < arrayString.length; i++) {

					if (arrayString[i].equals("E@§")) {
						quant++;
					}

				}

				lblEnvios.setText(String.valueOf(quant));

				quant = 0;

				for (int i = 0; i < arrayString.length; i++) {

					if (arrayString[i].equals("F@§")) {
						quant++;
					}

				}

				lblErros.setText(String.valueOf(quant));

				quant = 0;

				for (int i = 0; i < arrayString.length; i++) {

					if (arrayString[i].equals("N@§")) {
						quant++;
					}

				}

				lblNaoEncontrados.setText(String.valueOf(quant));

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
