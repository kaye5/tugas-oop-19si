import java.util.*;
public class tampilan {
	String cwd = System.getProperty("user.dir");
	public void MainPage() {
		clear();
		System.out.println("Pick a user");
		System.out.println("1. Student");
		System.out.println("2. Admin");
		System.out.print("Masukan input : ");
		String user = GetInput();
		clear();
		switch(user) {
		case "1" :
			Student s = new Student();
			s.studentLogin();
			break;
		case "2" : 
			Admin a = new Admin();
			a.adminLogin();
			break;
		default : 
			System.out.println("Wrong input");
			pause();
		}
	}
	
	
	private String GetInput(){
		Scanner myObj = new Scanner(System.in);
		String userInput = myObj.nextLine();
		return userInput.toString();
	}
	private void pause() {
		System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();
	}
	
	private void put(Object args) {
		System.out.print(args);
	}
	private void putl(Object args) {
		System.out.println(args);
	}
	private void clear() {
		System.out.flush();
	}
}
