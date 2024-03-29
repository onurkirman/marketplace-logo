package restAPI.Service;

import org.springframework.stereotype.Service;
import restAPI.Model.Auction;
import restAPI.Model.Buyer;
import restAPI.Model.Seller;

import java.util.ArrayList;
import java.util.Random;

@Service
public class MarketplaceService {

    private final ArrayList<Seller>     sellerList;
    private final ArrayList<Buyer>      buyerList;
    private final ArrayList<Auction>    auctionList;

    public MarketplaceService(){
        sellerList  = new ArrayList<>();
        buyerList   = new ArrayList<>();
        auctionList = new ArrayList<>();
    }

    public ArrayList<Auction> getAuctionList() { return auctionList; }

    public ArrayList<Buyer> getBuyerList() { return buyerList; }

    public ArrayList<Seller> getSellerList() { return sellerList; }


    public void addAuction(Auction a){ addGeneric("auction",   a); }

    public void addBuyer(Buyer b){     addGeneric("buyer",     b); }

    public void addSeller(Seller s){   addGeneric("seller",    s); }

    private <T> void addGeneric(String type, T obj){
        String id = createRandomID();
        switch (type){
            case "buyer":
                while(checkBUYER_ID(id)){ id = createRandomID(); }
                ((Buyer)obj).setID(id);
                buyerList.add((Buyer)obj);
                break;
            case "seller":
                while(checkSELLER_ID(id)){ id = createRandomID(); }
                ((Seller)obj).setID(id);
                sellerList.add((Seller)obj);
                break;
            case "auction":
                while(checkAUCTION_ID(id)){ id = createRandomID(); }
                ((Auction)obj).setID(id);
                auctionList.add((Auction)obj);
                break;
        }
    }


    public Seller getSeller(String id){
        for(Seller i : sellerList){
            if(i.getID().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public Buyer getBuyer(String id){
        for(Buyer i : buyerList){
            if(i.getID().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public Auction getAuction(String id){
        for(Auction i : auctionList){
            if(i.getID().equals(id)) {
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
        return checkGeneric("seller",id);
    }

    private boolean checkBUYER_ID(String id){
        return checkGeneric("buyer", id);
    }

    private boolean checkAUCTION_ID(String id){
        return checkGeneric("auction", id);
    }

    private boolean checkGeneric(String type,String id){
        boolean ret = false;
        ArrayList<?> list = new ArrayList<>();

        switch (type){
            case "seller":
                list = sellerList;
                break;
            case "buyer":
                list = buyerList;
                break;
            case "auction":
                list = auctionList;
                break;
        }

        for(Object i : list){
            if(i instanceof Seller){
                if(((Seller)i).getID().equals(id)){
                    ret = true;
                    break;
                }
            }
            else if(i instanceof Buyer){
                if(((Buyer)i).getID().equals(id)){
                    ret = true;
                    break;
                }
            }
            else if(i instanceof Auction){
                if(((Auction)i).getID().equals(id)){
                    ret = true;
                    break;
                }
            }
        }
        return ret;
    }

    // Used for Test Cases
    public void clearAll(){
        auctionList.clear();
        sellerList.clear();
        buyerList.clear();
    }

}
