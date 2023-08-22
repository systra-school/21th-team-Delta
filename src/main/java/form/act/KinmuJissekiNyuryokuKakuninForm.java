/**
 * ファイル名：KinmuJissekiNyuryokuKakuninForm.java
 *
 * 変更履歴
 * 1.0  2010/11/04 Kazuya.Naraki
 */
package form.act;

import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForm;

import form.common.DateBean;

/**
 * 説明：勤務実績入力確認フォーム
 * @author naraki
 *
 */
public class KinmuJissekiNyuryokuKakuninForm extends ActionForm {

    /** 勤務実績入力確認BeanList */
    private List<KinmuJissekiNyuryokuKakuninBean> kinmuJissekiNyuryokuKakuninList;
    /** 日付リスト */
    private List<DateBean> dateBeanList;
    /** 年月コンボ */
    private Map<String, String> yearMonthCmbMap;
    /** 年月 */
    private String yearMonth;
    /** 社員コンボ */
    private Map<String, String> shainCmbMap;
    /** 社員ID */
    private String shainId;
    /** 社員名 */
    private String shainName;

    /** ページング用 */
    private String paging;

    /**
     * @return kinmuJissekiNyuryokuKakuninList
     */
    public List<KinmuJissekiNyuryokuKakuninBean> getKinmuJissekiNyuryokuKakuninList() {
        return kinmuJissekiNyuryokuKakuninList;
    }
    /**
     * @param kinmuJissekiNyuryokuKakuninList をセットする
     */
    public void setKinmuJissekiNyuryokuKakuninList(
            List<KinmuJissekiNyuryokuKakuninBean> kinmuJissekiNyuryokuKakuninList) {
        this.kinmuJissekiNyuryokuKakuninList = kinmuJissekiNyuryokuKakuninList;
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
     * @return shainCmbMap
     */
    public Map<String, String> getShainCmbMap() {
        return shainCmbMap;
    }
    /**
     * @param shainCmbMap をセットする
     */
    public void setShainCmbMap(Map<String, String> shainCmbMap) {
        this.shainCmbMap = shainCmbMap;
    }

}