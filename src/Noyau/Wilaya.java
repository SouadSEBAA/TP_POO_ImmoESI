package Noyau;

import java.io.Serializable;

public enum Wilaya implements Serializable {
    wilaya1(100000), wilaya2(30000), wilaya3(60000);
    private double prix;

    private Wilaya(double prix)
    {
        this.prix = prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

}
