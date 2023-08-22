/**
 * ファイル名：ShainMstMntDao.java
 *
 * 変更履歴
 * 1.0  2010/07/19 Kazuya.Naraki
 */
package business.db.dao.mst;

import static constant.DbConstant.M_shain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import business.db.dao.AbstractDao;
import business.dto.LoginUserDto;
import business.dto.mst.ShainMstMntDto;
import business.logic.utils.CommonUtils;



/**
 * 説明：社員マスタメンテナンスDao
 *
 * @author naraki
 *
 */
public class ShainMstMntDao extends AbstractDao {

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * ユーザID、パスワードをキーにデータを取得する。
     *
     * @param 検索用Dto
     * @return 社員マスタ検索Ｄｔｏ
     * @author Kazuya.Naraki
     */
    public ShainMstMntDto getShain(ShainMstMntDto mshainDtoSearch) throws SQLException {
        ShainMstMntDto mShainDto = new ShainMstMntDto();

        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT * FROM ");
            strSql.append("M_SHAIN ");
            strSql.append("WHERE ");
            strSql.append("SHAIN_ID = ? ");
            strSql.append("AND PASSWORD = ?");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, mshainDtoSearch.getShainId());
            ps.setString(2, mshainDtoSearch.getPassword());

            // ログ出力
            log.info(ps);

            // 実行
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            if (rs.next()) {
                mShainDto.setShainId(rs.getString(M_shain.SHAIN_ID.getName())); // 社員ID
                mShainDto.setPassword(rs.getString(M_shain.PASSWORD.getName())); // パスワード
                mShainDto.setShainName(rs.getString(M_shain.SHAIN_NAME.getName())); // 社員名
                mShainDto.setShainNameKana(rs.getString(M_shain.SHAIN_NAME_KANA.getName())); // 社員名カナ
                mShainDto.setKengenId(rs.getString(M_shain.KENGEN_ID.getName())); // 権限ID
                mShainDto.setCreateShainId(rs.getString(M_shain.CREATE_SHAIN_ID.getName())); // 作成者ID
                mShainDto.setCreateDt(rs.getDate(M_shain.CREATE_DT.getName())); // 作成日時
                mShainDto.setUpdateShainId(rs.getString(M_shain.UPDATE_SHAIN_ID.getName())); // 更新者ID
                mShainDto.setUpdateDt(rs.getDate(M_shain.UPDATE_DT.getName())); // 更新日時
            } else {
                return null;
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
        return mShainDto;
    }

