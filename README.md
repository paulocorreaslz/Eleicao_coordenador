UNIVERSIDADE FEDERAL DO MARANH�O
P�S-GRADUA��O EM ENGENHARIA DA ELETRICIDADE
CI�NCIA DA COMPUTA��O
SISTEMAS DISTRIBUIDOS

IMPLEMENTA��O DE ALGORITMO PARA ELEI��O DE COORDENADOR � GARCIA - MOLINA

PAULO JOSE MELO GOMES CORR�A

S�o Luis - MA
2006
 
Descri��o do problema: 	

O problema do coordenador central ser o ponto �nico de falha, podendo limitar a disponibilidade de um servi�o.

O problema pode ser contornado se poder ser eleito um novo coordenador.

A detec��o de falha � feita normalmente por time-out: caso o processo n�o receba a resposta do seu coordenador, automaticamente ele inicia uma elei��o com os processos de prioridade superior para eleger um novo coordenador.
 
Implementa��o da solu��o:

A solu��o ser� implementada por uma classe respons�vel pelo recebimento e envio das mensagens dos outros processos (classe Processo - Anexo I).

Ao receber uma mensagem, o processo inicia uma thread (uma nova linha de execu��o) que realmente ir� processar a mensagem recebida (classe Processo - Anexo II).

A nova thread verifica a mensagem e responde para o processo que enviou a mensagem;

Caso o processo n�o responda a mensagem, automaticamente o processo que iniciou a elei��o ira ordenar quais processos de prioridade superior responderam a mensagem e verificar qual ser� o novo coordenandor.

Mais informa��es sobre a implementa��o e classes utilizadas: javadoc.

Compilando o programa:	

Para compilar o programa, pode-se utilizar o arquivo compila.bat. Os objetos compilados ser�o criados no diret�rio bin.

Executando o programa:
executar o iniciarProcesso

Amostra de uma execu��o de testes:

Executou-se o Processo utilizando a instru��o:

Mensagens do servidor:
	
Analisaremos agora as principais mensagens do programa do servidor. 
As outras linhas de mensagens do servidor dessa execu��o podem ser lidas no Anexo VI.

Mensagens dos Clientes:
Analisaremos as principais linhas do programa dos clientes.

As outras linhas de mensagens dos clientes dessa execu��o podem ser lidas no Anexo VII.
 
Anexo I � C�digo-fonte da classe Servidor

