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
import java.io.Serializable;

/**
 * Esta Classe representa o tipo de mensagem a ser enviada para toda comunicação feita pelo processo.
 *
 * @author Paulo jose Melo Gomes Correa
 * 
 */
public class Mensagem implements Serializable {
	private String tipo; //tipo de mensagem.
	private String host; //informações de host.
	
	/**
	 * Cria um pacote.
	 * @param tipo Representa o tipo da mensagem.
	 * @param host Representa informações de host que devem 
	 * ser enviadas junto com a mensagem.
	 */
	
	Mensagem(String tipo, String host){
		setTipo(tipo);
		setHost(host);
		
	}
	/**
	 * Define o tipo de mensagem.
	 * @param tipo Representa o tipo da mensagem.
	 */
	public void setTipo(String tipo) { 
		this.tipo = tipo; 
	}
	/**
	 * Retorna o tipo de pacote. 
	 * @return O tipo de pacote.
	 */
	public String getTipo() { 
		return tipo; 
	}
	/**
	 * Retorna as informações de host a serem enviadas.
	 * @return As informações de host a serem enviadas.
	 */
	public String getHost() {
		return host;
	}
	/**
	 * Define as informações de host a serem enviadas.
	 * @param host Representa as informações de host a serem enviadas.
	 */
	public void setHost(String host) {
		this.host = host;
	}
}