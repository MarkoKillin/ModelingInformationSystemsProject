package crud;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ObavestenjaCrud {

	public boolean domarObavestenje() {
		try(BufferedReader fr = new BufferedReader(new FileReader("res/DomarObavestenja.txt"));) {
			String line = fr.readLine();
			if(line != null && !line.equals(""))
				return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void deleteDomarObavestenje() {
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("res/DomarObavestenja.txt", false)));) {
			pw.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addDomarObavestenje() {
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("res/DomarObavestenja.txt", false)));) {
			pw.println("Obavestenje");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean menadzerObavestenje() {
		try(BufferedReader fr = new BufferedReader(new FileReader("res/MenadzerObavestenja.txt"));) {
			String line = fr.readLine();
			if(line != null && !line.equals(""))
				return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void deleteMenadzerObavestenje() {
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("res/MenadzerObavestenja.txt", false)));) {
			pw.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addMenadzerObavestenje() {
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("res/MenadzerObavestenja.txt", false)));) {
			pw.println("Obavestenje");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
