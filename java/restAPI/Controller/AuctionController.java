package restAPI.Controller;

import org.springframework.stereotype.Service;
import restAPI.Model.Auction;
import restAPI.Model.Bid;

import java.util.ArrayList;

@Service
class AuctionController {

    private int counter;

    public AuctionController(){this.counter = 0;}

    void addBid(Auction a, Bid b){
        counter++;
        b.setID("" + counter);
        ArrayList<Bid> list = a.getBidList();
        list.add(b);
        a.setBidList(list);
    }

    ArrayList<Bid> getBids(Auction a){
        return a.getBidList();

    }
}
