package ru.cpm.ufs.css.dictionary;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.cpm.css.budget.Application;
import ru.cpm.ufs.css.dictionary.configuration.DatabaseConfiguration;
import ru.cpm.ufs.css.dictionary.configuration.DatabaseDockerConfig;

@AutoConfigureWebTestClient(timeout = "60000")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {Application.class,
    DatabaseDockerConfig.class,
    DatabaseConfiguration.class}
)
@ActiveProfiles("internal")
public abstract class AbstractCommonContext {


}
