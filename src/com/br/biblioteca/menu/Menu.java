package com.br.biblioteca.menu;

import java.util.Scanner;

public class Menu {
	
	public int exibirMenu() {
		System.out.println(" Biblioteca de livros");
		System.out.println("-----------------------");
		System.out.println("| Operações:          |");
		System.out.println("| Listar livros (1)   |");
		System.out.println("| Alugar livro (2)    |");
		System.out.println("| Devolver livro (3)  |");
		System.out.println("| Cadastrar livro (4) |");
		System.out.println("| Sair (0)            |");
		System.out.println("-----------------------");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("Sua opção: ");
		return sc.nextInt();
	}
}
