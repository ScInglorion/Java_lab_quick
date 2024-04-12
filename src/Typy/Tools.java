package Typy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Tools {
public static boolean serialize(Object obj, String path) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
			out.writeObject(obj);
			out.close();
			System.out.println("Serialized data is saved in " + path);
			return true;
		} catch (IOException i) {
			i.printStackTrace();
			return false;
		}
	}
	
	/* deserialize an object from a file named 'path'
	 */
	public static Object deserialize(String path) throws ClassNotFoundException {
		Object obj = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
			obj = in.readObject();
			in.close();
			System.out.println("Serialized data is retrieved from " + path);
			return obj;
		} catch (IOException i) {
			i.printStackTrace();
			return obj;
		}
	}

	/**
	 * serializing the object passed as the argument
	 * to a byte array which is returned as the result 
	 * @param obj - object to serialize
	 * @return - byte array
	 */
	public static byte[] serialize(Object obj) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(obj);
		  out.flush();
		  return bos.toByteArray();
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		finally {
		  try {
		    bos.close();
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
	  }
	}
	
	/**
	 * deserializing the object from the byte array passed as the argument 
	 * 
	 * @param bytes - byte array that contains serialized object
	 * @return - the object on success, null on failure
	 */
	public static Object deserialize(byte[] bytes) throws ClassNotFoundException {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream in = new ObjectInputStream(bis);
			obj = in.readObject();
			in.close();
			System.out.println("Serialized data is retrieved from bytes array");
			return obj;
		} catch (IOException e) {
			e.printStackTrace();
			return obj;
		}
	}

	// APPLICATIONS of the above primitive (or better say elementary) routines
}
