package org.maxi.booter.web;

import org.springframework.test.web.servlet.MvcResult;

public class WebTestUtils {
	// TODO Need to learn Null and Exception handling best practices
	public static Long parseIdFromLocationHeader(MvcResult result) {
		if (result == null) {
			// Maybe throw custom exception will be better here
			return null;
		}
		String locationHeader = result.getResponse().getHeader("Location");
		if (locationHeader == null) {
			// Maybe throw custom exception will be better here
			return null;
		}
		
		return parseIdFromLink(locationHeader);
	}
	
	// TODO
	public static Long parseIdFromLink(String link) {
		Long id = null;
		// Maybe pattern verification and throwing custom exception will be better here
		try {
			id = Long.parseLong(link.substring(link.lastIndexOf("/") + 1));
		} catch (Exception ignore) {}

		return id;
	}

}
