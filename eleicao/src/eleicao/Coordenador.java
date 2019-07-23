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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Esta classe representa o COORDENADOR que será criado para testar o coordenador atual.
 * A thread iniciada pela classe coordenador, tem o papel de fazer a verificação, para saber se o coordenador está ativo. 
 * @author Paulo Jose Melo Gomes Correa
 */
public class Coordenador extends Thread {
	private boolean coordenadorAtivo = false; 
	private String ipCoord;
	private boolean souCoordenador = false;
	Coordenador(){
	}
	/**
	 * Cria o ambiente para efetuar o teste do coordenador.
	 * @param strCoord Representa o endereço IP do coordenador para realizar a verificação.
	 */
	public boolean testaCoordenador(String strCoord){
		try {
			sleep(1000);
			ObjectInputStream input = null;
			Socket s = new Socket(strCoord,83);				
			String MensagemparaServidor = "COORDENADOR";
			System.out.println("Classe Coordenador: Enviando mensagem para coordenador. IP: " + strCoord);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			Mensagem mensagem = new Mensagem("COORDENADOR",strCoord);
			out.flush();
			out.writeObject((Object) mensagem);
			out.flush();
			out.close();
			s.close();
			this.coordenadorAtivo =  true;
			return true;
		} catch (Exception e) {
			System.out.println("Classe Coordenador: Processo nao respondeu2.." + e.getMessage());
			this.coordenadorAtivo =  false;
			return false;
		}
	}
	/**
	 * 	 * @param strCoord Representa o endereço IP do coordenador.
	 */
	public void run(String strCoord) throws InterruptedException{
		sleep(1000);
	}
	/**
	 * Retorna as informações do coordenador (Ativo ou Não).
	 * @return As informações do coordenador.
	 */
	public boolean isCoordenadorAtivo() {
		return coordenadorAtivo;
	}
}
