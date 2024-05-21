package NationalCountries.services.QueueService;

import NationalCountries.config.RabbitMQConfig;
import NationalCountries.dto.WeatherInfoDTO;
import NationalCountries.services.ExternalCountriesService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WeatherRequestListenerService {

    @Autowired
    private ExternalCountriesService externalCountriesService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.REQUEST_QUEUE)
    public void handleWeatherRequest(@Payload String countryName) {
        try {
            System.out.println("Received request for country: " + countryName);
            WeatherInfoDTO weatherInfo = externalCountriesService.fetchWeatherForCapital(countryName);
            rabbitTemplate.convertAndSend(RabbitMQConfig.RESPONSE_QUEUE, weatherInfo);
            System.out.println("Processed and sent weather info for country: " + countryName);
        } catch (Exception e) {
            System.out.println("Error processing weather request for country: " + countryName);
        }
    }
}
