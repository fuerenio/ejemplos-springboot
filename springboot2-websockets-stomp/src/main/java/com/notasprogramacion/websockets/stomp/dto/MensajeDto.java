package com.notasprogramacion.websockets.stomp.dto;

import java.io.Serializable;
import java.util.List;

public class MensajeDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4321333031467621379L;
	private String name;
	private String type;
	private List<Integer> data;

	public MensajeDto() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Integer> getData() {
		return data;
	}

	public void setData(List<Integer> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "MensajeDto [name=" + name + ", type=" + type + ", data=" + data + "]";
	}

}
