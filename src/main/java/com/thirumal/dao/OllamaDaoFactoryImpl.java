/**
 * 
 */
package com.thirumal.dao;

import org.springframework.stereotype.Service;

import com.thirumal.dao.mysql.OllamaMySqlDao;
import com.thirumal.dao.postgres.OllamaPostgresDao;
import com.thirumal.model.OllamaChat;

/**
 * @author thirumal
 */
@Service
public class OllamaDaoFactoryImpl implements OllamaDaoFactory {

	private final OllamaMySqlDao mysqlDao;
	private final OllamaPostgresDao postgresDao;

	public OllamaDaoFactoryImpl(OllamaMySqlDao mysqlDao, OllamaPostgresDao postgresDao) {
		this.mysqlDao = mysqlDao;
		this.postgresDao = postgresDao;
	}

	@Override
	public OllamaDao<OllamaChat> getDao(DbType dbType) {
        return switch (dbType) {
	        case MYSQL -> mysqlDao;
	        case POSTGRESQL -> postgresDao;
        };
	}

}
