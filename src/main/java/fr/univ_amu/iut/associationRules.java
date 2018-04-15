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
        if((line.length()-line.substring(pos,line.length()).length())==0)
            return line.substring(0, pos);
        else
            return line.substring(0,pos-1);
    }

    private String removeSubstr(String toRemove,String myString)
    {
        int pos=myString.indexOf(toRemove);

        if(pos==-1)
            return myString;

        if(pos==0)
            return myString.substring(toRemove.length()+1,myString.length());
        else if(pos+toRemove.length()==myString.length())
            return myString.substring(0,pos);
        else
            return myString.substring(0,pos)+myString.substring(pos+toRemove.length(),myString.length());

    }

    void generate(String fileName, double minConf) {
        ArrayList<String> motifs = new ArrayList<String>();
        ArrayList<String> treatedMotifs = new ArrayList<String>();
        ArrayList<String> readValues = new ArrayList<>();

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
                int countAppearanceOfCurrent=0;
                for (String tmp : readValues)
                {
                    if(tmp.contains(currentMotif))
                        ++countAppearanceOfCurrent;
                }
                for (String tmpMotif : motifs)
                {
                    if(tmpMotif.contains(currentMotif) && !tmpMotif.matches(currentMotif) &&tmpMotif.length()>1)
                    {
                        int count=0;
                        for(String readValue : readValues)
                        {
                            if(readValue.contains(tmpMotif))
                                ++count;
                        }
                        if((double)count/countAppearanceOfCurrent>=minConf && !treatedMotifs.contains(tmpMotif))
                            treatedMotifs.add(currentMotif + "->" + removeSubstr(currentMotif,tmpMotif)+"/"+(double)count/countAppearanceOfCurrent);
                    }
                }

            }

            for(String tmp : treatedMotifs)
            {
                out.write(tmp+"\n");
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

