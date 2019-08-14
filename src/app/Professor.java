package app;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class Professor extends Pessoa {
	private String departamento;
	private Map<Integer, String> resultadosAlunos = new HashMap<Integer, String>();
	private static Gravacao leituraEscrita = new Gravacao();
	Observable resultadoNovo;

	public Professor(String login, String senha,String nome, String email, String departamento){
		super(login, senha,nome, email);
		this.setDepartamento(departamento);
		pessoas.put(login, this);
		
	}
	
	private Pessoa buscaLogin() {
		String login = JOptionPane.showInputDialog("Digite o login: ");
		while(!hasLogin(login)) {
			int escolha = Integer.parseInt(JOptionPane.showInputDialog("login invalido\n1 Digitar outro login \n-1 Sair\n"));	
			if(escolha == -1) return null;
			login = JOptionPane.showInputDialog("Digite o login: ");
		}
		return getPessoa(login);
	}
	
	//professor adicionando aluno em uma turma
	public void menuAdicionaAlunoEmTurma() {
		JOptionPane.showMessageDialog(null,"Mostrando lista de turmas disponiveis\n");	
		Turma t = acessaTurma();
		if(t == null) return;

		
		JOptionPane.showMessageDialog(null,"Agora escolha o login de quem deseja inserir na turma\n");	
		Pessoa p = buscaLogin();
		if(p == null) return;

		p.addKey(t.getKey());
		updatePessoas(p);
		JOptionPane.showMessageDialog(null,"O login foi adicionado na turma\n");	
	}
	//professor criando uma turma
	public void menuAdicionaTurma() {
		String nome;
		int escolha;

		nome = JOptionPane.showInputDialog("Digite o nome da turma: ");	
		while(getKeyOfTurma(nome) != -1) {
			escolha = Integer.parseInt(JOptionPane.showInputDialog("Nome de turma ja usado\n1 Tentar novamente\n-1 Sair\n"));	
			if(escolha == -1) return;
			nome = JOptionPane.showInputDialog("Digite o nome da turma: ");	
		}
		
		Turma t = new Turma();
		t.setNome(nome);
		System.out.println("botou nome");
		t = t.addTurma(t);
		addKey(t.getKey());
		JOptionPane.showMessageDialog(null,"A turma foi criada\n");	
	}
	
	//professor criando questoes para o questionario
	public Questao criaQuestao(int tipo) {
		int op;
		String enunciado;
		String [] alternativas;
		String resposta;
		int qtd;
		char correta;
		JScrollPane barraRolagem;
		
		
		enunciado = JOptionPane.showInputDialog("Digite o enunciado: ");	
		op = Integer.parseInt(JOptionPane.showInputDialog("Esta satisfeito com o enunciado?\n" + enunciado + "\n 1 sim\n 2 nao\n"));
		while(op == 2) {
			enunciado = JOptionPane.showInputDialog("Digite o enunciado: ");	
			op = Integer.parseInt(JOptionPane.showInputDialog("Esta satisfeito com o enunciado?\n" + enunciado + "\n 1 sim\n 0 nao\n"));
		}

		if(tipo == 1 || tipo == 3) {
			resposta = JOptionPane.showInputDialog("Digite a resposta: ");	
			op = Integer.parseInt(JOptionPane.showInputDialog("Esta satisfeito com a resposta?\n" + resposta + "\n 1 sim\n 2 nao\n"));
			while(op == 2) {
				resposta = JOptionPane.showInputDialog("Digite a resposta: ");	
				op = Integer.parseInt(JOptionPane.showInputDialog("Esta satisfeito com a resposta?\n" + resposta + "\n 1 sim\n 2 nao\n"));
			}
			return (tipo == 1? new Dissertativa(enunciado, resposta) : new Numerica(enunciado,resposta));
		}
		
		qtd = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de alternativas: "));
		while(qtd > 5) {
			JOptionPane.showMessageDialog(null,"Limite de 5 alternativas excedido\n");	
			qtd = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de alternativas: "));
		}
		alternativas = new String[qtd];
		for(int i = 0; i < qtd; i++) 
			alternativas[i] = JOptionPane.showInputDialog("Digite o enunciado da alternativa " + (i+1) + ":");

		correta = JOptionPane.showInputDialog("Digite a alternativa correta: ").charAt(0);	
		
		while(!(correta - 'a' >= 0 && correta -'a' < qtd)) {
			JOptionPane.showMessageDialog(null,"Alternativa invalida\n");	
			correta = JOptionPane.showInputDialog("Digite a alternativa correta: ").charAt(0);	
		}
		
		return new MultiplaEscolha(enunciado,alternativas,correta);
	}
	
	//professor criando um questionario
	public void menuCriaQuestionario() {
		Turma t = acessaTurma();
		if(t == null) return;
		
		String nome = JOptionPane.showInputDialog("Nome do questionario");
		int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Quantidade de questoes: "));	
		int dias = Integer.parseInt(JOptionPane.showInputDialog("Duracao do questionario em dias: "));	
		
		LocalDate data = LocalDate.now();
		data.plusDays(dias);
		
		Questionario q = new Questionario(t.nome,tamanho, data, nome, this.login);

		for(int i = 0; i < tamanho; i++) {
			int tipo = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo da questao\n1 Dissertativa\n2 Multipla Escolha\n3 Numerica\n"));

			while(!(tipo >= 1 && tipo <= 3)) { 
				JOptionPane.showMessageDialog(null,"Tipo de questao invalida\n");	
				tipo = Integer.parseInt(JOptionPane.showInputDialog("Escolha o tipo da questao\n1 Dissertativa\n2 Multipla Escolha\n3 Numerica\n"));
			}

			int peso = Integer.parseInt(JOptionPane.showInputDialog("Digite o peso da questao: \n"));
		
			q.adicionaQuestaoNoQuestionario(criaQuestao(tipo), peso);
		}

		//criacao do questionario em txt
		try {
			leituraEscrita.gravaArquivo(q);
			System.out.println("Arquivo gravado");
		}catch( IOException e) {
			System.out.println(e);
		}
		//adicionando o questionario nos dados
		t.addQuestionario(q);
		t.updateTurma(t.getKey(), t);
	}
	
	public void updateResultadosAlunos(Integer questId, String novoResultado) {
		resultadosAlunos.replace(questId, novoResultado);
	}
	
	public void addResultadoAluno(Integer questId, String resultadoAluno) {
		if(resultadosAlunos.containsKey(questId)) {
			String novoResultado = resultadosAlunos.get(questId);
			novoResultado += resultadoAluno;
			updateResultadosAlunos(questId, novoResultado);
		} else {
			resultadosAlunos.put(questId, resultadoAluno);
		}
	}

	public boolean isProfessor(String login) {
		return (pessoas.get(login) instanceof Professor);
	}
	
	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public Questionario acessaQuestionario(Turma t)  {
		int escolha,id;

		JOptionPane.showMessageDialog(null, "Mostrando questionarios disponiveis respondidos da turma\n");
		t.mostraQuestionarios(this);
		
		id = Integer.parseInt(JOptionPane.showInputDialog("Escolha o questionario: (digite o codigo que apareceu ao lado do nome)"));
		
		while(!t.hasQuestionario(id)) {
			JOptionPane.showMessageDialog(null, "Questionario invalida\n");
			escolha = Integer.parseInt(JOptionPane.showInputDialog("1 Escolha um questionario\n2 Visualizar novamente os questionarios da turma\n3 Escolher outra turma\n-1 Sair"));
			if(escolha == 1 || escolha == 2) {
				if(escolha == 2) t.mostraQuestionarios(this);
				id = Integer.parseInt(JOptionPane.showInputDialog("Escolha o questionario: (digite o codigo que apareceu ao lado do nome"));
			}
			else if(escolha == 3) t = this.acessaTurma();
			
			if(escolha == -1 || t == null) return null;
		}
		
		return t.getQuestionario(id);
	}
	
	//acesso do professor as pontuacoes dos alunos do questionario dado como parametro
	//@Override
	public void verPontuacaoAlunos() {
		Turma t = null;
		Questionario q = null;
		
		t = this.acessaTurma();
		if(t == null) return;

		q = this.acessaQuestionario(t);
		if(q == null) return;
	
		
		JOptionPane.showMessageDialog(null, "As notas serao mostradas em porcentagem\n");
		for(Map.Entry<Integer, String> entry : resultadosAlunos.entrySet())  {
			int questId = entry.getKey();
			if(questId == q.id) {
				JOptionPane.showMessageDialog(null, entry.getValue());
				return;
			}
		}
	}
}


