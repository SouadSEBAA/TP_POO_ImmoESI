package listeBiens;

import Noyau.Agence;
import Noyau.Bien;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class listeBienController extends Stage {
    Agence agence;
    @FXML
    private AnchorPane entete;
    @FXML
    private SplitPane tout;

    @FXML
    private ListView<Node> list;

    @FXML
    private AnchorPane bas;

    public  void setAgence(Agence a){this.agence=a;}
/*
    public void setList(double width,double height) {
        list.resize(width,height);
        bas.resize(width,bas.getHeight());
        tout.resize(width,height);
        entete.resize(width,entete.getHeight());
    }
*/
    public void ajouter_biens(Collection<Bien> liste,boolean type)  {

             ArrayList<Bien> l = new ArrayList<>(agence.Trier_ordre_decroissant(liste));//agence.getListe_biens()
            list.setEditable(true);
            Iterator<Bien> i=l.iterator();
            Bien b;
            int ind=0;
            while (i.hasNext()) {
                System.out.println(ind);
                AffichBienController controlleur=null;
                Parent root=null;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../listeBiens/AffichBien.fxml"));
                    root = loader.load();
                    controlleur = loader.getController();
                }
                catch(Exception e){System.out.println("Erreur");e.printStackTrace();}
                b=i.next();
                if(controlleur==null){System.out.println("null controlleur");}
                controlleur.setBien(b);
                controlleur.setAgence(agence);
                if(type){controlleur.PrepareAdmin();}
                else{controlleur.PrepareClient();}
                controlleur.ajouter_info(b);
                list.getItems().add(ind,root);ind++;
            }

    }

}
