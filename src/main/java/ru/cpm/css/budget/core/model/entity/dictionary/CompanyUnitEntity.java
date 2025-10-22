package ru.cpm.css.budget.core.model.entity.dictionary;


import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@Getter
@Setter
@Table("company_unit")
@FieldNameConstants
public class CompanyUnitEntity {

  private UUID id;
  private String code;
  private String name;
  private String description;
}
