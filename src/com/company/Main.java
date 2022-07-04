package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //1. create a new file with those text
        //2. open the file
        //3. Read the file line by line
        //4. process the errors from the line
        //5. close the file
        //6. print the result

        FileInputStream file = null;
        try {
            file = new FileInputStream("./src/com/company/foo.log");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(file));
        String lineRead;

        List<String> logsFound = new ArrayList<String>();
        while (true) {

            try {
                if (!((lineRead = br.readLine()) != null)) break;

                if (lineRead.contains("ERROR")) {
                    // Print the content on the console
//                    System.out.println(lineRead);

                    logsFound.add(lineRead.split(" ")[1]);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : logsFound) {
            System.out.println(s);
        }
        List<String> uniqueIds = logsFound.stream().distinct().collect(Collectors.toList());

        for(String s:uniqueIds){
            System.out.println( s + " --> " + logsFound.stream().filter(n -> n.equals(s)).count());
        }
    }
}
