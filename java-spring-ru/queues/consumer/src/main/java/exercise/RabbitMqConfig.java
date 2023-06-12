package exercise;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // BEGIN
    // Объявление очереди с именем "queue" по аналогии с приложением-источником
    @Bean
    Queue queue() {
        return new Queue("queue", false);
    }
    // END
}
