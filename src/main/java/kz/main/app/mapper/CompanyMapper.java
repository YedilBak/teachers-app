package kz.main.app.mapper;

import kz.main.app.dto.CompanyDto;
import kz.main.app.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(source = "name", target = "companyName")
    CompanyDto toDto(Company company);

    @Mapping(source = "companyName", target = "name")
    Company toModel(CompanyDto companyDto);

    List<CompanyDto> toDtosList(List<Company> companies);

    List<Company> toModelList(List<CompanyDto> companyDtoList);
}
