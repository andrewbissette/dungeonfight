package utils;

import java.util.Scanner;

/**
 * Created by andrewbissette on 08/01/2017.
 *
 * for input and ouput
 *
 */
public class Text {

    public static void log(String comment) {
        // because I'm lazy
        // later might actually be useful and may go to a utils package later
        System.out.println(comment);
    }

    public static String read(String prompt) {
        // super lazy utility for prompts
        // this will probably go to a utils package later
        // new scanner to read input
        Scanner scanner = new Scanner(System.in);

        // prompt for input
        System.out.println(prompt);

        // get input
        return scanner.next();
    }


}
