package com.br.amaro;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductController {

	public String readFile(String args) {
		String result = "";
		try {
	        BufferedReader br = new BufferedReader(new FileReader(args));
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
	        	List<Integer> tagsVector = new ArrayList<Integer>();
	        	JSONArray tag = new JSONArray(jarray.getJSONObject(i).getJSONArray("tags").toString());
	        	JSONArray tagVector = null;
	        	if(jarray.getJSONObject(i).has("tagsVector")) {
	        		tagVector = new JSONArray(jarray.getJSONObject(i).getJSONArray("tagsVector").toString());
	        	}
	        	for(int x = 0; x < tag.length(); x++) {
	        		tags.add(tag.getString(x));
	        	}
	        	if(null != tagVector) {
		        	for(int x = 0; x < tagVector.length(); x++) {
		        			tagsVector.add(tagVector.getInt(x));
		        	}
	        	}
	        	product = new Product(jarray.getJSONObject(i).getInt("id"),
	        			jarray.getJSONObject(i).getString("name"),
	        			tags, 
	        			tagsVector);
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
			FileWriter writeFile = new FileWriter("C:\\Users\\gabriel.d.momberg\\Documents\\git\\produtosComAdicional.txt");
			writeFile.write(obj.toString());
			writeFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void findById(String args) {
		String result = "";
		int id = 0;
		id = Integer.valueOf(args);
		result = readFile("C:\\Users\\gabriel.d.momberg\\Documents\\git\\produtosComAdicional.txt");
		List<Product> products = new ArrayList<Product>();
	    products = parseToProduct(result);
	    for (Product product : products) {
			if(id == product.getId()) {
				System.out.println("Os três produtos mais similares ao produto " + product.getId() + " (" + product.getName() + ") sao:");
				calculateProducts(products, product);
			}
		}
	}
	
	private void calculateProducts(List<Product> products, Product product) {
		double S = 0;
		double D = 0;
		List<Product> finalPrds = new ArrayList<Product>();
		List<Integer> v1 = new ArrayList<Integer>();
		List<Integer> v2 = new ArrayList<Integer>();
		for (Product prd : products) {
			D = 0;
			S = 0;
			if(product.getId() != prd.getId()) {
				v1 = product.getTagsVector();
				v2 = prd.getTagsVector();
				for(int x = 0; x < product.getTagsVector().size(); x++) {
					D += Math.pow((v1.get(x) - v2.get(x)), 2);
				}
				S = 1/(1 + Math.sqrt(D));
				prd.setS(S);
				finalPrds.add(prd);
			}
		}
		Collections.sort(finalPrds);
		for(int x = 0; x < 3; x++) {
			System.out.println(finalPrds.get(x).getId() + " " + finalPrds.get(x).getName() + String.format( " com S= %.2f", finalPrds.get(x).getS() ));
		}
	}
	
}
