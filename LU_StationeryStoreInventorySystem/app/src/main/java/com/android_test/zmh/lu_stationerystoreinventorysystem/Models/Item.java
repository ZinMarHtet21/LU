package com.android_test.zmh.lu_stationerystoreinventorysystem.Models;

/**
 * Created by student on 4/3/15.
 */
public class Item {
    private String id;
    private String category;
    private String description;
    private int reorderLevel;
    private int reorderQty;
    private int virtualBalance;
    private String status;
    private String uom;

    public Item() {
    }

    public Item(String id, String category, String description, int reorderLevel, int reorderQty, int virtualBalance, String status, String uom) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.reorderLevel = reorderLevel;
        this.reorderQty = reorderQty;
        this.virtualBalance = virtualBalance;
        this.status = status;
        this.uom = uom;
    }

    public String getID(){
        return id;
    }

    public void setID(String id){
        this.id = id;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getReorderLevel(){
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel){
        this.reorderLevel = reorderLevel;
    }

    public int getReorderQty(){
        return reorderQty;
    }

    public void setReorderQty(int reorderQty){
        this.reorderQty = reorderQty;
    }

    public int getVirtualBalance(){
        return virtualBalance;
    }

    public void setVirtualBalance(int remark){
        this.virtualBalance = virtualBalance;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getUOM(){
        return uom;
    }

    public void setUOM(String uom){
        this.uom = uom;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", reorderLevel=" + reorderLevel +
                ", reorderQty=" + reorderQty +
                ", virtualBalance=" + virtualBalance +
                ", status='" + status + '\'' +
                ", uom='" + uom + '\'' +
                '}';
    }
}
