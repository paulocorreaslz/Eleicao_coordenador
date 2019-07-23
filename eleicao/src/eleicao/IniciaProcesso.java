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
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Esta Classe representa o processo inicial da aplica��o, devendo conter a informa��o b�sica e 
 * unica, nao devendo conter divergencias com o arquivo de configura��o.
 *
 * @author Paulo jose Melo Gomes Correa
 * 
 */

public class IniciaProcesso {

	/**
	 * Metodo main, responsavel por iniciar todo o procedimento de trabalho de elei��o de coordenador.  
	 * 
	 */
	public static void main(String[] args) {
	  Processo obj = new Processo("5","192.168.254.1");	  
	  obj.lerConfig();
	  System.out.println("coordenador atual: "+obj.getidCoordenadorAtual()+ " IP: "+obj.getIpcoordenadorAtual());
      obj.recebeEleicao();
      obj.iniciaEleicao();
	  obj.testaCoordenador(obj.getIpcoordenadorAtual());
	  System.out.println("o Coordenador Atual �:"+obj.getIpcoordenadorAtual());
	}
}
