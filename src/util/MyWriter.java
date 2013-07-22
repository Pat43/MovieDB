package util;

import java.io.PrintWriter;

public class MyWriter {

	public static void writePageHeader(PrintWriter out, String title) {
		out.println("<HTML>");
		out.println("<HEAD><TITLE>"+title+"</TITLE></HEAD>");
		out.println("<BODY>");
	}

	public static void writePageFooter(PrintWriter out) {
		out.println("</BODY>");
		out.println("</HTML>");
	}
	
}
