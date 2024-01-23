package tw.chen.midproject.dao;

public class CancerHospitalDaoFactory {
	public static ICancerHospitalDao creaCancerHospitalDaoFactory() {
		return new CancerHospitalDaoJdbcImpl();
	}
}
