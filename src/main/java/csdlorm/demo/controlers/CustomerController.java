package csdlorm.demo.controlers;

import csdlorm.demo.model.Customer;
import csdlorm.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public String list(Model model){
        model.addAttribute("customers",customerService.findAll());
        return "/customer/list";
    }

    @GetMapping("create")
    public String showCreateForm(Model model){
        model.addAttribute("customer", new Customer());
        return "/customer/create";
    }

    @PostMapping("/create")
    public String saveCustomer(Customer customer){
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit-customer/{id}")
    public String showFormEdit(@PathVariable Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);

        return "/customer/edit";
    }

    @PostMapping("/edit-customer")
    public String update(Customer customer){
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete-customer/{id}")
    public String delete(@PathVariable Long id){

        customerService.remove(id);
        return "redirect:/customers";
    }













}
