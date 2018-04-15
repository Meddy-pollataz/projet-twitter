package fr.univ_amu.iut;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class associationRules {
    private String getMotif(String line) {
        int pos = 0;
        for (int i = 0; i < line.length(); ++i) {
            if (line.charAt(i) == '(')
                pos = i;
        }
        return line.substring(0, pos);
    }

    void generate(String fileName, double minConf) {
        ArrayList<String> motifs = new ArrayList<String>();
        ArrayList<String> treatedMotifs = new ArrayList<String>();
        ArrayList<String> readValues = new ArrayList<String>();

        try {

            if (!fileName.endsWith(".out"))
                throw new Exception();

            String outputFileName = fileName.substring(0, fileName.length() - 4) + "minConf.out";
            String inputFileName = fileName.substring(0,fileName.length()-3) + "trans";
            BufferedReader inMotifs = new BufferedReader(new FileReader(fileName));
            BufferedReader inValues = new BufferedReader(new FileReader(inputFileName));
            FileWriter out = new FileWriter(outputFileName);
            String line;

            while ((line = inMotifs.readLine()) != null)
                motifs.add(getMotif(line));

            inMotifs.close();

            while((line=inValues.readLine())!=null)
                readValues.add(line);

            inValues.close();
            for (String currentMotif : motifs)
            {
                int count=0;
                for (String tmpMotif : motifs)
                {
                    if(currentMotif.contains(tmpMotif)&&!currentMotif.matches(tmpMotif))
                        ++count;
                }
                if((double)(count/readValues.size())>=minConf && !treatedMotifs.contains(currentMotif))
                    treatedMotifs.add(currentMotif);

            }
            for(String tmp : treatedMotifs)
            {
                out.write(tmp);
                out.flush();
            }
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

