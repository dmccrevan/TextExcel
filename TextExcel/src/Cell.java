import java.text.ParseException;
import java.util.Scanner;


public abstract class Cell {
	//original contents of all types of cells
	String oContents;
	
	//for setting data to the Spreadsheet
	public abstract String toString(Spreadsheet ss);
	
	
	//for print contents of the cell
	public String oContents(){
		return oContents;
	}
	
	public static Cell decide(String contents){
		
		if(contents.indexOf('"') != -1){
			return new StringCell(contents);
		}
		else if(contents.indexOf("(") != -1){
			return new FormulaCell(contents);
		}
		else if(contents.indexOf("/") != -1){
				try {
					return new DateCell(contents);
				} catch (ParseException e) {
					throw new IllegalArgumentException("If you are setting the block to a date, use the MM/DD/YYYY format.", e);
				}
		}
		else if(contents.equalsIgnoreCase("<empty>")){
			return new EmptyCell();
		}
		else{
			try{
				return new DoubleCell(contents);
			}
			catch(Exception e){
				throw new IllegalArgumentException("If you are setting the block to a word, surround the word with quotes.", e);
			}
		}
	}
	
}

