package movieDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.MovieDAO;
import model.Movie;
import util.MyWriter;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
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
		
		
		// Java initialisations:
		MovieDAO movieDAO = new MovieDAO();
		
		List<Movie> moviesToDisplay = null;
		if(request.getParameterMap().isEmpty()
				|| request.getParameter("movie_title").equals(""))
			moviesToDisplay = movieDAO.getAll();
		else
			moviesToDisplay = movieDAO.findByName(request.getParameter("movie_title"));
		
		// Web content :
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		String title = Messages.getString("Home.MovieDB");
		
		
		MyWriter.writePageHeader(out, title);
		
		
		String form = "<form action='h	ome' method='get'>" +
					Messages.getString("Home.titre")+"<input type='text' name='movie_title'>" +
					"<input type='submit' value='"+Messages.getString("Home.rechercher")+"'>" +
					"</form>";
		
		out.println("Rechercher un film : <br>");
		out.println(form);
		
		java.util.Collections.reverse(moviesToDisplay);		
		for(Movie movie : moviesToDisplay){
			out.println("<a href=movieinfos?ref="+movie.getId()+">"+movie.getName()+"</a>"+" "+movie.getGenres().get(0).getLabel()+"<br>");
		}
		
		MyWriter.writePageFooter(out);
		
		
		out.close();
		
	}

}
