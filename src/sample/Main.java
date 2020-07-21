package sample;

import Noyau.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //creation de l'agence
        Agence a =new Agence();
/*
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
        a.serialisation();*/
        a=a.Deserialisation();
        primaryStage = new Accueil(a);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }
}
