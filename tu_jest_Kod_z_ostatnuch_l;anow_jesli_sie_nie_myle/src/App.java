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
        
        // lowkey uselss bo jes tfunkcja w tools
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

        Short przebieg_sily[] = {1,2,3,4};

        TimeHistory<Short, Float> ss = new TimeHistory<>("Czujnik", "Sygnal", 1L, 1, "N", (Float)0.001F, przebieg_sily, (short)10); // shorta i flow mozna wrzucic do szablonu
        System.out.println("Timehistory check:\n" + ss.toString());
        byte bytes_tools[] = Tools.serialize(ss);
        TH_b.write(bytes_tools);
        TH_b.close();
        // System.out.println("Serialized bytes: " + Arrays.toString(bytes_tools));
        TimeHistory<Short, Float> test_tools = (TimeHistory<Short, Float>)Tools.deserialize(bytes_tools);
        System.out.println("\nFirst test:\nDeserialized data: \n"+ test_tools);
        TH.writeUTF(test_tools.toString());
        TH.close();
 

        Alarm alarmm = new Alarm("komora", "temperatura za duza", 1L, 2, 22.5, Alarm.eScale.BOTH );
        System.out.println("\nAlarm check\n" + alarmm.toString());
        byte bytes_alarm[] = Tools.serialize(alarmm);
        Alarmik_b.write(bytes_alarm);
        Alarmik_b.close();
        Alarm test_alarm = (Alarm)Tools.deserialize(bytes_alarm);
        System.out.println("\n2nd test:\nDeserialized data: \n"+ test_alarm);
        Alarmik.writeUTF(test_alarm.toString());
        Alarmik.close();

        Spectrum spec = new Spectrum("PC", "Beast", 1L, 2, "Hz", (Float)0.001F, przebieg_sily, Spectrum.eScale.LIN);
        System.out.println("\nSpectrum check\n" + spec.toString());
        byte bytes_spec[] = Tools.serialize(spec);
        Spectr_b.write(bytes_spec);
        Spectr_b.close();
        Spectrum test_spec = (Spectrum)Tools.deserialize(bytes_spec);
        System.out.println("\n3rd test:\nDeserialized data: \n"+ test_spec);
        spectr.writeUTF(test_spec.toString());
        spectr.close();


        // FileInputStream fis = new FileInputStream("THd.txt");
        // DataInputStream reader = new DataInputStream(fis);
        // String result;
        // result = reader.readUTF();
        // reader.close();
        // System.out.println("\ngo:" +result);

    }
}
