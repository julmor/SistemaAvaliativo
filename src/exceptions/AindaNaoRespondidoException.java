package exceptions;

@SuppressWarnings("serial")
public class AindaNaoRespondidoException extends Exception{
	
	public AindaNaoRespondidoException() {
		super("Ainda não respondido, impossível mostrar nota");
	}
	
}

