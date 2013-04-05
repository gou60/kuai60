package com.gou60.bot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kuaileren.util.ConnetionUtil;

public class ClassService {
	Connection conn = null;
	Statement stmt = null;
	
	public List<ClassDO> getClassList(){
		List<ClassDO> classList = new ArrayList<ClassDO>();
		
		try {
			conn = ConnetionUtil.getConnetion();
			stmt = conn.createStatement();
			
			String sqlstr = "select * from t_classes";
			ResultSet rs = stmt.executeQuery(sqlstr);
			
			while(rs.next()){
				ClassDO classDO = new ClassDO();
				classDO.setFclass_leaf_id(rs.getLong("Fclass_leaf_id"));
				classDO.setFclass_leaf_name(rs.getString("Fclass_leaf_name"));
				classDO.setFclass_refer_id(rs.getLong("Fclass_refer_id"));
				classDO.setFclass_refer_name(rs.getString("Fclass_refer_name"));
				classDO.setFclass_level_id(rs.getLong("Fclass_level_id"));
				classDO.setFclass_level_name(rs.getString("Fclass_level_name"));
				
				classList.add(classDO);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnetionUtil.closeStatement(stmt);
			ConnetionUtil.close(conn);
		}
	
		return classList;
	}

	public void insert(ClassDO classDO, List<String> list) {
		try {
		conn = ConnetionUtil.getConnetion();
		conn.setAutoCommit(false);
		
		String sqlstr = "insert into t_item_source_url(Furl,Fclass_leaf_id,Fclass_leaf_name,Fclass_level_id) values(?,?,?,?)";
 
		PreparedStatement pstmt = conn.prepareStatement(sqlstr);   
		
		for(String url:list){
			pstmt.setString(1,url);   
			pstmt.setLong(2,classDO.getFclass_leaf_id());   
			pstmt.setString(3,classDO.getFclass_leaf_name());   
			pstmt.setLong(4,classDO.getFclass_level_id());   
			pstmt.addBatch();  
		}
		pstmt.executeBatch();
		conn.commit();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ConnetionUtil.close(conn);
		}
	}
}
