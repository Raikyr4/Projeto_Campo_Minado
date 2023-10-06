package raiky.cm.modelo;


import java.util.ArrayList;
import java.util.List;

public class Campo {

	private final int linha;
	private final int coluna;
	
	private boolean aberto = false;
	private boolean minado = false;
	private boolean marcado = false;
	
	private List<Campo> vizinhos = new ArrayList<>();
	private List<CampoObs> observers = new ArrayList<>();
	//private List<BiConsumer<Campo, CampoObs>> Observadores = new ArrayList<>();
	
	
	Campo(int linha, int coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}
	
	public void registrarOBS(CampoObs O) {
		observers.add(O);
	}
	
	private void notificarOBS(CampoEvento E) {
		observers.stream().forEach(o->o.eventoOcorreu(this, E));
	}
	
	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = linha != vizinho.linha;
		boolean colunaDiferente = coluna != vizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - vizinho.linha);
		int deltaColuna = Math.abs(coluna - vizinho.coluna);
		int detalGeral = deltaColuna + deltaLinha;
		
		if(detalGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if(detalGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}
	
	public void alternarMarcacao() {
		if(aberto)return;
		if(!aberto) {
			marcado = !marcado;
		}
		if(marcado) {
			notificarOBS(CampoEvento.MARCAR);
		}else {
			notificarOBS(CampoEvento.DESMARCAR);
		}
	}
	
	public boolean abrir() {
		
		if(!aberto && !marcado) {
			aberto = true;
			
			if(minado) {
				notificarOBS(CampoEvento.EXPLODIR);
				return true;
			}
			
			setAberto(true);
			notificarOBS(CampoEvento.ABRIR);
			
			if(vizinhancaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			
			return true;
		} else {
			return false;			
		}
	}
	
	public boolean vizinhancaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}
	
	void minar() {
		minado = true;
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
		
		if(aberto) {
			notificarOBS(CampoEvento.ABRIR);
		}
	}

	public boolean isAberto() {
		return aberto;
	}

	public boolean isFechado() {
		return !isAberto();
	}

	public int getLinha() {
		return linha;
	}

	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	public int minasNaVizinhanca() {
		return (int)vizinhos.stream().filter(v -> v.minado).count();
	}
	
	public void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
		notificarOBS(CampoEvento.REINICIAR);
	}
	

}