import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCell extends Cell {
	
	Date date;
	String sDate;
	
	//parses a data then sets original contents then the data to the cell
	public DateCell(String temp) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		Date dateStr = format.parse(temp);
		String formattedDate = format.format(dateStr);
		date = dateStr;
		sDate = formattedDate;
		oContents = formattedDate;
	}
	
	//returns a string containing the date
	public String toString(Spreadsheet ss) {
		return(sDate);
	}

}
