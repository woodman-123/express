package com.heima.express.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class StringUtils {
	
	
	public static String getCheckCode() {
		//Math.random() (0,1)
		//Math.random()*9 (0,9)
		//Math.random()*9+1  (1,10);
		//Math.random()*9+1)*100000  (100000,1000000);
		return (int)((Math.random()*9+1)*100000)+"";
	}
	
	
	
	
	//中文分词								
		public static List<String> queryWords(String query) throws IOException {							
	        Configuration cfg = DefaultConfig.getInstance();								
	        //System.out.println(cfg.getMainDictionary()); // 系统默认词库								
	        //System.out.println(cfg.getQuantifierDicionary());								
	        List<String> list = new ArrayList<String>();								
	        StringReader input = new StringReader(query.trim());								
	        IKSegmenter ikSeg = new IKSegmenter(input, true);   // true 用智能分词 ，false细粒度								
	        Lexeme lexeme =  ikSeg.next();								
	        while(lexeme!=null) {								
	        	list.add(lexeme.getLexemeText());							
	        	lexeme =  ikSeg.next();							
	        }								
	        return list;								
									
	    }								

	

	public static void main(String[] args) throws IOException {
		List<String> strs=queryWords("广西壮族自治区玉林市北流市凯旋广场桃源");
		
		System.out.println(strs);
	}
	
}
