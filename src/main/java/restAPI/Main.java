package restAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }




    /*
    public static void main(String[] args) throws IOException {

        Marketplace         MP = new Marketplace();
        LotController       LC = new LotController();
        AuctionController   AC = new AuctionController();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            System.out.print(   "Please choose an option listed down below!\n" +
                                "1: ADD SELLER\n" +
                                "2: ADD LOT via Creating a new SELLER\n" +
                                "3: ADD LOT via Existing SELLER\n" +
                                "4: SHOW MARKETPLACE\n" +
                                "5: CHANGE HARVEST DATE\n" +
                                "6: REMOVE LOT\n" + "7: ADD BUYER\n" +
                                "8: ADD LOT to AUCTION\n" +
                                "9: SHOW BIDS of a LOT\n" +
                                "10: ADD BID to Existing Auction\n" +
                                "0: EXIT\n" );
            String option = br.readLine();
            switch(option){
                case "1": // ADD SELLER
                    System.out.println("Please provide a seller name");
                    MP.addSeller(new Seller(br.readLine()));
                    break;
                case "2": // ADD LOT via Creating a new SELLER
                    // #ADD SELLER
                    System.out.println("Please provide a seller name");
                    Seller seller = new Seller(br.readLine());
                    MP.addSeller(seller);

                    // #ADD LOT
                    System.out.printf("Now you can add a lot for %s seller\n", seller.getSellerName());
                    String input = br.readLine();
                    String[] arr = input.split(",");
                    Lot lot = new Lot(arr[0],arr[1],arr[2],arr[3]);
                    LC.addLOT(seller, lot);
                    break;
                case "3": // ADD LOT via Existing SELLER
                    System.out.println("Please provide a seller id");
                    seller = MP.getSeller(br.readLine());
                    if(seller == null){ System.out.println("No such seller found"); break; }
                    System.out.println("Now enter lot specs");
                    input = br.readLine();
                    arr = input.split(",");
                    LC.addLOT(seller, new Lot(arr[0],arr[1],arr[2],arr[3]));
                    break;
                case "4": // SHOW MARKETPLACE
                    MP.showSeller(LC);
                    MP.showAuction(AC);
                    MP.showBuyer();
                    break;
                case "5": // CHANGE LOT DATE
                    System.out.println("Please provide a seller id");
                    seller = MP.getSeller(br.readLine());
                    if(seller == null){ System.out.println("No such seller found"); break; }
                    System.out.println("Now enter lotID");
                    input = br.readLine();
                    System.out.println("Now enter new date");
                    String date = br.readLine();
                    LC.changeDATE(seller, input,date);
                    break;
                case "6": // REMOVE LOT; do not forget to clear bids
                    System.out.println("Please provide a seller id");
                    seller = MP.getSeller(br.readLine());
                    if(seller == null){ System.out.println("No such seller found"); break; }
                    System.out.println("Now enter lotID");
                    input = br.readLine();
                    LC.removeLOT(seller, input);
                    break;
                case "7": // ADD BUYER
                    System.out.println("Please provide a buyer name");
                    MP.addBuyer(new Buyer(br.readLine()));
                    break;
                case "8": // ADD LOT to AUCTION
                    System.out.println("Please provide a seller id");
                    seller = MP.getSeller(br.readLine());

                    if(seller == null){ System.out.println("No such seller found"); break; }
                    System.out.println("Now enter lot id");
                    input = br.readLine();
                    lot = LC.getLOT(seller,input);

                    System.out.println("Now enter AuctionDay");
                    String auctionDay = br.readLine();
                    System.out.println("Now enter price");
                    String price = br.readLine();
                    System.out.println("Now enter duration");
                    String duration = br.readLine();
                    MP.addAuction(new Auction(seller,lot,auctionDay,Double.parseDouble(price),duration));
                    break;
                case "9": // SHOW BIDS of a LOT; lotID, sellerID
                    System.out.println("Please enter Auction ID");
                    Auction auction = MP.getAuction(br.readLine());
                    AC.showBids(auction);
                    break;
                case "10": // ADD BID to Existing Auction
                    System.out.println("Please enter Buyer ID");
                    Buyer buyer = MP.getBuyer(br.readLine());

                    System.out.println("Please enter Auction ID");
                    auction = MP.getAuction(br.readLine());

                    System.out.println("Please enter Price");
                    Bid bid = new Bid(buyer,auction.getLot(),Double.parseDouble(br.readLine()));
                    AC.addBid(auction,bid);
                    break;
                case "0": // EXIT
                    System.out.println("It was a pleasure.\nBye Bye...\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unexpected value: " + option +
                                                    "please select a proper case");
            }
        }

    }*/
}
