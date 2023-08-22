/**
 * ファイル名：ShainMstMntBean.java
 *
 * 変更履歴
 * 1.0  2010/08/23 Kazuya.Naraki
 */
package form.mst;

/**
 * 説明：社員マスタメンテナンスフォームクラス
 * @author naraki
 *
 */
public class ShainMstMntBean {

    /** 社員ＩＤ */
    private String shainId;
    /** パスワード */
    private String password;
    /** 社員名 */
    private String shainName;
    /** 社員名カナ */
    private String shainNameKana;
    /** 権限ＩＤ */
    private String kengenId;
    /** 削除社員 */
    private boolean deleteFlg;
    /** 削除社員ＩＤ */
    private String deleteShainId;

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
     * @return password
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param password をセットする
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
     * @param shainNameKana をセットする
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
     * @param kengenId をセットする
     */
    public void setKengenId(String kengenId) {
        this.kengenId = kengenId;
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
    /**
     * @return deleteShainId
     */
    public String getDeleteShainId() {
        return deleteShainId;
    }
    /**
     * @param deleteShainId をセットする
     */
    public void setDeleteShainId(String deleteShainId) {
        this.deleteShainId = deleteShainId;
    }

}
