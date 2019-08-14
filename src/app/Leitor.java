package app;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.io.PrintStream;
import java.awt.Desktop;
import app.Questionario;


public class Leitor {

	public void leitura(String arquivo){
        try{ 
            Scanner scan = new Scanner(new File(arquivo+".txt")); /* leitura do arquivo */
            while (scan.hasNext()) {
                System.out.print(scan.nextLine());
            }
            scan.close();
        }catch(Exception e){ 
            System.out.println("Erro na leitura do arquivo");
        }
        System.out.println("Arquivo lido!");

    }
	
	public void abrirArquivo(String arquivo) {
		File file = new File(arquivo+".txt");
        
        if(!Desktop.isDesktopSupported()){
            System.out.println("Desktop não é suportado, impossivel abrir");
            return;
        }
        
        Desktop desktop = Desktop.getDesktop();
        if(file.exists())
			try {
				desktop.open(file);
			} catch (IOException e) {
				System.out.println("Erro ao abrir o arquivo no Desktop: " + e);
			}
	}

}