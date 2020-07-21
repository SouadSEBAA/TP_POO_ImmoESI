package Noyau;

import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;

public class Terrain extends Bien implements Serializable
{
    private String statu;
    private int façade;
    public  Terrain(boolean prix_nego, Transaction nat_transaction, String photos, Date date, double superficie, String descriptif, double prix, Proprietaire prop,
                    String adresse, Wilaya wilaya, String statu, int façade)
    {
        super(prix_nego,nat_transaction,photos,date,superficie,descriptif,prix,prop,adresse,wilaya);
        this.façade=façade;
        this.statu=statu;
    }
    public void affInit()
    {
        System.out.println("Ce bien est un terrain ");
        super.affInit();

    }

    public  void Afficher()
    {
        System.out.println("Ce bien est un terrain caracterisé par ce qui suit :");
        super.Afficher();
        System.out.println("Ce terrain est caracterisé par ce status : "+statu);
        System.out.print("nombre de façades : "+façade);
    }
    public double Calcul_prix(boolean wilayaEchange)
    {
        double prix_initial=this.prix_inititial();
        Transaction nat=this.getNat_transaction();
        if(nat==Transaction.vente || nat==Transaction.echange)
        {
            if (façade>1)
            {prix_initial=prix_initial+0.005*façade*getPrix();}
        }
        if (nat==Transaction.echange && wilayaEchange)
        {
            System.out.println("Veuillez entrer le numéro correspendant à la wilaya de l'echange de ce bien\n                          *** ");
            this.affInit();
            Wilaya tab_wilaya[] = Wilaya.values();
            int ind;
            for ( ind = 0; ind < tab_wilaya.length; ind++) { System.out.println((ind) + "." + tab_wilaya[ind]); }
            Scanner sc = new Scanner(System.in);
            ind=Recuperation_input.lectInt(ind-1);
            if (tab_wilaya[ind]!=(this.getWilaya()))
            {prix_initial=prix_initial+0.0025*this.getPrix();}
        }
        return prix_initial;
    }

    //Setters

    public void setFaçade(int façade) {
        this.façade = façade;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getStatu() {
        return statu;
    }

    public int getFaçade() {
        return façade;
    }
}
