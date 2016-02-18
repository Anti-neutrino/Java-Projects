package bg.uni.sofia.fmi.corejava.movie;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	String title;
	Integer years;
	List<Actor> actors;

	public Movie(String title, Integer years) {
		this.title = title;
		this.years = years;
		this.actors = new ArrayList<>();
	}

	public String getTitle() {
		return title;
	}

	public Integer getYears() {
		return years;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void addActor(Actor actor) {
		this.actors.add(actor);
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", years=" + years + ", actors=" + actors + "]";
	}
}