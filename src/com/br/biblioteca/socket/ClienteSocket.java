package com.br.biblioteca.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.br.biblioteca.functions.ClienteFunctions;
import com.br.biblioteca.menu.Menu;
import com.br.biblioteca.model.Livro;

public class ClienteSocket {

	public static void main(String[] args) {
		int opcao = new Menu().exibirMenu();
		ClienteFunctions clienteFunctions = new ClienteFunctions();
		
		try {
			Socket socket = new Socket("localhost", 12345);
			
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			
			Livro livro = new Livro("Quando Nietzsche chorou", "Irvin d yarlim", "Romance", 3);
			out.writeObject(livro);
			switch (opcao) {
			case 1:
				clienteFunctions.reqGetLivros(in, out, opcao);
				break;
			case 2:
				System.out.println("opcao 2");
				break;
			case 3:
				System.out.println("opcao 3");
				break;
			case 4:
				System.out.println("opcao 4");
				break;
			case 0:
				System.out.println("opcao 0");
				break;
			default:
				break;
			}
			
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
