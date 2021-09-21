/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market;

import com.sales.market.model.*;
import com.sales.market.repository.BuyRepository;
import com.sales.market.service.*;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

@Component
public class DevelopmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final PositionService positionService;

    // injeccion evita hacer instancia   = new Clase();
    // bean pueden tener muchos campos y otros beans asociados

    public DevelopmentBootstrap(PositionService positionService) {
        this.positionService = positionService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("evento de spring");
       persistPositions();
    }



    private String getResourceAsString(String resourceName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private InputStream getResourceAsStream(String resourceName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            return inputStream;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void persistPositions() {
        positionService.save(new Position("Gerente"));
        positionService.save(new Position("Cajero"));
        positionService.save(new Position("Acomodador"));
        positionService.save(new Position("Almacenero"));
    }
}
