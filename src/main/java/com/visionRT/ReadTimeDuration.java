package com.visionRT;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

/*From the log file you need to read the timestamp from line containing text frmMain::CurrentDeltas – entered and also read the timestamp from line containing text frmMain::CurrentDeltas – exited to calculate the time and display.
There may be multiple occurrence of the above messages, so need to calculate for each occurrence.
You are free to use any programming language, please send us a working code and a note to explain the logic.
*/

public class ReadTimeDuration {
    public static void main(String args[]) {
        try {
            //reading the .log file
            FileInputStream fileInputStream = new FileInputStream("src\\test\\Resources\\Test01.log");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
            String logLine;
            List<Double> entered = new ArrayList<>();
            List<Double> exited = new ArrayList<>();
            while ((logLine = br.readLine()) != null) {
                if (logLine.contains("frmMain::CurrentDeltas")) {
                    if (logLine.contains("entered")) {
                        System.out.println(logLine);
                        double temp = Double.parseDouble(logLine.substring(8, 20));
                        System.out.println("\tEntered:" + temp);
                        entered.add(temp);

                    } else {
                        System.out.println(logLine);
                        double temp = Double.parseDouble(logLine.substring(8, 20));
                        System.out.println("\t Exit: " + temp);
                        exited.add(temp);
                    }
                }
            }
            System.out.println("_________________________________________________");
            for (int i = 0; i < entered.size(); i++) {
                double temp = exited.get(i) - entered.get(i);
                Formatter fmt = new Formatter();
                fmt.format("%.8f", temp);
                System.out.println("Total Duration for " + (i + 1) + " occurrence: " + fmt);
            }
            System.out.println("_________________________________________________");
            fileInputStream.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}


