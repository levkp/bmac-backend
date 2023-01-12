package com.bmac.factory.adapters.out.jpa.recipe;

import org.hibernate.annotations.MapKeyType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "fac_recipes")
public class RecipeJpaEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID id;

    @Type(type = "uuid-char")
    @Column(nullable = false, unique = true)
    private UUID productId;

    @ElementCollection
    @CollectionTable(name = "fac_recipeingredients",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"))
    @Column(nullable = false, name = "quantity")
    @MapKeyColumn(name = "ingredient_id")
    @MapKeyType(@Type(type = "uuid-char"))
    private Map<UUID, Double> ingredients = new HashMap<>();

    public RecipeJpaEntity(UUID id, UUID productId, Map<UUID, Double> ingredients) {
        this.id = id;
        this.productId = productId;
        this.ingredients = ingredients;
    }

    protected RecipeJpaEntity() { }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public Map<UUID, Double> getIngredients() {
        return ingredients;
    }
}
