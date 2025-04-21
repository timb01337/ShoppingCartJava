package cart.shopping.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Article {

    private final String articleNumber;
    private final String name;
    private final String description;
    private BigDecimal netPrice;
    private BigDecimal taxRate;

    public Article(String articleNumber, String name, String description, BigDecimal netPrice, BigDecimal taxRate) {
        this.articleNumber = articleNumber;
        this.name = name;
        this.description = description;
        setNetPrice(netPrice);
        setTaxRate(taxRate);
    }

    public BigDecimal getGrossPrice() {
        return netPrice.multiply(BigDecimal.ONE.add(taxRate)).setScale(2, RoundingMode.HALF_UP);
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getNetPrice() {
        return netPrice;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    private void setNetPrice(BigDecimal netPrice) {
        if (netPrice == null || netPrice.compareTo(new BigDecimal("0.01")) < 0) {
            throw new IllegalArgumentException("Net price must be greater than or equal to 0.01");
        }

        if (netPrice.scale() > 2) {
            throw new IllegalArgumentException("Net price must have at most two decimal places");
        }

        this.netPrice = netPrice;
    }

    private void setTaxRate(BigDecimal taxRate) {
        if (taxRate == null || taxRate.compareTo(new BigDecimal("0.01")) < 0 || taxRate.compareTo(BigDecimal.ONE) >= 0) {
            throw new IllegalArgumentException("Tax rate must be greater than or equal to 0.01 and less than 1.0");
        }

        if (taxRate.scale() > 2) {
            throw new IllegalArgumentException("Tax rate must have at most two decimal places");
        }

        this.taxRate = taxRate;
    }
}