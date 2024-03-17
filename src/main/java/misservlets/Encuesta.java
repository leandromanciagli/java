package misservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Encuesta")
public class Encuesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Integer> encuestaMascotas;
    private Integer totalVotos = 0;
    private String mascotaMasVotada;
    private Integer maxVotos = -999;
    private Integer porcentajeMaxVotos = 0;
    private String message;
    private Boolean empate = true; 
    
    public void init() {
    	encuestaMascotas = new HashMap<>();
    	encuestaMascotas.put("Perro", 0);
    	encuestaMascotas.put("Gato", 0);
    	encuestaMascotas.put("Hamster", 0);
    	encuestaMascotas.put("Tortuga", 0);
    	encuestaMascotas.put("Pez", 0);
    	encuestaMascotas.put("Mono", 0);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] resultadosEncuesta = request.getParameterValues("mascotas");
		if (resultadosEncuesta != null) {			
			for (int i = 0; i < resultadosEncuesta.length; i++) {
				encuestaMascotas.put(resultadosEncuesta[i], encuestaMascotas.get(resultadosEncuesta[i])+1);
				totalVotos++;
			}
		}
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("<html>");
        out.println("<style>table, th, td { border:1px solid black; }</style>");
        out.println("<head><title>Resultados encuesta</title></head>");
        out.println("<body>");
        out.println("<h1>Resultados encuesta</h1>");
        out.println("<table style='width:30%'>");
        out.println("<tr><th>Mascota</th><th>Cantidad de votos</th></tr>");
        for (String i : encuestaMascotas.keySet()) {
        	  out.println("<tr><td>" + i + "</td><td><center>"+ encuestaMascotas.get(i) +"</center></td></tr>");
        	  if (encuestaMascotas.get(i) > maxVotos) {
        		  mascotaMasVotada = i;
        		  maxVotos = encuestaMascotas.get(i);
        		  empate = false;
        	  } else if (encuestaMascotas.get(i) == maxVotos) {
        		  empate = true;
			}
        }
        if (empate) {			
        	message = "Aún no hay una mascota más votada";
        } else {
        	porcentajeMaxVotos = maxVotos * 100 / totalVotos;
        	message = "La mascota más votada es: " + mascotaMasVotada + " con el " + porcentajeMaxVotos + "% de los votos.";
		}
        out.println("</table>");
        out.println("<br><br><span>"+ message +"</span><br><br>");
        out.println("<a href='http://localhost:8080/Practica1Web40/mascotas.html'>Volver a la encuesta</a>");
        out.println("</body>");
        out.println("</html>");
        out.close();
	}
}
