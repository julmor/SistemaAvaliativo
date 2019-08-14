package exceptions;

@SuppressWarnings("serial")
public class LimiteQuestoesException extends Exception{

	public LimiteQuestoesException(int tamanho){
		super("Valor máximo de questões para o questionário já atingido: " + tamanho + " questões");
	}
	
}

