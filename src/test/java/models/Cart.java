package models;

public class Cart {
    private int id;
    private int userId;
    private int[] products;
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int[] getProducts() { return products; }
    public void setProducts(int[] products) { this.products = products; }
}