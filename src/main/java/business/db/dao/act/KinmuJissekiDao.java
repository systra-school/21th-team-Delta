/**
 * ファイル名：KinmuJissekiDao.java
 *
 * 変更履歴
 * 1.0  2010/07/19 Kazuya.Naraki
 */
package business.db.dao.act;

import static constant.DbConstant.T_Kinmu_Jisseki;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import constant.DbConstant.M_shift;

import business.db.dao.AbstractDao;
import business.dto.act.KinmuJissekiDto;
import business.logic.utils.CheckUtils;

/**
 * 説明：勤務実績Dao
 *
 * @author naraki
 *
 */
public class KinmuJissekiDao extends AbstractDao {

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * シフト、勤務実績のデータを取得する
     *
     * @param shainId 社員ID
     * @param startDay 開始日
     * @param endDay 終了日
     * @return 勤務実績マップ
     * @author Kazuya.Naraki
     */
    public Map<String, KinmuJissekiDto> getKinmuJissekiShiftData(String shainId, String startDay, String endDay) throws SQLException {
        // 戻り値 Key：稼動日, value：勤務実績Dto
        Map<String, KinmuJissekiDto> kinmuJissekiMap = new LinkedHashMap<String, KinmuJissekiDto>();

        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT ");
            strSql.append("    SHIFT.SHAIN_ID         AS SHAIN_ID, ");
            strSql.append("    SHIFT.SHIFT_ID         AS SHIFT_ID, ");
            strSql.append("    SHIFT.SYMBOL           AS SYMBOL, ");
            strSql.append("    SHIFT.START_TIME_SHIFT AS START_TIME_SHIFT, ");
            strSql.append("    SHIFT.END_TIME_SHIFT   AS END_TIME_SHIFT,  ");
            strSql.append("    SHIFT.BREAK_TIME_SHIFT AS BREAK_TIME_SHIFT, ");
            strSql.append("    (CASE WHEN SHIFT.KADOU_DAY IS NULL THEN TKJ.KADOU_DAY  ");
            strSql.append("    ELSE SHIFT.KADOU_DAY END) AS KADOU_DAY, ");
            strSql.append("    TKJ.START_TIME         AS START_TIME, ");
            strSql.append("    TKJ.END_TIME           AS END_TIME, ");
            strSql.append("    TKJ.BREAK_TIME         AS BREAK_TIME, ");
            strSql.append("    TKJ.JITSUDOU_TIME      AS JITSUDOU_TIME, ");
            strSql.append("    TKJ.JIKANGAI_TIME      AS JIKANGAI_TIME, ");
            strSql.append("    TKJ.KYUUJITSU_TIME     AS KYUUJITSU_TIME, ");
            strSql.append("    TKJ.BIKOU              AS BIKOU ");
            strSql.append("FROM ");
            strSql.append("    (SELECT ");
            strSql.append("        SHAIN_ID, ");
            strSql.append("        KADOU_DAY ");
            strSql.append("    FROM ");
            strSql.append("        T_KINMU_JISSEKI ");
            strSql.append("    WHERE ");
            strSql.append("        SHAIN_ID = ? AND ");
            strSql.append("        kadou_day >= ? AND ");
            strSql.append("        kadou_day <= ? ");
            strSql.append("    UNION   ");
            strSql.append("    SELECT ");
            strSql.append("        SHAIN_ID, ");
            strSql.append("        YEAR_MONTH_DAY KADOU_DAY ");
            strSql.append("    FROM ");
            strSql.append("        T_SHIFT ");
            strSql.append("    WHERE ");
            strSql.append("        SHAIN_ID = ? AND ");
            strSql.append("        YEAR_MONTH_DAY >= ? AND ");
            strSql.append("        YEAR_MONTH_DAY <= ? ");
            strSql.append("    ORDER BY ");
            strSql.append("        KADOU_DAY ");
            strSql.append("    ) BASE  ");
            strSql.append("LEFT OUTER JOIN T_KINMU_JISSEKI TKJ ON BASE.SHAIN_ID = TKJ.SHAIN_ID AND BASE.KADOU_DAY = TKJ.KADOU_DAY ");
            strSql.append("LEFT OUTER JOIN ( ");
            strSql.append("    SELECT ");
            strSql.append("        TS.SHAIN_ID, ");
            strSql.append("        TS.YEAR_MONTH_DAY AS KADOU_DAY, ");
            strSql.append("        MS.SHIFT_ID AS SHIFT_ID, ");
            strSql.append("        MS.SYMBOL, ");
            strSql.append("        MS.START_TIME AS START_TIME_SHIFT, ");
            strSql.append("        MS.END_TIME AS END_TIME_SHIFT, ");
            strSql.append("        MS.BREAK_TIME AS BREAK_TIME_SHIFT ");
            strSql.append("    FROM ");
            strSql.append("        T_SHIFT TS LEFT OUTER JOIN M_SHIFT MS ON TS.SHIFT_ID = MS.SHIFT_ID ");
            strSql.append(") SHIFT ON BASE.SHAIN_ID = SHIFT.SHAIN_ID AND BASE.KADOU_DAY = SHIFT.KADOU_DAY ");
            strSql.append("ORDER BY ");
            strSql.append("    TKJ.KADOU_DAY ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, shainId);
            ps.setString(2, startDay);
            ps.setString(3, endDay);
            ps.setString(4, shainId);
            ps.setString(5, startDay);
            ps.setString(6, endDay);

            // ログ出力
            log.info(ps);

            // 実行
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            while (rs.next()) {
                KinmuJissekiDto kinmuJissekiDto = new KinmuJissekiDto();

                String kadouDay = rs.getString(T_Kinmu_Jisseki.KADOU_DAY.getName());
                String startShift = rs.getString("START_TIME_SHIFT"); // 開始時間(シフト)
                String endShift = rs.getString("END_TIME_SHIFT"); // 終了時間(シフト)
                String breakShift = rs.getString("BREAK_TIME_SHIFT"); // 休憩時間(シフト)

                String startTime = rs.getString(T_Kinmu_Jisseki.START_TIME.getName()); // 開始時間
                String endTime = rs.getString(T_Kinmu_Jisseki.END_TIME.getName()); // 終了時間
                String breakTime = rs.getString(T_Kinmu_Jisseki.BREAK_TIME.getName()); // 休憩時間

                kinmuJissekiDto.setShainId(rs.getString(T_Kinmu_Jisseki.SHAIN_ID.getName())); // 社員ID
                kinmuJissekiDto.setKadouDay(kadouDay); // 稼働日
                kinmuJissekiDto.setShiftId(rs.getString(M_shift.SHIFT_ID.getName())); // シフトID
                kinmuJissekiDto.setSymbol(rs.getString(M_shift.SYMBOL.getName())); // シンボル
                kinmuJissekiDto.setStartTimeShift(startShift); // 開始時間(シフト)
                kinmuJissekiDto.setEndTimeShift(endShift); // 終了時間(シフト)
                kinmuJissekiDto.setBreakTimeShift(breakShift); // 休憩時間(シフト)
                if (CheckUtils.isEmpty(startTime)) {
                    kinmuJissekiDto.setStartTime(startShift); // 開始時間
                } else {
                    kinmuJissekiDto.setStartTime(startTime); // 開始時間
                }
                if (CheckUtils.isEmpty(endTime)) {
                    kinmuJissekiDto.setEndTime(endShift); // 終了時間
                } else {
                    kinmuJissekiDto.setEndTime(endTime); // 終了時間
                }
                if (CheckUtils.isEmpty(breakTime)) {
                    kinmuJissekiDto.setBreakTime(breakShift); // 休憩時間
                } else {
                    kinmuJissekiDto.setBreakTime(breakTime); // 休憩時間
                }
                kinmuJissekiDto.setJitsudouTime(rs.getString(T_Kinmu_Jisseki.JITSUDOU_TIME.getName())); // 実働時間
                kinmuJissekiDto.setJikangaiTime(rs.getString(T_Kinmu_Jisseki.JIKANGAI_TIME.getName())); // 時間外時間
                kinmuJissekiDto.setKyuujitsuTime(rs.getString(T_Kinmu_Jisseki.KYUUJITSU_TIME.getName())); // 休日時間
                kinmuJissekiDto.setBikou(rs.getString(T_Kinmu_Jisseki.BIKOU.getName())); // 備考

                kinmuJissekiMap.put(kadouDay, kinmuJissekiDto);
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
        return kinmuJissekiMap;
    }
    /**
     * 勤務実績のデータを取得する
     *
     * @param shainId 社員ID
     * @param startDay 開始日
     * @param endDay 終了日
     * @return 勤務実績マップ
     * @author Kazuya.Naraki
     */
    public Map<String, KinmuJissekiDto> getKinmuJissekiData(String shainId, String startDay, String endDay) throws SQLException {
        // 戻り値 Key：稼動日, value：勤務実績Dto
        Map<String, KinmuJissekiDto> kinmuJissekiMap = new LinkedHashMap<String, KinmuJissekiDto>();

        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT ");
            strSql.append("    SHIFT.SHAIN_ID, ");
            strSql.append("    SHIFT.SHIFT_ID, ");
            strSql.append("    SHIFT.SYMBOL, ");
            strSql.append("    SHIFT.START_TIME_SHIFT, ");
            strSql.append("    SHIFT.END_TIME_SHIFT, ");
            strSql.append("    SHIFT.BREAK_TIME_SHIFT, ");
            strSql.append("    (CASE WHEN SHIFT.KADOU_DAY IS NULL THEN TKJ.KADOU_DAY  ");
            strSql.append("    ELSE SHIFT.KADOU_DAY END) KADOU_DAY, ");
            strSql.append("    TKJ.START_TIME AS END_TIME, ");
            strSql.append("    TKJ.END_TIME   AS START_TIME, ");
            strSql.append("    TKJ.BREAK_TIME, ");
            strSql.append("    TKJ.JITSUDOU_TIME, ");
            strSql.append("    TKJ.JIKANGAI_TIME, ");
            strSql.append("    TKJ.KYUUJITSU_TIME, ");
            strSql.append("    TKJ.BIKOU ");
            strSql.append("FROM ");
            strSql.append("    (SELECT ");
            strSql.append("        SHAIN_ID, ");
            strSql.append("        KADOU_DAY ");
            strSql.append("    FROM ");
            strSql.append("        t_kinmu_jisseki ");
            strSql.append("    WHERE ");
            strSql.append("        SHAIN_ID = ? AND ");
            strSql.append("        kadou_day >= ? AND ");
            strSql.append("        kadou_day <= ? ");
            strSql.append("    UNION   ");
            strSql.append("    SELECT ");
            strSql.append("        SHAIN_ID, ");
            strSql.append("        YEAR_MONTH_DAY KADOU_DAY ");
            strSql.append("    FROM ");
            strSql.append("        t_shift ");
            strSql.append("    WHERE ");
            strSql.append("        SHAIN_ID = ? AND ");
            strSql.append("        YEAR_MONTH_DAY >= ? AND ");
            strSql.append("        YEAR_MONTH_DAY <= ? ");
            strSql.append("    ORDER BY ");
            strSql.append("        KADOU_DAY ");
            strSql.append("    ) BASE  ");
            strSql.append("LEFT OUTER JOIN T_KINMU_JISSEKI TKJ ON BASE.SHAIN_ID = TKJ.SHAIN_ID AND BASE.KADOU_DAY = TKJ.KADOU_DAY ");
            strSql.append("LEFT OUTER JOIN ( ");
            strSql.append("    SELECT ");
            strSql.append("        TS.SHAIN_ID, ");
            strSql.append("        TS.YEAR_MONTH_DAY AS KADOU_DAY, ");
            strSql.append("        MS.SHIFT_ID AS SHIFT_ID, ");
            strSql.append("        MS.SYMBOL, ");
            strSql.append("        MS.START_TIME AS START_TIME_SHIFT, ");
            strSql.append("        MS.END_TIME AS END_TIME_SHIFT, ");
            strSql.append("        MS.BREAK_TIME AS BREAK_TIME_SHIFT ");
            strSql.append("    FROM ");
            strSql.append("        T_SHIFT TS LEFT OUTER JOIN M_SHIFT MS ON TS.SHIFT_ID = MS.SHIFT_ID ");
            strSql.append(") SHIFT ON BASE.SHAIN_ID = SHIFT.SHAIN_ID AND BASE.KADOU_DAY = SHIFT.KADOU_DAY ");
            strSql.append("ORDER BY ");
            strSql.append("    TKJ.KADOU_DAY ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, shainId);
            ps.setString(2, startDay);
            ps.setString(3, endDay);
            ps.setString(4, shainId);
            ps.setString(5, startDay);
            ps.setString(6, endDay);

            // ログ出力
            log.info(ps);

            // 実行
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            while (rs.next()) {
                KinmuJissekiDto kinmuJissekiDto = new KinmuJissekiDto();

                String kadouDay = rs.getString(T_Kinmu_Jisseki.KADOU_DAY.getName());
                String startShift = rs.getString("START_TIME_SHIFT"); // 開始時間(シフト)
                String endShift = rs.getString("END_TIME_SHIFT"); // 終了時間(シフト)
                String breakShift = rs.getString("BREAK_TIME_SHIFT"); // 休憩時間(シフト)

                String startTime = rs.getString(T_Kinmu_Jisseki.START_TIME.getName()); // 開始時間
                String endTime = rs.getString(T_Kinmu_Jisseki.END_TIME.getName()); // 終了時間
                String breakTime = rs.getString(T_Kinmu_Jisseki.BREAK_TIME.getName()); // 休憩時間

                kinmuJissekiDto.setShainId(rs.getString(T_Kinmu_Jisseki.SHAIN_ID.getName())); // 社員ID
                kinmuJissekiDto.setKadouDay(kadouDay); // 稼働日
                kinmuJissekiDto.setShiftId(rs.getString(M_shift.SHIFT_ID.getName())); // シフトID
                kinmuJissekiDto.setSymbol(rs.getString(M_shift.SYMBOL.getName())); // シンボル
                kinmuJissekiDto.setStartTimeShift(startShift); // 開始時間(シフト)
                kinmuJissekiDto.setEndTimeShift(endShift); // 終了時間(シフト)
                kinmuJissekiDto.setBreakTimeShift(breakShift); // 休憩時間(シフト)
                kinmuJissekiDto.setStartTime(startTime); // 開始時間
                kinmuJissekiDto.setEndTime(endTime); // 終了時間
                kinmuJissekiDto.setBreakTime(breakTime); // 休憩時間
                kinmuJissekiDto.setJitsudouTime(rs.getString(T_Kinmu_Jisseki.JITSUDOU_TIME.getName())); // 実働時間
                kinmuJissekiDto.setJikangaiTime(rs.getString(T_Kinmu_Jisseki.JIKANGAI_TIME.getName())); // 時間外時間
                kinmuJissekiDto.setKyuujitsuTime(rs.getString(T_Kinmu_Jisseki.KYUUJITSU_TIME.getName())); // 休日時間
                kinmuJissekiDto.setBikou(rs.getString(T_Kinmu_Jisseki.BIKOU.getName())); // 備考

                kinmuJissekiMap.put(kadouDay, kinmuJissekiDto);
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
        return kinmuJissekiMap;
    }

    /**
     * 勤務実績テーブルに対象データが存在するか確認する
     *
     * @param shainId 社員ID
     * @param kadouDay 稼働日
     * @return true：あり,false：なし
     * @author Kazuya.Naraki
     */
    public boolean isData(String shainId, String kadouDay) throws SQLException {
        try {
            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT ");
            strSql.append("    * ");
            strSql.append("FROM ");
            strSql.append("    T_KINMU_JISSEKI ");
            strSql.append("WHERE ");
            strSql.append("    SHAIN_ID = ? AND ");
            strSql.append("    KADOU_DAY = ? ");


            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, shainId);
            ps.setString(2, kadouDay);

            // ログ出力
            log.info(ps);

            // 実行
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        }
    }

    /**
     * 勤務実績のデータを更新する。
     *
     * @param mshainDto 更新用社員マスタDto
     * @param loguinUserDto ログインユーザDto
     * @author Kazuya.Naraki
     */
    public void updateKinmuJisseki(KinmuJissekiDto kinmuJissekiDto, String shainId) throws SQLException{

        try {

            StringBuffer strSql = new StringBuffer();
            strSql.append("UPDATE T_KINMU_JISSEKI SET ");
            strSql.append("START_TIME = ?,");
            strSql.append("END_TIME = ?,");
            strSql.append("BREAK_TIME = ?,");
            strSql.append("JITSUDOU_TIME = ?,");
            strSql.append("JIKANGAI_TIME = ?,");
            strSql.append("KYUUJITSU_TIME = ?,");
            strSql.append("BIKOU = ?,");
            strSql.append("UPDATE_SHAIN_ID = ?,");
            strSql.append("UPDATE_DT = current_timestamp()");
            strSql.append("WHERE ");
            strSql.append("SHAIN_ID = ?");
            strSql.append("AND KADOU_DAY = ?");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, kinmuJissekiDto.getStartTime());
            ps.setString(2, kinmuJissekiDto.getEndTime());
            ps.setString(3, kinmuJissekiDto.getBreakTime());
            ps.setString(4, kinmuJissekiDto.getJitsudouTime());
            ps.setString(5, kinmuJissekiDto.getJikangaiTime());
            ps.setString(6, kinmuJissekiDto.getKyuujitsuTime());
            ps.setString(7, kinmuJissekiDto.getBikou());
            ps.setString(8, shainId);
            ps.setString(9, kinmuJissekiDto.getShainId());
            ps.setString(10, kinmuJissekiDto.getKadouDay());

            // ログ出力
            log.info(ps);

            // SQLを実行する
            ps.executeUpdate();

        } catch (SQLException e) {
            // 例外発生
            throw e;
        }
    }

    /**
     * 勤務実績のデータを登録する。
     *
     * @param mshainDto 社員マスタＤｔｏ
     * @author Kazuya.Naraki
     */
    public void insertKinmuJisseki(KinmuJissekiDto kinmuJissekiDto, String shainId) throws SQLException{

        try {

            StringBuffer strSql = new StringBuffer();
            strSql.append("INSERT INTO ");
            strSql.append("T_KINMU_JISSEKI ");
            strSql.append("( ");
            strSql.append("SHAIN_ID, ");
            strSql.append("KADOU_DAY, ");
            strSql.append("START_TIME, ");
            strSql.append("END_TIME, ");
            strSql.append("BREAK_TIME, ");
            strSql.append("JITSUDOU_TIME, ");
            strSql.append("JIKANGAI_TIME, ");
            strSql.append("KYUUJITSU_TIME, ");
            strSql.append("BIKOU, ");
            strSql.append("CREATE_SHAIN_ID, ");
            strSql.append("CREATE_DT, ");
            strSql.append("UPDATE_SHAIN_ID, ");
            strSql.append("UPDATE_DT ");
            strSql.append(") ");
            strSql.append("VALUES ");
            strSql.append("( ");
            strSql.append("? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(", current_timestamp() ");
            strSql.append(",? ");
            strSql.append(", current_timestamp() ");
            strSql.append(") ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, kinmuJissekiDto.getShainId());
            ps.setString(2, kinmuJissekiDto.getKadouDay());
            ps.setString(3, kinmuJissekiDto.getStartTime());
            ps.setString(4, kinmuJissekiDto.getEndTime());
            ps.setString(5, kinmuJissekiDto.getBreakTime());
            ps.setString(6, kinmuJissekiDto.getJitsudouTime());
            ps.setString(7, kinmuJissekiDto.getJikangaiTime());
            ps.setString(8, kinmuJissekiDto.getKyuujitsuTime());
            ps.setString(9, kinmuJissekiDto.getBikou());
            ps.setString(10, shainId);
            ps.setString(11, shainId);

            // ログ出力
            log.info(ps);

            // SQLを実行する
            ps.executeUpdate();

        } catch (SQLException e) {
            // 例外発生
            throw e;
        }
    }

}
