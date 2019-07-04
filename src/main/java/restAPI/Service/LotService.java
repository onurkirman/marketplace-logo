package restAPI.Service;

import org.springframework.stereotype.Service;
import restAPI.Model.Lot;
import restAPI.Model.Seller;

import java.util.ArrayList;

@SuppressWarnings("ALL")
@Service
public class LotService {

    private int counter;

    LotService(){ this.counter = 0; }

    public void addLOT(Seller s, Lot l){
        counter++;
        l.set_lotID(counter);
        ArrayList<Lot> list = s.getLOTlist();
        list.add(l);
        s.setLOTlist(list);
    }

    public boolean removeLOT(Seller s, String lotID){
        ArrayList<Lot> list = s.getLOTlist();
        boolean isFound = list.removeIf(i -> i.get_lotID().equals(lotID));
        s.setLOTlist(list);
        return isFound;
    }

    @SuppressWarnings("SpellCheckingInspection")
    public void changeDATE(Seller s, String lotID, String newDate){
        ArrayList<Lot> list = s.getLOTlist();
        list.stream().filter(l -> l.get_lotID().equals(lotID)).forEach(l -> l.setHarvestDate(newDate));
        s.setLOTlist(list);
    }

    public Lot getLOT(Seller s, String lotID){
        ArrayList<Lot> list = s.getLOTlist();
        return list.stream().filter(i -> i.get_lotID().equals(lotID)).findFirst().orElse(null);
    }
}
