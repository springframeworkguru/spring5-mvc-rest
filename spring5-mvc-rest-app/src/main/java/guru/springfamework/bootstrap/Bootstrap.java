package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Darcy Xian  21/11/20  11:25 am      spring5-mvc-rest
 */
@Component
@AllArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository vendorRepository;


    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
        loadVendors();

    }

    private void loadCategories() {
        // for category
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded = " + categoryRepository.count());
    }

    private void loadCustomers(){
        // for Customer
        Customer customer = new Customer();
        customer.setFirstname("Lin");
        customer.setLastname("Ken");

        Customer customer1 = new Customer();
        customer1.setFirstname("Jerry");
        customer1.setLastname("Joe");

        customerRepository.save(customer1);
        customerRepository.save(customer);
        System.out.println("Customers Loaded = " + customerRepository.count());
   }

   private void loadVendors(){
       Vendor vendor = new Vendor();
       vendor.setName("Nike");
       vendor.setNickName("ccccc");

       Vendor vendor1 = new Vendor();
       vendor1.setName("Addidass");
       vendor1.setNickName("kkkkkk");

       vendorRepository.save(vendor);
       vendorRepository.save(vendor1);
       System.out.println("Vendors Loaded = " + vendorRepository.count());


   }
}
