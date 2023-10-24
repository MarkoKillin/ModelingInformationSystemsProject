package model;

public class Gost {
	private String name;
	private String surname;
	private String cellNumber;
	private String email;
	private String username;
	private String password;
	private String type;

	public Gost(String name, String surname, String cellNumber, String email, String username, String password, String type) {
		super();
		this.name = name;
		this.surname = surname;
		this.cellNumber = cellNumber;
		this.email = email;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getCellNumber() {
		return cellNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return name + " " + surname + ", " + cellNumber + ", " + email + ", " + type;
	}
	
	public String filePrintFormat() {
		return name + " " + surname + " " + cellNumber + " " + email + " " + username + " " + password + " " + type;
	}
	
	public static Gost parseGost(String text) {
		String[] part = text.split(" ");
		Gost g = new Gost(part[0], part[1], part[2], part[3], part[4], part[5], part[6]);
		return g;
	}
}
