package restAPI.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Buyer {

    private final String buyerName;
    private String id;

    @JsonCreator
    public Buyer(@JsonProperty("buyerName") String buyerName){
        this.buyerName = buyerName;
    }

    public String getBuyerName(){return buyerName;}

    public void setID(String id){ this.id = id;}

    public String getID(){ return id; }

    @Override
    public String toString(){ return "BuyerName: " + buyerName + ", ID: " + id; }
}
