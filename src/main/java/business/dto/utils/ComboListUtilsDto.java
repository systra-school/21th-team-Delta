/**
 * ファイル名：ComboListUtilsDto.java
 *
 * 変更履歴
 * 1.0  2010/08/29 Kazuya.Naraki
 */
package business.dto.utils;

import java.util.Date;

/**
 * 説明：分類マスタDto
 * @author naraki
 *
 */
public class ComboListUtilsDto {
    /** 分類ID */
    private String bunruiId;
    /** コード */
    private String code;
    /** 名称 */
    private String name;
    /** 表示順 */
    private String hyoujiJyun;
    /** 表示１ */
    private boolean hyouji1;
    /** 表示２ */
    private boolean hyouji2;
    /** 表示３ */
    private boolean hyouji3;
    /** 表示４ */
    private boolean hyouji4;
    /** 表示５ */
    private boolean hyouji5;
    /** 作成ユーザID */
    private String createShainId;
    /** 作成日付 */
    private Date createDt;
    /** 更新ユーザID */
    private String updateShainId;
    /** 更新日付 */
    private Date updateDt;

    /**
     * @return bunruiId
     */
    public String getBunruiId() {
        return bunruiId;
    }
    /**
     * @param bunruiId をセットする
     */
    public void setBunruiId(String bunruiId) {
        this.bunruiId = bunruiId;
    }
    /**
     * @return code
     */
    public String getCode() {
        return code;
    }
    /**
     * @param code をセットする
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name をセットする
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return hyoujiJyun
     */
    public String getHyoujiJyun() {
        return hyoujiJyun;
    }
    /**
     * @param hyoujiJyun をセットする
     */
    public void setHyoujiJyun(String hyoujiJyun) {
        this.hyoujiJyun = hyoujiJyun;
    }
    /**
     * @return hyouji1
     */
    public boolean getHyouji1() {
        return hyouji1;
    }
    /**
     * @param hyouji1 をセットする
     */
    public void setHyouji1(boolean hyouji1) {
        this.hyouji1 = hyouji1;
    }
    /**
     * @return hyouji2
     */
    public boolean getHyouji2() {
        return hyouji2;
    }
    /**
     * @param hyouji2 をセットする
     */
    public void setHyouji2(boolean hyouji2) {
        this.hyouji2 = hyouji2;
    }
    /**
     * @return hyouji3
     */
    public boolean getHyouji3() {
        return hyouji3;
    }
    /**
     * @param hyouji3 をセットする
     */
    public void setHyouji3(boolean hyouji3) {
        this.hyouji3 = hyouji3;
    }
    /**
     * @return hyouji4
     */
    public boolean getHyouji4() {
        return hyouji4;
    }
    /**
     * @param hyouji4 をセットする
     */
    public void setHyouji4(boolean hyouji4) {
        this.hyouji4 = hyouji4;
    }
    /**
     * @return hyouji5
     */
    public boolean getHyouji5() {
        return hyouji5;
    }
    /**
     * @param hyouji5 をセットする
     */
    public void setHyouji5(boolean hyouji5) {
        this.hyouji5 = hyouji5;
    }
    /**
     * @return createShainId
     */
    public String getCreateShainId() {
        return createShainId;
    }
    /**
     * @param createShainId をセットする
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
     * @param createDt をセットする
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
     * @param updateShainId をセットする
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
     * @param updateDt をセットする
     */
    public void setUpdateDt(Date updateDt) {
        this.updateDt = updateDt;
    }

}
