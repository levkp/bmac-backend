package com.bmac.warehouse.ports.out;

import com.bmac.warehouse.domain.Sector;

public interface SectorCreatePort {
    void create(Sector sector);
}
