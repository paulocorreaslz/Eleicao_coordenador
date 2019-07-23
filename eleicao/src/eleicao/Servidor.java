/*
 * UNIVERSIDADE FEDERAL DO MARANHÃO
 * MESTRADO EM CIÊNCIA DA COMPUTAÇÃO - DEE
 * TRABALHO DE SISTEMAS DISTRIBUIDOS
 * IMPLEMENTAÇÃO DE ELEIÇÃO DE COORDENADOR - GARCIA E MOLINA
 * 
 * PAULO JOSE MELO GOMES CORREA
 * 
 * 2006
 */

package eleicao;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Esta classe representa o SERVIDOR que será criado para receber as mensagens.
 * Cada Processo atuará como um cliente e servidor, para receber e enviar as solicitações
 * de Eleitor e Coordenador.
 * @author Paulo Jose Melo Gomes Correa
 */

public class Servidor extends Thread {
	
	private String ipNovoCoordenador; // id do novo coordenador
	private boolean novaEleicaonoAr= false; // define se existe uma nova eleição acontecendo.
	
	
	public void recebeEleicao() {	
	}
	/**
	 * Metodo responsavel por receber as mensagens, iniciado por uma thread responsavel apenas
	 * por fazer a filtragem dos dados e identificar se a mensagem enviada é uma nova eleição ou
	 * se a mensagem é um teste de coordenador ativo.
	 */
	
	public synchronized void run() {	
		try{
			try {
				sleep(1000);
				ServerSocket server = new ServerSocket(83);				
				System.out.println("Classe Servidor Aguardando Mensagens..");				
				while(true) {											
					Socket conexao = server.accept();  
					ObjectInputStream in = new ObjectInputStream(conexao.getInputStream());
					System.out.println("Servidor: lendo mensagem ");
					Mensagem men = (Mensagem) in.readObject();
					//processa o pacote
					System.out.println("Servidor: mensagem recebida do Host: "+men.getHost()+" tipo: "+men.getTipo());
					//fecha a conexao
					in.close();
					conexao.close();
					if (men.getTipo().equals("ELEICAO")) {
						System.out.println("Servidor: NOVA ELEICAO NO AR");
						novaEleicaonoAr = true;
					}
					if (men.getTipo().equals("COORDENADOR")) {
						System.out.println("Servidor: Mensagem Coordenador");
						ObjectOutputStream out = new ObjectOutputStream(conexao.getOutputStream());
						Mensagem mensagem2 = new Mensagem("COORDENADOR",conexao.getInetAddress().getHostAddress());					
						out.flush();
						
					}
				}
			}catch (SocketException e) {
				System.out.println("Clase Servidor: Servidor ja iniciado. "+e.getMessage());
			}catch (IOException e) {
				System.out.println("Clase Servidor Erro:"+e.getMessage());	
			}
			
		}catch(Exception e ){
			System.out.println("Classe Servidor: erro run servidor:"+e.getMessage());
		}
		
	}
	/**
	 * Retorna o ip do novo coordenador. 
	 * @return o ip do novo coordenador.
	 */
	public String getIpNovoCoordenador() {
		return ipNovoCoordenador;
	}
	/**
	 * Define as informações do ip do novo coordenador.
	 * @param ipNovoCoordenador as informações do ip do novo coordenador.
	 */
	public void setIpNovoCoordenador(String ipNovoCoordenador) {
		this.ipNovoCoordenador = ipNovoCoordenador;
	}
	/**
	 * Retorna o valor responsavel por identificar se existe uma nova eleição acontecendo. 
	 * @return o valor responsavel por identificar se existe uma nova eleição acontecendo.
	 */
	public boolean isNovaEleicaonoAr() {
		return novaEleicaonoAr;
	}
}
