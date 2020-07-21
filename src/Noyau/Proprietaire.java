package Noyau;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class Proprietaire implements Serializable {
    private HashSet<Bien> liste_biens ;
    private String nom, prenom, adr_mail , adr;
    private long num_tel;

    public Proprietaire(String nom,String prenom,String mail,String adr,long num)
    {
        this.nom = nom;
        this.prenom = prenom;
        this.adr_mail = mail;
        this.adr = adr;
        this.num_tel = num;
        liste_biens = new HashSet<> ();
    }

    public void Afficher_info()
    {
        System.out.println(nom + " " + prenom + " numero de tel " + num_tel );
    }

    public void Afficher_biens()
    {    ArrayList<Bien>  l = new ArrayList<Bien>(liste_biens);
        Collections.sort(l, new BienDateComparator());
        System.out.println("Les biens de ce propriétaire sont :");
        Iterator <Bien> i = l.iterator();
        Bien b ;
        int x=0;
        while (i.hasNext())
        {   x++;
            System.out.println("\n*********bien"+x+"*********");
            b = i.next();
            b.Afficher();
        }
    }
    public void ajouter_bien(Bien b)
    {
        liste_biens.add(b);
    }

    public void supprimer_bien(Bien b){liste_biens.remove(b);}

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdr_mail(String adr_mail) {
        this.adr_mail = adr_mail;
    }

    public void setListe_biens(HashSet<Bien> liste_biens) {
        this.liste_biens = liste_biens;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom(){return nom ;}

    public String getPrenom(){return prenom;}

    public long getNum_tel() {
        return num_tel;
    }

    public String getAdr_mail() {
        return adr_mail;
    }

    public String getAdr() {
        return adr;
    }

    public ArrayList<Bien> getListe_biens() {
        ArrayList<Bien>  l = new ArrayList<Bien>(liste_biens);
        return l;
    }


    public boolean equals(Object obj) {
        return ((this.nom.equals(((Proprietaire)obj).getNom())) && (this.prenom.equals(((Proprietaire)obj).getPrenom()))&& (this.num_tel==((Proprietaire)obj).getNum_tel()));
    }
    public int hashCode(){
        return  nom.hashCode();
    }
}
