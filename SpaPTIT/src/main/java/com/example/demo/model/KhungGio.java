package com.example.demo.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
public class KhungGio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private Time gio;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Time getGio() {
		return gio;
	}
	public void setGio(Time gio) {
		this.gio = gio;
	}
	public KhungGio(int id, Time gio) {
		super();
		this.id = id;
		this.gio = gio;
	}
	public KhungGio() {
		super();
	}
	
	
}
