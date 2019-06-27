package restAPI.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@SuppressWarnings("ALL")
public class Seller {

    private final String    sellerName;
    private String          id;         // can be assigned using counter or might be given
    private ArrayList<Lot>  LOTlist;    //

    @JsonCreator
    public Seller (@JsonProperty("sellerName") String sellerName){
        this.sellerName = sellerName;
        this.LOTlist = new ArrayList<>();
    }

    public ArrayList<Lot> getLOTlist(){ return LOTlist; }

    public void setLOTlist(ArrayList<Lot> l){ LOTlist = l; }

    public String getSellerName(){ return sellerName; }

    public void setID(String id){ this.id = id; }

    public String getID(){ return id; }

    @Override
    public String toString(){ return "SellerName: " + sellerName + ", ID: " + id; }
}
