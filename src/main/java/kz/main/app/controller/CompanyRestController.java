package kz.main.app.controller;

import kz.main.app.entity.Company;
import kz.main.app.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyRestController {

    private final CompanyRepository companyRepository;

    @GetMapping(value = "/all")
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    @GetMapping(value = "/get-id/{id}")
    public Company getCompanyById(@PathVariable int id){
        return companyRepository.findById(id).get();
    }

    @PostMapping(value = "/add-company")
    public Company addCompany(@RequestBody Company company){

        System.out.println(company);

        return companyRepository.save(company);
    }

    @PutMapping(value = "/update")
    public void updateCompany(@RequestBody Company company){
        companyRepository.save(company);
    }

    @DeleteMapping(value = "/delete")
    public void deleteCompany(@RequestParam int id){
        companyRepository.deleteById(id);
    }


}
