/**
 * ファイル名：HibetsuShiftForm.java
 *
 * 変更履歴
 * 1.0  2010/10/23 Kazuya.Naraki
 */
package form.day;

import java.util.List;

import org.apache.struts.action.ActionForm;

/**
 * 説明：日別シフトフォーム
 * @author naraki
 *
 */
public class HibetsuShiftForm extends ActionForm {

    /** 日別シフトBeanリスト（表示一覧）*/
    private List<HibetsuShiftBean> hibetsuShiftBeanList;

    /** 表示対象日 */
    private String yearMonthDay;
    /** 表示対象日（画面表示用） */
    private String yearMonthDayDisp;

    /** ページング用 */
    private String paging;

    /**
     * @return hibetsuShiftBeanList
     */
    public List<HibetsuShiftBean> getHibetsuShiftBeanList() {
        return hibetsuShiftBeanList;
    }

    /**
     * @param hibetsuShiftBeanList をセットする
     */
    public void setHibetsuShiftBeanList(List<HibetsuShiftBean> hibetsuShiftBeanList) {
        this.hibetsuShiftBeanList = hibetsuShiftBeanList;
    }

    /**
     * @return paging
     */
    public String getPaging() {
        return paging;
    }

    /**
     * @param paging をセットする
     */
    public void setPaging(String paging) {
        this.paging = paging;
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
     * @return yearMonthDayDisp
     */
    public String getYearMonthDayDisp() {
        return yearMonthDayDisp;
    }

    /**
     * @param yearMonthDayDisp をセットする
     */
    public void setYearMonthDayDisp(String yearMonthDayDisp) {
        this.yearMonthDayDisp = yearMonthDayDisp;
    }



}
