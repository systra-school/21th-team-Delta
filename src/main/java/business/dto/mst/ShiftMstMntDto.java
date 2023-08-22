/**
 * ファイル名：ShiftMstMntDto.java
 *
 * 変更履歴
 * 1.0  2010/07/19 Kazuya.Naraki
 */
package business.dto.mst;

import java.util.Date;

/**
 * 説明：シフトマスタDto
 * @author naraki
 *
 */
public class ShiftMstMntDto {

    /** シフトID */
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
    /** 作成ユーザID */
    private String createShainId;
    /** 作成日付 */
    private Date createDt;
    /** 更新ユーザID */
    private String updateShainId;
    /** 更新日付 */
    private Date updateDt;

    /** 削除フラグ */
    private boolean deleteFlg;


    /**
     * @return createShainId
     */
    public String getCreateShainId() {
        return createShainId;
    }

    /**
     * @param createShainId
     * セットする createShainId
     */
    public void setCreateShainId(String createShainId) {
        this.createShainId = createShainId;
    }

    /**
     * @return createDt
     */
    public Date getCreateDt() {
        return createDt;
    }

    /**
     * @param createDt
     * セットする createDt
     */
    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    /**
     * @return updateShainId
     */
    public String getUpdateShainId() {
        return updateShainId;
    }

    /**
     * @param updateShainId
     * セットする updateShainId
     */
    public void setUpdateShainId(String updateShainId) {
        this.updateShainId = updateShainId;
    }

    /**
     * @return updateDt
     */
    public Date getUpdateDt() {
        return updateDt;
    }

    /**
     * @param updateDt
     * セットする updateDt
     */
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
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

    /**
     * @return deleteFlg
     */
    public boolean getDeleteFlg() {
        return deleteFlg;
    }

    /**
     * @param deleteFlg をセットする
     */
    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }
}
