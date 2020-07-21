package Noyau;

import java.util.Comparator;

public class BienDateComparator implements Comparator<Bien> {
    public int compare (Bien b1, Bien b2)
    {
        return -(b1.getDate().compareTo(b2.getDate()));
    }
}
