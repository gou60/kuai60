package com.kuaileren.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.kuaileren.domain.UserDO;

public class UserDaoImpl extends BaseDao implements UserDao {

	  private static final Logger log = Logger.getLogger(UserDaoImpl.class);
	
	public List<UserDO> queryUserList(UserDO user) {

		@SuppressWarnings("unchecked")
		List<UserDO> userList = (List<UserDO>)queryForList("user.queryUserList",user);
		return userList;
	}

	@Override
	public UserDO queryUser(UserDO user) {
		user = (UserDO)queryForObject("user.queryUser",user);
		return user;
	}
	
	@Override
	public UserDO register(UserDO user) {
		int userId = (Integer)insert("user.insertUser", user);
		user.setUserId(userId);
		return user;
	}

	@Override
	public UserDO modify(UserDO user) {
		 update("user.updateUser", user);
		return user;
	}
	
	public void test_JDBC() throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement("select * from user");
			ps.execute();
			ResultSet rs = ps.getResultSet();

			while (rs.next()) {
				System.out.println(rs.getObject(1));
			}

		} catch (SQLException e) {
			log.error(e,e);
		}finally{
			ps.close();
		   connection.close();		
		}

	}

}
