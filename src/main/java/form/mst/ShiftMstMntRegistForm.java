/**
 * ファイル名：ShiftMstMntRegistForm.java
 *
 * 変更履歴
 * 1.0  2010/08/23 Kazuya.Naraki
 */
package form.mst;


import org.apache.struts.action.ActionForm;

/**
 * 説明：シフトマスタメンテナンスフォームクラス
 * @author naraki
 *
 */
public class ShiftMstMntRegistForm extends ActionForm {

    /** シフトＩＤ */
    private String shiftId;
    /** シフト名 */
    private String shiftName;
    /** シンボル */
    private String symbol;
    /** 開始時間 */
    private String startTime;
    /** 終了時間 */
    private String endTime;
    /** 休憩時間 */
    private String breakTime;

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
     * @return shiftName
     */
    public String getShiftName() {
        return shiftName;
    }
    /**
     * @param shiftName をセットする
     */
    public void setShiftName(String shiftName) {
        this.shiftName = shiftName;
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

}
