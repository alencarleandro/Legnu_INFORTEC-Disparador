package br.com.legnu_propagar.util;

import javafx.scene.control.Alert;

public class Alerta {

	public static enum genero {
		A, O
	};

	public static enum acao {
		ADICIONAR, EDITAR, REMOVER
	};
	
	public static enum Tempo {
		MINUTOS, SEGUNDOS
	};

	public static void alertaSucesso(String nomeDaAdicao, genero g, acao ac) {

		Alert a = new Alert(Alert.AlertType.WARNING);
		a.setTitle("Atenção");
		a.setHeaderText(((ac == acao.ADICIONAR) ? "Adicionar" : ((ac == acao.EDITAR) ? "Editar" : "Remover")));
		a.setContentText(
				nomeDaAdicao + ((ac == acao.ADICIONAR) ? " adicionad" : ((ac == acao.EDITAR) ? " editad" : " removid"))
						+ ((g == genero.A) ? "a" : "o")
						+ ((ac == acao.REMOVER) ? " junta de todas suas dependencias e dados vinculados a ele(a)" : "")
						+ " com Sucesso!");
		a.show();
	}

	public static void alertaVazio(String campo) {

		Alert a = new Alert(Alert.AlertType.WARNING);
		a.setTitle("Atenção");
		a.setHeaderText("Adicionar");
		a.setContentText("O(s) campo(s) (" + campo + ") não pode(m) ser vazio(s).");
		a.show();
	}

	public static void alertaEscolhaCombobox(String nome) {
		Alert a = new Alert(Alert.AlertType.WARNING);
		a.setTitle("Atenção");
		a.setHeaderText("Escolha");
		a.setContentText("Alternativa " + nome + " invalida.");
		a.show();
	}

	public static void alertaIniciandoDisparo() {
		Alert a = new Alert(Alert.AlertType.WARNING);
		a.setTitle("Atenção");
		a.setHeaderText("Disparo Iniciado");
		a.setContentText("Clicke no OK e aguarde.");
		a.show();
	}

	public static void alertaDisparoFinalizado() {
		Alert a = new Alert(Alert.AlertType.WARNING);
		a.setTitle("Atenção");
		a.setHeaderText("Disparo Finalizado");
		a.setContentText("Disparo concluido com sucesso.");
		a.show();
	}
	
	public static void alertaTempo(Tempo t) {
		Alert a = new Alert(Alert.AlertType.WARNING);
		a.setTitle("Atenção");
		a.setHeaderText("Calibrar");
		a.setContentText("O numero inserido neste campo sera interpretado em " + ((t == Tempo.MINUTOS) ? "minutos." : "segundos."));
		a.show();
	}
	

}
