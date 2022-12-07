import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.zipcode.data.dto.ZipCodeDTO;
import com.kenzie.app.zipcode.format.AddressFormatter;
import com.kenzie.app.zipcode.http.HTTPConnector;

import java.util.Scanner;


public class Application {
    public static void main(String[] args) {
        try {
            //declare variables
            String BASE_URL = "https://www.zippopotam.us/";
            String urlPath;
            Scanner scanner = new Scanner(System.in);
            String recipientName;
            String streetAddress;
            String city;
            String state;
            String zipCode;

            //Use scanner - read in user input
            System.out.println("Enter recipient first and last name:");
            recipientName = scanner.nextLine();

            //Use scanner - read in user input
            System.out.println("Enter street address:");
            streetAddress = scanner.nextLine();

            System.out.println("Enter city:");
            city = scanner.nextLine();

            System.out.println("Enter state: (two letter abbreviation)");
            state = scanner.nextLine();

            //Clean user input
            //replace spaces with %20
            String cityURL = city.replaceAll(" ", "%20");

            //Create URL
            //append BASE_URL to the path - state/city
            urlPath = BASE_URL + "us/" + state + "/" + cityURL;

            //Call HTTP GET
            String httpResponse = HTTPConnector.makeGETRequest(urlPath);

            if (httpResponse.equals("\\{\\}")) {
                System.out.println("No valid zip code found");
            } else {
                //Use ObjectMapper on the DTO
                ObjectMapper objectMapper = new ObjectMapper();

                ZipCodeDTO zipObj = objectMapper.readValue(httpResponse, ZipCodeDTO.class);

                //Loop and ask user for zipcode if more than one
                //zipObj.getPlaces()

                if(zipObj.getPlaces().size()==1){
                    zipCode = zipObj.getPlaces().get(0).getPostCode();
                }else{
                    //print out menu
                    System.out.println("Choose a zip code.");
                    for (int i = 0; i < zipObj.getPlaces().size(); i++) {
                        System.out.println(i + ")" +zipObj.getPlaces().get(i).getPostCode());
                    }
                    //read in choice
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    //set zipcode to choice
                    zipCode = zipObj.getPlaces().get(choice).getPostCode();
                }

                AddressFormatter.initializeAddressMap();
                //address info - need to format
                System.out.println("Name: " + AddressFormatter.formatAddress(recipientName));
                System.out.println("Street address: " + AddressFormatter.formatStreetAddress(streetAddress));
                System.out.println("City: " + AddressFormatter.formatAddress(city));
                System.out.println("State: " + AddressFormatter.formatAddress(state));
                System.out.println("Zip: " + zipCode);
            }
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e);
        }


    }

    public static void main_backup(String[] args) {
        try {
            //API - https://www.zippopotam.us/us/ca/Los%20angeles
            // https://jamboard.google.com/d/1n9-68L50Ba8Yn1EMtG_Ri1jVhkridA7kb9uo7HPc7N4/viewer?f=0

            //declare variables
            final String TEST_URL = "https://www.zippopotam.us/us/ca/Los%20angeles";
            final String TEST_FAIL_URL = "https://www.zippopotam.us/us/ca/Los%20%20angeles";

            String httpResponseStr;

            //Read in user input - Scanner


            //Format user input using AddressFormatter

            AddressFormatter.initializeAddressMap();
            AddressFormatter.replaceAbbreviation("123 Main St.");


            //Connect to zippopotam.us and get zipcode
            httpResponseStr = HTTPConnector.makeGETRequest(TEST_URL);

            System.out.println(httpResponseStr);

            //ObjectMapper
            //1. Instantiate ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            //Declare DTO object
            ZipCodeDTO zipObj;

            //read data - readValue()
            zipObj = objectMapper.readValue(httpResponseStr, ZipCodeDTO.class);

            //Print out place name, zipcode and State
            System.out.println("City:" + zipObj.getPlaces().get(0).getPlace_name());
            System.out.println("Zipcode:" + zipObj.getPlaces().get(0).getPostCode());
            System.out.println("State:" + zipObj.getState());

            //check if more than 1
            if (zipObj.getPlaces().size() > 1) {

                //Loop and print the list of all zipcodes
                for (int i = 0; i < zipObj.getPlaces().size(); i++) {
                    System.out.println("City:" + zipObj.getPlaces().get(i).getPlace_name());
                    System.out.println("Zipcode:" + zipObj.getPlaces().get(i).getPostCode());
                    System.out.println("State:" + zipObj.getState());
                }
            }

            //Failure case
            //System.out.println("Failure case");
            //httpResponseStr = HTTPConnector.makeGETRequest(TEST_FAIL_URL);
            //System.out.println(httpResponseStr);
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e);
        }

    }
}
