package Noyau;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class Appartement extends Habitable implements Serializable {
    private int num_etage;
    private boolean simp_dup;
    private boolean ascenseur;
    public void affInit()
    {
        System.out.println("Ce bien est un appartement ");
        super.affInit();

    }

    public void Afficher()
    {
        System.out.println("Ce bien est un appartement qui se caractérise par ce qui quit: ");
        super.Afficher();
        System.out.println("le ombre d'num_etage de l'appartement: "+ num_etage);
        System.out.println("cet appartement et de type: "+simp_dup);

    }
    public Appartement(boolean prix_nego, Transaction nat_transaction, String photos, Date date, double superficie, String descriptif, double prix, Proprietaire prop,
                       String adresse, Wilaya wilaya, int nbrs_pieces, int num_etage, boolean meuble, boolean simp_dup, boolean ascenseur)
    {
        super(prix_nego,nat_transaction,photos,date,superficie,descriptif,prix,prop,adresse,wilaya, nbrs_pieces, meuble);
        this.num_etage= num_etage;
        this.simp_dup=simp_dup;
        this.ascenseur=ascenseur;
    }

    public double Calcul_prix(boolean wilayaEchange)
    {
        double prix_initial=this.prix_inititial();
        Transaction nat=this.getNat_transaction();
        if(nat==Transaction.vente || nat==Transaction.echange)
        {
            if (num_etage <3)
            {prix_initial=prix_initial+50000;}
            if (nat==Transaction.echange && wilayaEchange)
            {
                System.out.println("Veuillez entrer le numéro correspendant à la wilaya de l'echange de ce bien\n                       ***");
                this.affInit();
                Wilaya tab_wilaya[] = Wilaya.values();
                int ind;
                for ( ind = 0; ind < tab_wilaya.length; ind++) { System.out.println((ind) + "." + tab_wilaya[ind]); }
                Scanner sc = new Scanner(System.in);
                ind=Recuperation_input.lectInt(ind-1);
                if (tab_wilaya[ind]!=(this.getWilaya()))
                {prix_initial=prix_initial+0.0025*this.getPrix();}
            }
        }
        if(nat==Transaction.location)
        {
            if(num_etage <3)
            {
                prix_initial=prix_initial+5000;
            }
            if((num_etage >5) &&(!ascenseur))
            { prix_initial=prix_initial-500*this.getSuperficie();}

        }
        return  prix_initial;
    }
    //Setters
    public void setNum_etage(int num_etage) {
        this.num_etage = num_etage;
    }

    public void setSimp_dup(boolean simp_dup) {
        this.simp_dup = simp_dup;
    }

    public int getNum_etage() {
        return num_etage;
    }

    public boolean getSimp_dup() {
        return simp_dup;
    }

    public boolean getAscenseur() {
        return ascenseur;
    }

    public void setAscenseur(boolean ascenseur) {
        this.ascenseur = ascenseur;
    }

    public boolean isAscenseur() {
        return ascenseur;
    }
}
