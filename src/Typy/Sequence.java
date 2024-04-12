package Typy;
import java.util.Arrays;

public abstract class Sequence <ValueType, DomainType> extends Packet{ // <T> sprawia ze klasa jest szablonowa
    private int channerNr = 1;
    private String unit = "Volt";
    private DomainType resolution;
    private ValueType buffer[];
    
    public Sequence() {
    }

    public Sequence(String device, String description, long date, int channerNr, String unit, DomainType resolution,
            ValueType[] buffer) {
        super(device, description, date);
        this.channerNr = channerNr;
        this.unit = unit;
        this.resolution = resolution;
        this.buffer = buffer;
    }

    @Override
    public String toString() {
        return "Sequence [channerNr=" + channerNr + ", unit=" + unit + ", resolution=" + resolution + ", buffer="
                + Arrays.toString(buffer) + "]" + 
                "\n " + super.toString();
    }

}
