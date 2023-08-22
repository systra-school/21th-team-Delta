/**
 * ファイル名：ShukkinKibouDao.java
 *
 * 変更履歴
 * 1.0  2010/10/06 Kazuya.Naraki
 */
package business.db.dao.shk;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import constant.DbConstant.M_shain;
import constant.DbConstant.M_shift;
import constant.DbConstant.T_Shift;

import business.db.dao.AbstractDao;
import business.dto.LoginUserDto;
import business.dto.shk.ShukkinKibouKakuninDto;
import business.dto.shk.ShukkinKibouNyuuryokuDto;
import business.logic.utils.CommonUtils;

/**
 * 説明：出勤希望処理のDao
 * @author naraki
 *
 */
public class ShukkinKibouDao extends AbstractDao{

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * シフトテーブルのデータを指定した条件で検索する。
     * @param shainId 検索対象の社員ID
     * @param yearMonth 検索対象年月
     * @return 出勤希望Dtoリスト
     * @author Kazuya.Naraki
     */
    public List<ShukkinKibouNyuuryokuDto> getShiftTblData(String shainId, String yearMonth) throws SQLException {
        // 戻り値
        List<ShukkinKibouNyuuryokuDto> shukkinKibouNyuuryokuDtoList = new ArrayList<ShukkinKibouNyuuryokuDto>();

        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT ");
            strSql.append("SHAIN_ID, ");
            strSql.append("YEAR_MONTH_DAY, ");
            strSql.append("KIBOU_SHIFT_ID ");
            strSql.append("FROM ");
            strSql.append("T_SHIFT ");
            strSql.append("WHERE ");
            strSql.append("SHAIN_ID = ? ");
            strSql.append("AND ");
            strSql.append("SUBSTRING(YEAR_MONTH_DAY, 1, 6) = ? ");
            strSql.append("ORDER BY  ");
            strSql.append("YEAR_MONTH_DAY ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, shainId);
            ps.setString(2, yearMonth);

            // ログ出力
            log.info(ps);

            // SQLを実行する
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            while (rs.next()) {

                ShukkinKibouNyuuryokuDto dto = new ShukkinKibouNyuuryokuDto();
                dto.setShainId(rs.getString(T_Shift.SHAIN_ID.getName()));
                dto.setYearMonthDay(rs.getString(T_Shift.YEAR_MONTH_DAY.getName()));
                dto.setKibouShiftId(rs.getString(T_Shift.KIBOU_SHIFT_ID.getName()));

                // 取得した値を戻り値のリストにセットする。
                shukkinKibouNyuuryokuDtoList.add(dto);
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
        return shukkinKibouNyuuryokuDtoList;
    }

    /**
     * 社員分の希望シフトリストのリストを取得する。
     * @param yearMonth 検索対象年月
     * @return 出勤希望Dtoリスト
     * @author Kazuya.Naraki
     */
    public List<List<ShukkinKibouKakuninDto>> getShiftTblListList(String yearMonth) throws SQLException {
        // 戻り値
        List<List<ShukkinKibouKakuninDto>> shukkinKibouKakuninDtoListList = new ArrayList<List<ShukkinKibouKakuninDto>>();
        List<ShukkinKibouKakuninDto> shukkinKibouKakuninDtoList = new ArrayList<ShukkinKibouKakuninDto>();

        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT ");
            strSql.append("MSHAIN.SHAIN_ID, ");
            strSql.append("MSHAIN.SHAIN_NAME, ");
            strSql.append("TSHIFT.YEAR_MONTH_DAY, ");
            strSql.append("TSHIFT.KIBOU_SHIFT_ID, ");
            strSql.append("TSHIFT.SYMBOL ");
            strSql.append("FROM ");
            strSql.append("M_SHAIN MSHAIN ");
            strSql.append("LEFT OUTER JOIN ");
            strSql.append("(SELECT ");
            strSql.append("SHAIN_ID, ");
            strSql.append("KIBOU_SHIFT_ID, ");
            strSql.append("MSHIFT.SYMBOL, ");
            strSql.append("YEAR_MONTH_DAY ");
            strSql.append("FROM ");
            strSql.append("T_SHIFT TSHIFT RIGHT OUTER JOIN ");
            strSql.append("M_SHIFT MSHIFT ON ");
            strSql.append("TSHIFT.KIBOU_SHIFT_ID = ");
            strSql.append("MSHIFT.SHIFT_ID ");
            strSql.append("WHERE ");
            strSql.append("SUBSTRING(YEAR_MONTH_DAY, 1, 6) = ?) TSHIFT  ON ");
            strSql.append("MSHAIN.SHAIN_ID = TSHIFT.SHAIN_ID ");
            strSql.append("ORDER BY ");
            strSql.append("SHAIN_ID,");
            strSql.append("YEAR_MONTH_DAY");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, yearMonth);

            // ログ出力
            log.info(ps);

            // SQLを実行する
            ResultSet rs = ps.executeQuery();

            String shainId = "";

            // 取得結果セット
            while (rs.next()) {

                ShukkinKibouKakuninDto dto = new ShukkinKibouKakuninDto();
                String newShainId = rs.getString(M_shain.SHAIN_ID.getName());
                if ("".equals(shainId)) {
                    // 初回
                    shainId = newShainId;
                } else if (newShainId.equals(shainId)) {
                    // 同一社員のデータ
                    // 特になにもしない
                } else {
                    // 別の社員のデータに切り替わる場合

                    // 戻り値のリストに前の社員分のリストを追加する。
                    shukkinKibouKakuninDtoListList.add(shukkinKibouKakuninDtoList);

                    // 比較対象を入れ替える。
                    shainId = newShainId;

                    shukkinKibouKakuninDtoList = new ArrayList<ShukkinKibouKakuninDto>();

                }

                dto.setShainId(newShainId);
                dto.setShainName(rs.getString(M_shain.SHAIN_NAME.getName()));
                dto.setYearMonthDay(rs.getString(T_Shift.YEAR_MONTH_DAY.getName()));
                dto.setKibouShiftId(rs.getString(T_Shift.KIBOU_SHIFT_ID.getName()));
                dto.setKibouShiftSymbol(CommonUtils.changeNullToHyphen(rs.getString(M_shift.SYMBOL.getName())));
                // 取得した値を戻り値のリストにセットする。
                shukkinKibouKakuninDtoList.add(dto);
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }

        // 最後の社員分のリストを追加する
        shukkinKibouKakuninDtoListList.add(shukkinKibouKakuninDtoList);

        return shukkinKibouKakuninDtoListList;
    }

}
