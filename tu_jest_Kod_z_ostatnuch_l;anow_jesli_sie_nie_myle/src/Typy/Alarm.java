package Typy;
public class Alarm extends Packet{
    private int channerNr;
    private double threshold;
    public enum eScale {BOTH, DOWN, UP};
    private eScale direction;
    
    public Alarm() {
    }
    eScale imba = eScale.DOWN;
    public Alarm(String device, String description, long date, int channerNr, double threshold,
            Alarm.eScale direction) {
        super(device, description, date);
        this.channerNr = channerNr;
        this.threshold = threshold;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Alarm [channerNr=" + channerNr + ", threshold=" + threshold + ", direction=" + direction + "]" + super.toString();
    }

    public void process(){     
         // tu domyslna akcja zapisu w pliku dyskowym  
         Tools.serialize(this, "Alarm.ala");  
    }
    
}
