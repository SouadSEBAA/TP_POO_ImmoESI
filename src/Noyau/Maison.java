package Noyau;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class Maison extends Habitable implements Serializable {
    private int etage;
    private boolean picsine;
    private boolean garage;
    private double superficie_habitable;
    private boolean jardin;

    //********************************************
    //   Les methodes
    public Maison(boolean prix_nego, Transaction nat_transaction, String photos, Date date, double superficie, String descriptif, double prix, Proprietaire prop,
                  String adresse, Wilaya wilaya, int nbrs_pieces, boolean meuble, int etage, boolean piscine, boolean garage, double msuperficie, boolean jardin) {
        super(prix_nego, nat_transaction, photos, date, superficie, descriptif, prix, prop, adresse, wilaya, nbrs_pieces, meuble);
        this.etage = etage;
        this.picsine = piscine;
        this.garage = garage;
        this.superficie_habitable = msuperficie;
        if(superficie<superficie_habitable){
            System.out.println("erreur.. la superficie habitable dépace celle du bien");
            this.superficie_habitable=Recuperation_input.lectSupHabitable(superficie);
        }
        this.jardin = jardin;
    }
    public void affInit()
    {
        System.out.println("Ce bien est une maison ");
        super.affInit();

    }
    public void Afficher() {
        System.out.println("Ce bien est une maison qui se caractérise par ce qui suit: ");
        super.Afficher();
        System.out.println("cette maison dispose de " + etage + "etage");
        System.out.println("cette maison dispose d'une piscine? " + picsine);
        System.out.println("cette maison dispose d'un garage? " + garage);
        System.out.println("cette maison dispose d'un jardin? " + jardin);
        System.out.println("cette maiso a une superficie habitable de  " + superficie_habitable);
    }

    public double Calcul_prix(boolean wilayaEchange) {
        double prix_initial = this.prix_inititial();
        Transaction nat = getNat_transaction();
        if (nat == Transaction.vente || nat==Transaction.echange) {
            if (garage || picsine || jardin) {
                prix_initial = prix_initial + 0.005 * getPrix();
            }
            if (nat == Transaction.echange && wilayaEchange) {
                this.affInit();
                System.out.println("Veuillez entrer le numéro correspendant à la wilaya de l'echange de ce bien\n                    *** ");
                Wilaya tab_wilaya[] = Wilaya.values();
                int ind;
                for ( ind = 0; ind < tab_wilaya.length; ind++) { System.out.println((ind ) + "." + tab_wilaya[ind]); }
                Scanner sc = new Scanner(System.in);
                ind=Recuperation_input.lectInt(ind-1);
                if (tab_wilaya[ind]!=(this.getWilaya()))
                {prix_initial=prix_initial+0.0025*this.getPrix();}
            }
        } else// location
        {
            if (picsine) {
                prix_initial = prix_initial + 50000;
            }

        }

        return prix_initial;
    }
    //Setters
    public void setEtage(int etage) {
        this.etage = etage;
    }

    public void setPicsine(boolean picsine) {
        this.picsine = picsine;
    }

    public void setJardin(boolean jardin) {
        this.jardin = jardin;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public void setSuperficie_habitable(double superficie_habitable) {
        this.superficie_habitable = superficie_habitable;
    }

    public int getEtage() {
        return etage;
    }

    public double getSuperficie_habitable() {
        return superficie_habitable;
    }

    public boolean isGarage() {
        return garage;
    }

    public boolean isPicsine() {
        return picsine;
    }

    public boolean isJardin() {
        return jardin;
    }
}