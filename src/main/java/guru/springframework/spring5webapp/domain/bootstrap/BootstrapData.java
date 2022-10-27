package guru.springframework.spring5webapp.domain.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.respositories.AuthorRepository;
import guru.springframework.spring5webapp.respositories.BookRepository;
import guru.springframework.spring5webapp.respositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap!");

        Publisher picador = new Publisher();
        picador.setName("Picador");
        picador.setCity("London");
        picador.setState("UK");

        publisherRepository.save(picador);

        Publisher bloomsbury = new Publisher();
        bloomsbury.setName("Bloomsbury");
        bloomsbury.setCity("London");
        bloomsbury.setState("UK");

        publisherRepository.save(bloomsbury);

        Author cormac = new Author("Cormac", "McCarthy");
        Book bm = new Book("Blood Meridian", "123123");
        cormac.getBooks().add(bm);
        bm.getAuthors().add(cormac);
        bm.setPublisher(picador);
        picador.getBooks().add(bm);

        authorRepository.save(cormac);
        bookRepository.save(bm);
        publisherRepository.save(picador);

        Author susanna = new Author("Susanna", "Clarke");
        Book jnmn = new Book("Jonathan Strange & Mr Norrell", "321321");
        susanna.getBooks().add(jnmn);
        jnmn.getAuthors().add(susanna);
        jnmn.setPublisher(bloomsbury);
        bloomsbury.getBooks().add(jnmn);

        authorRepository.save(susanna);
        bookRepository.save(jnmn);
        publisherRepository.save(bloomsbury);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publisher number of books: " + bloomsbury.getBooks().size());
    }
}
