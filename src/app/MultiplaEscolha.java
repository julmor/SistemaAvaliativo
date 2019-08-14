package app;
import java.util.Scanner;

import app.Alternativa;
public class MultiplaEscolha extends Questao {
	Scanner sc = new Scanner(System.in);
	private Alternativa [] alternativas = new Alternativa [5];
	private char [] letras = {'a','b','c','d','e'};
	

	// tratar caso a resposta não seja uma alternativa no próprio programa principal
	public MultiplaEscolha(String enunciado,String [] alternativas, char correta){
		super("Multipla Escolha", enunciado, null);
		this.alternativas = new Alternativa[alternativas.length];
		for(int i=0; i<this.alternativas.length; i++) {
			if(this.letras[i]==correta) {
				this.alternativas[i]= new Alternativa(true, alternativas[i]);
				super.setResposta(alternativas[i]);
			}
			else {
				this.alternativas[i]= new Alternativa(false, alternativas[i]);
			}
		}
		
	}
	
	
	@Override
	public String toString(){
		return getTipo() + ";" + getEnunciado() + ";" + criarStringAlternativas();
	}
	
	@Override
	public String toStringResposta() {
		return toString() + ";" + "Resposta: " + getResposta() + ";";
	}
	
	private String criarStringAlternativas() {
		String s = "";
		for(int i=0; i<alternativas.length;i++) {
			s=s+alternativas[i].getAlternativa() + ";";
		}
		return s;
	}

	@Override
	public String getAlternativas() {
		String alters = "";
		for(int i =0; i<alternativas.length; i++) {
			alters = alters + letras[i] + "-) " + alternativas[i].alternativa + "\n";
		}
		return alters;
	}
	
}


