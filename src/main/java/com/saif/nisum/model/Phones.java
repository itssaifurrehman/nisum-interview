package com.saif.nisum.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int phoneId;
	private String number;
	private String citycode;
	private String contrycode;

	@ManyToOne
	@JoinColumn(name = "FK_phone", updatable = false)
	private UserDTO userDTO;

	public int getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getContrycode() {
		return contrycode;
	}

	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}

	@Override
	public String toString() {
		return String.format("Phones [phoneId=%s, number=%s, citycode=%s, contrycode=%s]", phoneId, number, citycode,
				contrycode);
	}

}
