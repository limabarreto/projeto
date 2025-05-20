package projeto;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Digite o IP do servidor (da sua máquina): ");
            String ip = scanner.nextLine();

            System.out.print("Digite a porta para escutar: ");
            int porta = Integer.parseInt(scanner.nextLine());

            // Faz o bind no IP e porta informados
            ServerSocket servidor = new ServerSocket();
            servidor.bind(new InetSocketAddress(ip, porta));

            System.out.println("Servidor escutando em " + ip + ":" + porta);

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado de: " + cliente.getInetAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);

            BufferedWriter arquivo = new BufferedWriter(new FileWriter("conversa.txt", true));
            Scanner teclado = new Scanner(System.in);
            String mensagem;

            while (true) {
                mensagem = entrada.readLine();
                if (mensagem == null || mensagem.equalsIgnoreCase("exit")) {
                    System.out.println("Conexão encerrada pelo cliente.");
                    break;
                }
                System.out.println("Cliente: " + mensagem);
                arquivo.write("Cliente: " + mensagem);
                arquivo.newLine();

                System.out.print("Servidor: ");
                String resposta = teclado.nextLine();
                saida.println(resposta);
                arquivo.write("Servidor: " + resposta);
                arquivo.newLine();
            }

            arquivo.close();
            servidor.close();
            cliente.close();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
