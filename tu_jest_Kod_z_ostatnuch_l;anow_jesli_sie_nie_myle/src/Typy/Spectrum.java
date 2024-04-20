package Typy;
public class Spectrum <ValueType, DomainType> extends Sequence<ValueType, DomainType> {

    public enum eScale {LIN,LOG;}

    private eScale scale;

    public Spectrum() {
    }

    public Spectrum(String device, String description, long date, int channerNr, String unit, DomainType resolution,
            ValueType[] buffer, Spectrum.eScale scale) {
        super(device, description, date, channerNr, unit, resolution, buffer);
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "Spectrum [scale=" + scale + "]" + 
        "\n " + super.toString();
    }

    
    public void process(){ 
        // tu domyslna akcja zapisu w pliku dyskowym  
        Tools.serialize(this, "Spectrum.spe");  
    }

    
}
