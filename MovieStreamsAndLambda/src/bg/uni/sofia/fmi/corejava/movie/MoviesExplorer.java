package bg.uni.sofia.fmi.corejava.movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.omg.Messaging.SyncScopeHelper;

public class MoviesExplorer {
	public static void main(String[] args) throws IOException {
		List<Movie> movies=new ArrayList<>();
		
		//1.Read all films
		try(Stream<String> lines=Files.lines(Paths.get("resources", "movies-mpaa.txt"))){
			lines.forEach(line->addMovie(movies, line));
		}
		
		//2.All films from 2003
		Integer numberOfFilmsin2003=(int) movies.stream().filter(p-> p.getYears()==2003).count();
		
		//3.Find the first Lord of the Rings film
		Optional<Movie> firstLOTR= movies.stream().filter(p->p.getTitle().contains("Lord of the Rings")).findFirst();
		firstLOTR.ifPresent(movie->System.out.println(movie.getTitle()+" "+movie.getYears()));
		firstLOTR.orElseThrow(IllegalArgumentException::new);
		
		//4.Sort films by year
		//movies.stream().sorted((p1,p2)->p1.getYears()-p2.getYears()).forEach(p->System.out.println(p));
		
		//5.Find the first and the last year in the statistic
		int minYear=movies.stream().mapToInt(Movie::getYears).min().getAsInt();
		int maxYear=movies.stream().mapToInt(Movie::getYears).max().getAsInt();
		
		//6.Map movies by the key
		Map<Integer,List<Movie>> mapMovies=	movies.stream().collect(Collectors.groupingBy(Movie::getYears));
		
		//7.Read all actors
		Set<Actor> actors=movies.stream().flatMap(p->p.getActors().stream()).collect(Collectors.toSet());
		Iterator<Actor> it=actors.iterator();
		while(it.hasNext()){
			Actor actor=it.next();
			System.out.println(actor);
			it.remove();
			
		}
		
		//8.Kevin Spacey films
		Actor actor=new Actor("Kevin","Spacey");
		movies.stream().filter(p->p.getActors().stream().anyMatch(c->c.equals(actor))).collect(Collectors.toList()).forEach(q->System.out.println(q));
	}

	private static void addMovie(List<Movie> movies, String movieInfo) {
		String elements[] = movieInfo.split("/");
		String title = parseMovieTitle(elements);
		String releaseYear = parseMovieReleaseYear(elements);

		Movie movie = new Movie(title, Integer.valueOf(releaseYear));

		for (int i = 1; i < elements.length; i++) {
			String[] name = elements[i].split(", ");
			String lastName = name[0].trim();
			String firstName = "";
			if (name.length > 1) {
				firstName = name[1].trim();
			}

			Actor actor = new Actor(firstName, lastName);
			movie.addActor(actor);
		}

		movies.add(movie);
	}

	private static String parseMovieTitle(String[] elements) {
		return elements[0].substring(0, elements[0].toString().lastIndexOf("(")).trim();
	}

	private static String parseMovieReleaseYear(String[] elements) {
		String releaseYear = elements[0].substring(elements[0].toString().lastIndexOf("(") + 1,
				elements[0].toString().lastIndexOf(")"));
		if (releaseYear.contains(",")) {
			releaseYear = releaseYear.substring(0, releaseYear.indexOf(","));
		}
		return releaseYear;
	}

}