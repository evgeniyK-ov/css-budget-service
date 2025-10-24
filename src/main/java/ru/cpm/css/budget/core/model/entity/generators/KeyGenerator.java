package ru.cpm.css.budget.core.model.entity.generators;

import java.util.UUID;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.cpm.common.css.model.KeyIdentifier;

@Component
public class KeyGenerator implements BeforeConvertCallback<KeyIdentifier> {

  @Override
  public Publisher<KeyIdentifier> onBeforeConvert(KeyIdentifier entity, SqlIdentifier table) {
    if (entity.getId() == null) {
      entity.setId(UUID.randomUUID());
    }
    return Mono.just(entity);
  }
}
