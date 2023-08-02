public class ItemWithStock {
        private Item item;
        private int stock;

        public ItemWithStock(Item item, int stock) {
            this.item = item;
            this.stock = stock;
        }

        public Item getItem() {
            return item;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }
    }