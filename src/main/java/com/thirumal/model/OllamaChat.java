/**
 * 
 */
package com.thirumal.model;

import java.io.Serializable;

import com.thirumal.dao.DbType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Thirumal
 */
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@ToString@Builder
public class OllamaChat implements Serializable {

	private static final long serialVersionUID = -4932685812165778514L;
	
	private String question;
	@Builder.Default
	private DbType dbType = DbType.MYSQL;
	
}
