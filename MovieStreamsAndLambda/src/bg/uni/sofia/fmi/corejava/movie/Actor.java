package bg.uni.sofia.fmi.corejava.movie;

public class Actor {
	String firstName;
	String lastName;

	public Actor(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "Actor [firstName= " + firstName + ", lastname= " + lastName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.firstName == null) ? 0 : this.firstName.hashCode());
		result = prime * result + ((this.lastName == null) ? 0 : this.lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (object == null) {
			return false;
		}

		if (getClass() != object.getClass()) {
			return false;
		}

		Actor actor = (Actor) object;
		if (firstName == null) {
			if (actor.firstName != null) {
				return false;
			}
		} else if (!actor.firstName.equals(this.firstName)) {
			return false;
		}

		if (lastName == null) {
			if (actor.lastName != null) {
				return false;
			}
		} else if (!actor.lastName.equals(this.lastName)) {
			return false;
		}

		return true;
	}
}
