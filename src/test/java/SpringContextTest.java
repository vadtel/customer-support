import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.vadtel.support.config.ApplicationContextConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationContextConfig.class})
@WebAppConfiguration
public class SpringContextTest {

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }
}

