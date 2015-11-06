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
	
	//to be deleted not used in project 5
	//returns the next word even if there are preceding separators
	//returns null if there is no next word
	public String readWord(){
		String newword = "";
		try{
			//the list of separators 
			String separators = ",.!?\"%$&- 	"+'\n'+'\r'+"1234567890";
			//read first character
			int c = reader.read();
			//check if next character is end of file
			if (c == -1){
				return null;
			}
			String letter = Character.toString((char) c);
			//remove preceding separators or reach end of file
			while(separators.contains(letter)){
				c = reader.read();
				if (c == -1){
					return null;
				}
				letter = Character.toString((char) c);
			}
			//now letter is always the first part of a word 
			while(!separators.contains(letter)){
				//build word one letter at a time
				newword= newword + letter;
				c = reader.read();
				//check for end of word
				if (c == -1){
					return newword;
				}
				//get letter to build 
				letter = Character.toString((char) c);
			}
		//exit if read error
		}catch (IOException e) {
			System.out.println("check readWord io error");
			System.exit(9);
		}
		return newword;
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

