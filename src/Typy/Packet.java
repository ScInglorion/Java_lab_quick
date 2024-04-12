package Typy;
import java.io.Serializable;

public abstract class Packet implements Serializable {
    private String device = "device";
    private String description =  "description";
    private long date  = 0L;
        
    public Packet() {
        System.out.println("\nI'm packet");
    }


    public Packet(String device, String description, long date) {
        this.device = device;
        this.description = description;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Packet [device=" + device + ", description=" + description + ", date=" + date + "]";
    }       
}
