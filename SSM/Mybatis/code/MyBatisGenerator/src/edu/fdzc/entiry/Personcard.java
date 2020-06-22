package edu.fdzc.entiry;

public class Personcard {
    private Integer cardid;

    private String cardinfo;

    public Integer getCardid() {
        return cardid;
    }

    public void setCardid(Integer cardid) {
        this.cardid = cardid;
    }

    public String getCardinfo() {
        return cardinfo;
    }

    public void setCardinfo(String cardinfo) {
        this.cardinfo = cardinfo == null ? null : cardinfo.trim();
    }
}