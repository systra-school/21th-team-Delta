/**
 * ファイル名：KinmuJissekiNyuryokuKakuninBean.java
 *
 * 変更履歴
 * 1.0  2010/11/04 Kazuya.Naraki
 */
package form.act;

/**
 * 説明：勤務実績入力確認
 * @author naraki
 *
 */
public class KinmuJissekiNyuryokuKakuninBean {

    /** 社員ID */
    private String shainId;
    /** 稼働日 */
    private String kadouDay;
    /** 稼働日(表示用) */
    private String kadouDayDisp;
    /** シフト */
    private String shiftId;
    /** シンボル */
    private String symbol;
    /** 開始時間(シフト) */
    private String startTimeShift;
    /** 終了時間(シフト) */
    private String endTimeShift;
    /** 休憩時間(シフト) */
    private String breakTimeShift;
    /** 開始時間 */
    private String startTime;
    /** 終了時間 */
    private String endTime;
    /** 休憩時間 */
    private String breakTime;
    /** 実働時間 */
    private String jitsudouTime;
    /** 時間外時間 */
    private String jikangaiTime;
    /** 休日時間 */
    private String kyuujitsuTime;
    /** 備考 */
    private String bikou;
    /** 曜日 */
    private String youbi;
    /** 祝日フラグ */
    private boolean shukujitsuFlg;

    /**
     * @return shainId
     */
    public String getShainId() {
        return shainId;
    }
    /**
     * @param shainId をセットする
     */
    public void setShainId(String shainId) {
        this.shainId = shainId;
    }
    /**
     * @return kadouDay
     */
    public String getKadouDay() {
        return kadouDay;
    }
    /**
     * @param kadouDay をセットする
     */
    public void setKadouDay(String kadouDay) {
        this.kadouDay = kadouDay;
    }
    /**
     * @return symbol
     */
    public String getSymbol() {
        return symbol;
    }
    /**
     * @param symbol をセットする
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    /**
     * @return startTime
     */
    public String getStartTime() {
        return startTime;
    }
    /**
     * @param startTime をセットする
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    /**
     * @return endTime
     */
    public String getEndTime() {
        return endTime;
    }
    /**
     * @param endTime をセットする
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    /**
     * @return breakTime
     */
    public String getBreakTime() {
        return breakTime;
    }
    /**
     * @param breakTime をセットする
     */
    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }
    /**
     * @return jitsudouTime
     */
    public String getJitsudouTime() {
        return jitsudouTime;
    }
    /**
     * @param jitsudouTime をセットする
     */
    public void setJitsudouTime(String jitsudouTime) {
        this.jitsudouTime = jitsudouTime;
    }
    /**
     * @return jikangaiTime
     */
    public String getJikangaiTime() {
        return jikangaiTime;
    }
    /**
     * @param jikangaiTime をセットする
     */
    public void setJikangaiTime(String jikangaiTime) {
        this.jikangaiTime = jikangaiTime;
    }
    /**
     * @return kyuujitsuTime
     */
    public String getKyuujitsuTime() {
        return kyuujitsuTime;
    }
    /**
     * @param kyuujitsuTime をセットする
     */
    public void setKyuujitsuTime(String kyuujitsuTime) {
        this.kyuujitsuTime = kyuujitsuTime;
    }
    /**
     * @return bikou
     */
    public String getBikou() {
        return bikou;
    }
    /**
     * @param bikou をセットする
     */
    public void setBikou(String bikou) {
        this.bikou = bikou;
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
     * @return kadouDayDisp
     */
    public String getKadouDayDisp() {
        return kadouDayDisp;
    }
    /**
     * @param kadouDayDisp をセットする
     */
    public void setKadouDayDisp(String kadouDayDisp) {
        this.kadouDayDisp = kadouDayDisp;
    }
    /**
     * @return shiftId
     */
    public String getShiftId() {
        return shiftId;
    }
    /**
     * @param shiftId をセットする
     */
    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }
    /**
     * @return startTimeShift
     */
    public String getStartTimeShift() {
        return startTimeShift;
    }
    /**
     * @param startTimeShift をセットする
     */
    public void setStartTimeShift(String startTimeShift) {
        this.startTimeShift = startTimeShift;
    }
    /**
     * @return endTimeShift
     */
    public String getEndTimeShift() {
        return endTimeShift;
    }
    /**
     * @param endTimeShift をセットする
     */
    public void setEndTimeShift(String endTimeShift) {
        this.endTimeShift = endTimeShift;
    }
    /**
     * @return breakTimeShift
     */
    public String getBreakTimeShift() {
        return breakTimeShift;
    }
    /**
     * @param breakTimeShift をセットする
     */
    public void setBreakTimeShift(String breakTimeShift) {
        this.breakTimeShift = breakTimeShift;
    }

}
