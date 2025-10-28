package ru.cpm.css.budget.core.repository;

import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.cpm.css.budget.core.model.entity.dictionary.CompanyUnitEntity;
import ru.cpm.css.budget.core.repository.fragments.FragmentHandler;
import ru.cpm.css.budget.core.repository.fragments.FragmentPageableFilterExtractor;
import ru.cpm.css.budget.service.repository.fragments.extractors.CompanyUnitPageableFilterExtractor;

@Repository
public interface CompanyUnitRepository extends AbstractRepository<CompanyUnitEntity, UUID, String>,
    FragmentHandler<FragmentPageableFilterExtractor<CompanyUnitEntity, String>> {


  Mono<CompanyUnitEntity> findByCodeIgnoreCase(String code);

  @Override
  default Mono<Long> count(String filter) {
    return getExtractor(CompanyUnitPageableFilterExtractor.class).countByFilter(filter);
  }

  @Override
  default Flux<CompanyUnitEntity> findAllBy(String filter, Pageable pageable) {
    return getExtractor(CompanyUnitPageableFilterExtractor.class).findAllByFilter(filter, pageable);
  }
}
