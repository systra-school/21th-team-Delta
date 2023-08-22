/**
 * ファイル名：ShainMstMntDto.java
 *
 * 変更履歴
 * 1.0  2010/07/19 Kazuya.Naraki
 */
package business.dto.mst;

import java.util.Date;

/**
 * 説明：社員マスタDto
 * @author naraki
 *
 */
public class ShainMstMntDto {

    /** 社員ID */
    private String shainId;
    /** パスワード */
    private String password;
    /** 社員名 */
    private String shainName;
    /** 社員名カナ */
    private String shainNameKana;
    /** 権限ID */
    private String kengenId;
    /** 作成ユーザID */
    private String createShainId;
    /** 作成日付 */
    private Date createDt;
    /** 更新ユーザID */
    private String updateShainId;
    /** 更新日付 */
    private Date updateDt;

    /** 削除フラグ */
    private boolean deleteFlg;

    /**
     * @return shainId
     */
    public String getShainId() {
        return shainId;
    }

    /**
     * @param shainId
     * セットする shainId
     */
    public void setShainId(String shainId) {
        this.shainId = shainId;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     * セットする password
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return shainNameKana
     */
    public String getShainNameKana() {
        return shainNameKana;
    }

    /**
     * @param shainNameKana
     * セットする shainNameKana
     */
    public void setShainNameKana(String shainNameKana) {
        this.shainNameKana = shainNameKana;
    }

    /**
     * @return kengenId
     */
    public String getKengenId() {
        return kengenId;
    }

    /**
     * @param kengenId
     * セットする kengenId
     */
    public void setKengenId(String kengenId) {
        this.kengenId = kengenId;
    }

    /**
     * @return createShainId
     */
    public String getCreateShainId() {
        return createShainId;
    }

    /**
     * @param createShainId
     * セットする createShainId
     */
    public void setCreateShainId(String createShainId) {
        this.createShainId = createShainId;
    }

    /**
     * @return createDt
     */
    public Date getCreateDt() {
        return createDt;
    }

    /**
     * @param createDt
     * セットする createDt
     */
    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    /**
     * @return updateShainId
     */
    public String getUpdateShainId() {
        return updateShainId;
    }

    /**
     * @param updateShainId
     * セットする updateShainId
     */
    public void setUpdateShainId(String updateShainId) {
        this.updateShainId = updateShainId;
    }

    /**
     * @return updateDt
     */
    public Date getUpdateDt() {
        return updateDt;
    }

    /**
     * @param updateDt
     * セットする updateDt
     */
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

    /**
     * @return deleteFlg
     */
    public boolean getDeleteFlg() {
        return deleteFlg;
    }

    /**
     * @param deleteFlg をセットする
     */
    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }


}
