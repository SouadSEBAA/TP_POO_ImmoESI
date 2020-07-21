package Noyau;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public abstract class Bien implements Comparable , Serializable {

    //Les attributs
    private boolean prix_nego;
    private Transaction nat_transaction;
    private String photos;
    private Date date;
    private double superficie;
    private String descriptif;
    private double prix;
    private Proprietaire prop;
    private String adresse;
    private Wilaya wilaya;
    //*********************************************
    // les methodes
    public Bien(boolean prix_nego, Transaction nat_transaction, String photos, Date date, double superficie, String descriptif,
                double prix, Proprietaire prop, String adresse, Wilaya wilaya)
    {
        this.prix_nego=prix_nego;
        this.nat_transaction=nat_transaction;
        this.photos=photos;
        this.date=date;
        this.superficie =superficie;
        this.descriptif=descriptif;
        this.prix=prix;
        this.prop=prop;
        this.adresse=adresse;
        this.wilaya=wilaya;
    }
    public void affInit()
    {
        System.out.println("la nature de transaction  "+nat_transaction);
        System.out.println("l'adresse "+adresse);
        System.out.println("la wilaya  "+wilaya);
        System.out.println("la date d'ajout :" + date.toGMTString() );
    }
    public void Afficher()
    {   System.out.println("la nature de transaction  "+nat_transaction);
        System.out.println("la descriptif "+descriptif);
        System.out.println("la superficie "+superficie);
        System.out.printf("le prix initial  %f\n", prix);
        System.out.println("le nom du proprietaire ");
        prop.Afficher_info();
        System.out.println("l'adresse "+adresse);
        System.out.println("la wilaya  "+wilaya);
        System.out.println("la date d'ajout :" + date.toGMTString() );
    }
    public abstract double Calcul_prix(boolean wilayaEchange);
    //***********************************************************************************
    public Transaction getNat_transaction()
    {return nat_transaction;}
    //***********************************************************************************
    public  double getSuperficie()
    {return superficie;}
    //************************************************************************************
    public Wilaya getWilaya()
    {return wilaya;}
    //************************************************************************************
    public double getPrix()
    {return prix;}
    //************************************************************************************
    public Date getDate()
    { return date; }
    //************************************************************************************
    public Proprietaire getProp() {
        return prop;
    }
    //***********************************************************************************
    public String getAdresse() {
        return adresse;
    }

    public String getDescriptif() {
        return descriptif;
    }

    public String getPhotos() {
        return photos;
    }

    //Les Setters
    public void setDate(Date date) { this.date = date; }

    public void setSuperficie(double n) { this.superficie = n; }

    public  void setNat_transaction(Transaction t) { this.nat_transaction = t; }

    public void setAdresse(String adresse) { this.adresse = adresse; }

    public void setPrix(double prix) { this.prix = prix; }

    public void setPrix_nego(boolean prix_nego) { this.prix_nego = prix_nego; }

    public void setDescriptif(String descriptif) { this.descriptif = descriptif; }

    public void setWilaya(Wilaya wilaya) { this.wilaya = wilaya; }

    public void setProp(Proprietaire prop) {
        this.prop = prop;
    }

    public boolean isPrix_nego() {
        return prix_nego;
    }

    //************************************************************************************
    public double prix_inititial()
    {
        if(nat_transaction==Transaction.vente || nat_transaction==Transaction.echange)
        {    if(prix<5000000)
        {
            if(wilaya.getPrix() < 50000)
            {return (prix+0.03*prix);}
            else
            {return(prix+0.035*prix);}
        }
        else
        {
            if (prix<15000000)
            {
                if (wilaya.getPrix()<50000)
                {return(prix+prix*0.02);}
                else{return (prix+prix*0.025);}
            }
            else
            {
                if (wilaya.getPrix()<70000)
                {return(prix+prix*0.01);}
                else{return(prix+prix*0.02);}
            }

        }
        }
        else
        {
            if (nat_transaction==Transaction.location)
            {
                if(superficie<60)
                {
                    if(wilaya.getPrix()<50000)
                    {return (prix+0.01*prix);}
                    else
                    {return(prix+0.015*prix);}
                }
                else
                {
                    if (superficie<150)
                    {
                        if(wilaya.getPrix()<50000)
                        {return (prix+0.02*prix);}
                        else
                        {return(prix+0.025*prix);}
                    }
                    else
                    {
                        if(wilaya.getPrix()<50000)
                        {return (prix+0.03*prix);}
                        else
                        {return(prix+0.035*prix);}
                    }
                }
            }
        }
        return 0;}

    //********************************************************************************************************************
    public ArrayList<Field> recuperer_attributs ()
    {
        ArrayList<Field> f = new ArrayList<Field>();
        Class c = this.getClass();
        while (c != null)
        {
            f.addAll(Arrays.asList(c.getDeclaredFields()));
            c = c.getSuperclass();
        }
        return f;
    }

    //********************************************************************************************************************
    public void modifier_attribut(String attribut, Object x)
    {
        if (recuperer_attributs().toString().contains(attribut)) {

            switch (attribut){
                default:
                    //tableau de methodes
                    Method tab_methods[] = this.getClass().getMethods();

                    //Chercher le setter correspondant
                    int ind = 0;
                    while (! Utilitaire.isSetter(tab_methods[ind], attribut) && ind < tab_methods.length) {
                        ind++;
                    }
                    //Si on trouve le setter correspondant
                    if (ind < tab_methods.length)
                    {
                        //invoquer le setter correspondant et l'exécuter avec l'info x
                        try {    tab_methods[ind].invoke(this, x); }
                        catch (Exception e) {   System.out.println("\nMethode " +  tab_methods[ind].getName()+ " non exécutée, l'attribut " + attribut + " non modifié."); }
                    }
            }
        }
    }
    public int compareTo(Object o)
    {/*
        if (this.equals(o)) return 0;//Si le mm bien
        else
        {//Si c pas le mm bien
            BienDateComparator compare = new BienDateComparator();
            if (compare.compare(this, (Bien) o) == 0) return 1;//Si c la mm date
            else {
                return compare.compare(this, (Bien) o);//Sinon
            }
        }
*/
        return this.date.compareTo(((Bien)o).getDate());

    }

    public boolean equals(Object o)
    {
        return this.adresse.equals(((Bien)o).getAdresse()) && this.wilaya.equals(((Bien) o).wilaya);
    }
    public  int hashCode(){
        return ((adresse)+wilaya.name()).hashCode();
    }

    //Pour la serialisation
    private void readObject(final ObjectInputStream ois)throws IOException,ClassNotFoundException{
        this.adresse=(String) ois.readObject();
        this.wilaya=(Wilaya) ois.readObject();
        ois.defaultReadObject();
    }
    private void writeObject(final ObjectOutputStream oos)throws IOException,ClassNotFoundException{
        oos.writeObject(this.adresse);
        oos.writeObject(this.wilaya);
        oos.defaultWriteObject();
    }

}
