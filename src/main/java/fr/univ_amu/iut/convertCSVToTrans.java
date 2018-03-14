package fr.univ_amu.iut;

import java.io.*;

public class convertCSVToTrans {

    void convert(String fileName)
    {
        try {

            if(!fileName.endsWith(".csv"))
                throw new Exception();

            String outputFileName = fileName.substring(0,fileName.length()-3) + "trans";
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            PrintWriter out = new PrintWriter(outputFileName);
            String tmpLine;
            String line;

            while((line=in.readLine())!=null)
            {
                tmpLine="";

                for (int i=0;i<line.length();++i)
                {
                    if(line.charAt(i)!=';')
                        tmpLine= tmpLine + line.charAt(i);

                    else
                        tmpLine = tmpLine + ' ';

                }
                out.println(tmpLine);


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
