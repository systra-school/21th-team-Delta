/**
 * ファイル名：LoginDto.java
 *
 * 変更履歴
 * 1.0  2010/08/07 Kazuya.Naraki
 */
package business.dto.cmn;

/**
 * 説明：ログイン処理のDto
 *
 * @author naraki
 *
 */
public class LoginDto {

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
