package com.itedu.framework.codelist;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

public class JdbcCodeList extends AbstractReloadableCodeList {

	private JdbcTemplate jdbcTemplate;

	private String querySql;

	private String valueColumn;

	private String labelColumn;

	@Override
	protected Map<String, String> retrieveMap() {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(querySql);
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
		for (Map<String, Object> row : rows) {
			Object key = row.get(valueColumn);
			Object value = row.get(labelColumn);
			if (key != null && value != null) {
				result.put(key.toString(), value.toString());
			}
		}
		return result;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterPropertiesSet() {
		Assert.hasLength(querySql, "querySql is empty");
		Assert.hasLength(valueColumn, "valueColumn is empty");
		Assert.hasLength(labelColumn, "labelColumn is empty");
		Assert.notNull(jdbcTemplate, "jdbcTemplate (or dataSource) is empty");
		super.afterPropertiesSet();
	}

	public void setLabelColumn(String labelColumn) {
		this.labelColumn = labelColumn;
	}

	public void setValueColumn(String valueColumn) {
		this.valueColumn = valueColumn;
	}

	public void setQuerySql(String querySql) {
		this.querySql = querySql;
	}

}
