package restAPI.Service;

import org.springframework.stereotype.Service;
import restAPI.Model.Auction;
import restAPI.Model.Bid;

import java.util.ArrayList;

@Service
public class AuctionService {

    private int counter;

    public AuctionService(){ this.counter = 0; }

    public void addBid(Auction a, Bid b){
        counter++;
        b.setID("" + counter);
        ArrayList<Bid> list = a.getBidList();
        list.add(b);
        a.setBidList(list);
    }

    public ArrayList<Bid> getBids(Auction a){ return a.getBidList(); }
}
