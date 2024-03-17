package misservlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;



@WebServlet("/LoginUsr")
public class LoginUsr extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, String> usersAndPasswords;

    public void init() {
        usersAndPasswords = new HashMap<>();
        usersAndPasswords.put("Leandro", "BackInBlack1980");
        usersAndPasswords.put("Camila", "LetThereBeRock1977");
        usersAndPasswords.put("Diego", "Powerage1978");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user").trim();
		String password = request.getParameter("password").trim();
        String responseTitle;
        String responseMessage;
        String storedPassword = usersAndPasswords.get(user);
        if (storedPassword != null && storedPassword.equals(password)) {
        	responseTitle = "¡Bienvenido " + user + "!";
        	responseMessage = "Usted es un usuario válido.";
        } else {
        	responseTitle = "¡Upss!";
        	responseMessage = "El usuario o contraseña ingesados son inválidos. Por favor, intente nuevamente <a href='http://localhost:8080/Practica1Web40/login.html'>aquí</a>";
        }
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
		out.println("<HTML>"); 
		out.println("<HEAD>");
		out.println("<TITLE>" + responseTitle + "</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<H1>" + responseTitle + "</H1>");
		out.println("<P>" + responseMessage + "</P>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}
}
