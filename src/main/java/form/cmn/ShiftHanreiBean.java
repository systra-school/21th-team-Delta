/**
 * ファイル名：ShiftHanreiBean.java
 *
 * 変更履歴
 * 1.0  2010/11/04 Kazuya.Naraki
 */
package form.cmn;

/**
 * 説明：基本シフト凡例
 * @author nishioka
 *
 */
public class ShiftHanreiBean {

    /** シフト名 */
    private String shiftName;
    /** シンボル */
    private String symbol;
    /** 時間帯 */
    private String timeZone;
    /** 休憩 */
    private String kyukei;
	/**
	 * @return shiftName
	 */
	public String getShiftName() {
		return shiftName;
	}
	/**
	 * @param shiftName セットする shiftName
	 */
	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
	/**
	 * @return simbol
	 */
	public String getSymbol() {
		return symbol;
	}
	/**
	 * @param simbol セットする simbol
	 */
	public void setSymbol(String simbol) {
		this.symbol = simbol;
	}
	/**
	 * @return timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}
	/**
	 * @param timeZone セットする timeZone
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	/**
	 * @return kyukei
	 */
	public String getKyukei() {
		return kyukei;
	}
	/**
	 * @param kyukei セットする kyukei
	 */
	public void setKyukei(String kyukei) {
		this.kyukei = kyukei;
	}


}
