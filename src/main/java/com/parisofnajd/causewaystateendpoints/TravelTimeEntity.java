package com.parisofnajd.causewaystateendpoints;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import org.joda.time.DateTime;


public class TravelTimeEntity {

	public static void persist(String origin, String dest, DateTime date, String duration_in_text,long duration_in_seconds) {
	
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
	
		Entity at = new Entity("TravelTime");
		at.setProperty("From", origin);
		at.setProperty("To", dest);
		at.setProperty("DateTime", date.toString());
		at.setProperty("Duration text",duration_in_text);
		at.setProperty("Duration seconds",duration_in_seconds);
		ds.put(at);

	}
	
	public static String fetchAccessToken(long userid) {
		
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Key k = KeyFactory.createKey("AccessTokens", userid);
		Entity accesstoken=null;
		try {
			accesstoken=ds.get(k);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
		return accesstoken.getProperty("Token").toString();
	}
}
 