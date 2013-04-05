package com.gou60.bot;

import java.util.Date;

public class ItemDO {

	 
	  private long Fitem_id;
	  private String Fitem_title;
	  private long Fclass_leaf_id;
	  private String Fitem_photo_addr;
	  private float Fitem_price;
	  private String Fitem_desc;
	  private long Fprice_id;
	  private Date Fitem_start_time;
	  private long Fitem_sell_num;
	  private long Fitem_good_rat;
	  private long Fitem_source_id;
	  private String Fitem_source_url;
	  
	public long getFitem_id() {
		return Fitem_id;
	}
	public void setFitem_id(long fitem_id) {
		Fitem_id = fitem_id;
	}
	public String getFitem_title() {
		return Fitem_title;
	}
	public void setFitem_title(String fitem_title) {
		Fitem_title = fitem_title;
	}
	public long getFclass_leaf_id() {
		return Fclass_leaf_id;
	}
	public void setFclass_leaf_id(long fclass_leaf_id) {
		Fclass_leaf_id = fclass_leaf_id;
	}
	public String getFitem_photo_addr() {
		return Fitem_photo_addr;
	}
	public void setFitem_photo_addr(String fitem_photo_addr) {
		Fitem_photo_addr = fitem_photo_addr;
	}
	public float getFitem_price() {
		return Fitem_price;
	}
	public void setFitem_price(float fitem_price) {
		Fitem_price = fitem_price;
	}
	public String getFitem_desc() {
		return Fitem_desc;
	}
	public void setFitem_desc(String fitem_desc) {
		Fitem_desc = fitem_desc;
	}
	public long getFprice_id() {
		return Fprice_id;
	}
	public void setFprice_id(long fprice_id) {
		Fprice_id = fprice_id;
	}
	public Date getFitem_start_time() {
		return Fitem_start_time;
	}
	public void setFitem_start_time(Date fitem_start_time) {
		Fitem_start_time = fitem_start_time;
	}
	public long getFitem_sell_num() {
		return Fitem_sell_num;
	}
	public void setFitem_sell_num(long fitem_sell_num) {
		Fitem_sell_num = fitem_sell_num;
	}
	public long getFitem_good_rat() {
		return Fitem_good_rat;
	}
	public void setFitem_good_rat(long fitem_good_rat) {
		Fitem_good_rat = fitem_good_rat;
	}
	public long getFitem_source_id() {
		return Fitem_source_id;
	}
	public void setFitem_source_id(long fitem_source_id) {
		Fitem_source_id = fitem_source_id;
	}
	public String getFitem_source_url() {
		return Fitem_source_url;
	}
	public void setFitem_source_url(String fitem_source_url) {
		Fitem_source_url = fitem_source_url;
	}
	@Override
	public String toString() {
		return "ItemDO [Fitem_id=" + Fitem_id + ", Fitem_title=" + Fitem_title
				+ ", Fclass_leaf_id=" + Fclass_leaf_id + ", Fitem_photo_addr="
				+ Fitem_photo_addr + ", Fitem_price=" + Fitem_price
				+ ", Fitem_desc=" + Fitem_desc + ", Fprice_id=" + Fprice_id
				+ ", Fitem_start_time=" + Fitem_start_time
				+ ", Fitem_sell_num=" + Fitem_sell_num + ", Fitem_good_rat="
				+ Fitem_good_rat + ", Fitem_source_id=" + Fitem_source_id
				+ ", Fitem_source_url=" + Fitem_source_url + "]";
	}
	  
	  

}
