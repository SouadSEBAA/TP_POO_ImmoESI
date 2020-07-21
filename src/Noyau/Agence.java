package Noyau;

import java.io.*;
import java.util.*;

public class Agence implements Serializable {

    //Attributs
    private Set<Bien> liste_biens;
    private Set<Bien> archive;
    private HashSet<Proprietaire> liste_proprietaires;
    private HashMap<Bien,HashMap<String,ArrayList<String>>> messages=new HashMap<Bien, HashMap<String,ArrayList<String>>>();
    private List<Agent> agents=new ArrayList<Agent>();

    public void Ajouter_prop(Proprietaire p){
        liste_proprietaires.add(p);
    }
    public void setMessages(HashMap<Bien, HashMap<String, ArrayList<String>>> messages) {
        this.messages = messages;
    }

    //Méthodes
    public Agence() {
        liste_biens = new HashSet<>();
        archive = new HashSet<Bien>();
        liste_proprietaires = new HashSet<>();
    }
    //********************************************************************************************************************
    public void ajouter_agent(Agent a)
    {
        agents.add(a);
    }
    //********************************************************************************************************************
    public boolean authentifier(Agent a)
    {
        return agents.contains(a);
    }
    //********************************************************************************************************************
    public void suppr_bien(Bien b) {
        liste_biens.remove(b);
        Proprietaire p;
        Iterator<Proprietaire> i = liste_proprietaires.iterator();
        while (i.hasNext()) {

            p= i.next();
            if (b.getProp().equals(p))
                p.supprimer_bien(b);
        }
    }
    //********************************************************************************************************************
    public void archiver_bien(Bien b) {
        archive.add(b);
        suppr_bien(b);
    }
    //********************************************************************************************************************
    public ArrayList<Bien> Trier_ordre_decroissant(Collection<Bien> c) {
        ArrayList<Bien> l = new ArrayList (c);
        Collections.sort(l, new BienDateComparator());
        return l;
    }
    //********************************************************************************************************************
    public void Affich_message()
    {
        for (Map.Entry<Bien,HashMap<String,ArrayList<String>>> entry1 : messages.entrySet())
        {
            System.out.println("*****************************************");
            entry1.getKey().affInit();
            for (Map.Entry<String,ArrayList<String>> entry2 : entry1.getValue().entrySet())
            {
                System.out.println("______________________________________");
                System.out.println("Le mail du visiteur :"+entry2.getKey()+"\nSes messages:");
                Iterator<String>i=entry2.getValue().iterator();
                while (i.hasNext()) {
                    System.out.println(i.next());
                    System.out.println("______________________________________");
                }
            }

        }
    }
    //************************************************************************************
    public void ajouter_bien(Bien b) {
        liste_biens.add(b);
        Proprietaire p;
        boolean stop=false;
        b.getProp().ajouter_bien(b);
        Iterator<Proprietaire> i = liste_proprietaires.iterator();
        while (i.hasNext()) {
            p= i.next();
            if (b.getProp().equals(p)) {
                p.ajouter_bien(b);
                stop = true;
            }
        }
        if(stop==false) {
            liste_proprietaires.add(b.getProp());
        }
    }
    //********************************************************************************************************************/
    public void modifier_bien(Bien b, HashMap<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            b.modifier_attribut( entry.getKey(), entry.getValue());
        }
    }
    //********************************************************************************************************************/
    private ArrayList<Bien> Recherche_critere(ArrayList l, Criteres c, HashSet<Object> parametres) {
        Iterator<Bien> i = l.iterator();
        ArrayList<Bien> liste = new ArrayList<Bien>();
        Iterator<Object> ip= parametres.iterator();
        int n = 1, ind; Bien b;Object o;

        if (parametres.isEmpty())
            return l;

        switch (c) {
            case Transaction: //Si c == Transaction
                while (i.hasNext())
                {
                    b = i.next();
                    if (parametres.contains(b.getNat_transaction()))
                        liste.add(b);
                }break;
            case Type_du_bien: //Si c == Type_du_bien
                while (i.hasNext())
                {
                    b = i.next();
                    if (parametres.contains(b.getClass().getSimpleName()))
                        liste.add(b);
                }break;
            case Wilaya: //Si c == Wilaya
                while (i.hasNext()) {
                    b = i.next();
                    if (parametres.contains(b.getWilaya()))
                        liste.add(b);
                }break;
            case Prix_max: //Si c == prix_max
                o=ip.next();
                while (i.hasNext())
                {
                    b = i.next();
                    if (b.Calcul_prix(false) <=((double)o))
                        liste.add(b);
                }break;
            case Prix_min://Si c == prix_min
                o=ip.next();
                while (i.hasNext()) {
                    b = i.next();
                    if (b.Calcul_prix(false) >= ((double)o))
                        liste.add(b);
                }break;
            case Superficie_min://Si c == superficie_min
                o=ip.next();
                while (i.hasNext()) {
                    b = i.next();
                    if (b.getSuperficie() >= ((double) o))
                        liste.add(b);
                }break;
            case Superficie_max: //Si c == superficie_max
                o=ip.next();
                while (i.hasNext()) {
                    b = i.next();
                    if (b.getSuperficie() <=((double)o) )
                        liste.add(b);
                }break;
            case nb_min_pieces:  //Si c == nb_pieces_min
                o=ip.next();
                while (i.hasNext()) {
                    b = i.next();
                    if (b instanceof  Habitable) {
                        if ( ((Habitable) b).getNbrs_pieces() >= ((Integer)o))
                            liste.add(b);
                    }
                }break;
        }
        return liste;
    }
    //********************************************************************************************************************/
    public ArrayList<Bien> Filtrer(HashMap<Criteres, HashSet<Object>> map) {

        ArrayList<Bien> resultat = new ArrayList<Bien>();//liste de Bien qui contiendra le résulatat
        resultat.addAll(liste_biens);

        for (Map.Entry<Criteres, HashSet<Object>> mp: map.entrySet()) {
            resultat = Recherche_critere(resultat, mp.getKey(), mp.getValue());
        }
        return resultat;
    }

    //Affichage
    //********************************************************************************************************************
    public void affiche_liste_biens(ArrayList<Bien> l) {
        Iterator<Bien> i = l.iterator();
        Bien b;
        System.out.println("\nListe des biens qu'offre cette Agence :");
        while (i.hasNext()) {
            b = i.next();
            System.out.println("\n ********** Bien " + (l.indexOf(b) + 1) + " ************");
            b.Afficher();
        }
        if (l.size() == 0) System.out.println("La liste est vide.");
    }
    //********************************************************************************************************************
    public void affiche_liste_biens_sans_details(Collection <Bien> l) {
        int cpt = 1;
        System.out.println("\nListe des biens qu'offre cette Agence :");
        for (Bien b : l) {
            System.out.println("\n ********** Bien " + cpt + " ************");
            b.affInit();
            cpt++;
        }
        if (l.size() == 0) System.out.println("La liste est vide.");
    }
    //*********************************************************************************************************************
    public int afficheInit_liste_biens(ArrayList<Bien> l) {
        Iterator<Bien> i = l.iterator();
        Bien b;
        int x=0;
        System.out.println("\nListe des biens qu'offre cette Agence :");
        while (i.hasNext()) {
            b = i.next();
            System.out.println("\n ********** Bien " + (l.indexOf(b) + 1) + " ************");
            b.affInit();
        }
        System.out.print("\nChoisissez le numero du bien que vous voulez selectionner: ");
        if (l.size() == 0) System.out.println("La liste est vide.");
        else
            try {
                Scanner c = new Scanner(System.in);
                x = Recuperation_input.lectInt(l.size());
            }
            catch (InputMismatchException e){
                x=0;
            }
        if(x>l.size()+1)
            x=0;

        return  x;
    }
    //********************************************************************************************************************
    public int afficheInit_liste_prop()
    {
        Iterator<Proprietaire> i = liste_proprietaires.iterator();
        Proprietaire b; int cpt = 1;
        int x=0;
        System.out.println("\nListe des propriétaires inscrits dans l'agence :");
        System.out.println("Choisissez le numero du propriétaire que vous voulez selectionner.");
        while (i.hasNext()) {
            b = i.next();
            System.out.println("\n ********** Propriétaire " + cpt + " ************");
            b.Afficher_info(); cpt++;
        }
        if (liste_proprietaires.size() == 0) System.out.println("La liste est vide.");
        else

                x = Recuperation_input.lectInt(liste_proprietaires.size());
        return  x;
    }
    //********************************************************************************************************************
    public void affiche_ts_prix_finaux(Collection<Bien> l) {
        int cpt = 1;
        for (Bien b: l)
        {
            System.out.println("\n ********** Bien " + cpt + " ************");
            b.affInit();
            System.out.printf("\n-> Le prix final de ce bien : %f" , b.Calcul_prix(true));
            cpt++;
        }
    }
    //********************************************************************************************************************
    public void envoyer_message(Bien bien,String mail,String mes ){

        //**********
        HashMap<String,ArrayList<String>> h = messages.get(bien);
        ArrayList<String> listmes=null;
        //***********************

        //**********************
        if(h!=null)
        {
            listmes=h.get(mail);
            messages.get(bien).remove(mail);}
        if(messages.containsKey(bien)){ messages.remove(bien); }
        if (h == null)
            h = new HashMap<>();


        if(listmes==null){listmes=new ArrayList<>();}
        //******************************
        listmes.add(mes);
        h.put(mail,listmes);
        messages.put(bien,h);
        System.out.println("**************************");
    }
    //Getters
    public Set<Bien> getListe_biens() { return liste_biens; }
    public Set<Bien> getArchive() { return archive; }
    public Set<Proprietaire> getListe_proprietaires() { return liste_proprietaires; }

    public HashMap<Bien, HashMap<String, ArrayList<String>>> getMessages() {
        return messages;
    }
//*************************
    //la serialisation des biens
public void serialisation(){
    ObjectOutputStream out=null;

    try {
        out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Agence.txt"))));
        out.writeObject(this);
        out.close();
    }catch (Exception e){e.printStackTrace();}

}

    public Agence Deserialisation(){
        try {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Agence.txt"));
            Agence a=(Agence)in.readObject();
            in.close();
            return a;
        }catch (Exception e){e.printStackTrace();}
        return null;
    }
}
