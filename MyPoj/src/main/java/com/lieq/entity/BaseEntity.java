package com.lieq.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public class BaseEntity {
	
	/**
	 * 主键自增 initialValue=1 从1开始
	 */
	@Id
	@SequenceGenerator(name="sequence", sequenceName="LEQ_SEQUENCE", allocationSize=1, initialValue=1)
	@GeneratedValue(generator="sequence", strategy=GenerationType.SEQUENCE)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
