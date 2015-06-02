
public class DoubleCell extends Cell {
	double contents;
	//sets original contents then the data to the cell
	public DoubleCell(String temp) {
		oContents = temp;
		Double _temp = Double.parseDouble(temp);
		contents = _temp;
	}

	//returns a string containing the double value
	public String toString(Spreadsheet ss) {
		String _contents = Double.toString(contents);
		return(_contents);
	}


}
   