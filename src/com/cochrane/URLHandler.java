package com.cochrane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Base;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class URLHandler {

	static int topicIndex = 0;
	static Connection.Response loginForm;
	
	// Initial session to maintain cookies
	public static Document intiateSession()
	{
		String LoginUrl = "https://www.cochranelibrary.com/c/portal/login";
		String mainPageurl ="http://www.cochranelibrary.com/";
		
		try {
			loginForm = Jsoup.connect(mainPageurl)
			        .method(Connection.Method.GET)
			        .execute();
	
			
			Connection.Response mainPage = Jsoup.connect(LoginUrl)
					.cookies(loginForm.cookies())
		            .execute();
         
	 Map<String, String> cookies = mainPage.cookies();
	 	Thread.sleep(1000);
	 	
	 	Document evaluationPage = Jsoup.connect(mainPageurl)
	            .cookies(cookies)
	            .execute().parse();
        
	 	return evaluationPage;
	 	
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
		
		
	}
	
	// to get the topics from the home page
	public static Element getTopic(Document homePage)
	{
		Elements topics = homePage.getElementsByClass("browse-by-list-item");
		Element topic = topics.get(topicIndex);
		topicIndex++;
		return topic;
	}
	
	
	// to get reviews from the page
	public static Document getReviewsPage(String topicUrl)
	{
		try {
			Thread.sleep(1000);
			
			// using the cookies to each time visiting any page
			Document reviews = Jsoup.connect(topicUrl)
					.cookies(loginForm.cookies())
		            .execute().parse();
			
			return reviews;
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	/*public static Elements getReviews(String pageUrl)
	{
		
		String LoginUrl = "https://www.cochranelibrary.com/c/portal/login";
		String mainPageurl ="http://www.cochranelibrary.com/";
		Connection.Response loginForm;
		Document homePage;
		
		try {
			
			loginForm = Jsoup.connect(LoginUrl)
			        .method(Connection.Method.GET)
			        .execute();
		
			
			Thread.sleep(1000);
			System.out.println(pageUrl);
			
			Document mainPage = Jsoup.connect(LoginUrl)
					 .cookies(loginForm.cookies())
					 .data("_58_login", "sgd23@njit.edu")
		                .data("_58_password", "Sainath@4321")
		                .post();
			
			Document reviews= Jsoup.connect(pageUrl)
	                .get();
			
			Elements reviewsList = mainPage.getElementsByClass("search-results-item-body");
			
			return reviewsList;
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
		
	}
	
	
	public static boolean hasNextPage(Document reviewPage)
	{
		Element next = reviewPage.getElementsByClass("pagination-next-link").first();
		System.out.println("link :"+next);
		if(next!=null)
			return true;
		else
			return false;
	}
	*/
	public static ArrayList<String> getNextPagesUrl(Document reviewPage)
	{
		ArrayList<String> nextPages = new ArrayList<>();
		
		Elements allNextPages = reviewPage.getElementsByClass("pagination-page-list-item");
		
		for(Element e : allNextPages)
		{
		//	System.out.println(e.select("a[href]").attr("href").toString());
			nextPages.add(e.select("a[href]").attr("href").toString());
		}
		
		return nextPages;
	}
	
/*	public static Document getNextPage(Document reviewPage)
	{
		try {
			Thread.sleep(10000);
			Element next = reviewPage.getElementsByClass("pagination-next-link").first();
			
			
			Document nextreviews = Jsoup.connect(next.select("a[href]").attr("href").toString())
	                .get();
			
			return nextreviews;
			
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	
	}*/
	
	
}
