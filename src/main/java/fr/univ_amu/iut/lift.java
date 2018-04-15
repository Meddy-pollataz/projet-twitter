package fr.univ_amu.iut;

import java.io.*;
import java.util.ArrayList;

public class lift {
    void generateLiftFile(String fileName, double minLift)
    {
        try {

            if(!fileName.endsWith("minConf.out"))
                throw new Exception();
            String outputFileName="";
            if(fileName.endsWith(".out"))
                outputFileName = fileName.substring(0,fileName.length()-11) + "lift.out";
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            FileWriter out = new FileWriter(outputFileName);

            ArrayList<String> associationRules = new ArrayList<>();
            ArrayList<String> Ylist = new ArrayList<>();
            String tmpLine;
            String line;

            in.close();
            out.close();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            System.out.println("Le fichier " + fileName + " n'existe pas");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Le type du fichier est incorrect");
        }
    }

}
