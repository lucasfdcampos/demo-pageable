package br.com.demo.service;

import br.com.demo.model.Customer;
import br.com.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void save(Customer customer) {
        this.customerRepository.save(customer);
    }

    public void update(Long id, Customer customer) {
        customer.setId(id);
        this.customerRepository.save(customer);
    }

    public void delete(Long id) {
        this.customerRepository.delete(this.customerRepository.getOne(id));
    }

    @Transactional(readOnly = true)
    public Customer findById(Long id) {
        return this.customerRepository.getOne(id);
    }

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Customer> search(String searchTerm, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        return customerRepository.search(searchTerm.toLowerCase(), pageRequest);
    }

    @Transactional(readOnly = true)
    public Page<Customer> findAllPagination() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "name");
        return new PageImpl<>(customerRepository.findAll(), pageRequest, size);
    }
}
