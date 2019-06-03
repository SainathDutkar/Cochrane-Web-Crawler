package com.cochrane;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FileHandler {

	public static boolean createFile(ArrayList<Elements> reviews,String topic)
	{
		String baseURL = "https://www.cochranelibrary.com";
		String path = "output/"+"cochrane_reviews.txt";
		
		try (FileWriter file = new FileWriter(path);
				BufferedWriter bw = new BufferedWriter(file)) {
			
			for(Elements review:reviews)
			{
				for(Element e:review)
				{
					bw.write(
							baseURL+e.getElementsByClass("result-title").select("a[href]").attr("href") + "|"
							+topic+"|"
							+e.getElementsByClass("result-title").text()+"|"	
							+e.getElementsByClass("search-result-authors").text()+"|"
							+e.getElementsByClass("search-result-date").text()
							);
					bw.newLine();
				}
			}
			
            file.flush();

        } catch (IOException e) {
            return false;
        }
		
		return true;
	}
	
}
