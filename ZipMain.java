import java.lang.Math;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
* ZipMain.java
* Find the distance in miles between two zip codes. In addition, display the cities for each of these zip codes
* and identify the top restaurant in the destination zip code.
* Acknowledgements: Haversine formula (implemented as an algorithm in the program)
**/

public class ZipMain {
   // Instance/Class Variables

   // Stores the value of user-inputted ZIP Code (From and To in an integer format) and num to get other class variables
   static int fromZipInputInt = 0, toZipInputInt = 0;
   // Stores the ZIP code's latitude and longitude for the "from" and "destination" ZIP code
   static double zipLatFrom, zipLongFrom, zipLongDestination, zipLatDestination;
   // Stores the city of the "from" and "destination" ZIP code
   static String cityFrom, cityDestination;
   // Stores the proper decimal format displayed in the program output
   private static final DecimalFormat df = new DecimalFormat("0.00");
   // Stores the restaurant Destination based on the destination ZIP code
   static String restaurantDestination;

   // Sets fromZip as user input value
   static Scanner fromZipScan = new Scanner(System.in);
   // Sets toZip as user input
   static Scanner toZipScan = new Scanner(System.in);
   // Declares a list to store a list of ZIP code objects (zipList)
   static List<ZipCode> zipList = new ArrayList<ZipCode>();

