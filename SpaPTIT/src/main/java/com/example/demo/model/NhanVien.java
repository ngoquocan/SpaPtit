package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class NhanVien extends ThanhVien{
	private String vitri;
	
}
