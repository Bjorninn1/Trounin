package Archive;

public class DateRange {
	private int firstDate;
	private int lastDate;
	private int rangeSize;
	
	public DateRange(int d1, int m1, int y1, int d2, int m2, int y2) throws IllegalArgumentException
	{
		if(d1 <=0 ||31 < d1 ||d2 <=0 ||31<d2 ||m1 <=0 ||m2<=0 ||12 <m1 ||12 < m2 ||
				y1 <= 2000||y2<=2000 ||2100 <= y1 ||2100 <=y2)
			throw new IllegalArgumentException();
		
		if((28 < d1 && m1 == 2)||(28 <d2 && m2 ==2)||(30 <d1 &&(m1==4 ||m1==6 ||m1==9 ||m1==11)) ||
				(30 < d2 &&(m2 ==4||m2==6 ||m2==9 ||m2==11)))
			throw new IllegalArgumentException();
		
		int dayNum1 = getDateNumber(d1, m1, (y1-2000));
		int dayNum2 = getDateNumber(d2, m2, (y2-2000));
		if(dayNum2 < dayNum1)
			throw new IllegalArgumentException();
		
		this.rangeSize = dayNum2-dayNum1 + 1;
		this.firstDate = (y1-2000)*10000 + m1*100 + d1;
		this.lastDate = (y2-2000)*10000 + m2*100 + d2;
			
	}
	public DateRange()
	{
		this.firstDate = 100000;
		this.lastDate = 500000;
		this.rangeSize = 0;
	}
	int getFirstDate()
	{
		return this.firstDate;
	}
	int getLastDate()
	{
		return this.lastDate;
	}
	
	int size()
	{
		return this.rangeSize;
	}
	
	private int getDateNumber(int d, int m, int y)
	{
		int dayNum = 0;
		dayNum += d;
		dayNum += (y * 365);
		
		if(m==2)dayNum += 31;
		else if(m==3)dayNum += 59;
		else if(m==4)dayNum += 90;
		else if(m==5)dayNum += 120;
		else if(m==6)dayNum += 151;
		else if(m==7)dayNum += 181;
		else if(m==8)dayNum += 212;
		else if(m==9)dayNum += 243;
		else if(m==10)dayNum += 273;
		else if(m==11)dayNum += 304;
		else if(m==12)dayNum += 334;
		
		return dayNum;
	}
	
	//only used for building database
	void nextDate()
	{
		int first = this.firstDate;
		int year = first/10000;
		first = first%10000;
		int month = first/100;
		first = first%100;
		int day = first;
		
		day += 1;
		if((day >31)||(day >30 &&(month==4 ||month==6 ||month==9 ||month==11))||
				(day>28 && month==2)){
			day = 1;
			month += 1;
		}
		if(month > 12)
		{
			month = 1;
			year += 1;
		}
		
		this.firstDate = year*10000 + month*100 + day;
	}
}
