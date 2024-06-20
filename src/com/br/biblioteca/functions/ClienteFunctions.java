package com.br.biblioteca.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.br.biblioteca.model.Livro;

public class ClienteFunctions {
	
	private final Scanner sc = new Scanner(System.in);
	
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
	
	public void resTerminaAplicacao(BufferedReader in) throws IOException {
		System.out.println("\n" + in.readLine());
	}
	
}
