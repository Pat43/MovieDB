package util;

import java.util.Comparator;
import java.util.List;

import model.Movie;
public class Comparators {
	
	/**
	 * 
	 * @author Loic
	 *
	 */
	public static class AbcComparator implements Comparator<Movie>{

		/**
		 * Compares two movies names alphabetically;
		 */
		@Override
		public int compare(Movie m1, Movie m2) {
			return m2.getName().compareToIgnoreCase(m1.getName());
		}
	}
	
	/**
	 * 
	 * @author Loic
	 *
	 */
	public static class genreComparator implements Comparator<Movie>{
		
		List<String> genres;
		
		/**
		 * 
		 * @param genres
		 */
		public genreComparator(List<String> genres){
			this.genres = genres;
		}
		
		/**
		 * Gets the number of similar genres of the two movies according to the genres attribute, and 
		 * puts first the one with the highest number. 
		 */
		@Override
		public int compare(Movie m1, Movie m2) {
			
			if(genres.get(0).equals(""))
				return m2.getGenres().get(0).getLabel().compareTo(m1.getGenres().get(0).getLabel());
			
			Integer n1 = getNbSimilarGenres(m1, genres);
			Integer n2 = getNbSimilarGenres(m2, genres);
			
			if(n1.compareTo(n2)==0)
				return m2.getGenres().get(0).getLabel().compareTo(m1.getGenres().get(0).getLabel());
			
			return n1.compareTo(n2);
		}
		
		private int getNbSimilarGenres(Movie movie, List<String> genres){
			int i=0;
			for(String genre : genres)
				if(movie.getGenreIds().contains(Integer.valueOf(genre)))
					i++;
					
			return i;
		}
		
	}
	
	/**
	 * 
	 * @author Loic
	 * 
	 */
	public static class languageComparator implements Comparator<Movie>{
		
		/**
		 * Compares two movies languages by id;
		 */
		@Override
		public int compare(Movie m1, Movie m2) {
			return m2.getLanguage().getId().compareTo(m1.getLanguage().getId());
		}
		
	}

}
