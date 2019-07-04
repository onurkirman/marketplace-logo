package restAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import restAPI.Model.*;
import restAPI.Service.AuctionService;
import restAPI.Service.LotService;
import restAPI.Service.MarketplaceService;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainTest {

    @Autowired
    MarketplaceService  MS;
    @Autowired
    LotService          LS;
    @Autowired
    AuctionService      AS;
    @Autowired
    private MockMvc     mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void initUsingJsonRequest () throws Exception {
        this.mockMvc.perform(get("/init")).andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void showSellers() throws Exception {
        initForTesting();
        this.mockMvc.perform(get("/sellers")).andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void showBuyers() throws Exception {
        initForTesting();
        this.mockMvc.perform(get("/buyers")).andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void showAuctions() throws Exception {
        initForTesting();
        this.mockMvc.perform(get("/auctions")).andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }

    private void initForTesting() throws Exception {
        this.mockMvc.perform(get("/init")).andDo(print());
    }

    @Test
    public void getSellerDoesExists() throws Exception {
        Seller seller = new Seller("Test Seller");
        MS.addSeller(seller);
        seller.setID("1111");
        this.mockMvc.perform(get("/seller/1111")).andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void getSellerDoesNotExists() throws Exception {
        this.mockMvc.perform(get("/seller/1111")).andDo(print()).andExpect(status().isNotFound());
        MS.clearAll();
    }

//

    @Test
    public void getBuyerDoesExists() throws Exception {
        Buyer buyer = new Buyer("Test Buyer");
        MS.addBuyer(buyer);
        buyer.setID("1111");
        this.mockMvc.perform(get("/buyer/1111")).andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void getBuyerDoesNotExists() throws Exception {
        this.mockMvc.perform(get("/buyer/1111")).andDo(print()).andExpect(status().isNotFound());
        MS.clearAll();
    }

//

    @Test
    public void getAuctionDoesExists() throws Exception {
        Seller seller = new Seller("TestSeller");
        MS.addSeller(seller);
        seller.setID("1111");
        Lot lot = new Lot("Test Banana", "Turkey", "12 June 2019", "1000");
        LS.addLOT(seller, lot);
        lot.set_lotID(1111);

        Auction auctionInserted = new Auction(seller,lot,"28 July 2019","5.5", "2");
        MS.addAuction(auctionInserted);
        auctionInserted.setID("1111");

        this.mockMvc.perform(get("/auction/1111")).andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void getAuctionDoesNotExists() throws Exception {
        this.mockMvc.perform(get("/auction/1111")).andDo(print()).andExpect(status().isNotFound());
        MS.clearAll();
    }

    @Test
    public void getBidsDoesExists() throws Exception {
        Seller seller = new Seller("TestSeller");
        MS.addSeller(seller);
        seller.setID("1111");
        Lot lot = new Lot("Test Banana", "Turkey", "12 June 2019", "1000");
        LS.addLOT(seller, lot);
        lot.set_lotID(1111);

        Auction auction = new Auction(seller,lot,"28 July 2019","5.5", "2");
        MS.addAuction(auction);
        auction.setID("1111");

        Buyer buyer = new Buyer("Test Buyer");
        MS.addBuyer(buyer);
        Bid bid = new Bid(buyer,lot,5.6);
        AS.addBid(auction, bid);

        this.mockMvc.perform(get("/showBids/1111")).andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void getBidsDoesNotExists() throws Exception {
        this.mockMvc.perform(get("/showBids/1111")).andDo(print()).andExpect(status().isNotFound());
        MS.clearAll();
    }

    @Test
    public void getNotBiddenAuction() throws Exception {
        Seller seller = new Seller("TestSeller");
        MS.addSeller(seller);
        seller.setID("1111");
        Lot lot = new Lot("Test Banana", "Turkey", "12 June 2019", "1000");
        LS.addLOT(seller, lot);
        lot.set_lotID(1111);

        Auction auction = new Auction(seller,lot,"28 July 2019","5.5", "2");
        MS.addAuction(auction);
        auction.setID("1111");

        this.mockMvc.perform(get("/showBids/1111")).andDo(print()).andExpect(status().isNotFound());
        MS.clearAll();
    }


    @Test
    public void addingSellerUsingJson () throws Exception {
        Object randomObj = new Object() {
            public final String sellerName = "Test Seller";
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/seller")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(json))
                    .andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void addingBuyerUsingJson () throws Exception {
        Object randomObj = new Object() {
            public final String buyerName = "Test Buyer";
        };

        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/buyer")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(json))
                    .andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void addingAuctionUsingJson () throws Exception {
        Object randomObj = new Object() {
            public final String auctionDay = "07 June 2019";
            public final String price = "3";
            public final String duration = "2";
        };

        Seller seller = new Seller("TestSeller");
        MS.addSeller(seller);
        seller.setID("1111");
        Lot lot = new Lot("Test Banana", "Turkey", "12 June 2019", "1000");
        LS.addLOT(seller, lot);
        lot.set_lotID(1111);

        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/auction/1111/1111")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(json))
                    .andDo(print()).andExpect(status().isOk());
        MS.clearAll();
    }


    @Test
    public void addLotWithNegativeWeight () throws Exception {
        Seller seller = new Seller("TestSeller");
        MS.addSeller(seller);
        seller.setID("1111");

        Object randomObj = new Object() {
            public final String cultivar = "Test Banana";
            public final String origin = "Turkey";
            public final String harvestDate= "12 June 2019";
            public final String weight = "-100";
        };

        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/addLot/1111")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andDo(print()).andExpect(status().isForbidden());
        MS.clearAll();
    }

    @Test
    public void addLotWithNotSufficientWeight () throws Exception {
        Seller seller = new Seller("TestSeller");
        MS.addSeller(seller);
        seller.setID("1111");

        Object randomObj = new Object() {
            public final String cultivar = "Test Banana";
            public final String origin = "Turkey";
            public final String harvestDate= "12 June 2019";
            public final String weight = "200";
        };

        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/addLot/1111")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andDo(print()).andExpect(status().isForbidden());
        MS.clearAll();
    }

    @Test
    public void addLotWithNotExistingSeller () throws Exception {
        Object randomObj = new Object() {
            public final String cultivar = "Test Banana";
            public final String origin = "Turkey";
            public final String harvestDate= "12 June 2019";
            public final String weight = "200";
        };

        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/addLot/-100")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andDo(print()).andExpect(status().isNotFound());
        MS.clearAll();
    }



    @Test
    public void updateLotDate () throws Exception {
        Seller seller = new Seller("TestSeller");
        MS.addSeller(seller);
        seller.setID("1111");
        Lot lot = new Lot("Test Banana", "Turkey", "12 June 2019", "1000");
        LS.addLOT(seller, lot);
        lot.set_lotID(1111);

        this.mockMvc.perform(put("/updateLotDate/1111/1111/04 March 2019"))
                    .andDo(print())
                    .andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void removeLotExists () throws Exception {
        Seller seller = new Seller("TestSeller");
        MS.addSeller(seller);
        seller.setID("1111");
        Lot lot = new Lot("Test Banana", "Turkey", "12 June 2019", "1000");
        LS.addLOT(seller, lot);
        lot.set_lotID(1111);

        this.mockMvc.perform(delete("/seller/1111/1111"))
                    .andDo(print())
                    .andExpect(status().isOk());
        MS.clearAll();
    }

    @Test
    public void removeLotNotExists () throws Exception {
        this.mockMvc.perform(delete("/seller/1111/1111"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        MS.clearAll();
    }


//
    @Test
    public void addingBuyerUsingObject () {
        Buyer buyerInserted = new Buyer("TestBuyer");
        MS.addBuyer(buyerInserted);
        buyerInserted.setID("1111");
        Buyer buyerGathered = MS.getBuyer("1111");
        assertEquals(buyerInserted,buyerGathered);
        MS.clearAll();
    }

    @Test
    public void addingSellerUsingObject () {
        Seller sellerInserted = new Seller("TestBuyer");
        MS.addSeller(sellerInserted);
        sellerInserted.setID("1111");
        Seller sellerGathered = MS.getSeller("1111");
        assertEquals(sellerInserted,sellerGathered);
        MS.clearAll();
    }

    @Test
    public void addingAuctionUsingObject () {
        Seller seller = new Seller("TestSeller");
        MS.addSeller(seller);
        seller.setID("1111");
        Lot lot = new Lot("Test Banana", "Turkey", "12 June 2019", "1000");
        LS.addLOT(seller, lot);
        lot.set_lotID(1111);

        Auction auctionInserted = new Auction(seller,lot,"28 July 2019","5.5", "2");
        MS.addAuction(auctionInserted);
        auctionInserted.setID("1111");
        Auction auctionGathered = MS.getAuction("1111");
        assertEquals(auctionInserted,auctionGathered);
        MS.clearAll();
    }

}
