package app;

public class Dissertativa extends Questao {

	public Dissertativa(String enunciado, String resposta){
		super("Dissertativa", enunciado, resposta);
	}
	
	@Override
	public String toString(){
		return getTipo() + ";" + getEnunciado() + ";";
	}
	
	@Override
	public String toStringResposta() {
		return toString() + ";" +"Resposta: "+ getResposta() + ";";
	}
}

