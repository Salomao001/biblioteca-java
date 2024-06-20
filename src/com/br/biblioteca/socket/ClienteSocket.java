package com.br.biblioteca.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.br.biblioteca.functions.ClienteFunctions;
import com.br.biblioteca.menu.Menu;

public class ClienteSocket {

	public static void main(String[] args) {
		ClienteFunctions clienteFunctions = new ClienteFunctions(); // Cria uma instância de ClienteFunctions para manipular as operações do cliente

		try {
			Socket socket = new Socket("localhost", 12345); // Cria um socket para se conectar ao servidor na porta 12345 do localhost

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Cria um BufferedReader para ler dados do servidor
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // Cria um BufferedWriter para enviar dados ao servidor

			int opcao = 0;
			do {
				opcao = new Menu().exibirMenu();// Exibe o menu e obtém a opção do usuário
				out.write(opcao);

				out.flush(); // Garante que os dados sejam enviados imediatamente

				switch (opcao) {
					case 1:
						clienteFunctions.reqGetLivros(in, out);
						break;
					case 2:
						clienteFunctions.reqAlugaLivro(out, in);
						break;
					case 3:
						clienteFunctions.reqDevolveLivro(in, out);
						break;
					case 4:
						clienteFunctions.reqAddLivros(in, out);
						break;
					case 0:
						clienteFunctions.resTerminaAplicacao(in);
						break;
					default:
						System.out.println("Opção inválida. Por favor, escolha uma opção válida do menu.");
						break;
				}

			} while (opcao != 0);

			socket.close(); // Fecha o socket após o término da aplicação
		} catch (UnknownHostException e) {
			e.printStackTrace(); // Imprime a pilha de erros se o host for desconhecido
		} catch (IOException e) {
			e.printStackTrace(); // Imprime a pilha de erros para exceções de E/S
		}

	}

}
