package com.scrape.service;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BaseScrapper {
	public String fetchPageHTML(String url) throws Exception {
		if(url.length() == 0){
			throw new Exception("No url specified for scrapping");
		}
		trustEveryone();
		return Jsoup.connect(url).execute().body();
	}
	public Document fetchPageDocument(String html) throws Exception {
		if(html.trim().length() == 0){
			throw new Exception("Blank page returned from the server");
		}
		return Jsoup.parse(html);
	}
	
	public static void trustEveryone() {
	    try {
	        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        });

	        SSLContext context = SSLContext.getInstance("TLS");
	        context.init(null, new X509TrustManager[]{new X509TrustManager() {
	            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException { }

	            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException { }

	            public X509Certificate[] getAcceptedIssuers() {
	                return new X509Certificate[0];
	            }
	        }}, new SecureRandom());
	        HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
	    } catch (Exception e) { // should never happen
	        e.printStackTrace();
	    }
	}
}
