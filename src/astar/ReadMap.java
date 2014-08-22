/**
 * 
 */
package astar;

import java.io.*;


/**
 * @author agonzales
 *
 */
public class ReadMap {

	private static void readMap(File fin) throws IOException {
		// Construct BufferedReader from FileReader
		BufferedReader br = new BufferedReader(new FileReader(fin));
	 
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	 
		br.close();
	}
	public static void main(String[] args){
		File f = new File("AStar.java");
		try {
			readMap(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
