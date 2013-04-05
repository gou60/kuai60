package com.kuaileren.common;

import java.util.ArrayList;
import java.util.List;

import com.kuaileren.domain.UserDO;

public class UserResult extends BaseResult {

	private UserDO userDO =null;
	private List<UserDO> userList  = new ArrayList<UserDO>();
	
	public UserResult(boolean b) {
		super(b);
	}

	public UserDO getUserDO() {
		return userDO;
	}

	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
	}

	public List<UserDO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDO> userList) {
		this.userList = userList;
	}

	
}
