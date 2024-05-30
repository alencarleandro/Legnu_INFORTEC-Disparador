package br.com.legnu_propagar.util.busca;

import java.util.Date;

import javax.swing.JOptionPane;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PegarDatasUtil {

	private static Calendar date;

	public static Date getDate() {
		date = Calendar.getInstance();
		return date.getTime();
	}

	public static int getHour() {
		date = Calendar.getInstance();
		return date.get(Calendar.HOUR_OF_DAY);
	}

	public static int getDay() {
		date = Calendar.getInstance();
		return date.get(Calendar.DAY_OF_MONTH);
	}

	public static int getDayOfYear() {
		date = Calendar.getInstance();
		return date.get(Calendar.DAY_OF_YEAR);
	}

	public static int getMount() {
		date = Calendar.getInstance();
		return date.get(Calendar.MONTH);
	}

	public static String mesDuasCasas() {
		switch (PegarDatasUtil.getMount()) {
		case 0:
			return "01";
		case 1:
			return "02";
		case 2:
			return "03";
		case 3:
			return "04";
		case 4:
			return "05";
		case 5:
			return "06";
		case 6:
			return "07";
		case 7:
			return "08";
		case 8:
			return "09";
		case 9:
			return "10";
		case 10:
			return "11";
		case 11:
			return "12";
		default:
			return "01";
		}
	}

	public static int getYear() {
		date = Calendar.getInstance();
		return date.get(Calendar.YEAR);
	}

	public static java.sql.Date proximoVale() {
		date = Calendar.getInstance();
		date.add(Calendar.MONTH, 1);
		date.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), 7);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date dSqt = new java.sql.Date(date.getTime().getTime());
		df.format(dSqt);

		return dSqt;
	}

	public static Date pegarDataDepoisDe(int dias) {
		date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, dias);
		return date.getTime();
	}

	public static Date subtrairDataDe(int dias) {
		date = Calendar.getInstance();
		date.add(Calendar.DAY_OF_YEAR, (-dias));
		return date.getTime();
	}

	public static java.sql.Date toSqlDate(Date data) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date dSqt = new java.sql.Date(data.getTime());
		df.format(dSqt);

		return dSqt;
	}

	public static java.sql.Date dataPadrao() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date dSqt = new java.sql.Date(0);
		df.format(dSqt);

		return dSqt;
	}

	public static int mandarDateToString(escolhas escolha, String recive) {
		switch (escolha) {
		case YEAR:
			return Integer.parseInt(recive.split("-")[0]);
		case MONTH:
			return Integer.parseInt(recive.split("-")[1]);
		case DAY:
			return Integer.parseInt(recive.split("-")[2]);
		default:
			return 0;
		}
	}

	public static enum escolhas {
		YEAR, MONTH, DAY;
	}

	public static int diferencaEmDiasDe(Calendar primeiraData, Calendar segundaData) {
		return (primeiraData.DAY_OF_YEAR - segundaData.DAY_OF_YEAR);
	}

	public static boolean eDepois(Date primeiraData, Date segundaData) {
		if (primeiraData.after(segundaData)) {
			return true;
		} else {
			return false;
		}
	}
}
