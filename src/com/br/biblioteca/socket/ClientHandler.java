package com.br.biblioteca.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.br.biblioteca.functions.ServidorFunctions;

public class ClientHandler implements Runnable{

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ServidorFunctions servidorFunctions = new ServidorFunctions();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            int opcao;
            do {
                opcao = in.read();

                switch (opcao) {
                    case 1: servidorFunctions.getLivros(out); break;
                    case 2: servidorFunctions.alugarLivro(in.readLine(), in.readLine(), out); break;
                    case 3: servidorFunctions.devolverLivro(in.readLine(), in.readLine(), out); break;
                    case 4: servidorFunctions.AddLivro(in, out); break;
                    case 0: servidorFunctions.terminaAplicacao(out); break;
                    default:
                        System.out.println("outro");
                        break;
                }

            } while (opcao != 0);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}