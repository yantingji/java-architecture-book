package com.itedu365.springframework.context.support;

public class PropertyDefinition {

	private String name;
	private String ref;

	public PropertyDefinition(String name, String ref) {
		this.name = name;
		this.ref = ref;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

}
