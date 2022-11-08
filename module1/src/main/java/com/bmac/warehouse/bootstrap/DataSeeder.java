package com.bmac.warehouse.bootstrap;

import com.bmac.warehouse.domain.Sector;
import com.bmac.warehouse.domain.Temperature;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("seed")
public class DataSeeder implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Sector s1 = new Sector(Temperature.DRY, 10);
        Sector s2 = new Sector(Temperature.DRY, 20);
        Sector s3 = new Sector(Temperature.DRY, 20);
        Sector s4 = new Sector(Temperature.COOLED, 10);
        Sector s5 = new Sector(Temperature.COOLED, 10);
        Sector s6 = new Sector(Temperature.REFRIGERATED, 20);
    }
}
