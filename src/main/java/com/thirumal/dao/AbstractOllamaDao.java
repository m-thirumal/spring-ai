/**
 * 
 */
package com.thirumal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.jdbc.core.simple.JdbcClient;

/**
 * @author Thirumal
 */
public abstract class AbstractOllamaDao<T> implements OllamaDao<T> {


    protected final JdbcClient jdbcClient;
    protected final OllamaChatModel ollamaChatModel;

    protected AbstractOllamaDao(JdbcClient jdbcClient, OllamaChatModel ollamaChatModel) {
        this.jdbcClient = jdbcClient;
        this.ollamaChatModel = ollamaChatModel;
    }
    
    protected String cleanSql(String raw) {
        return raw.replace("```sql", "")
                  .replace("```", "")
                  .trim();
    }
    
    protected String executeQueryAndBuildHtml(String sql) {
        List<Map<String, Object>> rows = jdbcClient.sql(sql)
                .query()
                .listOfRows();

        if (rows.isEmpty()) {
            return "<p>No results found.</p>";
        }

        StringBuilder html = new StringBuilder("<table border='1'><tr>");
        for (String column : rows.get(0).keySet()) {
            html.append("<th style='background-color: #007BFF; color: white;'>").append(column).append("</th>");
        }
        html.append("</tr>");
        for (Map<String, Object> row : rows) {
            html.append("<tr style='background-color: yellow; color: black;'>");
            for (Object value : row.values()) {
                html.append("<td>").append(value != null ? value.toString() : "").append("</td>");
            }
            html.append("</tr>");
        }
        html.append("</table>");
        return html.toString();
    }

    
}
