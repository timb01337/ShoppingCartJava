package cart.shopping.data;

import cart.shopping.core.Article;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MockArticleData {

    public static List<Article> getAllMockArticles() {
        List<Article> articles = new ArrayList<>();

        articles.add(new Article("B001", "Mountainbike", "Robustes Mountainbike für jedes Gelände", new BigDecimal("1200.00"), new BigDecimal("0.19")));
        articles.add(new Article("B002", "Rennrad", "Leichtes Rennrad für hohe Geschwindigkeiten", new BigDecimal("1500.00"), new BigDecimal("0.19")));
        articles.add(new Article("B003", "Citybike", "Komfortables Fahrrad für die Stadt", new BigDecimal("800.00"), new BigDecimal("0.19")));
        articles.add(new Article("B004", "E-Bike", "Elektrisches Fahrrad mit langer Akkulaufzeit", new BigDecimal("2500.00"), new BigDecimal("0.19")));
        articles.add(new Article("B005", "Kinderfahrrad", "Fahrrad für Kinder ab 6 Jahren", new BigDecimal("300.00"), new BigDecimal("0.19")));

        articles.add(new Article("P001", "Lenker", "Ergonomischer Fahrradlenker", new BigDecimal("50.00"), new BigDecimal("0.19")));
        articles.add(new Article("P002", "Sattel", "Bequemer Fahrradsattel", new BigDecimal("70.00"), new BigDecimal("0.19")));
        articles.add(new Article("P003", "Sattelstütze", "Verstellbare Sattelstütze", new BigDecimal("40.00"), new BigDecimal("0.19")));
        articles.add(new Article("P004", "Bremsscheiben", "Hochwertige Bremsscheiben", new BigDecimal("30.00"), new BigDecimal("0.19")));
        articles.add(new Article("P005", "Kette", "Fahrradkette für alle Modelle", new BigDecimal("25.00"), new BigDecimal("0.19")));
        articles.add(new Article("P006", "Pedale", "Rutschfeste Fahrradpedale", new BigDecimal("35.00"), new BigDecimal("0.19")));
        articles.add(new Article("P007", "Schutzblech", "Schutzblech für Vorder- und Hinterrad", new BigDecimal("20.00"), new BigDecimal("0.19")));
        articles.add(new Article("P008", "Lichtset", "LED-Lichtset für Vorder- und Rücklicht", new BigDecimal("60.00"), new BigDecimal("0.19")));
        articles.add(new Article("P009", "Fahrradschloss", "Sicheres Fahrradschloss", new BigDecimal("45.00"), new BigDecimal("0.19")));
        articles.add(new Article("P010", "Fahrradpumpe", "Kompakte Fahrradpumpe", new BigDecimal("15.00"), new BigDecimal("0.19")));

        articles.add(new Article("E001", "Energieriegel Schoko", "Leckerer Energieriegel mit Schokoladengeschmack", new BigDecimal("2.50"), new BigDecimal("0.07")));
        articles.add(new Article("E002", "Energieriegel Banane", "Energieriegel mit Bananengeschmack", new BigDecimal("2.50"), new BigDecimal("0.07")));

        articles.add(new Article("A001", "Fahrradhelm", "Sicherer Fahrradhelm", new BigDecimal("80.00"), new BigDecimal("0.19")));
        articles.add(new Article("A002", "Fahrradrucksack", "Wasserdichter Fahrradrucksack", new BigDecimal("100.00"), new BigDecimal("0.19")));
        articles.add(new Article("A003", "Trinkflasche", "Fahrrad-Trinkflasche", new BigDecimal("10.00"), new BigDecimal("0.19")));
        articles.add(new Article("A004", "Fahrradständer", "Stabiler Fahrradständer", new BigDecimal("25.00"), new BigDecimal("0.19")));
        articles.add(new Article("A005", "Fahrradcomputer", "Digitaler Fahrradcomputer", new BigDecimal("120.00"), new BigDecimal("0.19")));
        articles.add(new Article("A006", "Fahrradhandschuhe", "Bequeme Fahrradhandschuhe", new BigDecimal("30.00"), new BigDecimal("0.19")));
        articles.add(new Article("A007", "Fahrradkorb", "Praktischer Fahrradkorb", new BigDecimal("40.00"), new BigDecimal("0.19")));

        return articles;
    }

    public static List<Article> getRandomArticles(int countOfRandomArticles) {
        List<Article> allArticles = getAllMockArticles();
        Collections.shuffle(allArticles);
        return allArticles.subList(0, Math.min(countOfRandomArticles, allArticles.size()));
    }
}