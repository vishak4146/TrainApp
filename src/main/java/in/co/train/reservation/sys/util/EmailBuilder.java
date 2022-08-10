package in.co.train.reservation.sys.util;

import java.util.HashMap;

/**
 * Class that build Application Email messages
 *
 */

public class EmailBuilder {


	public static String getUserRegistrationMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("Registration is successfully On Online Train Reservation System");
		msg.append("<H1>Hi! Greetings from Fashion Store!</H1>");
		msg.append(
				"<P>Congratulations for registering on Fashion Store You can now access your Online Train Reservation System account online - anywhere, anytime.</P>");
		
		msg.append("<P><B>Login Id : " + map.get("login") + "<BR>" + " Password : " + map.get("password") + "</B></p>");

		msg.append(
				"<P> As a security measure, we recommended that you change your password after you first log in.</p>");
		
		msg.append(
				"<p>We assure you the best service at all times and look forward to a warm and long-standing association with you.</p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}

	

	public static String getForgetPasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your password is reccovered !! " + map.get("firstName") + " " + map.get("lastName") + "</H1>");
		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>" + " Password : "
				+ map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}


	public static String getChangePasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();

		msg.append("<HTML><BODY>");
		msg.append("<H1>Your Password has been changed Successfully !! " + map.get("firstName") + " "
				+ map.get("lastName") + "</H1>");

		msg.append("<P><B>To access account user Login Id : " + map.get("login") + "<BR>" + " Password : "
				+ map.get("password") + "</B></p>");
		msg.append("</BODY></HTML>");

		return msg.toString();
	}

	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", "Rohan123");
		map.put("password", "268651");
		System.out.println(EmailBuilder.getUserRegistrationMessage(map));
	}
}
