package com.br.biblioteca.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.br.biblioteca.functions.ServidorFunctions;

public class ClientHandler implements Runnable{

    private Socket socket; // Socket para comunicação com o cliente

    // Construtor que recebe o socket do cliente
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // Cria uma instância de ServidorFunctions para manipular as operações do servidor
        try {
            ServidorFunctions servidorFunctions = new ServidorFunctions();

            // Cria leitores e escritores para comunicação com o cliente
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            int opcao;
            do {
                // Lê a opção enviada pelo cliente
                opcao = in.read();

                // Executa a operação correspondente à opção lida
                switch (opcao) {
                    case 1: servidorFunctions.getLivros(out);// Lista os livros
                    break;
                    case 2: servidorFunctions.alugarLivro(in.readLine(), in.readLine(), out);// Aluga um livro
                    break;
                    case 3: servidorFunctions.devolverLivro(in.readLine(), in.readLine(), out);// Devolve um livro
                    break;
                    case 4: servidorFunctions.AddLivro(in, out);// Adiciona um novo livro
                    break;
                    case 0: servidorFunctions.terminaAplicacao(out);// Termina a aplicação
                    break;
                    default:
                        System.out.println("outro");
                        break;
                }

            } while (opcao != 0); // Continua até o cliente enviar a opção 0
            socket.close(); // Fecha o socket após a comunicação
        } catch (IOException e) {
            e.printStackTrace(); // Trata exceções de entrada/saída
        }
    }
}