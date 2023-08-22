/**
 * ファイル名：LoginLogicImpl.java
 *
 * 変更履歴
 * 1.0  2010/07/19 Kazuya.Naraki
 */
package business.logic.cmn;

import java.sql.SQLException;

import form.cmn.LoginForm;
import business.db.dao.cmn.LoginDao;
import business.dto.cmn.LoginDto;
import business.dto.mst.ShainMstMntDto;

/**
 * 説明：ログイン処理のロジック実装クラス
 *
 * @author naraki
 *
 */
public class LoginLogic {
    public LoginDto getShainData(LoginForm loginForm) throws SQLException{

        // 社員マスタ検索用エンティティ
        ShainMstMntDto mshainDtoSearch = new ShainMstMntDto();

        // 検索条件セット
        mshainDtoSearch.setShainId(loginForm.getShainId()); // 社員ID
        mshainDtoSearch.setPassword(loginForm.getPassword()); // パスワード

        // 社員マスタDao
        LoginDao mShainDao = new LoginDao();
        // 社員情報を取得する。

        // Seasar2 を使用しない場合
        LoginDto loginDto = mShainDao.getShain(mshainDtoSearch);
        // Seasar2 を使用する場合はコメントアウトを除去してください
        // LoginDto loginDto = mShainDao.getShainS2(mshainDtoSearch);

        return loginDto;
    }
}
