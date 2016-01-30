package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/drone")
public class Drone_control {

	// This method is called if TEXT_PLAIN is request
	@GET
	@Path("/welcome")
	@Produces(MediaType.TEXT_PLAIN)
	public String welcome() {
		return "Welcome to FAA Web Service";
	}

	// This method is called if XML is request
	@GET
	@Path("/welcome.xml")
	@Produces(MediaType.TEXT_XML)
	public String welcomeXML() {
		return "<?xml version=\"1.0\"?>" + "<title> Welcome to FAA Web Service" + "</title>";
	}

	// This method is called if HTML is request
	@GET
	@Path("/welcome.html")
	@Produces(MediaType.TEXT_HTML)
	public String welcomeHTML() {
		return "<html> " + "<title>" + "Welcome" + "</title>" + "<body><h1>" + "Welcome to FAA Web Service" + "</body></h1>"
				+ "</html> ";
	}
}
