package com.bmac.warehouse.ports;

import com.bmac.warehouse.domain.Sector;
import org.springframework.context.annotation.Profile;

@Profile("seed")
public interface SectorCreatePort {
    void create(Sector sector);
}
