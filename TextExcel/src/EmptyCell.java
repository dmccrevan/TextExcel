
public class EmptyCell extends Cell {
	//initial cell with no data
	String contents = "";
	
	//sets the original contents to empty
	public EmptyCell() {
		oContents = "<empty>";
	}
	
	//returns a string with nothing in it
	public String toString(Spreadsheet ss){
		return(contents);
	}
}
