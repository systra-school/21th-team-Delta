/**
 * ファイル名：ShukkinKibouKakuninDto.java
 *
 * 変更履歴
 * 1.0  2010/10/06 Kazuya.Naraki
 */
package business.dto.shk;

/**
 * 説明：出勤希望日入力Dto
 * @author naraki
 *
 */
public class ShukkinKibouKakuninDto {
    /** 社員ID */
    private String shainId;
    /** 社員名 */
    private String shainName;
    /** 年月日 */
    private String yearMonthDay;
    /** 希望シフト */
    private String kibouShiftId;
    /** 希望シフトシンボル */
    private String kibouShiftSymbol;

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
     * @return kibouShiftId
     */
    public String getKibouShiftId() {
        return kibouShiftId;
    }
    /**
     * @param kibouShiftId をセットする
     */
    public void setKibouShiftId(String kibouShiftId) {
        this.kibouShiftId = kibouShiftId;
    }
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
     * @return kibouShiftSymbol
     */
    public String getKibouShiftSymbol() {
        return kibouShiftSymbol;
    }
    /**
     * @param kibouShiftSymbol をセットする
     */
    public void setKibouShiftSymbol(String kibouShiftSymbol) {
        this.kibouShiftSymbol = kibouShiftSymbol;
    }

}
