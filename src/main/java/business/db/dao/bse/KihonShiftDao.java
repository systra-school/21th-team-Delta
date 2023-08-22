/**
 * ファイル名：KihonShiftDao.java
 *
 * 変更履歴
 * 1.0  2010/07/19 Kotaro.Nishioka
 */
package business.db.dao.bse;

import static constant.DbConstant.M_base_shift;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import constant.DbConstant.M_shain;


import business.db.dao.AbstractDao;
import business.dto.bse.KihonShiftDto;

/**
 * 説明：基本シフトDao
 *
 * @author nishioka
 *
 */
public class KihonShiftDao extends AbstractDao {

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * シフト、基本シフトのデータを取得する
     *
     * @return 勤務実績マップ
     * @author Kotaro.Nishioka
     */
    public Map<String, KihonShiftDto> getKihonShiftDataList() throws SQLException {
        // 戻り値 Key：稼動日, value：基本シフトDto
        Map<String, KihonShiftDto> kihonShiftList = new LinkedHashMap<String, KihonShiftDto>();

        try {
            // コネクション接続
            this.connect();

            /* 基本シフトを取得する */
            StringBuffer strSql = new StringBuffer();
            strSql.append(" SELECT             ");
            strSql.append("     MS.SHAIN_ID    ");
            strSql.append("   , MS.SHAIN_NAME  ");
            strSql.append("   , MBS.MONDAY     ");
            strSql.append("   , MBS.TUESDAY    ");
            strSql.append("   , MBS.WEDNESDAY  ");
            strSql.append("   , MBS.THURSDAY   ");
            strSql.append("   , MBS.FRIDAY     ");
            strSql.append("   , MBS.SATURDAY   ");
            strSql.append("   , MBS.SUNDAY     ");
            strSql.append("   , MBS.CREATE_DT  ");
            strSql.append("   , MBS.CREATE_SHAIN_ID  ");
            strSql.append("   , MBS.UPDATE_DT  ");
            strSql.append("   , MBS.UPDATE_SHAIN_ID  ");
            strSql.append(" FROM           ");
            strSql.append("           M_SHAIN      MS");
            strSql.append(" LEFT JOIN M_BASE_SHIFT MBS");
            strSql.append(" ON        MS.SHAIN_ID = MBS.SHAIN_ID ");
            strSql.append(" ORDER BY       ");
            strSql.append("   MBS.SHAIN_ID     ");
            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            // ログ出力
            log.info(ps);

            // 実行
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            while (rs.next()) {
                KihonShiftDto kihonShiftDto = new KihonShiftDto();

                kihonShiftDto.setShainId           (rs.getString(M_base_shift.SHAIN_ID.getName()));          // 社員ID
                kihonShiftDto.setShainName         (rs.getString(M_shain.SHAIN_NAME.getName()));             // 社員ID
                kihonShiftDto.setShiftIdOnSunday   (rs.getString(M_base_shift.SUNDAY.getName()));            // 日曜日シフトＩＤ
                kihonShiftDto.setShiftIdOnMonday   (rs.getString(M_base_shift.MONDAY.getName()));            // 月曜日シフトＩＤ
                kihonShiftDto.setShiftIdOnTuesday  (rs.getString(M_base_shift.TUESDAY.getName()));           // 火曜日シフトＩＤ
                kihonShiftDto.setShiftIdOnWednesday(rs.getString(M_base_shift.WEDNESDAY.getName()));         // 水曜日シフトＩＤ
                kihonShiftDto.setShiftIdOnThursday (rs.getString(M_base_shift.THURSDAY.getName()));          // 木曜日シフトＩＤ
                kihonShiftDto.setShiftIdOnFriday   (rs.getString(M_base_shift.FRIDAY.getName()));            // 金曜日シフトＩＤ
                kihonShiftDto.setShiftIdOnSaturday (rs.getString(M_base_shift.SATURDAY.getName()));          // 土曜日シフトＩＤ

                kihonShiftDto.setCreateDt(rs.getDate(M_base_shift.CREATE_DT.getName()));                     // 作成日
                kihonShiftDto.setCreateShainId(rs.getString(M_base_shift.CREATE_SHAIN_ID.getName()));        // 作成者
                kihonShiftDto.setUpdateDt(rs.getDate(M_base_shift.UPDATE_DT.getName()));                     // 更新日
                kihonShiftDto.setUpdateShainId(rs.getString(M_base_shift.UPDATE_SHAIN_ID.getName()));        // 更新者

                kihonShiftList.put(kihonShiftDto.getShainId(), kihonShiftDto);
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
        return kihonShiftList;
    }

    /**
     * 基本シフトマスタに対象データが存在するか確認する
     *
     * @param shainId 社員ID
     * @param kadouDay 稼働日
     * @return true：あり,false：なし
     * @author Kotaro.Nishioka
     */
    public boolean isData(String shainId) throws SQLException {
        try {
            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT           ");
            strSql.append("    1            ");
            strSql.append("FROM             ");
            strSql.append("    M_BASE_SHIFT ");
            strSql.append("WHERE            ");
            strSql.append("    SHAIN_ID = ? ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, shainId);

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
     * @author Kotaro.Nishioka
     */
    public void updateKinmuJisseki(KihonShiftDto kihonShiftDto, String loginShainId) throws SQLException{

        try {

            StringBuffer strSql = new StringBuffer();
            strSql.append(" UPDATE ");
            strSql.append("     M_BASE_SHIFT ");
            strSql.append(" SET ");
            strSql.append("      MONDAY          = ? ");
            strSql.append("     ,TUESDAY         = ? ");
            strSql.append("     ,WEDNESDAY       = ? ");
            strSql.append("     ,THURSDAY        = ? ");
            strSql.append("     ,FRIDAY          = ? ");
            strSql.append("     ,SATURDAY        = ? ");
            strSql.append("     ,SUNDAY          = ? ");
            strSql.append("     ,UPDATE_SHAIN_ID = ? ");
            strSql.append("     ,UPDATE_DT       = CURRENT_DATE ");
            strSql.append(" WHERE ");
            strSql.append("     SHAIN_ID   = ? ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, kihonShiftDto.getShiftIdOnMonday());
            ps.setString(2, kihonShiftDto.getShiftIdOnTuesday());
            ps.setString(3, kihonShiftDto.getShiftIdOnWednesday());
            ps.setString(4, kihonShiftDto.getShiftIdOnThursday());
            ps.setString(5, kihonShiftDto.getShiftIdOnFriday());
            ps.setString(6, kihonShiftDto.getShiftIdOnSaturday());
            ps.setString(7, kihonShiftDto.getShiftIdOnSunday());
            ps.setString(8, loginShainId);
            ps.setString(9, kihonShiftDto.getShainId());

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
     * 基本シフトのデータを登録する。
     *
     * @param kihonShiftDto 基本シフトＤｔｏ
     * @author Kotaro.Nishioka
     */
    public void insertKihonShift(KihonShiftDto kihonShiftDto, String loginShainId) throws SQLException{

        try {

            StringBuffer strSql = new StringBuffer();
            strSql.append(" INSERT INTO ");
            strSql.append("     M_BASE_SHIFT  ");
            strSql.append("     ( ");
            strSql.append("          SHAIN_ID        ");
            strSql.append("         ,MONDAY          ");
            strSql.append("         ,TUESDAY         ");
            strSql.append("         ,WEDNESDAY       ");
            strSql.append("         ,THURSDAY        ");
            strSql.append("         ,FRIDAY          ");
            strSql.append("         ,SATURDAY        ");
            strSql.append("         ,SUNDAY          ");
            strSql.append("         ,CREATE_SHAIN_ID ");
            strSql.append("         ,CREATE_DT       ");
            strSql.append("         ,UPDATE_SHAIN_ID ");
            strSql.append("         ,UPDATE_DT       ");
            strSql.append("     ) ");
            strSql.append("     VALUES ");
            strSql.append("     ( ");
            strSql.append("          ? ");
            strSql.append("         ,? ");
            strSql.append("         ,? ");
            strSql.append("         ,? ");
            strSql.append("         ,? ");
            strSql.append("         ,? ");
            strSql.append("         ,? ");
            strSql.append("         ,? ");
            strSql.append("         ,? ");
            strSql.append("         ,current_timestamp() ");
            strSql.append("         ,? ");
            strSql.append("         ,current_timestamp() ");
            strSql.append("     ) ");


            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, kihonShiftDto.getShainId());
            ps.setString(2, kihonShiftDto.getShiftIdOnMonday());
            ps.setString(3, kihonShiftDto.getShiftIdOnTuesday());
            ps.setString(4, kihonShiftDto.getShiftIdOnWednesday());
            ps.setString(5, kihonShiftDto.getShiftIdOnThursday());
            ps.setString(6, kihonShiftDto.getShiftIdOnFriday());
            ps.setString(7, kihonShiftDto.getShiftIdOnSaturday());
            ps.setString(8, kihonShiftDto.getShiftIdOnSunday());
            ps.setString(9, loginShainId);
            ps.setString(10,loginShainId);

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
