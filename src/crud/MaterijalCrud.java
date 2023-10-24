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

public class MaterijalCrud {

	public boolean addMaterials(List<String> list) {
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("res/Materijal.txt", true)));) {
			for (String s : list) {
				pw.println(s);
			}
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<String> allMaterial() { 
		List<String> list = new ArrayList<>();
		try(BufferedReader fr = new BufferedReader(new FileReader("res/Materijal.txt"));) {
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
	
	public boolean order(String s) {
		try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("res/Poruceno.txt", true)));) {
			pw.println(s);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
