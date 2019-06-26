package restAPI.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bid {

    private String  id;
    private Buyer   buyer;
    private Lot     lot;
    private final double  price; // TL/kg

    @JsonCreator
    public Bid(@JsonProperty("buyer") Buyer buyer, @JsonProperty("lot") Lot lot, @JsonProperty("price") Double price){
        this.buyer  = buyer;
        this.lot    = lot;
        this.price  = price;
    }

    public Lot getLot() { return lot; }

    public void setLot(Lot lot) { this.lot = lot; }

    public double getPrice() { return price; }

    public Buyer getBuyer() { return buyer; }

    public void setBuyer(Buyer buyer) { this.buyer = buyer; }

    public String getID() { return id; }

    public void setID(String id) { this.id = id; }

    @Override
    public String toString(){ return "Bid ID: " + id + " " + buyer.toString() + " " + lot.toString() + " Price: " + price; }
}
