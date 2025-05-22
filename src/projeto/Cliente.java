package projeto;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Solicita IP e porta do servidor
            System.out.print("Digite o endereço IP do servidor: ");
            String ip = scanner.nextLine();

            System.out.print("Digite a porta do servidor: ");
            int porta = Integer.parseInt(scanner.nextLine());

            try (
                    Socket cliente = new Socket(ip, porta); 
                    BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream())); 
                    PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true); 
                    FileWriter logConversa = new FileWriter("conversa.txt", true)) {
                System.out.println("Conectado ao servidor em " + ip + ":" + porta);
                Scanner teclado = new Scanner(System.in);
                String mensagem;

                while (true) {
                    System.out.print("Você: ");
                    mensagem = teclado.nextLine();
                    saida.println(mensagem);
                    logConversa.write("Você: " + mensagem + "\n");

                    if (mensagem.equalsIgnoreCase("exit")) {
                        System.out.println("Conexão encerrada.");
                        break;
                    }

                    String resposta = entrada.readLine();
                    if (resposta == null) {
                        System.out.println("Servidor encerrou a conexão.");
                        break;
                    }

                    System.out.println("Servidor: " + resposta);
                    logConversa.write("Servidor: " + resposta + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro no cliente: " + e.getMessage());
        }
    }
}
