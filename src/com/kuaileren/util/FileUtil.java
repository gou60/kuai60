package com.kuaileren.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static List<String> readIpList(String filepath) {
		String line = "";
		List<String> list = new ArrayList<String>();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filepath));
			while((line=br.readLine())!=null){
				list.add(line.trim());
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		return list;
	}

	static FileWriter fw = null;
	static BufferedWriter  bw = null;

	
	public static String url_file = "D:\\www\\java\\2011dayun.com\\wwwlog\\url_list.txt";
	
	public static void write(String path,String message){
		try {
		FileWriter fw = new FileWriter(path,true);
		BufferedWriter bw = new BufferedWriter(fw);
		 
		bw.write(message);
		bw.newLine();
		
		bw.flush();
		fw.close();
		
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static void main(String[] a){
		List<String> list = readIpList("e:/text1.txt");
		for(String line:list){
			System.out.println(line);
		}
	}
}
