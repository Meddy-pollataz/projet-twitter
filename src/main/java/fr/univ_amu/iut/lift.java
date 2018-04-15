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
            String inputFileName = fileName.substring(0, fileName.length()-11)+".trans";
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            BufferedReader inRead = new BufferedReader(new FileReader(inputFileName));
            FileWriter out = new FileWriter(outputFileName);

            ArrayList<String> associationRules = new ArrayList<>();
            ArrayList<Double> associationRulesValues = new ArrayList<>();
            ArrayList<String> associationReadedValues = new ArrayList<>();
            ArrayList<String> Ylist = new ArrayList<>();
            ArrayList<Integer> YlistCount = new ArrayList<>();
            ArrayList<String> validValues = new ArrayList<>();
            ArrayList<String> read = new ArrayList<>();
            String tmpLine;
            String line;

            while((line=in.readLine())!=null)
                associationReadedValues.add(line);

            while((line=inRead.readLine())!=null)
                read.add(line);


            for(String tmp : associationReadedValues)
            {
                associationRules.add(tmp.substring(0,tmp.indexOf('/')));
                associationRulesValues.add(Double.parseDouble(tmp.substring(tmp.indexOf('/')+1)));
                Ylist.add(tmp.substring(tmp.indexOf("->")+2,tmp.indexOf('/')));

            }

            for(String y : Ylist)
            {
                int count=0;
                for(String readLine : read)
                {
                    if(readLine.contains(y))
                        ++count;
                }
                YlistCount.add(count);
            }
            inRead.close();

            for(int i=0;i<associationRules.size();++i)//Les trois arrays auront forcément la même taille à chaque fois
            {
                if ((double) associationRulesValues.get(i) /( (double) YlistCount.get(i) / read.size()) >= minLift)
                {
                    validValues.add(associationRules.get(i));
                }
            }

            in.close();

            for(String tmp:validValues)
            {
                out.write(tmp+"\n");
                out.flush();
            }

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
