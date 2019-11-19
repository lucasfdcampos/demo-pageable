package br.com.demo.controller;

import br.com.demo.model.Customer;
import br.com.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping(value = "/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        this.customerService.save(customer);
        return customer;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable("id") Long id) {
        return customerService.findById(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        this.customerService.update(id, customer);
        return customer;
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<String> deleteCustomer(@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return new ResponseEntity<String>("DELETED", HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findAll() {
        return this.customerService.findAll();
    }

    @GetMapping(value = "/list-pagination")
    @ResponseStatus(HttpStatus.OK)
    public Page<Customer> findAllPagination() {
        return this.customerService.findAllPagination();
    }

    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<Customer> search(@RequestParam("searchTerm") String searchTerm,
                                 @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        return this.customerService.search(searchTerm, page, size);
    }
}
