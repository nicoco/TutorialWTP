package com.eclipsetotale;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: AcountServlet
 *
 */
 public class AccountServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);	
		String logged = (String)session.getAttribute("logged");

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		// disable cache //////
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Max-Age", 0);
		// ////////////////////
		if (logged == null) {
			LoginServlet.printLoginPage(out);
			
		} else {
			  String content="";
			  String login=(String)session.getAttribute("login");
			  
			  DatabaseRequest aDatabaseRequest = new DatabaseRequest();
			  String query="SELECT detail FROM accounts where name='" + login + "'";
			  String column="detail";
			  Vector aResVector = new Vector(1);
			  aDatabaseRequest.execute(query, column, aResVector);

			  if (aResVector.size()>0) {
				  content = (String)aResVector.get(0);
			  }
			
			  printMainPage(out, content);
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	static public void printMainPage(PrintWriter oPrintWriter, String iContent) {
		oPrintWriter.println(
				
			"<CENTER>"
		+	"<p>your account:</p>"
		+	"<p>"+iContent+"</p>"
		+	"</CENTER>"
		
				);
	}
}