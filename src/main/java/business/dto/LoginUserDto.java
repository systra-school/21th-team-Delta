/**
 * ファイル名：LoginUserDto.java
 *
 * 変更履歴
 * 1.0  2010/08/09 Kazuya.Naraki
 */
package business.dto;

/**
 * 説明：ログインユーザ保持用Dto
 *       ログイン画面以外での書き換え、セットの呼び出しは禁止
 * @author naraki
 *
 */
public class LoginUserDto {

    // 社員ID
    private String shainId;
    // パスワード
    private String password;
    // 社員名
    private String shainName;
    // 社員名カナ
    private String shainNameKana;
    // 権限ID
    private String kengenId;

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
    public String getpassword() {
        return password;
    }

    /**
     * @param password
     * セットする password
     */
    public void setpassword(String password) {
        this.password = password;
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
     * @return shainName
     */
    public String getShainName() {
        return shainName;
    }

    /**
     * @param shainName
     * セットする shainName
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
}
