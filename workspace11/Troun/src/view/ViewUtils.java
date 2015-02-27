package view;

public class ViewUtils {
	static String[] itemsFrom = new String[]{"London", "Berlin", "Barcelona", "Paris", "Amsterdam"};
	static String[] itemsTo = new String[]{"Keflavik", "Akureyri"};
	static String[] itemsBudget = new String[]{"1000", "2000"};
	static String[] itemsHotel = new String[]{"Hotel Cabin", "Hotel 101", "Hotel Saga",};
	static String[] itemsArea = new String[]{"Reykjavik", "Akureyri"};
	
	
	public static String[] getDropDownFrom() {
		return itemsFrom;
	}
	
	public static String[] getDropDownTo() {
		return itemsTo;
	}
	
	public static String[] getDropDownBudget() {
		return itemsBudget;
	}
	
	public static String[] getDropDownHotel() {
		return itemsHotel;
	}
	
	public static String[] getDropDownArea() {
		return itemsArea;
	}
}
