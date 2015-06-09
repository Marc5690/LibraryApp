package ab.cd;

import java.util.Comparator;

public class BookComparatorIsbn implements Comparator<Book>{

	@Override
	public int compare(Book one, Book two) {
		// TODO Auto-generated method stub
		return one.getIsbn() - two.getIsbn();
	}

}
