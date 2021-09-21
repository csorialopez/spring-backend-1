package com.sales.market.model;

import com.sales.market.dto.ItemInventoryEntryDto;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
public class ItemInventoryEntry extends ModelBase<ItemInventoryEntryDto> {

    @OneToOne
    @JoinColumn(name = "item_inventory_id")
    private ItemInventory itemInventory;
    private MovementType movementType;
    private int quantity; // represent sale or buy instances quantity

    public ItemInventory getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(ItemInventory itemInventory) {
        this.itemInventory = itemInventory;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

 /*
    Take into account sku cannot be duplicated
    In the service make possible:
       register buy item instances -> Si no existe el producto crearlo, registrar instancias,
                                        crear y actualizar el ItemInventory correspondiente con sus totalizados
                                        Generar los ItemInventoryEntry para reflejar la operacion de entrada o salida
                                         de almacen

       vender un producto
       desechar un producto similar a una venta pero a costo 0. Debe reflejar el totalizado correctamente de
       ItemInventory

       Debe haber tests unitarios que muestren escenarios para estas operaciones en casos de exito y de error.
    */
}
