package Archive;

public class HotelRoom {
	private int roomId;
	private int hotelId;
	private int bedCount;
	private int priceOfNight;
	private boolean tvAccess;
	private boolean handicapAccess;
	private boolean minibarAccess;
	
	HotelRoom(int hId, int rId, int beds, boolean tv, boolean handicap,int price, boolean minibar)
	{
		hotelId = hId;
		roomId = rId;
		bedCount = beds;
		tvAccess = tv;
		handicapAccess = handicap;
		minibarAccess = minibar;
		priceOfNight = price;
	}
	public int getNightPrice()
	{
		return priceOfNight;
	}	
	int getHotelId()
	{
		return hotelId;
	}
	int getRoomId()
	{
		return roomId;
	}
	public int getBedCount()
	{
		return bedCount;
	}
	public boolean getTVAccess()
	{
		return tvAccess;
	}
	public boolean getHandicapAccess()
	{
		return handicapAccess;
	}
	public boolean getMinibarAccess()
	{
		return minibarAccess;
	}
}

