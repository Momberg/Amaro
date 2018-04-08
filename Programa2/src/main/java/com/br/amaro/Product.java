package com.br.amaro;

import java.util.List;

public class Product implements Comparable<Product> {
	
	private Integer id;
	private String name;
	private List<String> tags;
	private List<Integer> tagsVector;
	private double S;
	
	public Product(Integer id, String name, List<String> tags, List<Integer> tagsVector) {
		this.id = id;
		this.name = name;
		this.tags = tags;
		this.tagsVector = tagsVector;
	}
	
	public Product() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<Integer> getTagsVector() {
		return tagsVector;
	}

	public void setTagsVector(List<Integer> tagsVector) {
		this.tagsVector = tagsVector;
	}

	public double getS() {
		return S;
	}

	public void setS(double s) {
		S = s;
	}

	public int compareTo(Product o) {
		return 0;
	}

}
