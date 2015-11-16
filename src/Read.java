import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class Read{
	BufferedReader reader;
	
	public Read(String file){
		//checking for bad input 
		try {
			reader = Files.newBufferedReader(Paths.get(file));
			if(!reader.ready()){
				System.out.println("please use a valid file");
				System.exit(4);
			}
		} catch (IOException e) {
			System.out.println("please enter a valid text file");
			System.exit(99);
		}
		
	}
	
	public String readLine(){
		String line = "";
		try{
			line = reader.readLine();
		}
		//exit if read error
		catch (IOException e) {
			System.out.println("check readLine, io error");
			System.exit(10);
		}
		return line;
	}
	
	public boolean ready(){
		boolean isready = false;
		try {
			isready= reader.ready();
		} catch (IOException e) {
			System.out.println("not ready");
			System.exit(10);
		}
		return isready;
	}
	
	// be sure to close the file to prevent errors 
	public void close(){
		try {
			reader.close();
		} catch (IOException e) {
			System.out.println("issues with closing");
			System.exit(22);
		}
	}
}

