package app;

public class Alternativa {
	private boolean resp;
	protected String alternativa;

	public Alternativa(boolean resp, String alternativa) {
		this.setAlternativa(alternativa);
		this.setResp(resp);
	}

	public boolean isResp() {
		return resp;
	}

	public void setResp(boolean resp) {
		this.resp = resp;
	}

	public String getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(String alternativa) {
		this.alternativa = alternativa;
	}
}

