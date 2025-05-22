# Chat TCP em Java

Uma aplicação de bate-papo simples entre cliente e servidor, utilizando sockets TCP em Java. O projeto demonstra como criar uma comunicação bidirecional, onde ambos os lados podem enviar e receber mensagens simultaneamente.

**Tecnologias utilizadas:**

- Java (JDK 8+)
- Programação com Sockets TCP
- Terminal (linha de comando)
- Máquina Virtual (Linux) para execução do servidor

Ideal para quem deseja aprender os fundamentos da comunicação em rede usando Java.

---

## 💻 Como instalar e rodar o projeto

Este projeto é composto por duas aplicações: **Servidor** e **Cliente**, ambos desenvolvidos em Java utilizando Sockets TCP.

## 🛠️ Pré-requisitos

- Java JDK 8 ou superior instalado
- Git (opcional, se for clonar o repositório)
- IDE (como NetBeans, IntelliJ ou VS Code com suporte a Java)
- Máquina virtual Linux (ou outro computador) para simular o servidor
- Terminal (cmd, PowerShell ou bash)

---

### 📥 1. Clonar o repositório

Se estiver usando Git, abra o terminal e clone este repositório:

bash git clone
[https://github.com/limabarreto/projeto.git]


### 📦 2. Compilar os arquivos Java

**No servidor (na VM Linux):**

cd ServidorTCP
javac Servidor.java

**No cliente (no Windows):**

cd ClienteTCP
javac Cliente.java

### ▶️ 3. Executar o projeto

Passo 1: Inicie o servidor na VM (ou outro computador com Linux):

java Servidor

O servidor ficará escutando na porta especificada (exemplo: 12345).

Passo 2: Inicie o cliente no Windows:

java Cliente

O cliente irá se conectar ao IP e porta do servidor.

    💡 Certifique-se de que a máquina virtual esteja acessível via rede (use ip a no Linux para obter o IP da VM).

### 🧪 4. Teste de conexão

    #Envie uma mensagem no terminal do cliente.

    #Veja a resposta aparecendo no terminal do servidor.

    #Ambos podem conversar em tempo real via terminal.

### 🐛5. Problemas comuns

    #Erro de conexão: Verifique o IP e a porta do servidor.

    #Firewall bloqueando: Permita conexões na porta usada (ex: 12345).

    #Java não reconhecido: Verifique se o Java está instalado e configurado no PATH.
