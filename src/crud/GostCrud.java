package crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Gost;

public class GostCrud {
	private List<Gost> listGost;
	
	public GostCrud() {
		this.listGost = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader("res/Korisnici.txt"));) {
			String line = "";
			while((line = br.readLine()) != null) {
				Gost g = Gost.parseGost(line);
				listGost.add(g);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Gost login(String username, String password) {
		for (Gost gost : listGost) {
			if(gost.getUsername().equals(username) && gost.getPassword().equals(password))
				return gost; 
		}
		return null;
	}

	public boolean addGost(Gost g) {
		if(g.getName().equals("") || g.getSurname().equals("") || g.getCellNumber().equals("") || 
				g.getEmail().equals("") || g.getUsername().equals("") || g.getPassword().equals(""))
			return false;
		for (Gost gost : listGost) {
			if(gost.getUsername().equals(g.getUsername()) || gost.getPassword().equals(g.getPassword()))
				return false;
		}
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("res/Korisnici.txt", true)));) {
			pw.println(g.filePrintFormat());
		} catch (IOException e) {
			e.printStackTrace();
		} 
		listGost.add(g);
		return true;
	}
	
}
