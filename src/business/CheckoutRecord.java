package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable{
	private List<CheckoutRecordEntry> entries;
	
	public List<CheckoutRecordEntry> getEntries() {
		return entries;
	}
	
	public void setEntries(List<CheckoutRecordEntry> entries) {
		this.entries = entries;
	}
	
	public void addRecord(CheckoutRecordEntry ch) {
		if(entries == null) entries = new ArrayList<>();
		entries.add(ch);
	}
}
