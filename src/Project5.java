
public class Project5 {

	public static void main(String[] args) {
		Read reader = new Read("puzzle.txt");
		String temp[];
		while(reader.ready())
		System.out.println(reader.readLine().split(" "));
		//System.out.println(temp);

	}

}
