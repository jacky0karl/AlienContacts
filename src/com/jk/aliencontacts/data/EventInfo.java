package com.jk.aliencontacts.data;

public class EventInfo extends BaseInfo {
    private String startDate;
    private int type;
    private String label;

    public EventInfo(long id, String startDate, int type, String label) {
        super(id);
        this.startDate = startDate;
        this.type = type;
        this.label = label;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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