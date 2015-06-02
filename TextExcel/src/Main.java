
import java.io.File;
import java.text.ParseException;
import java.util.Scanner;

import persistence.PersistenceHelper;
import persistence.Savable;
public class Main {

	public static void main(String[] args) throws Exception {
		Scanner get = new Scanner(System.in);
		System.out.println("Welcome to Text Excel!");
		boolean run = true;
		Spreadsheet ss = new Spreadsheet();
		while(run == true){
			
			
			//Gets user input and decides what to do
			System.out.print("Enter a command: ");
			String UI = get.nextLine();
			
			try{
				
			
				//prints the spreadsheet
				if(UI.equalsIgnoreCase("print")){
					ss.print();
				}
				
				//quits the spreadsheet
				else if(UI.equalsIgnoreCase("quit")){
					System.out.println("Shutting Down...");
					run = false;
				}
				
				//sets data to cells
				else if(UI.indexOf("=") != -1){
					String position = UI.substring(0, UI.indexOf("=") - 1);
					Position p = Spreadsheet.findPos(position);
					String contents = UI.substring(UI.indexOf("=") + 2);
					ss.setCell(p, contents);
				}
				//clears the SS data
				else if(UI.equalsIgnoreCase("clear")){
					ss.clear();
				}
				
				//saves the spreadsheet data to a text file
				else if(UI.equalsIgnoreCase("save")){
					try {
						System.out.println("What file would you like to save to?");
						String filename = get.nextLine();
						PersistenceHelper.save(filename, ss);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//loads the spreadsheet data from a text file
				else if(UI.equalsIgnoreCase("load")){
					System.out.println("What file would you like to load from?");
					String filename = get.nextLine();
					PersistenceHelper.load(filename, ss);
				}
				//clears all cells in spreadsheet
				else if(UI.startsWith("clear")){
					String position = UI.substring(6);
					Position p = Spreadsheet.findPos(position);
					ss.setCell(p, "<empty>");
				}
				
				//sorts the specified cells in ascending order
				else if(UI.indexOf("sorta") != -1 || UI.indexOf("sortd") != -1){
					ss.sort(UI);
				}
				//prints out the data of the typed in cell
				else{
					Position p = Spreadsheet.findPos(UI);
					
					ss.printContents(p.row, p.col);
				}
			}
			catch(Exception e){
				System.out.println("Invalid Input.");
				e.printStackTrace();
			}
		}
	}
	
	

}
