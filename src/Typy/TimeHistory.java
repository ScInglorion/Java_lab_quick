package Typy;
public class TimeHistory <ValueType, DomainType> extends Sequence<ValueType, DomainType>{

    private ValueType sensitivity;
 
    public TimeHistory() {
        // super(); to jest niepotrzenme bo domyslnie sie wywołuje konstruktor z kotrego sie dziedziczy czy os
    }

    public TimeHistory(String device, String description, long date, int channerNr, String unit, DomainType resolution,
            ValueType[] buffer, ValueType sensitivity) {
        super(device, description, date, channerNr, unit, resolution, buffer);
        this.sensitivity = sensitivity;
    }

    @Override
    public String toString() {
        return "TimeHistory [sensitivity=" + sensitivity + "]" +
        "\n " + super.toString();
    }



}
