package business.logic.bse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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

public class KihonShiftLogic{
	
	//基本シフトデータ取得メソッド
	public Map<String, KihonShiftDto> getKihonShiftData() throws SQLException, CommonException {
		
		KihonShiftDao kihonShiftDao = new KihonShiftDao();
		
		// 基本シフトデータを取得する
		Map<String, KihonShiftDto> kihonShiftMap = kihonShiftDao.getKihonShiftDataList();

		return kihonShiftMap;
	}
	
	// 基本shiftデータをもとに1か月分のシフトを作る
	public Map<String, List<TsukibetsuShiftDto>> getMonthlyData(List<DateBean> dateBeanF,Map<String, KihonShiftDto> kihonShiftDtoF) throws SQLException {
		// 戻り値
		Map<String, List<TsukibetsuShiftDto>> tsukibetsuShiftMap = new LinkedHashMap<String, List<TsukibetsuShiftDto>>();

		// 渡された1か月分の日付情報を格納したリストを代入
		List<DateBean> dateBeanList = dateBeanF;
		// 渡された基本shiftデータを入れておくMap
		Map<String, KihonShiftDto> kihonShiftDtoMap = kihonShiftDtoF;

		// 社員の人数を取得
		int shainCount = kihonShiftDtoMap.size();

		// 当月の情報 Stringとint
		String strYear = dateBeanList.get(0).getYearMonthDay().substring(0, 4);
		String strMonth = dateBeanList.get(0).getYearMonthDay().substring(4, 6);
		int intYear = Integer.parseInt(strYear);
		int intMonth = Integer.parseInt(strMonth) - 1;

		// 当月ついたちの曜日を取得
		// Calendarオブジェクト
		Calendar cal = Calendar.getInstance();
		// calに日時をセット ついたちの曜日を入れるため、必ずdayが１になる(○月1日の1)
		cal.set(intYear, intMonth, 1);
		// 曜日は1が日曜日～7が土曜日 戻り値はint型
		int startYoubiNum = cal.get(Calendar.DAY_OF_WEEK);

		// 基本shiftデータをもとに配列を作成する(keyは社員ID、valueは1週間分の基本shiftID)
		// これがのちに使われる、社員と当月一日の曜日順にはいったkihonShiftDto
		Map<String, String[]> kihonShift7 = new HashMap<>();
		// MapのKey用のString[]
		String[] shainId = new String[shainCount];
		// Mapのvalue用のList<String[]>
		List<String[]> kihonShiftDtoArray = new ArrayList<>();

		// 一時社員ID入れる用
		String tmpId = "";
		// Map回すときに使うCount
		int count = 0;
		// shainId[] に社員IDをいれる
		for (String key : kihonShiftDtoMap.keySet()) {
			shainId[count] = key;
			count++;
		}

		// 基本シフトをその月の曜日順に入れる！
		// 一人分の基本シフトIDをまずはString[]にいれる
		int yCount = startYoubiNum;
		String[] shiftId = new String[7];
		for (int i = 0; i < shainCount; i++) {
			for (int j = 0; j < 7; j++) {
				/*
				 * while(true) { yCount++; if(yCount > 7) { yCount = startYoubiNum; } if(yCount
				 * == startYoubiNum) { j = 0; break; } }
				 */
				switch (yCount) {
				case Calendar.SUNDAY:
					shiftId[yCount - 1] = kihonShiftDtoMap.get(shainId[i]).getShiftIdOnSunday();
					break;
				case Calendar.MONDAY:
					shiftId[yCount - 1] = kihonShiftDtoMap.get(shainId[i]).getShiftIdOnMonday();
					break;
				case Calendar.TUESDAY:
					shiftId[yCount - 1] = kihonShiftDtoMap.get(shainId[i]).getShiftIdOnTuesday();
					break;
				case Calendar.WEDNESDAY:
					shiftId[yCount - 1] = kihonShiftDtoMap.get(shainId[i]).getShiftIdOnWednesday();
					break;
				case Calendar.THURSDAY:
					shiftId[yCount - 1] = kihonShiftDtoMap.get(shainId[i]).getShiftIdOnThursday();
					break;
				case Calendar.FRIDAY:
					shiftId[yCount - 1] = kihonShiftDtoMap.get(shainId[i]).getShiftIdOnFriday();
					break;
				case Calendar.SATURDAY:
					shiftId[yCount - 1] = kihonShiftDtoMap.get(shainId[i]).getShiftIdOnSaturday();
					break;
				}
				// yCountを１日ずらす
				yCount++;
				// yCountは1～7まで。8以上になったら1に戻す。
				if (yCount > 7) {
					yCount = 1;
				}
				if (j == 6) {
					kihonShiftDtoArray.add(shiftId);
					shiftId = new String[7];
				}
			}
		}

		/*
		 * //7こつくった箱をListであるkihonShiftDtoArrayに人数分addしていく for(int i = 0; i <
		 * kihonShiftDtoArray.size(); i++) { //List<String[]>にいれる用のString[]
		 * kihonShiftIdArrayをつくる 数は１週間分のため7つ String[] kihonShiftIdArray = new String[7];
		 * kihonShiftDtoArray.add(kihonShiftIdArray); }
		 */
		// String tmp = kihonShiftDtoArray.get(i)[i]; の形で値を取得できる。

		// 上で作ったString社員IDとListをMap(kihonShift7)にputしていく（中身はまだない。箱作る。）
		// 人数分まわす（put)する必要がある。
		for (int i = 0; i < shainCount; i++) {
			kihonShift7.put(shainId[i], kihonShiftDtoArray.get(i));
		}
		// ↑これで箱が完成！ キーにはすでに社員IDがはいっているから、指定ができる。
		// 最終的に、tsukibetsuShiftMapにputしていかなければならない。
		// まずは、tsukibetsuShiftMapのvalueであるList<TsukibetsuShiftDto>を作る必要がある。
		// それをつくるために、TsukibetsuShiftDao（データベースからshift情報を取得するもの）が必要。
		// Dao作成
		TsukibetsuShiftDao tsukibetsuShiftDao = new TsukibetsuShiftDao();
		// List<TsukibetsuShiftDto>作成
		List<TsukibetsuShiftDto> tsukibetsuShiftList = tsukibetsuShiftDao.getShiftTblData(strYear + strMonth, true);

		// 以降で各社員の基本shiftデータをもとに一か月分のシフトをputしていく
		String oldShainId = "";
		// 一時領域
		List<TsukibetsuShiftDto> tmpList = new ArrayList<>();

		count = startYoubiNum - 1;
		boolean flg = false;
		// tsukibetsuShiftDtoのデータベース情報を、社員ごとに区切る
		for (TsukibetsuShiftDto dto : tsukibetsuShiftList) {
			if (CheckUtils.isEmpty(oldShainId)) {
				// 社員IDが初回で何も入っていないとき ↓
				oldShainId = dto.getShainId();

				// dtoのシフトIDを順番にいれる。
				dto.setShainId(kihonShiftDtoMap.get(oldShainId).getShainId());
				dto.setShainName(kihonShiftDtoMap.get(oldShainId).getShainName());
				dto.setShiftId(kihonShift7.get(oldShainId)[count]);
				count++;
				if (count > 6) {
					count = 0;
				}
				// 取得した値をListにセットする
				tmpList.add(dto);
			} else {
				if (oldShainId.equals(dto.getShainId())) {
					// oldShainIdが空文字じゃない かつ oldShainidと今回ってるdtoの社員IDが一致しているなら

					/*
					 * // countをstartYoubiCount(1～7はいっている)をリセット if (flg) { count = startYoubiNum -
					 * 1; flg = false; }
					 */

					// dtoのシフトIDを順番に入れる
					dto.setShainId(kihonShiftDtoMap.get(oldShainId).getShainId());
					dto.setShainName(kihonShiftDtoMap.get(oldShainId).getShainName());
					dto.setShiftId(kihonShift7.get(oldShainId)[count]);
					count++;
					if (count > 6) {
						count = 0;
					}
					// 取得した値を一時領域のリストにセットする
					tmpList.add(dto);

				} else {
					// oldShainIdが空文字ではない かつ 今回ってるdtoの社員IDが異なる（つまり、別人）
					// 一時領域のtmpListをこのメソッドの戻り値であるMapにputする
					tsukibetsuShiftMap.put(oldShainId, tmpList);

					// 別の社員のため、oldShinIdをいれかえる
					oldShainId = dto.getShainId();

					// ↑でいれた社員の分のshiftデータをいれる箱を再度作成 上書きではダメ
					tmpList = new ArrayList<>();
					count = startYoubiNum;
					tmpList.add(dto);
				}
			}
		} // dto領域ここまで 最後の人の丸々一か月はいらなくない？

		if (!CheckUtils.isEmpty(oldShainId)) {
			// 最後分を追加（最後の人の分は↑だけではputされないで押し出されちゃっている。）

			// ということで、最後の一人の一か月分を作ってListにくっつける。
			// 最後の一か月が何日かを振り分けるすいっち
			int endDay = 0;
			switch (intMonth) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				endDay = 31;
				break;
			default:
				endDay = 30;
			}

			// 基本シフトの二次元配列のインデックスであるcountを設定しなおす
			count = startYoubiNum - 1;

			int index = tsukibetsuShiftList.size() - endDay;
			for (int i = 0; i < endDay; i++) {
				TsukibetsuShiftDto dto = new TsukibetsuShiftDto();
				// dtoに1日分のdtoであるtsukibetsuShiftListの一つを入れ込む。これはシフトID（とシンボルもだけど…）だけが入っていない状態。
				dto = tsukibetsuShiftList.get(index);
				// たぶんIdとNameはとれてるはずだけど一応setしておく。
				dto.setShainId(oldShainId);
				dto.setShainName(kihonShiftDtoMap.get(oldShainId).getShainName());
				// ここで基本シフトIDをいれた配列がkihonShift7の中にあるため、社員IDでgetする。countは曜日に連動している。
				dto.setShiftId(kihonShift7.get(oldShainId)[count]);
				index++;
				count++;
				if (count > 6) {
					count = 0;
				}
				// 一日分をtmpListに追加していく。tmpListは↑のループでnewされている状態のため、ここではaddするだけで済んでいる。
				tmpList.add(dto);
			}
			// 最後の一人の一か月分のtsukibetsuShiftListであるtmpListを戻り値のtsukibetsuShiftMapにputする！
			tsukibetsuShiftMap.put(oldShainId, tmpList);
		}

		return tsukibetsuShiftMap;
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