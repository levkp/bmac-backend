package com.bmac.warehouse.adapters.out.jpa.shelf;

import com.bmac.warehouse.domain.ShelfActivity;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "wh.shelf_activities")
public class ShelfActivityJpaEntity {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String shelfId;

    @Type(type = "uuid-char")
    @Column(nullable = false)
    private UUID itemId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShelfActivity.Action action;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    public ShelfActivityJpaEntity(String shelfId, UUID itemId, ShelfActivity.Action action, double amount, LocalDateTime timestamp) {
        this.shelfId = shelfId;
        this.itemId = itemId;
        this.action = action;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    protected ShelfActivityJpaEntity() { }

    public UUID getId() {
        return id;
    }

    public String getShelfId() {
        return shelfId;
    }

    public UUID getItemId() {
        return itemId;
    }

    public ShelfActivity.Action getAction() {
        return action;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
