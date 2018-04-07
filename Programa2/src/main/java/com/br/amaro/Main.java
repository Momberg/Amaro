package com.br.amaro;

public class Main {

	public static void main(String[] args) {
		Product product = new Product();
		String teste = "";
		teste = product.readFile();
		System.out.println(teste);
		product.parseToJson(teste);

	}

}
