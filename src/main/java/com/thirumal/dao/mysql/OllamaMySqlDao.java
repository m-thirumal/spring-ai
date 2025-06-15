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
				you have access to a database with tables:
				usr_dtls(usr_id, uin, entty_id, emp_no, adhar_no, pan_no, nationality, dob, status, usr_typ_id, ftp_id, dsgntn, remarks, reg_id, is_dsc_reg, msme_ref, reg_source, vrfd_usr, uid, crdt_tm, updt_tm, usr_status, is_tnc_accepted)
				
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
