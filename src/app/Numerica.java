package app;

public class Numerica extends Questao {
	
	public Numerica(String enunciado, String resposta){
		super("Num�rica",enunciado,resposta);
	}
	
	@Override
	public String toString(){
		return getTipo() + ";" + getEnunciado() + ";";
		  
	}
	
	@Override
	public String toStringResposta() {
		return toString() + ";" +"Resposta: " + getResposta() + ";";
	}
}


