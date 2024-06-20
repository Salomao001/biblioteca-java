package com.br.biblioteca.functions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.br.biblioteca.model.Livro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ServidorFunctions {

	private final String pathFile = "./src/livros.json"; // Caminho do arquivo JSON
	private List<Livro> livrosAlugados = new ArrayList<>(); // Cria uma Lista para livros alugados

	// Método para listar os livros disponíveis
	public void getLivros(BufferedWriter out) {
		System.out.println("Servidor: Listando livros\n");
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
			out.write("END\n");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método para adicionar um novo livro
	public void AddLivro(BufferedReader in, BufferedWriter out) throws IOException {
		System.out.println("Servidor: Cadastrando um livro novo ou existente\n");
		
		String jsonString = readFile(pathFile);
		
		JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
		
		String titulo = in.readLine();
		String autor = in.readLine();
		String genero = in.readLine();
		int exemplares = Integer.valueOf(in.readLine());
		
		addOrUpdateLivro(jsonObject, titulo, autor, genero, exemplares);
        
        writeFile(pathFile, jsonObject);
        
        
        out.write("Livro adicionado com sucesso!\n");
        out.flush();
	}

	// Método para alugar um livro
	public void alugarLivro(String titulo, String autor, BufferedWriter out) throws IOException {
		System.out.println("Servidor: Cliente alugando livro\n");
		
		boolean livroExiste = false;
        String jsonString = readFile(pathFile);
		
        if (jsonString != null) {
            JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonArray livros = jsonObject.getAsJsonArray("livros");

            for (int i = 0; i < livros.size(); i++) {
                JsonObject livro = livros.get(i).getAsJsonObject();
                if (livro.get("titulo").getAsString().equals(titulo) && livro.get("autor").getAsString().equals(autor)) {
                	livroExiste = true;
                    int exemplares = livro.get("exemplares").getAsInt();
                    if (exemplares > 0) {
                        livro.addProperty("exemplares", exemplares - 1);
                        Livro livroAlugado = new Livro(titulo, autor, "", 1);
                        livrosAlugados.add(livroAlugado);
                        out.write("Livro alugado com sucesso!\n");
                        out.flush();
                    } else {
                        out.write("Nenhum exemplar disponível para aluguel.\n");
                        out.flush();
                    }
                    break;
                }
            }
            if (!livroExiste)
            	out.write("Este livro Não está no catálogo\n");
            	out.flush();
            writeFile(pathFile, jsonObject);
        }
    }

	// Método para devolver um livro
	public void devolverLivro(String titulo, String autor, BufferedWriter out) throws IOException {
		System.out.println("Servidor: Cliente devolvendo livro\n");
        String jsonString = readFile(pathFile);
        if (jsonString != null) {
            JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
            JsonArray livros = jsonObject.getAsJsonArray("livros");

            boolean livroAlugado = false;
            for (Livro livro : livrosAlugados) {
            	if (livro.getTitulo().equals(titulo) && livro.getAutor().equals(autor)) {
            		for (int i = 0; i < livros.size(); i++) {
            			JsonObject livroJ = livros.get(i).getAsJsonObject();
            			if (livroJ.get("titulo").getAsString().equals(titulo) && livroJ.get("autor").getAsString().equals(autor)) {
            				int exemplares = livroJ.get("exemplares").getAsInt();
            				livroJ.addProperty("exemplares", exemplares + 1);
            				break;
            			}
            		}
            		livrosAlugados.remove(livro);
            		livroAlugado = true;
                    break;
            	}
            }
            
            if (livroAlugado) {
            	writeFile(pathFile, jsonObject);
            	out.write("Livro devolvido com sucesso!\n");
            	out.flush();
            } else {
            	out.write("Voce não alugou este livro!\n");
            	out.flush();
            }
        }
    }

	// Método para ler o JSON como uma String
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

	// Método para escrever no arquivo JSON
	public void writeFile(String path, JsonObject jsonObject) {
		try {
			FileWriter file = new FileWriter(path);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String jsonString = gson.toJson(jsonObject);
			file.write(jsonString);
			file.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Método para adicionar ou atualizar um livro no arquivo JSON
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

	// Método para terminar a aplicação
	public void terminaAplicacao(BufferedWriter out) throws IOException {
		System.out.println("Servidor: Finalizando aplicação\n");
		if (livrosAlugados.isEmpty()) {
			out.write("Sessão finalizada.\n");
			out.flush();
		} else {
			out.write("Voce pegou livros e não devolveu??? Vai ser cobrado!!\n");
			out.flush();
		}
	}
}
