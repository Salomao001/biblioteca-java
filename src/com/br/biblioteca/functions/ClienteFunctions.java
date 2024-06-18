package com.br.biblioteca.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import com.br.biblioteca.model.Livro;

public class ClienteFunctions {
	
	private final Scanner sc = new Scanner(System.in);
	
	public void reqGetLivros(ObjectInputStream in, ObjectOutputStream out, int opcao) throws IOException {
		out.write(opcao);
		out.flush();

		String linha;
		try {
			while((linha = in.readObject().toString()) != null) {
				System.out.println(linha);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reqAddLivros() {
		
		
		Livro livro = new Livro("Quando Nietzsche chorou", "Irvin d yarlim", "Romance", 3);
	}
}