   /**
    * Entry to the program
    *
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       String inputCheck = "", fromZipInput = "", toZipInput = "";

       // Calls the method CreateZipList
       CreateZipList();

       // Checking whether the pass is from or to Zip Code check pass
       inputCheck = "from";
       // Prompts for user to input starting point ZIP code
       System.out.print("Enter starting ZIP Code from Orange County, CA\nFrom: ");
       fromZipInput = fromZipScan.nextLine();

       // Calls the Algorithm to prompt the user for inputs, validates and the calculates the distance between zipcodes.
       // This uses Sequence, Selection and Iteration
       GetDistanceBetweenZip(inputCheck, fromZipInput, toZipInput);

       // Prompts for user to input ending point ZIP code
       inputCheck = "to";
       System.out.print("\nEnter destination ZIP Code in Orange County, CA\nTo: ");
       toZipInput = toZipScan.nextLine();

       // Calls the Algorithm to prompt the user for inputs, validates and the calculates the distance between zipcodes.
       // This uses Sequence, Selection and Iteration
       GetDistanceBetweenZip(inputCheck, fromZipInput, toZipInput);
   }

   // This method gets the user inputs and also invokes the Algorithm using Haversine formula to obtain the distance
   // between zipcodes
   private static void GetDistanceBetweenZip(String inputCheck, String fromZip, String toZip) {
       boolean validZip = false;

       // Do While iterator is implemented to make sure entered ZIP Code is a valid Orange County, CA ZIP code
       if (inputCheck == "from") {
           do {
               if (isNumeric(fromZip)) {
                   fromZipInputInt = Integer.parseInt(fromZip);
               }
               if (FindZipUsingIterator(fromZipInputInt, inputCheck) == 0)
               {
                   // Prompts for user to input starting point ZIP code
                   System.out.print("Invalid Entry. Re-enter your starting Zip Code from Orange County, CA.\nFrom: ");
                   fromZip = fromZipScan.nextLine();
               }
               else
               {
                   validZip = true;
               }
           } while (validZip == false);
       }
       // Do While iterator is implemented to make sure entered ZIP Code is a valid Orange County, CA ZIP code
       if (inputCheck == "to") {
           do {
               if (isNumeric(toZip)) {
                   toZipInputInt = Integer.parseInt(toZip);
               }
               if (FindZipUsingIterator(toZipInputInt, inputCheck) == 0)
               {
                   // Prompts for user to input destination point ZIP code
                   System.out.print("\nInvalid Entry. Re-enter your destination Zip Code to Orange County, CA.\nTo: ");
                   toZip = toZipScan.nextLine();
               }
               else
               {
                   validZip = true;
               }
           } while (validZip == false);


           // Program legibly outputs user inputted ZIP codes, city of ZIP codes, and the distance between the codes in miles.
           System.out.println("The distance between " + cityFrom + " (" + fromZipInputInt + ") to " + cityDestination + " (" + toZipInputInt + ") " +
                   "is " + calcDistance(zipLatFrom, zipLatDestination, zipLongFrom, zipLongDestination) + " miles.");
           // Program also outputs recommended restaurant within the destination ZIP code
           System.out.println("A highly recommended restaurant nearby is " + restaurantDestination + ".");
       }
   }

   // Method isNumeric is set as a boolean to identify whether the string can be converted to an integer
   public static boolean isNumeric(String string) {
       int intValue;
       // Empty values would result in false
       if (string == null || string.equals("")) {
           return false;
       }

       try {
           // If conversion successful, results in true
           intValue = Integer.parseInt(string);
           return true;
           // If operation runs into runtime error, creates exception and results false
       } catch (NumberFormatException e) {
       }
       return false;
   }

   // Method FindZipUsingIterator gets the integer ZIP value and uses linear search to find an identical ZIP code from the list of ZIP codes
   public static int FindZipUsingIterator(int zipValue, String inputCheck) {
       if (zipValue != 0) {
           Iterator<ZipCode> iterator = zipList.iterator();
           while (iterator.hasNext()) {
               ZipCode ZipCo = iterator.next();
               if (inputCheck == "from") {
                   if (ZipCo.getZip() == (zipValue)) {
                       zipLatFrom = ZipCo.getLatitude();
                       zipLongFrom = ZipCo.getLongitude();
                       cityFrom = ZipCo.getCity();
                       return ZipCo.getZip();
                   }
               } else if (inputCheck == "to") {
                   if (ZipCo.getZip() == (zipValue)) {
                       zipLatDestination = ZipCo.getLatitude();
                       zipLongDestination = ZipCo.getLongitude();
                       cityDestination = ZipCo.getCity();
                       restaurantDestination = ZipCo.getTopRestaurant();
                       return ZipCo.getZip();
                   }
               }

           }
       }

       return 0;
   }

   // The core Algorithm utilizes Haversine formula (determines the great-circle distance between 2 points given their longitude and latitude.
   // This is the shortest distance over the earth's surface) to convert latitude and longitude points into distance (miles in this case)
   public static String calcDistance(double lat1, double lat2, double lon1, double lon2) {
       lon1 = Math.toRadians(lon1);
       lon2 = Math.toRadians(lon2);
       lat1 = Math.toRadians(lat1);
       lat2 = Math.toRadians(lat2);
       double dlon = lon2 - lon1;
       double dlat = lat2 - lat1;
       double a = Math.pow(Math.sin(dlat / 2), 2) +
               Math.cos(lat1) * Math.cos(lat2) *
                       Math.pow(Math.sin(dlon / 2), 2);

       double c = 2 * Math.asin(Math.sqrt(a));
       double r = 6371;
       double kilometers = c * r;
       double miles = kilometers / 1.6;

       return df.format(miles);
   }

   // Method CreateZipList creates 147 objects containing ZIP codes, it's latitude and longitude, city, and recommended restaurant
   // Method also creates a new list containing the ZIP code objects
   public static void CreateZipList() {
       ZipCode s1 = new ZipCode(92899, 33.835400, -117.914800, "Anaheim", "Red Lobster");
       ZipCode s2 = new ZipCode(92887, 33.883223, -117.73856, "Yorba Linda", "McDonald's");
       ZipCode s3 = new ZipCode(92886, 33.888062, -117.80407, "Yorba Linda", "Subway");
       ZipCode s4 = new ZipCode(92885, 33.887600, -117.802300, "Yorba Linda", "Red Lobster");
       ZipCode s5 = new ZipCode(92870, 33.881971, -117.850575, "Placentia", "Subway");
       ZipCode s6 = new ZipCode(92869, 33.792687, -117.79975, "Orange", "Red Lobster");
       ZipCode s7 = new ZipCode(92868, 33.787266, -117.87494, "Orange", "Chipotle Mexican Grill");
       ZipCode s8 = new ZipCode(92867, 33.811566, -117.82919, "Orange", "Red Lobster");
       ZipCode s9 = new ZipCode(92866, 33.785091, -117.84569, "Orange", "Chipotle Mexican Grill");
       ZipCode s10 = new ZipCode(92865, 33.831800, -117.84739, "Orange", "Red Lobster");
       ZipCode s11 = new ZipCode(92864, 33.787800, -117.852300, "Orange", "Olive Garden");
       ZipCode s12 = new ZipCode(92863, 33.787800, -117.852300, "Orange", "Red Lobster");
       ZipCode s13 = new ZipCode(92862, 33.811156, -117.699084, "Orange", "Chipotle Mexican Grill");
       ZipCode s14 = new ZipCode(92861, 33.812662, -117.81623, "Villa Park", "Red Lobster");
       ZipCode s15 = new ZipCode(92845, 33.782916, -118.02619, "Garden Grove", "Olive Garden");
       ZipCode s16 = new ZipCode(92844, 33.765516, -117.97310, "Garden Grove", "Red Lobster");
       ZipCode s17 = new ZipCode(92843, 33.763033, -117.93550, "Garden Grove", "Chipotle Mexican Grill");
       ZipCode s18 = new ZipCode(92841, 33.786915, -117.98224, "Garden Grove", "Red Lobster");
       ZipCode s19 = new ZipCode(92833, 33.874314, -117.96370, "Fullerton", "Chipotle Mexican Grill");
       ZipCode s20 = new ZipCode(92832, 33.869114, -117.92985, "Fullerton", "Red Lobster");
       ZipCode s21 = new ZipCode(92831, 33.878981, -117.89325, "Fullerton", "Red Lobster");
       ZipCode s22 = new ZipCode(92823, 33.918965, -117.83252, "Brea", "Red Lobster");
       ZipCode s23 = new ZipCode(92821, 33.923463, -117.89559, "Brea", "Chipotle Mexican Grill");
       ZipCode s24 = new ZipCode(92808, 33.858151, -117.73864, "Anaheim", "Olive Garden");
       ZipCode s25 = new ZipCode(92807, 33.850515, -117.79113, "Anaheim", "Red Lobster");
       ZipCode s26 = new ZipCode(92806, 33.835465, -117.87362, "Anaheim", "Olive Garden");
       ZipCode s27 = new ZipCode(92805, 33.835665, -117.90807, "Anaheim", "Red Lobster");
       ZipCode s29 = new ZipCode(92804, 33.818165, -117.97304, "Anaheim", "Chipotle Mexican Grill");
       ZipCode s30 = new ZipCode(92802, 33.806965, -117.92594, "Anaheim", "Red Lobster");
       ZipCode s31 = new ZipCode(92801, 33.844814, -117.95381, "Anaheim", "Olive Garden");
       ZipCode s32 = new ZipCode(92782, 33.739571, -117.78618, "Tustin", "Red Lobster");
       ZipCode s33 = new ZipCode(92780, 33.741651, -117.82127, "Tustin", "Olive Garden");
       ZipCode s34 = new ZipCode(92708, 33.708618, -117.95629, "Fountain Valley", "Red Lobster");
       ZipCode s35 = new ZipCode(92707, 33.719118, -117.87143, "Santa Ana Heights", "Red Lobster");
       ZipCode s36 = new ZipCode(92705, 33.762117, -117.81828, "Cowan Heights", "Red Lobster");
       ZipCode s37 = new ZipCode(92705, 33.762117, -117.81828, "Cowan Heights", "Red Lobster");
       ZipCode s38 = new ZipCode(92704, 33.724167, -117.90623, "Santa Ana", "Red Lobster");
       ZipCode s39 = new ZipCode(92703, 33.747067, -117.90433, "Santa Ana", "Chipotle Mexican Grill");
       ZipCode s40 = new ZipCode(92701, 33.747017, -117.86248, "Santa Ana", "Red Lobster");
       ZipCode s41 = new ZipCode(92694, 33.570763, -117.63463, "Ladera Ranch", "Red Lobster");
       ZipCode s42 = new ZipCode(92692, 33.615462, -117.64093, "Mission Viejo", "Olive Garden");
       ZipCode s43 = new ZipCode(92691, 33.611272, -117.66681, "Mission Viejo", "Red Lobster");
       ZipCode s44 = new ZipCode(92688, 33.639994, -117.60351, "Rancho Santa Mar", "Chipotle Mexican Grill");
       ZipCode s45 = new ZipCode(92683, 33.751418, -117.99392, "Westminster", "Red Lobster");
       ZipCode s46 = new ZipCode(92679, 33.639227, -117.58518, "Coto De Caza", "Red Lobster");
       ZipCode s47 = new ZipCode(92677, 33.531938, -117.70250, "Laguna Niguel", "Red Lobster");
       ZipCode s48 = new ZipCode(92676, 33.743168, -117.63563, "Silverado", "Red Lobster");
       ZipCode s49 = new ZipCode(92675, 33.500843, -117.65866, "Mission Viejo", "Red Lobster");
       ZipCode s50 = new ZipCode(92673, 33.462927, -117.62414, "San Clemente", "Chipotle Mexican Grill");
       ZipCode s51 = new ZipCode(92672, 33.427078, -117.61401, "San Clemente", "Red Lobster");
       ZipCode s52 = new ZipCode(92663, 33.619221, -117.93087, "Newport Beach", "Olive Garden");
       ZipCode s53 = new ZipCode(92662, 33.606271, -117.89243, "Newport Beach", "Red Lobster");
       ZipCode s54 = new ZipCode(92661, 33.601822, -117.89966, "Newport Beach", "Chipotle Mexican Grill");
       ZipCode s55 = new ZipCode(92660, 33.634821, -117.87453, "Newport Beach", "Red Lobster");
       ZipCode s56 = new ZipCode(92657, 33.610717, -117.83250, "Newport Beach", "Chipotle Mexican Grill");
       ZipCode s57 = new ZipCode(92656, 33.581533, -117.72474, "Aliso Viejo", "Chipotle Mexican Grill");
       ZipCode s58 = new ZipCode(92655, 33.745166, -117.98569, "Midway City", "Red Lobster");
       ZipCode s59 = new ZipCode(92653, 33.602823, -117.71295, "Laguna Hills", "Chipotle Mexican Grill");
       ZipCode s60 = new ZipCode(92651, 33.535325, -117.77192, "Laguna Niguel", "Red Lobster");
       ZipCode s61 = new ZipCode(92649, 33.720017, -118.04614, "Huntington Beach", "Red Lobster");
       ZipCode s62 = new ZipCode(92648, 33.673468, -118.00243, "Huntington Beach", "Chipotle Mexican Grill");
       ZipCode s63 = new ZipCode(92647, 33.725167, -118.00509, "Huntington Beach", "Applebee’s");
       ZipCode s64 = new ZipCode(92646, 33.666269, -117.96930, "Huntington Beach", "Red Lobster");
       ZipCode s65 = new ZipCode(92630, 33.640223, -117.69108, "Lake Forest", "Red Lobster");
       ZipCode s66 = new ZipCode(92629, 33.475302, -117.70327, "Monarch Bay", "Applebee’s");
       ZipCode s67 = new ZipCode(92627, 33.647028, -117.91506, "Costa Mesa", "Red Lobster");
       ZipCode s68 = new ZipCode(92626, 33.677224, -117.90863, "Costa Mesa", "Chipotle Mexican Grill");
       ZipCode s69 = new ZipCode(92625, 33.600172, -117.86823, "Corona Del Mar", "Red Lobster");
       ZipCode s70 = new ZipCode(92624, 33.460727, -117.66467, "Capistrano Beach", "Red Lobster");
       ZipCode s71 = new ZipCode(92620, 33.714889, -117.76330, "Irvine", "Red Lobster");
       ZipCode s72 = new ZipCode(92618, 33.659639, -117.73948, "Irvine", "Red Lobster");
       ZipCode s73 = new ZipCode(92614, 33.680408, -117.82592, "Irvine", "Applebee’s");
       ZipCode s74 = new ZipCode(92612, 33.650813, -117.81771, "Irvine", "Red Lobster");
       ZipCode s75 = new ZipCode(92610, 33.685479, -117.66690, "Foothill Ranch", "Chipotle Mexican Grill");
       ZipCode s76 = new ZipCode(92606, 33.695576, -117.80588, "Irvine", "Red Lobster");
       ZipCode s77 = new ZipCode(92604, 33.687620, -117.78852, "Irvine", "Red Lobster");
       ZipCode s78 = new ZipCode(92602, 33.732970, -117.76932, "Irvine", "Red Lobster");
       ZipCode s79 = new ZipCode(90743, 33.729750, -118.08630, "Surfside", "Red Lobster");
       ZipCode s80 = new ZipCode(90742, 33.718167, -118.07179, "Sunset Beach", "Chipotle Mexican Grill");
       ZipCode s81 = new ZipCode(90740, 33.759283, -118.08239, "Seal Beach", "Red Lobster");
       ZipCode s82 = new ZipCode(90720, 33.794028, -118.07218, "Rossmoor", "Red Lobster");
       ZipCode s83 = new ZipCode(90680, 33.803565, -117.99596, "Stanton", "Applebee’s");
       ZipCode s84 = new ZipCode(90631, 33.934513, -117.95159, "La Habra Heights", "Red Lobster");
       ZipCode s85 = new ZipCode(90630, 33.817481, -118.03990, "Cypress", "Red Lobster");
       ZipCode s86 = new ZipCode(90623, 33.849327, -118.04395, "Cerritos", "Chipotle Mexican Grill");
       ZipCode s87 = new ZipCode(90621, 33.875714, -117.99404, "Buena Park", "Red Lobster");
       ZipCode s88 = new ZipCode(90620, 33.841014, -118.00969, "Buena Park", "Applebee’s");
       ZipCode s89 = new ZipCode(92871, 33.884800, -117.857800, "Placentia", "Red Lobster");
       ZipCode s28 = new ZipCode(90622, 33.867700, -117.997400, "Buena Park", "Chipotle Mexican Grill");
       ZipCode s90 = new ZipCode(90622, 33.867700, -117.997400, "Buena Park", "Red Lobster");
       ZipCode s91 = new ZipCode(90632, 33.931900, -117.945500, "La Habra", "Applebee’s");
       ZipCode s92 = new ZipCode(90632, 33.931900, -117.945500, "La Habra", "Red Lobster");
       ZipCode s93 = new ZipCode(90721, 33.803200, -118.071400, "Los Alamitos", "Red Lobster");
       ZipCode s94 = new ZipCode(92603, 33.624468, -117.795152, "Irvine", "Red Lobster");
       ZipCode s95 = new ZipCode(92605, 33.703600, -117.994200, "Huntington Beach", "Red Lobster");
       ZipCode s96 = new ZipCode(92607, 33.542500, -117.782500, "Laguna Niguel", "Red Lobster");
       ZipCode s97 = new ZipCode(92609, 33.595500, -117.707700, "Lake Forest", "Chipotle Mexican Grill");
       ZipCode s98 = new ZipCode(92615, 33.660600, -117.998300, "Huntington Beach", "Red Lobster");
       ZipCode s99 = new ZipCode(92616, 33.669500, -117.822200, "Irvine", "Red Lobster");
       ZipCode s100 = new ZipCode(92617, 33.643014, -117.841949, "Irvine", "Red Lobster");
       ZipCode s101 = new ZipCode(92620, 33.708990, -117.758996, "Irvine", "Applebee’s");
       ZipCode s102 = new ZipCode(92623, 33.669500, -117.822200, "Irvine", "Red Lobster");
       ZipCode s103 = new ZipCode(92628, 33.641400, -117.917800, "Costa Mesa", "Red Lobster");
       ZipCode s104 = new ZipCode(92637, 33.607122, -117.731077, "Laguna Woods", "Chipotle Mexican Grill");
       ZipCode s105 = new ZipCode(92650, 33.675800, -117.758700, "Irvine", "Red Lobster");
       ZipCode s106 = new ZipCode(92652, 33.542500, -117.782500, "Laguna Beach", "Applebee’s");
       ZipCode s107 = new ZipCode(92654, 33.542500, -117.782500, "Laguna Hills", "Red Lobster");
       ZipCode s108 = new ZipCode(92658, 33.618700, -117.928300, "Newport Beach", "Chipotle Mexican Grill");
       ZipCode s109 = new ZipCode(92659, 33.618700, -117.928300, "Newport Beach", "Red Lobster");
       ZipCode s110 = new ZipCode(92674, 33.426600, -117.611200, "San Clemente", "Red Lobster");
       ZipCode s111 = new ZipCode(92678, 33.663600, -117.589400, "Unincorporated OC", "Red Lobster");
       ZipCode s112 = new ZipCode(92684, 33.759500, -118.005900, "Westminster", "Chipotle Mexican Grill");
       ZipCode s113 = new ZipCode(92685, 33.749700, -117.989000, "Westminster", "Red Lobster");
       ZipCode s114 = new ZipCode(92690, 33.600200, -117.671100, "Mission Viejo", "Red Lobster");
       ZipCode s115 = new ZipCode(92693, 33.501700, -117.661800, "San Juan Capistrano", "Red Lobster");
       ZipCode s116 = new ZipCode(92697, 33.647320, -117.840944, "Irvine", "Red Lobster");
       ZipCode s117 = new ZipCode(92698, 33.669500, -117.822300, "Aliso Viejo", "Chipotle Mexican Grill");
       ZipCode s118 = new ZipCode(92702, 33.745700, -117.866900, "Santa Ana", "Red Lobster");
       ZipCode s119 = new ZipCode(92711, 33.745700, -117.866900, "Santa Ana", "Applebee’s");
       ZipCode s120 = new ZipCode(92712, 33.745700, -117.866900, "Santa Ana", "Red Lobster");
       ZipCode s121 = new ZipCode(92728, 33.709400, -117.952600, "Fountain,Valley", "Red Lobster");
       ZipCode s122 = new ZipCode(92735, 33.745800, -117.866700, "Santa Ana", "Applebee’s");
       ZipCode s123 = new ZipCode(92781, 33.739200, -117.816200, "Tustin", "Red Lobster");
       ZipCode s124 = new ZipCode(92799, 33.745800, -117.866700, "Santa Ana", "Applebee’s");
       ZipCode s125 = new ZipCode(92803, 33.835500, -117.913600, "Anaheim", "Red Lobster");
       ZipCode s126 = new ZipCode(92809, 33.844400, -117.951900, "Anaheim Hills", "Red Lobster");
       ZipCode s127 = new ZipCode(92811, 33.865700, -117.830100, "Placentia", "Chipotle Mexican Grill");
       ZipCode s128 = new ZipCode(92812, 33.835500, -117.913600, "Anaheim", "Red Lobster");
       ZipCode s129 = new ZipCode(92814, 33.835500, -117.913600, "Anaheim", "Applebee’s");
       ZipCode s130 = new ZipCode(92815, 33.835500, -117.913600, "Anaheim", "Red Lobster");
       ZipCode s131 = new ZipCode(92816, 33.835500, -117.913600, "Anaheim", "Chipotle Mexican Grill");
       ZipCode s132 = new ZipCode(92817, 33.835500, -117.913600, "Anaheim", "Red Lobster");
       ZipCode s133 = new ZipCode(92822, 33.923500, -117.890400, "Brea", "Red Lobster");
       ZipCode s134 = new ZipCode(92825, 33.835500, -117.913600, "Anaheim", "Red Lobster");
       ZipCode s135 = new ZipCode(92834, 33.879600, -117.897800, "Fullerton", "Applebee’s");
       ZipCode s136 = new ZipCode(92835, 33.902868, -117.905932, "Fullerton", "Red Lobster");
       ZipCode s137 = new ZipCode(92836, 33.879600, -117.897800, "Fullerton", "Chipotle Mexican Grill");
       ZipCode s138 = new ZipCode(92837, 33.879600, -117.897800, "Fullerton", "Red Lobster");
       ZipCode s139 = new ZipCode(92838, 33.879600, -117.897800, "Fullerton", "Red Lobster");
       ZipCode s140 = new ZipCode(92840, 33.788624, -117.927680, "Garden Grove", "Red Lobster");
       ZipCode s141 = new ZipCode(92842, 33.787500, -117.933200, "Garden Grove", "Applebee’s");
       ZipCode s142 = new ZipCode(92846, 33.787500, -117.933200, "Garden Grove", "Red Lobster");
       ZipCode s143 = new ZipCode(92850, 33.835500, -117.913600, "Anaheim", "Red Lobster");
       ZipCode s144 = new ZipCode(92856, 33.787800, -117.852300, "Orange", "Applebee’s");
       ZipCode s145 = new ZipCode(92859, 33.787800, -117.852300, "Orange", "Chipotle Mexican Grill");
       ZipCode s146 = new ZipCode(92857, 33.787800, -117.852300, "Orange", "Red Lobster");
       ZipCode s147 = new ZipCode(90620, 33.841014, -118.00969, "Buena Park", "Auntie Anne’s");

       // Adds ZipCode objects to a list
       zipList.add(s1); zipList.add(s2); zipList.add(s3); zipList.add(s4); zipList.add(s5); zipList.add(s6); zipList.add(s7); zipList.add(s8); zipList.add(s9);
       zipList.add(s10); zipList.add(s11); zipList.add(s12); zipList.add(s13); zipList.add(s14); zipList.add(s15); zipList.add(s16); zipList.add(s17);
       zipList.add(s18); zipList.add(s19); zipList.add(s20); zipList.add(s21); zipList.add(s22); zipList.add(s23); zipList.add(s24); zipList.add(s25);
       zipList.add(s26); zipList.add(s27); zipList.add(s28); zipList.add(s29); zipList.add(s30); zipList.add(s31); zipList.add(s32); zipList.add(s33);
       zipList.add(s34); zipList.add(s35); zipList.add(s36); zipList.add(s37); zipList.add(s38); zipList.add(s39); zipList.add(s40); zipList.add(s41);
       zipList.add(s42); zipList.add(s43); zipList.add(s44); zipList.add(s45); zipList.add(s46); zipList.add(s47); zipList.add(s48); zipList.add(s49);
       zipList.add(s50); zipList.add(s51); zipList.add(s52); zipList.add(s53); zipList.add(s54); zipList.add(s55); zipList.add(s56); zipList.add(s57);
       zipList.add(s58); zipList.add(s59); zipList.add(s60); zipList.add(s61); zipList.add(s62); zipList.add(s63); zipList.add(s64); zipList.add(s65);
       zipList.add(s66); zipList.add(s67); zipList.add(s68); zipList.add(s69); zipList.add(s70); zipList.add(s71); zipList.add(s72); zipList.add(s73);
       zipList.add(s74); zipList.add(s75); zipList.add(s76); zipList.add(s77); zipList.add(s78); zipList.add(s79); zipList.add(s80); zipList.add(s81);
       zipList.add(s82); zipList.add(s83); zipList.add(s84); zipList.add(s85); zipList.add(s86); zipList.add(s87); zipList.add(s88); zipList.add(s89);
       zipList.add(s90); zipList.add(s91); zipList.add(s92); zipList.add(s93); zipList.add(s94); zipList.add(s95); zipList.add(s96); zipList.add(s97);
       zipList.add(s98); zipList.add(s99); zipList.add(s100); zipList.add(s101); zipList.add(s102); zipList.add(s103); zipList.add(s104); zipList.add(s105);
       zipList.add(s106); zipList.add(s107); zipList.add(s108); zipList.add(s109); zipList.add(s110); zipList.add(s111); zipList.add(s112); zipList.add(s113);
       zipList.add(s114); zipList.add(s115); zipList.add(s116); zipList.add(s117); zipList.add(s118); zipList.add(s119); zipList.add(s120); zipList.add(s121);
       zipList.add(s122); zipList.add(s123); zipList.add(s124); zipList.add(s125); zipList.add(s126); zipList.add(s127); zipList.add(s128); zipList.add(s129);
       zipList.add(s130); zipList.add(s131); zipList.add(s132); zipList.add(s133); zipList.add(s134); zipList.add(s135); zipList.add(s136); zipList.add(s137);
       zipList.add(s138); zipList.add(s139); zipList.add(s140); zipList.add(s141); zipList.add(s142); zipList.add(s143); zipList.add(s144); zipList.add(s145);
       zipList.add(s146); zipList.add(s147);
   }
}
