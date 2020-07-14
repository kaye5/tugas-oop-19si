import java.io.*;
import java.util.*;
public class GetFile {
	List<List<String>> data = new ArrayList<List<String>>();
	GetFile(String path){
		try {
	      File myObj = new File(path);
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        String csv = myReader.nextLine();
	        List<String> temp = Arrays.asList(csv.split(","));
	        data.add(temp);
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
	}
	GetFile(){
		
	}
	public void saveFile(String path,List<List<String>> newItem){
		try {
		FileWriter file = new FileWriter(path);
		for(List<String> csv : newItem) {
			String text = String.join(",", csv);
			file.write(text+"\n");
		}
		file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<List<String>> Get(){
		return data;
	}
}
