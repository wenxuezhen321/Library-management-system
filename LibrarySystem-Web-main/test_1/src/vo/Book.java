package vo;

public class Book {
	//����ID
	private String bookID;
	//����������
	private String bookName;
	//�鼮����
	private String bookCategory;
	//�鼮���
	private String bookIntroduction;
	
	
	//�Զ�����get��set����
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}
	public String getBookIntroduction() {
		return bookIntroduction;
	}
	public void setBookIntroduction(String bookIntroduction) {
		this.bookIntroduction = bookIntroduction;
	}
	
	public Book(){
		super();
	}
	public Book(String bookID, String bookName, String bookCategory,
			String bookIntroduction) {
		super();
		this.bookID = bookID;
		this.bookName = bookName;
		this.bookCategory = bookCategory;
		this.bookIntroduction = bookIntroduction;
	}
	
	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", bookName=" + bookName
				+ ", bookCategory=" + bookCategory + ", bookIntroduction="
				+ bookIntroduction + "]";
	}
	

}
