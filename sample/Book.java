package sample;

public class Book {
	String bookId,bookTitle,bookCategory,bookLevel,description;
	
	//constructor
	public Book(String bookId,String bookTitle,String bookCategory,String bookLevel,String description) {
		this.bookId=bookId;
		this.bookTitle=bookTitle;
		this.bookCategory=bookCategory;
		this.bookLevel=bookLevel;
		this.description=description;
	}

	//getters and setters
	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getBookLevel() {
		return bookLevel;
	}

	public void setBookLevel(String bookLevel) {
		this.bookLevel = bookLevel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
		
	
	

}
