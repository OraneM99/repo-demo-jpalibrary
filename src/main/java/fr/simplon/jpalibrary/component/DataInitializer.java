package fr.simplon.jpalibrary.component;

import fr.simplon.jpalibrary.model.Book;
import fr.simplon.jpalibrary.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            System.out.println("Initialisation de la base de données ...");

            Book book1 = new Book("Le Seigneur des Anneaux", "Une quête épique en Terre du Milieu.");
            Book book2 = new Book("1984", "Un roman dystopique sur la surveillance de masse.");
            Book book3 = new Book("Le Petit Prince", "Un conte poétique et philosophique.");
            Book book4 = new Book("Clean Code", "Manuel de savoir-faire pour les développeurs.");

            bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4));

            System.out.println("Base de donnéess intialisée avec succès.");
        } else {
            System.out.println("Base de données non initialisée ...");
        }
    }
}
