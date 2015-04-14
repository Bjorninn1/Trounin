package Archive;


public class Comment {
	private int userId;
	private String comment;
	private int hotelId;

	Comment(int userId, int hotelId, String comment)
	{
		this.comment = comment;
		this.userId = userId;
		this.hotelId = hotelId;
	}

	public String getText()
	{
		return comment;
	}
	public int getUserId()
	{
		return userId;
	}
	public int getHotelId()
	{
		return hotelId;
	}
}
