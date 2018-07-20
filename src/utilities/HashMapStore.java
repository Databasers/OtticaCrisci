package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class HashMapStore<K,V> extends HashMap<K, V>{
	private static final long serialVersionUID = 1L;

	File save;
	
	public HashMapStore (String saveDir) throws FileNotFoundException, IOException, ClassNotFoundException{
		super();
		save= new File(saveDir);
		if(save.exists()) {
			load();
			System.out.println("Ripristino mappa di hash");
		}else {
			save();
			System.out.println("Creo mappa di hash");
		}
	}
	
	public void save() throws FileNotFoundException, IOException {
			ObjectOutputStream x= new ObjectOutputStream(new FileOutputStream(save));
			x.writeObject(this);
			x.close();
	}
	
	public void load() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream x= new ObjectInputStream(new FileInputStream(save));
		HashMapStore<K, V> nuova= (HashMapStore<K,V>) x.readObject();
		x.close();
		this.clear();
		this.putAll(nuova);
	}
	
	public V put(K e,V x) {
		V old=super.put(e, x);
		try {
			save();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return old;
		
	}
	
}
