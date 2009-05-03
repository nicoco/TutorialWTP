package com.eclipsetotale;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: LoginServlet
 *
 */
 public class LoginServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  String login = request.getParameter("login");
		  String pass = request.getParameter("pass");
		  String message="";
		  
		  DatabaseRequest aDatabaseRequest = new DatabaseRequest();
		  String query="SELECT pass FROM logins where name='" + login + "'";
		  String column="pass";
		  Vector aResVector = new Vector(1);
		  aDatabaseRequest.execute(query, column, aResVector);

		  if (aResVector.size()==0) {
			  message = "Invalid login";
		  } else {
			  if(pass.equals(aResVector.get(0))) {
				  message = "OK";
				  HttpSession session = request.getSession(true);
				  session.setAttribute("logged","1");
				  session.setAttribute("login",login);
			  } else {
				  message = "Bad password";
			  }
		  }
			
		  PrintWriter out = response.getWriter();
		  response.setContentType("text/html");
		  out.println(message);	
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}   	  	  
	
	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	static public void printLoginPage(PrintWriter oPrintWriter) {
		oPrintWriter.println(

			"<H2 ALIGN='CENTER'>Please login</H2>"

		+	"<FORM>"

		+	"<CENTER>"
		+	"Login: <INPUT TYPE='TEXT' NAME='login' VALUE='nico'><BR>"
		+	"Pass: <INPUT TYPE='TEXT' NAME='pass' VALUE='secret'><BR>"
		+	"<p id='comment'>enter your login and password</p>"
		+	"<input type='button' onclick='sendXhrLogin()' value='modifier le champ'>"
		+	"</CENTER>"

		+	"</FORM>"
	
				);
	}
}