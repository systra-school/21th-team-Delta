/**
 * ファイル名：HibetsuShiftDao.java
 *
 * 変更履歴
 * 1.0  2010/10/23 Kazuya.Naraki
 */
package business.db.dao.day;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import constant.DbConstant.M_shain;
import constant.DbConstant.M_shift;
import business.db.dao.AbstractDao;
import business.dto.day.HibetsuShiftDto;
import business.logic.utils.CommonUtils;

/**
 * 説明：日別シフトDao
 * @author naraki
 *
 */
public class HibetsuShiftDao extends AbstractDao {

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * シフト情報を取得。
     * @param yearMonth 検索対象年月
     * @return 日別シフトリスト
     * @author Kazuya.Naraki
     */
    public List<HibetsuShiftDto> getHibetsuShiftDtoList(String yearMonthDay) throws SQLException {
        // 戻り値
        List<HibetsuShiftDto> hibetsuShiftDtoList = new ArrayList<HibetsuShiftDto>();

        try {
            // コネクション接続
            this.connect();

            /*
             * 社員マスタを主テーブルに
             * 特定の日付のシフト情報を取得する。
             */
            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT ");
            strSql.append("    MSHAIN.SHAIN_ID, ");
            strSql.append("    MSHAIN.SHAIN_NAME, ");
            strSql.append("    SHIFT.START_TIME, ");
            strSql.append("    SHIFT.END_TIME, ");
            strSql.append("    SHIFT.BREAK_TIME ");
            strSql.append("FROM ");
            strSql.append("    M_SHAIN MSHAIN INNER JOIN  ");
            strSql.append("    (SELECT ");
            strSql.append("        TSHIFT.SHAIN_ID, ");
            strSql.append("        MSHIFT.START_TIME, ");
            strSql.append("        MSHIFT.END_TIME, ");
            strSql.append("        MSHIFT.BREAK_TIME ");
            strSql.append("    FROM ");
            strSql.append("        T_SHIFT TSHIFT LEFT OUTER JOIN  ");
            strSql.append("        M_SHIFT MSHIFT ");
            strSql.append("        ON TSHIFT.SHIFT_ID = MSHIFT.SHIFT_ID ");
            strSql.append("    WHERE ");
            strSql.append("        YEAR_MONTH_DAY = ? ");
            strSql.append("    ) SHIFT ON  MSHAIN.SHAIN_ID = SHIFT.SHAIN_ID ");
            strSql.append("ORDER BY ");
            strSql.append("    SHAIN_ID, ");
            strSql.append("    START_TIME ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, yearMonthDay);

            // ログ出力
            log.info(ps);

            // SQLを実行する
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            while (rs.next()) {

                HibetsuShiftDto dto = new HibetsuShiftDto();
                dto.setShainName(CommonUtils.changeNullToBlank(rs.getString(M_shain.SHAIN_NAME.getName())));
                dto.setStartTime(CommonUtils.changeNullToBlank(rs.getString(M_shift.START_TIME.getName())));
                dto.setEndTime(CommonUtils.changeNullToBlank(rs.getString(M_shift.END_TIME.getName())));
                dto.setBreakTime(CommonUtils.changeNullToBlank(rs.getString(M_shift.BREAK_TIME.getName())));

                // 取得した値を戻り値のリストにセットする。
                hibetsuShiftDtoList.add(dto);
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
        return hibetsuShiftDtoList;
    }
}
