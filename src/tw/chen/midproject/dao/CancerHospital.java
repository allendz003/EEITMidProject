package tw.chen.midproject.dao;

import java.io.Serializable;

//JavaBean
public class CancerHospital implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String city;
	private String hospitalLevel;
	private String hospitalName;
	private int postCode;
	private String hospitalAddress;

	public CancerHospital() {
	}

	public CancerHospital(String city, String hospitalLevel, String hospitalName, int postCode,
			String hospitalAddress) {
		super();
		this.city = city;
		this.hospitalLevel = hospitalLevel;
		this.hospitalName = hospitalName;
		this.postCode = postCode;
		this.hospitalAddress = hospitalAddress;
	}

	public CancerHospital(int id, String city, String hospitalLevel, String hospitalName, int postCode,
			String hospitalAddress) {
		super();
		this.id = id;
		this.city = city;
		this.hospitalLevel = hospitalLevel;
		this.hospitalName = hospitalName;
		this.postCode = postCode;
		this.hospitalAddress = hospitalAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHospitalLevel() {
		return hospitalLevel;
	}

	public void setHospitalLevel(String hospitalLevel) {
		this.hospitalLevel = hospitalLevel;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

}
