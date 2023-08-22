/**
 * ファイル名：TsukibetsuShiftNyuuryokuForm.java
 *
 * 変更履歴
 * 1.0  2010/10/06 Kazuya.Naraki
 */
package form.mth;

import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import form.common.DateBean;

/**
 * 説明：月別シフトフォーム
 * @author naraki
 *
 */
public class TsukibetsuShiftNyuuryokuForm extends ActionForm {

    /** 出勤希望入力リスト */
    private List<TsukibetsuShiftNyuuryokuBean> tsukibetsuShiftNyuuryokuBeanList;
    /** 日付リスト */
    private List<DateBean> dateBeanList;
    /** 年月 */
    private String yearMonth = "";
    /** シフトコンボ */
    private Map<String, String> shiftCmbMap;
    /** 年月コンボ */
    private Map<String, String> yearMonthCmbMap;

    /** ページング用 */
    private String paging;
    /** オフセット */
    private int offset;
    /** 表示ページ */
    private int cntPage;
    /** 最大ページ */
    private int maxPage;

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
     * @return offset
     */
    public int getOffset() {
        return offset;
    }
    /**
     * @param offset をセットする
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }
    /**
     * @return cntPage
     */
    public int getCntPage() {
        return cntPage;
    }
    /**
     * @param cntPage をセットする
     */
    public void setCntPage(int cntPage) {
        this.cntPage = cntPage;
    }
    /**
     * @return maxPage
     */
    public int getMaxPage() {
        return maxPage;
    }
    /**
     * @param maxPage をセットする
     */
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
    /**
     * @return tsukibetsuShiftNyuuryokuBeanList
     */
    public List<TsukibetsuShiftNyuuryokuBean> getTsukibetsuShiftNyuuryokuBeanList() {
        return tsukibetsuShiftNyuuryokuBeanList;
    }
    /**
     * @param tsukibetsuShiftNyuuryokuBeanList をセットする
     */
    public void setTsukibetsuShiftNyuuryokuBeanList(
            List<TsukibetsuShiftNyuuryokuBean> tsukibetsuShiftNyuuryokuBeanList) {
        this.tsukibetsuShiftNyuuryokuBeanList = tsukibetsuShiftNyuuryokuBeanList;
    }

}
