package gamesate;

import java.util.ArrayList;
import java.util.List;

public class PictureMod {

	PictureMod (){
	
	}
	/**
	 * 
	 * @param pic
	 * @param x
	 * @param y
	 * @return
	 */
	public List getPixelVal(Picture pic, int x, int y){
		int red = pic.getRed(x, y);
		int green = pic.getGreen(x,y);
		int blue = pic.getBlue(x, y);
		List pixels = new List();
		pixels.add(red,green,blue);
		
		return pixels;
	}
	
	/**
	 * will iterate over a range in the picture to get values
	 * @param start
	 * @param stop
	 * @return
	 */
	public ArrayList iteratePixels(int startx, int stopx, int starty, int stopy){
		ArrayList al = new ArrayList(); 
				
		return al;
	}
	
	
	public static void main(String[] args) {
		System.out.println("testing pic class");
		
		Picture pic = new Picture();
		
		//test blue values
		int red = pic.getRed(0, 0);
		int blue = pic.getBlue(0, 0);
		int green = pic.getGreen(0,0);
		
		System.out.println("blue = " + blue);
		System.out.println("red = " + red);
		System.out.println("green = " + green);
		
		System.out.println(getPixelVal(pic, 10,10));
		
		
		
	}

}
