/**
 * ファイル名：HibetsuShiftDto.java
 *
 * 変更履歴
 * 1.0  2010/10/23 Kazuya.Naraki
 */
package business.dto.day;

/**
 * 説明：日別シフトDto
 * @author naraki
 *
 */
public class HibetsuShiftDto {

    /** 社員名 */
    private String shainName;
    /** 開始時間 */
    private String startTime;
    /** 終了時間 */
    private String endTime;
    /** 休憩時間 */
    private String breakTime;
    /**
     * @return shainName
     */
    public String getShainName() {
        return shainName;
    }
    /**
     * @param shainName をセットする
     */
    public void setShainName(String shainName) {
        this.shainName = shainName;
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
