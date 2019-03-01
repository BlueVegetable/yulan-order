package com.yulan.pojo;

import java.util.Objects;

public class ProductGroupType {

	private String id;
	private String name;
	private String value;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id=id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ProductGroupType)) return false;
		ProductGroupType that = (ProductGroupType) o;
		return Objects.equals(getId(), that.getId()) &&
				Objects.equals(getName(), that.getName()) &&
				Objects.equals(getValue(), that.getValue());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getName(), getValue());
	}

	@Override
	public String toString() {
		return "ActivityGroupType{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", value='" + value + '\'' +
				'}';
	}
}