package server;

import java.io.*;
import java.util.Scanner;

public class FileInfo {
    public static String[] getInfo(String fileName) {
        String[] file = new String[2];

        try {
            int i = 0;
            Scanner sc = new Scanner(new File(fileName));
            while(sc.hasNext()) {
                file[i++] = sc.next();
            }
        } catch(FileNotFoundException e) {
            //When file not found, set default information
            System.out.println("Error message: File not found exception");
            file[0] = "127.0.0.1";
            file[1] = "1234";
        }

        return file;
    }
}
