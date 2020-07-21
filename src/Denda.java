import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Denda {
	String id;
	String cwd = System.getProperty("user.dir");
	Denda(String id){
		this.id = id;
	}
	public void bayarDenda() {
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		System.out.println("Anda dikenakan denda untuk id peminjaman "+id);
		GetFile denda = new GetFile(cwd+"\\src\\denda.csv");
		List<String> temp = new ArrayList<String>();
		temp.add(id);
		temp.add(strDate);
		denda.Get().add(temp);
		denda.saveFile(cwd+"\\src\\denda.csv", denda.Get());
	}
}
