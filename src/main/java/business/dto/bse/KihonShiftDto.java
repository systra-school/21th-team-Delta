/**
 * ファイル名：KinmuJissekiDto.java
 *
 * 変更履歴
 * 1.0  2010/11/03 Kazuya.Naraki
 */
package business.dto.bse;

import java.util.Date;

/**
 * 説明：基本シフトDto
 * @author nishioka
 *
 */
public class KihonShiftDto {
    /** 社員ID */
    private String shainId;
    /** 社員名 */
    private String shainName;
    /** 月曜日シフト */
    private String shiftIdOnMonday;
    /** 火曜日シフト */
    private String shiftIdOnTuesday;
    /** 水曜日シフト */
    private String shiftIdOnWednesday;
    /** 木曜日シフト */
    private String shiftIdOnThursday;
    /** 金曜日シフト */
    private String shiftIdOnFriday;
    /** 土曜日シフト */
    private String shiftIdOnSaturday;
    /** 日曜日シフト */
    private String shiftIdOnSunday;
    /** 作成ユーザID */
    private String createShainId;
    /** 作成日付 */
    private Date createDt;
    /** 更新ユーザID */
    private String updateShainId;
    /** 更新日付 */
    private Date updateDt;

    /**
     * @return shainId
     */
    public String getShainId() {
        return shainId;
    }
    /**
	 * @return shainName
	 */
	public String getShainName() {
		return shainName;
	}
	/**
	 * @param shainName セットする shainName
	 */
	public void setShainName(String shainName) {
		this.shainName = shainName;
	}
	/**
     * @param shainId セットする shainId
     */
    public void setShainId(String shainId) {
        this.shainId = shainId;
    }
    /**
     * @return shiftIdOnMonday
     */
    public String getShiftIdOnMonday() {
        return shiftIdOnMonday;
    }
    /**
     * @param shiftIdOnMonday セットする shiftIdOnMonday
     */
    public void setShiftIdOnMonday(String shiftIdOnMonday) {
        this.shiftIdOnMonday = shiftIdOnMonday;
    }
    /**
     * @return shiftIdOnTuesday
     */
    public String getShiftIdOnTuesday() {
        return shiftIdOnTuesday;
    }
    /**
     * @param shiftIdOnTuesday セットする shiftIdOnTuesday
     */
    public void setShiftIdOnTuesday(String shiftIdOnTuesday) {
        this.shiftIdOnTuesday = shiftIdOnTuesday;
    }
    /**
     * @return shiftIdOnWednesday
     */
    public String getShiftIdOnWednesday() {
        return shiftIdOnWednesday;
    }
    /**
     * @param shiftIdOnWednesday セットする shiftIdOnWednesday
     */
    public void setShiftIdOnWednesday(String shiftIdOnWednesday) {
        this.shiftIdOnWednesday = shiftIdOnWednesday;
    }
    /**
     * @return shiftIdOnThursday
     */
    public String getShiftIdOnThursday() {
        return shiftIdOnThursday;
    }
    /**
     * @param shiftIdOnThursday セットする shiftIdOnThursday
     */
    public void setShiftIdOnThursday(String shiftIdOnThursday) {
        this.shiftIdOnThursday = shiftIdOnThursday;
    }
    /**
     * @return shiftIdOnFriday
     */
    public String getShiftIdOnFriday() {
        return shiftIdOnFriday;
    }
    /**
     * @param shiftIdOnFriday セットする shiftIdOnFriday
     */
    public void setShiftIdOnFriday(String shiftIdOnFriday) {
        this.shiftIdOnFriday = shiftIdOnFriday;
    }
    /**
     * @return shiftIdOnSaturday
     */
    public String getShiftIdOnSaturday() {
        return shiftIdOnSaturday;
    }
    /**
     * @param shiftIdOnSaturday セットする shiftIdOnSaturday
     */
    public void setShiftIdOnSaturday(String shiftIdOnSaturday) {
        this.shiftIdOnSaturday = shiftIdOnSaturday;
    }
    /**
     * @return shiftIdOnSunday
     */
    public String getShiftIdOnSunday() {
        return shiftIdOnSunday;
    }
    /**
     * @param shiftIdOnSunday セットする shiftIdOnSunday
     */
    public void setShiftIdOnSunday(String shiftIdOnSunday) {
        this.shiftIdOnSunday = shiftIdOnSunday;
    }
    /**
     * @return createShainId
     */
    public String getCreateShainId() {
        return createShainId;
    }
    /**
     * @param createShainId セットする createShainId
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
     * @param createDt セットする createDt
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
     * @param updateShainId セットする updateShainId
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
     * @param updateDt セットする updateDt
     */
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }


}
