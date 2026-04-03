package kz.main.app.service.impl;

import kz.main.app.dto.CompanyDto;
import kz.main.app.entity.Company;
import kz.main.app.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl {

    private final CompanyRepository companyRepository;


    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company getCompanyById(int id){
        return companyRepository.findById(id).orElseThrow();
    }


    public CompanyDto toDto(Company company){
        CompanyDto dto = new CompanyDto();

        dto.setId(company.getId());
        dto.setCompanyName(company.getName());
        dto.setRating(company.getRating());
        dto.setCountTeachers(company.getCountTeachers());

        return dto;
    }

    public List<CompanyDto> toDtosList(List<Company> companies){

        List<CompanyDto> companyDtoList = new ArrayList<>();

        for(Company company: companies){
            companyDtoList.add(toDto(company));
        }

        return companyDtoList;
    }

}
