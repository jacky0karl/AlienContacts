package com.jk.aliencontacts.data;

public class PhoneInfo extends BaseInfo {
    private String number;
    private int type;
    private String label;

    public PhoneInfo(long id, String number, int type, String label) {
        super(id);
        this.number = number;
        this.type = type;
        this.label = label;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}