import java.util.SortedSet;
import java.util.TreeSet;


public class Solver {
	private char puzzle[][];
	private int size;
	private TreeSet<String> words;
	
	//reads in and makes data structures for the puzzle and the list of words
	public Solver(){
		//read in the puzzle
		Read reader = new Read("puzzle.txt");
		size = Integer.parseInt(reader.readLine());
		puzzle = new char[size][size];
		int i = 0;
		while(reader.ready()){
			String[] temp = reader.readLine().split(" ");
			for(int j = 0; j< size; j++){
				puzzle[i][j] = temp[j].charAt(0);
			}
			i++;
		}
		reader.close();
		
		//read in the words to search for 
		Read reader2 = new Read("words.txt");
		words = new TreeSet<String>();
		while(reader2.ready()){
			String word = reader2.readLine();
			words.add(word);
		}
		reader2.close();
	}

	public void showPuzzle() {
		System.out.println(size);
		for(int j = 0; j<size;j++){
			for(int k = 0; k<size;k++){
				System.out.print(puzzle[j][k]+" ");
			}
			System.out.print("\n");
		}
	 }
	
	public void showWords() {
		System.out.println(words);
	 }
	
	//search the puzzle for the words move only along major lines
	public void walkPuzzle(){
		for (int i = 0; i < size;i++ ){
			for (int j =0; j<size;j++){
//				search(i,j);
				Search explore = new Search(i,j,size,puzzle,words);
				explore.run();
			}
		}
	}
	
	public void search(int i, int j){
		if(i<0 || i>size || j<0 || j>size){
			System.out.println("search out of bounds");
			System.exit(1);
		}
		if(i-3 >= 0){searchN(i,j);}
		if(j+4 <= size){searchE(i,j);}
		if(i+4 <= size){searchS(i,j);}
		if(j-3 >= 0){searchW(i,j);}
		if(i-3 >= 0 && j+4 <= size){searchNE(i,j);}
		if(i-3 >= 0 && j-3 >= 0){searchNW(i,j);}
		if(i+4 <= size && j+4 <= size){searchSE(i,j);}
		if(i+4 <= size && j-3 >= 0){searchSW(i,j);}
		System.gc();
	}
	
	private void searchN(int i,int j){
		StringBuffer posWord = new StringBuffer();
		char c;
		for(int k = 0; k < 4; k++){
			c = puzzle[i-k][j];
			posWord.append(c);
		}
		boolean isword = words.contains(posWord.toString());
		if(isword){
			System.out.println(posWord +"("+(j+1)+","+(i+1)+",n)");
		}
		SortedSet<String> subset;
		subset = words.subSet((posWord.toString()+"a"), (posWord.toString()+"z"));
		//same k from for loop 12 lines up
		int k = 4;
		while(!subset.isEmpty()){
			//not sure that this is a good idea
			if(i-k<0){break;}
			c = puzzle[i-k][j];
			posWord.append(c);
			isword = subset.contains(posWord.toString());
			if(isword){
				System.out.println(posWord +"("+(j+1)+","+(i+1)+",n)");
			}
			subset = subset.subSet(posWord.toString()+"a", posWord.toString()+"z");
			k++;
		}	
	}
	
