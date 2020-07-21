package Noyau;

import java.io.Serializable;
import java.util.Date;

public abstract class Habitable extends Bien implements Serializable {
    private int nbrs_pieces;
    private boolean meuble;
    public Habitable(boolean prix_nego,Transaction nat_transaction,String photos,Date date,double superficie,String descriptif,double prix,Proprietaire prop,
                     String adresse,Wilaya wilaya,int nbrs_pieces,boolean meuble)
    {    super(prix_nego,nat_transaction,photos,date,superficie,descriptif,prix,prop,adresse,wilaya);
        this.nbrs_pieces=nbrs_pieces;
        this.meuble=meuble;

    }
    public void Afficher()
    {   super.Afficher();
        System.out.println("le nombre de pieces  "+nbrs_pieces);
        System.out.println("meublé?  "+ meuble);
    }
    public int getNbrs_pieces()
    {
        return nbrs_pieces;
    }

    public boolean isMeuble() {
        return meuble;
    }

    //Setters

    public void setNbrs_pieces(int nbrs_pieces) {
        this.nbrs_pieces = nbrs_pieces;
    }

    public void setMeuble(boolean meuble) {
        this.meuble = meuble;
    }
}
