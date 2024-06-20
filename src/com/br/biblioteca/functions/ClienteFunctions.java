package com.br.biblioteca.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.br.biblioteca.model.Livro;

public class ClienteFunctions {
	
	private final Scanner sc = new Scanner(System.in); // Scanner para leitura de entrada do usuário

	// Método para solicitar a listagem dos livros disponíveis
	public void reqGetLivros(BufferedReader in, BufferedWriter out) throws IOException {
		String linha;
        System.out.println("Livros disponíveis:");
        while ((linha = in.readLine()) != null && !linha.equals("END")) {
            System.out.println(linha);
            if (linha.contains("exemplares"))
            	System.out.println("\n");
        }

        if (linha.equals("END")) {
            System.out.println("Fim da lista de livros.\n");
        }
	}

	// Método para solicitar a adição de um novo livro
	public void reqAddLivros(BufferedReader in, BufferedWriter out) throws IOException {
		System.out.printf("Título: ");
		out.write(sc.nextLine().strip());
		out.newLine();
		
		System.out.printf("Autor: ");
		out.write(sc.nextLine().strip());
		out.newLine();
		
		System.out.printf("Genero: ");
		out.write(sc.nextLine().strip());
		out.newLine();
		
		System.out.printf("Exemplares: ");
		out.write(sc.nextLine().strip());
		out.newLine();
		out.flush();
		
		System.out.println("\n\n" + in.readLine() + "\n");
	}

	// Método para solicitar o aluguel de um livro
	public void reqAlugaLivro(BufferedWriter out, BufferedReader in) throws IOException {
		System.out.printf("Título: ");
		out.write(sc.nextLine().strip());
		out.newLine();
		
		System.out.printf("Autor: ");
		out.write(sc.nextLine().strip());
		out.newLine();
		out.flush();
		
		System.out.println("\n\n" + in.readLine()+ "\n");
	}

	// Método para solicitar a devolução de um livro
	public void reqDevolveLivro(BufferedReader in,BufferedWriter out) throws IOException {
		System.out.printf("Título: ");
		out.write(sc.nextLine().strip());
		out.newLine();
		
		System.out.printf("Autor: ");
		out.write(sc.nextLine().strip());
		out.newLine();
		out.flush();
		
		System.out.println("\n\n" + in.readLine() + "\n");
	}

	// Método para terminar a aplicação
	public void resTerminaAplicacao(BufferedReader in) throws IOException {
		System.out.println("\n" + in.readLine());
	}
	
}
