package com.br.amaro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductController {

	public String readFile() {
		String result = "";
		try {
	        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Momberg\\Documents\\Amaro\\produtos.txt"));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	        result = sb.toString();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	public List<Product> parseToProduct(String file) {
		JSONObject jobj = new JSONObject(file);
	    JSONArray jarray = new JSONArray(jobj.getJSONArray("products").toString());
	    List<Product> products = new ArrayList<Product>();
	    Product product;
	    for(int i = 0; i < jarray.length(); i++) {
	        try {
	        	List<String> tags = new ArrayList<String>();
	        	JSONArray tag = new JSONArray(jarray.getJSONObject(i).getJSONArray("tags").toString());
	        	for(int x = 0; x < tag.length(); x++) {
	        		tags.add(tag.getString(x));
	        	}
	        	product = new Product(jarray.getJSONObject(i).getInt("id"),
	        			jarray.getJSONObject(i).getString("name"),
	        			tags);
	        	products.add(product);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
	    }
	    return products;
	}
	
	public void compareCharacteristics(List<Product> products) {
		boolean exists = false;
		List<Integer> tagsVector = null;
		for (Product product : products) {
			tagsVector = new ArrayList<Integer>();
			for (Characteristics characteristics : Characteristics.values()) {
				exists = false;
				for (String tag : product.getTags()) {
					if(tag.trim().toLowerCase().equals(characteristics.toString().toLowerCase())) {
						exists = true;
					}
				}
				if(exists) {
					tagsVector.add(1);
				} else {
					tagsVector.add(0);
				}
			}
			product.setTagsVector(tagsVector);
		}
	}
	
	public void createFile(List<Product> products) {
		JSONArray file = new JSONArray();
		JSONObject obj;
		for (Product product : products) {
			obj = new JSONObject();
			obj.put("id", product.getId());
			obj.put("name", product.getName());
			obj.put("tags", product.getTags());
			obj.put("tagsVector", product.getTagsVector());
			file.put(obj);
		}
		obj = new JSONObject();
		obj.put("products", file);
		try {
			FileWriter writeFile = new FileWriter("C:\\Users\\Momberg\\Documents\\Amaro\\produtosComAdicional.txt");
			writeFile.write(obj.toString());
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
