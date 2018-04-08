package com.br.amaro;

import java.util.ArrayList;
import java.util.List;

public class BuscarProdutosSimilares {

	public static void main(String[] args) {
		ProductController productController = new ProductController();
		List<Product> products = new ArrayList<Product>();
		String teste = "";
		teste = productController.readFile(args[0]);
		products = productController.parseToProduct(teste);
		productController.compareCharacteristics(products);
		productController.createFile(products);
		productController.findById(args[1]);
	}

}
