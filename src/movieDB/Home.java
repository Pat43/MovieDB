package movieDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;









import dao.GenreDAO;
import dao.LanguageDAO;
import dao.MovieDAO;
import model.Genre;
import model.Language;
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
		
		String p_movieTitle = request.getParameter("movie_title");
		String p_movieGenre = request.getParameter("movie_genre");
		String[] p_movieLang = request.getParameterValues("movie_lang");
		
		
		MovieDAO movieDAO = new MovieDAO();
		GenreDAO genreDAO = new GenreDAO();
		LanguageDAO languageDAO = new LanguageDAO();
		
		List<Movie> moviesToDisplay = null;
		List<Genre> genres = genreDAO.getAll();
		List<Language> languages = languageDAO.getAll();
		
		if(request.getParameterMap().isEmpty())
			moviesToDisplay = movieDAO.getAll();
		else
			moviesToDisplay = movieDAO.findByCriteria(p_movieTitle, p_movieGenre, p_movieLang);
		
		
		// Web content :
		response.setContentType("text/html"); 
		PrintWriter out = response.getWriter();
		String title = Messages.getString("Home.MovieDB");
		
		
		MyWriter.writePageHeader(out, title);
		
		// FORM :
		
		String form = "<form action='#' method='get'>";
		
		// Search by title :
		form += Messages.getString("Home.titre")+"<input type='text' name='movie_title' ";
		if(request.getParameter("movie_title") != null)
			form += "value='"+p_movieTitle+"' "; 
		form += ">";
		
		// Search by type :
		form += "Genre : "+"<select name='movie_genre'>";
		form += "<option value=''></option'>";
		for(Genre genre : genres){
			form += "<option ";
			if(p_movieGenre != null && ! p_movieGenre.equals("") &&  Integer.parseInt(p_movieGenre) == genre.getId())
				form += "selected='selected' ";
			form += "value='"+genre.getId()+"'>"+genre.getLabel()+"</option'>";
		}
		form += "</select> ";
		
		// Search by language :
		form += "Language : "+"<select name='movie_lang' multiple='multiple'>";
		for(Language language : languages){
			form += "<option ";
			if(p_movieLang != null && ! p_movieLang.equals("") &&  Arrays.asList(p_movieLang).contains(String.valueOf(language.getId())))
				form += "selected='selected' ";
			form += "value='"+language.getId()+"'>"+language.getLabel()+"</option'>";
		}
		form += "</select> ";
		
		// submit			
		form += "<input type='submit' value='"+Messages.getString("Home.rechercher")+"'>" +
					"</form>";
		
		out.println("Rechercher un film : <br>");
		out.println(form);
		
		// MOVIES :
		
		java.util.Collections.reverse(moviesToDisplay);		
		for(Movie movie : moviesToDisplay){
			out.println("<a href=movieinfos?ref="+movie.getId()+">"+movie.getName()+"</a> // ");
			Iterator<Genre> genreIt = movie.getGenres().iterator();
			while(genreIt.hasNext()){
				out.println(genreIt.next().getLabel());
				if(genreIt.hasNext())
					out.println(", ");
			}
			out.println(" // "+movie.getLanguage().getLabel());
			out.println("<br>");
		}
		
		MyWriter.writePageFooter(out);
		
		
		out.close();
		
	}

}
