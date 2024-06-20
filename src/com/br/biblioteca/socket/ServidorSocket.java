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
		try (ServerSocket serverSocket = new ServerSocket(12345)) { // Cria um ServerSocket na porta 12345
			System.out.println("Servidor iniciado na porta " + 12345);

			while (true) {
				Socket clientSocket = serverSocket.accept(); // Aguarda uma conexão de um cliente
				System.out.println("Nova conexão aceita: " + clientSocket.getInetAddress().getHostAddress());

				new Thread(new ClientHandler(clientSocket)).start();// Cria uma nova thread para lidar com o cliente
			}
		} catch (IOException e) {
			e.printStackTrace(); // Trata exceções de entrada/saída
		}
	}
}