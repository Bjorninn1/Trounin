public class HotelManager {
    
    public HotelManager() {
    	
    }

    //input is a lot of parameters that should match the hotel
    //output should be a list of hotels that match the given criteria
    //Use: hotelManager.findHotels(budget,date,numOfPeople,location)
    //Pre: budget is the maximum price of hotels you want to find, date is the date of bookin,
    //     numOfPeople is the number of travelers, and location the city you want to find an hotel in
    //Post: returns an array of hotels that match the criteria, empty if none matched
    public Hotel[] findHotels(int budget, String date, int numOfPeople, String location) {
        Hotel[] hotels = new Hotel[5];
        for(int i = 0; i < hotels.length; i++) {
            hotels[i] = new Hotel(location, "Hilton", date, (int)(Math.random()*budget), numOfPeople);
        }
        return hotels;
    }
    
    //Use: hotelManager.getHotel(location, hotelName, numOfpeople, date, budget)
    //Pre: location is the city of the hotel your looking for, hotelName is it's name,
    //       numOfPeople the number of travelers, date the date of bookin, and budget the max price
    //Post: Should return an array of availability for requested hotel, empty if none found
    public Hotel[] getHotel(String location, String hotelName, int numOfpeople, String date, int budget) {
        Hotel[] hotels = new Hotel[5];
        for(int i = 0; i < hotels.length; i++) {
            hotels[i] = new Hotel(location, hotelName, date, (int)(Math.random()*budget), numOfpeople);
        }
        return hotels;
    }

    //Use: hotelManager.bookHotelRoom(hotel, numOfPeople)
    //Pre: hotel is the hotel you want to book a room at, and numOfPeople the number of travelers
    //Post: returns true if there was availability at the hotel, false otherwise
    public boolean bookHotelRoom(Hotel hotel, int numOfPeople) {
        if(hotel.getCapacity() >= numOfPeople){
            hotel.reduceCapacity(numOfPeople);
            return true;
        }else
            return false;
    }
}