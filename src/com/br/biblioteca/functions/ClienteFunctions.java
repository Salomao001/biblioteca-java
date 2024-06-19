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
        }


        if (linha.equals("END")) {
            System.out.println("Fim da lista de livros.");
        }
	}
	
	public void reqAddLivros(BufferedReader in, BufferedWriter out) throws IOException {
		
		System.out.printf("Título: ");
		out.write("Quando Nietzsche chorou");
		out.newLine();
		
		System.out.printf("Autor: ");
		out.write("Irvin D Yalom");
		//out.write(sc.nextLine());
		out.newLine();
		
		System.out.printf("Genero: ");
		out.write("Romance");
		//out.write(sc.nextLine());
		out.newLine();
		
		System.out.printf("Exemplares: ");
		out.write("3");
		//out.write(sc.nextLine());
		out.newLine();
		out.flush();
		
		System.out.println("\n" + in.readLine());
	}
	
	public void reqAlugaLivro(BufferedWriter out) throws IOException {
		System.out.printf("Título: ");
		out.write("Cartas Chilenas");
		out.newLine();
		
		System.out.printf("Autor: ");
		out.write("Tomás Antônio Gonzaga");
		out.newLine();
		out.flush();
	}
	
	public void reqDevolveLivro(BufferedWriter out) throws IOException {
		System.out.printf("Título: ");
		out.write("Cartas Chilenas");
		out.newLine();
		
		System.out.printf("Autor: ");
		out.write("Tomás Antônio Gonzaga");
		out.newLine();
		out.flush();
	}
	
	public void reqTerminaAplicacao(BufferedWriter out) {
		
	}
	
}
