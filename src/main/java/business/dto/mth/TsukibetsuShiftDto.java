/**
 * ファイル名：TsukibetsuShiftDto.java
 *
 * 変更履歴
 * 1.0  2010/10/06 Kazuya.Naraki
 */
package business.dto.mth;

/**
 * 説明：月別シフトDto
 * @author naraki
 *
 */
public class TsukibetsuShiftDto {
    /** 社員ID */
    private String shainId;
    /** 社員名 */
    private String shainName;
    /** 年月日 */
    private String yearMonthDay;
    /** シフトID */
    private String shiftId;
    /** シンボル */
    private String symbol;

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


}
