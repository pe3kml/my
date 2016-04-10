package Objects;

public class Client {
	
	private static String Id_client;
	private static String Name;
	private static String Surname;
	private static float weight;
	private static float height;
	private static String address;
	private static String date_birth;
	
	
	public static String getId_client() {
		return Id_client;
	}
	public static void setId_client(String string) {
		Id_client = string;
	}
	public static String getName() {
		return Name;
	}
	public static void setName(String name) {
		Name = name;
	}
	public static String getSurname() {
		return Surname;
	}
	public static void setSurname(String surname) {
		Surname = surname;
	}
	public static float getWeight() {
		return weight;
	}
	public static void setWeight(float weight) {
		Client.weight = weight;
	}
	public static float getHeight() {
		return height;
	}
	public static void setHeight(float height) {
		Client.height = height;
	}
	public static String getAddress() {
		return address;
	}
	public static void setAddress(String address) {
		Client.address = address;
	}
	public static String getDate_birth() {
		return date_birth;
	}
	public static void setDate_birth(String date_birth) {
		Client.date_birth = date_birth;
	}
	

}
