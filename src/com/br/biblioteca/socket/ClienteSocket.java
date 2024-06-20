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
		ClienteFunctions clienteFunctions = new ClienteFunctions();

		try {
			Socket socket = new Socket("localhost", 12345);

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			int opcao = 0;
			do {
				opcao = new Menu().exibirMenu();
				out.write(String.valueOf(opcao));
				out.newLine(); // Adiciona uma nova linha para garantir que o dado seja lido corretamente pelo servidor
				out.flush();

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

			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
