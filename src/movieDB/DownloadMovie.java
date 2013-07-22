package movieDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.Config;

/**
 * Servlet implementation class DdownloadMovie
 */
@WebServlet("/DdownloadMovie")
public class DownloadMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadMovie() {
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
		
		String filePath = Config.movieRepoLocal+"AliG.avi";
		String fileName = "AliG.avi";
		
		PrintWriter out = response.getWriter();
		
		FileInputStream fileToDownload = new FileInputStream(filePath);
		response.setContentType("video/avi");
		response.setHeader("Content-Disposition","attachment; filename="+fileName);
		response.setContentLength(fileToDownload.available());
		
		int c;
		while((c=fileToDownload.read()) != -1)
			out.write(c);
		
		out.flush();
		out.close();
		fileToDownload.close();
	
	}
		
	

}
