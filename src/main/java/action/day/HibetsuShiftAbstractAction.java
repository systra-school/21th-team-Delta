/**
 * ファイル名：HibetsuShiftAbstractAction.java
 *
 * 変更履歴
 * 1.0  2010/10/26 Kazuya.Naraki
 */
package action.day;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;

import business.dto.day.HibetsuShiftDto;
import form.day.HibetsuShiftBean;

/**
 * 説明：日別シフト確認画面の抽象クラス
 * @author naraki
 *
 */
public abstract class HibetsuShiftAbstractAction extends Action {

    // ログ出力クラス
    protected Log log = LogFactory.getLog(this.getClass());

    /** 00:00 */
    private final String TIME00 = "00:00";
    /** 01:00 */
    private final String TIME01 = "01:00";
    /** 02:00 */
    private final String TIME02 = "02:00";
    /** 03:00 */
    private final String TIME03 = "03:00";
    /** 04:00 */
    private final String TIME04 = "04:00";
    /** 05:00 */
    private final String TIME05 = "05:00";
    /** 06:00 */
    private final String TIME06 = "06:00";
    /** 07:00 */
    private final String TIME07 = "07:00";
    /** 08:00 */
    private final String TIME08 = "08:00";
    /** 09:00 */
    private final String TIME09 = "09:00";
    /** 10:00 */
    private final String TIME10 = "10:00";
    /** 11:00 */
    private final String TIME11 = "11:00";
    /** 12:00 */
    private final String TIME12 = "12:00";
    /** 13:00 */
    private final String TIME13 = "13:00";
    /** 14:00 */
    private final String TIME14 = "14:00";
    /** 15:00 */
    private final String TIME15 = "15:00";
    /** 16:00 */
    private final String TIME16 = "16:00";
    /** 17:00 */
    private final String TIME17 = "17:00";
    /** 18:00 */
    private final String TIME18 = "18:00";
    /** 19:00 */
    private final String TIME19 = "19:00";
    /** 2:00 */
    private final String TIME20 = "20:00";
    /** 21:00 */
    private final String TIME21 = "21:00";
    /** 22:00 */
    private final String TIME22 = "22:00";
    /** 23:00 */
    private final String TIME23 = "23:00";
    /** 24:00 */
    private final String TIME24 = "24:00";
    /** 出勤 */
    private final String SHUKKIN = "出勤";
    /** 休憩 */
    private final String BREAKTIME = "休憩";

