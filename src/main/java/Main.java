package main.java;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void run(){
        try {
            File file = new File(Main.class.getClassLoader().getResource("dates.txt").getFile());
            Scanner sc = new Scanner(file);
            MarsRoverClient client = new MarsRoverClient();
            // For each date in the dates.txt file, normalize the date and download the photo
            while (sc.hasNextLine()){   
                String rawDate = sc.nextLine();
                String validDate = DateNormalization.normalize(rawDate);
                if(validDate.equals("Invalid date")){
                    System.out.println(rawDate + " is invalid");
                }
                else{
                    try {
                        //download the photo using the normalized date
                        client.downloadPhotosForDate(validDate);
                    } catch (IOException | InterruptedException e) {
                        System.out.println("Error downloading photo for date " + rawDate + ": " + e.getMessage());
                    }
                }
            }   
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        run();
    }
}