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
import com.br.biblioteca.model.Livro;

public class ClienteSocket {

	public static void main(String[] args) {

		ClienteFunctions clienteFunctions = new ClienteFunctions(); // Instância das funções do cliente
		
		try {
			Socket socket = new Socket("localhost", 12345); // Conecta ao servidor na porta 12345
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));// Leitor para receber dados do servidor
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));// Escritor para enviar dados ao servidor
			
			int opcao = 0;
			do {
				opcao = new Menu().exibirMenu(); // Exibe o menu e lê a opção do usuário
				out.write(opcao); // Envia a opção escolhida para o servidor
				out.flush(); // Garante que os dados sejam enviados

				// Executa a função correspondente à opção escolhida
				switch (opcao) {
				case 1:
					clienteFunctions.reqGetLivros(in, out); // Solicita listagem dos livros
					break;
				case 2:
					clienteFunctions.reqAlugaLivro(out, in);// Solicita aluguel de um livro
					break;
				case 3:
					clienteFunctions.reqDevolveLivro(in, out);// Solicita devolução de um livro
					break;
				case 4:
					clienteFunctions.reqAddLivros(in, out); // Solicita adição de um novo livro
					break;
				case 0:
					clienteFunctions.resTerminaAplicacao(in); // Solicita término da aplicação
					break;
				default:
					break;
				}
				
			} while (opcao != 0);

			socket.close(); // Fecha o socket após o término da aplicação
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
