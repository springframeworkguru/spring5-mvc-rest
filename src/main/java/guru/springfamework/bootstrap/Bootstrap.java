package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();


        loadCustomers();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Category data loaded: " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer joeNewman = new Customer();
        joeNewman.setFirstName("Joe");
        joeNewman.setLastName("Newman");

        Customer michaelLachapple = new Customer();
        michaelLachapple.setFirstName("Michael");
        michaelLachapple.setLastName("Lachapple");

        Customer davidWinter = new Customer();
        davidWinter.setFirstName("David");
        davidWinter.setLastName("Winter");

        Customer anneHine = new Customer();
        anneHine.setFirstName("Anne");
        anneHine.setLastName("Hine");

        Customer aliceEastman = new Customer();
        aliceEastman.setFirstName("Alice");
        aliceEastman.setLastName("Eastman");

        customerRepository.save(joeNewman);
        customerRepository.save(michaelLachapple);
        customerRepository.save(davidWinter);
        customerRepository.save(anneHine);
        customerRepository.save(aliceEastman);

        System.out.println("Customer data loaded: " + customerRepository.count());
    }
}
