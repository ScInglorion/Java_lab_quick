import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import Typy.Alarm;
import Typy.Spectrum;
import Typy.TimeHistory;
import Typy.Tools;








public class App {
    public static void main(String[] args) throws Exception {
        // Serialization into file
        
        
        /* do wypierdolenia i zastapienia serialize*/  

        FileOutputStream TH_b = new FileOutputStream("TH.txt");
        FileOutputStream Alarmik_b = new FileOutputStream("Alarmik.txt");
        FileOutputStream Spectr_b = new FileOutputStream("Spectr.txt");
        // Deserialization into file
        FileOutputStream TH_d = new FileOutputStream("THd.txt");
        FileOutputStream Alarmik_d = new FileOutputStream("Alarmikd.txt");
        FileOutputStream Spectr_d = new FileOutputStream("Spectrd.txt");
        DataOutputStream TH = new DataOutputStream(new BufferedOutputStream(TH_d));
        DataOutputStream Alarmik = new DataOutputStream(new BufferedOutputStream(Alarmik_d));
        DataOutputStream spectr = new DataOutputStream(new BufferedOutputStream(Spectr_d));

        /* do wypierdolenia*/  
        Short przebieg_sily[] = {1,2,3,4};

        TimeHistory<Short, Float> ss = new TimeHistory<>("Czujnik", "Sygnal", 1L, 1, "N", (Float)0.001F, przebieg_sily, (short)10); // shorta i flow mozna wrzucic do szablonu
        System.out.println("Timehistory check:\n" + ss.toString());
        byte bytes_tools[] = Tools.serialize(ss);
        // System.out.println("Serialized bytes: " + Arrays.toString(bytes_tools));
        TimeHistory<Short, Float> test_tools = (TimeHistory<Short, Float>)Tools.deserialize(bytes_tools);
        System.out.println("\nFirst test:\nDeserialized data: \n"+ test_tools);

 

        Alarm alarmm = new Alarm("komora", "temperatura za duza", 1L, 2, 22.5, Alarm.eScale.BOTH );
        System.out.println("\nAlarm check\n" + alarmm.toString());
        byte bytes_alarm[] = Tools.serialize(alarmm);

        Alarm test_alarm = (Alarm)Tools.deserialize(bytes_alarm);
        System.out.println("\n2nd test:\nDeserialized data: \n"+ test_alarm);

        Spectrum spec = new Spectrum("PC", "Beast", 1L, 2, "Hz", (Float)0.001F, przebieg_sily, Spectrum.eScale.LIN);
        System.out.println("\nSpectrum check\n" + spec.toString());
        byte bytes_spec[] = Tools.serialize(spec);

        Spectrum test_spec = (Spectrum)Tools.deserialize(bytes_spec);
        System.out.println("\n3rd test:\nDeserialized data: \n"+ test_spec);

    }
}
