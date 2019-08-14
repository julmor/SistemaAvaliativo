package app;

public abstract class Questao implements ItoString, ItoStringResposta {
	private String tipo;
	private String enunciado;
	private String resposta;
	
	public Questao(String tipo, String enunciado, String resposta){
		this.tipo = tipo;
		this.enunciado = enunciado;
		this.resposta = resposta;
	}
	
	@Override
	public abstract String toString(); 
	
	@Override
	public abstract String toStringResposta();
	
	public void setTipo(String tipo){
		this.tipo = tipo;
	}
	
	public String getTipo(){
		return tipo;
	}
	
	public void setEnunciado(String enunciado){
		this.enunciado = enunciado;
	}
	public String getEnunciado(){
		return enunciado;
	}
	
	public void setResposta(String resposta){
		this.resposta = resposta;
	}
	
	public String getResposta(){
		return resposta;
	}
	
	public String getAlternativas() {
		return "0";
	}
}


