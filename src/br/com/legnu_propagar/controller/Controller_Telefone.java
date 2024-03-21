package br.com.legnu_propagar.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.dao.DaoTelefone;
import br.com.legnu_propagar.model.Telefone;
import br.com.legnu_propagar.util.Alerta;
import br.com.legnu_propagar.util.MaskedTextField;
import br.com.legnu_propagar.util.Alerta.acao;
import br.com.legnu_propagar.util.Alerta.genero;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class Controller_Telefone implements Initializable {

	/***
	 * Variaveis
	 *******************************************************************************************************************************************************************************************************************/

	private static double xOffset = 0;
	private static double yOffset = 0;

	@FXML
	private TableView<Telefone> tbTelefone;
	@FXML
	private TableColumn<Telefone, Integer> tbTelefone_ID;
	@FXML
	private TableColumn<Telefone, String> tbTelefone_TIPO;
	@FXML
	private TableColumn<Telefone, String> tbTelefone_NUMERO;

	@FXML
	private TextField txtTelefone;
	@FXML
	private Label lblId;
	@FXML
	private Button btnAdicionar_Editar;
	@FXML
	private Button btnRemover;
	@FXML
	private ComboBox<String> cbTipo;

	/***
	 * Inicialização
	 *******************************************************************************************************************************************************************************************************************/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTable();
		instanciarComboBox();
	}

	/***
	 * Ações
	 *******************************************************************************************************************************************************************************************************************/
	public void instanciarComboBox() {
		cbTipo.setValue("Tipo");
		cbTipo.getItems().add("Tipo");
		cbTipo.getItems().add("Celular");
		cbTipo.getItems().add("Fixo");
	}

	public String formatar(String telefone) {

		MaskedTextField mask = new MaskedTextField();

		if (cbTipo.getValue().equals("Celular")) {
			mask.setMask("(##)#####-####");
		} else if (cbTipo.getValue().equals("Fixo")) {
			mask.setMask("(##)####-####");
		}

		// Change placeholder to empty space
		mask.setPlaceholder(' ');

		// Change mask
		mask.setPlainText(telefone.replace("(", "").replace(")", "").replace("-", ""));

		return mask.getText();
	}

	public void initTable() {
		tbTelefone_ID.setCellValueFactory(new PropertyValueFactory("id"));
		tbTelefone_TIPO.setCellValueFactory(new PropertyValueFactory("tipo"));
		tbTelefone_NUMERO.setCellValueFactory(new PropertyValueFactory("telefone"));
		tbTelefone.setItems(FXCollections.observableArrayList(DaoTelefone.gerarTabela()));
	}

	private void Limpar() {
		txtTelefone.setText("");
		btnAdicionar_Editar.setText("Adicionar");
		btnRemover.setDisable(true);
		cbTipo.setValue("Tipo");
		initTable();
	}

	/***
	 * Eventos
	 *******************************************************************************************************************************************************************************************************************/

	@FXML
	public void adicionar_Editar(ActionEvent event) {
		try {
			if (!cbTipo.getValue().equals("Tipo")) {
				if (!txtTelefone.getText().isBlank() || !txtTelefone.getText().isEmpty()) {
					if (btnAdicionar_Editar.getText().equals("Adicionar")) {
						DaoTelefone.inserirTelefone(cbTipo.getValue(), formatar(txtTelefone.getText()),
								Controller_CadastroCliente.getLblID());
						Limpar();
						Alerta.alertaSucesso("Telefone", genero.O, acao.ADICIONAR);
					} else {
						DaoTelefone.editarTelefone(Integer.parseInt(lblId.getText()), cbTipo.getValue(),
								formatar(txtTelefone.getText()));
						Limpar();
						Alerta.alertaSucesso("Telefone", genero.O, acao.EDITAR);
					}
				} else {
					Alerta.alertaVazio("Telefone");
				}
			} else {
				Alerta.alertaEscolhaCombobox("Tipo");
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
			Alerta.alertaVazio("Telefone");
			Limpar();
		}
	}

	@FXML
	public void remover(ActionEvent event) {
		try {
			if (!btnRemover.isDisable()) {
				DaoTelefone.deletarTelefone(Integer.parseInt(lblId.getText()));
				Limpar();
				Alerta.alertaSucesso("Telefone", genero.O, acao.REMOVER);
			}
		} catch (Exception e) {
			// TODO: handle exception
			Alerta.alertaVazio("Telefone");
			Limpar();
		}
	}

	@FXML
	public void setarCampos(MouseEvent event) {
		if (tbTelefone.getSelectionModel().getSelectedItem() != null) {
			lblId.setText(String.valueOf(tbTelefone.getSelectionModel().getSelectedItem().getId()));
			cbTipo.setValue(String.valueOf(tbTelefone.getSelectionModel().getSelectedItem().getTipo()));
			txtTelefone.setText(tbTelefone.getSelectionModel().getSelectedItem().getTelefone());

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
		Controller_CadastroCliente.getStage().setX(event.getScreenX() - xOffset);
		Controller_CadastroCliente.getStage().setY(event.getScreenY() - yOffset);
	}

	@FXML
	public void dispensar(MouseEvent event) {
		Controller_CadastroCliente.getStage().close();
	}
}
