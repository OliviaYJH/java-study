package creational.singleton;

public class Client {

	public static void main(String[] args) { // Singleton 의 Client 
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
	}

}
