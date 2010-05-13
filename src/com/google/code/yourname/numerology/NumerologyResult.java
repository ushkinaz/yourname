package com.google.code.yourname.numerology;

/**
 * Defines a result of numerology calculations for one token.
 *
 * @author Dmitry Sidorenko
 */
public class NumerologyResult {
    /**
     * Original token for which calculation took place.
     */
    private final String token;

    /**
     * Summ of numeric values of letters.
     */
    private int sum;

    /**
     * A number of the token.
     */
    private int number;

    /**
     * A modality.
     */
    private int modality;

    public NumerologyResult(String token) {
        this.token = token;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getModality() {
        return modality;
    }

    public void setModality(int modality) {
        this.modality = modality;
    }

    public String getToken() {
        return token;
    }


    @Override
    @SuppressWarnings({"RedundantIfStatement"})
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumerologyResult)) return false;

        NumerologyResult that = (NumerologyResult) o;

        if (modality != that.modality) return false;
        if (number != that.number) return false;
        if (sum != that.sum) return false;
        if (!token.equalsIgnoreCase(that.token)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = token.hashCode();
        result = 31 * result + sum;
        result = 31 * result + number;
        result = 31 * result + modality;
        return result;
    }

    @Override
    public String toString() {
        return "NumerologyResult{" +
                "token='" + token + '\'' +
                ", sum='" + sum + '\'' +
                ", number=" + number +
                ", modality=" + modality +
                '}';
    }
}
