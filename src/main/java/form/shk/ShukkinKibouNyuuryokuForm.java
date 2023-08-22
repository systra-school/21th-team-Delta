/**
 * ファイル名：ShukkinKibouNyuuryokuForm.java
 *
 * 変更履歴
 * 1.0  2010/10/06 Kazuya.Naraki
 */
package form.shk;

import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import form.common.DateBean;

/**
 * 説明：出勤希望日入力フォーム
 * @author naraki
 *
 */
public class ShukkinKibouNyuuryokuForm extends ActionForm {

    /** 出勤希望入力リスト */
    private List<ShukkinKibouNyuuryokuBean> shukkinKibouNyuuryokuBeanList;
    /** 日付リスト */
    private List<DateBean> dateBeanList;
    /** 年月 */
    private String yearMonth = "";
    /** シフトコンボ */
    private Map<String, String> shiftCmbMap;
    /** 年月コンボ */
    private Map<String, String> yearMonthCmbMap;

    /**
     * @return yearMonth
     */
    public String getYearMonth() {
        return yearMonth;
    }
    /**
     * @param yearMonth をセットする
     */
    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
    /**
     * @return shukkinKibouNyuuryokuBeanList
     */
    public List<ShukkinKibouNyuuryokuBean> getShukkinKibouNyuuryokuBeanList() {
        return shukkinKibouNyuuryokuBeanList;
    }
    /**
     * @param shukkinKibouNyuuryokuBeanList をセットする
     */
    public void setShukkinKibouNyuuryokuBeanList(
            List<ShukkinKibouNyuuryokuBean> shukkinKibouNyuuryokuBeanList) {
        this.shukkinKibouNyuuryokuBeanList = shukkinKibouNyuuryokuBeanList;
    }
    /**
     * @return dateBeanList
     */
    public List<DateBean> getDateBeanList() {
        return dateBeanList;
    }
    /**
     * @param dateBeanList をセットする
     */
    public void setDateBeanList(List<DateBean> dateBeanList) {
        this.dateBeanList = dateBeanList;
    }
    /**
     * @return shiftCmbMap
     */
    public Map<String, String> getShiftCmbMap() {
        return shiftCmbMap;
    }
    /**
     * @param shiftCmbMap をセットする
     */
    public void setShiftCmbMap(Map<String, String> shiftCmbMap) {
        this.shiftCmbMap = shiftCmbMap;
    }
    /**
     * @return yearMonthCmbMap
     */
    public Map<String, String> getYearMonthCmbMap() {
        return yearMonthCmbMap;
    }
    /**
     * @param yearMonthCmbMap をセットする
     */
    public void setYearMonthCmbMap(Map<String, String> yearMonthCmbMap) {
        this.yearMonthCmbMap = yearMonthCmbMap;
    }

}
