package com.parisofnajd.causewaystateendpoints;

import java.io.*;
import javax.servlet.http.*;

import com.google.maps.DirectionsApi; 
import com.google.maps.GeoApiContext;
import com.google.maps.GaeRequestHandler;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TrafficModel;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@SuppressWarnings("serial")
public class Worker extends HttpServlet {
	private GeoApiContext context = new GeoApiContext(new GaeRequestHandler());
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		context.setApiKey("AIzaSyAo0AI9Ym9NUweWLrq9uluGMpHmsyvLUrU");
		
		try {
			KSA2BHR();
			BHR2KSA();
			
		} catch (Exception e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void KSA2BHR() throws Exception {
		DateTime date = new DateTime(DateTimeZone.forID("Asia/Riyadh"));
		LatLng origin = new LatLng(26.218625, 50.206226);
		LatLng dest = new LatLng(26.172190,50.458094);
		DirectionsResult result = DirectionsApi.newRequest(context).trafficModel(TrafficModel.PESSIMISTIC).departureTime(date)
	        .origin(origin)
	        .destination(dest).await();
		 TravelTimeEntity.persist("KSA", "BHR", date, result.routes[0].legs[0].durationInTraffic.humanReadable,result.routes[0].legs[0].durationInTraffic.inSeconds);			    
	}
	
	private void BHR2KSA() throws Exception {
		DateTime date = new DateTime(DateTimeZone.forID("Asia/Riyadh"));
		LatLng origin = new LatLng(26.172396,50.459185);
		LatLng dest = new LatLng(26.219009,50.208105);
		DirectionsResult result = DirectionsApi.newRequest(context).trafficModel(TrafficModel.PESSIMISTIC).departureTime(date)
	        .origin(origin)
	        .destination(dest).await();
	    TravelTimeEntity.persist("BHR", "KSA", date, result.routes[0].legs[0].durationInTraffic.humanReadable,result.routes[0].legs[0].durationInTraffic.inSeconds);
	}
	
}