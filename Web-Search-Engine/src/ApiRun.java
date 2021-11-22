/*
This is the main driver program which runs all the modules
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import algorithms.Sequences;
import algorithms.LLOperation;
import algorithms.QPHashTable2;
import algorithms.QPHashTable2.HashEntry;

public class ApiRun{
	public static  String DIR_NAME = "W3C_Web_Pages/Text2/";

	public static void display_contents_file(String filename) throws FileNotFoundException {
		String path = DIR_NAME + filename;
		File file = new File(path);
		Scanner scanner = new Scanner(file);
		System.out.println("********URL content******");
		while (scanner.hasNext()) {
		
			String line = scanner.nextLine();
			System.out.println(line+"\n");
		}
	scanner.close();
	}

	public static void display_websites(HashEntry obj,int flag, LLOperation linked_list_obj) throws FileNotFoundException {

			System.out.println("\n**********Recommended URL links based on word frequency**********\n");
		
			for (int l = 0; l < obj.display_file.size(); l++) {
				 System.out.println(l+1 + ". " + obj.display_file.get(l));
				 if (l == 9) break;
			}

		Scanner in2 = new Scanner(System.in);
		String temp="";
	
		if (flag == 1) temp = "Enter the id of the URL you want to open";
		if (flag == 2) temp = "If you want to check out another url, enter the id of that URL you want to open";

		System.out.println("\n"+temp+": \nEnter -100 to go back");
		System.out.println("\tEnter your choice: ");
		int choice = in2.nextInt();

		if (choice == -100)
			return;

		else if (choice > obj.display_file.size() || choice < 0) {
			System.out.println("!!!!!!! PLEASE GIVE VALID INPUT CHOICE !!!!!!!!!!!!");
			display_websites(obj, 1, linked_list_obj);
		}

		else {
		
			String file_name = obj.display_file.get(choice-1).toString();
			file_name = file_name.substring(0,file_name.indexOf("="));

	
			linked_list_obj.add_node_beginning(file_name);

			display_contents_file(file_name);


			display_websites(obj, 2, linked_list_obj);
		}
	}



	public static void search_word(QPHashTable2 QH, LLOperation linked_list_obj) throws FileNotFoundException {
		Scanner in2 = new Scanner(System.in);

		System.out.println("\n\tEnter a word: ");
		String word = in2.nextLine();

	
		int freq = (QH.return_freq(word));
		HashEntry obj = null;

	
		if (freq != -1) {
			System.out.println("\n\t*************List of URLS with frequency count*********\n");
	
			 obj = QH.return_obj(word);

		
			 display_websites(obj, 1, linked_list_obj);

		}
		else {
			
			System.out.println("!!!!!!! Word not found. !!!!!!!!!!!!");

		
			ArrayList arr = QH.index_arr;

		
			Sequences o1 = new Sequences();


			HashEntry[] arr2 = QH.array;


			int min_thres = 2;
			int l = 0;
			for (int k = 0; k < arr.size() ; k++) {

				String hash_table_word = arr2[(int) arr.get(k)].element.toString();

				int dr = o1.editDistance(word, hash_table_word);
				if (dr <= min_thres) {
					l ++;
					if (l == 1) System.out.println("*********Are you looking for this word?********");
					System.out.println(hash_table_word);
				}
				if (l == 5) break;

			}



		}//end of else
	}

	public static void browser_history_operation(LLOperation linked_list_obj) {
		while (1 == 1) {
	
			System.out.println("1.View history\n2.Delete history by id\n3.Clear the entire history\n4.Go Back");
			System.out.println("\tEnter your choice: ");
			Scanner in = new Scanner(System.in);
			int choice = in.nextInt();

			if (choice == 1) linked_list_obj.display_list();
			else if (choice == 2) {

				System.out.println("\tEnter the id of the URL you want to delete: ");
				Scanner in2 = new Scanner(System.in);
				int id = in2.nextInt();
		
				linked_list_obj.delete_node(id);

			}

			else if (choice == 3) linked_list_obj.clear_list();
			else if (choice == 4) {return;}
			else {System.out.println("!!!!!!! PLEASE GIVE VALID INPUT CHOICE !!!!!!!!!!!!");}
		}
	}

	public static void main(String[] args) throws IOException, URISyntaxException {


	
		File folder = new File("W3C_Web_Pages/");

		HTMLToText1 htmlToText = new HTMLToText1();
	
		System.out.println("---------------- NLP in Progress -------------------");
		htmlToText.filesFolderOpr(folder);
		System.out.println("---------------- Completed -------------------");

		QPHashTable2 hash_table_obj = HashmapCreation.read_files();

		HashmapCreation.sorting(hash_table_obj);


		LLOperation linked_list_obj = new LLOperation();
	


		while (1==1) {
			System.out.println("\n\t\t\t*********Welcome to our search engine*********");
			System.out.println("1.Search for a word\n2.Browser history related operations\n3.Exit");
			System.out.println("\tEnter your choice: ");

			Scanner in = new Scanner(System.in);
			try {
				 int choice = in.nextInt();

			if (choice == 1) {
				search_word(hash_table_obj, linked_list_obj);
			}
			else if (choice == 2) {
				browser_history_operation(linked_list_obj);
			}
			else if (choice == 3) {
				break;
			}
			else {
				System.out.println("!!!!!!! PLEASE GIVE VALID INPUT CHOICE !!!!!!!!!!!!");
			}
			}catch(InputMismatchException e) {
				System.out.println("!!!!!!! PLEASE GIVE VALID INPUT CHOICE !!!!!!!!!!!!");
			}catch(Exception e) {
				System.out.println("Excpeption Occured : "+e);
			}
		}
	}
}
