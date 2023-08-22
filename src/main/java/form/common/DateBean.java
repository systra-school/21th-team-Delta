/**
 * ファイル名：DateBean.java
 *
 * 変更履歴
 * 1.0  2010/10/13 Kazuya.Naraki
 */
package form.common;

import constant.CommonConstant.DayOfWeek;

/**
 * 説明：日付情報格納クラス
 * @author naraki
 *
 */
public class DateBean {
    /** 年月日 yyyy/MM/dd */
    private String yearMonthDay;
    /** 曜日 */
    private String youbi;
    /** 曜日(Enum) */
    private DayOfWeek youbiEnum;
    /** 祝日フラグ */
    private boolean shukujitsuFlg;
    /**
     * @return yearMonthDay
     */
    public String getYearMonthDay() {
        return yearMonthDay;
    }
    /**
     * @param yearMonthDay をセットする
     */
    public void setYearMonthDay(String yearMonthDay) {
        this.yearMonthDay = yearMonthDay;
    }
    /**
     * @return youbi
     */
    public String getYoubi() {
        return youbi;
    }
    /**
     * @param youbi をセットする
     */
    public void setYoubi(String youbi) {
        this.youbi = youbi;
    }
    /**
     * @return shukujitsuFlg
     */
    public boolean getShukujitsuFlg() {
        return shukujitsuFlg;
    }
    /**
     * @param shukujitsuFlg をセットする
     */
    public void setShukujitsuFlg(boolean shukujitsuFlg) {
        this.shukujitsuFlg = shukujitsuFlg;
    }
    /**
     * @return youbiEnum
     */
    public DayOfWeek getYoubiEnum() {
        return youbiEnum;
    }
    /**
     * @param youbiEnum をセットする
     */
    public void setYoubiEnum(DayOfWeek youbiEnum) {
        this.youbiEnum = youbiEnum;
    }
}
