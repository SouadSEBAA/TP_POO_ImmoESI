package Noyau;

import java.io.Serializable;

public class Agent implements Serializable {
    private String nom;
    private String mdp;
    public Agent(String nom,String mdp)
    {
        this.nom=nom;
        this.mdp=mdp;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public boolean equals(Object obj) {
        return this.nom.equals(((Agent) obj).getNom()) && this.mdp.equals(((Agent) obj).getMdp());
    }
}
