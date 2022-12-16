package com.bmac.warehouse.bootstrap;

import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.domain.Temperature;
import com.bmac.warehouse.ports.in.CreateItemCommand;
import com.bmac.warehouse.ports.in.CreateItemUseCase;
import com.bmac.warehouse.ports.out.CreateItemPort;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Profile("seed")
public class DataSeeder implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CreateItemUseCase createItem;

    @Autowired
    public DataSeeder(CreateItemUseCase createItem) {
        this.createItem = createItem;
    }

    @Override
    public void run(String... args) {
        log.info("Seeding database");

        Faker faker = new Faker();
        Random random = new Random();

        List<Item> dryItems = new ArrayList<>();
        List<Item> cooledItems = new ArrayList<>();
        List<Item> refrigeratedItem = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            dryItems.add(createItem.create(new CreateItemCommand(
                    faker.food().ingredient(),
                    random.nextDouble(5.0),
                    random.nextDouble(50.0),
                    Temperature.DRY,
                    Item.Unit.values()[random.nextInt(Item.Unit.values().length)])
            ));
        }

        for (int i = 0; i < 40; i++) {
            cooledItems.add(createItem.create(new CreateItemCommand(
                    faker.food().ingredient(),
                    random.nextDouble(3.0),
                    random.nextDouble(40.0),
                    Temperature.COOLED,
                    Item.Unit.values()[random.nextInt(Item.Unit.values().length)])
            ));
        }

        for (int i = 0; i < 40; i++) {
            refrigeratedItem.add(createItem.create(new CreateItemCommand(
                    faker.food().ingredient(),
                    random.nextDouble(3.0),
                    random.nextDouble(40.0),
                    Temperature.DRY,
                    Item.Unit.values()[random.nextInt(Item.Unit.values().length)])
            ));
        }



//        Map<Temperature, List<Item>> items = Map.of(
//                Temperature.DRY, dryItems, Temperature.COOLED, cooledItems, Temperature.REFRIGERATED, refrigeratedItem);

//        List<Sector> sectors = List.of(
//                new Sector(Temperature.DRY, 15),
//                new Sector(Temperature.DRY, 15),
//                new Sector(Temperature.COOLED, 20),
//                new Sector(Temperature.REFRIGERATED, 10)
//        );
//
//        for(int i = 0; i < 100; i++) {
//            dryItems.add(new Item(
//                    faker.food().ingredient(),
//                    random.nextDouble(5.0),
//                    random.nextDouble(100.0),
//                    Temperature.DRY,
//                    Item.Unit.values()[random.nextInt(Item.Unit.values().length)]));
//
//            cooledItems.add(new Item(
//                    faker.food().ingredient(),
//                    random.nextDouble(5.0),
//                    random.nextDouble(100.0),
//                    Temperature.DRY,
//                    Item.Unit.values()[random.nextInt(Item.Unit.values().length)]));
//
//            refrigeratedItem.add(new Item(
//                    faker.food().ingredient(),
//                    random.nextDouble(5.0),
//                    random.nextDouble(100.0),
//                    Temperature.DRY,
//                    Item.Unit.values()[random.nextInt(Item.Unit.values().length)]));
//        }
//
//        // Let's hope this won't take too long
//        for (Sector sector : sectors) {
//            for(int i = 0; i < random.nextInt(3, 10); i++) {
//                List<Stock> shelfContent = new ArrayList<>();
//                for(int j = 0; j < random.nextInt(5, 100); j++) {
//                    shelfContent.add(new Stock(
//                            items.get(sector.getTemperature()).get(random.nextInt(items.size())),
//                            sector.getShelves().get(random.nextInt(sector.getShelves().size())),
//                            LocalDate.ofEpochDay(random.nextLong(LocalDate.now().toEpochDay(), LocalDate.MAX.toEpochDay())),
//                            random.nextDouble(0.0, 100.0)
//                    ));
//                }
//                sector.addShelf(shelfContent);
//            }
//        }
    }
}