    /**
     * DtoからFormへ変換する
     * @param
     * @return
     * @author naraki
     */
    protected List<HibetsuShiftBean> dtoToForm(List<HibetsuShiftDto> list) {
        List<HibetsuShiftBean> shainMstMntBeanList = new ArrayList<HibetsuShiftBean>();

        for (HibetsuShiftDto dto : list) {
            HibetsuShiftBean hibetsuShiftBean = new HibetsuShiftBean();

            String startTime = dto.getStartTime();
            String endTime = dto.getEndTime();
            String breakTime = dto.getBreakTime();

            if (TIME00.compareTo(endTime) < 0 && TIME01.compareTo(startTime) > 0) {
                // 00:00～01:00
                hibetsuShiftBean.setBoolTime00(true);

                if (TIME00.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime00(SHUKKIN);
                }
            }
            if (TIME01.compareTo(endTime) < 0 && TIME02.compareTo(startTime) > 0) {
                // 01:00～02:00
                hibetsuShiftBean.setBoolTime01(true);

                if (TIME01.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime01(SHUKKIN);
                }
            }
            if (TIME02.compareTo(endTime) < 0 && TIME03.compareTo(startTime) > 0) {
                // 02:00～03:00
                hibetsuShiftBean.setBoolTime02(true);

                if (TIME02.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime02(SHUKKIN);
                }
            }
            if (TIME03.compareTo(endTime) < 0 && TIME04.compareTo(startTime) > 0) {
                // 03:00～04:00
                hibetsuShiftBean.setBoolTime03(true);

                if (TIME03.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime03(SHUKKIN);
                }
            }
            if (TIME04.compareTo(endTime) < 0 && TIME05.compareTo(startTime) > 0) {
                // 05:00～05:00
                hibetsuShiftBean.setBoolTime04(true);

                if (TIME04.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime04(SHUKKIN);
                }
            }
            if (TIME05.compareTo(endTime) < 0 && TIME06.compareTo(startTime) > 0) {
                // 05:00～06:00
                hibetsuShiftBean.setBoolTime05(true);

                if (TIME05.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime05(SHUKKIN);
                }
            }
            if (TIME06.compareTo(endTime) < 0 && TIME07.compareTo(startTime) > 0) {
                // 06:00～07:00
                hibetsuShiftBean.setBoolTime06(true);

                if (TIME06.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime06(SHUKKIN);
                }
            }
            if (TIME07.compareTo(endTime) < 0 && TIME08.compareTo(startTime) > 0) {
                // 07:00～08:00
                hibetsuShiftBean.setBoolTime07(true);

                if (TIME07.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime07(SHUKKIN);
                }
            }
            if (TIME08.compareTo(endTime) < 0 && TIME09.compareTo(startTime) > 0) {
                // 08:00～09:00
                hibetsuShiftBean.setBoolTime08(true);

                if (TIME08.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime08(SHUKKIN);
                }
            }
            if (TIME09.compareTo(endTime) < 0 && TIME10.compareTo(startTime) > 0) {
                // 09:00～10:00
                hibetsuShiftBean.setBoolTime09(true);

                if (TIME09.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime09(SHUKKIN);
                }
            }
            if (TIME10.compareTo(endTime) < 0 && TIME11.compareTo(startTime) > 0) {
                // 10:00～11:00
                hibetsuShiftBean.setBoolTime10(true);

                if (TIME10.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime10(SHUKKIN);
                }
            }
            if (TIME11.compareTo(endTime) < 0 && TIME12.compareTo(startTime) > 0) {
                // 11:00～12:00
                hibetsuShiftBean.setBoolTime11(true);

                if (TIME11.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime11(SHUKKIN);
                }
            }
            if (TIME12.compareTo(endTime) < 0 && TIME13.compareTo(startTime) > 0) {
                // 12:00～13:00
                hibetsuShiftBean.setBoolTime12(true);

                if (TIME12.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime12(SHUKKIN);
                }
            }
            if (TIME13.compareTo(endTime) < 0 && TIME14.compareTo(startTime) > 0) {
                // 13:00～14:00
                hibetsuShiftBean.setBoolTime13(true);

                if (TIME13.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime13(SHUKKIN);
                }
            }
            if (TIME14.compareTo(endTime) < 0 && TIME15.compareTo(startTime) > 0) {
                // 14:00～15:00
                hibetsuShiftBean.setBoolTime14(true);

                if (TIME14.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime14(SHUKKIN);
                }
            }
            if (TIME15.compareTo(endTime) < 0 && TIME16.compareTo(startTime) > 0) {
                // 15:00～16:00
                hibetsuShiftBean.setBoolTime15(true);

                if (TIME15.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime15(SHUKKIN);
                }
            }
            if (TIME16.compareTo(endTime) < 0 && TIME17.compareTo(startTime) > 0) {
                // 16:00～17:00
                hibetsuShiftBean.setBoolTime16(true);

                if (TIME16.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime16(SHUKKIN);
                }
            }
            if (TIME17.compareTo(endTime) < 0 && TIME18.compareTo(startTime) > 0) {
                // 17:00～18:00
                hibetsuShiftBean.setBoolTime17(true);

                if (TIME17.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime17(SHUKKIN);
                }
            }
            if (TIME18.compareTo(endTime) < 0 && TIME19.compareTo(startTime) > 0) {
                // 18:00～19:00
                hibetsuShiftBean.setBoolTime18(true);

                if (TIME18.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime18(SHUKKIN);
                }
            }
            if (TIME19.compareTo(endTime) < 0 && TIME20.compareTo(startTime) > 0) {
                // 19:00～20:00
                hibetsuShiftBean.setBoolTime19(true);

                if (TIME19.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime19(SHUKKIN);
                }
            }
            if (TIME20.compareTo(endTime) < 0 && TIME21.compareTo(startTime) > 0) {
                // 20:00～21:00
                hibetsuShiftBean.setBoolTime20(true);

                if (TIME20.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime20(SHUKKIN);
                }
            }
            if (TIME21.compareTo(endTime) < 0 && TIME22.compareTo(startTime) > 0) {
                // 21:00～22:00
                hibetsuShiftBean.setBoolTime21(true);

                if (TIME21.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime21(SHUKKIN);
                }
            }
            if (TIME22.compareTo(endTime) < 0 && TIME23.compareTo(startTime) > 0) {
                // 22:00～23:00
                hibetsuShiftBean.setBoolTime22(true);

                if (TIME22.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime22(SHUKKIN);
                }
            }
            if (TIME23.compareTo(endTime) < 0 && TIME24.compareTo(startTime) > 0) {
                // 23:00～24:00
                hibetsuShiftBean.setBoolTime23(true);

                if (TIME23.compareTo(startTime) <= 0) {
                    // 開始時刻の時間帯の場合
                    hibetsuShiftBean.setStrTime23(SHUKKIN);
                }
            }

            hibetsuShiftBean.setShainName(dto.getShainName());
            hibetsuShiftBean.setStartTime(startTime);
            hibetsuShiftBean.setEndTime(endTime);

            StringBuffer breakTimeBuf = new StringBuffer();
            breakTimeBuf.append(BREAKTIME).append("： ").append(breakTime);
            hibetsuShiftBean.setBreakTime(breakTimeBuf.toString());

            shainMstMntBeanList.add(hibetsuShiftBean);

        }
        return shainMstMntBeanList;
    }
}
