package misservlets;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/bienvenida")
public class Bienvenida extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String message = "¡Bienvenido " + request.getParameter("nombre") + "!";
		response.setContentType("text/html");
		PrintWriter out=response.getWriter(); 
		out.println("<HTML>"); 
		out.println("<HEAD>");
		out.println("<TITLE>" + message + "</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<H1>" + message + "</H1>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}
}

