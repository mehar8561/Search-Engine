/*
This class HashmapCreation creates the quadratic hash table with keys being the
words and values being another hash table. The values hash table consist of URL
name along with its frequency for the key(words).
*/

import java.util.*;

import algorithms.QPHashTable2;
import algorithms.QPHashTable2.HashEntry;

import java.io.*;


public class HashmapCreation {

	public static QPHashTable2<String> QH = new QPHashTable2<>();
	public static QPHashTable2 read_files() throws FileNotFoundException {

		String folder_path = "W3C_Web_Pages/Text3/";
		File folder = new File(folder_path);

	
		for (final File fileEntry : folder.listFiles()) {
			String path = folder_path + fileEntry.getName();
			File file = new File(path);
			Scanner scanner = new Scanner(file);

		
			while (scanner.hasNextLine()) {
				Scanner s2 = new Scanner(scanner.nextLine());


				while (s2.hasNext()) {
		            String s = s2.next().toLowerCase();
		            QH.insert(s, fileEntry.getName());

		        }

			}
			}


		return QH;
	}

	public static ArrayList merge_sort(Map<String, Integer> table){


	       ArrayList<Map.Entry<?, Integer>> sorted_list = new ArrayList(table.entrySet());
	       Collections.sort(sorted_list, new Comparator<Map.Entry<?, Integer>>(){

	         public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
	            return o2.getValue().compareTo(o1.getValue());
	        }});

	       return sorted_list;
	    }

	public static void sorting(QPHashTable2 obj) {

		
		HashEntry[] a = obj.array;

	
		ArrayList b = obj.index_arr;

		for (int i = 0 ; i < b.size(); i++) {
			Map arr = a[(int) b.get(i)].table;
			ArrayList l =merge_sort(arr);
			
			a[(int) b.get(i)].display_file =  l;
		}

	}

}
