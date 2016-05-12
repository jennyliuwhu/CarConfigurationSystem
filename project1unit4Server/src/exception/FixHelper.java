/**
 * Name=Jialing Liu
 * AndrewId=jialingl
 * Thanks for grading me! :)
 */
package exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class FixHelper {

	// fix missing price exception, errno = 1
	public float fix1(int errno) {
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Write a number: ");
		try{
			// read from user's input
			s = br.readLine();
			// validate if s is a number 
			// if so we skip catch call and return its float form, otherwise, catch the exception
			float newPrice = Float.parseFloat(s);
			return newPrice;
		}
		catch(Exception e){
			// call this class again, so user can try it again
			return fix1(errno);
		}
	}

	// fix missing OptionSet name exception, errno = 2
	public String fix2(int errno) {
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Give an OptionSet name: ");
		try{
			// read from user's input
			s = br.readLine();
			if (s.length() == 0) {
				throw new Exception();
			}
			return s;
		}
		catch(Exception e){
			// call this class again, so user can try it again
			return fix2(errno);
		}
	}

	// fix missing Option name exception, errno = 3
	public String fix3(int errno) {
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Give an Option name: ");
		try{
			// read from user's input
			s = br.readLine();
			if (s.length() == 0) {
				throw new Exception();
			}
			return s;
		}
		catch(Exception e){
			// call this class again, so user can try it again
			return fix3(errno);
		}
	}

	// fix missing filename or wrong filename exception, errno = 4
	public String fix4(int errno) {
		String filename;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Give a validated filename: ");
		try{
			// read from user's input
			filename = br.readLine();
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			return filename;
		}
		catch(Exception e){
			// call this class again, so user can try it again
			return fix4(errno);
		}
	}

	// fix wrong type for option price exception, errno = 5
	public float fix5(int errno) {
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Write a number: ");
		try{
			// read from user's input
			s = br.readLine();
			// validate if s is a number 
			// if so we skip catch call and return its float form, otherwise, catch the exception
			float newPrice = Float.parseFloat(s);
			return newPrice;
		}
		catch(Exception e){
			// call this class again, so user can try it again
			return fix5(errno);
		}
	}
}