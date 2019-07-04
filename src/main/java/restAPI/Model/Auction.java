package restAPI.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Auction {

    private String id;
    private final String auctionDay;
    private final String price;
    private final String duration;
    private Seller seller;
    private Lot    lot;
    private ArrayList<Bid> bidList;
    private Bid winner;

    @JsonCreator
    public Auction(@JsonProperty("seller") Seller seller, @JsonProperty("lot") Lot lot,@JsonProperty("auctionDay") String auctionDay,
                   @JsonProperty("price") String price, @JsonProperty("duration") String duration){
        this.auctionDay = auctionDay;
        this.price      = price;
        this.duration   = duration;
        this.seller     = seller;
        this.lot        = lot;
        this.bidList    = new ArrayList<>();
    }

    public String getPrice() { return price; }

    public String getAuctionDay() { return auctionDay; }

    public String getDuration() { return duration; }

    public Seller getSeller() { return seller; }

    public void setSeller(Seller seller) { this.seller = seller; }

    public Lot getLot() { return lot; }

    public void setLot(Lot lot) { this.lot = lot; }

    public String getID() { return id; }

    public void setID(String id) { this.id = id; }

    public ArrayList<Bid> getBidList() { return bidList; }

    public void setBidList(ArrayList<Bid> bidList) { this.bidList = bidList; }

    public Bid getWinner() { return winner; }

    public void setWinner(Bid winner) { // new Bid(winner.getBuyer(),winner.getLot(),winner.getPrice())
        this.winner = winner;
    }

    @Override
    public String toString(){ return "Auction ID: " + id + "; " + seller.toString() + "; " + lot.toString() +
            "; ActionDay: " + auctionDay + ", Price: " + price + ", Duration: " + duration; }
}

