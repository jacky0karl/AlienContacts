package com.jk.aliencontacts.data;

public class ContactInfo extends BaseInfo {
    public String mDisplayName;
    public String[] mSplitName;

    public ContactInfo(long id, String displayName, String sortKey) {
        super(id);
        mDisplayName = displayName;
        mSplitName = sortKey.split("\\s");
    }

    public String getFirstLetter() {
        return mSplitName[0].substring(0, 1);
    }
}