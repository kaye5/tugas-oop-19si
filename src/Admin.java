import java.util.*;
public class Admin {
	String cwd = System.getProperty("user.dir");
	Buku b = new Buku();
	Student s = new Student();
	Admin(){
	}
	private void main() {
		clear();
		putl("1.Add buku");
		putl("2.Delete buku");
		putl("3.Edit buku");
		putl("4.Add mahasiwa");
		putl("5.delete mahasiwa");
		putl("6.show mahasiwa");
		putl("7.show buku");
		put("Masukan input  : ");
		switch(GetInput()) {
		case "1" : 
			b.addBuku();
			break;
		case "2" :
			b.deleteBuku();
			break;
		case "3" : 
			b.editBuku();
			break;
		case "4" :
			addMahasiswa();
			break;
		case "5" : 
			deleteMahasiswa();
			break;
		case "6" :			
			s.showMahsiswa();
			break;
		case "7" : 
			b.getBuku();
			break;
		default :
			putl("Wrong input");
			pause();
			main();
			break;
		}
		
	}
	private int deleteMahasiswa() {
		put("Masukan NIM : ");
		String nim = GetInput();
		GetFile mahasiswa = new GetFile(cwd+"\\src\\student.json");
		int idx =  0 ;
		for(List<String> data : mahasiswa.Get()) {
			if(data.get(1).equals(nim)) {
				mahasiswa.Get().remove(idx);
				mahasiswa.saveFile(cwd+"\\src\\student.json", mahasiswa.Get());
				putl("OK");
				return 0;
			}
			idx++;
		}
		putl("Not Found");
		return 0;
	}
	private void addMahasiswa() {
		clear();
		GetFile mahasiswa = new GetFile(cwd+"\\src\\student.json");
		put("Masukan nama mahasiswa = ");
		String name= GetInput();
		put("Mauskan nim mahasiswa = ");
		String nim = GetInput();
		put("Mauskan password mahasiswa = ");
		String password= GetInput();
		List<String> temp = new ArrayList<String>();
		temp.add(name); temp.add(nim); temp.add(password);
		mahasiswa.Get().add(temp);
		mahasiswa.saveFile(cwd+"\\src\\student.json", mahasiswa.Get());
	}
	public void adminLogin() {
		Boolean flag = false;
		GetFile file = new GetFile(cwd+"\\src\\admin.json");
		System.out.print("Masukan Username : ");
		String username = GetInput();
		System.out.print("Masukan Password : ");
		String password = GetInput();		
		for(List<String> data : file.Get()) {
			if(data.get(1).equals(username) && data.get(2).equals(password)) {
				flag = true;
				main();
				break;
			}				
		}
		if(!flag) {
			putl("wrong credentials");
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
