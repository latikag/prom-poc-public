package com.example.timescaledb;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "slo_error_budget")
public class SloError {
	private double threshold;
	private double sli_value;
	//left
	private double error_budget;
	private long slo_id;
	private OffsetDateTime time;
	private long id;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public double getSli_value() {
		return sli_value;
	}

	public void setSli_value(double sli_value) {
		this.sli_value = sli_value;
		error_budget = (sli_value - threshold)*100;
	}

	public double getError_budget() {
		return error_budget;
	}

	public void setError_budget(double error_budget) {
		this.error_budget = error_budget;
	}

	public long getSlo_id() {
		return slo_id;
	}

	public void setSlo_id(long slo_id) {
		this.slo_id = slo_id;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	public OffsetDateTime getTime() {
		return time;
	}

	public void setTime(OffsetDateTime time) {
		this.time = time;
	}

}
