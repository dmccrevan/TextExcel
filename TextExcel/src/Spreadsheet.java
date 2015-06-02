import java.text.ParseException;
import java.util.Scanner;

import persistence.PersistenceHelper;
import persistence.Savable;


public class Spreadsheet implements Savable{
	Cell[][] ss = new Cell[10][7];
	
	public Spreadsheet(){
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 7; col++){
				ss[row][col] = new EmptyCell();
			}
		}
	}
	
	//array of letters for top header
	String[] abc = {" ","A", "B", "C", "D", "E", "F", "G"};
	public void print(){
		//Prints out top header
		for(int i = 0; i < 8; i++){
			System.out.print(return12(abc[i]) + "|");
		}
		System.out.println();
		for(int i = 0; i < 8; i++){
			System.out.print("------------+");
		}
		System.out.println();
		
		//prints out header and spreadsheet
		for(int row = 0; row < 10; row++){
			//prints out header
			String leftNum = Integer.toString(row + 1);
			System.out.print(return12(leftNum) + "|");
			
			//prints out spreadsheet
			for(int col = 0; col < 7; col++){		
				System.out.print(return12(ss[row][col].toString(this)) + "|");
			}
			
			//prints out header
			System.out.println();
			System.out.print("------------+");
			
			//prints out spreadsheet
			for(int k = 0; k < 7; k++){
				System.out.print("------------+");
			}
			System.out.println();
		}
	}
	//returns a string to a 12 character long string
	public static String return12(String temp){
		int amountofchars = temp.length();
		boolean whichSide = true;
		if(amountofchars > 12){
			temp = temp.substring(0, 11) + ">";
		}
		else{
			while(amountofchars < 12){
				if(whichSide == true){
					temp = " " + temp;
					whichSide = false;
				}
				else{
					temp = temp + " ";
					whichSide = true;
				}
				amountofchars++;
			}
		}
		return temp;
	}
	
	//prints the cell contents
	public void printContents(int _row, int _col) {
		System.out.println(ss[_row][_col].oContents());	
	}
	
	//clears all cell contents
	 public void clear(){
		 for(int row = 0; row < 10; row++){
				for(int col = 0; col < 7; col++){
					ss[row][col] = new EmptyCell();
				}
			}
	 }
	 
	//find ss position
	public static Position findPos(String temp){
		String col = temp.substring(0,1);
		String row = temp.substring(1);
		int _col = col.charAt(0) - 'A';
		int _row = Integer.parseInt(row) - 1;
		Position position = new Position(_row, _col);
		return position;
	}

	//saves the contents of the cells to a text file
	@Override
	public String[] getSaveData() {
		String[] temp;
		temp = new String[10 * 7];
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 7; col++){
				temp[(row * 7) + col] = ss[row][col].oContents;
			}
		}
		return temp;
	}
	
	//loads the contents of the cells from the text file
	@Override
	public void loadFrom(String[] saveData) {
		for(int row = 0; row < 10; row++){
			for(int col = 0; col < 7; col++){
				ss[row][col] = Cell.decide(saveData[(row * 7) + col]);
			}
		}
		
	}
	
	//sets the Cells to the spreadsheet
	public void setCell(Position p, String contents) throws ParseException{
		ss[p.row][p.col] = Cell.decide(contents);
	}
	
	//gets value of formula cell
	public double getValue(Position p){
		String str = ss[p.row][p.col].toString(this);
		double answer = Double.parseDouble(str);
		return answer;
		
	}
	
	public void sort(String pos){
		
		boolean DorA = false; //true is ascending, false is descending
		Scanner get = new Scanner(pos);
		String AorD = get.next();
		if(AorD.equalsIgnoreCase("sorta")){
			DorA = true;
		}
		
		Position start = findPos(get.next());
		String nothing = get.next();
		Position end = findPos(get.next());
		int _pos = 0;
		Cell[] arr = new Cell[(end.row + 1 - start.row) * (end.col + 1 - start.col)];

		
		for(int i = start.row; i <= end.row; i++){
			for(int j = start.col; j <= end.col; j++){
				arr[_pos] = ss[i][j]; //set the next position of arr to the next value of the specified cells
				_pos++; //move to next Position of arr
			}
		}
		
		//bubble sort
		for (int k = 0; k < arr.length; k++) {
			for (int i = 0; i < arr.length - 1; i++) {
				double f = Double.parseDouble(arr[i].toString(this));
				double s = Double.parseDouble(arr[i + 1].toString(this));
				if(DorA == true){
					if (f > s) {
						Cell temp = arr[i];
						arr[i] = arr[i + 1];
						arr[i + 1] = temp;
					}
				}
				else{
					if (f < s) {
						Cell temp = arr[i];
						arr[i] = arr[i + 1];
						arr[i + 1] = temp;
					}
				}
			}
		}
		
		int __pos = 0;
		for(int i = start.row; i <= end.row; i++){
			for(int j = start.col; j <= end.col; j++){
				ss[i][j] = arr[__pos]; 
				__pos++;
			}
		}
		
		
		
	}
	
}

