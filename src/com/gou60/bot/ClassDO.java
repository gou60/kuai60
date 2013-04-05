package com.gou60.bot;

public class ClassDO {

	   private long Fclass_leaf_id;
	   private String Fclass_leaf_name;
	   private long Fclass_refer_id;
	   private String Fclass_refer_name;
	   private long Fclass_level_id;
	   private String Fclass_level_name;
	   
	   
	public long getFclass_leaf_id() {
		return Fclass_leaf_id;
	}
	public void setFclass_leaf_id(long fclass_leaf_id) {
		Fclass_leaf_id = fclass_leaf_id;
	}
	public String getFclass_leaf_name() {
		return Fclass_leaf_name;
	}
	public void setFclass_leaf_name(String fclass_leaf_name) {
		Fclass_leaf_name = fclass_leaf_name;
	}
	public long getFclass_refer_id() {
		return Fclass_refer_id;
	}
	public void setFclass_refer_id(long fclass_refer_id) {
		Fclass_refer_id = fclass_refer_id;
	}
	public String getFclass_refer_name() {
		return Fclass_refer_name;
	}
	public void setFclass_refer_name(String fclass_refer_name) {
		Fclass_refer_name = fclass_refer_name;
	}
	public long getFclass_level_id() {
		return Fclass_level_id;
	}
	public void setFclass_level_id(long fclass_level_id) {
		Fclass_level_id = fclass_level_id;
	}
	public String getFclass_level_name() {
		return Fclass_level_name;
	}
	public void setFclass_level_name(String fclass_level_name) {
		Fclass_level_name = fclass_level_name;
	}
	@Override
	public String toString() {
		return "ClassDO [Fclass_leaf_id=" + Fclass_leaf_id
				+ ", Fclass_leaf_name=" + Fclass_leaf_name
				+ ", Fclass_refer_id=" + Fclass_refer_id
				+ ", Fclass_refer_name=" + Fclass_refer_name
				+ ", Fclass_level_id=" + Fclass_level_id
				+ ", Fclass_level_name=" + Fclass_level_name + "]";
	}
	   
	   
}
