package com.skcc.wdp.poc.view.fragment;

public class Person {
    private String name;
    private String expiry_date;
    private String bank;
    private int person_img;
    public Person(String name, String expiry_date, String bank, int person_img) {
        this.name = name;
        this.expiry_date = expiry_date;
        this.bank = bank;
        this.person_img=person_img;
    }

    public int getPerson_img() {
        return person_img;
    }

    public void setPerson_img(int person_img) {
        this.person_img = person_img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}
