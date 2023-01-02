package com.bmac.warehouse.bootstrap;

import com.bmac.warehouse.adapters.exception.WarehouseEntityConstraintException;
import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.domain.Sector;
import com.bmac.warehouse.domain.Shelf;
import com.bmac.warehouse.domain.Temperature;
import com.bmac.warehouse.ports.in.item.CreateItemCommand;
import com.bmac.warehouse.ports.in.item.CreateItemUseCase;
import com.bmac.warehouse.ports.in.stock.ChangeStockCommand;
import com.bmac.warehouse.ports.in.stock.ChangeStockUseCase;
import com.bmac.warehouse.ports.out.sector.SectorCreatePort;
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
    private final ChangeStockUseCase changeStock;
    private final SectorCreatePort sectorCreator;

    @Autowired
    public DataSeeder(CreateItemUseCase createItem, ChangeStockUseCase changeStock, SectorCreatePort sectorCreator) {
        this.createItem = createItem;
        this.changeStock = changeStock;
        this.sectorCreator = sectorCreator;
    }

    @Override
    public void run(String... args) {
        log.info("Seeding database");

        Faker faker = new Faker();
        Random random = new Random();
        List<Item> dryItems = new ArrayList<>();
        List<Item> cooledItems = new ArrayList<>();
        List<Item> refrigeratedItem = new ArrayList<>();

        Temperature temperature = Temperature.DRY;
        List<Item> list = dryItems;

        for (int i = 0; i < 150; i++) {
            if (i == 50) {
                temperature = Temperature.COOLED;
                list = cooledItems;
            } else if (i == 100) {
                temperature = Temperature.REFRIGERATED;
                list = refrigeratedItem;
            }

            try {
                list.add(createItem.create(new CreateItemCommand(
                        faker.food().ingredient(),
                        random.nextInt(5) / 10.0,
                        random.nextInt(5) / 10.0,
                        temperature,
                        Item.Unit.values()[random.nextInt(Item.Unit.values().length)],
                        random.nextInt(5, 60))
                ));
            } catch (WarehouseEntityConstraintException exception) {
                /* This exception will occur because there is a unique constraint on the name field of an Item, and it's
                 * possible that the JavaFaker library will return the same values multiple times. There will be fewer
                 * items created than declared in the loop header, but that's okay.
                 */
            }
        }

        List<Sector> sectors = List.of(
                new Sector("DRY.A", Temperature.DRY),
                new Sector("DRY.B", Temperature.DRY),
                new Sector("DRY.C", Temperature.DRY),
                new Sector("COOL.A", Temperature.COOLED),
                new Sector("COOL.B", Temperature.COOLED),
                new Sector("REF.A", Temperature.REFRIGERATED)
        );

        Map<Temperature, List<Item>> itemsByTemperature = Map.of(
                Temperature.DRY, dryItems, Temperature.COOLED, cooledItems, Temperature.REFRIGERATED, refrigeratedItem);

        for(Sector sector : sectors) {
            for(int i = 0; i < 5; i++) {
                sector.addShelf(new Shelf(sector.getId().concat(String.valueOf(i))));
            }
            sectorCreator.create(sector);

            for(Shelf shelf : sector.getShelves()) {
                List<Item> items = itemsByTemperature.get(sector.getTemperature());
                UUID itemId = items.get(random.nextInt(items.size())).getId();
                changeStock.add(new ChangeStockCommand(shelf.getId(), itemId, random.nextInt(100) / 10.0));
            }
        }
    }
}
