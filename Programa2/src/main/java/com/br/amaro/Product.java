package com.br.amaro;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;

public class Product {

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
	
	public void parseToJson(String file) {
		JSONObject jobj = new JSONObject(file);
	    JSONArray jarr = new JSONArray(jobj.getJSONArray("products").toString());
	    for(int i = 0; i < jarr.length(); i++) {
	        System.out.println("Keyword: " + jarr.getString(i));
	    }
	}
	
}
