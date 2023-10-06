package raiky.cm.visao;

import raiky.cm.modelo.Tabuleiro;

public class Aplicacao {

	public static void main(String[] args) {
		
		Tabuleiro tab = new Tabuleiro(6,6,3);
		new TabuleiroConsole(tab);
	}
}
