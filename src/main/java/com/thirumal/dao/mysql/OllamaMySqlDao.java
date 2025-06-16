/**
 * 
 */
package com.thirumal.dao.mysql;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import com.thirumal.dao.AbstractOllamaDao;
import com.thirumal.model.OllamaChat;

/**
 * @author Thirumal
 */
@Repository
public class OllamaMySqlDao extends AbstractOllamaDao<OllamaChat>{


	public OllamaMySqlDao(@Qualifier("mysqlJdbcClient")JdbcClient jdbcClient, OllamaChatModel ollamaChatModel) {
		super(jdbcClient, ollamaChatModel);
	}

	@Override
	public String processQuestion(OllamaChat ollamaChat) {
		String prompt = """
				you have access to a database of all tables:
				User question: "%s"
				
				Return only a valid SQL SELECT query based on the question.
                DO NOT explain anything, only raw SQL. No markdown, no comments.
			""".formatted(ollamaChat.getQuestion());
		ChatResponse response = ollamaChatModel.call(new Prompt(prompt));
        String sql = cleanSql(response.getResult().getOutput().getText());

        System.out.println("Generated SQL:"+sql);
        if (!sql.trim().toLowerCase().startsWith("select")) {
            throw new IllegalArgumentException("Only SELECT queries are allowed.");
        }

        return executeQueryAndBuildHtml(sql);
	}
	
  
}
