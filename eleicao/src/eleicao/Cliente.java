/*
 * UNIVERSIDADE FEDERAL DO MARANH�O
 * MESTRADO EM CI�NCIA DA COMPUTA��O - DEE
 * TRABALHO DE SISTEMAS DISTRIBUIDOS
 * IMPLEMENTA��O DE ELEI��O DE COORDENADOR - GARCIA E MOLINA
 * 
 * PAULO JOSE MELO GOMES CORREA
 * 
 * 2006
 */

package eleicao;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Esta classe representa o CLIENTE que ser� criado para enviar as mensagens.
 * Cada Processo atuar� como um cliente e servidor, para receber e enviar as solicita��es
 * de Eleitor e Coordenador.
 * @author Paulo Jose Melo Gomes Correa
 * 
 */
public class Cliente extends Thread {
	private int id; // id do participante
	private String ip; // ip do participante
	private int Participante; // participante
	
	/**
	 * Cria uma thread do tipo cliente responsavel por fazer os pedidos de elei��o.
	 * @param b Representa o ip do cliente.
	 */
	
	Cliente(String b){
		this.ip = b;
	}
	/**
	 * thread do tipo cliente responsavel por iniciar a elei��o entre os participantes.
	 * @param strIp Representa o ip do cliente que est� participando da elei��o.
	 */	
	public void iniciaEleicao(String strIp){		
		try{
			int i ;
			try {
				System.out.println("Classe Cliente: Enviando mensagem para participante.. IP:"+strIp);	
				//ObjectInputStream input = null;
				Socket s = new Socket(strIp,83);				
				String MensagemparaServidor = "ELEICAO"; 
				ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
				Mensagem mensagem = new Mensagem("ELEICAO",strIp);
				out.flush();
				out.writeObject((Object) mensagem);
				System.out.println("Classe Cliente: Escrevendo mensagem ELEICAO IP:"+strIp);
				out.flush();
				out.close();
				s.close();
				
			} catch (Exception e){
				System.out.println("Classe Cliente: Servidor nao respondeu: "+ e.getMessage());
			}
			
		}catch(Exception e){
			System.out.println("Erro Run cliente:"+e.getMessage());
			
		}
	}
	/**
	 * metodo de cria��o da thread.
	 * @param strIp representa o ip do participante.
	 */
	public void run(String strIp) {		
	}
	/**
	 * metodo de destrui��o da thread.
	 * 
	 */
	public void destroy() {		
		super.destroy();
	}
	/**
	 * Retorna as informa��es do participante.
	 * @return As informa��es do participante.
	 */
	public int getParticipante() {
		return Participante;
	}
}
