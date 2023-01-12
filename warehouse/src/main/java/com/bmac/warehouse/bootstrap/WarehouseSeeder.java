package com.bmac.warehouse.bootstrap;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.exception.EntityConstraintException;
import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.domain.Sector;
import com.bmac.warehouse.domain.Shelf;
import com.bmac.warehouse.ports.in.stock.ChangeStockCommand;
import com.bmac.warehouse.ports.in.stock.ChangeStockUseCase;
import com.bmac.warehouse.ports.in.item.CreateItemCommand;
import com.bmac.warehouse.ports.in.item.CreateItemUseCase;
import com.bmac.warehouse.ports.out.common.LoadCommonIngredientPort;
import com.bmac.warehouse.ports.out.item.LoadItemPort;
import com.bmac.warehouse.ports.out.sector.SectorCreatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Order(2)
public class WarehouseSeeder implements CommandLineRunner {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final Environment env;
    private final LoadCommonIngredientPort commonIngredientLoader;
    private final LoadItemPort itemLoader;
    private final CreateItemUseCase createItem;
    private final ChangeStockUseCase changeStock;
    private final SectorCreatePort sectorCreator;

    public WarehouseSeeder(Environment env,
                           LoadCommonIngredientPort commonIngredientLoader,
                           LoadItemPort itemLoader,
                           CreateItemUseCase createItem,
                           ChangeStockUseCase changeStock,
                           SectorCreatePort sectorCreator) {
        this.env = env;
        this.commonIngredientLoader = commonIngredientLoader;
        this.itemLoader = itemLoader;
        this.createItem = createItem;
        this.changeStock = changeStock;
        this.sectorCreator = sectorCreator;
    }

    private void syncItems() {
        log.info("Syncing items from factory");
        Random random = new Random();

        try {
            commonIngredientLoader.loadAll().forEach(commonIngredient -> createItem.create(new CreateItemCommand(
                    commonIngredient.id,
                    commonIngredient.name,
                    random.nextInt(10, 50) / 10.0,
                    random.nextInt(50, 100) / 10.0,
                    commonIngredient.temperature,
                    commonIngredient.unit,
                    random.nextInt(5, 31))));
        } catch (EntityConstraintException exception) {
            // Todo: this shouldn't happen here!
        }
    }

    private void fakeSectorsAndShelvesAndStock() {
        log.info("Faking sectors, shelves and stock");

        Random random = new Random();
        List<Sector> sectors = List.of(
                new Sector("DRY.A", IngredientTemperature.DRY),
                new Sector("DRY.B", IngredientTemperature.DRY),
                new Sector("DRY.C", IngredientTemperature.DRY),
                new Sector("COOL.A", IngredientTemperature.COOLED),
                new Sector("COOL.B", IngredientTemperature.COOLED),
                new Sector("REF.A", IngredientTemperature.REFRIGERATED)
        );
        Map<IngredientTemperature, List<Item>> itemsByTemperature = Map.of(
                IngredientTemperature.DRY, itemLoader.loadAllByTemperature(IngredientTemperature.DRY),
                IngredientTemperature.COOLED, itemLoader.loadAllByTemperature(IngredientTemperature.COOLED),
                IngredientTemperature.REFRIGERATED, itemLoader.loadAllByTemperature(IngredientTemperature.REFRIGERATED)
        );

        for (Sector sector : sectors) {
            for (int i = 0; i < 5; i++) {
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

    @Override
    public void run(String... args) {
        if (Arrays.asList(env.getActiveProfiles()).contains("seed")) {
            syncItems();
            fakeSectorsAndShelvesAndStock();
        }
    }
}
