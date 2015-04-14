package Archive;
public class RoomCriteria {
	public static final int NO_DEMAND = -999999999;
	private int bedCount;
	private boolean tvAccess;
	private boolean minibar;
	private int priceOfNight;
	private boolean handicap;
	
	public RoomCriteria(int beds, int price, boolean tvAccess, boolean minibar, boolean handicap)
	{
		this.bedCount = beds;
		this.tvAccess = tvAccess;
		this.minibar = minibar;
		this.priceOfNight = price;
		this.handicap = handicap;
	}
	public RoomCriteria(int beds)
	{
		this.bedCount = beds;
		this.tvAccess = false;
		this.minibar = false;
		this.priceOfNight = RoomCriteria.NO_DEMAND;
		this.handicap = false;
	}
	public RoomCriteria()
	{
		this.bedCount = RoomCriteria.NO_DEMAND;
		this.tvAccess = false;
		this.minibar = false;
		this.priceOfNight = RoomCriteria.NO_DEMAND;
		this.handicap = false;
	}
	
	boolean matchesCriteria(int beds,int price, int tv, int mini, int handi)
	{
		if(this.bedCount != beds && this.bedCount != RoomCriteria.NO_DEMAND)return false;
		if(this.priceOfNight < price && this.priceOfNight != RoomCriteria.NO_DEMAND) return false;
		if(this.minibar == true && mini == 0)return false;
		if(this.handicap == true && handi == 0)return false;
		if(this.tvAccess == true && tv == 0)return false;
		return true;
	}
}
