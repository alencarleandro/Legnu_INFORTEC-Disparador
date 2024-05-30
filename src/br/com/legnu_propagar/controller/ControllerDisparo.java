package br.com.legnu_propagar.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.legnu_propagar.dao.DaoCategoria;
import br.com.legnu_propagar.dao.DaoClientes;
import br.com.legnu_propagar.dao.DaoConfiguracao;
import br.com.legnu_propagar.dao.DaoGrupos;
import br.com.legnu_propagar.dao.DaoMensagens;
import br.com.legnu_propagar.dao.DaoProfiles;
import br.com.legnu_propagar.disparo.Disparo;
import br.com.legnu_propagar.disparo.Disparo.Modo;
import br.com.legnu_propagar.disparo.Disparo.Quem;
import br.com.legnu_propagar.main.Main;
import br.com.legnu_propagar.main.Main.Tela;
import br.com.legnu_propagar.model.Categoria;
import br.com.legnu_propagar.model.Clientes;
import br.com.legnu_propagar.model.Grupos;
import br.com.legnu_propagar.model.Mensagens;
import br.com.legnu_propagar.util.Alerta;
import br.com.legnu_propagar.util.Alerta.Tempo;
import br.com.legnu_propagar.util.Alerta.acao;
import br.com.legnu_propagar.util.Alerta.genero;
import br.com.legnu_propagar.util.busca.PegarDatasUtil;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class ControllerDisparo implements Initializable {

	/***
	 * Variaveis
	 *******************************************************************************************************************************************************************************************************************/
	@FXML
	private RadioButton rbWhatsapp;
	@FXML
	private RadioButton rbFacebook;
	@FXML
	private RadioButton rbInstagram;
	@FXML
	private RadioButton rbEmail;

	@FXML
	private ComboBox<String> cbPerfil;
	@FXML
	private ImageView btnVoltar;

	@FXML
	private TableView<Clientes> tbClientes;
	@FXML
	private TableColumn<Clientes, Integer> tbClientes_ID;
	@FXML
	private TableColumn<Clientes, Integer> tbClientes_POSICAO;
	@FXML
	private TableColumn<Clientes, String> tbClientes_NOME;
	@FXML
	private TableColumn<Clientes, String> tbClientes_CATEGORIA;
	@FXML
	private TableColumn<Clientes, Date> tbClientes_ULTIMOENVIO;

	@FXML
	private Button btnClientes_ANTERIOR;
	@FXML
	private Label lblClientes_PAGINA_ATUAL;
	@FXML
	private Label lblClientes_NUMERO_DE_PAGINAS;
	@FXML
	private Button btnClientes_PROXIMO;

	@FXML
	private TextField txtPesquisar_CLIENTE;

	@FXML
	private TableView<Grupos> tbGrupos;
	@FXML
	private TableColumn<Grupos, Integer> tbGrupos_ID;
	@FXML
	private TableColumn<Grupos, String> tbGrupos_NOME;
	@FXML
	private TableColumn<Grupos, String> tbGrupos_CATEGORIA;
	@FXML
	private TableColumn<Grupos, Integer> tbGrupos_POSICAO;
	@FXML
	private TableColumn<Grupos, Date> tbGrupos_ULTIMOENVIO;

	@FXML
	private Button btnGrupos_ANTERIOR;
	@FXML
	private Label lblGrupos_PAGINA_ATUAL;
	@FXML
	private Label lblGrupos_NUMERO_DE_PAGINAS;
	@FXML
	private Button btnGrupos_PROXIMO;

	@FXML
	private TextField txtPesquisar_GRUPO;

	@FXML
	private Button btnDisparar;

	@FXML
	private TableView<Categoria> tbCategoria;
	@FXML
	private TableColumn<Categoria, String> tbCategoria_COLUNA;

	@FXML
	private TableView<Categoria> tbCategoriasSelecionadas;
	@FXML
	private TableColumn<Categoria, String> tbCategoriasSelecionadas_COLUNA;

	@FXML
	private TableView<Mensagens> tbMensagens;
	@FXML
	private TableColumn<Mensagens, String> tbMensagens_COLUNA;

	@FXML
	private TableView<Mensagens> tbMensagensSelecionadas;
	@FXML
	private TableColumn<Mensagens, String> tbMensagensSelecionadas_COLUNA;

	@FXML
	private RadioButton rbEnviarPara_HOJE;
	@FXML
	private RadioButton rbEnviarPara_SEMANA;
	@FXML
	private RadioButton rbEnviarPara_MES;
	@FXML
	private RadioButton rbEnviarPara_NUNCA;
	@FXML
	private RadioButton rbEnviarPara_TODOS;

	@FXML
	private TextField txtPorPosição_DE;
	@FXML
	private TextField txtPorPosição_A;

	@FXML
	private RadioButton rbDebugDoDisparo_DESATIVADO;
	@FXML
	private RadioButton rbDebugDoDisparo_ATIVADO;

	@FXML
	private Button btnAplicar;

	@FXML
	private Button btnMandarMensagem;
	@FXML
	private ImageView imgMandarMensagem;
	@FXML
	private Button btnTirarMensagem;
	@FXML
	private ImageView imgTirarMensagem;

	@FXML
	private Button btnMandarCategoria;
	@FXML
	private ImageView imgMandarCategoria;
	@FXML
	private Button btnTirarCategoria;
	@FXML
	private ImageView imgTirarCategoria;

	@FXML
	private Label lblLinhasGrupos;
	@FXML
	private Label lblLinhasClientes;

	@FXML
	private RadioButton rbComeçando_QUALQUER;

	@FXML
	private RadioButton rbComeçando_A;
	@FXML
	private RadioButton rbComeçando_B;
	@FXML
	private RadioButton rbComeçando_C;
	@FXML
	private RadioButton rbComeçando_D;
	@FXML
	private RadioButton rbComeçando_E;
	@FXML
	private RadioButton rbComeçando_F;
	@FXML
	private RadioButton rbComeçando_G;
	@FXML
	private RadioButton rbComeçando_H;
	@FXML
	private RadioButton rbComeçando_I;
	@FXML
	private RadioButton rbComeçando_J;
	@FXML
	private RadioButton rbComeçando_K;
	@FXML
	private RadioButton rbComeçando_L;
	@FXML
	private RadioButton rbComeçando_M;
	@FXML
	private RadioButton rbComeçando_N;
	@FXML
	private RadioButton rbComeçando_O;
	@FXML
	private RadioButton rbComeçando_P;
	@FXML
	private RadioButton rbComeçando_Q;
	@FXML
	private RadioButton rbComeçando_R;
	@FXML
	private RadioButton rbComeçando_S;
	@FXML
	private RadioButton rbComeçando_T;
	@FXML
	private RadioButton rbComeçando_U;
	@FXML
	private RadioButton rbComeçando_V;
	@FXML
	private RadioButton rbComeçando_W;
	@FXML
	private RadioButton rbComeçando_X;
	@FXML
	private RadioButton rbComeçando_Y;
	@FXML
	private RadioButton rbComeçando_Z;

	@FXML
	private RadioButton rbComeçando_ESP;

	@FXML
	private Tab tabClientes;
	@FXML
	private Tab tabGrupos;

	@FXML
	private TextField txtPersonalizado;
	
	@FXML
	private TextField txtGeckoDriver;
	@FXML
	private TextField txtFirefox;
	@FXML
	private TextField txtIniciar_Whatsapp;
	@FXML
	private TextField txtReiniciar_Whatsapp;
	@FXML
	private TextField txtIniciar_Facebook;
	@FXML
	private TextField txtReiniciar_Facebook;
	@FXML
	private TextField txtIniciar_Instagram;
	@FXML
	private TextField txtReiniciar_Instagram;
	@FXML
	private TextField txtIniciar_Gmail;
	@FXML
	private TextField txtReiniciar_Gmail;

	private List<Mensagens> Mensagens = new ArrayList<Mensagens>();
	private List<Categoria> Categorias = new ArrayList<Categoria>();

	final ToggleGroup redeSocial = new ToggleGroup();
	final ToggleGroup enviarPara = new ToggleGroup();
	final ToggleGroup celulasPorPagina = new ToggleGroup();
	final ToggleGroup debugDisparo = new ToggleGroup();

	/***
	 * Inicialização
	 *******************************************************************************************************************************************************************************************************************/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTable();
		instanciaComboBox();
		radioGroups();
		contagemLinhas();
		instanciarConfiguracao();
	}

	/***
	 * Ações
	 *******************************************************************************************************************************************************************************************************************/

	public void instanciarConfiguracao() {
		try {
			txtGeckoDriver.setText(DaoConfiguracao.pegarConfiguracao(1).getExecutavelGecko());
			txtFirefox.setText(DaoConfiguracao.pegarConfiguracao(1).getExecutavelFirefox());
		
			txtIniciar_Whatsapp.setText(String.valueOf(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(2).getConf1()) / 60000));
			txtReiniciar_Whatsapp.setText(String.valueOf(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(2).getConf2()) / 1000));
		
			txtIniciar_Facebook.setText(String.valueOf(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(3).getConf1()) / 60000));
			txtReiniciar_Facebook.setText(String.valueOf(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(3).getConf2()) / 1000));
		
			txtIniciar_Instagram.setText(String.valueOf(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(4).getConf1()) / 60000));
			txtReiniciar_Instagram.setText(String.valueOf(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(4).getConf2()) / 1000));
		
			txtIniciar_Gmail.setText(String.valueOf(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(5).getConf1()) / 60000));
			txtReiniciar_Gmail.setText(String.valueOf(Integer.parseInt(DaoConfiguracao.pegarConfiguracao(5).getConf2()) / 1000));
		
		}catch (Exception e) {}
				
	}
	
	public void contagemLinhas() {
		lblLinhasClientes.setText(String.valueOf(tbClientes.getItems().size()));
		lblLinhasGrupos.setText(String.valueOf(tbGrupos.getItems().size()));
	}

	public void instanciaComboBox() {
		cbPerfil.getItems().add("Perfil");
		cbPerfil.setValue("Perfil");
	}

	public void initTable() {/* tbClientes, tbGrupos, tbCategoria, tbPerfil, tbMensagem */		
		tbClientes_NOME.setCellValueFactory(new PropertyValueFactory<Clientes, String>("nome"));
		tbClientes_CATEGORIA.setCellValueFactory(new PropertyValueFactory<Clientes, String>("categoria"));
		tbClientes_POSICAO.setCellValueFactory(new PropertyValueFactory<Clientes, Integer>("posicao"));
		tbClientes_ULTIMOENVIO.setCellValueFactory(new PropertyValueFactory<Clientes, Date>("ultimoEnvio"));
		tbClientes.setItems(FXCollections.observableArrayList(DaoClientes.gerarTabela()));

		tbGrupos_NOME.setCellValueFactory(new PropertyValueFactory<Grupos, String>("nome"));
		tbGrupos_CATEGORIA.setCellValueFactory(new PropertyValueFactory<Grupos, String>("categoria"));
		tbGrupos_POSICAO.setCellValueFactory(new PropertyValueFactory<Grupos, Integer>("posicao"));
		tbGrupos_ULTIMOENVIO.setCellValueFactory(new PropertyValueFactory<Grupos, Date>("ultimoEnvio"));
		tbGrupos.setItems(FXCollections.observableArrayList(DaoGrupos.gerarTabela()));

		tbCategoria_COLUNA.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nome"));
		tbCategoria.setItems(FXCollections.observableArrayList(DaoCategoria.gerarTabela()));

		tbMensagens_COLUNA.setCellValueFactory(new PropertyValueFactory<Mensagens, String>("titulo"));
		tbMensagens.setItems(FXCollections.observableArrayList(DaoMensagens.gerarTabela()));

		tbMensagensSelecionadas_COLUNA.setCellValueFactory(new PropertyValueFactory<Mensagens, String>("titulo"));
		tbMensagensSelecionadas.setItems(FXCollections.observableArrayList(Mensagens));

		tbCategoriasSelecionadas_COLUNA.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nome"));
		tbCategoriasSelecionadas.setItems(FXCollections.observableArrayList(Categorias));
	
	}

	public void giroParaDireita(Button btn, ImageView img) {
		TranslateTransition translate = new TranslateTransition();

		translate.setNode(img);
		translate.setDuration(Duration.millis(200));
		translate.setCycleCount(2);
		translate.setFromX(0);
		translate.setToX(30);
		translate.setAutoReverse(true);
		translate.play();
		btn.setDisable(true);
	}

	public void giroParaEsquerda(Button btn, ImageView img) {
		TranslateTransition translate = new TranslateTransition();

		translate.setNode(img);
		translate.setDuration(Duration.millis(200));
		translate.setCycleCount(2);
		translate.setFromX(0);
		translate.setToX(-30);
		translate.setAutoReverse(true);
		translate.play();
		btn.setDisable(true);
	}

	public void radioGroups() {
		rbWhatsapp.setToggleGroup(redeSocial);
		rbFacebook.setToggleGroup(redeSocial);
		rbInstagram.setToggleGroup(redeSocial);
		rbEmail.setToggleGroup(redeSocial);
		rbWhatsapp.setSelected(true);

		rbEnviarPara_HOJE.setToggleGroup(enviarPara);
		rbEnviarPara_MES.setToggleGroup(enviarPara);
		rbEnviarPara_NUNCA.setToggleGroup(enviarPara);
		rbEnviarPara_SEMANA.setToggleGroup(enviarPara);
		rbEnviarPara_TODOS.setToggleGroup(enviarPara);
		rbEnviarPara_TODOS.setSelected(true);

		rbDebugDoDisparo_ATIVADO.setToggleGroup(debugDisparo);
		rbDebugDoDisparo_DESATIVADO.setToggleGroup(debugDisparo);
		rbDebugDoDisparo_DESATIVADO.setSelected(true);

	}

	/***
	 * Categoria
	 *******************************************************************************************************************************************************************************************************************/

	public void mandarCategoria() {
		if (tbCategoria.getSelectionModel().getSelectedItem() != null) {
			Categoria c = new Categoria();
			c.setNome(tbCategoria.getSelectionModel().getSelectedItem().getNome());

			Categorias.add(c);

			tbCategoriasSelecionadas_COLUNA.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nome"));
			tbCategoriasSelecionadas.setItems(FXCollections.observableArrayList(Categorias));
			filtro();	
		}
	}

	public void tirarCategoria() {
		if (tbCategoriasSelecionadas.getSelectionModel().getSelectedItem() != null) {	
			Categorias.remove(tbCategoriasSelecionadas.getSelectionModel().getSelectedItem());
			tbCategoriasSelecionadas_COLUNA.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nome"));
			tbCategoriasSelecionadas.setItems(FXCollections.observableArrayList(Categorias));
			filtro();	
		}
	}
	

	/***
	 * Mensagem
	 *******************************************************************************************************************************************************************************************************************/

	public void mandarMensagem() {
		if (tbMensagens.getSelectionModel().getSelectedItem() != null) {

			Mensagens m = new Mensagens();

			m.setId(tbMensagens.getSelectionModel().getSelectedItem().getId());

			m.setArquivo(tbMensagens.getSelectionModel().getSelectedItem().getArquivo());
			m.setConteudoArquivo(tbMensagens.getSelectionModel().getSelectedItem().getConteudoArquivo());

			m.setConteudo(tbMensagens.getSelectionModel().getSelectedItem().getConteudo());
			m.setMidia(tbMensagens.getSelectionModel().getSelectedItem().getMidia());
			m.setTitulo(tbMensagens.getSelectionModel().getSelectedItem().getTitulo());

			m.setTituloEnquete(tbMensagens.getSelectionModel().getSelectedItem().getTituloEnquete());
			m.setConteudoEnquete_1(tbMensagens.getSelectionModel().getSelectedItem().getConteudoEnquete_1());
			m.setConteudoEnquete_2(tbMensagens.getSelectionModel().getSelectedItem().getConteudoEnquete_2());
			m.setConteudoEnquete_3(tbMensagens.getSelectionModel().getSelectedItem().getConteudoEnquete_3());
			m.setConteudoEnquete_4(tbMensagens.getSelectionModel().getSelectedItem().getConteudoEnquete_4());
			m.setConteudoEnquete_5(tbMensagens.getSelectionModel().getSelectedItem().getConteudoEnquete_5());
			m.setConteudoEnquete_6(tbMensagens.getSelectionModel().getSelectedItem().getConteudoEnquete_6());
			m.setConteudoEnquete_7(tbMensagens.getSelectionModel().getSelectedItem().getConteudoEnquete_7());
			m.setConteudoEnquete_8(tbMensagens.getSelectionModel().getSelectedItem().getConteudoEnquete_8());
			m.setConteudoEnquete_9(tbMensagens.getSelectionModel().getSelectedItem().getConteudoEnquete_9());
			m.setConteudoEnquete_10(tbMensagens.getSelectionModel().getSelectedItem().getConteudoEnquete_10());

			m.setVariasRespostas(tbMensagens.getSelectionModel().getSelectedItem().isVariasRespostas());

			Mensagens.add(m);
			tbMensagensSelecionadas_COLUNA.setCellValueFactory(new PropertyValueFactory<Mensagens, String>("titulo"));
			tbMensagensSelecionadas.setItems(FXCollections.observableArrayList(Mensagens));
		}
	}

	public void tirarMensagem() {
		if (tbMensagensSelecionadas.getSelectionModel().getSelectedItem() != null) {
			Mensagens.remove(tbMensagensSelecionadas.getSelectionModel().getSelectedItem());
			tbMensagensSelecionadas_COLUNA.setCellValueFactory(new PropertyValueFactory<Mensagens, String>("titulo"));
			tbMensagensSelecionadas.setItems(FXCollections.observableArrayList(Mensagens));
		}
	}

	public void filtro() {
		if (txtPorPosição_DE.getText().equals("")) {
			txtPorPosição_DE.setText("0");
		}

		if (txtPorPosição_A.getText().equals("")) {
			txtPorPosição_A.setText("0");
		}

		if (tabClientes.isSelected()) {
			initTable();
			
			List<Clientes> aux = new ArrayList<Clientes>();
			
			Date referencia = new Date();			
			
			
			if(rbEnviarPara_HOJE.isSelected()) {
				for(int i = 0; i < tbClientes.getItems().size(); i++) {
					referencia = tbClientes.getItems().get(i).getUltimoEnvio();
					if(PegarDatasUtil.toSqlDate(referencia).toString().equals(PegarDatasUtil.toSqlDate(PegarDatasUtil.getDate()).toString())) {
						aux.add(tbClientes.getItems().get(i));
					}
				}				
			} else if (rbEnviarPara_SEMANA.isSelected()){
				
				for (int i = 0; i < tbClientes.getItems().size(); i++) {
					if (tbClientes.getItems().get(i).getUltimoEnvio().after(PegarDatasUtil.subtrairDataDe(7))) {
						aux.add(tbClientes.getItems().get(i));
					}
				}
			} else if (rbEnviarPara_MES.isSelected()) {
				for (int i = 0; i < tbClientes.getItems().size(); i++) {
					if (tbClientes.getItems().get(i).getUltimoEnvio().after(PegarDatasUtil.subtrairDataDe(30))) {
						aux.add(tbClientes.getItems().get(i));
					}
				}
			} else if (rbEnviarPara_NUNCA.isSelected()) {
				for (int i = 0; i < tbClientes.getItems().size(); i++) {
					if (tbClientes.getItems().get(i).getUltimoEnvio().after(PegarDatasUtil.subtrairDataDe(3650))) {
						aux.add(tbClientes.getItems().get(i));
					}
				}
		    }
			
			if(rbComeçando_QUALQUER.isSelected()) {
				
			}else if(rbComeçando_ESP.isSelected()) {
				for (int i = 0; i < tbClientes.getItems().size(); i++) {
					try {
					if (String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("a") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("b") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("c") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("d") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("e") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("f") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("g") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("h") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("i") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("j") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("k") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("l") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("m") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("n") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("o") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("p") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("q") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("r") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("s") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("t") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("u") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("v") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("w") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("x") ||
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("y") || 
						String.valueOf((tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("z")) {
						aux.add(tbClientes.getItems().get(i));
					}
					}catch (Exception e) {
					}
				}
			} else {

				List<Character> listaPermitidos = new ArrayList<Character>();

				if (rbComeçando_A.isSelected()) {
					listaPermitidos.add('a');
				}

				if (rbComeçando_B.isSelected()) {
					listaPermitidos.add('b');
				}

				if (rbComeçando_C.isSelected()) {
					listaPermitidos.add('c');
				}

				if (rbComeçando_D.isSelected()) {
					listaPermitidos.add('d');
				}

				if (rbComeçando_E.isSelected()) {
					listaPermitidos.add('e');
				}

				if (rbComeçando_F.isSelected()) {
					listaPermitidos.add('f');
				}

				if (rbComeçando_G.isSelected()) {
					listaPermitidos.add('g');
				}

				if (rbComeçando_H.isSelected()) {
					listaPermitidos.add('h');
				}

				if (rbComeçando_I.isSelected()) {
					listaPermitidos.add('i');
				}

				if (rbComeçando_J.isSelected()) {
					listaPermitidos.add('j');
				}

				if (rbComeçando_K.isSelected()) {
					listaPermitidos.add('k');
				}

				if (rbComeçando_L.isSelected()) {
					listaPermitidos.add('l');
				}

				if (rbComeçando_M.isSelected()) {
					listaPermitidos.add('m');
				}

				if (rbComeçando_N.isSelected()) {
					listaPermitidos.add('n');
				}

				if (rbComeçando_O.isSelected()) {
					listaPermitidos.add('o');
				}

				if (rbComeçando_P.isSelected()) {
					listaPermitidos.add('p');
				}

				if (rbComeçando_Q.isSelected()) {
					listaPermitidos.add('q');
				}

				if (rbComeçando_R.isSelected()) {
					listaPermitidos.add('r');
				}

				if (rbComeçando_S.isSelected()) {
					listaPermitidos.add('s');
				}

				if (rbComeçando_T.isSelected()) {
					listaPermitidos.add('t');
				}

				if (rbComeçando_U.isSelected()) {
					listaPermitidos.add('u');
				}

				if (rbComeçando_V.isSelected()) {
					listaPermitidos.add('v');
				}

				if (rbComeçando_W.isSelected()) {
					listaPermitidos.add('w');
				}

				if (rbComeçando_X.isSelected()) {
					listaPermitidos.add('x');
				}

				if (rbComeçando_Y.isSelected()) {
					listaPermitidos.add('y');
				}

				if (rbComeçando_Z.isSelected()) {
					listaPermitidos.add('z');
				}
				
				for(int i = 0; i < tbClientes.getItems().size(); i++) {
					for (int o = 0; o <= listaPermitidos.size(); o++) {												
						if(o == listaPermitidos.size()) {
							aux.add(tbClientes.getItems().get(i));		
							break;
						}
						
						try {
							if(tbClientes.getItems().get(i).getNome().toLowerCase().charAt(0) == listaPermitidos.get(o).charValue()) {
								break;
							}
						}catch (Exception e) {
							break;
						}
					}
				}
			}
			
			
			for(int i = 0; i < tbClientes.getItems().size(); i++) {
				if((tbClientes.getItems().get(i).getPosicao() < Integer.parseInt(txtPorPosição_DE.getText())) 
						|| (tbClientes.getItems().get(i).getPosicao() > Integer.parseInt(txtPorPosição_A.getText()))) {
					aux.add(tbClientes.getItems().get(i));				
				}
			}	

			if(!txtPesquisar_CLIENTE.getText().isEmpty()) {
				for(int i = 0; i < tbClientes.getItems().size(); i++) {
					for(int o = 0; o < txtPesquisar_CLIENTE.getText().length(); o++) {
						if(!(tbClientes.getItems().get(i).getNome().length() <  txtPesquisar_CLIENTE.getText().length())) {
							if(!(tbClientes.getItems().get(i).getNome().toLowerCase().charAt(o) == txtPesquisar_CLIENTE.getText().toLowerCase().charAt(o))) {
								aux.add(tbClientes.getItems().get(i));	
							}
						}else {
							aux.add(tbClientes.getItems().get(i));	
						}
					}
				}				
			}
			
			tbClientes.getItems().removeAll(aux);			

		} else if (tabGrupos.isSelected()) {	
			
			initTable();
			
			List<Grupos> aux = new ArrayList<Grupos>();
			
			Date referencia = new Date();				
			
			if(rbEnviarPara_HOJE.isSelected()) {
				for(int i = 0; i < tbGrupos.getItems().size(); i++) {
					referencia = tbGrupos.getItems().get(i).getUltimoEnvio();
					if(PegarDatasUtil.toSqlDate(referencia).toString().equals(PegarDatasUtil.toSqlDate(PegarDatasUtil.getDate()).toString())) {
						aux.add(tbGrupos.getItems().get(i));
					}
				}				
			} else if (rbEnviarPara_SEMANA.isSelected()){
				for(int i = 0; i < tbGrupos.getItems().size(); i++) {
					if(tbGrupos.getItems().get(i).getUltimoEnvio().after(PegarDatasUtil.subtrairDataDe(7))) {
						aux.add(tbGrupos.getItems().get(i));
					}
				}	
		    } else if (rbEnviarPara_MES.isSelected()){
		    	for(int i = 0; i < tbGrupos.getItems().size(); i++) {
					if(tbGrupos.getItems().get(i).getUltimoEnvio().after(PegarDatasUtil.subtrairDataDe(30))) {
						aux.add(tbGrupos.getItems().get(i));
					}
				}	
			} else if (rbEnviarPara_NUNCA.isSelected()){
				for(int i = 0; i < tbGrupos.getItems().size(); i++) {
					if(tbGrupos.getItems().get(i).getUltimoEnvio().after(PegarDatasUtil.subtrairDataDe(3650))) {
						aux.add(tbGrupos.getItems().get(i));
					}
				}	
		    }
			
			if(rbComeçando_QUALQUER.isSelected()) {
								
			}else if(rbComeçando_ESP.isSelected()) {
				for (int i = 0; i < tbGrupos.getItems().size(); i++) {
					try {
					if (String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("a") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("b") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("c") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("d") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("e") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("f") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("g") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("h") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("i") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("j") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("k") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("l") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("m") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("n") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("o") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("p") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("q") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("r") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("s") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("t") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("u") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("v") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("w") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("x") ||
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("y") || 
						String.valueOf((tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0))).equals("z")) {
						aux.add(tbGrupos.getItems().get(i));
					}
					}catch (Exception e) {
					}
				}
			}else{
				List<Character> listaPermitidos = new ArrayList<Character>();

				if (rbComeçando_A.isSelected()) {
					listaPermitidos.add('a');
				}

				if (rbComeçando_B.isSelected()) {
					listaPermitidos.add('b');
				}

				if (rbComeçando_C.isSelected()) {
					listaPermitidos.add('c');
				}

				if (rbComeçando_D.isSelected()) {
					listaPermitidos.add('d');
				}

				if (rbComeçando_E.isSelected()) {
					listaPermitidos.add('e');
				}

				if (rbComeçando_F.isSelected()) {
					listaPermitidos.add('f');
				}

				if (rbComeçando_G.isSelected()) {
					listaPermitidos.add('g');
				}

				if (rbComeçando_H.isSelected()) {
					listaPermitidos.add('h');
				}

				if (rbComeçando_I.isSelected()) {
					listaPermitidos.add('i');
				}

				if (rbComeçando_J.isSelected()) {
					listaPermitidos.add('j');
				}

				if (rbComeçando_K.isSelected()) {
					listaPermitidos.add('k');
				}

				if (rbComeçando_L.isSelected()) {
					listaPermitidos.add('l');
				}

				if (rbComeçando_M.isSelected()) {
					listaPermitidos.add('m');
				}

				if (rbComeçando_N.isSelected()) {
					listaPermitidos.add('n');
				}

				if (rbComeçando_O.isSelected()) {
					listaPermitidos.add('o');
				}

				if (rbComeçando_P.isSelected()) {
					listaPermitidos.add('p');
				}

				if (rbComeçando_Q.isSelected()) {
					listaPermitidos.add('q');
				}

				if (rbComeçando_R.isSelected()) {
					listaPermitidos.add('r');
				}

				if (rbComeçando_S.isSelected()) {
					listaPermitidos.add('s');
				}

				if (rbComeçando_T.isSelected()) {
					listaPermitidos.add('t');
				}

				if (rbComeçando_U.isSelected()) {
					listaPermitidos.add('u');
				}

				if (rbComeçando_V.isSelected()) {
					listaPermitidos.add('v');
				}

				if (rbComeçando_W.isSelected()) {
					listaPermitidos.add('w');
				}

				if (rbComeçando_X.isSelected()) {
					listaPermitidos.add('x');
				}

				if (rbComeçando_Y.isSelected()) {
					listaPermitidos.add('y');
				}

				if (rbComeçando_Z.isSelected()) {
					listaPermitidos.add('z');
				}
								
				for(int i = 0; i < tbGrupos.getItems().size(); i++) {
					for (int o = 0; o <= listaPermitidos.size(); o++) {												
						if(o == listaPermitidos.size()) {
							aux.add(tbGrupos.getItems().get(i));		
							break;
						}
						
						try {
							if(tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(0) == listaPermitidos.get(o).charValue()) {
								break;
							}
						}catch (Exception e) {
							break;
						}
					}
				}
			}
			
			
			for(int i = 0; i < tbGrupos.getItems().size(); i++) {
				if((tbGrupos.getItems().get(i).getPosicao() < Integer.parseInt(txtPorPosição_DE.getText())) 
						|| (tbGrupos.getItems().get(i).getPosicao() > Integer.parseInt(txtPorPosição_A.getText()))) {
					aux.add(tbGrupos.getItems().get(i));				
				}
			}	
			
			if(!txtPesquisar_GRUPO.getText().isEmpty()) {
				for(int i = 0; i < tbGrupos.getItems().size(); i++) {
					for(int o = 0; o < txtPesquisar_GRUPO.getText().length(); o++) {
						if(!(tbGrupos.getItems().get(i).getNome().length() <  txtPesquisar_GRUPO.getText().length())) {
							if(!(tbGrupos.getItems().get(i).getNome().toLowerCase().charAt(o) == txtPesquisar_GRUPO.getText().toLowerCase().charAt(o))) {
								aux.add(tbGrupos.getItems().get(i));	
							}
						}else {
							aux.add(tbGrupos.getItems().get(i));	
						}
					}
				}				
			}
			
			tbGrupos.getItems().removeAll(aux);
			
		}

		List<Clientes> cli = new ArrayList<Clientes>();
		for(int i = 0; i < tbClientes.getItems().size(); i++) {
			for(int o = 0; o < tbCategoriasSelecionadas.getItems().size(); o++) {
				if(!(tbClientes.getItems().get(i).getCategoria() ==  null)) {
					if(tbClientes.getItems().get(i).getCategoria().equals(tbCategoriasSelecionadas.getItems().get(o).getNome())) {
						cli.add(tbClientes.getItems().get(i));
					} 
				}
			}
		}		
				
		List<Grupos> gru = new ArrayList<Grupos>();
		for(int i = 0; i < tbGrupos.getItems().size(); i++) {
			for(int o = 0; o < tbCategoriasSelecionadas.getItems().size(); o++) {
				if(!(tbGrupos.getItems().get(i).getCategoria() ==  null)) {
					if(tbGrupos.getItems().get(i).getCategoria().equals(tbCategoriasSelecionadas.getItems().get(o).getNome())) {
						gru.add(tbGrupos.getItems().get(i));
					}
				}
			}
		}
		
		if(tbCategoriasSelecionadas.getItems().size() > 0) {
			while(tbClientes.getItems().size() > 0) {tbClientes.getItems().remove(0);}			
			while(tbGrupos.getItems().size() > 0) {tbGrupos.getItems().remove(0);}
			
			tbClientes.getItems().addAll(cli);
			tbGrupos.getItems().addAll(gru);
		}
		
		for(int i = 0; i < tbGrupos.getItems().size(); i++) {
			tbGrupos.getItems().get(i).setPosicao(i);
		}
		
		for(int i = 0; i < tbClientes.getItems().size(); i++) {
			tbClientes.getItems().get(i).setPosicao(i);
		}
		
		contagemLinhas();
	}
	
	/***
	 * Eventos
	 *******************************************************************************************************************************************************************************************************************/

	@FXML
	public void padrao(MouseEvent event) {
		txtIniciar_Facebook.setText("");
		txtIniciar_Gmail.setText("");
		txtIniciar_Instagram.setText("");
		txtIniciar_Whatsapp.setText("");
		txtReiniciar_Facebook.setText("");
		txtReiniciar_Gmail.setText("");
		txtReiniciar_Instagram.setText("");
		txtReiniciar_Whatsapp.setText("");
	}
	
	@FXML
	public void disparar(MouseEvent event) {
		if (!cbPerfil.getValue().equals("Perfil")) {
			List<Clientes> Clientes = new ArrayList<Clientes>();
			List<Grupos> Grupos = new ArrayList<Grupos>();

			for (int i = 0; i < tbClientes.getItems().size(); i++) {
				Clientes c = new Clientes();

				c.setId(tbClientes.getItems().get(i).getId());
				c.setNome(tbClientes.getItems().get(i).getNome());

				Clientes.add(c);
			}

			for (int i = 0; i < tbGrupos.getItems().size(); i++) {
				Grupos g = new Grupos();

				g.setId(tbGrupos.getItems().get(i).getId());
				g.setNome(tbGrupos.getItems().get(i).getNome());

				Grupos.add(g);
			}

			if (tabClientes.isSelected()) {
				if (rbWhatsapp.isSelected()) {
					Disparo.disparar(Quem.CLIENTES, Modo.WHATSAPP, Grupos, Clientes, Mensagens,
							DaoProfiles.gerarTabelaPorTitulo(cbPerfil.getValue()).getFirst());
				} else if (rbFacebook.isSelected()) {
					Disparo.disparar(Quem.CLIENTES, Modo.FACEBOOK, Grupos, Clientes, Mensagens,
							DaoProfiles.gerarTabelaPorTitulo(cbPerfil.getValue()).getFirst());
				}else if (rbInstagram.isSelected()) {
					Disparo.disparar(Quem.CLIENTES, Modo.INSTAGRAM, Grupos, Clientes, Mensagens,
							DaoProfiles.gerarTabelaPorTitulo(cbPerfil.getValue()).getFirst());
				}else if (rbEmail.isSelected()) {
					Disparo.disparar(Quem.CLIENTES, Modo.EMAIL, Grupos, Clientes, Mensagens,
							DaoProfiles.gerarTabelaPorTitulo(cbPerfil.getValue()).getFirst());
				}
			} else {
				if (rbWhatsapp.isSelected()) {
					Disparo.disparar(Quem.GRUPOS, Modo.WHATSAPP, Grupos, Clientes, Mensagens,
							DaoProfiles.gerarTabelaPorTitulo(cbPerfil.getValue()).getFirst());
				} else if (rbFacebook.isSelected()) {
					Disparo.disparar(Quem.GRUPOS, Modo.FACEBOOK, Grupos, Clientes, Mensagens,
							DaoProfiles.gerarTabelaPorTitulo(cbPerfil.getValue()).getFirst());
				}else if (rbInstagram.isSelected()) {
					JOptionPane.showMessageDialog(null, "Para o Instagram mude para clientes.");
				}else if (rbEmail.isSelected()) {
					JOptionPane.showMessageDialog(null, "Para o gmail mude para clientes.");
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um perfil.");
		}
	}

	@FXML
	public void voltar(MouseEvent event) {
		Main.setScreen(Tela.PRINCIPAL);
	}

	@FXML
	public void buscar(KeyEvent event) {		
		filtro();
	}

	@FXML
	public void aplicar(MouseEvent event) {
		filtro();
	}

	@FXML
	public void tirarTodos(ActionEvent event) {
		rbComeçando_A.setSelected(false);
		rbComeçando_B.setSelected(false);
		rbComeçando_C.setSelected(false);
		rbComeçando_D.setSelected(false);
		rbComeçando_E.setSelected(false);
		rbComeçando_F.setSelected(false);
		rbComeçando_G.setSelected(false);
		rbComeçando_H.setSelected(false);
		rbComeçando_I.setSelected(false);
		rbComeçando_J.setSelected(false);
		rbComeçando_K.setSelected(false);
		rbComeçando_L.setSelected(false);
		rbComeçando_M.setSelected(false);
		rbComeçando_N.setSelected(false);
		rbComeçando_O.setSelected(false);
		rbComeçando_P.setSelected(false);
		rbComeçando_Q.setSelected(false);
		rbComeçando_R.setSelected(false);
		rbComeçando_S.setSelected(false);
		rbComeçando_T.setSelected(false);
		rbComeçando_U.setSelected(false);
		rbComeçando_V.setSelected(false);
		rbComeçando_W.setSelected(false);
		rbComeçando_X.setSelected(false);
		rbComeçando_Y.setSelected(false);
		rbComeçando_Z.setSelected(false);
		rbComeçando_ESP.setSelected(false);
	}

	@FXML
	public void tirar(ActionEvent event) {
		rbComeçando_QUALQUER.setSelected(false);
		rbComeçando_ESP.setSelected(false);
	}

	@FXML
	public void especial(ActionEvent event) {
		rbComeçando_QUALQUER.setSelected(false);
		rbComeçando_A.setSelected(false);
		rbComeçando_B.setSelected(false);
		rbComeçando_C.setSelected(false);
		rbComeçando_D.setSelected(false);
		rbComeçando_E.setSelected(false);
		rbComeçando_F.setSelected(false);
		rbComeçando_G.setSelected(false);
		rbComeçando_H.setSelected(false);
		rbComeçando_I.setSelected(false);
		rbComeçando_J.setSelected(false);
		rbComeçando_K.setSelected(false);
		rbComeçando_L.setSelected(false);
		rbComeçando_M.setSelected(false);
		rbComeçando_N.setSelected(false);
		rbComeçando_O.setSelected(false);
		rbComeçando_P.setSelected(false);
		rbComeçando_Q.setSelected(false);
		rbComeçando_R.setSelected(false);
		rbComeçando_S.setSelected(false);
		rbComeçando_T.setSelected(false);
		rbComeçando_U.setSelected(false);
		rbComeçando_V.setSelected(false);
		rbComeçando_W.setSelected(false);
		rbComeçando_X.setSelected(false);
		rbComeçando_Y.setSelected(false);
		rbComeçando_Z.setSelected(false);
	}

	@FXML
	public void InstanciarPerfil(MouseEvent event) {

		try {
			while (cbPerfil.getItems().size() != 1) {
				cbPerfil.getItems().remove(cbPerfil.getItems().size() - 1);
			}
		} catch (Exception e) {
		} finally {
			for (int i = 0; i < DaoProfiles.gerarTabela().size(); i++) {
				cbPerfil.getItems().add(DaoProfiles.gerarTabela().get(i).getTitulo());
			}
		}

	}

	@FXML
	public void adicionarMensagem(MouseEvent event) {
		giroParaDireita(btnMandarMensagem, imgMandarMensagem);
		mandarMensagem();
	}

	@FXML
	public void habilitarIdaMensagem(MouseEvent event) {
		btnMandarMensagem.setDisable(false);
	}

	@FXML
	public void retirarMensagem(MouseEvent event) {
		giroParaEsquerda(btnTirarMensagem, imgTirarMensagem);
		tirarMensagem();
	}

	@FXML
	public void habilitarRetiradaMensagem(MouseEvent event) {
		btnTirarMensagem.setDisable(false);
	}

	@FXML
	public void adicionarCategoria(MouseEvent event) {
		giroParaDireita(btnMandarCategoria, imgMandarCategoria);
		mandarCategoria();
	}

	@FXML
	public void habilitarIdaCategoria(MouseEvent event) {
		btnMandarCategoria.setDisable(false);
	}

	@FXML
	public void retirarCategoria(MouseEvent event) {
		giroParaEsquerda(btnTirarCategoria, imgTirarCategoria);
		tirarCategoria();
	}

	@FXML
	public void habilitarRetiradaCategoria(MouseEvent event) {
		btnTirarCategoria.setDisable(false);
	}
	
	public void AplicarConfiguracao(MouseEvent event) {
		
		try {
		
		DaoConfiguracao.editarConfiguracao(txtFirefox.getText(), txtGeckoDriver.getText(), "", "", "", "", 
				"", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", 
				1);
		
		DaoConfiguracao.editarConfiguracao("", "", 
				String.valueOf(Integer.parseInt(txtIniciar_Whatsapp.getText()) * 60000), 
				String.valueOf(Integer.parseInt(txtReiniciar_Whatsapp.getText()) * 1000), "", "", 
				"", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", 
				2);
		
		DaoConfiguracao.editarConfiguracao("", "", String.valueOf(Integer.parseInt(txtIniciar_Facebook.getText()) * 60000), 
				String.valueOf(Integer.parseInt(txtReiniciar_Facebook.getText()) * 1000), "", "", 
				"", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", 
				3);
		
		DaoConfiguracao.editarConfiguracao("", "", String.valueOf(Integer.parseInt(txtIniciar_Instagram.getText()) * 60000), 
				String.valueOf(Integer.parseInt(txtReiniciar_Instagram.getText()) * 1000), "", "", 
				"", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", 
				4);
		
		DaoConfiguracao.editarConfiguracao("", "", String.valueOf(Integer.parseInt(txtIniciar_Gmail.getText()) * 60000), 
				String.valueOf(Integer.parseInt(txtReiniciar_Gmail.getText()) * 1000), "", "", 
				"", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", "", 
				"", "", "", "", "", "", "", "", 
				5);

		Alerta.alertaSucesso("Configuração", genero.A, acao.EDITAR);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Somente numeros nos campos de configuração de tempo.\n"
											  + "Campos de configuração de tempo não pode ser vazio.");
		}
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
	
	@FXML
	public void emSegundos(MouseEvent event) {
		Alerta.alertaTempo(Tempo.SEGUNDOS);
	}
	
	@FXML
	public void emMinutos(MouseEvent event) {
		Alerta.alertaTempo(Tempo.MINUTOS);
	}

}
