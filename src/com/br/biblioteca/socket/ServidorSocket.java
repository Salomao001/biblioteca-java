package com.br.biblioteca.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.br.biblioteca.functions.ServidorFunctions;

public class ServidorSocket {
	
	public static void main(String[] args ) {
		ServidorFunctions servidorFunctions = new ServidorFunctions();
		ServerSocket server;
		try {
			server = new ServerSocket(12345);
			Socket socket = server.accept();
			System.out.println("servidor conectado na porta " + socket.getLocalPort() + " ip " + socket.getInetAddress().getHostAddress());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			int opcao;
			do {
				opcao = in.read();
				
				switch (opcao) {
				case 1: servidorFunctions.getLivros(out); break;
				case 2: servidorFunctions.alugarLivro(in.readLine(), in.readLine()); break;
				case 3: servidorFunctions.devolverLivro(in.readLine(), in.readLine()); break;
				case 4: servidorFunctions.AddLivro(in, out); break;
				case 0: System.out.println("opcao 0" ); break;
				default:
					System.out.println("outro");
					break;
				}
				
			} while (opcao != 0);
			socket.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
