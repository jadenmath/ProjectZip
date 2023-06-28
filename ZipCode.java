/**
* ZipCode.java
* A custom container class to store all the attributes associated with a zipcode.
* The attributes consist of the latitude, longitude, city and top Restaurant
**/
public class ZipCode {
   // Instance/Class Variables

   // Stores the value of latitude for the ZIP Code
   double latitude;
   // Stores the value of longitude for the ZIP Code
   double longitude;
   // Stores the ZIP code
   int zip;
   // Stores the city for the ZIP code
   String city;
   // Stores the top restaurant for the ZIP Code
   String topRestaurant;

   // Constructor created to store ZIP Code, latitude, longitude, city, and restaurant
   public ZipCode(int zipcode, double latitude, double longitude, String city, String restaurant) {
       this.latitude = latitude;
       this.longitude = longitude;
       this.zip = zipcode;
       this.city = city;
       this.topRestaurant = restaurant;
   }

   // Method which returns value of latitude when called
   public double getLatitude() { return latitude; }

   // Method which sets the latitude
   public void setLatitude(double latitude) {
       this.latitude = latitude;
   }

   // Method which returns value of longitude when called
   public double getLongitude() {
       return longitude;
   }

   // Method which sets the longitude
   public void setLongitude(double longitude) {
       this.longitude = longitude;
   }

   // Method which returns value of ZIP when called
   public int getZip() { return zip; }

   // Method which sets the ZIP code
   public void setZip(int zip) {
       this.zip = zip;
   }

   // Method which returns value of city when called
   public String getCity() {
       return city;
   }

   // Method which sets the city
   public void setCity(String city) {
       this.city = city;
   }

   // Method which returns value of city when called
   public String getTopRestaurant() {
       return topRestaurant;
   }

   // Method which sets the recommended restaurant
   public void setTopRestaurant(String restaurant) {
       this.topRestaurant = restaurant;
   }
}