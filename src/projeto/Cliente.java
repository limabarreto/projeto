package projeto; 

// importa bibliotecas p entrada e saída de dados, comunicação via rede e leitura do teclado
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente { 

    public static void main(String[] args) { 
        try (Scanner scanner = new Scanner(System.in)) { 
            System.out.print("Digite o endereço IP do servidor: ");
            String ip = scanner.nextLine(); 
            System.out.print("Digite a porta do servidor: ");
            int porta = Integer.parseInt(scanner.nextLine()); 

            // inicia a conexão com o servidor e abre os fluxos de entrada e saída
            try (
                    Socket cliente = new Socket(ip, porta); // cria um socket e conecta ao ip e porta informados
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream())); // lê os dados q vem do servidor
                    PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true); // envia dados p o servidor
                    FileWriter logConversa = new FileWriter("conversa.txt", true)) { // abre ou cria um arquivo p salvar a conversa
                        
                //  conexão foi estabelecida com sucesso
                System.out.println("Conectado ao servidor em " + ip + ":" + porta);
                
                // cria outro scanner p continuar lendo o que o usuário digita
                Scanner teclado = new Scanner(System.in); 
                String mensagem; // armazena a mensagem digitada pelo usuário

                // loop p comunicação
                while (true) {
                    System.out.print("Você: "); 
                    mensagem = teclado.nextLine(); 

                    saida.println(mensagem); 
                    logConversa.write("Você: " + mensagem + "\n"); 

                   
                    if (mensagem.equalsIgnoreCase("exit")) {
                        System.out.println("Conexão encerrada."); 
                        break; 
                    }

                    
                    String resposta = entrada.readLine(); // lê a resposta enviada pelo servidor

                    // se a resposta for nula, quer dizer que o servidor fechou a conexão
                    if (resposta == null) {
                        System.out.println("Servidor encerrou a conexão."); 
                        break; 
                    }

                    // exibe a resposta do servidor e registra no arquivo
                    System.out.println("Servidor: " + resposta);
                    logConversa.write("Servidor: " + resposta + "\n");
                }
            }
        } catch (IOException e) { 
            System.out.println("Erro no cliente: " + e.getMessage()); 
        }
    }
}
