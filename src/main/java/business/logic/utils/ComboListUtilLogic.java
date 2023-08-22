/**
 * ファイル名：ComboListUtils.java
 *
 * 変更履歴
 * 1.0  2010/08/29 Kazuya.Naraki
 */
package business.logic.utils;
import static constant.DbConstant.Mbunrui;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;

import constant.CommonConstant;

import exception.CommonException;


import business.db.dao.utils.ComboListUtilsDao;
import business.dto.utils.ComboListUtilsDto;

/**
 * 説明：コンボボックス生成共通部品
 * @author naraki
 *
 */
public class ComboListUtilLogic {

    /** 年月コンボ区分(前年) */
    public static final int KBN_YEARMONTH_PRE = 0;
    /** 年月コンボ区分(次年) */
    public static final int KBN_YEARMONTH_NEXT = 1;
    /** 年月コンボ区分(前年次年) */
    public static final int KBN_YEARMONTH_PRE_NEXT= 2;

    // 分類マスタDao
    private ComboListUtilsDao mbunruiDao = new ComboListUtilsDao();


    /**
     * コンボボックスのマップを取得する。
     * @param bunruiId 分類ＩＤ
     * @param hyouji 表示対象を絞りこむために使用するカラム名
     * @param blankFlg 空白有りフラグ true：空白あり false：空白行なし
     * @return
     * @author naraki
     */
    public Map<String, String> getCombo(String bunruiId, String hyouji,
            boolean blankFlg) throws SQLException, CommonException {

        // 検索条件
        ComboListUtilsDto mbunruiSearch = new ComboListUtilsDto();

        // 分類ＩＤ
        mbunruiSearch.setBunruiId(bunruiId);

        // 表示対象を設定する
        if (Mbunrui.HYOUJI1.getName().equals(hyouji)) {
            mbunruiSearch.setHyouji1(true);
        } else if (Mbunrui.HYOUJI2.getName().equals(hyouji)) {
            mbunruiSearch.setHyouji2(true);
        } else if (Mbunrui.HYOUJI3.getName().equals(hyouji)) {
            mbunruiSearch.setHyouji3(true);
        } else if (Mbunrui.HYOUJI4.getName().equals(hyouji)) {
            mbunruiSearch.setHyouji4(true);
        } else if (Mbunrui.HYOUJI5.getName().equals(hyouji)) {
            mbunruiSearch.setHyouji5(true);
        } else {
            throw new CommonException("不正な値 hyouji：" + hyouji);
        }

        // コンボマップを取得する。
        Map<String, String> cmbMap = mbunruiDao.getComboMap(mbunruiSearch,
                                                            blankFlg);

        return cmbMap;
    }

    /**
     * コンボボックス(社員)のマップを取得する。
     * @param blankFlg 空白有りフラグ true：空白あり false：空白行なし
     * @return
     * @author naraki
     */
    public Map<String, String> getComboShain(boolean blankFlg) throws SQLException, CommonException {

        // コンボマップを取得する。
        Map<String, String> cmbMap = mbunruiDao.getShainComboMap(blankFlg);

        return cmbMap;
    }

    /**
     * コンボボックス(シフト)のマップを取得する。
     * @param blankFlg 空白有りフラグ true：空白あり false：空白行なし
     * @return key：シフトID,value：シンボル
     * @author naraki
     */
    public Map<String, String> getComboShift(boolean blankFlg) throws SQLException, CommonException {

        // コンボマップを取得する。
        Map<String, String> cmbMap = mbunruiDao.getShiftComboMap(blankFlg);

        return cmbMap;
    }

    /**
     * コンボボックス(年月)のマップを取得する。
     * @param yearMonth 対象年月
     * @param cnt 対象年月から表示させる月数
     * @param blankFlg 空白有りフラグ true：空白あり false：空白行なし
     * @return
     * @author naraki
     */
    public Map<String, String> getComboYearMonth(String yearMonth, int cnt, int kbn, boolean blankFlg) throws SQLException, CommonException {

        // コンボマップを取得する。
        Map<String, String> cmbMap = new LinkedHashMap<String, String>();

        // 空白追加
        if (blankFlg) {
            // 空白あり
            cmbMap.put("-1", CommonConstant.BLANK);
        }

        // 年
        int intYear = Integer.parseInt(yearMonth.substring(0, 4));
        // 月
        int intMonth = Integer.parseInt(yearMonth.substring(4, 6)) - 1;
        // 日
        int day = 1;

        Calendar calendar = new GregorianCalendar(intYear, intMonth, day);

        StringBuffer strBuf = new StringBuffer();
        intYear = calendar.get(Calendar.YEAR);
        intMonth = calendar.get(Calendar.MONTH) + 1;

        strBuf.append(String.valueOf(intYear));
        if (intMonth < 10) {
            strBuf.append("0");
        }
        strBuf.append(String.valueOf(intMonth));


        if (kbn == KBN_YEARMONTH_PRE) {
            // 対象年月から前の年月

            cmbMap.put(strBuf.toString(), CommonUtils.addSlash(strBuf.toString()));

            for (int i = cnt; i >= 0; i--) {
                strBuf = new StringBuffer();
                // 加算処理
                CommonUtils.add(calendar, 0, -1, 0);

                this.putYearMonthMap(calendar, cmbMap);
            }
        } else if (kbn == KBN_YEARMONTH_NEXT) {
            // 対象年月から次の年月

            cmbMap.put(strBuf.toString(), CommonUtils.addSlash(strBuf.toString()));

            for (int i = 0; i < cnt; i++) {

                // 加算処理
                CommonUtils.add(calendar, 0, 1, 0);

                this.putYearMonthMap(calendar, cmbMap);
            }
        } else {
            // 対象年月から前年、次年
            CommonUtils.add(calendar, 0, -cnt, 0);

            this.putYearMonthMap(calendar, cmbMap);

            for (int i = 0; i < cnt * 2; i++) {

                // 加算処理
                CommonUtils.add(calendar, 0, 1, 0);

                this.putYearMonthMap(calendar, cmbMap);
            }

        }

        return cmbMap;
    }

    /**
     * 年月コンボのマップに値をセットする
     * @param calendar カレンダー
     * @param cmbMap 年月コンボマップ
     * @author naraki
     */
    private void putYearMonthMap(Calendar calendar, Map<String, String> cmbMap) {

        StringBuffer strBuf = new StringBuffer();

        // 年、月を取得する
        int intYear = calendar.get(Calendar.YEAR);
        int intMonth = calendar.get(Calendar.MONTH) + 1;

        strBuf.append(String.valueOf(intYear));
        if (intMonth < 10) {
            strBuf.append("0");
        }
        strBuf.append(String.valueOf(intMonth));
        cmbMap.put(strBuf.toString(), CommonUtils.addSlash(strBuf.toString()));

    }


}
