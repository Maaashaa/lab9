package fraction;

import java.util.Objects;

public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            simplify();
    }
    public Fraction simplify(){
        for (int i = 2; i < Math.abs(this.numerator); i++) {
            while (this.numerator % i == 0 && this.denominator % i == 0) {
                this.numerator /= i;
                this.denominator /= i;
            }
        }
        return this;
    }
    public Fraction() {
        this.numerator = 1;
        this.denominator = 1;
    }
    public static Fraction sum(Fraction fraction1, Fraction fraction2) {
        int newDenominator = fraction1.denominator * fraction2.denominator;
        int newNumerator = fraction1.numerator * fraction2.denominator + fraction2.numerator * fraction1.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public void sum(Fraction fraction) {
        int newDenominator = fraction.denominator * this.denominator;
        int newNumerator = fraction.numerator * this.denominator + this.numerator * fraction.denominator;
        this.denominator = newDenominator;
        this.numerator = newNumerator;
    }

    public static Fraction dif(Fraction fraction1, Fraction fraction2) {
        int newDenominator = fraction1.denominator * fraction2.denominator;
        int newNumerator = fraction1.numerator * fraction2.denominator - fraction2.numerator * fraction1.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public void dif(Fraction fraction) {
        int newDenominator = fraction.denominator * this.denominator;
        int newNumerator = fraction.numerator * this.denominator - this.numerator * fraction.denominator;
        this.denominator = newDenominator;
        this.numerator = newNumerator;
    }

    public static Fraction multiplic(Fraction fraction1, Fraction fraction2) {
        int newDenominator = fraction1.denominator * fraction2.denominator;
        int newNumerator = fraction1.numerator * fraction2.numerator;
        return new Fraction(newNumerator, newDenominator);
    }

    public void multiplic(Fraction fraction) {
        int newDenominator = fraction.denominator * this.denominator;
        int newNumerator = fraction.numerator * this.numerator;
        this.denominator = newDenominator;
        this.numerator = newNumerator;
    }

    public static Fraction div(Fraction fraction1, Fraction fraction2) {
        int newDenominator = fraction1.denominator * fraction2.numerator;
        int newNumerator = fraction1.numerator * fraction2.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    public void div(Fraction fraction) {
        int newDenominator = fraction.denominator * this.numerator;
        int newNumerator = fraction.numerator * this.denominator;
        this.denominator = newDenominator;
        this.numerator = newNumerator;
    }

    @Override
    public String toString() {
        return "fraction=" + numerator +
                "/"+ denominator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction fraction = (Fraction) o;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }
}