/**
 * ファイル名：M_shainDao.java
 *
 * 変更履歴
 * 1.0  2010/12/25 Kazuya.Naraki
 */
package s2.dao;

import java.util.List;

import org.seasar.dao.annotation.tiger.Query;

import s2.entity.M_shain;

/**
 * 説明：ログイン処理のロジック
 * @author naraki
 *
 */
public interface M_shainDao {

    public Class<M_shain> BEAN = M_shain.class;

    /**
     * 更新
     * @param mShain エンティティ
     * @return
     * @author naraki
     */
    public int update(M_shain mShain);
    /**
     * 登録
     * @param mShain エンティティ
     * @return
     * @author naraki
     */
    public int insert(M_shain mShain);
    /**
     * 削除
     * @param mShain エンティティ
     * @return
     * @author naraki
     */
    public int delete(M_shain mShain);
    /**
     * 一括取得
     * @return 取得リスト
     * @author naraki
     */
    public List<M_shain> getAll();

    /**
     * プライマリキーを指定してデータを取得する。
     * @param shainId 社員ＩＤ
     * @param password パスワード
     * @return
     * @author naraki
     */
    public M_shain getData(String shainId, String password);
    public static final String getData_QUERY = "SHAIN_ID = ? AND PASSWORD = ?";

}
