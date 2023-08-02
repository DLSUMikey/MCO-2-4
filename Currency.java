import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Currency class represents a collection of different denominations of currency (bills and coins).
 * It allows storing and managing quantities of each denomination and provides methods to calculate
 * the total currency value and access individual denominations.
 */
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

    /**
     * Creates a new Currency instance with the specified quantities of each denomination.
     *
     * @param thousands The quantity of thousand bills.
     * @param fiveHundreds The quantity of five-hundred bills.
     * @param hundreds The quantity of hundred bills.
     * @param fifties The quantity of fifty bills.
     * @param twenties The quantity of twenty bills.
     * @param tens The quantity of ten bills.
     * @param fives The quantity of five bills.
     * @param ones The quantity of one bills.
     * @param halfPeso The quantity of half-peso coins.
     * @param quarter The quantity of quarter coins.
     * @param dime The quantity of dime coins.
     * @param nickel The quantity of nickel coins.
     * @param penny The quantity of penny coins.
     */
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

    /**
     * Calculates the total value of the currency based on the quantities of each denomination.
     *
     * @return The total value of the currency in dollars.
     */
    public double getTotalCurrencyValue() {
        return thousands * 1000.0 +
                fiveHundreds * 500.0 +
                hundreds * 100.0 +
                fifties * 50.0 +
                twenties * 20.0 +
                tens * 10.0 +
                fives * 5.0 +
                ones * 1.0 +
                halfPeso * 0.5 +
                quarter * 0.25 +
                dime * 0.1 +
                nickel * 0.05 +
                penny * 0.01;
    }

    /**
     * Retrieves the quantities of each denomination as a LinkedHashMap, preserving the insertion order.
     *
     * @return A LinkedHashMap containing denomination names as keys and their respective quantities as values.
     */
    public Map<String, Integer> getDenominations() {
        Map<String, Integer> denominations = new LinkedHashMap<>();
        denominations.put("Thousands", getThousands());
        denominations.put("FiveHundreds", getFiveHundreds());
        denominations.put("Hundreds", getHundreds());
        denominations.put("Fifties", getFifties());
        denominations.put("Twenties", getTwenties());
        denominations.put("Tens", getTens());
        denominations.put("Fives", getFives());
        denominations.put("Ones", getOnes());
        denominations.put("HalfPeso", getHalfPeso());
        denominations.put("Quarter", getQuarter());
        denominations.put("Dime", getDime());
        denominations.put("Nickel", getNickel());
        denominations.put("Penny", getPenny());
        return denominations;
    }

    /**
     * Retrieves an array of all denomination names.
     *
     * @return An array of strings representing all denomination names.
     */
    public String[] getAllDenominations() {
        return new String[]{
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

    /**
     * Get the quantity of thousand bills.
     *
     * @return The quantity of thousand bills.
     */
    public int getThousands() {
        return thousands;
    }

    /**
     * Get the quantity of five-hundred bills.
     *
     * @return The quantity of five-hundred bills.
     */
    public int getFiveHundreds() {
        return fiveHundreds;
    }

    /**
     * Get the quantity of hundred bills.
     *
     * @return The quantity of hundred bills.
     */
    public int getHundreds() {
        return hundreds;
    }

    /**
     * Get the quantity of fifty bills.
     *
     * @return The quantity of fifty bills.
     */
    public int getFifties() {
        return fifties;
    }

    /**
     * Get the quantity of twenty bills.
     *
     * @return The quantity of twenty bills.
     */
    public int getTwenties() {
        return twenties;
    }

    /**
     * Get the quantity of ten bills.
     *
     * @return The quantity of ten bills.
     */
    public int getTens() {
        return tens;
    }

    /**
     * Get the quantity of five bills.
     *
     * @return The quantity of five bills.
     */
    public int getFives() {
        return fives;
    }

    /**
     * Get the quantity of one bills.
     *
     * @return The quantity of one bills.
     */
    public int getOnes() {
        return ones;
    }

    /**
     * Get the quantity of half-peso coins.
     *
     * @return The quantity of half-peso coins.
     */
    public int getHalfPeso() {
        return halfPeso;
    }

    /**
     * Get the quantity of quarter coins.
     *
     * @return The quantity of quarter coins.
     */
    public int getQuarter() {
        return quarter;
    }

    /**
     * Get the quantity of dime coins.
     *
     * @return The quantity of dime coins.
     */
    public int getDime() {
        return dime;
    }

    /**
     * Get the quantity of nickel coins.
     *
     * @return The quantity of nickel coins.
     */
    public int getNickel() {
        return nickel;
    }

    /**
     * Get the quantity of penny coins.
     *
     * @return The quantity of penny coins.
     */
    public int getPenny() {
        return penny;
    }

    /**
     * Set the quantity of thousand bills.
     *
     * @param thousands The new quantity of thousand bills.
     */
    public void setThousands(int thousands) {
        this.thousands = thousands;
    }

    /**
     * Set the quantity of five-hundred bills.
     *
     * @param fiveHundreds The new quantity of five-hundred bills.
     */
    public void setFiveHundreds(int fiveHundreds) {
        this.fiveHundreds = fiveHundreds;
    }

    /**
     * Set the quantity of hundred bills.
     *
     * @param hundreds The new quantity of hundred bills.
     */
    public void setHundreds(int hundreds) {
        this.hundreds = hundreds;
    }

    /**
     * Set the quantity of fifty bills.
     *
     * @param fifties The new quantity of fifty bills.
     */
    public void setFifties(int fifties) {
        this.fifties = fifties;
    }

    /**
     * Set the quantity of twenty bills.
     *
     * @param twenties The new quantity of twenty bills.
     */
    public void setTwenties(int twenties) {
        this.twenties = twenties;
    }

    /**
     * Set the quantity of ten bills.
     *
     * @param tens The new quantity of ten bills.
     */
    public void setTens(int tens) {
        this.tens = tens;
    }

    /**
     * Set the quantity of five bills.
     *
     * @param fives The new quantity of five bills.
     */
    public void setFives(int fives) {
        this.fives = fives;
    }

    /**
     * Set the quantity of one bills.
     *
     * @param ones The new quantity of one bills.
     */
    public void setOnes(int ones) {
        this.ones = ones;
    }

    /**
     * Set the quantity of half-peso coins.
     *
     * @param halfPeso The new quantity of half-peso coins.
     */
    public void setHalfPeso(int halfPeso) {
        this.halfPeso = halfPeso;
    }

    /**
     * Set the quantity of quarter coins.
     *
     * @param quarter The new quantity of quarter coins.
     */
    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    /**
     * Set the quantity of dime coins.
     *
     * @param dime The new quantity of dime coins.
     */
    public void setDime(int dime) {
        this.dime = dime;
    }

    /**
     * Set the quantity of nickel coins.
     *
     * @param nickel The new quantity of nickel coins.
     */
    public void setNickel(int nickel) {
        this.nickel = nickel;
    }

    /**
     * Set the quantity of penny coins.
     *
     * @param penny The new quantity of penny coins.
     */
    public void setPenny(int penny) {
        this.penny = penny;
    }
}
