/**
 * ファイル名：KinmuJissekiNyuryokuKakuninRegistAction.java
 *
 * 変更履歴
 * 1.0  2010/11/02 Kazuya.Naraki
 */
package action.act;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import business.dto.LoginUserDto;
import business.dto.act.KinmuJissekiDto;
import business.logic.act.KinmuJissekiLogic;
import business.logic.utils.CheckUtils;
import business.logic.utils.ComboListUtilLogic;
import business.logic.utils.CommonUtils;
import constant.CommonConstant;
import constant.RequestSessionNameConstant;
import form.act.KinmuJissekiNyuryokuKakuninBean;
import form.act.KinmuJissekiNyuryokuKakuninForm;
import form.common.DateBean;

/**
 * 説明：勤務実績入力確認登録処理
 * @author naraki
 *
 */
public class KinmuJissekiNyuryokuKakuninRegistAction extends Action {

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 勤務実績入力確認登録処理のアクション
     *
     * @param mapping アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @return アクションフォワード
     * @author naraki
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest req, HttpServletResponse res) throws Exception {
        log.info(new Throwable().getStackTrace()[0].getMethodName());

        // セッション
        HttpSession session = req.getSession();

        // フォワードキー
        String forward = "";

        // ログインユーザ情報をセッションより取得
        LoginUserDto loginUserDto = (LoginUserDto) session.getAttribute(RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_INFO);

        // 社員ID
        String shainId = loginUserDto.getShainId();

        // フォーム
        KinmuJissekiNyuryokuKakuninForm kinmuJissekiForm = (KinmuJissekiNyuryokuKakuninForm) form;

        // 対象年月
        String yearMonth = kinmuJissekiForm.getYearMonth();

        // 対象年月の月情報を取得する。
        List<DateBean> dateBeanList = CommonUtils.getDateBeanList(yearMonth);

        // 勤務実績ロジック
        KinmuJissekiLogic kinmuJissekiLogic = new KinmuJissekiLogic();

        // フォームデータをDtoに変換する
        List<KinmuJissekiDto> kinmuJissekiDtoList = this.formToDto(kinmuJissekiForm);

        // 時間計算を行う
        kinmuJissekiLogic.calculation(kinmuJissekiDtoList);

        // 勤務実績データの更新・登録を行う
        kinmuJissekiLogic.registKinmuJisseki(kinmuJissekiDtoList, loginUserDto);

        // 勤務実績データの取得
        Map<String, KinmuJissekiDto> kinmuJissekiMap = kinmuJissekiLogic.getKinmuJissekiShiftData(shainId, yearMonth);

        // セレクトボックスの取得
        ComboListUtilLogic comboListUtils = new ComboListUtilLogic();
        Map<String, String> yearMonthCmbMap = comboListUtils.getComboYearMonth(CommonUtils.getFisicalDay(CommonConstant.yearMonthNoSl), 24, ComboListUtilLogic.KBN_YEARMONTH_PRE, false);

        // データを変換する
        List<KinmuJissekiNyuryokuKakuninBean> kinmuJissekiList = this.dtoToBean(dateBeanList, kinmuJissekiMap, loginUserDto);

        // フォームにデータをセットする
        kinmuJissekiForm.setDateBeanList(dateBeanList);
        kinmuJissekiForm.setKinmuJissekiNyuryokuKakuninList(kinmuJissekiList);
        kinmuJissekiForm.setYearMonthCmbMap(yearMonthCmbMap);

        forward = CommonConstant.SUCCESS;

        return mapping.findForward(forward);
    }


    /**
     * dtoデータをBeanのリストへ変換する
     * @param kinmuJissekiMap 勤務実績マップ key 稼働日, val 勤務実績Dto
     * @return
     * @author naraki
     * @throws ParseException
     */
    private List<KinmuJissekiNyuryokuKakuninBean> dtoToBean(
            List<DateBean> dateBeanList,
            Map<String, KinmuJissekiDto> kinmuJissekiMap,
            LoginUserDto loginUserDto) throws ParseException {

        // 戻り値
        List<KinmuJissekiNyuryokuKakuninBean> rtnList = new  ArrayList<KinmuJissekiNyuryokuKakuninBean>();

        for (DateBean dateBean : dateBeanList) {

            // 勤務実績Bean
            KinmuJissekiNyuryokuKakuninBean kinmuJissekiNyuryokuKakuninBean = new KinmuJissekiNyuryokuKakuninBean();

            // 年月日
            String yearMonthDay = dateBean.getYearMonthDay();

            // 表示用の月日
            String monthDay = CommonUtils.changeFormat(
                    yearMonthDay,
                    CommonConstant.yearMonthDayNoSl,
                    CommonConstant.yearMonthDay).substring(5, 10);

            // 月日をセットする
            kinmuJissekiNyuryokuKakuninBean.setKadouDay(yearMonthDay);
            kinmuJissekiNyuryokuKakuninBean.setKadouDayDisp(monthDay);
            // 曜日をセットする
            kinmuJissekiNyuryokuKakuninBean.setYoubi(dateBean.getYoubi());
            // 祝日フラグをセットする
            kinmuJissekiNyuryokuKakuninBean.setShukujitsuFlg(dateBean.getShukujitsuFlg());
            // 社員IDをセットする
            kinmuJissekiNyuryokuKakuninBean.setShainId(loginUserDto.getShainId());

            // Dtoを取得する
            KinmuJissekiDto kinmuJissekiDto = kinmuJissekiMap.get(yearMonthDay);

            if (CheckUtils.isEmpty(kinmuJissekiDto)) {

                // データが存在しなかった場合
                rtnList.add(kinmuJissekiNyuryokuKakuninBean);
                // 次へ
                continue;
            }

            kinmuJissekiNyuryokuKakuninBean.setShiftId(kinmuJissekiDto.getShiftId());
            kinmuJissekiNyuryokuKakuninBean.setSymbol(kinmuJissekiDto.getSymbol());
            kinmuJissekiNyuryokuKakuninBean.setStartTimeShift(kinmuJissekiDto.getStartTimeShift());
            kinmuJissekiNyuryokuKakuninBean.setEndTimeShift(kinmuJissekiDto.getEndTimeShift());
            kinmuJissekiNyuryokuKakuninBean.setBreakTimeShift(kinmuJissekiDto.getBreakTimeShift());
            kinmuJissekiNyuryokuKakuninBean.setStartTime(kinmuJissekiDto.getStartTime());
            kinmuJissekiNyuryokuKakuninBean.setEndTime(kinmuJissekiDto.getEndTime());
            kinmuJissekiNyuryokuKakuninBean.setBreakTime(kinmuJissekiDto.getBreakTime());
            kinmuJissekiNyuryokuKakuninBean.setJitsudouTime(kinmuJissekiDto.getJitsudouTime());
            kinmuJissekiNyuryokuKakuninBean.setJikangaiTime(kinmuJissekiDto.getJikangaiTime());
            kinmuJissekiNyuryokuKakuninBean.setKyuujitsuTime(kinmuJissekiDto.getKyuujitsuTime());
            kinmuJissekiNyuryokuKakuninBean.setBikou(kinmuJissekiDto.getBikou());

            rtnList.add(kinmuJissekiNyuryokuKakuninBean);
        }

        return rtnList;
    }
    /**
     * formデータをDtoに変化する
     * @param
     * @return
     * @author naraki
     */
    private List<KinmuJissekiDto> formToDto(KinmuJissekiNyuryokuKakuninForm kinmuJissekiForm) {

        // 戻り値のリスト
        List<KinmuJissekiDto> dtoList = new ArrayList<KinmuJissekiDto>();
        // 画面の一覧
        List<KinmuJissekiNyuryokuKakuninBean> kinmuJissekiList = kinmuJissekiForm.getKinmuJissekiNyuryokuKakuninList();

        for (KinmuJissekiNyuryokuKakuninBean bean : kinmuJissekiList) {
            KinmuJissekiDto kinmuJissekiDto = new KinmuJissekiDto();

            // 計算以外の部分をセットする
            kinmuJissekiDto.setShainId(bean.getShainId()); // 社員ID
            kinmuJissekiDto.setKadouDay(bean.getKadouDay()); // 稼働日
            kinmuJissekiDto.setShiftId(bean.getShiftId()); // シフトID
            kinmuJissekiDto.setSymbol(bean.getSymbol()); // シンボル
            kinmuJissekiDto.setStartTimeShift(bean.getStartTimeShift()); // 開始時間(シフト)
            kinmuJissekiDto.setEndTimeShift(bean.getEndTimeShift()); // 終了時間(シフト)
            kinmuJissekiDto.setBreakTimeShift(bean.getBreakTimeShift()); // 休憩時間(シフト)
            kinmuJissekiDto.setStartTime(bean.getStartTime()); // 開始時間
            kinmuJissekiDto.setEndTime(bean.getEndTime()); // 終了時間
            kinmuJissekiDto.setBreakTime(bean.getBreakTime()); // 休憩時間
            kinmuJissekiDto.setBikou(bean.getBikou()); // 備考

            dtoList.add(kinmuJissekiDto);

        }

        return dtoList;

    }
}
