package restAPI.Controller;

import org.springframework.stereotype.Service;
import restAPI.Model.Lot;
import restAPI.Model.Seller;

import java.util.ArrayList;

@SuppressWarnings("ALL")
@Service
class LotController {

    private int counter;

    LotController(){ this.counter = 0; }

    void addLOT(Seller s, Lot l){
        counter++;
        l.set_lotID(counter);
        ArrayList<Lot> list = s.getLOTlist();
        list.add(l);
        s.setLOTlist(list);
    }

    void removeLOT(Seller s, String lotID){
        ArrayList<Lot> list = s.getLOTlist();
        list.removeIf(i -> i.get_lotID().equals(lotID));
        s.setLOTlist(list);
    }
/*
    String showLOTS(Seller s){
        String str = "";
        ArrayList<Lot> list = s.getLOTlist();
        if(list.size() == 0){ return null; } // stop if there is no LOT assigned
        str += "****LOTS****\n";
        for(Lot l:list){
            str += l.toString() + "\n";
        }
        return str;
    }
*/
@SuppressWarnings("SpellCheckingInspection")
void changeDATE(Seller s, String lotID, String newDate){
        ArrayList<Lot> list = s.getLOTlist();
        for(Lot l : list){
            if(l.get_lotID().equals(lotID)){
                l.setHarvestDate(newDate);
            }
        }
        s.setLOTlist(list);
    }

    Lot getLOT(Seller s, String lotID){
        ArrayList<Lot> list = s.getLOTlist();
        for (Lot i : list) {
            if (i.get_lotID().equals(lotID)) return i;
        }
        return null;
    }
}
