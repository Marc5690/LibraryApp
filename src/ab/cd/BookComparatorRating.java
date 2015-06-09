package ab.cd;

import java.util.Comparator;

public class BookComparatorRating implements Comparator<Book>{

	@Override
	public int compare(Book one, Book two) {
		// TODO Auto-generated method stub
		return two.getRating() - one.getRating();
	}

}
