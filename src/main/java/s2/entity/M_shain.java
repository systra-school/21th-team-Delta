/**
 * ファイル名：M_shain.java
 *
 * 変更履歴
 * 1.0  2010/12/25 Kazuya.Naraki
 */
package s2.entity;

import java.sql.Timestamp;

/**
 * 説明：社員マスタ エンティティ
 * @author naraki
 *
 */
public class M_shain {
    public static final String TABLE = "M_SHAIN";

    // 社員ＩＤ
    private String shain_id;

    // パスワード
    private String password;

    // 社員名
    private String shain_name;

    // 社員名カナ
    private String shain_name_kana;

    // 権限ＩＤ
    private String kengen_id;

    // 作成社員ＩＤ
    private String create_shain_id;

    // 作成日付
    private Timestamp create_dt;

    // 更新社員ＩＤ
    private String update_shain_id;

    // 更新日付
    private Timestamp update_dt;

    /**
     * @return shain_id
     */
    public String getShain_id() {
        return shain_id;
    }

    /**
     * @param shain_id をセットする
     */
    public void setShain_id(String shain_id) {
        this.shain_id = shain_id;
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
     * @return shain_name
     */
    public String getShain_name() {
        return shain_name;
    }

    /**
     * @param shain_name をセットする
     */
    public void setShain_name(String shain_name) {
        this.shain_name = shain_name;
    }

    /**
     * @return shain_name_kana
     */
    public String getShain_name_kana() {
        return shain_name_kana;
    }

    /**
     * @param shain_name_kana をセットする
     */
    public void setShain_name_kana(String shain_name_kana) {
        this.shain_name_kana = shain_name_kana;
    }

    /**
     * @return kengen_id
     */
    public String getKengen_id() {
        return kengen_id;
    }

    /**
     * @param kengen_id をセットする
     */
    public void setKengen_id(String kengen_id) {
        this.kengen_id = kengen_id;
    }

    /**
     * @return create_shain_id
     */
    public String getCreate_shain_id() {
        return create_shain_id;
    }

    /**
     * @param create_shain_id をセットする
     */
    public void setCreate_shain_id(String create_shain_id) {
        this.create_shain_id = create_shain_id;
    }

    /**
     * @return create_dt
     */
    public Timestamp getCreate_dt() {
        return create_dt;
    }

    /**
     * @param create_dt をセットする
     */
    public void setCreate_dt(Timestamp create_dt) {
        this.create_dt = create_dt;
    }

    /**
     * @return update_shain_id
     */
    public String getUpdate_shain_id() {
        return update_shain_id;
    }

    /**
     * @param update_shain_id をセットする
     */
    public void setUpdate_shain_id(String update_shain_id) {
        this.update_shain_id = update_shain_id;
    }

    /**
     * @return update_dt
     */
    public Timestamp getUpdate_dt() {
        return update_dt;
    }

    /**
     * @param update_dt をセットする
     */
    public void setUpdate_dt(Timestamp update_dt) {
        this.update_dt = update_dt;
    }
}
