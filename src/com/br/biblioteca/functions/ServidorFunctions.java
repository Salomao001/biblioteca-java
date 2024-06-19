package com.br.biblioteca.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.br.biblioteca.model.Livro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServidorFunctions {

	private final String pathFile = "./src/livros.json";
	
	public void getLivros(BufferedWriter out) {
		try {
			FileReader file = new FileReader(pathFile);
			BufferedReader in = new BufferedReader(file);
		
			String linha;
			out.write("Livros: \n");
			while ((linha = in.readLine()) != null) {
				if (linha.contains("titulo")) 
					out.write(linha + "\n");
				if (linha.contains("autor")) 
					out.write(linha + "\n");
				if (linha.contains("genero")) 
					out.write(linha + "\n");
				if (linha.contains("exemplares")) 
					out.write(linha + "\n");
			}
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void AddLivro(BufferedReader in, BufferedWriter out) throws IOException {
		
		String jsonString = readFile(pathFile);
		
		JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
		
		String titulo = in.readLine();
		String autor = in.readLine();
		String genero = in.readLine();
		int exemplares = Integer.valueOf(in.readLine());
		
		addOrUpdateLivro(jsonObject, titulo, autor, genero, exemplares);
        
        writeFile(pathFile, jsonObject);
        
        
        
        out.write("Livro adicionado com sucesso!");
	}
	
	public String readFile(String path) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = bf.readLine()) != null) {
                contentBuilder.append(linha);
                contentBuilder.append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return contentBuilder.toString();
    }
	
	public void writeFile(String path, JsonObject jsonObject) {
		try {
			FileWriter file = new FileWriter(path);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String jsonString = gson.toJson(jsonObject);
			System.out.println(jsonString);
			file.write(jsonString);
			file.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOrUpdateLivro(JsonObject jsonObject, String titulo, String autor, String genero, int exemplares) {
		JsonArray livros = jsonObject.getAsJsonArray("livros");
		boolean livroExistente = false;

		for (int i = 0; i < livros.size(); i++) {
            JsonObject livro = livros.get(i).getAsJsonObject();
            if (livro.get("titulo").getAsString().equals(titulo) && livro.get("autor").getAsString().equals(autor)) {
                livro.addProperty("exemplares", livro.get("exemplares").getAsInt() + exemplares);
                livroExistente = true;
                break;
            }
        }
        
		if (!livroExistente) {	
			Livro novoLivro = new Livro(titulo, autor, genero, exemplares);
			Gson gson = new Gson();
			JsonObject novoLivroJson = gson.toJsonTree(novoLivro).getAsJsonObject();
			livros.add(novoLivroJson);
		}
    }
}
