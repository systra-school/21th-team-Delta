/**
 * ファイル名：KinmuJissekiLogic.java
 *
 * 変更履歴
 * 1.0  2010/11/04 Kazuya.Naraki
 */
package business.logic.bse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import exception.CommonException;

import business.db.dao.bse.KihonShiftDao;
import business.dto.LoginUserDto;
import business.dto.bse.KihonShiftDto;

/**
 * 説明：ログイン処理のロジック
 * @author nishioka
 *
 */
public class KihonShiftLogic {

    /**
     * シフト、基本シフトのデータを取得する
     *
     * @param shainId 社員ID
     * @param yearMonth 対象年月
     * @return 勤務実績マップ
     * @author Kazuya.Naraki
     */
    public Map<String, KihonShiftDto> getKihonShiftData() throws SQLException, CommonException {

        KihonShiftDao kihonShiftDao = new KihonShiftDao();

        // 基本シフトデータを取得する
        Map<String, KihonShiftDto> kihonShiftMap = kihonShiftDao.getKihonShiftDataList();

        return kihonShiftMap;
    }

    /**
     * 勤務実績データの登録を行う
     *
     * @param shainId 社員ID
     * @param yearMonth 対象年月
     * @return 勤務実績マップ
     * @author Kazuya.Naraki
     * @throws Exception
     */
    public void registKihonShift(List<KihonShiftDto> kinmuJissekiDtoList, LoginUserDto loginUserDto) throws Exception {

        // 基本シフトDao
        KihonShiftDao kihonShiftDao = new KihonShiftDao();
        // コネクション
        Connection connection = kihonShiftDao.getConnection();

        // トランザクション処理
        connection.setAutoCommit(false);

        try {
            for (int i = 0; i < kinmuJissekiDtoList.size(); i++) {

            	KihonShiftDto kihonShiftDto = kinmuJissekiDtoList.get(i);
                String shainId = kihonShiftDto.getShainId();

                // データが存在するか確認
                boolean updateFlg = kihonShiftDao.isData(shainId);

                if (updateFlg) {
                    // 更新
                    kihonShiftDao.updateKinmuJisseki(kihonShiftDto, loginUserDto.getShainId());
                } else {
                    // 登録
                    kihonShiftDao.insertKihonShift(kihonShiftDto, loginUserDto.getShainId());
                }

            }
        } catch (Exception e) {
            // ロールバック処理
            connection.rollback();

            // 切断
            connection.close();

            throw e;
        }
        // コミット
        connection.commit();
        // 切断
        connection.close();

    }

}
