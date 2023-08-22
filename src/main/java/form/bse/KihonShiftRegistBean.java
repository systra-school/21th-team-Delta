/**
 * ファイル名：KihonShiftRegistForm.java
 *
 * 変更履歴
 * 1.0  2010/10/05 Satoshi.Sugi
 */
package form.bse;

/**
 * 説明：基本シフトフォームクラス
 * @author sugi
 *
 */
public class KihonShiftRegistBean {

	/* シンボル */
	private String symble = "";
	/* TO時間 */
	private String toTime = "";
	/* FROM時間 */
	private String fromTime = "";
	/* 休憩 */
	private String breakTime = "";

	public String getSymble() {
		return symble;
	}
	public void setSymble(String symble) {
		this.symble = symble;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(String breakTime) {
		this.breakTime = breakTime;
	}
}