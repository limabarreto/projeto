package projeto; 

import java.io.*; // Importa classes para operações de entrada e saída (leitura e escrita)
import java.net.*; // Importa classes para operações de rede (sockets, IP, etc.)
import java.util.Scanner; // Importa a classe Scanner para leitura de dados do teclado

public class Servidor { 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 

        try {
          
            System.out.print("Digite o IP do servidor (da sua máquina): ");
            String ip = scanner.nextLine(); 
            System.out.print("Digite a porta para escutar: ");
            int porta = Integer.parseInt(scanner.nextLine()); 

            // Cria um socket de servidor e associa (bind) ao IP e porta informados
            ServerSocket servidor = new ServerSocket();
            servidor.bind(new InetSocketAddress(ip, porta));

            System.out.println("Servidor escutando em " + ip + ":" + porta);

            // Aguarda a conexão de um cliente
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado de: " + cliente.getInetAddress());

            // Cria um leitor de mensagens recebidas do cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            // Cria um escritor de mensagens que serão enviadas para o cliente
            PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);

            // Cria um gravador para salvar a conversa em um arquivo texto
            BufferedWriter arquivo = new BufferedWriter(new FileWriter("conversa.txt", true));

            // Cria um scanner para ler mensagens digitadas pelo servidor
            Scanner teclado = new Scanner(System.in);
            String mensagem; // armazena as mensagens recebidas

            // Loop principal de comunicação c o cliente
            while (true) {
                mensagem = entrada.readLine(); // Lê a próxima mensagem enviada pelo cliente

                
                if (mensagem == null || mensagem.equalsIgnoreCase("exit")) {
                    System.out.println("Conexão encerrada pelo cliente.");
                    break;
                }

                
                System.out.println("Cliente: " + mensagem);
                arquivo.write("Cliente: " + mensagem);
                arquivo.newLine(); 

                // Solicita a resposta do servidor
                System.out.print("Servidor: ");
                String resposta = teclado.nextLine(); // Lê a resposta digitada

                // Envia a resposta ao cliente e grava no arquivo
                saida.println(resposta);
                arquivo.write("Servidor: " + resposta);
                arquivo.newLine(); 
            }

            // Finaliza os recursos abertos
            arquivo.close();
            servidor.close();
            cliente.close();

        } catch (IOException e) { 
            System.out.println("Erro: " + e.getMessage()); 
        }
    }
}
