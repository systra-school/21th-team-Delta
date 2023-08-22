/**
 * ファイル名：KinmuJissekiLogic.java
 *
 * 変更履歴
 * 1.0  2010/11/04 Kazuya.Naraki
 */
package business.logic.act;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import exception.CommonException;

import business.db.dao.act.KinmuJissekiDao;
import business.dto.LoginUserDto;
import business.dto.act.KinmuJissekiDto;
import business.logic.utils.CheckUtils;
import business.logic.utils.CommonUtils;

/**
 * 説明：ログイン処理のロジック
 * @author naraki
 *
 */
public class KinmuJissekiLogic {
    /**
     * シフト、勤務実績のデータを取得する
     *
     * @param shainId 社員ID
     * @param yearMonth 対象年月
     * @return 勤務実績マップ
     * @author Kazuya.Naraki
     */
    public Map<String, KinmuJissekiDto> getKinmuJissekiShiftData(String shainId, String yearMonth) throws SQLException, CommonException {

        KinmuJissekiDao kinmuJissekiDao = new KinmuJissekiDao();

        // 開始日
        String startDay = yearMonth.concat("01");
        // 終了日
        String endDay = CommonUtils.getEndYearMonthDay(startDay);

        // 勤務実績データを取得する
        Map<String, KinmuJissekiDto> kinmuJissekiMap = kinmuJissekiDao.getKinmuJissekiShiftData(shainId, startDay, endDay);

        return kinmuJissekiMap;
    }

    /**
     * 勤務実績のデータを取得する
     *
     * @param shainId 社員ID
     * @param yearMonth 対象年月
     * @return 勤務実績マップ
     * @author Kazuya.Naraki
     */
    public Map<String, KinmuJissekiDto> getKinmuJissekiData(String shainId, String yearMonth) throws SQLException, CommonException {

        KinmuJissekiDao kinmuJissekiDao = new KinmuJissekiDao();

        // 開始日
        String startDay = yearMonth.concat("01");
        // 終了日
        String endDay = CommonUtils.getEndYearMonthDay(startDay);

        // 勤務実績データを取得する
        Map<String, KinmuJissekiDto> kinmuJissekiMap = kinmuJissekiDao.getKinmuJissekiData(shainId, startDay, endDay);

        return kinmuJissekiMap;
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
    public void registKinmuJisseki(List<KinmuJissekiDto> kinmuJissekiDtoList, LoginUserDto loginUserDto) throws Exception {

        // 勤務実績Dao
        KinmuJissekiDao kinmuJissekiDao = new KinmuJissekiDao();
        // コネクション
        Connection connection = kinmuJissekiDao.getConnection();

        // トランザクション処理
        connection.setAutoCommit(false);

        try {
            for (int i = 0; i < kinmuJissekiDtoList.size(); i++) {

                KinmuJissekiDto kinmuJissekiDto = kinmuJissekiDtoList.get(i);
                String shainId = kinmuJissekiDto.getShainId();
                String kadouDay = kinmuJissekiDto.getKadouDay();

                // データが存在するか確認
                boolean updateFlg = kinmuJissekiDao.isData(shainId, kadouDay);

                if (updateFlg) {
                    // 更新
                    kinmuJissekiDao.updateKinmuJisseki(kinmuJissekiDto, loginUserDto.getShainId());
                } else {
                    // 登録
                    kinmuJissekiDao.insertKinmuJisseki(kinmuJissekiDto, loginUserDto.getShainId());
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

    /**
     * 実労働時間、残業時間の計算を行う
     * @param kinmuJissekiDtoList 勤務実績Dtoリスト
     * @author Kazuya.Naraki
     * @throws ParseException
     */
    public void calculation(List<KinmuJissekiDto> kinmuJissekiDtoList) throws ParseException {

        final String colon = ":";

        for (KinmuJissekiDto kinmuJissekiDto : kinmuJissekiDtoList) {

            // 画面のリスト分処理を繰り返す

            // 開始、終了、休憩時間を取得する
            String startTime = kinmuJissekiDto.getStartTime();
            String endTime = kinmuJissekiDto.getEndTime();
            String breakTime = kinmuJissekiDto.getBreakTime();

            if (CheckUtils.isEmpty(startTime) || CheckUtils.isEmpty(endTime) || CheckUtils.isEmpty(breakTime)) {
                // 入力なしの場合次へ
                continue;
            }

//            // フォーマットを変換する
//            startTime = CommonUtils.changeFormat(startTime, "HH:mm", "yyyy/MM/dd HH:mm:ss");
//            endTime = CommonUtils.changeFormat(endTime, "HH:mm", "yyyy/MM/dd HH:mm:ss");
//            breakTime = CommonUtils.changeFormat(breakTime, "HH:mm", "yyyy/MM/dd HH:mm:ss");
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//
//            // ミリ秒に変換する
//            long startTimeLong = formatter.parse(startTime).getTime();
//            long endTimeLong = formatter.parse(endTime).getTime();
//            long breakTimeLong = formatter.parse(breakTime).getTime();
//
//            // 実労働時間(終了時間 - 開始時間)
//            long jitsudouTimeS = (endTimeLong - startTimeLong ) / 1000; // 秒
//            long jitsudouTimeM = jitsudouTimeS / 60; // 分
//            long jitsudouTimeH = jitsudouTimeM / 60; // 時
//            jitsudouTimeM = jitsudouTimeM % 60; // 余りが分になる

            /*
             * 計算処理を行うために各時間を
             * 秒に変換する。
             */
            long startTimeLong = CommonUtils.getSecond(startTime);
            long endTimeLong = CommonUtils.getSecond(endTime);
            long breakTimeLong = CommonUtils.getSecond(breakTime);

            // 実労働時間(終了時間 - 開始時間)
            long jitsudouTimeS = (endTimeLong - startTimeLong - breakTimeLong); // 秒

            if (jitsudouTimeS < 0) {
                // 休憩が多かったとき
                jitsudouTimeS = 0;
            }

            // 秒を60で除算する → 分に変換。
            long jitsudouTimeM = jitsudouTimeS / 60; // 分
            // 分を60で除算する → 時に変換。
            long jitsudouTimeH = jitsudouTimeM / 60; // 時
            // 分を60で除算したときの余り → 分を算出する。
            jitsudouTimeM = jitsudouTimeM % 60; // 余りが分になる

            // 算出した値を画面へ表示する形式にする hh:mm
            StringBuffer jitsudouTime = new StringBuffer();
            jitsudouTime.append(CommonUtils.padWithZero(String.valueOf(jitsudouTimeH), 2));
            jitsudouTime.append(colon);
            jitsudouTime.append(CommonUtils.padWithZero(String.valueOf(jitsudouTimeM), 2));

            /*
             * 時間外時間算出のために
             * シフトの時間を取得する。
             */
            String startTimeShift = kinmuJissekiDto.getStartTimeShift();
            String endTimeShift = kinmuJissekiDto.getEndTimeShift();
            String breakTimeShift = kinmuJissekiDto.getBreakTimeShift();

            if (CheckUtils.isEmpty(startTimeShift) || CheckUtils.isEmpty(endTimeShift) || CheckUtils.isEmpty(breakTimeShift)) {
                // シフトがない（休日の場合）
                // 実働時間を勤務実績Dtoの休日へセット
                kinmuJissekiDto.setKyuujitsuTime(jitsudouTime.toString());
            } else {
                // シフトがある場合

                // 実働時間を勤務実績Dtoの勤務実績へセット
                kinmuJissekiDto.setJitsudouTime(jitsudouTime.toString());
            }
        }
    }
}
