package crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import model.Gost;

public class SobaCrud {
	
	public List<String> allRooms() { 
		List<String> list = new ArrayList<>();
		try(BufferedReader fr = new BufferedReader(new FileReader("res/Sobe.txt"));) {
			String line = "";
			while((line = fr.readLine()) != null) {
				list.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<String> allFreeRooms() { 
		List<String> list = new ArrayList<>();
		try(BufferedReader fr = new BufferedReader(new FileReader("res/SlobodneSobe.txt"));) {
			String line = "";
			while((line = fr.readLine()) != null) {
				line = line.trim();
				String[] tmp = line.split("-");
				list.add(tmp[0]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean reserve(String room, Gost korisnik, String start, String end, String type) {
		List<String> list = new ArrayList<>();
		BufferedReader fr = null;
		PrintWriter pwf = null;
		PrintWriter pwt = null;
		try {
			//citanje iz fajla i oslobadjanje resursa
			fr = new BufferedReader(new FileReader("res/SlobodneSobe.txt"));;
			String line = "";
			while((line = fr.readLine()) != null) {
				list.add(line);
			}
			fr.close();
			fr = null;
			
			//brisanje sa spiska slobodnih, cuvanje novog spiska slobodnih
			pwf = new PrintWriter(new BufferedWriter(new FileWriter("res/SlobodneSobe.txt", false)));
			for (String s : list) {
				if(s.equals(room)) 
					list.remove(s);
			}
			for (String s : list) {
				pwf.println(s);
			}
			
			//i dodavanje u spisak rezervacija
			pwt = new PrintWriter(new BufferedWriter(new FileWriter("res/Rezervacije.txt", true)));
			String tmp = room + "-" + korisnik.getName() + "-" + korisnik.getSurname() + "-" + start + "-" + end + "-" + type;
			pwt.println(tmp);
			
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null)
					fr.close();
				if(pwf != null)
					pwf.close();
				if(pwt != null)
					pwt.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public List<String> details(String room) {
		List<String> list = new ArrayList<>();
		try(BufferedReader fr = new BufferedReader(new FileReader("res/SlobodneSobe.txt"));) {
			String line = "";
			while((line = fr.readLine()) != null) {
				line = line.trim();
				String[] tmp = line.split("-");
				if(tmp[0].equals(room)) {
					for (int i = 1; i < tmp.length; i++) {
						list.add(tmp[i]);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<String> allTypes() { 
		List<String> list = new ArrayList<>();
		try(BufferedReader fr = new BufferedReader(new FileReader("res/TipoviRezervacije.txt"));) {
			String line = "";
			while((line = fr.readLine()) != null) {
				list.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
