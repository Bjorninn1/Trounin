package Archive;


public class HotelCriteria {
	public static final int NO_DEMAND = -99999999;
	private boolean breakfast;
	private boolean dinner;
	private boolean wifi;
	private boolean handicap;
	private int stars;
	public HotelCriteria(boolean breakfast, boolean dinner, boolean wifi, boolean handicap, int stars)
	{
		this.breakfast = breakfast;
		this.dinner = dinner;
		this.wifi = wifi;
		this.handicap = handicap;
		this.stars = stars;
	}
	public HotelCriteria()
	{
		this.breakfast = false;
		this.dinner = false;
		this.wifi = false;
		this.handicap = false;
		this.stars = HotelCriteria.NO_DEMAND;
	}
	
	boolean matchesCriteria(int breakfast, int dinner, int wifi, int handicap, int stars)
	{
		if(breakfast == 0 && this.breakfast == true) return false;
		if(dinner == 0 && this.dinner == true) return false;
		if(wifi == 0 && this.wifi == true) return false;
		if(handicap == 0 && this.handicap == true) return false;
		if(stars < this.stars) return false;
		return true;
	}
}
