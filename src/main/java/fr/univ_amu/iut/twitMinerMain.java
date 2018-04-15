package fr.univ_amu.iut;

import java.io.*;
import java.util.StringTokenizer;

public class twitMinerMain {
    public static void main(String[] args) {
        associationRules association = new associationRules();
        association.generate("test.out",0.5);
    }
}
