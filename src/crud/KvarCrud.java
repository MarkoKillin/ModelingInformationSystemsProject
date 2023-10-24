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

public class KvarCrud {
	
	public boolean addError(String room, String description) {
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("res/Kvarovi.txt", true)));) {
			String error = room + " | " + description;
			pw.println(error);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<String> allErrors() { 
		List<String> list = new ArrayList<>();
		try(BufferedReader fr = new BufferedReader(new FileReader("res/Kvarovi.txt"));) {
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
	
	public boolean deleteError(String kvar) {
		List<String> list = new ArrayList<>();
		try(BufferedReader fr = new BufferedReader(new FileReader("res/Kvarovi.txt"));
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("res/Kvarovi.txt", false)));) {
			String line = "";
			while((line = fr.readLine()) != null) {
				list.add(line);
			}
			for (String s : list) {
				if(s.equals(kvar))
					list.remove(s);
			}
			for (String s : list) {
				pw.println(s);
			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
