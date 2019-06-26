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

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public ArrayList<Bid> getBidList() { return bidList; }

    public void setBidList(ArrayList<Bid> bidList) { this.bidList = bidList; }

    @Override
    public String toString(){ return "Auction ID: " + id +seller.toString() + " " + lot.toString() +
            " ActionDay: " + auctionDay + " Price: " + price + " Duration: " + duration; }
}

