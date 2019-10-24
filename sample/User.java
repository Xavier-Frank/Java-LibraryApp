package sample;

public class User {
	
	String firstName;
	String lastName;
	int yearOfStudy;
	String faculty;
	int studentId;
	
	//constructor
	public User(int studentId, String firstName, String lastName, int yearOfStudy, String faculty) {
		this.studentId=studentId;
		this.firstName=firstName;
		this.lastName=lastName;
		this.yearOfStudy=yearOfStudy;
		this.faculty=faculty;
		
	}
	//getters and setters


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
}
