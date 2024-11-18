package model;

public class IndyWinner {
    private int year;
    private String driver;
    private double averageSpeed;
    private String country;

    public IndyWinner(int year, String driver, double averageSpeed, String country) {
        this.year = year;
        this.driver = driver;
        this.averageSpeed = averageSpeed;
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public String getDriver() {
        return driver;
    }

    public double getAverageSpeed() {
        return averageSpeed;
    }

    public String getCountry() {
        return country;
    }
}
