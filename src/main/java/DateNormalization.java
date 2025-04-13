package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateNormalization {
    
    // List of possible formats of the dates from dates.txt
    private static final DateTimeFormatter[] inputFormats = new DateTimeFormatter[] {
        DateTimeFormatter.ofPattern("MM/dd/yy"),
        DateTimeFormatter.ofPattern("MMM-dd-yyyy"),    
        DateTimeFormatter.ofPattern("MMMM d, yyyy")      
    };
    // the date format required for the Mars Rover API
    private static final DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String normalize(String rawDate){
        // try each formatter pattern against the raw text until one matches, convert to output format
        for (DateTimeFormatter formatter : inputFormats){
            try{
                LocalDate parsedDate = LocalDate.parse(rawDate, formatter);
                return parsedDate.format(outputFormat);
            }
            //A parse exception occurs if the pattern doesn't match the text.
            catch(DateTimeParseException ignored){}
        }
        // If here, no match was found or the data does not exist
        return "Invalid date: " + rawDate;
    }
}