package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable{
	private static final long serialVersionUID = 4588414476496935558L;
	private LocalDate checkoutDate;
	private Book book;
	private int copyNum;
	private LocalDate dueDate;
	
	public CheckoutRecordEntry(Book book, int copyNum,  LocalDate checkoutDate, LocalDate dueDate) {
		this.book = book;
		this.copyNum = copyNum;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
	}
	
	
	@Override
	public String toString() {
		return "ISBN: " + book.getIsbn() + " | title: " + book.getTitle() + " "+ " | copy id: " + copyNum +" |  checkedout date: " + checkoutDate + " | due date: " + dueDate + "\n";
	}
	
	
}
