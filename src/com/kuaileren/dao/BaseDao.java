package com.kuaileren.dao;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;


public class BaseDao extends SqlMapClientTemplate{

	protected DataSource dataSource;
	
    public  DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public BaseDao()
    {
    }
}
