package principal;

import javax.swing.JOptionPane;
import app.*;

public class Principal {

	static Professor adm = new Professor("admin", "123", "admin", "123", "admin");
	
	public static void main(String[] args) {
		int escolha;
		while(true) {
			escolha = Integer.parseInt(JOptionPane.showInputDialog("Faca o login \n" + "1. Fazer Login\n" + "2. Criar novo usuario\n" + "-1. Sair"));

			switch (escolha) {
				case 1:
					fazerLogin();
					break;

				case 2:
					criarNovoUsuario();
					break;
					
				case -1:
					return;

				default:
					System.out.println("Opcao invalida!");
					break;
			}
		}
	}
	
	public static void menuProfessor(String login) {
		int escolha = -1;
		Professor p = (Professor)adm.getPessoa(login);

		do {
			escolha = Integer.parseInt(JOptionPane.showInputDialog("1 Criar questionario \n2 Adicionar turma\n3 Adicionar aluno em turma\n4 Ver pontuacao de questionario\n-1 Sair"));
			switch(escolha) {
				case 1: 
					p.menuCriaQuestionario();
					p.updatePessoas(p);
					break;
				case 2:
					p.menuAdicionaTurma();
					p.updatePessoas(p);
					break;
				case 3:
					p.menuAdicionaAlunoEmTurma();
					p.updatePessoas(p);
					break;
				case 4:
					p.verPontuacaoAlunos();
					p.updatePessoas(p);
				case -1:
					return;
				default:
					JOptionPane.showMessageDialog(null,"Opcao invalida\n");
			}
		} while(escolha != -1);

		p.updatePessoas(p);
	}
	
	public static void menuAluno(String login) {
		Aluno p = (Aluno)adm.getPessoa(login);
		int escolha;
		
		do {
			escolha = Integer.parseInt(JOptionPane.showInputDialog("1 Reponder questionario \n2 Visualizar notas\n-1 Sair"));
			switch(escolha) {
				case 1:
					p.menuResponderQuestionario();
					p.updatePessoas(p);
					break;
					
				case 2:
					p.visualizarNotas();
					p.updatePessoas(p);
					break;

				case -1:
					return;
			}
			
		} while(escolha != -1);
		p.updatePessoas(p);
	}
	
	public static void fazerLogin() {
		String login, senha;
		login = JOptionPane.showInputDialog("Login: ");
		senha = JOptionPane.showInputDialog("Senha: ");
		int escolha;
		
		while(!adm.hasLoginAndSenha(login, senha)) {
			JOptionPane.showMessageDialog(null,"login invalido\n");
			escolha = Integer.parseInt(JOptionPane.showInputDialog("1 Tentar novamente\n-1 Sair"));
			if(escolha == -1) return;
			login = JOptionPane.showInputDialog("Login: ");
			senha = JOptionPane.showInputDialog("Senha: ");
		}
		
		if(adm.isProfessor(login)) menuProfessor(login);
		 else menuAluno(login);
	}
	
	public static void criarNovoUsuario() {
		String login, senha, nome, email, departamento;
		int tipo;
		login = JOptionPane.showInputDialog("Insira um login: ");
		senha = JOptionPane.showInputDialog("Insira uma senha: ");
		nome = JOptionPane.showInputDialog("Insira seu nome: ");
		email = JOptionPane.showInputDialog("Insira seu email: ");
		tipo = Integer.parseInt(JOptionPane.showInputDialog("Voce eh: \n" + "1. Professor\n" + "2. Aluno\n"));
		
		
		if(tipo == 1) {
			departamento = JOptionPane.showInputDialog("Escreva seu departamento: \n");
			while(adm.hasLogin(login)) {
				JOptionPane.showMessageDialog(null,"login invalido");
				tipo = Integer.parseInt(JOptionPane.showInputDialog("1 Tentar novamente\n-1 Sair"));
				if(tipo == -1) return;
				login = JOptionPane.showInputDialog("Insira um login: ");

			}
			Professor p = new Professor(login,senha,nome,email,departamento);
			p.addPessoa(p);
		}
		
		else {
			while(adm.hasLogin(login)) {
				JOptionPane.showMessageDialog(null,"login invalido");
				tipo = Integer.parseInt(JOptionPane.showInputDialog("1 Tentar novamente\n-1 Sair"));
				if(tipo == -1) return;
				login = JOptionPane.showInputDialog("Insira um login: ");

			}
			Aluno p = new Aluno(login, senha, nome, email);
			p.addPessoa(p);
		}

		
		fazerLogin();
	}
}

