/**
 * ファイル名：ComboListUtilsDao.java
 *
 * 変更履歴
 * 1.0  2010/08/29 Kazuya.Naraki
 */
package business.db.dao.utils;
import static constant.DbConstant.Mbunrui;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import constant.CommonConstant;
import constant.DbConstant.M_shain;
import constant.DbConstant.M_shift;


import business.db.dao.AbstractDao;
import business.dto.utils.ComboListUtilsDto;

/**
 * 説明：分類マスタアクセス部品
 * @author naraki
 *
 */
public class ComboListUtilsDao extends AbstractDao {

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 分類マスタより対象レコードを取得しリストで返却する。
     *
     * @param 検索用エンティティ
     * @return 分類マスタ検索Ｄｔｏ
     * @author Kazuya.Naraki
     */
    public Map<String, String> getComboMap(ComboListUtilsDto mbunruiSearch,
            boolean blankFlg) throws SQLException {

        Map<String, String> comboMap = new LinkedHashMap<String, String>();

        if (blankFlg) {
            // 空白あり
            comboMap.put("-1", CommonConstant.BLANK);
        }

        boolean hyouji1Bool = mbunruiSearch.getHyouji1();
        boolean hyouji2Bool = mbunruiSearch.getHyouji2();
        boolean hyouji3Bool = mbunruiSearch.getHyouji3();
        boolean hyouji4Bool = mbunruiSearch.getHyouji4();
        boolean hyouji5Bool = mbunruiSearch.getHyouji5();

        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT * FROM M_BUNRUI ");
            strSql.append("WHERE BUNRUI_ID = ? ");
            if (hyouji1Bool) {
                strSql.append("AND HYOUJI1 = ? ");
            }
            if (hyouji2Bool) {
                strSql.append("AND HYOUJI1 = ? ");
            }
            if (hyouji3Bool) {
                strSql.append("AND HYOUJI1 = ? ");
            }
            if (hyouji4Bool) {
                strSql.append("AND HYOUJI1 = ? ");
            }
            if (hyouji5Bool) {
                strSql.append("AND HYOUJI1 = ? ");
            }
            strSql.append("ORDER BY HYOUJI_JYUN ASC ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            int index = 1;
            // 分類ＩＤ
            ps.setString(index, mbunruiSearch.getBunruiId());
            index++;
            // 表示１
            if (hyouji1Bool) {
                ps.setBoolean(index, hyouji1Bool);
                index++;
            }
            // 表示２
            if (hyouji2Bool) {
                ps.setBoolean(index, hyouji2Bool);
                index++;
            }
            // 表示３
            if (hyouji3Bool) {
                ps.setBoolean(index, hyouji3Bool);
                index++;
            }
            // 表示４
            if (hyouji4Bool) {
                ps.setBoolean(index, hyouji4Bool);
                index++;
            }
            // 表示５
            if (hyouji5Bool) {
                ps.setBoolean(index, hyouji5Bool);
                index++;
            }

            // ログ出力
            log.info(ps);

            // 実行
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            while (rs.next()) {
                comboMap.put(rs.getString(Mbunrui.CODE.getName()), rs.getString(Mbunrui.NAME.getName()));
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
        return comboMap;
    }

    /**
     * シフトマスタよりコンボ用のマップを作成する。
     * @param 空白ありフラグ
     * @return シフトコンボマップ
     * @author Kazuya.Naraki
     */
    public Map<String, String> getShiftComboMap(boolean blankFlg) throws SQLException{

        // 戻り値
        Map<String, String> comboMap = new LinkedHashMap<String, String>();

        if (blankFlg) {
            // 空白あり
            comboMap.put(CommonConstant.BLANK_ID, CommonConstant.BLANK);
        }

        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT * FROM ");
            strSql.append("M_SHIFT ");
            strSql.append(" ORDER BY SHIFT_ID ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            // ログ出力
            log.info(ps);

            // SQLを実行する
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            while (rs.next()) {
                comboMap.put(rs.getString(M_shift.SHIFT_ID.getName()), rs.getString(M_shift.SYMBOL.getName()));
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
        return comboMap;
    }

    /**
     * 社員マスタよりコンボ用のマップを作成する。
     * @param 空白ありフラグ
     * @return 社員コンボマップ
     * @author Kazuya.Naraki
     */
    public Map<String, String> getShainComboMap(boolean blankFlg) throws SQLException{

        // 戻り値
        Map<String, String> comboMap = new LinkedHashMap<String, String>();

        if (blankFlg) {
            // 空白あり
            comboMap.put("-1", CommonConstant.BLANK);
        }

        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT SHAIN_ID, ");
            strSql.append("CONCAT(CONCAT(SHAIN_ID ,':'), SHAIN_NAME) SHAIN_NAME ");
            strSql.append("FROM ");
            strSql.append("M_SHAIN ");
            strSql.append("ORDER BY SHAIN_ID ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            // ログ出力
            log.info(ps);

            // SQLを実行する
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            while (rs.next()) {
                comboMap.put(rs.getString(M_shain.SHAIN_ID.getName()), rs.getString(M_shain.SHAIN_NAME.getName()));
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
        return comboMap;
    }
}
