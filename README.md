UNIVERSIDADE FEDERAL DO MARANHÃO
PÓS-GRADUAÇÃO EM ENGENHARIA DA ELETRICIDADE
CIÊNCIA DA COMPUTAÇÃO
SISTEMAS DISTRIBUIDOS

IMPLEMENTAÇÃO DE ALGORITMO PARA ELEIÇÃO DE COORDENADOR – GARCIA - MOLINA

PAULO JOSE MELO GOMES CORRÊA

São Luis - MA
2006
 
Descrição do problema: 	

O problema do coordenador central ser o ponto único de falha, podendo limitar a disponibilidade de um serviço.

O problema pode ser contornado se poder ser eleito um novo coordenador.

A detecção de falha é feita normalmente por time-out: caso o processo não receba a resposta do seu coordenador, automaticamente ele inicia uma eleição com os processos de prioridade superior para eleger um novo coordenador.
 
Implementação da solução:

A solução será implementada por uma classe responsável pelo recebimento e envio das mensagens dos outros processos (classe Processo - Anexo I).

Ao receber uma mensagem, o processo inicia uma thread (uma nova linha de execução) que realmente irá processar a mensagem recebida (classe Processo - Anexo II).

A nova thread verifica a mensagem e responde para o processo que enviou a mensagem;

Caso o processo não responda a mensagem, automaticamente o processo que iniciou a eleição ira ordenar quais processos de prioridade superior responderam a mensagem e verificar qual será o novo coordenandor.

Mais informações sobre a implementação e classes utilizadas: javadoc.

Compilando o programa:	

Para compilar o programa, pode-se utilizar o arquivo compila.bat. Os objetos compilados serão criados no diretório bin.

Executando o programa:
executar o iniciarProcesso

Amostra de uma execução de testes:

Executou-se o Processo utilizando a instrução:

Mensagens do servidor:
	
Analisaremos agora as principais mensagens do programa do servidor. 
As outras linhas de mensagens do servidor dessa execução podem ser lidas no Anexo VI.

Mensagens dos Clientes:
Analisaremos as principais linhas do programa dos clientes.

As outras linhas de mensagens dos clientes dessa execução podem ser lidas no Anexo VII.
 
Anexo I – Código-fonte da classe Servidor

