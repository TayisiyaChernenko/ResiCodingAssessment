package main.java;
import java.io.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Main {

    public static void fetch_image(String validDate){
        System.out.println(validDate);
    }
    public static void run(){
        try {
            File file = new File(Main.class.getClassLoader().getResource("dates.txt").getFile());
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){   
                String rawDate = sc.nextLine();
                String normDate = DateNormalization.normalize(rawDate);
                if(normDate.equals("Invalid date")){
                    System.out.println(rawDate + " is invalid");
                }
                else{
                    fetch_image(normDate);
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