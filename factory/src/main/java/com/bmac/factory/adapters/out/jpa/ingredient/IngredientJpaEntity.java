package com.bmac.factory.adapters.out.jpa.ingredient;

import com.bmac.common.IngredientTemperature;
import com.bmac.common.IngredientUnit;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "fac_ingredients")
public class IngredientJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IngredientUnit unit;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private IngredientTemperature temperature;

    public IngredientJpaEntity(UUID id, String name, IngredientUnit unit, IngredientTemperature temperature) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.temperature = temperature;
    }

    protected IngredientJpaEntity() { }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IngredientUnit getUnit() {
        return unit;
    }

    public IngredientTemperature getTemperature() {
        return temperature;
    }
}
