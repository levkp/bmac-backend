package com.bmac.warehouse.adapters.out.jpa.item;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.IngredientUnit;
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
    private IngredientTemperature temperature;

    @Enumerated(EnumType.STRING)
    private IngredientUnit unit;

    @Column(nullable = false)
    private int expiryDays;


    public ItemJpaEntity(UUID id, String name, double minimum, double maximum, IngredientTemperature temperature, IngredientUnit unit) {
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    public IngredientTemperature getTemperature() {
        return temperature;
    }

    public void setTemperature(IngredientTemperature temperature) {
        this.temperature = temperature;
    }

    public IngredientUnit getUnit() {
        return unit;
    }

    public void setUnit(IngredientUnit unit) {
        this.unit = unit;
    }

    public int getExpiryDays() {
        return expiryDays;
    }

    public void setExpiryDays(int expiryDays) {
        this.expiryDays = expiryDays;
    }
}
