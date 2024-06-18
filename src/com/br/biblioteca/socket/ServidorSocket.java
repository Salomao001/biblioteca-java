package com.br.biblioteca.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.br.biblioteca.functions.ServidorFunctions;
import com.br.biblioteca.model.Livro;

public class ServidorSocket {
	
	public static void main(String[] args ) {
		ServidorFunctions servidorFunctions = new ServidorFunctions();
		ServerSocket server;
		try {
			server = new ServerSocket(12345);
			Socket socket = server.accept();
			System.out.println("servidor conectado na porta " + socket.getLocalPort() + " ip " + socket.getInetAddress().getHostAddress());
			
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			try {
				System.out.println(in.readObject());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int opcao = in.read();
			
			switch (opcao) {
			case 1: servidorFunctions.getLivros(out);; break;
			case 2: System.out.println("opcao 2" ); break;
			case 3: System.out.println("opcao 3" ); break;
			case 4: System.out.println("opcao 4" ); break;
			case 0: System.out.println("opcao 0" ); break;
			default:
				System.out.println("outro");
				break;
			}
			
			socket.close();
			server.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
