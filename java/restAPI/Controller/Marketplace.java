package restAPI.Controller;

import org.springframework.stereotype.Service;
import restAPI.Model.Auction;
import restAPI.Model.Buyer;
import restAPI.Model.Seller;

import java.util.ArrayList;
import java.util.Random;

@Service
class Marketplace {

    private final ArrayList<Seller>     sellerList;
    private final ArrayList<Buyer>      buyerList;
    private final ArrayList<Auction>    auctionList;

    public Marketplace(){
        sellerList  = new ArrayList<>();
        buyerList   = new ArrayList<>();
        auctionList = new ArrayList<>();
    }

    ArrayList<Auction> getAuctionList() { return auctionList; }

    ArrayList<Buyer> getBuyerList() { return buyerList; }

    ArrayList<Seller> getSellerList() { return sellerList; }


    void addAuction(Auction a){
        String id = createRandomID();
        while(checkAUCTION_ID(""+id)){
            id = createRandomID();
        }
        a.setId(id);
        auctionList.add(a);
    }

    void addBuyer(Buyer b){
        String id = createRandomID();
        while(checkBUYER_ID(""+id)){
            id = createRandomID();
        }
        b.setID(id);
        buyerList.add(b);
    }

    void addSeller(Seller s){
        String id = createRandomID();
        while(checkSELLER_ID(""+id)){
            id = createRandomID();
        }
        s.setID(id);
        sellerList.add(s);
    }


    Seller getSeller(String id){
        for(Seller i : sellerList){
            if(i.getID().equals(id)) {
                return i;
            }
        }
        return null;
    }

    Buyer getBuyer(String id){
        for(Buyer i : buyerList){
            if(i.getID().equals(id)) {
                return i;
            }
        }
        return null;
    }

    Auction getAuction(String id){
        for(Auction i : auctionList){
            if(i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }


    private String createRandomID(){
        Random rand = new Random();
        int tmp = rand.nextInt(1000);
        return "" + tmp;
    }

    private boolean checkSELLER_ID(String id){
        boolean ret = false;
        for(Seller i: sellerList){
            if(i.getID().equals(id)){
                ret = true;
                break;
            }
        }
        return ret;
    }

    private boolean checkBUYER_ID(String id){
        boolean ret = false;
        for(Buyer i: buyerList){
            if(i.getID().equals(id)){
                ret = true;
                break;
            }
        }
        return ret;
    }

    private boolean checkAUCTION_ID(String id){
        boolean ret = false;
        for(Auction i: auctionList){
            if(i.getId().equals(id)){
                ret = true;
                break;
            }
        }
        return ret;
    }

}
