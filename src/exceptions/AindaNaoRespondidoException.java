package exceptions;

@SuppressWarnings("serial")
public class AindaNaoRespondidoException extends Exception{
	
	public AindaNaoRespondidoException() {
		super("Ainda n�o respondido, imposs�vel mostrar nota");
	}
	
}

