package restAPI.Model;

import java.util.ArrayList;

public class Seller {

    private final String          sellerName;
    private String          id;         // can be assigned using counter or might be given
    private ArrayList<Lot>  LOTlist;    //

    public Seller (String sellerName){
        this.sellerName = sellerName;
        this.LOTlist = new ArrayList<>(); // should not be here!!
    }

    public ArrayList<Lot> getLOTlist(){ return LOTlist; }

    public void setLOTlist(ArrayList<Lot> l){ LOTlist = l; }

    public String getSellerName(){ return sellerName; }

    public void setID(String id){ this.id = id; }

    public String getID(){ return id; }

    @Override
    public String toString(){ return sellerName + " " + id; }
}
