package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product can't be null");
        }
        this.products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity == 0 || quantity <= 0) {
            throw new IllegalArgumentException("Quantity can't be null or a negative value");
        }
        for (int i = 0; i < quantity; i++) {
            this.products.add(product);
        }
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Product product : this.products) {
            BigDecimal price = product.getPrice();
            subtotal = subtotal.add(price);
        }
        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Product product : this.products) {
            BigDecimal tax = product.getTaxPercent().multiply(product.getPrice());
            subtotal = subtotal.add(tax);
        }
        return subtotal;
    }

    public BigDecimal getTotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (Product product : this.products) {
            BigDecimal priceWithTax = product.getPriceWithTax();
            subtotal = subtotal.add(priceWithTax);
        }
        return subtotal;
    }
}
