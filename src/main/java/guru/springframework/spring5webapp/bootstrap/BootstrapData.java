package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.respositories.AuthorRepository;
import guru.springframework.spring5webapp.respositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author cormac = new Author("Cormac", "McCarthy");
        Book bm = new Book("Blood Meridian", "123123");
        cormac.getBooks().add(bm);
        bm.getAuthors().add(cormac);

        authorRepository.save(cormac);
        bookRepository.save(bm);

        Author susanna = new Author("Susanna", "Clarke");
        Book jnmn = new Book("Jonathan Strange & Mr Norrell", "321321");
        susanna.getBooks().add(jnmn);
        jnmn.getAuthors().add(susanna);

        authorRepository.save(susanna);
        bookRepository.save(jnmn);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
    }
}
