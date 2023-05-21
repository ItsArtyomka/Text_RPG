class Trader implements Seller {
    @Override
    public String sell(Goods goods) {
        String result = "";
        if (goods == Goods.POTION) {
            result = "potion";
            Player.potionsList.add(new Potion());
        }
        return result;
    }

    public enum Goods {
        POTION
    }
}
