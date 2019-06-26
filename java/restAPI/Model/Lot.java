package restAPI.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Lot {

    private int    lotID = 0;   // id, increments as new entry given
    private final String cultivar;    // species
    private final String origin;      // country of plant
    private String harvestDate; // day harvested
    private final String weight;      // total weight of plant harvested

    @JsonCreator
    public Lot (@JsonProperty("cultivar") String cultivar, @JsonProperty("origin") String origin,
                @JsonProperty("harvestDate") String harvestDate, @JsonProperty("weight") String weight){
        this.cultivar = cultivar;
        this.origin = origin;
        this.harvestDate = harvestDate;
        this.weight = weight;
    }

    public String getCultivar() { return cultivar; }

    public String getHarvestDate() { return harvestDate; }

    public String getOrigin() { return origin; }

    public String getWeight() { return weight; }

    public String get_lotID(){return String.valueOf(lotID);}

    public void set_lotID(int id){ this.lotID = id; }

    public void setHarvestDate(String newDate){ this.harvestDate = newDate; }

    @Override
    public String toString(){ return "LOT ID: " + lotID + "; "+ cultivar + " " + origin + " " + harvestDate + " " + weight; }
}
