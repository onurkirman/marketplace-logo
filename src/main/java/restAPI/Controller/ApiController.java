package restAPI.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import restAPI.Model.*;
import restAPI.Service.AuctionService;
import restAPI.Service.LotService;
import restAPI.Service.MarketplaceService;
import restAPI.Util.CustomErrorType;

import java.util.ArrayList;

@SuppressWarnings("ALL")
@RestController
public class ApiController {

    private static final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private MarketplaceService MP;
    @Autowired
    private LotService LC;
    @Autowired
    private AuctionService AC;

    @RequestMapping("/init")
    public void init(){
        logger.info("Initializing market");
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
    private ResponseEntity<?> showSellers(){
        return showAllGeneric("sellers");
    }

    @RequestMapping("/buyers")
    private ResponseEntity<?> showBuyers(){
        return showAllGeneric("buyers");
    }

    @RequestMapping("/auctions")
    private ResponseEntity<?> showAuctions(){
        return showAllGeneric("auctions");
    }

    private ResponseEntity<?> showAllGeneric(String str){
        logger.info("Fetching {} data", str);
        ArrayList<?> list = new ArrayList<>();
        switch (str){
            case "sellers":
                list = MP.getSellerList();
                break;
            case "buyers":
                list = MP.getBuyerList();
                break;
            case "auctions":
                list = MP.getAuctionList();
                break;
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    // GET SPECIFIC OBJ
    @RequestMapping("/seller/{id}")
    private ResponseEntity<?> getSeller(@PathVariable String id){
        return getGeneric("Seller",id);
    }

    @RequestMapping("/buyer/{id}")
    private ResponseEntity<?> getBuyer(@PathVariable String id){
        return getGeneric("Buyer", id);
    }

    @RequestMapping("/auction/{id}")
    private ResponseEntity<?> getAuction(@PathVariable String id){
        return getGeneric("Auction", id);
    }

    private <T> ResponseEntity<?> getGeneric(String str, String id){
        logger.info("Fetching {} with id {}", str, id);
        T element;
        switch (str){
            case "Seller":
                element = (T) MP.getSeller(id);
                break;
            case "Buyer":
                element = (T) MP.getBuyer(id);
                break;
            case "Auction":
                element = (T) MP.getAuction(id);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + str);
        }
        if (isNull(element)) {
            logger.error("{} with id: {} not found", str, id);
            return new ResponseEntity<>(new CustomErrorType("No such thing exists"), HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<>(element, HttpStatus.OK);
    }

    private <T> boolean isNull(T element){
        return element == null;
    }

    @RequestMapping("/showBids/{id}")
    private ResponseEntity<?> showBids(@PathVariable String id){
        logger.info("Fetching bids with auction id {}", id);
        Auction auction = MP.getAuction(id);
        if(auction == null){
            logger.error("There is no such auction with id {}", id);
            return new ResponseEntity<>(new CustomErrorType("No such auction exists"), HttpStatus.NOT_FOUND);
        }
        ArrayList<Bid> list = AC.getBids(auction);
        if(list.size() == 0){
            logger.error("There is no bid to fetch");
            return new ResponseEntity<>(new CustomErrorType("There is no such bid to show"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    // ADD OBJ
    @RequestMapping(method = RequestMethod.POST, value = "/seller")
    private ResponseEntity<?> addSeller(@RequestBody Seller seller){
        logger.info("Trying to add a seller");
        MP.addSeller(seller);
        logger.info("Successfully added the seller with id {}", seller.getID());
        return new ResponseEntity<>(MP.getSeller(seller.getID()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/buyer")
    private ResponseEntity<?> addBuyer(@RequestBody Buyer buyer){
        logger.info("Trying to add a buyer");
        MP.addBuyer(buyer);
        logger.info("Successfully added the buyer with id {}", buyer.getID());
        return new ResponseEntity<>(MP.getBuyer(buyer.getID()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/auction/{sellerID}/{lotID}")
    private ResponseEntity<?> addAuction(@RequestBody Auction auction, @PathVariable String sellerID, @PathVariable String lotID){
        logger.info("Trying to add an auction");
        Seller seller = MP.getSeller(sellerID);
        if(seller == null){ return  new ResponseEntity<>(
                                    new CustomErrorType("No such seller exists"), HttpStatus.NOT_FOUND); }
        auction.setSeller(seller);
        auction.setLot(LC.getLOT(seller, lotID));
        MP.addAuction(auction);
        logger.info("Successfully added the auction with id {}", auction.getID());
        return new ResponseEntity<>(MP.getAuction(auction.getID()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addLot/{id}")
    private ResponseEntity<?> addLot(@RequestBody Lot lot, @PathVariable String id){
        logger.info("Trying to add a lot");
        Seller seller = MP.getSeller(id);
        if(seller == null){return  new ResponseEntity<>(
                new CustomErrorType("No such seller exists"), HttpStatus.NOT_FOUND); }
        if(Double.valueOf(lot.getWeight()) < 1000){
            return new ResponseEntity<>(new CustomErrorType("The weight entered is not enough!!"),
                HttpStatus.FORBIDDEN);}
        LC.addLOT(seller, lot);
        logger.info("Successfully added the lot");
        return new ResponseEntity<>(MP.getSeller(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addBid/{buyerID}/{auctionID}")
    private ResponseEntity<?> addBid(@RequestBody Bid bid, @PathVariable String auctionID, @PathVariable String buyerID){
        logger.info("Trying to add a bid");
        Auction auction = MP.getAuction(auctionID);
        if(auction == null){return  new ResponseEntity<>(
                new CustomErrorType("No such seller exists"), HttpStatus.NOT_FOUND); }
        Buyer buyer = MP.getBuyer(buyerID);
        if(buyer == null){return  new ResponseEntity<>(
                new CustomErrorType("No such seller exists"), HttpStatus.NOT_FOUND); }
        bid.setBuyer(buyer);
        bid.setLot(auction.getLot());
        AC.addBid(auction, bid);
        ArrayList<Bid> list = AC.getBids(auction);
        logger.info("Successfully added the bid with id {} to auction: {}", bid.getID(), auction.getID());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    // UPDATE OBJ
    @RequestMapping(method = RequestMethod.PUT, value = "/updateLotDate/{sellerID}/{lotID}/{newDate}")
    private ResponseEntity<?> updateLotDate(@PathVariable String sellerID, @PathVariable String lotID, @PathVariable String newDate){
        logger.info("Updating date of Lot {} to {}",lotID,newDate);
        Seller seller = MP.getSeller(sellerID);
        if(seller == null){return  new ResponseEntity<>(
                new CustomErrorType("No such seller exists"), HttpStatus.NOT_FOUND); }
        LC.changeDATE(seller,lotID,newDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    // REMOVE OBJ
    @RequestMapping(method = RequestMethod.DELETE, value = "/seller/{sellerID}/{lotID}")
    private void deleteLot(@PathVariable String lotID, @PathVariable String sellerID){
        LC.removeLOT(MP.getSeller(sellerID),lotID);
    }
}
