package javabrain.org.expenditure.pojo;

/**
 * Created by Fernando García on 05/04/2019.
 */

public class Expenditure {

    private int id;
    private String expenditureName;
    private int period;
    private double expenditure;
    private double expenditureMonth;
    private double expenditureMonthPayed;
    private double monthPay;
    private double monthPayed;
    private String expenditureDate;
    private boolean isPayed;

    public Expenditure() {}

    public Expenditure(int id, String expenditureName, int period, double expenditure, double expenditureMonth, double expenditureMonthPayed, double monthPay, double monthPayed, String expenditureDate, boolean isPayed) {
        this.id = id;
        this.expenditureName = expenditureName;
        this.period = period;
        this.expenditure = expenditure;
        this.expenditureMonth = expenditureMonth;
        this.expenditureMonthPayed = expenditureMonthPayed;
        this.monthPay = monthPay;
        this.monthPayed = monthPayed;
        this.expenditureDate = expenditureDate;
        this.isPayed = isPayed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpenditureName() {
        return expenditureName;
    }

    public void setExpenditureName(String expenditureName) {
        this.expenditureName = expenditureName;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }

    public double getExpenditureMonth() {
        return expenditureMonth;
    }

    public void setExpenditureMonth(double expenditureMonth) {
        this.expenditureMonth = expenditureMonth;
    }

    public double getExpenditureMonthPayed() {
        return expenditureMonthPayed;
    }

    public void setExpenditureMonthPayed(double expenditureMonthPayed) {
        this.expenditureMonthPayed = expenditureMonthPayed;
    }

    public double getMonthPay() {
        return monthPay;
    }

    public void setMonthPay(double monthPay) {
        this.monthPay = monthPay;
    }

    public double getMonthPayed() {
        return monthPayed;
    }

    public void setMonthPayed(double monthPayed) {
        this.monthPayed = monthPayed;
    }

    public String getExpenditureDate() {
        return expenditureDate;
    }

    public void setExpenditureDate(String expenditureDate) {
        this.expenditureDate = expenditureDate;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setPayed(boolean payed) {
        isPayed = payed;
    }
}
