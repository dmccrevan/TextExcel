import java.util.Scanner;


public class FormulaCell extends Cell {
	
	double value;
	
	//returns the value for the specified operation
	public String toString(Spreadsheet ss){
		String temp = oContents.substring(1, oContents.length() - 1);
		Scanner get = new Scanner(oContents);
		
			double answer = math(ss);
			String str = Double.toString(answer);
			return(str);
		
	}
	
	//sets the original data to the formula
	public FormulaCell(String string){
		oContents = string;
	}

	//parse formula
	private double math(Spreadsheet ss){
		
		//grabs the operation from the input
		String operation = oContents.substring(1, oContents.length() - 1);
		Scanner get = new Scanner(operation);
		double answer = 0;
		
		//grab original value to start with
		answer = getVal(ss, get);
				
				//keep running until there is no operation left to do.
				while(get.hasNext()){
					String op = get.next();
					double otherNum = getVal(ss, get);
					if(op.equals("+")){
						answer += otherNum;
					}
					else if(op.equals("-")){
						answer -= otherNum;
					}
					else if(op.equals("*")){
						answer = answer * otherNum;
					}
					else{
						answer = answer / otherNum;
					}
				} 
		
		return answer;
	}
	
	//gets the value of the next number in the operation.
	private double getVal(Spreadsheet ss, Scanner get){
		
		
		double num;
		//grabs the double if the next object is a double
			if(get.hasNextDouble()){
				num = get.nextDouble();
			}
			//if the object is not a double, do this
			else{
				//grabs the next strng
				String temp = get.next();
				
				//if the next string is the sum operation, calculate the sum of the cells specified
				if(temp.equalsIgnoreCase("sum")){
					String start = get.next();
					String nothing = get.next();
					String end = get.next();
					num = sum(start, end, ss);
				}
				//if the next string is the avg operation, calculate the average of the cells specified
				else if(temp.equalsIgnoreCase("avg")){
					String start = get.next();
					String nothing = get.next();
					String end = get.next();
					num = avg(start, end, ss);
				}
				//grabs the reference value of the cell
				else{
					Position p = Spreadsheet.findPos(temp);
					num = ss.getValue(p);
				}
			}
		return num;
	}
	
	//get average of values in rectangle
	private double avg(String _start, String _end, Spreadsheet ss){
		Position start = Spreadsheet.findPos(_start); //gets start cell
		Position end = Spreadsheet.findPos(_end); //gets end cell
		double answer = 0; //initialize variable
		int cellAmount = 0; //tracker of how many cells there are for the average
		for(int i = start.row; i <= end.row; i++){
			for(int j = start.col; j <= end.col; j++){
				Position temp = new Position(i, j);
				answer = answer + ss.getValue(temp); //adds to answer
				cellAmount++;
			}
		}
		
		answer = answer / cellAmount; //gets the average of the cells
		
		return answer;
	}

	//get sum of values in rectangle
	private double sum(String _start, String _end, Spreadsheet ss){
		Position start = Spreadsheet.findPos(_start); //gets the starting cell
		Position end = Spreadsheet.findPos(_end); //gets the ending cell
		double answer = 0; //initializes value to return
		for(int i = start.row; i <= end.row; i++){
			for(int j = start.col; j <= end.col; j++){
				Position temp = new Position(i, j);
				answer = answer + ss.getValue(temp); //adds to the answer
			}
		}
		
		return answer;
	}

	
}
