package com.jk.aliencontacts.data;

import java.util.Calendar;

public class CalllogInfo extends BaseInfo {
    private String number;
    private int type;
    private long date;
    private long duration;
    private boolean isRead;

    public CalllogInfo(long id, String number, int type, long date, long duration, int isRead) {
        super(id);
        this.number = number;
        this.type = type;
        this.date = date;
        this.duration = duration;

        if (isRead == 0) {
            this.isRead = false;
        } else {
            this.isRead = true;
        }
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

    public String getDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        String time = String.valueOf(cal.get(Calendar.YEAR));
        time += "-";
        if (cal.get(Calendar.MONTH) + 1 < 10) {
            time += "0";
        }
        time += (cal.get(Calendar.MONTH) + 1);
        time += "-";
        if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
            time += "0";
        }
        time += cal.get(Calendar.DAY_OF_MONTH);
        time += " ";
        if (cal.get(Calendar.HOUR_OF_DAY) < 10) {
            time += "0";
        }
        time += cal.get(Calendar.HOUR_OF_DAY);
        time += ":";
        if (cal.get(Calendar.MINUTE) < 10) {
            time += "0";
        }
        time += cal.get(Calendar.MINUTE);
        time += ":";
        if (cal.get(Calendar.SECOND) < 10) {
            time += "0";
        }
        time += cal.get(Calendar.SECOND);
        return time;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDuration() {
        int hour = 0;
        int min = 0;
        int sec = (int) duration;
        if (sec > 60) {
            min = sec / 60;
            sec = sec % 60;
            if (min > 60) {
                hour = min / 60;
                min = min % 60;
            }
        }

        String dur = "";
        if (hour < 10) {
            dur += "0";
        }
        dur += String.valueOf(hour);
        dur += ":";
        if (min < 10) {
            dur += "0";
        }
        dur += String.valueOf(min);
        dur += ":";
        if (sec < 10) {
            dur += "0";
        }
        dur += String.valueOf(sec);
        return dur;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
}
