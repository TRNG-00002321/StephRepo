import java.util.Scanner;

public class ScannerDemo
{
	public static void main(String []args)
	{
		Scanner scanner = new Scanner(System.in);
		String firstName;
		System.out.println("Enter your name: ");
		firstName = scanner.next();
		System.out.println("Hello, " + firstName);
	}
}