	private void searchNE(int i,int j){
		StringBuffer posWord = new StringBuffer();
		char c;
		for(int k = 0; k < 4; k++){
			c = puzzle[i-k][j+k];
			posWord.append(c);
		}
		boolean isword = words.contains(posWord.toString());
		if(isword){
			System.out.println(posWord +"("+(j+1)+","+(i+1)+",ne)");
		}
		SortedSet<String> subset;
		subset = words.subSet((posWord.toString()+"a"), (posWord.toString()+"z"));
		//same k from for loop 12 lines up
		int k = 4;
		while(!subset.isEmpty()){
			if(i-k < 0 || j+k >= size){break;}
			c = puzzle[i-k][j+k];
			posWord.append(c);
			isword = subset.contains(posWord.toString());
			if(isword){
				System.out.println(posWord +"("+(j+1)+","+(i+1)+",ne)");
			}
			subset = subset.subSet(posWord.toString()+"a", posWord.toString()+"z");
			k++;
		}
	}
	private void searchE(int i,int j){
		StringBuffer posWord = new StringBuffer();
		char c;
		for(int k = 0; k < 4; k++){
			c = puzzle[i][j+k];
			posWord.append(c);
		}
		boolean isword = words.contains(posWord.toString());
		if(isword){
			System.out.println(posWord +"("+(j+1)+","+(i+1)+",e)");
		}
		SortedSet<String> subset;
		subset = words.subSet((posWord.toString()+"a"), (posWord.toString()+"z"));
		//same k from for loop 12 lines up
		int k = 4;
		while(!subset.isEmpty()){
			if(j+k >= size){break;}
			c = puzzle[i][j+k];
			posWord.append(c);
			isword = subset.contains(posWord.toString());
			if(isword){
				System.out.println(posWord +"("+(j+1)+","+(i+1)+",e)");
			}
			subset = subset.subSet(posWord.toString()+"a", posWord.toString()+"z");
			k++;
		}
	}
	private void searchSE(int i,int j){
		StringBuffer posWord = new StringBuffer();
		char c;
		for(int k = 0; k < 4; k++){
			c = puzzle[i+k][j+k];
			posWord.append(c);
		}
		boolean isword = words.contains(posWord.toString());
		if(isword){
			System.out.println(posWord +"("+(j+1)+","+(i+1)+",se)");
		}
		SortedSet<String> subset;
		subset = words.subSet((posWord.toString()+"a"), (posWord.toString()+"z"));
		//same k from for loop 12 lines up
		int k = 4;
		while(!subset.isEmpty()){
			if(i+k >= size || j+k >= size){break;}
			c = puzzle[i+k][j+k];
			posWord.append(c);
			isword = subset.contains(posWord.toString());
			if(isword){
				System.out.println(posWord +"("+(j+1)+","+(i+1)+",se)");
			}
			subset = subset.subSet(posWord.toString()+"a", posWord.toString()+"z");
			k++;
		}
	}
	private void searchS(int i,int j){
		StringBuffer posWord = new StringBuffer();
		char c;
		for(int k = 0; k < 4; k++){
			c = puzzle[i+k][j];
			posWord.append(c);
		}
		boolean isword = words.contains(posWord.toString());
		if(isword){
			System.out.println(posWord +"("+(j+1)+","+(i+1)+",s)");
		}
		SortedSet<String> subset;
		subset = words.subSet((posWord.toString()+"a"), (posWord.toString()+"z"));
		//same k from for loop 12 lines up
		int k = 4;
		while(!subset.isEmpty()){
			if(i+k >= size){break;}
			c = puzzle[i+k][j];
			posWord.append(c);
			isword = subset.contains(posWord.toString());
			if(isword){
				System.out.println(posWord +"("+(j+1)+","+(i+1)+",s)");
			}
			subset = subset.subSet(posWord.toString()+"a", posWord.toString()+"z");
			k++;
		}
	}
	private void searchSW(int i,int j){		
		StringBuffer posWord = new StringBuffer();
		char c;
		for(int k = 0; k < 4; k++){
			c = puzzle[i+k][j-k];
			posWord.append(c);
		}
		boolean isword = words.contains(posWord.toString());
		if(isword){
			System.out.println(posWord +"("+(j+1)+","+(i+1)+",sw)");
		}
		SortedSet<String> subset;
		subset = words.subSet((posWord.toString()+"a"), (posWord.toString()+"z"));
		//same k from for loop 12 lines up
		int k = 4;
		while(!subset.isEmpty()){
			//not sure that this is a good idea
			subset = new TreeSet<String>(subset);
			if(i+k >= size || j-k < 0){break;}
			c = puzzle[i+k][j-k];
			posWord.append(c);
			isword = subset.contains(posWord.toString());
			if(isword){
				System.out.println(posWord +"("+(j+1)+","+(i+1)+",sw)");
			}
			subset = subset.subSet(posWord.toString()+"a", posWord.toString()+"z");
			k++;
		}
	}
	private void searchW(int i,int j){
		StringBuffer posWord = new StringBuffer();
		char c;
		for(int k = 0; k < 4; k++){
			c = puzzle[i][j-k];
			posWord.append(c);
		}
		boolean isword = words.contains(posWord.toString());
		if(isword){
			System.out.println(posWord +"("+(j+1)+","+(i+1)+",w)");
		}
		SortedSet<String> subset;
		subset = words.subSet((posWord.toString()+"a"), (posWord.toString()+"z"));
		//same k from for loop 12 lines up
		int k = 4;
		while(!subset.isEmpty()){
			if(j-k < 0){break;}
			c = puzzle[i][j-k];
			posWord.append(c);
			isword = subset.contains(posWord.toString());
			if(isword){
				System.out.println(posWord +"("+(j+1)+","+(i+1)+",w)");
			}
			subset = subset.subSet(posWord.toString()+"a", posWord.toString()+"z");
			k++;
		}
	}
	private void searchNW(int i,int j){
		StringBuffer posWord = new StringBuffer();
		char c;
		for(int k = 0; k < 4; k++){
			c = puzzle[i-k][j-k];
			posWord.append(c);
		}
		boolean isword = words.contains(posWord.toString());
		if(isword){
			System.out.println(posWord +"("+(j+1)+","+(i+1)+",nw)");
		}
		SortedSet<String> subset;
		subset = words.subSet((posWord.toString()+"a"), (posWord.toString()+"z"));
		//same k from for loop 12 lines up
		int k = 4;
		while(!subset.isEmpty()){
			if(i-k < 0 || j-k < 0){break;}
			c = puzzle[i-k][j-k];
			posWord.append(c);
			isword = subset.contains(posWord.toString());
			if(isword){
				System.out.println(posWord +"("+(j+1)+","+(i+1)+",nw)");
			}
			subset = subset.subSet(posWord.toString()+"a", posWord.toString()+"z");
			k++;
		}
	}
	
	public void prefixTest(String ilist){
		SortedSet<String> newwords;
		newwords = words.subSet(ilist+"a", ilist+"z");
		System.out.println(words.contains(ilist));
		System.out.println(newwords);
	}

}

