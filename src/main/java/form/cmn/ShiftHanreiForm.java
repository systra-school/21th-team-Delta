/**
 * ファイル名：ShiftHanreiForm.java
 *
 * 変更履歴
 * 1.0  2010/11/04 Kazuya.Naraki
 */
package form.cmn;

import java.util.List;

import org.apache.struts.action.ActionForm;


/**
 * 説明：基本シフト入力確認フォーム
 * @author naraki
 *
 */
public class ShiftHanreiForm extends ActionForm {

    /**
     *シリアル
     */
    private static final long serialVersionUID = 1483629197030517493L;

    /** 社員ID */
    private String shainId;
    /** 社員名 */
    private String shainName;

    /** 設定済み基本シフトBeanList */
    private List<ShiftHanreiBean> shiftHanreiBeanList;

    /**
     * @return shainId
     */
    public String getShainId() {
        return shainId;
    }
    /**
     * @param shainId セットする shainId
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
     * @param shainName セットする shainName
     */
    public void setShainName(String shainName) {
        this.shainName = shainName;
    }

    /**
     * @return shiftHanreiBeanList
     */
    public List<ShiftHanreiBean> getShiftHanreiBeanList() {
        return shiftHanreiBeanList;
    }
    /**
     * @param shiftHanreiBeanList をセットする
     */
    public void setShiftHanreiBeanList(List<ShiftHanreiBean> shiftHanreiBeanList) {
        this.shiftHanreiBeanList = shiftHanreiBeanList;
    }
}