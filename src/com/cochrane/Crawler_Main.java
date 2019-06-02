package com.cochrane;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

public class Crawler_Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Document doc = null;
		JSONArray PageData = new JSONArray();
		//String url = "https://www.cochranelibrary.com/en/home?p_p_id=58_INSTANCE_MODAL&amp;p_p_lifecycle=1&amp;p_p_state=normal&amp;p_p_mode=view&amp;_58_INSTANCE_MODAL_struts_action=%2Flogin%2Flogin";
		String url ="http://www.cochranelibrary.com/";
	//	String LoginUrl = "https://www.cochranelibrary.com/c/portal/login?p_l_id=20185&amp;redirect=%2Fhome%3Fp_p_id%3D58_INSTANCE_MODAL%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26saveLastPath%3Dfalse";
		String LoginUrl = "https://www.cochranelibrary.com/c/portal/login";
		try {
			
			 Connection.Response loginForm = Jsoup.connect(LoginUrl)
		                .method(Connection.Method.GET)
		                .execute();
			 
			 Document mainPage = Jsoup.connect(LoginUrl)
					 .cookies(loginForm.cookies())
		                .post();
		                //.data("_58_login", "sgd23@njit.edu")
		                //.data("_58_password", "Sainath@4321")
		                

		        Document evaluationPage = Jsoup.connect(url)
		                .get();
		       // System.out.println(evaluationPage.toString());	
		        
		        Elements topics = evaluationPage.getElementsByClass("browse-by-list-item");
		        System.out.println(topics);
		//	doc = Jsoup.connect(url).get();
		    
		   } catch (IOException e) {
			   System.out.println(e);
		}
		
		
		
	}

}
