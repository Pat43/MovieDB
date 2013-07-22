package movieDB;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import model.Movie;
import util.MyWriter;

/**
 * Servlet implementation class Film
 */
@WebServlet("/Film")
public class MovieInfos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieInfos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * Function called either the form is sent by Get or Post method.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Java initialisations :
		MovieDAO movieDAO = new MovieDAO();
		Movie movie = movieDAO.getById(Integer.parseInt(request.getParameter("ref")));
		
		// Web Content :
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		MyWriter.writePageHeader(out, "");
		
		out.println("<h1>"+movie.getName()+"</h1>");
		out.println("<a href=download>DOWNLOAD</a>");
		MyWriter.writePageFooter(out);
		
		
	}

}
