package main.java;

public class Main {
    public static void main(String[] args){
        String normDate = DateNormalization.normalize("June 2, 2018");
        System.out.println(normDate);
    }
}