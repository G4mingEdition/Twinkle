/**
	@author
		• Genco Luca
**/

package be.twinkle.daoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

import be.twinkle.dao.Dao;
public class DaoFactory {
	private static final String FICHIER_CONFIGURATION = "config.xml";
	private static final DaoFactory INSTANCE = new DaoFactory();

	private Persistance persistance;
	private BoneCP connectionPool = null;

	public static DaoFactory getInstance() {
		return INSTANCE;
	}

	private DaoFactory() {
		try {
			this.persistance = ParserConfig.lireConfiguration(FICHIER_CONFIGURATION);
			this.persistance.configurer();
			if (this.persistance.getType().equals(Persistance.DB)) {
				this.connectionPool = creationConnectionPool();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private BoneCP creationConnectionPool() {
		BoneCP connectionPool = null;
		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(persistance.getUrl());
			config.setUsername(persistance.getUser());
			config.setPassword(persistance.getPassword());
			config.setMinConnectionsPerPartition(5);
			config.setMaxConnectionsPerPartition(10);
			config.setPartitionCount(2);
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connectionPool;
	}

	public Connection getConnexion() {
		Connection connection = null;
		try {
			connection = this.connectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public Dao getDaoImpl(Class<? extends Dao> interfaceDao) {
		return this.persistance.getDaoImpl(interfaceDao);
	}
}