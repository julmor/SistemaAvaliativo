package app;

import java.util.Map;

import javax.swing.JOptionPane;

import java.util.HashMap;

public class Turma {
	public String nome;
	static int contador;
	private int key;
	private Map<Integer, Questionario> questionarios = new HashMap<Integer, Questionario>();
	static Map<Integer, Turma> turmas = new HashMap<Integer, Turma>();
	
	
	public boolean hasQuestionario(Integer key) {
		return questionarios.containsKey(key);
	}
	
	public void mostraQuestionarios(Professor p) {
		int total = 0;
		for(Map.Entry<Integer,Questionario> entry : questionarios.entrySet()) {
			JOptionPane.showMessageDialog(null,entry.getValue().nome + " " +entry.getKey() + '\n');
			total++;
		}

		if(total == 0)
			JOptionPane.showMessageDialog(null, "Nenhum questinário nessa matéria\n");

	}
	
	public void mostraQuestionariosNaoRespondidos(Aluno p) {
		int naoRespondidos = 0;
		int total = 0;
		for(Map.Entry<Integer,Questionario> entry : questionarios.entrySet()) {
			if(!p.respondeuQuestionario(entry.getKey())) {
				JOptionPane.showMessageDialog(null,entry.getValue().nome + " " +entry.getKey() + '\n');
				naoRespondidos++;
			}
			total++;
		}

		if(naoRespondidos == 0)
			JOptionPane.showMessageDialog(null, "Nenhum questionario nao respondido dessa materia\n");
		
		JOptionPane.showMessageDialog(null, "Total de questionarios na materia: " + total + "\nTotal respondidos: " + (total-naoRespondidos) + "\n");
	}
	
	public void addQuestionario(Questionario q) {
		questionarios.put(q.id,q);
	}
	
	public void updateTurma(Integer key, Turma t) {
		turmas.replace(key,t);
	}
	
	public boolean hasTurma(Integer key) {
		return turmas.containsKey(key);
	}
	
	public Turma addTurma(Turma t) {
		t.key = contador++;
		turmas.put(t.key,t);
		return t;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public Questionario getQuestionario(Integer key) {
		return this.questionarios.get(key);
	}
	
	public Turma getTurma(Integer key) {
		return turmas.get(key);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
