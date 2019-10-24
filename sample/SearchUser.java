package sample;

public class SearchUser {
    int student_id;
    String firstName,lastName,faculty;
    int yearOfStudy;

    //constructor
    public SearchUser(int student_id,String firstName,String faculty,int  yearOfStudy,String lastName){
        this.faculty=faculty;
        this.firstName=firstName;
        this.lastName=lastName;
        this.student_id=student_id;
        this.yearOfStudy=yearOfStudy;

    }
    //getters and setters


    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }
}
