package com.cochrane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;

public class Crawler_Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.out.println("Execution Started");
		// Initiate the first session and get cookies
		Document homePage = URLHandler.intiateSession();
		
		// Get first element from the Topics List
		Element top = URLHandler.getTopic(homePage);

		// getting Document for 1st review page
		Document reviewPage = URLHandler.getReviewsPage(top.select("a[href]").attr("href").toString());
		
		//Using review page to get list of all the review pages
		ArrayList<String> allReviewsPages = URLHandler.getNextPagesUrl(reviewPage);
		
		// ArrayList to hold the Elements of all review pages
		ArrayList<Elements> allReviews =  new ArrayList<>();
		
		// Traversing through every page to get all the reviews
		for(String pageurl : allReviewsPages)
		{
			Document page = URLHandler.getReviewsPage(pageurl);
			Elements reviews = page.getElementsByClass("search-results-item-body");
			allReviews.add(reviews);
		}
		
		// Write the reviews to text file
		if(FileHandler.createFile(allReviews,top.text()))
			{
			System.out.println("Done");
			};
	
	}

}
