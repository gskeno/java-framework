package com.gson.pico.container.introduce;

public class ShoppingCart {
    private Store store;
    private User user;

    public ShoppingCart(Store store, User user) {
        this.store = store;
        this.user = user;
    }

//    public boolean addItemTo(Make make, Model model, int quantity) {
//        System.out.println(String.format("addItemTo invoked, make=%s, model=%s, quantity=%s", make, model, quantity));
//        return true;
//    }

    public void addItemTo(Make make, Model model, int quantity) {
        System.out.println(String.format("addItemTo invoked, make=%s, model=%s, quantity=%s", make, model, quantity));
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "store=" + store +
                ", user=" + user +
                '}';
    }
}
