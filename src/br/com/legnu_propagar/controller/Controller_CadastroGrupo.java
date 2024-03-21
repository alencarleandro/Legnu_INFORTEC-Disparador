package br.com.legnu_propagar.controller;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.dao.DaoCategoria;
import br.com.legnu_propagar.dao.DaoClientes;
import br.com.legnu_propagar.dao.DaoGrupos;
import br.com.legnu_propagar.main.Main;
import br.com.legnu_propagar.main.Main.Tela;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Grupos;
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

public class Controller_CadastroGrupo implements Initializable {

	/***
	 * Variaveis
	 *******************************************************************************************************************************************************************************************************************/
	private static double xOffset = 0;
	private static double yOffset = 0;
	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		Controller_CadastroGrupo.stage = stage;
	}

	@FXML
	private TableView<Grupos> tbGrupos;
	@FXML
	private TableColumn<Grupos, Integer> tbGrupos_ID;
	@FXML
	private TableColumn<Grupos, String> tbGrupos_NOME;
	@FXML
	private TableColumn<Grupos, String> tbGrupos_LINK;
	@FXML
	private TableColumn<Grupos, String> tbGrupos_CATEGORIA;

	@FXML
	private Button btnAdicionarEditar;
	@FXML
	private Button btnRemover;
	@FXML
	private Button btnAtualizar;

	@FXML
	private TextField txtPesquisar;
	@FXML
	private TextField txtGrupo;
	@FXML
	private TextField txtLink;

	@FXML
	private ComboBox<String> cbCategoria;

	@FXML
	private Label lblID;
	
	@FXML
	private TextField txtGrupos_Importar;
	@FXML
	private Button btnCaminho_Grupos;
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

		initTable(DaoGrupos.gerarTabela());
		instanciaComboBox();

		Limpar();

	}

	/***
	 * Ações
	 *******************************************************************************************************************************************************************************************************************/

	public void instanciaComboBox() {
		cbCategoria.getItems().add("Categoria do Grupo");
		cbCategoria_Importar.getItems().add("Categoria da importação");
	}

	public void initTable(List<Grupos> lista) {
		tbGrupos_ID.setCellValueFactory(new PropertyValueFactory<Grupos, Integer>("id"));
		tbGrupos_NOME.setCellValueFactory(new PropertyValueFactory<Grupos, String>("nome"));
		tbGrupos_LINK.setCellValueFactory(new PropertyValueFactory<Grupos, String>("link"));
		tbGrupos_CATEGORIA.setCellValueFactory(new PropertyValueFactory<Grupos, String>("categoria"));
		tbGrupos.setItems(FXCollections.observableArrayList(lista));
	}

	private void Limpar() {
		txtGrupo.setText("");
		txtLink.setText("");
		cbCategoria.setValue("Categoria do Grupo");
		btnAdicionarEditar.setText("Adicionar");
		cbCategoria_Importar.setValue("Categoria da importação");
		txtGrupos_Importar.setText("");
		btnRemover.setDisable(true);
		initTable(DaoGrupos.gerarTabela());
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
		initTable(DaoGrupos.gerarTabelaPorNome(txtPesquisar.getText()));
	}

	@FXML
	public void adicionarEditar(ActionEvent event) {
		try {
			if (!txtGrupo.getText().isBlank() || !txtGrupo.getText().isEmpty()) {
				if (btnAdicionarEditar.getText().equals("Adicionar")) {
					if (!cbCategoria.getValue().equals("Categoria do Grupo")) {
						DaoGrupos.inserirGrupo(txtGrupo.getText(), txtLink.getText(),
								DaoCategoria.idCategoria(cbCategoria.getValue()));
					} else {
						DaoGrupos.inserirGrupo(txtGrupo.getText(), txtLink.getText());
					}

					Limpar();
					Alerta.alertaSucesso("Grupo", genero.O, acao.ADICIONAR);
				} else {
					if (!cbCategoria.getValue().equals("Categoria do Grupo")) {
						DaoGrupos.editarGrupo(Integer.parseInt(lblID.getText()), txtGrupo.getText(),
								DaoCategoria.idCategoria(cbCategoria.getValue()), txtLink.getText());
					} else {
						DaoGrupos.editarGrupo(Integer.parseInt(lblID.getText()), txtGrupo.getText(), txtLink.getText());
					}
					Limpar();
					Alerta.alertaSucesso("Grupo", genero.O, acao.EDITAR);
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
	public void PegarCaminho(MouseEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Arquivo");
		File file = fileChooser.showOpenDialog(Main.getStage().getScene().getWindow());

		if (file != null) {
			// pickUpPathField it's your TextField fx:id
			txtGrupos_Importar.setText(file.getPath());

		} else {
			System.out.println("error"); // or something else
		}
	}
	
	@FXML
	public void Importar(ActionEvent event) {
		try {
			if (!cbCategoria_Importar.getValue().equals("Categoria da importação")
					&& !cbCategoria_Importar.getValue().isEmpty()) {
				
				File file = new File(txtGrupos_Importar.getText());	
				String dados = new String(Files.readAllBytes(file.toPath()));
				
				String[] partes = dados.split("§");
				
				for (int i = 0; i < partes.length; i++) {
					DaoGrupos.inserirGrupo(partes[i].replaceAll("\\r\\n", "").replaceAll("\\n", ""),"", DaoCategoria.idCategoria(cbCategoria_Importar.getValue()));
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
	public void remover(ActionEvent event) {
		try {
			if (!btnRemover.isDisable()) {
				DaoGrupos.deletarGrupo(Integer.parseInt(lblID.getText()));
				Limpar();
				Alerta.alertaSucesso("Grupo", genero.O, acao.REMOVER);
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
		if (tbGrupos.getSelectionModel().getSelectedItem() != null) {
			lblID.setText(String.valueOf(tbGrupos.getSelectionModel().getSelectedItem().getId()));
			txtGrupo.setText(tbGrupos.getSelectionModel().getSelectedItem().getNome());
			txtLink.setText(tbGrupos.getSelectionModel().getSelectedItem().getLink());
			cbCategoria.setValue(tbGrupos.getSelectionModel().getSelectedItem().getCategoria());

			btnAdicionarEditar.setText("Editar");
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
			JOptionPane.showMessageDialog(null, "Erro ao abrir Tela Categoria: " + e);
		}
	}

	
}
