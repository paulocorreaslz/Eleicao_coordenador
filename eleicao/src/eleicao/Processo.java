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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
/**
 * Esta classe representa o PROCESSO que será criado para verificação de coordenador.
 * Cada Processo atuará como um cliente e servidor, para receber e enviar as solicitações
 * de Eleitor e Coordenador.
 * @author Paulo Jose Melo Gomes Correa
 */
public class Processo {
	private String Id; // Id unico do processo, será a sua prioridade também.
	private int idcoordenadorAtual = 0; 
	private String ipcoordenadorAtual; // IP do atual processo coordenador.
	//private boolean coordenadorAtivo; // variavel boolean para saber se o coordenador atual esta ativo ou inativo.
	public ArrayList Maiores = new ArrayList();
	private String IP;
	private boolean EleicaoOcorrendo;
	ArrayList participantes = null;

	/**
	 * Cria o processo para fazer a verificação de coordenador.
	 * @param numID Representa Id unico do processo, será a sua prioridade também.
	 * @paran strIp Representa a string do endereço ip relacionado com o processo
	 */

	Processo(String numId, String strIp){
		this.Id = numId;	
		this.IP = strIp;
	}
	
	/**
	 * Metodo responsavel pela leitura da configuração de todos os processo que devem estar ativos.
	 * responsavel por fazer a filtragem inicial dos processos a partir do arquivo de configuração,
	 * identificando pelo numero de id/prioridade quais os processos que são superiores a ele mesmo e
	 * que estarão participando de uma futura eleição.
	 */
	public void lerConfig(){
		try {	         
			 ArrayList part = new ArrayList();
	         BufferedReader reader = new BufferedReader(new FileReader("config.txt"));
	         String dados[] = new String[2];      
	         String linha = reader.readLine();
	         int idTemp = 0;
	         String coordTemp = "";
	         int i = 0 ;
	         while (linha != null) {
	         	
	            StringTokenizer st = new StringTokenizer(linha,",\"");	            
	            dados[0] = st.nextToken();
	            dados[1] = st.nextToken();
	            Processo proc = new Processo(dados[0],dados[1]);
	            System.out.println(dados[0] + dados[1]);
	            
	            if (Integer.parseInt(proc.Id)>idTemp) {
	            	idTemp = Integer.parseInt(proc.Id);
	            	coordTemp = proc.IP;
	            }

	            ServerSocket socket = new ServerSocket(85);;
	            if (socket.getInetAddress().toString() != proc.IP) {
	            	if (Integer.parseInt(this.Id)<Integer.parseInt(proc.Id)){
	            		Maiores.add(proc.IP.toString());	
	            	}
	            	
				}	
	            
				socket.close();
	            linha = reader.readLine();
	         }
	         this.idcoordenadorAtual = idTemp;
	         this.ipcoordenadorAtual = coordTemp;
	         participantes = Maiores;
	      }
	      catch (Exception e) {	         
	         System.err.println("Erros: " + e.getMessage());
	      }
	}
	/**
	 * Recebe a eleição de outro processo e comunica que esta ativo.
	 * @throws IOException
	 * 
	 */
	public void recebeEleicao() {
		Servidor se = new Servidor();
		se.start();		
		if (se.isNovaEleicaonoAr()) {
			this.iniciaEleicao();
		}
		}
	/**
	 * Metodo responsavel pelo teste para verificar se o coordenador está ativo. 
	 * 
	 */
	public void testaCoordenador(String strCoord){
		Coordenador co = new Coordenador();
		co.start();
		while(true) {
		if (!co.testaCoordenador(strCoord)) {
		    if(!EleicaoOcorrendo) { 
		    	this.iniciaEleicao();
		    }
		 }
	   }
	}

	/**
	 * Inicia a eleição para verificar quais os processos estão ativos e retorna o processo de maior prioridade.
	 * 
	 */
	public synchronized void iniciaEleicao(){	
		EleicaoOcorrendo = true;
		int i = 0;  
		int Participantes = 0;
		if (Maiores.size()>0){
		      	Iterator it = Maiores.iterator();
		      	String strIP;
		      	while (it.hasNext()){
		      		strIP = (String) it.next(); 
		      		Cliente cli = new Cliente(strIP);
		      		cli.iniciaEleicao(strIP);
		      	    Participantes = cli.getParticipante();
		        }
	      	    if (Participantes == 0) {
	      	    	System.out.println("Novo Coordenador: "+ Id + " IP: "+ IP);
	      	    	this.setidCoordenadorAtual(Integer.parseInt(Id));
	      	    	this.setIpcoordenadorAtual(IP);
	      	    } 
	      } 
		EleicaoOcorrendo = false;
	}
	/**
	 * Retorna as informações do id do processo atual.
	 * @return As informações do id do processo atual.
	 */
	public String getId() {
		return Id;
	}
	/**
	 * Define as informações do id do processo.
	 * @param id Representa as informações do id do processo.
	 */
	public void setId(String id) {
		Id = id;
	}
	/**
	 * Retorna as informações do ip do processo atual.
	 * @return As informações do ip do processo atual.
	 */
	public String getIP() {
		return IP;
	}
	/**
	 * Retorna as informações do id do coordenador atual.
	 * @return As informações do id do coordenador atual.
	 */
	public String getidCoordenadorAtual() {
		return ""+idcoordenadorAtual;
	}
	/**
	 * Retorna as informações do array de participantes da configuração geral.
	 * @return As informações do array de participantes da configuração geral.
	 */
	public ArrayList getParticipantes() {
		return participantes;
	}
	/**
	 * Define as informações do ip do processo.
	 * @param ip Representa as informações do ip do processo.
	 */
	public void setIP(String ip) {
		IP = ip;
	}
	/**
	 * Define as informações do id do coordenador atual.
	 * @param coordenadorAtual Representa as informações do ip do coordenador atual.
	 */
	public void setidCoordenadorAtual(int coordenadorAtual) {
		this.idcoordenadorAtual = coordenadorAtual;
	}
	/**
	 * Retorna as informações do ip do coordenador atual.
	 * @return As informações do ip do coordenador atual
	 */
	public String getIpcoordenadorAtual() {
		return ipcoordenadorAtual;
	}
	/**
	 * Define as informações do ip do coordenador atual.
	 * @param ipcoordenadorAtual Representa as informações do ip do coordenador atual.
	 */
	public void setIpcoordenadorAtual(String ipcoordenadorAtual) {
		this.ipcoordenadorAtual = ipcoordenadorAtual;
	}
}
