package Noyau;

import java.lang.reflect.Field;
import java.util.*;

public class Recuperation_input {

    //input pour la méthode Filtrer et recherche_critere
    private static HashSet<Object> recuperer_args(Criteres c) {
        Scanner sc = new Scanner(System.in);
        int n = 1, ind; HashSet<Object> arg = new HashSet<>();
        double d;
        switch (c) {
            case Transaction: //Si c == Transaction
                System.out.println("\n********** Les Transactions **********");
                Transaction tab[] = Transaction.values();
                for (ind = 0; ind < tab.length; ind++)//Afficher les transactions
                    System.out.println(ind + 1 + ". " + tab[ind]);
                while (n!=0){
                    System.out.print("\n->Entrez le numéro de la transaction correspondante à votre recherche , 0 sinon: ");
                    n = lectInt(tab.length);
                    if (n!=0) arg.add(tab[n-1]);}
                break;

            case Type_du_bien: //Si c == Type_du_bien
                String tab_types[] = {"Maison", "Terrain", "Appartement"}; //getClasses
                System.out.println("\n*********** Types des biens *************");
                //Afficher les types des biens
                for (ind = 0; ind < tab_types.length; ind++)
                    System.out.println(ind + 1 + ". " + tab_types[ind]);
                while (n!=0){
                    System.out.print("\n->Entrez le numéro du type de bien correspondant à votre recherche: ");
                    n = lectInt(tab_types.length);
                    if (n!=0) arg.add(tab_types[n-1]);}
                break;
            case Wilaya: //Si c == Wilaya
                while (n != 0) {
                    Wilaya tab_wilaya[] = Wilaya.values();
                    System.out.println("\n************ Wilaya ***************");
                    for (ind = 0; ind < tab_wilaya.length; ind++)//Afficher les wilayas existantes
                        System.out.println((ind + 1) + ". " + tab_wilaya[ind].name());

                    System.out.print("\nEntrez le numéro de la Wilaya correspondante à votre recherche , 0 sinon: ");
                    n = lectInt(tab_wilaya.length);
                    if (n!=0) arg.add(tab_wilaya[n-1]);
                }
                break;
            case Prix_max: //Si c == prix_max
                System.out.print("\nDonnez le prix maximum: ");
                d = lectDouble(); arg.add(d);
                break;
            case Prix_min://Si c == prix_min
                System.out.print("\nDonnez le prix minimum: ");
                d = lectDouble();  arg.add(d);
                break;
            case Superficie_min://Si c == superficie_min
                System.out.print("\nDonnez la superficie minimum en m²: ");
                d= lectDouble();
                arg.add( d );
                break;
            case Superficie_max: //Si c == superficie_max
                System.out.print("\nDonnez la superficie maximum en m²: ");
                d = lectDouble();  arg.add(d);
                break;
            case nb_min_pieces:  //Si c == nb_pieces_min
                System.out.print("\nDonnez le nmbre de pieces minimum : ");
                n = lectEntier();  arg.add(n);
                break;
        }
        return arg;
    }
    //********************************************************************************************************************
    public static HashMap<Criteres, HashSet<Object>> recuperer_input_filtrer ()
    {
        Scanner sc = new Scanner(System.in);
        int n=1, choix=1;
        Criteres tab [] = Criteres.values();//mettre toutes les énumération de Critères dans un tableau
        HashMap<Criteres, HashSet<Object>> input = new HashMap<>();
        HashSet<Object> col ;

        //Affichage des critères
        System.out.println("\nVoici les critères :");
        for (int i = 0; i<tab.length; i++)
            System.out.println(i+1 + ". "+tab[i]);

        //Choix des critères
        System.out.print("\nEntrez le numéro du " + n + "eme critére sinon 0: ");
        choix = lectInt(tab.length);
        while (choix != 0)
        {
            n++;
            col = recuperer_args(tab[choix-1]);

            if (input.get(tab[choix-1]) != null)
            {
                input.get(tab[choix-1]).addAll(col);
                input.put(tab[choix-1],  input.get(tab[choix-1]));
            }
            else {input.put(tab[choix-1],  col);}

            System.out.print("\nEntrez le numéro du " + n + "eme critére sinon 0: ");
            choix = lectInt(tab.length);
        }
        return input;
    }
    //********************************************************************************************************************
    //********************************************************************************************************************
    //input pour la méthode modifier_bien
    public static HashMap<String, Object> recuperer_input_modifier_bien(Bien b)
    {
        Scanner sc = new Scanner(System.in);
        int  n = 1, choix = 1; Object x=0;
        HashMap<String, Object> map = new HashMap<>();

        //liste des attributs
        ArrayList<Field> liste_attributs = b.recuperer_attributs();

        //Afficher les attributs
        System.out.println("\nLes attributs que vous pouvez changer :");
        for (int i = 0; i < liste_attributs.size(); i++)
            System.out.println((i+1) +". " +liste_attributs.get(i).getName());

        //Choisir l'attribut à changer
        System.out.print("\nEntrez le numéro du " + n + "eme attribut à changer, sinon 0: ");
        choix = lectInt(liste_attributs.size());

        while (choix != 0){
            Field attribut =  liste_attributs.get(choix-1);
            Class c = attribut.getType();

            if (c.equals(Boolean.TYPE))
            {System.out.print("\nEst-ce-que il ya/est un(e) "+attribut.getName()+
                    " ? " +
                    "Donner 1 si c vrai, 0 sinon :");
                x = lectInt(1);
                if ((Integer) x == 1) {x = true;} else {x = false;}}

            else if (c.equals(Integer.TYPE)){
                System.out.print("\nDonner le nombre de "+attribut.getName()+" :");
                x = lectEntier();}

            else if (c.equals(Double.TYPE)) {
                System.out.print("\nDonner le(a) nouveau(elle) "+attribut.getName()+" :");
                x = lectDouble();
            }
            else if (c.equals(new String().getClass())){
                System.out.print("\nDonner la nouvelle information  :");
                x = sc.nextLine ();
            }
            else if (c.isEnum()){
                Object [] tab = c.getEnumConstants();
                for (int i = 0; i<tab.length; i++)
                    System.out.println((i+1) + ". "+tab[i]);

                System.out.println("\n->Entrer le numéro du choix correspondant :");
                x = lectInt(tab.length);
                x = tab[(Integer) x-1];
            }
            else if (c.getName().equals("Proprietaire"))
            {
                x = lectProp();
            }
            map.put(attribut.getName(), x);
            //Choisir l'attribut à changer, boucle
            n ++;
            System.out.print("\nEntrez le numéro du " + n + "eme attribut à changer, sinon 0: ");
            choix = lectInt(liste_attributs.size());
        }
        return map;}
    //********************************************************************************************************************
    //********************************************************************************************************************
    //input pour la méthode ajouter_bien
    public static Bien recuperer_input_ajouter_bien ()
    {
        Scanner sc = new Scanner(System.in);
        int ind;
        Bien b=null;

        System.out.println("*********** Ajout d'un bien ***********");
        //Le type du bien
        String tab_types [] = {"Maison", "Terrain", "Appartement"}; //getClasses
        System.out.println("*********** Types des biens *************");
        for (ind = 0; ind < tab_types.length; ind++) { System.out.println(ind  + "." + tab_types[ind]); }
        System.out.print("Entrez le numéro du type de bien correspondant à votre recherche:");
        int type = lectInt(2);
        //La nature de transaction
        System.out.println("********** Nature de Transaction **********");
        Transaction tab_trans[] = Transaction.values();
        for (ind = 0; ind < tab_trans.length; ind++) { System.out.println(ind  + "." + tab_trans[ind]); }
        System.out.print("Entrez le numéro de la transaction correspondante à votre recherche :");
        int trans = lectInt(2);
        //La superficie
        System.out.print("Entrez la superficie en m²:");
        double superficie = lectDouble();
        //L'adresse
        System.out.print("Entrez l'adresse du bien:");
        String adresse = sc.next();
        //La wilaya
        Wilaya tab_wilaya[] = Wilaya.values();
        System.out.println("************ Wilaya ***************");
        for (ind = 0; ind < tab_wilaya.length; ind++) { System.out.println((ind) + "." + tab_wilaya[ind]); }
        System.out.print("Entrez le numéro de la Wilaya correspondante au bien : ");
        ind=lectInt(ind-1);
        Wilaya wilaya = tab_wilaya[ind];
        //Le prix
        System.out.print("Entrez le prix du bien:");
        double prix = lectDouble();
        //Prix négociable ou pas
        System.out.print("Entrez 1 si le prix est négociable ,0 sinon :");
        boolean prix_nego = lectbool();
        //Le descriptif
        System.out.print("Entrez le descriptif:");
        sc.nextLine();
        String descriptif = sc.nextLine();
        //Photos
        //Proprietaire
        Proprietaire p=lectProp();
        //La date d'ajout au système
        Date date=new Date();

        switch (type){
            case 0 ://Si c'est une maison
                //Nb pieces
                System.out.print("Entrez le nombre de pieces :");
                int Nbpiece=lectEntier();
                //meuble
                System.out.print("Entrez 1 si la maison est meublé, 0 sinon :");
                boolean meuble=lectbool();
                //Nb etages
                System.out.print("Entrez le nombre d'étages :");
                int nb_etages = lectEntier();
                //Superficie Habitable
                System.out.print("Entrez la superficie habitable en m²:");
                double superficie_habitable = lectSupHabitable(superficie);
                //Piscine ou pas
                System.out.print("Entrez 1 si il y a une piscine, 0 sinon :");
                boolean piscine =lectbool() ;
                //jardin
                System.out.print("Entrez 1 si la maison a un jardin , 0 sinon :");
                boolean jardin = lectbool();
                //garage
                System.out.print("Entrez 1 si la maison a un garage , 0 sinon :");
                boolean garage = lectbool();
                b = new Maison(prix_nego, tab_trans[trans], "", date, superficie, " ", prix, p,
                        adresse, wilaya, Nbpiece , meuble, nb_etages,piscine,garage ,superficie_habitable,jardin);
                break;
            case 1 ://Si c'est un terrain
                //statu

                System.out.print("Entrez le statut du terrain:");
                String statu=sc.nextLine();
                //façade
                System.out.print("Entrez le nombre de façades :");
                int facade=lectEntier();
                b=new Terrain((boolean)prix_nego,tab_trans[trans]," ",date,superficie," ",prix,p,adresse,wilaya,statu,facade);
                break;
            case 2 ://Si c'est un appartement
                //nb etage
                System.out.print("Entrez le nombre d'etage de l'appartement :");
                int etage=lectEntier();
                //Nb pieces
                System.out.print("Entrez le nombre de pieces :");
                Nbpiece=lectEntier();
                //meuble
                System.out.print("Entrez 1 si la maison est meublé, 0 sinon :");
                meuble = lectbool();
                //Nb etages
                System.out.print("Entrez le nombre d'étages :");
                nb_etages = lectEntier();
                //ascenseur
                System.out.print("Entrez 1 si l'appartement dispose d'un ascenseur, 0 sinon :");
                boolean ascenseur=lectbool();
                b=new Appartement((boolean)prix_nego,tab_trans[trans]," ",date,superficie," ",prix,p,adresse,wilaya,nb_etages,etage,meuble,false,ascenseur);
                break;

        }
        return b;
    }
    //********************************************************************************************************************
    //********************************************************************************************************************
    //input pour la méthode main
    public static int choix_client_administrateur()
    {
        System.out.println("Soyez les bienvenus chez ImmoESI");
        System.out.println("Vous etes :\n");
        System.out.println("0.Client.");
        System.out.println("1.Agent administrateur.");
        System.out.print("Choisissez une option :\n");
        int x=lectInt(1);
        return x;
    }

