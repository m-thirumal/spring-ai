/**
 * 
 */
package com.thirumal.dao;

import com.thirumal.model.OllamaChat;

/**
 * 
 */
public interface OllamaDaoFactory {
	
	OllamaDao<OllamaChat> getDao(DbType dbType);
	
}
