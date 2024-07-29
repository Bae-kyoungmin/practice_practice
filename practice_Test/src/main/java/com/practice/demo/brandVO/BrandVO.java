package com.practice.demo.brandVO;

public class BrandVO {
	private String name;
	private int since;
	
	public BrandVO(String name, int since) {
		this.name = name;
		this.since = since;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSince() {
		return since;
	}
	public void setSince(int since) {
		this.since = since;
	}

	@Override
	public String toString() {
		return "{name=" + name + ", since=" + since + "}";
	}
	
}


