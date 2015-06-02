
public class StringCell extends Cell {
	String contents;
	
	//sets original contents then the data to the cell
	public StringCell(String temp) {
		oContents = temp;
		temp = temp.substring(1, temp.length() - 1);
		contents = temp;
	}
	
	public String toString(Spreadsheet ss){
		return(contents);
	}
}
