/**
 * ファイル名：ShukkinKibouLogic.java
 *
 * 変更履歴
 * 1.0  2010/10/06 Kazuya.Naraki
 */
package business.logic.shk;

import java.sql.SQLException;
import java.util.List;

import business.db.dao.shk.ShukkinKibouDao;
import business.dto.shk.ShukkinKibouKakuninDto;

/**
 * 説明：希望出勤日入力処理のロジック
 * @author naraki
 *
 */
public class ShukkinKibouLogic {


    /**
     * 出勤希望確認画面に表示するリストを取得する。
     * 戻り値・・・社員分の希望シフトリストのリスト
     * @param yearMonth 年月
     * @return 出勤希望Dtoリストのリスト
     * @author naraki
     */
    public List<List<ShukkinKibouKakuninDto>> getShukkinKibouKakuninDtoList(String yearMonth) throws SQLException{

        // Dao
        ShukkinKibouDao dao = new ShukkinKibouDao();

        // シフト情報を取得する。
        List<List<ShukkinKibouKakuninDto>> kakuninDtoListList = dao.getShiftTblListList(yearMonth);

        return kakuninDtoListList;
    }
}
