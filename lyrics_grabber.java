import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class lyrics_grabber 
{
	private static String artistName;
	private static String songTitle;
	private static int startOfLyrics;
	private static int endOfLyrics;
	private static String choice;
	public static void main(String[] args) throws IOException
	{
		/**
		 * getting artist name and song name
		 */
		Scanner in = new Scanner(System.in);
		System.out.println("Artist Name: ");
		artistName = in.nextLine();
		System.out.println("Song name in full: ");
		songTitle = in.nextLine();

		/**
		 * Making the URL to the specified song
		 */
		URL website = new URL("https://www.azlyrics.com/lyrics/" + artistName.toLowerCase().replaceAll(" ", "") + "/" + songTitle.toLowerCase().replaceAll(" ", "") + ".html");
		/**
		 * getting the input from the website
		 */
		Scanner fromWebsite = new Scanner(website.openStream());
		StringBuffer stringBuffer = new StringBuffer();
		/**
		 * getting the input of it as a String 
		 */
		while(fromWebsite.hasNext())
		{
			stringBuffer.append(fromWebsite.next() + " ");
		}
		String result = stringBuffer.toString();
		result = result.replaceAll("<br>", "\n");
		result = result.replaceAll("<[^>]*>", "");
		startOfLyrics = result.indexOf(artistName + " Lyrics     ");
		endOfLyrics = result.indexOf("if ( /Android|");
		System.out.print(result.substring(startOfLyrics,endOfLyrics));
		/**
		 * Creating a .txt if one doesnt already exist
		 */
		File newTextFile = new File(artistName + "_" + songTitle + ".txt");
		/**
		 * asking user if they want to save it as a .txt
		 */
		System.out.print("Save as .txt?[y,n]: ");
		choice = in.nextLine();
		if(choice.equals("y"))
		{
			if(newTextFile.createNewFile())
			{
				FileWriter writeInside = new FileWriter(newTextFile.getName());
				writeInside.write(result.substring(startOfLyrics,endOfLyrics));
				System.out.print("Saved as " + newTextFile.getName());
				writeInside.close();
			}
			else
			{
				System.out.print("File already exists man :-(");
			}
		}
		else
		{
			System.out.print("Exiting program.");
		}
	}
}
