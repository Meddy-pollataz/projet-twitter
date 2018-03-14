package fr.univ_amu.iut;

import java.io.*;
import java.util.StringTokenizer;

public class twitMinerMain {
    public static void main(String[] args) {
        File dataFile = new File("/home/t16016200/Documents/document.txt");
        try {
            InputStream ips = new FileInputStream(dataFile);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String ligne;
            while ((ligne = br.readLine()) != null) {
// recuperation de la ligne courante
                System.out.println("Contenu de la ligne:" + ligne);
// separation de la ligne avec le toke " " (espace)
//
                String token = " ";
                StringTokenizer stringTokenizer = new StringTokenizer(ligne, token);
// Parcours des tokens de la ligne
                while (stringTokenizer.hasMoreElements()) {
                    String element = (String) stringTokenizer.nextElement();
                    System.out.println("Element : " + element);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
