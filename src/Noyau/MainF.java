package Noyau;

import java.util.ArrayList;
import java.util.Date;

public class MainF {
    public static void main(String[] args) {
        //creation de l'agence
        Agence a =new Agence();

        //Création de l'agent
        Agent adm=new Agent("admin","admin");

        //Ajout de l'agent à l'agence
        a.ajouter_agent(adm);

        //creation des propriétaires
        Proprietaire prop1=new Proprietaire("prop1","prenom1","xxxx@xx.dz","bougie",04713);
        Proprietaire prop2=new Proprietaire("prop2","abdellah","xxxx@xx.dz","bougie",06613);
        Proprietaire prop3=new Proprietaire("prop3","abdellah","xxxx@xx.dz","bougie",066);

        //Création des biens

        Bien bien1 = new Appartement(true, Transaction.vente, "Photos", new Date(2012, 3, 4),
                120,"Ce bien est ...", 4000000,prop2, "adr1", Wilaya.wilaya2, 4,
                1, true, false,true);
        Bien bien2 = new Maison(false, Transaction.vente, "Photos", new Date(11,10,10),
                200,"Ce bien est ...", 10000000,prop1, "adr2", Wilaya.wilaya3, 8,
                true, 2, false, false, 180, true);
        Bien bien3 = new Terrain(false, Transaction.vente, "Photos", new Date(10,2,5),
                500,"Ce bien est ...", 20000000,prop1, "adr3", Wilaya.wilaya1, "Statut",
                3);
        Bien bien4 = new Appartement(true, Transaction.location, "Photos", new Date(9,11,5),
                100,"Ce bien est ...", 40000,prop2, "adr4", Wilaya.wilaya3, 3,
                1, true, false,false);
        Bien bien5 = new Maison(false, Transaction.location, "Photos", new Date(8,1,1),
                160,"Ce bien est ...", 150000,prop3, "adr5", Wilaya.wilaya2, 5,
                true, 2, true, false, 150, false);
        Bien bien6 = new Appartement(true, Transaction.location, "Photos", new Date(5,1,4),
                50,"Ce bien est ...", 600000,prop2, "adr6", Wilaya.wilaya3, 1,
                6, true, false,false); //Sans ascenseur
        Bien bien7 = new Terrain(false, Transaction.echange, "Photos", new Date(4,1,1),
                650,"Ce bien est ...", 18000000,prop1, "adr7", Wilaya.wilaya1, "Statut",
                1);//wilaya d'echange souhaitée wilaya3
        Bien bien8 = new Maison(false, Transaction.echange, "Photos", new Date(2, 3 , 4),
                200,"Ce bien est ...", 14000000,prop2, "adr8", Wilaya.wilaya2, 8,
                true, 2, false, true, 180, false);//mm wilaya d'echange

        //Ajout des biens à l'agence
        Bien [] tab_biens = new Bien []{bien1, bien2, bien3, bien4, bien5, bien6, bien7, bien8} ;
        for (Bien bien: tab_biens)
            a.ajouter_bien(bien);

        //Choix des actions et exécution
        int choix=8;
        int choix2;
        Bien b;
        ArrayList<Bien>  l = new ArrayList<Bien>(a.getListe_biens());

        //Choix du client ou administrateur
        int entree = Recuperation_input.choix_client_administrateur();

        //Connexion de l'administrateur
        if (entree == 1)
        {
            Agent admin = Recuperation_input.lect_agent();
            entree = Recuperation_input.Connexion_admin(a, admin);
        }

        while(choix!=0 ) {
            choix = Recuperation_input.affMain(entree);
            System.out.println("\nle choix est "+choix);
            switch (choix){
                case 1:
                    System.out.println("\n********* Afficher les biens sans détails ************");
                    a.affiche_liste_biens_sans_details(a.Trier_ordre_decroissant(a.getListe_biens()));
                    break;
                case 2:
                    System.out.println("\n********* Afficher les informations d'un bien précis ************");
                    l = a.Trier_ordre_decroissant(a.getListe_biens());
                    choix2= a.afficheInit_liste_biens(l);
                    if(choix2!=0)
                        (l.get(choix2-1)).Afficher();
                    break;
                case 3:
                    System.out.println("\n********* Calculez les prix finaux pour chaque bien ************");
                    a.affiche_ts_prix_finaux(a.Trier_ordre_decroissant(a.getListe_biens()));
                    break;
                case 4:
                    System.out.println("\n********* Calculez le prix d'un bien précis ************");
                    l = a.Trier_ordre_decroissant(a.getListe_biens() );
                    choix2= a.afficheInit_liste_biens(l);
                    System.out.printf("Ce bien coute %f" , (l.get(choix2-1).Calcul_prix(true)));
                    break;
                case  5:
                    System.out.println("\n********* Recherche multi criteres ************");
                    a.affiche_liste_biens_sans_details(a.Trier_ordre_decroissant(a.Filtrer( Recuperation_input.recuperer_input_filtrer())));
                    break;
                case 6:
                    System.out.println("\n********* Afficher les biens d'un proprietaire précis ************");
                    choix2=a.afficheInit_liste_prop();
                    Proprietaire [] tab = new Proprietaire[a.getListe_proprietaires().size()];
                    a.getListe_proprietaires().toArray(tab);
                    if(choix2!=0){
                        tab[choix2-1].Afficher_info();
                        tab[choix2-1].Afficher_biens();}
                    break;
                case 7:
                    System.out.println("\n********* Envoyer un message à l'agence ************");
                   // a.envoyer_message();
                    break;
                case 8:
                    System.out.println("\n********* Ajouter un bien ************");
                    b=Recuperation_input.recuperer_input_ajouter_bien();
                    a.ajouter_bien(b);
                    break;
                case 9:
                    System.out.println("\n********* Archivez un bien ************");
                    l = new ArrayList<Bien>(a.Trier_ordre_decroissant(a.getListe_biens()));
                    choix2=a.afficheInit_liste_biens(l);
                    a.archiver_bien(l.get(choix2-1));
                    break;
                case 10:
                    System.out.println("\n********* Supprimer un bien ************");
                    l = new ArrayList<Bien>(a.Trier_ordre_decroissant(a.getListe_biens()));
                    choix2=a.afficheInit_liste_biens(l);
                    a.suppr_bien(l.get(choix2-1));
                    break;
                case 11:
                    System.out.println("\n********* Modifier un bien ************");
                    l = new ArrayList<>(a.Trier_ordre_decroissant(a.getListe_biens()));
                    b = l.get(a.afficheInit_liste_biens(l) - 1);
                    a.modifier_bien(b, Recuperation_input.recuperer_input_modifier_bien(b));
                    break;
                case 12:
                    System.out.println("\n********* Afficher liste des biens archivés ************");
                    a.afficheInit_liste_biens(a.Trier_ordre_decroissant(a.getArchive()));
                    break;
                case 13:
                    System.out.println("\n********* Afficher un message ************");
                    a.Affich_message();
            }
        }
    }
}
