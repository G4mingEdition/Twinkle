/**
	@author
		• Genco Luca
**/

package be.twinkle.daoimpl;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import be.twinkle.dao.Dao;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "persistance")
public class Persistance {
	public static String MOCK = "MOCK";
	public static String DB = "DB";

	@XmlAttribute
	private String type;

	@XmlElement(name = "dao")
	private Set<String> daos = new HashSet<String>();

	private String driver;

	private String url;

	private String user;

	private String password;

	public Persistance() {
		super();
	}

	public void configurer() {
		if (this.type.equals(DB)) {
			try {
				Class.forName(this.driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	
	@SuppressWarnings("unchecked")
	public Dao getDaoImpl(Class<? extends Dao> interfaceDao) {
		Dao dao = null;
		Class<Dao> classeDaoImpl;
		Class<?>[] interfaces;
		try {
			for (String nomDaoImpl : daos) {
				classeDaoImpl = (Class<Dao>) Class.forName(nomDaoImpl);
				interfaces = (Class<?>[]) classeDaoImpl.getInterfaces();
				for (Class<?> i : interfaces) {
					if ( i.getName().equals(interfaceDao.getName())) {
						dao = (Dao) classeDaoImpl.newInstance();
						return dao;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return this.type;
	}

	public int getNbDaos() {
		return this.daos.size();
	}

}