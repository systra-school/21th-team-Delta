/**
 * ファイル名：KinmuJissekiLogic.java
 *
 * 変更履歴
 * 1.0  2010/11/04 Kazuya.Naraki
 */
package business.logic.bse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import business.db.dao.bse.KihonShiftDao;
import business.db.dao.mth.TsukibetsuShiftDao;
import business.dto.LoginUserDto;
import business.dto.bse.KihonShiftDto;
import business.dto.mth.TsukibetsuShiftDto;
import business.logic.utils.CheckUtils;
import exception.CommonException;
import form.common.DateBean;

/**
 * 説明：ログイン処理のロジック
 * 
 * @author nishioka
 *
 */
public class KihonShiftLogic9 {

	/**
	 * シフト、基本シフトのデータを取得する
	 *
	 * @param shainId   社員ID
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

	/*
	 * RurikaMoriya 9/7～ 基本シフト登録をもとに１カ月分のシフトへ反映させる
	 * 
	 * @param 一か月分の日付情報格納クラスのリスト・各社員の基本シフトデータ
	 * 
	 * @return 月別シフト情報リスト
	 * 
	 * @
	 */
	public Map<String, List<TsukibetsuShiftDto>> getMonthlyData(List<DateBean> dataBeanList,
			Map<String, KihonShiftDto> kihonShiftMap) throws Exception, SQLException {

		// 戻り値 LinkedHashMapは、キー（社員ID）の重複を許可しないもの。
		Map<String, List<TsukibetsuShiftDto>> tsukibetsuShiftDtoMap = new LinkedHashMap<String, List<TsukibetsuShiftDto>>();

		// 日付情報リストを元に月の初日の曜日を取得する
		// 初日の曜日 = 1ヶ月分の日付情報格納クラスのリスト.0番目.曜日
		String youbi = dataBeanList.get(0).getYoubi();

		// kihonshiftMapのキーの数は社員数分
		int shainCount = kihonShiftMap.size();
		
		//DayOfWeekつかうための
		int intYear = Integer.parseInt(dataBeanList.get(0).getYearMonthDay().substring(0, 4));
		int intMonth = Integer.parseInt(dataBeanList.get(0).getYearMonthDay().substring(4, 6));
		int intDay = 1;
		Calendar cal = Calendar.getInstance();
		
		//社員IDと0～6のシフトID
		String[][] hako = new String[shainCount][7];
		int youbiNum = cal.get(Calendar.DAY_OF_WEEK);
		
		for(String[] tm : hako) {
			
			
			switch(youbiNum) {
			case Calendar.SUNDAY:
				
				break;
			}
			
		}
		
		
		/*
		 * //基本シフト曜日（人数分の基本シフトIDいれちゃう） String[][] kihonShiftId = new
		 * String[shainCount][6]; shainCount = 0; for(String key :
		 * kihonShiftMap.keySet()) { int youbiCnt = 0; switch() { case }
		 * kihonShiftId[shainCount][youbiCnt] =
		 * kihonShiftMap.get(key).getShiftIdOnFriday(); }
		 */
		
		// Dao
		TsukibetsuShiftDao dao = new TsukibetsuShiftDao();
		
		// シフト情報を取得する
		List<TsukibetsuShiftDto> tsukibetsuShiftDtoList = dao.getShiftTblData(dataBeanList.get(0).getYearMonthDay().substring(0, 6), true);
		int startDay = 1;
		
		
		// 基本シフトデータを元に配列を作成する 月～日曜の７日分(Listで管理　キーが社員名・valueが月～日のシフト)
		List<KihonShiftDto> kihonShiftList = new ArrayList<KihonShiftDto>();
		
		//キーとなる社員ID　でまずListにadd
		shainCount = 0;
		for(String key : kihonShiftMap.keySet()){
			KihonShiftDto kihonTmp = new KihonShiftDto();
			// 社員IDをいれる
			kihonTmp.setShainId(kihonShiftMap.get(key).getShainId());
			kihonShiftList.add(kihonTmp);
			shainCount++;
		}
		
		List<TsukibetsuShiftDto> tsukibetsuShiftList = new ArrayList<TsukibetsuShiftDto>();
		
		String oldShainId = "";
		
		//tsukibetsuShiftDtoに月曜日ならこの基本シフトIDをいれる！
		for (int i = 0; i < kihonShiftList.size(); i++) {
			// ↓で使うため、一時領域(社員一人分のシフト情報を入れる箱をaddするためのList)を作成する。
			TsukibetsuShiftDto tsukibetsuShift = new TsukibetsuShiftDto();
			
			//shainIDいれる
			tsukibetsuShift.setShainId(kihonShiftList.get(i).getShainId());
			
			// 基本シフトを0～6にそれぞれ入れる。
			for (int j = 0; j < dataBeanList.size(); j++) {
				//cal再セット用
				cal.set(intYear, intMonth, intDay);
				tsukibetsuShift.setYearMonthDay(cal.toString());
				
				//その日の曜日が○曜日だったら　○曜日に応じた基本シフトIDをいれる
				switch (cal.get(Calendar. DAY_OF_WEEK)) {
				case Calendar.SUNDAY:
					tsukibetsuShift.setShiftId(kihonShiftList.get(i).getShiftIdOnSunday());
					break;
				case Calendar.MONDAY:
					tsukibetsuShift.setShiftId(kihonShiftList.get(i).getShiftIdOnMonday());
					break;
				case Calendar.TUESDAY:
					tsukibetsuShift.setShiftId(kihonShiftList.get(i).getShiftIdOnTuesday());
					break;
				case Calendar.WEDNESDAY:
					tsukibetsuShift.setShiftId(kihonShiftList.get(i).getShiftIdOnWednesday());
					break;
				case Calendar.THURSDAY:
					tsukibetsuShift.setShiftId(kihonShiftList.get(i).getShiftIdOnThursday());
					break;
				case Calendar.FRIDAY:
					tsukibetsuShift.setShiftId(kihonShiftList.get(i).getShiftIdOnFriday());
					break;
				case Calendar.SATURDAY:
					tsukibetsuShift.setShiftId(kihonShiftList.get(i).getShiftIdOnSaturday());
					break;
				}
				tsukibetsuShiftList.add(tsukibetsuShift);
				intDay++; //1日経過分ずらす
			}
			if (CheckUtils.isEmpty(oldShainId)) {
				oldShainId = tsukibetsuShift.getShainId();
			} else {
				tsukibetsuShiftDtoMap.put(oldShainId ,tsukibetsuShiftList);
				oldShainId = tsukibetsuShift.getShainId();
			}
		}
		
		
		/*
		 * //↓で使うためにここで作成 社員IDを一時的にいれる。 
		 * String oldShainId = ""; 
		 * // DB取得より取得する値を各社員づつ区切る
		 * ここでは社員分の入れ物を作るような感じ 
		 * for (TsukibetsuShiftDto dto : tsukibetsuShiftDtoList) {
		 * if (CheckUtils.isEmpty(oldShainId)) { 
		 * // oldShainId（社員ID）が空のとき（初回） 
		 * oldShainId = dto.getShainId();
		 * 
		 * // 取得した値を戻り値のリストにセットする。 
		 * tmpList.add(dto);
		 * } else {
		 * if (oldShainId.equals(dto.getShainId())) { 
		 * // 同一社員のデータ 
		 * //取得した値を戻り値のリストにセットする。 
		 * tmpList.add(dto);
		 * } else { 
		 * // 別社員のデータのとき
		 * //(今回ってるforよりひとつ)前の社員分をマップに追加
		 * tsukibetsuShiftDtoMap.put(oldShainId, tmpList);
		 * 
		 * // マップに追加し終わったため、oldShainId を今forで回っているshainIdで代入しなおす
		 * oldShainId = dto.getShainId();
		 * 
		 * // 新しい社員のデータを追加していく
		 * tmpList = new ArrayList<TsukibetsuShiftDto>();
		 * tmpList.add(dto);
		 * }
		 * }
		 * }
		 */
		return tsukibetsuShiftDtoMap;
	}

	/**
	 * 勤務実績データの登録を行う
	 *
	 * @param shainId   社員ID
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
