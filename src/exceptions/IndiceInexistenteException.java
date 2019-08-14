package exceptions;

@SuppressWarnings("serial")
public class IndiceInexistenteException extends Exception{

	public IndiceInexistenteException(int questao) {
		super("A questão " + questao + "não existe");
	}
	
}

