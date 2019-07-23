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
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Esta Classe representa o processo inicial da aplicação, devendo conter a informação básica e 
 * unica, nao devendo conter divergencias com o arquivo de configuração.
 *
 * @author Paulo jose Melo Gomes Correa
 * 
 */

public class IniciaProcesso {

	/**
	 * Metodo main, responsavel por iniciar todo o procedimento de trabalho de eleição de coordenador.  
	 * 
	 */
	public static void main(String[] args) {
	  Processo obj = new Processo("5","192.168.254.1");	  
	  obj.lerConfig();
	  System.out.println("coordenador atual: "+obj.getidCoordenadorAtual()+ " IP: "+obj.getIpcoordenadorAtual());
      obj.recebeEleicao();
      obj.iniciaEleicao();
	  obj.testaCoordenador(obj.getIpcoordenadorAtual());
	  System.out.println("o Coordenador Atual é:"+obj.getIpcoordenadorAtual());
	}
}
