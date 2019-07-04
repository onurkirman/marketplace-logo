package restAPI.Service;

import org.springframework.stereotype.Service;
import restAPI.Model.Auction;
import restAPI.Model.Bid;

import java.util.ArrayList;

@Service
public class AuctionService {

    private int counter;

    public AuctionService(){ this.counter = 0; }

    public void addBid(Auction auction, Bid bid){
        counter++;
        bid.setID("" + counter);
        ArrayList<Bid> list = auction.getBidList();
        list.add(bid);
        auction.setBidList(list);
        if(auction.getWinner() == null){ auction.setWinner(bid); }
        else if(list.size() > 1 && ( bid.getPrice() > auction.getWinner().getPrice())){ auction.setWinner(bid); }
    }

    public ArrayList<Bid> getBids(Auction a){ return a.getBidList(); }
}
