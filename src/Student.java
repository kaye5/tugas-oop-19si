import java.util.*;
import java.io.IOException;
import java.text.*;
import java.time.*;
public class Student {
	String cwd = System.getProperty("user.dir");
	List<String> profile;
	Student(){		

	}
	private void studentMenu() {
		clear();
		putl("Student Menu");
		putl("1. Pinjam Buku");
		putl("2. Search Buku");
		putl("3. Kemabalikan Buku");
		putl("4. Keluar");
		put("Masukan Input : ");
		String i = GetInput();
		switch(i) {
		case "1":
			PinjamBuku();
			break;
		case "2":
			cariBuku();
			break;
		case "3"  :
			kembalikanBuku();
			break;
		case "4":
			tampilan t = new tampilan();
			t.MainPage();
			break;
		default:
			put("Wrong Input");			
			pause();
			clear();
			studentMenu();
		}
	}
	public void showMahsiswa() {
		clear();
		GetFile mahasiswa = new GetFile(cwd+"\\src\\student.json");
		for(List<String> item : mahasiswa.Get()) {
			System.out.printf("%-20s %-15s %s\n", item.get(0),item.get(1),item.get(2));
		}
	}
	private void cariBuku() {
		clear();
		putl("Search Buku");
		putl("==================");
		GetFile file = new GetFile(cwd+"\\src\\buku.csv");
		for(List<String> csv : file.Get()) {
			System.out.printf("%-3s %10s\n",csv.get(0),csv.get(1));
		}
		pause();
		clear();
	}
	private void PinjamBuku() {
		clear();
		GetFile pinjam = new GetFile(cwd+"\\src\\pinjaman.csv");
		GetFile buku = new GetFile(cwd+"\\src\\buku.csv");		
		putl("Pinjam Buku");
		putl("==================");
		putl("Masukan ID buku :");
		String id = GetInput();
		if(buku.Get().size() <= Integer.parseInt(id)) {
			putl("Buku tidak ditemukan");
			pause();
			clear();
		}			
		else {
			Date date = Calendar.getInstance().getTime();  
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
			String strDate = dateFormat.format(date);  
			List<String> baru = new ArrayList<String>();
			baru.add(id); baru.add(profile.get(0)); baru.add(strDate); baru.add("Waiting");
			pinjam.Get().add(baru);
			GetFile save = new GetFile();
			save.saveFile(cwd+"\\src\\pinjaman.csv", pinjam.Get());
			putl("Berhasil ID PEMINJAM = " + (pinjam.Get().size()-1) );
		}
	}

	private void kembalikanBuku() {
		Date tglKembali= Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String strTglKembali = dateFormat.format(tglKembali);  
		putl("Megembalikan buku");
		put("Masukan ID peminjam : ");
		String id = GetInput();
		GetFile pinjaman = new GetFile(cwd+"\\src\\pinjaman.csv");
		if(pinjaman.Get().size() > Integer.parseInt(id)-2 ) {
			List<String> temp = pinjaman.Get().get(Integer.parseInt(id));
			temp.set(3, "Done "+strTglKembali);
			pinjaman.Get().set(Integer.parseInt(id), temp);
			GetFile save = new GetFile();
			save.saveFile(cwd+"\\src\\pinjaman.csv", pinjaman.Get());
			String skrg;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(sdf.parse(temp.get(2)));
				c.add(Calendar.HOUR, 3);
				String tglPinjam = sdf.format(c.getTime());
				
				skrg = sdf.format(tglKembali);
				
				if(skrg.compareTo(tglPinjam) < 0)
					putl("Berhasil");
				else {
					Denda denda = new Denda(id);
					denda.bayarDenda();
				}
			} catch (ParseException e) {
//				 TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {		
			putl("Tidak ada entry");
			pause();
		}
	}
	
	public void studentLogin() {
		Boolean flag;
		GetFile file = new GetFile(cwd+"\\src\\student.json");
		System.out.print("Masukan Nim : ");
		String username = GetInput();
		System.out.print("Masukan Password : ");
		String password = GetInput();		
		for(List<String> data : file.Get()) {
			if(data.get(1).equals(username) && data.get(2).equals(password)) {
				flag = true;
				profile = data;
				studentMenu();
				break;
			}				
		}
		if(!true) {
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
