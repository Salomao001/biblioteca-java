package com.br.biblioteca.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	public int exibirMenu() {
		Scanner sc = new Scanner(System.in);
		int opcao = -1; // Inicializa com um valor inválido para garantir que o loop funcione

		while (true) {
			System.out.println(" Biblioteca de livros");
			System.out.println("-----------------------");
			System.out.println("| Operações:          |");
			System.out.println("| Listar livros (1)   |");
			System.out.println("| Alugar livro (2)    |");
			System.out.println("| Devolver livro (3)  |");
			System.out.println("| Cadastrar livro (4) |");
			System.out.println("| Sair (0)            |");
			System.out.println("-----------------------");

			System.out.printf("Sua opção: ");
			try {
				opcao = sc.nextInt();
				break; // Se a leitura for bem-sucedida, sai do loop
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida. Por favor, insira um número (0-4).");
				sc.next(); // Consome a entrada inválida
			}
		}

		return opcao;
	}
}