    public static Agent lect_agent()
    {
        Scanner sc = new Scanner(System.in); int choix = 1;
        System.out.println("Donner le nom d'utilisateur: ");
        String nom = sc.next();
        System.out.println("Donner le mot de passe: ");
        String mdp = sc.next();
        return new Agent(nom, mdp);
    }

    public static int Connexion_admin (Agence a, Agent admin)
    {int x;
        try {
           if (! a.authentifier(admin)) throw new CnxImpossibleException();
           x=1;
        }
        catch (CnxImpossibleException e)
        {
            System.out.println("Connexion impossible, mot de passe ou nom d'utilisateur incorrect.");
            System.out.println("0. Se conneter comme client.");
            System.out.println("1. Reessayer.");
            System.out.print("Choisissez :");
             x = lectInt(1);
            if (x == 1)//rééssayer la connexion
            {
                admin = lect_agent();
                x=Connexion_admin(a, admin);
            }
            else return 0; //client
        }
        return x; //authentification sans throw d'exception
    }

    public static  void menu_agent(){
        menu_client();

        System.out.println("8.Ajouter un bien.");
        System.out.println("9.Archivez un bien.");
        System.out.println("10.Supprimer un bien.");
        System.out.println("11.Modifier un bien.");
        System.out.println("12.Afficher liste des biens archivés.");
        System.out.println("13.Afficher liste des messages.");

    }
    public static  void menu_client(){
        System.out.println("\nSoyez les bienvenus chez ImmoESI");
        System.out.println("Choisissez l'option que vous voulez executer\n");
        System.out.println("0.Quitter.");
        System.out.println("1.Afficher les biens sans détails.");
        System.out.println("2.Afficher les informations d'un bien précis.");
        System.out.println("3.Calculez les prix finaux pour chaque bien.");
        System.out.println("4.Calculez le prix d'un bien précis.");
        System.out.println("5.Filtrer les biens selon un ou plusieurs critères.");
        System.out.println("6.Afficher les biens d'un proprietaire précis.");
        System.out.println("7.Envoyer un message à l'agence.");

    }
    public static int affMain (int choix){
        int x;
        if (choix == 0) {
            menu_client();
            x=lectInt(7);
        }
        else {
                menu_agent();
                x=lectInt(13);
        }
        return  x;
    }
    //********************************************************************************************************************
    //********************************************************************************************************************
    public  static int lectInt(int max )
    {
        Scanner sc = new Scanner(System.in);
        int  x;
        try {
            x = sc.nextInt();
        }
        catch (InputMismatchException e){
            System.out.println("Erreur de saisie\nVeuillez réessayer ");
            x=lectInt(max);
        }

        if(x>max || x<0) {
            System.out.println("Erreur de saisie\nVeuillez réessayer " );
            x = lectInt(max);
        }
        return  x;
    }
    //********************************************************************************************************************
    public static Proprietaire lectProp(){
        Scanner sc = new Scanner(System.in);
        Proprietaire p;
        String nom,prenom,adr,mail;
        int num;
        System.out.println("Donnez le nom du propriétaire. ");
        nom=sc.next();
        System.out.println("Donnez le prenom du propriétaire. ");
        prenom=sc.next();
        System.out.println("Donnez l'adresse du propriétaire. ");
        sc.nextLine();
        adr=sc.nextLine();
        System.out.println("Donnez le mail du propriétaire. ");
        mail=sc.next();
        System.out.println("Donnez le numero du propriétaire. ");
        num=lectEntier();
        p=new Proprietaire(nom,prenom,mail,adr,num);
        return p;
    }
    //********************************************************************************************************************
    public static int lectEntier()
    {
        Scanner sc = new Scanner(System.in);
        int  x;
        try {
            x = sc.nextInt(); if (x < 0) throw new Exception();
        }
        catch (Exception e){
            x=0;
            System.out.println("Erreur de saisie\nVeuillez réessayer");
            x=lectEntier();
        }
        return  x;
    }
    //********************************************************************************************************************
    public  static Double lectDouble()
    {
        Scanner sc = new Scanner(System.in);
        Double x;
        try {
            x = sc.nextDouble(); if (x < 0) throw new Exception();
        }
        catch (Exception e){

            System.out.println("Erreur de saisie\nVeuillez réessayer ");
            x=lectDouble();
        }
        return  x;
    }
    //********************************************************************************************************************
    public static boolean lectbool()
    {
        Scanner sc = new Scanner(System.in);
        int x;
        boolean b;

        try {
            x= sc.nextInt();
            if (x != 1 && x != 0) {
                throw new Exception();
            }
            if (x == 1) {b= true;} else {b = false;}
        }
        catch (Exception e) {
            System.out.println("Erreur de saisie\nVeuillez réessayer");
            b=lectbool();
        }

        return b;
    }
    //*********************************************************************************************************************
    public static double lectSupHabitable(double suptotal)
    {
        Scanner sc = new Scanner(System.in);
        double x=0;
        try {

            x = sc.nextDouble();
            if(x > suptotal) {throw new Exception();}
        }
        catch (Exception e) {
            if( x >suptotal) System.out.println("La superficie habitable dépace celle du bien \nVeuillez réessayer");
            else  System.out.println("Erreur de saisie\nVeuillez réessayer");
            x=lectSupHabitable(suptotal);
        }
        return  x;

    }


}
