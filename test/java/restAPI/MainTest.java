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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void init () throws Exception {
        this.mockMvc.perform(get("/init")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addingSeller () throws Exception {
        Object randomObj = new Object() {
            public final String sellerName = "Test Seller";
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/seller")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void addingBuyer () throws Exception {
        Object randomObj = new Object() {
            public final String buyerName = "Test Buyer";
        };

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(randomObj);

        this.mockMvc.perform(post("/buyer")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json))
                .andDo(print()).andExpect(status().isOk());
    }

    /*    @Test
        public void addingAuction () throws Exception {
            Object randomObj = new Object() {
                public final String sellerName  = "Test Auction";
                public final String auctionDay  = "10 10 1010";
                public final String price       = "5.5";
                public final String duration    = "2";
            };

            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(randomObj);

            this.mockMvc.perform(post("/auction/600/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(json))
                    .andDo(print()).andExpect(status().isOk());
        }
*/
}