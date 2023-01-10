package com.bmac.warehouse.adapters.out.jpa.item;

import com.bmac.warehouse.domain.Item;
import com.bmac.warehouse.domain.Temperature;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "wh_items", schema = "fruit_db")
public class ItemJpaEntity {

    @Id
    @Type(type = "uuid-char")
    @Column(name = "id")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    private double minimum;

    private double maximum;

    @Enumerated(EnumType.STRING)
    private Temperature temperature;

    @Enumerated(EnumType.STRING)
    private Item.Unit unit;

    @Column(nullable = false)
    private int expiryDays;


    public ItemJpaEntity(UUID id, String name, double minimum, double maximum, Temperature temperature, Item.Unit unit) {
        this.id = id;
        this.name = name;
        this.minimum = minimum;
        this.maximum = maximum;
        this.temperature = temperature;
        this.unit = unit;
    }

    protected ItemJpaEntity() {

    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMinimum() {
        return minimum;
    }

    public double getMaximum() {
        return maximum;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Item.Unit getUnit() {
        return unit;
    }

    public int getExpiryDays() {
        return expiryDays;
    }

    public void setExpiryDays(int expiryDays) {
        this.expiryDays = expiryDays;
    }
}
