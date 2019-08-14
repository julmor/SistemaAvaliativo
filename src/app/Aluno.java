package app;

import java.time.LocalDate;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;

import app.*;
import javax.swing.JOptionPane;

public class Aluno extends Pessoa {
	protected HashMap<String, Float> resultados = new HashMap<String, Float>();
	private Set<Integer> questionariosRespondidos = new HashSet<Integer>();
	private Leitor leitura = new Leitor(); //para ler o arquivo do questionario e mostrar ao aluno
	
	public Aluno(String login, String senha, String nome, String email){
		super(login,senha,nome, email);
		pessoas.put(login, this);
	}
	
	
	public boolean respondeuQuestionario(int key) {
		return this.questionariosRespondidos.contains(key);
	}
	
	public Questionario acessaQuestionario(Turma t)  {
		int escolha,id;

		JOptionPane.showMessageDialog(null, "Mostrando questionarios nao respondidos da turma\n");
		t.mostraQuestionariosNaoRespondidos(this);
		
		id = Integer.parseInt(JOptionPane.showInputDialog("Escolha o questionario: (digite o codigo que apareceu ao lado do nome)"));
		
		while(!t.hasQuestionario(id)) {
			JOptionPane.showMessageDialog(null, "Questionario invalida\n");
			escolha = Integer.parseInt(JOptionPane.showInputDialog("1 Escolha um questionario\n2 Visualizar novamente os questionarios da turma\n3 Escolher outra turma\n-1 Sair"));
			if(escolha == 1 || escolha == 2) {
				if(escolha == 2) t.mostraQuestionariosNaoRespondidos(this);
				id = Integer.parseInt(JOptionPane.showInputDialog("Escolha o questionario: (digite o codigo que apareceu ao lado do nome"));
			}
			else if(escolha == 3) t = this.acessaTurma();
			
			if(escolha == -1 || t == null) return null;
		}
		
		return t.getQuestionario(id);
	}
	
	public void menuResponderQuestionario() {
		Turma t = null;
		Questionario q = null;
		
		t = this.acessaTurma();
		if(t == null) return;

		q = this.acessaQuestionario(t);
		if(q == null) return;
		
		q = this.responderQuestionario(q);
		
		if(q != null) {
			Professor prof = (Professor)pessoas.get(q.getOwner());
			prof.addResultadoAluno(q.id, "Aluno: " + this.nome + " | Nota: " + q.resultado + "\n");
			this.questionariosRespondidos.add(q.id);
		}
	}
	
	private Questionario responderQuestionario(Questionario o) {
		int i = verificaTempo(o);
		if(i==0) {
			JOptionPane.showInputDialog("Questionario nao esta mais aceitando respostas \n");
			return null;
		}
		
		leitura.leitura(o.nome);
		leitura.abrirArquivo(o.nome);
		String respostas[] = new String[o.tamanho];

		int contador=0;
		boolean[] respondidas = new boolean[o.tamanho];
		int selecao;
		
		do {
			do {
				selecao = Integer.parseInt(JOptionPane.showInputDialog(null, "Selecione uma questao para responder\nQuestionario tem " + (o.tamanho) + " questï¿½es\n*Sï¿½ digite letras minï¿½sculas e sem acento"))-1;
			} while(selecao<0||selecao>=o.tamanho);
			
			if(o.questoes[selecao] instanceof MultiplaEscolha) {
				respostas[selecao]=JOptionPane.showInputDialog(o.questoes[selecao].getEnunciado()+ "\n" + o.questoes[selecao].getAlternativas());
			}
			else {
				respostas[selecao]=JOptionPane.showInputDialog(o.questoes[selecao].getEnunciado());
			}
			if(respondidas[selecao]==false) {
				respondidas[selecao]=true;
				contador++;
			}
			System.out.println(respostas[selecao]);
		}while(contador!=o.tamanho);
		
		float resultado = o.setPontuacao(respostas);
		
		JOptionPane.showMessageDialog(null,"Questionario " + o.nome + "\nPontuacao: " + resultado);
		
		updatePessoas(this);
		
		this.resultados.put(o.nome, o.setPontuacao(respostas));
		
		String resposta = JOptionPane.showInputDialog("Você deseja visualizar as respostas do questionario?");
		if(resposta.equals("Sim") || resposta.equals("sim") || resposta.equals("SIM")) {
			leitura.leitura(o.nome+"Respostas");
			leitura.abrirArquivo(o.nome+"Respostas");
		}
		
		return o;
	}

	//verifica se o questionario ainda pode ser respondido
	private int verificaTempo(Questionario o) {
		LocalDate h = java.time.LocalDate.now();
		if (h.isAfter(o.getPrazo())) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public void visualizarNotas() {
		String output = "";
		for(Map.Entry<String, Float> entry : resultados.entrySet()) 
			output += "Questionario: " + entry.getKey() + " | Notas: " + entry.getValue() + "\n";
		
		if(output != "") JOptionPane.showMessageDialog(null,output);
		else JOptionPane.showMessageDialog(null, "Nenhum questionario respondido\n");

	}
	
}

