package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Table
@Entity
@Data
public class NguyenLieuDichVu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private int id;
	@ManyToOne
	@JoinColumn
	private DichVu dichvu;
	@ManyToOne
	@JoinColumn
	private NguyenLieu nguyenlieu;
}