    /**
     * 社員マスタのデータを全件取得する。
     *
     * @return 社員マスタエンティティ
     * @author Kazuya.Naraki
     */
    public List<ShainMstMntDto> getShainAllList() throws SQLException{

        // 戻り値
        List<ShainMstMntDto> mShainList = new ArrayList<ShainMstMntDto>();

        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT * FROM M_SHAIN ");
            strSql.append("ORDER BY SHAIN_ID, PASSWORD ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            // ログ出力
            log.info(ps);

            // SQLを実行する
            ResultSet rs = ps.executeQuery();

            // 取得結果セット
            while (rs.next()) {
                // 社員情報
                ShainMstMntDto mShainDto = new ShainMstMntDto();

                mShainDto.setShainId(rs.getString(M_shain.SHAIN_ID.getName())); // 社員ID
                mShainDto.setPassword(rs.getString(M_shain.PASSWORD.getName())); // パスワード
                mShainDto.setShainName(CommonUtils.changeNullToBlank(rs.getString(M_shain.SHAIN_NAME.getName()))); // 社員名
                mShainDto.setShainNameKana(CommonUtils.changeNullToBlank(rs.getString(M_shain.SHAIN_NAME_KANA.getName()))); // 社員名カナ
                mShainDto.setKengenId(CommonUtils.changeNullToBlank(rs.getString(M_shain.KENGEN_ID.getName()))); // 権限ID
                mShainDto.setCreateShainId(CommonUtils.changeNullToBlank(rs.getString(M_shain.CREATE_SHAIN_ID.getName()))); // 作成者ID
                mShainDto.setCreateDt(rs.getDate(M_shain.CREATE_DT.getName())); // 作成日時
                mShainDto.setUpdateShainId(CommonUtils.changeNullToBlank(rs.getString(M_shain.UPDATE_SHAIN_ID.getName()))); // 更新者ID
                mShainDto.setUpdateDt(rs.getDate(M_shain.UPDATE_DT.getName())); // 更新日時

                // 取得した値を戻り値のリストにセットする。
                mShainList.add(mShainDto);
            }
        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
        return mShainList;
    }

    /**
     * 社員マスタのデータを更新する。
     *
     * @param mshainDto 更新用社員マスタDto
     * @param loguinUserDto ログインユーザDto
     * @author Kazuya.Naraki
     */
    public void updateShainMst(ShainMstMntDto mshainDto, LoginUserDto loginUserDto) throws SQLException{

        try {
            // コネクションの取得
            this.getConnection();

            StringBuffer strSql = new StringBuffer();
            strSql.append("UPDATE ");
            strSql.append("M_SHAIN ");
            strSql.append("SET ");
            strSql.append("PASSWORD = ?, ");
            strSql.append("SHAIN_NAME = ?, ");
            strSql.append("SHAIN_NAME_KANA = ?, ");
            strSql.append("KENGEN_ID = ?, ");
            strSql.append("UPDATE_SHAIN_ID = ?, ");
            strSql.append("UPDATE_DT = current_timestamp() ");
            strSql.append("WHERE ");
            strSql.append("SHAIN_ID = ? ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, mshainDto.getPassword());
            ps.setString(2, mshainDto.getShainName());
            ps.setString(3, mshainDto.getShainNameKana());
            ps.setString(4, mshainDto.getKengenId());
            ps.setString(5, loginUserDto.getShainId());
            ps.setString(6, mshainDto.getShainId());

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
     * 社員マスタのデータを削除する。
     *
     * @param shainId 社員ＩＤ
     * @return なし
     * @author Kazuya.Naraki
     */
    public void deleteShainMst(String shainId) throws SQLException{

        try {

            StringBuffer strSql = new StringBuffer();
            strSql.append("DELETE FROM ");
            strSql.append("M_SHAIN ");
            strSql.append("WHERE ");
            strSql.append("SHAIN_ID = ? ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, shainId);

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
     * 社員マスタのデータを登録する。
     *
     * @param mshainDto 社員マスタＤｔｏ
     * @author Kazuya.Naraki
     */
    public void registShainMst(ShainMstMntDto mshainDto, LoginUserDto loginUserDto) throws SQLException{

        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("INSERT INTO ");
            strSql.append("M_SHAIN ");
            strSql.append(" ( ");
            strSql.append("SHAIN_ID, ");
            strSql.append("PASSWORD, ");
            strSql.append("SHAIN_NAME, ");
            strSql.append("SHAIN_NAME_KANA, ");
            strSql.append("KENGEN_ID, ");
            strSql.append("CREATE_SHAIN_ID, ");
            strSql.append("CREATE_DT, ");
            strSql.append("UPDATE_SHAIN_ID, ");
            strSql.append("UPDATE_DT ");
            strSql.append(") ");
            strSql.append("VALUES ");
            strSql.append(" ( ");
            strSql.append("? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(",? ");
            strSql.append(", current_timestamp()");
            strSql.append(",? ");
            strSql.append(", current_timestamp()");
            strSql.append(") ");

            PreparedStatement ps = connection.prepareStatement(strSql.toString());

            ps.setString(1, mshainDto.getShainId());
            ps.setString(2, mshainDto.getPassword());
            ps.setString(3, mshainDto.getShainName());
            ps.setString(4, mshainDto.getShainNameKana());
            ps.setString(5, mshainDto.getKengenId());
            ps.setString(6, loginUserDto.getShainId());
            ps.setString(7, loginUserDto.getShainId());

            // ログ出力
            log.info(ps);

            // SQLを実行する
            ps.executeUpdate();

        } catch (SQLException e) {
            // 例外発生
            throw e;
        } finally {
            // コネクション切断
            disConnect();
        }
    }

}
