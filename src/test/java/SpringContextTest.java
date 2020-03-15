import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vadtel.support.dao.config.RepositoryConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration( classes = {RepositoryConfiguration.class},
        loader = AnnotationConfigContextLoader.class)
@WebAppConfiguration
@DirtiesContext
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}

