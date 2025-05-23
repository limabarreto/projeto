package projeto; 

import java.io.*; // p operações de entrada e saída (leitura e escrita)
import java.net.*; // p operações de rede (sockets, IP, etc.)
import java.util.Scanner; // importa a classe Scanner p leitura de dados do teclado

public class Servidor { 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 

        try {
          
            System.out.print("Digite o IP do servidor (da sua máquina): ");
            String ip = scanner.nextLine(); 
            System.out.print("Digite a porta para escutar: ");
            int porta = Integer.parseInt(scanner.nextLine()); 

            // cria um socket de servidor e associa (bind) ao IP e porta informados
            ServerSocket servidor = new ServerSocket();
            servidor.bind(new InetSocketAddress(ip, porta));

            System.out.println("Servidor escutando em " + ip + ":" + porta);

            // aguarda a conexão de um cliente
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado de: " + cliente.getInetAddress());

            // cria um leitor de mensagens recebidas do cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            // cria um escritor de mensagens que serão enviadas p o cliente
            PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);

            // cria um gravador p salvar a conversa em um arquivo texto
            BufferedWriter arquivo = new BufferedWriter(new FileWriter("conversa.txt", true));

            // cria um scanner p ler mensagens digitadas pelo servidor
            Scanner teclado = new Scanner(System.in);
            String mensagem; // armazena as mensagens recebidas

            // loop principal de comunicação c o cliente
            while (true) {
                mensagem = entrada.readLine(); // lê a próxima mensagem enviada pelo cliente

                
                if (mensagem == null || mensagem.equalsIgnoreCase("exit")) {
                    System.out.println("Conexão encerrada pelo cliente.");
                    break;
                }

                
                System.out.println("Cliente: " + mensagem);
                arquivo.write("Cliente: " + mensagem);
                arquivo.newLine(); 

                // solicita a resposta do servidor
                System.out.print("Servidor: ");
                String resposta = teclado.nextLine(); // lê a resposta digitada

                // envia a resposta ao cliente e grava no arquivo
                saida.println(resposta);
                arquivo.write("Servidor: " + resposta);
                arquivo.newLine(); 
            }

            // finaliza os recursos abertos
            arquivo.close();
            servidor.close();
            cliente.close();

        } catch (IOException e) { 
            System.out.println("Erro: " + e.getMessage()); 
        }
    }
}
