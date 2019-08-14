package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import app.Questionario;

public class Gravacao {

	//cria dois arquivos em txt, um para o aluno visualizar quando abrir o questionario, e outro para quando o aluno
	//já tiver respondido o questionario e quiser ver as respostas, o das respostas tem nome_dado_pelo_professor_Resposta
	public void gravaArquivo(Questionario o)throws IOException{
		int w = 0;
		//colocar a verificacao se existe ou não um arquivo com esse nome dependendo de onde forem guardados os questionarios
		//String nome = JOptionPane.showInputDialog("Escreva o nome do arquivo");
		String nome = o.nome;
		do {
			BufferedWriter bufferEscrita = new BufferedWriter(new FileWriter(nome+".txt", true));
			try {
				for(int i=0; i<o.questoes.length;i++) {
					if(o.questoes[i]!= null) {
						if(w!=1) {
							String numQuestao = "" + (i+1);
							bufferEscrita.write((numQuestao));
							String campos[] = o.questoes[i].toString().split(";");
							for(int j=0;j<campos.length;j++) {
								bufferEscrita.write(campos[j] + "\n");
								bufferEscrita.newLine();
							}
							bufferEscrita.newLine();
						}
						else {
							
							String camposResposta[] = o.questoes[i].toStringResposta().split(";");
							for(int h=0;h<camposResposta.length;h++) {
								bufferEscrita.write((h+1));
								bufferEscrita.write(camposResposta[h] + "\n");
								bufferEscrita.newLine();
								bufferEscrita.newLine();
							}
						}
					}
				}
			} finally {
				bufferEscrita.close();
			}
			w++;
			nome = o.nome+"Respostas";
		}while(w<=1);
	}

}


