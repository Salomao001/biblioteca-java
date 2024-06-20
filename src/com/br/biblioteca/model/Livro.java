package com.br.biblioteca.model;

import com.google.gson.annotations.SerializedName;

public class Livro {
	@SerializedName("titulo")
	private String titulo;
	@SerializedName("autor")
	private String autor;
	@SerializedName("genero")
	private String genero;
	@SerializedName("exemplares")
	private int exemplares;

	// Construtor para criar um novo livro.
	public Livro(String titulo, String autor, String genero, int exemplares) {
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.exemplares = exemplares;
	}

	// Getters e Setters.
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getExemplares() {
		return exemplares;
	}

	public void setExemplares(int exemplares) {
		this.exemplares = exemplares;
	}
	
	
}
