package fr.univ_amu.iut;

import java.io.*;

public class converterToCSV {

    void convert(String fileName)
    {
        try {

            if(!fileName.endsWith(".out")&&!fileName.endsWith(".trans"))
                throw new Exception();
            String outputFileName="";
            if(fileName.endsWith(".out"))
                outputFileName = fileName.substring(0,fileName.length()-3) + "csv";
            if(fileName.endsWith(".trans"))
                outputFileName = fileName.substring(0,fileName.length()-5) + "csv";
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            FileWriter out = new FileWriter(outputFileName);
            String tmpLine;
            String line;

            while((line=in.readLine())!=null)
            {
                tmpLine="";

                for (int i=0;i<line.length();++i)
                {
                    if(line.charAt(i)==' ' && i>25)//25 est la position de fin de la date, on ne veut pas traiter les espaces de la date en tant que ';'
                        tmpLine= tmpLine + ';';

                    else
                        tmpLine = tmpLine + line.charAt(i);

                }
                tmpLine=tmpLine+'\n';
                out.write(tmpLine);
                out.flush();



            }
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
