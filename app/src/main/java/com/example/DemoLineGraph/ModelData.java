package com.example.DemoLineGraph;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelData {

    @SerializedName("Months")
    @Expose
    private String months;
    @SerializedName("Expenses")
    @Expose
    private Expenses expenses;
    @SerializedName("Total")
    @Expose
    private Integer total;

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public void setExpenses(Expenses expenses) {
        this.expenses = expenses;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public class Expenses {

        @SerializedName("Shopping")
        @Expose
        private Integer shopping;
        @SerializedName("Food")
        @Expose
        private Integer food;
        @SerializedName("Travel")
        @Expose
        private Integer travel;
        @SerializedName("Fuel")
        @Expose
        private Integer fuel;
        @SerializedName("EMI")
        @Expose
        private Integer eMI;
        @SerializedName("Others")
        @Expose
        private Integer others;

        public Integer getShopping() {
            return shopping;
        }

        public void setShopping(Integer shopping) {
            this.shopping = shopping;
        }

        public Integer getFood() {
            return food;
        }

        public void setFood(Integer food) {
            this.food = food;
        }

        public Integer getTravel() {
            return travel;
        }

        public void setTravel(Integer travel) {
            this.travel = travel;
        }

        public Integer getFuel() {
            return fuel;
        }

        public void setFuel(Integer fuel) {
            this.fuel = fuel;
        }

        public Integer getEMI() {
            return eMI;
        }

        public void setEMI(Integer eMI) {
            this.eMI = eMI;
        }

        public Integer getOthers() {
            return others;
        }

        public void setOthers(Integer others) {
            this.others = others;
        }
    }
}
