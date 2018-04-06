package com.br.amaro;

public class Main {

	public static void main(String[] args) {
		
		Square squareFile = new Square();
		int[][] matrix = squareFile.readFile();
		if(squareFile.isAPerfectSquare(matrix)) {
			System.out.println("E um quadrado perfeito");
		} else {
			System.out.println("Nao e um quadrado perfeito");
		}
	}

}
