package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();

        loadCustomers();

        loadVendors();
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
        joeNewman.setFirstname("Joe");
        joeNewman.setLastname("Newman");

        Customer michaelLachapple = new Customer();
        michaelLachapple.setFirstname("Michael");
        michaelLachapple.setLastname("Lachapple");

        Customer davidWinter = new Customer();
        davidWinter.setFirstname("David");
        davidWinter.setLastname("Winter");

        Customer anneHine = new Customer();
        anneHine.setFirstname("Anne");
        anneHine.setLastname("Hine");

        Customer aliceEastman = new Customer();
        aliceEastman.setFirstname("Alice");
        aliceEastman.setLastname("Eastman");

        customerRepository.save(joeNewman);
        customerRepository.save(michaelLachapple);
        customerRepository.save(davidWinter);
        customerRepository.save(anneHine);
        customerRepository.save(aliceEastman);

        System.out.println("Customer data loaded: " + customerRepository.count());
    }

    private void loadVendors(){

        Vendor westernTasty = new Vendor();
        westernTasty.setName("Western Tasty Fruits Ltd");

        Vendor exoticFruits = new Vendor();
        exoticFruits.setName("Exotic Fruits Company");

        Vendor homeFruits = new Vendor();
        homeFruits.setName("Home Fruits");

        Vendor funFresh = new Vendor();
        funFresh.setName("Fun Fresh Fruits Ltd");

        Vendor nutsForNuts = new Vendor();
        nutsForNuts.setName("Nuts for Nuts Company");

        vendorRepository.save(westernTasty);
        vendorRepository.save(exoticFruits);
        vendorRepository.save(homeFruits);
        vendorRepository.save(funFresh);
        vendorRepository.save(nutsForNuts);

        System.out.println("Vendor data loaded: " + vendorRepository.count());

    }
}
