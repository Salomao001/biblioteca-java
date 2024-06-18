package com.br.biblioteca.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ServidorFunctions {

	private final String pathFile = "./src/livros.json";
	
	public void getLivros(ObjectOutputStream out) {
		try {
			FileReader file = new FileReader(pathFile);
			BufferedReader in = new BufferedReader(file);
		
			String linha;
			out.writeObject("Livros: \n");
			while ((linha = in.readLine()) != null) {
				if (linha.contains("titulo")) 
					out.writeObject(linha + "\n");
				if (linha.contains("autor")) 
					out.writeObject(linha + "\n");
				if (linha.contains("genero")) 
					out.writeObject(linha + "\n");
				if (linha.contains("exemplares")) 
					out.writeObject(linha + "\n");
			}
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
