package ab.cd;

import java.util.Comparator;

public class BookComparatorTitle implements Comparator<Book>{

		@Override
		public int compare(Book one, Book two) {
			// TODO Auto-generated method stub
			return one.getTitle().toLowerCase().compareTo(two.getTitle().toLowerCase());
		}

		
}
