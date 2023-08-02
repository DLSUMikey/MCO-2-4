import java.util.LinkedHashMap;
import java.util.Map;

public class Currency {
    private int thousands;
    private int fiveHundreds;
    private int hundreds;
    private int fifties;
    private int twenties;
    private int tens;
    private int fives;
    private int ones;
    private int halfPeso;
    private int quarter;
    private int dime;
    private int nickel;
    private int penny;

    public Currency(int thousands, int fiveHundreds, int hundreds, int fifties, int twenties, int tens, int fives, int ones,
                    int halfPeso, int quarter, int dime, int nickel, int penny) {
        this.thousands = thousands;
        this.fiveHundreds = fiveHundreds;
        this.hundreds = hundreds;
        this.fifties = fifties;
        this.twenties = twenties;
        this.tens = tens;
        this.fives = fives;
        this.ones = ones;
        this.halfPeso = halfPeso;
        this.quarter = quarter;
        this.dime = dime;
        this.nickel = nickel;
        this.penny = penny;
    }

    public double getTotalCurrencyValue() {
        return thousands * 1000.0 +
            fiveHundreds * 500.0 +
            hundreds * 100.0 +
            fifties * 50.0 +
            tens * 10.0 +
            fives * 5.0 +
            ones * 1.0 +
            halfPeso * 0.5 +
            quarter * 0.25 +
            dime * 0.1 +
            nickel * 0.05 +
            penny * 0.01;
    }

    public Map<String, Integer> getDenominations() {
        Map<String, Integer> denominations = new LinkedHashMap<>(); // Keep insertion order for display purposes
        denominations.put("Thousands", getThousands());
        denominations.put("Five Hundreds", getFiveHundreds());
        denominations.put("Hundreds", getHundreds());
        denominations.put("Fifties", getFifties());
        denominations.put("Twenties", getTwenties());
        denominations.put("Tens", getTens());
        denominations.put("Fives", getFives());
        denominations.put("Ones", getOnes());
        denominations.put("Half Peso", getHalfPeso());
        denominations.put("Quarter", getQuarter());
        denominations.put("Dime", getDime());
        denominations.put("Nickel", getNickel());
        denominations.put("Penny", getPenny());
        return denominations;
    }

    public String[] getAllDenominations() {
        return new String[] {
            "Thousands",
            "FiveHundreds",
            "Hundreds",
            "Fifties",
            "Twenties",
            "Tens",
            "Fives",
            "Ones",
            "HalfPeso",
            "Quarter",
            "Dime",
            "Nickel",
            "Penny"
        };
    }

    // Getters
    public int getThousands() {
        return thousands;
    }

    public int getFiveHundreds() {
        return fiveHundreds;
    }

    public int getHundreds() {
        return hundreds;
    }

    public int getFifties() {
        return fifties;
    }

    public int getTwenties() {
        return twenties;
    }

    public int getTens() {
        return tens;
    }

    public int getFives() {
        return fives;
    }

    public int getOnes() {
        return ones;
    }

    public int getHalfPeso() {
        return halfPeso;
    }

    public int getQuarter() {
        return quarter;
    }

    public int getDime() {
        return dime;
    }

    public int getNickel() {
        return nickel;
    }

    public int getPenny() {
        return penny;
    }

    // Setters
    public void setThousands(int thousands) {
        this.thousands = thousands;
    }

    public void setFiveHundreds(int fiveHundreds) {
        this.fiveHundreds = fiveHundreds;
    }

    public void setHundreds(int hundreds) {
        this.hundreds = hundreds;
    }

    public void setFifties(int fifties) {
        this.fifties = fifties;
    }

    public void setTwenties(int twenties) {
        this.twenties = twenties;
    }

    public void setTens(int tens) {
        this.tens = tens;
    }

    public void setFives(int fives) {
        this.fives = fives;
    }

    public void setOnes(int ones) {
        this.ones = ones;
    }

    public void setHalfPeso(int halfPeso) {
        this.halfPeso = halfPeso;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public void setDime(int dime) {
        this.dime = dime;
    }

    public void setNickel(int nickel) {
        this.nickel = nickel;
    }

    public void setPenny(int penny) {
        this.penny = penny;
    }
}
