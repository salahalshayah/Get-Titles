package com.wissamfawaz;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Main2 {

	public static void main(String[] args) throws Exception {
		// 1. Create a URL
		URL url = new URL("http://feeds.bbci.co.uk/news/rss.xml");
		// 2. Create a stream that is attached to the URL
		InputStream is = url.openStream();
		
		BufferedReader inFromURL = new BufferedReader(
								new InputStreamReader(is));
		String line;
		StringBuilder output = new StringBuilder();

		while((line = inFromURL.readLine()) != null) {
			output.append(line + "\n");
		}

        String titles = getTitles(output);
		System.out.println(titles);
		inFromURL.close();
		
		

	}

    public static String getTitles(StringBuilder input) {
        StringBuilder titles = new StringBuilder();
        String intext = input.toString();
        while(intext.contains("<title>")) {
            int titleStart = intext.indexOf("<title><![CDATA[");
            int titleEnd = intext.indexOf("]]></title>");
            titles.append(intext.substring(titleStart + 16, titleEnd) + "\n");
            intext = intext.substring(titleEnd + 11);
        }    
        return titles.toString();
    }
}
