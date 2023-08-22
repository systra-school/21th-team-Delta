/**
 * ファイル名：LoginDao.java
 *
 * 変更履歴
 * 1.0  2010/07/19 Kazuya.Naraki
 */
package business.db.dao.cmn;

import static constant.DbConstant.M_shain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import s2.dao.M_shainDao;

import business.db.dao.AbstractDao;
import business.dto.cmn.LoginDto;
import business.dto.mst.ShainMstMntDto;
import business.logic.utils.CheckUtils;



/**
 * 説明：ログイン処理のロジック
 *
 * @author naraki
 *
 */
public class LoginDao extends AbstractDao {

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * ユーザID、パスワードをキーにデータを取得する。(S2daoを使用しない場合)
     *
     * @param 検索用Dto
     * @return 社員マスタ検索Ｄｔｏ
     * @author Kazuya.Naraki
     */
    public LoginDto getShain(ShainMstMntDto mshainDtoSearch) throws SQLException {
        LoginDto loginDto = new LoginDto();
        try {
            // コネクション接続
            this.connect();

            StringBuffer strSql = new StringBuffer();
            strSql.append("SELECT * FROM M_SHAIN ");
            strSql.append("WHERE SHAIN_ID = ? ");
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
                loginDto.setShainId(rs.getString(M_shain.SHAIN_ID.getName())); // 社員ID
                loginDto.setPassword(rs.getString(M_shain.PASSWORD.getName())); // パスワード
                loginDto.setShainName(rs.getString(M_shain.SHAIN_NAME.getName())); // 社員名
                loginDto.setShainNameKana(rs.getString(M_shain.SHAIN_NAME_KANA.getName())); // 社員名カナ
                loginDto.setKengenId(rs.getString(M_shain.KENGEN_ID.getName())); // 権限ID
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
        return loginDto;
    }


    /**
     * ユーザID、パスワードをキーにデータを取得する。(S2daoを使用した場合)
     *
     * @param 検索用Dto
     * @return 社員マスタ検索Ｄｔｏ
     * @author Kazuya.Naraki
     */
    public LoginDto getShainS2(ShainMstMntDto mshainDtoSearch) throws SQLException {
        LoginDto logicDto = new LoginDto();

        M_shainDao dao = (M_shainDao)container.getComponent(M_shainDao.class);

        s2.entity.M_shain mshain = dao.getData(mshainDtoSearch.getShainId(), mshainDtoSearch.getPassword());

        if (!CheckUtils.isEmpty(mshain)) {
            logicDto.setShainId(mshain.getShain_id()); // 社員ID
            logicDto.setPassword(mshain.getPassword()); // パスワード
            logicDto.setShainName(mshain.getShain_name()); // 社員名
            logicDto.setShainNameKana(mshain.getShain_name_kana()); // 社員名カナ
            logicDto.setKengenId(mshain.getKengen_id()); // 権限ID
        } else {
            return null;
        }

        return logicDto;
    }
}
