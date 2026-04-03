package kz.main.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Integer id;

    private String companyName;

    private int countTeachers;

    private int rating;
}
