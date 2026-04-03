package kz.main.app.controller;

import kz.main.app.dto.CompanyDto;
import kz.main.app.entity.Company;
import kz.main.app.mapper.CompanyMapper;
import kz.main.app.repository.CompanyRepository;
import kz.main.app.service.impl.CompanyServiceImpl;
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
    private final CompanyServiceImpl companyService;
    private final CompanyMapper mapper;

    @GetMapping(value = "/all")
    public List<CompanyDto> getAllCompanies(){
        return mapper.toDtosList(companyRepository.findAll());
    }

    @GetMapping(value = "/get-id/{id}")
    public CompanyDto getCompanyById(@PathVariable int id){
        return mapper.toDto(companyRepository.findById(id).get());
    }

    @PostMapping(value = "/add-company")
    public CompanyDto addCompany(@RequestBody CompanyDto company){

        Company companyModel =  companyRepository.save(mapper.toModel(company));

        return mapper.toDto(companyModel);
    }

    @PutMapping(value = "/update")
    public void updateCompany(@RequestBody CompanyDto company){

      companyRepository.save(mapper.toModel(company));

    }

    @DeleteMapping(value = "/delete")
    public void deleteCompany(@RequestParam int id){
        companyRepository.deleteById(id);
    }


}
