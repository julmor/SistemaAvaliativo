package app;

import app.Questao;
import exceptions.*;
import app.*;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;


public class Questionario {
	public int id;
	public float resultado;
	private int contador;
	public String questao; 
	public String turma;  //guarda se o aluno ja tiver respondido ao questionario
	private int indiceAtual=0; //acompanha o quanto de questoes foram adicionadas ao questionario
	protected int tamanho; //tamanho do questionario
	public Questao questoes [];
	protected String nome;
	private float valores[]; //peso de cada questao
	private LocalDate prazo; // tempo que o questionario vai poder ser respondido
	private String loginOwner;
	
	//cria questionario com tamanho qualquer para uma turma especifica
	public Questionario(String turma, int tamanho, LocalDate prazo, String nome, String login){
		//this.resposta = resposta;
		this.loginOwner = login;
		this.turma = turma;
		this.tamanho=tamanho;
		this.questoes= new Questao[tamanho];
		this.valores=new float[tamanho];
		this.setPrazo(prazo);
		this.nome=nome;
	}
	
	//adicionar uma questao envolve a mesma e seu peso
	public void adicionaQuestaoNoQuestionario(Questao questao, float valor) {
		try {
			if(indiceAtual==this.tamanho)
				throw new LimiteQuestoesException(tamanho);
			questoes[indiceAtual]=questao;
			valores[indiceAtual]=valor;
			indiceAtual++;
			id = contador++;
		} catch (LimiteQuestoesException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	public void removeQuestaoNoQuestionario(int questao) { //passa o numero da questao nao o indice do vetor
		try {
			if(questao<=0||questao>this.tamanho)
				throw new IndiceInexistenteException(questao);
			else if(questoes[questao]==null) 
				throw new IndiceInexistenteException(questao);
			int i;
			this.questoes[questao-1]=null;
			for(i=questao-1; this.questoes[i+1]!=null; i++) { //permite remocao de questao mesmo se o questionario nao tiver sido finalizado
				this.questoes[i]=this.questoes[i+1];
			}
			questoes[i]=null;
		} catch (IndiceInexistenteException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	//A funcao abaixo eh sempre chamada quando o aluno terminar de responder o questionario
	public float setPontuacao(String[] a) { //a eh um Vetor generico que contem respostas dos alunos;
		float pontuacao=0f; 
		float valorTotal=0;
		for(int i=0; i<a.length; i++){
			if(questoes[i].getResposta().equals(a[i])) {
				pontuacao=pontuacao+valores[i];
			}
			valorTotal=valorTotal+valores[i];
		}
		this.resultado = (pontuacao*100)/valorTotal;
		return pontuacao;
	}
	
	public String getOwner() {
		return this.loginOwner;
	}

	public LocalDate getPrazo() {
		return prazo;
	}

	public void setPrazo(LocalDate prazo) {
		this.prazo = prazo;
	}

}

