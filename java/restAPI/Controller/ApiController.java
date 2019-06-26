package restAPI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import restAPI.Model.*;

import java.util.ArrayList;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@RestController
public class ApiController {

    @Autowired
    private Marketplace         MP;
    @Autowired
    private LotController       LC;
    @Autowired
    private AuctionController   AC;

    @RequestMapping("/init")
    public void init(){
        Seller seller = new Seller("Onur");
        MP.addSeller(seller);
        Lot lot = new Lot("Muz","Turkey","12 May 2019","1800");
        LC.addLOT(seller,lot);
        Buyer buyer = new Buyer("Ali");
        MP.addBuyer(buyer);
        Auction auction = new Auction(seller,lot,"24 June 2019","3.7","1");
        MP.addAuction(auction);
        AC.addBid(auction,new Bid(buyer,lot,4.0));
    }

    // GET ALL LISTS
    @RequestMapping("/sellers")
    public ArrayList<Seller> showSellers(){
        return MP.getSellerList();
    }

    @RequestMapping("/buyers")
    public ArrayList<Buyer> showBuyers(){
        return MP.getBuyerList();
    }

    @RequestMapping("/auctions")
    public ArrayList<Auction> showAuctions(){
        return MP.getAuctionList();
    }


    // GET SPECIFIC OBJ
    @RequestMapping("/seller/{id}")
    public Seller getSeller(@PathVariable String id){
        return MP.getSeller(id);
    }

    @RequestMapping("/buyer/{id}")
    public Buyer getBuyer(@PathVariable String id){
        return MP.getBuyer(id);
    }

    @RequestMapping("/auction/{id}")
    public Auction getAuction(@PathVariable String id){
        return MP.getAuction(id);
    }

    @RequestMapping("/bids/{id}")
    public ArrayList<Bid> showBids(@PathVariable String id){ return AC.getBids(MP.getAuction(id)); }



    // ADD OBJ
    @RequestMapping(method = RequestMethod.POST, value = "/seller")
    public Seller addSeller(@RequestBody Seller seller){
        MP.addSeller(seller);
        return MP.getSeller(seller.getID());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/buyer")
    public Buyer addBuyer(@RequestBody Buyer buyer){
        MP.addBuyer(buyer);
        return MP.getBuyer(buyer.getID());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/auction/{sellerID}/{lotID}")
    public Auction addAuction(@RequestBody Auction auction, @PathVariable String sellerID, @PathVariable String lotID){
        Seller seller = MP.getSeller(sellerID);
        auction.setSeller(seller);
        auction.setLot(LC.getLOT(seller, lotID));
        MP.addAuction(auction);
        return MP.getAuction(auction.getId());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addLOT/{id}")
    public Seller addLot(@RequestBody Lot lot, @PathVariable String id){
        LC.addLOT(MP.getSeller(id), lot);
        return MP.getSeller(id);
    }
//
    @RequestMapping(method = RequestMethod.POST, value = "/addBid/{buyerID}/{auctionID}")
    public ArrayList<Bid> addBid(@RequestBody Bid bid, @PathVariable String auctionID, @PathVariable String buyerID){
        Auction auction = MP.getAuction(auctionID);
        bid.setBuyer(MP.getBuyer(buyerID));
        bid.setLot(auction.getLot());
        AC.addBid(auction, bid);
        return AC.getBids(auction);
    }

    // UPDATE OBJ
    @RequestMapping(method = RequestMethod.PUT, value = "/updateDate/{sellerID}/{lotID}/{newDate}")
    public void updateDate(@PathVariable String sellerID, @PathVariable String lotID, @PathVariable String newDate){
        Seller seller = MP.getSeller(sellerID);
        LC.changeDATE(seller,lotID,newDate);
    }


    // REMOVE OBJ
    @RequestMapping(method = RequestMethod.DELETE, value = "/seller/{sellerID}/{lotID}")
    public void update(@PathVariable String lotID, @PathVariable String sellerID){
        LC.removeLOT(MP.getSeller(sellerID),lotID);
    }



}
