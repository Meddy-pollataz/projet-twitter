package fr.univ_amu.iut;

public class twitMinerMain {
    public static void main(String[] args) {
        associationRules assoc = new associationRules();
        assoc.generate("test.out",0.7);
    }
}
