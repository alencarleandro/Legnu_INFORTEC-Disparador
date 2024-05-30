package br.com.legnu_propagar.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.dao.DaoCategoria;
import br.com.legnu_propagar.dao.DaoClientes;
import br.com.legnu_propagar.dao.DaoTelefone;
import br.com.legnu_propagar.main.Main;
import br.com.legnu_propagar.main.Main.Tela;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.util.Alerta;
import br.com.legnu_propagar.util.Alerta.acao;
import br.com.legnu_propagar.util.Alerta.genero;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller_CadastroCliente implements Initializable {

	/***
	 * Variaveis
	 *******************************************************************************************************************************************************************************************************************/

	private static double xOffset = 0;
	private static double yOffset = 0;

	private static Stage stage;

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
	private ComboBox<String> cbCategoria;
	@FXML
	private ComboBox<String> cbTelefone;
	@FXML
	private TextField txtCliente;

	private static int lblID = -1;

	public static int getLblID() {
		return lblID;
	}

	public static void setLblID(int lblID) {
		Controller_CadastroCliente.lblID = lblID;
	}

	@FXML
	private TableView<Clientes> tbClientes;
	@FXML
	private TableColumn<Clientes, Integer> tbClientes_ID;
	@FXML
	private TableColumn<Clientes, String> tbClientes_NOME;
	@FXML
	private TableColumn<Clientes, String> tbClientes_CATEGORIA;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Controller_CadastroCliente.stage = stage;
	}

	@FXML
	private TextField txtCliente_Importar;
	@FXML
	private Button btnCaminho_Cliente;
	@FXML
	private ComboBox<String> cbCategoria_Importar;
	@FXML
	private Button btnCategoria_Importar;

	/***
	 * Inicialização
	 *******************************************************************************************************************************************************************************************************************/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTable(DaoClientes.gerarTabela());
		instanciaComboBox();
		
		Limpar();

	}

	/***
	 * Ações
	 *******************************************************************************************************************************************************************************************************************/
	public void instanciaComboBox() {
		cbCategoria.getItems().add("Categoria do Cliente");
		cbTelefone.getItems().add("Telefone(s) do Cliente");
		cbCategoria_Importar.getItems().add("Categoria da importação");
	}

	public void initTable(List<Clientes> lista) {
		tbClientes_ID.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("id"));
		tbClientes_NOME.setCellValueFactory(new PropertyValueFactory<Clientes, String>("nome"));
		tbClientes_CATEGORIA.setCellValueFactory(new PropertyValueFactory<Clientes, String>("categoria"));
		tbClientes.setItems(FXCollections.observableArrayList(lista));
	}

	private void Limpar() {
		txtCliente.setText("");
		lblID = -1;
		cbCategoria.setValue("Categoria do Cliente");
		cbTelefone.setValue("Telefone(s) do Cliente");
		cbCategoria_Importar.setValue("Categoria da importação");
		txtCliente_Importar.setText("");
		btnAdicionar.setDisable(false);
		btnEditar.setDisable(true);
		btnRemover.setDisable(true);
		initTable(DaoClientes.gerarTabela());
	}

	/***
	 * Eventos
	 *******************************************************************************************************************************************************************************************************************/
	@FXML
	public void voltar(MouseEvent event) {
		Main.setScreen(Tela.PRINCIPAL);
	}

	@FXML
	public void Pesquisar(KeyEvent event) {
		initTable(DaoClientes.gerarTabelaPorNome(txtPesquisar.getText()));
	}

	@FXML
	public void adicionar(ActionEvent event) {
		try {
			if (!txtCliente.getText().isBlank() || !txtCliente.getText().isEmpty()) {

				if (!cbCategoria.getValue().equals("Categoria do Cliente")) {
					DaoClientes.inserirCliente(txtCliente.getText(), DaoCategoria.idCategoria(cbCategoria.getValue()));
				} else {
					DaoClientes.inserirCliente(txtCliente.getText());
				}

				Limpar();
				Alerta.alertaSucesso("Cliente", genero.O, acao.ADICIONAR);

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
	public void PegarCaminho(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Arquivo");
		File file = fileChooser.showOpenDialog(Main.getStage().getScene().getWindow());

		if (file != null) {
			// pickUpPathField it's your TextField fx:id
			txtCliente_Importar.setText(file.getPath());

		} else {
			System.out.println("error"); // or something else
		}
	}
	
	@FXML
	public void Importar(ActionEvent event) {
		try {
			if (!cbCategoria_Importar.getValue().equals("Categoria da importação")
					&& !cbCategoria_Importar.getValue().isEmpty()) {
				
				File file = new File(txtCliente_Importar.getText());	
				String dados = new String(Files.readAllBytes(file.toPath()));
				
				String[] partes = dados.split("§");
				
				for (int i = 0; i < partes.length; i++) {
					DaoClientes.inserirCliente(partes[i].replaceAll("\\r\\n", "").replaceAll("\\n", ""), DaoCategoria.idCategoria(cbCategoria_Importar.getValue()));
				}
								
				JOptionPane.showMessageDialog(null, "Importação realizada com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "Insira uma categoria.");
			}
			
			Limpar();			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			Limpar();
		}
	}

	@FXML
	public void editar(ActionEvent event) {
		try {
			if (!txtCliente.getText().isBlank() || !txtCliente.getText().isEmpty()) {
				if (!cbCategoria.getValue().equals("Categoria do Cliente")) {
					DaoClientes.editarCliente(getLblID(), txtCliente.getText(),
							DaoCategoria.idCategoria(cbCategoria.getValue()));
				} else {
					DaoClientes.editarCliente(getLblID(), txtCliente.getText());
				}
				Limpar();
				Alerta.alertaSucesso("Cliente", genero.O, acao.EDITAR);

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
				DaoClientes.deletarCliente(getLblID());
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
	public void atualizar(ActionEvent event) {
		Limpar();
	}

	@FXML
	public void setarCampos(MouseEvent event) {
		if (tbClientes.getSelectionModel().getSelectedItem() != null) {
			lblID = tbClientes.getSelectionModel().getSelectedItem().getId();
			txtCliente.setText(tbClientes.getSelectionModel().getSelectedItem().getNome());
			cbCategoria.setValue(tbClientes.getSelectionModel().getSelectedItem().getCategoria());

			btnAdicionar.setDisable(true);
			btnEditar.setDisable(false);
			btnRemover.setDisable(false);
		}
	}

	@FXML
	public void InstanciarCategoria(MouseEvent event) {

		while (cbCategoria.getItems().size() != 1) {
			cbCategoria.getItems().remove(cbCategoria.getItems().size() - 1);
		}

		for (int i = 0; i < DaoCategoria.gerarTabela().size(); i++) {
			cbCategoria.getItems().add(DaoCategoria.gerarTabela().get(i).getNome());
		}

	}
	
	@FXML
	public void InstanciarCategoriaImportar(MouseEvent event) {

		while (cbCategoria_Importar.getItems().size() != 1) {
			cbCategoria_Importar.getItems().remove(cbCategoria_Importar.getItems().size() - 1);
		}

		for (int i = 0; i < DaoCategoria.gerarTabela().size(); i++) {
			cbCategoria_Importar.getItems().add(DaoCategoria.gerarTabela().get(i).getNome());
		}

	}

	@FXML
	public void InstanciarTelefone(MouseEvent event) {

		while (cbTelefone.getItems().size() != 1) {
			cbTelefone.getItems().remove(cbTelefone.getItems().size() - 1);
		}

		for (int i = 0; i < DaoTelefone.gerarTabela().size(); i++) {
			cbTelefone.getItems().add(DaoTelefone.gerarTabela().get(i).getTelefone());
		}

	}

	@FXML
	public void telefone(MouseEvent event) {
		try {
			if (!txtCliente.getText().isBlank() || !txtCliente.getText().isEmpty()) {
				if (getLblID() == -1) {
					if (!cbCategoria.getValue().equals("Categoria do Cliente")) {
						DaoClientes.inserirCliente(txtCliente.getText(),
								DaoCategoria.idCategoria(cbCategoria.getValue()));
					} else {
						DaoClientes.inserirCliente(txtCliente.getText());
					}
					lblID = DaoClientes.pegarUltimo().getId();
					initTable(DaoClientes.gerarTabela());
				}
			}

			if (getLblID() != -1) {
				FXMLLoader fxmlCadastroCliente = new FXMLLoader(
						Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaCadastroTelefone.fxml"));
				Parent telaTelefone = (Parent) fxmlCadastroCliente.load();

				if (stage != null) {
					stage.close();
				}

				stage = new Stage();
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.getIcons()
						.add(new Image(getClass().getResource("/br/com/legnu_propagar/util/imagens/LegnuIcon.png").toExternalForm()));
				stage.setScene(new Scene(telaTelefone));
				stage.show();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir Tela Telefone: " + e);
		}
	}

	@FXML
	public void categoria(MouseEvent event) {
		try {
			FXMLLoader fxmlCadastroCliente = new FXMLLoader(
					Controller_Introduxcao.class.getResource("/br/com/legnu_propagar/view/TelaCadastroCategoria.fxml"));
			Parent telaTelefone = (Parent) fxmlCadastroCliente.load();

			if (stage != null) {
				stage.close();
			}

			stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.getIcons().add(new Image(getClass().getResource("/br/com/legnu_propagar/util/imagens/LegnuIcon.png").toExternalForm()));
			stage.setScene(new Scene(telaTelefone));
			stage.show();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao abrir Tela Telefone: " + e);
		}
	}

}
