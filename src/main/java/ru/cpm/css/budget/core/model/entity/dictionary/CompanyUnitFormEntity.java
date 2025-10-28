package ru.cpm.css.budget.core.model.entity.dictionary;


import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;
import ru.cpm.common.css.model.KeyIdentifier;
import ru.cpm.common.css.model.TypeForm;

@NoArgsConstructor
@Getter
@Setter
@Table("company_unit_form")
@FieldNameConstants
public class CompanyUnitFormEntity implements KeyIdentifier {

  @Id
  private UUID id;
  private UUID companyUnitId;
  @Transient
  private CompanyUnitEntity companyUnit;
  private String code;
  private String name;
  private TypeForm typeForm;
  private LocalDate activeFrom;
  private LocalDate activeTo;
  private String description;
}