package app;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public abstract class Pessoa {
	protected String nome, email, login, senha;
	static Map< String, Pessoa > pessoas = new  HashMap< String, Pessoa >();
	private ArrayList<Integer> keys = new ArrayList<Integer>();
	
	public Pessoa(String login, String senha, String nome, String email){
		this.login = login;
		this.senha = senha;
		this.nome = nome;
		this.email = email;
	}

	public Turma acessaTurma() {
		this.mostraTurmas();
		int escolha;
		Turma t = new Turma();
		String nomeTurma = JOptionPane.showInputDialog("Qual turma?\n");

		int key = this.getKeyOfTurma(nomeTurma);

		while(key == -1) {
			JOptionPane.showMessageDialog(null, "Turma invalida\n");
			escolha = Integer.parseInt(JOptionPane.showInputDialog("1 Escolher nova turma\n2 Visualizar novamente as turmas\n-1 Sair"));
			if(escolha == 1 || escolha == 2) {
				if(escolha == 2) this.mostraTurmas();
				nomeTurma = JOptionPane.showInputDialog("Qual turma?\n");
				key = this.getKeyOfTurma(nomeTurma);
			}
			else return null;
		}
		
		return t.getTurma(key);
	}
	
	public void mostraTurmas() {
		Iterator<Integer> i = keys.iterator();
		Turma t = new Turma();
		String lista = "";

		while(i.hasNext()) 
			lista += t.getTurma(i.next()).getNome() + "\n";

		if(lista.equals("")) JOptionPane.showMessageDialog(null, "Sem turmas disponiveis\n");
		else JOptionPane.showMessageDialog(null, "Turmas disponiveis:\n" + lista);
	}
	

	public Integer getKeyOfTurma(String turmaProcurada) {
		Iterator<Integer> i = keys.iterator();
		Turma t = new Turma();
		while(i.hasNext()) {
			int key = i.next();
			if(t.getTurma(key).getNome().equals(turmaProcurada)) 
				return key;
		}
		return -1;
	}

	public void addKey(int key) {
		this.keys.add(key);
	}
	
	public void addPessoa(Pessoa p) {
		pessoas.put(p.getLogin(), p);
	}
	
	public void updatePessoas(Pessoa p) {
		pessoas.replace(login,p);
	}
	
	public Pessoa getPessoa(String login) {
		return pessoas.get(login);
	}
	
	public boolean hasLoginAndSenha(String login, String senha) {
		return (pessoas.containsKey(login) && pessoas.get(login).senha.equals(senha));
	}

	public boolean hasLogin(String login) {
		return pessoas.containsKey(login);
	}

	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}


