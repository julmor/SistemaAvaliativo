package exceptions;

@SuppressWarnings("serial")
public class LimiteQuestoesException extends Exception{

	public LimiteQuestoesException(int tamanho){
		super("Valor m�ximo de quest�es para o question�rio j� atingido: " + tamanho + " quest�es");
	}
	
}

