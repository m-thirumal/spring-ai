/**
 * 
 */
package com.thirumal.service;

import org.springframework.stereotype.Service;

import com.thirumal.dao.OllamaDao;
import com.thirumal.dao.OllamaDaoFactory;
import com.thirumal.model.OllamaChat;

/**
 * 
 */
@Service
public class OllamaService {
	
    private final OllamaDaoFactory ollamaDaoFactory;

    public OllamaService(OllamaDaoFactory ollamaDaoFactory) {
        this.ollamaDaoFactory = ollamaDaoFactory;
    }

    public String askQuestion(OllamaChat ollamaChat) {
        OllamaDao<OllamaChat> ollamaDao = ollamaDaoFactory.getDao(ollamaChat.getDbType());
        return ollamaDao.processQuestion(ollamaChat);
    }

	
}
