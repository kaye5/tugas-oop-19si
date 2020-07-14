import java.util.*;

public class Buku {
	Buku(){}
	String cwd = System.getProperty("user.dir");
	public  void getBuku() {
		clear();
		GetFile buku = new GetFile(cwd+"\\src\\buku.csv");
		for(List<String> item : buku.Get()) {
			System.out.printf("%-3s %-10s\n", item.get(0),item.get(1));
		}
	}
	public void addBuku() {
		put("Masukan Nama : ");
		String nama = GetInput();
		GetFile buku = new GetFile(cwd+"\\src\\buku.csv");
		List<String> bu= new ArrayList<String>();
		bu.add(""+(buku.Get().size()-1));
		bu.add(nama);
		buku.Get().add(bu);
		buku.saveFile(cwd+"\\src\\buku.csv", buku.Get());
	}
	public void deleteBuku() {
		clear();
		put("Masukan ID yang ingin dihapus :");
		int id = Integer.parseInt(GetInput());
		GetFile buku = new GetFile(cwd+"\\src\\buku.csv");
		buku.Get().remove(id+1);
		int idx = 0;
		for(List<String> b : buku.Get()) {
			if(idx == 0) {
				idx++;
				continue;
			}
			b.set(0, ""+(idx-1));
			buku.Get().set(idx, b);
			idx++;
		}		
		buku.saveFile(cwd+"\\src\\buku.csv", buku.Get());
	}
	public void editBuku() {
		clear();
		put("Masukan ID yang ingin diganti :");
		int id = Integer.parseInt(GetInput());
		put("Masukan Nama baru : ");
		String name = GetInput();
		GetFile buku = new GetFile(cwd+"\\src\\buku.csv");
		List<String> temp = new ArrayList<String>();
		temp.add(""+id);
		temp.add(name);
		buku.Get().set(id+1, temp);
		buku.saveFile(cwd+"\\src\\buku.csv", buku.Get());
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
