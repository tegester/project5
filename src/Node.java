
public class Node {
	private char letter;
	// color 0 = not visited
	// color 1 = explored
	private int color = 0; 
	
	public Node(char lett) {
		letter = lett;
	}
	
	public char getLetter(){
		return letter;
	}
	
	public void setColor(int newColor){
		color = newColor;
	}
	
	public int getColor(){
		return color;
	}
	
	public String toString(){
		return "[" +letter +", "+ color+"]";
	}

}
