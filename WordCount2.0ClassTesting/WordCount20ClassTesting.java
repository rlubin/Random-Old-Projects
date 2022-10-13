// package wordcount2.pkg0classtesting;

public class WordCount20ClassTesting {

	public static void main(String[] args) {

		System.out.println("-----Testing StringAnalyzer Class-----");
		String string = "The brown lazy dog jumped and killed the odd blue cat. 12 men said that she exploded! \"qwwqeewr\"";
		System.out.println(string);
		StringAnalyzer sa = new StringAnalyzer();
		sa.setString(string);
		int size = sa.size();
		/*
		 * System.out.println("String\n" + string);
		 * System.out.println("Number of unique words - " + size);
		 * System.out.println("\nList"); sa.printList(); System.out.println("\nMap");
		 * sa.printMap(); System.out.println("Analysis in string format");
		 * System.out.println(sa.getString());
		 */
		System.out.println("-----Done Testing StringAnalyzer Class-----");
	}

}
