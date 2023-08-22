/**
 * ファイル名：KinmuJissekiKakuninInitAction.java
 *
 * 変更履歴
 * 1.0  2010/11/02 Kazuya.nishioka
 */
package action.bse;

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

import java.util.Collection;

import business.dto.LoginUserDto;
import business.dto.bse.KihonShiftDto;
import business.dto.mst.ShiftMstMntDto;
import business.logic.bse.KihonShiftLogic;
import business.logic.mst.ShiftMstMntLogic;
import business.logic.utils.ComboListUtilLogic;
import constant.CommonConstant;
import constant.RequestSessionNameConstant;
import form.bse.KihonShiftHanreiBean;
import form.bse.KihonShiftMstMntBean;
import form.bse.KihonShiftMstMntForm;

/**
 * 説明：勤務実績確認初期処理
 * @author nishioka
 *
 */
public class KihonShiftMstMntInitAction extends Action {

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 勤務実績確認初期処理のアクション
     *
     * @param mapping アクションマッピング
     * @param form アクションフォーム
     * @param req リクエスト
     * @param res レスポンス
     * @return アクションフォワード
     * @author nishioka
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

        // フォーム
        KihonShiftMstMntForm kihonShiftMstMntForm = (KihonShiftMstMntForm) form;

        // セレクトボックス（シフトマスタ）の取得
        ComboListUtilLogic comboListUtils = new ComboListUtilLogic();
        Map<String, String> shiftCmbMap = comboListUtils.getComboShift(true);

        // シフトマスタロジック
        ShiftMstMntLogic shiftMstMntLogic = new ShiftMstMntLogic();
        // シフトマスタの取得
        List<ShiftMstMntDto> shiftMstMntDto = shiftMstMntLogic.getShiftData(loginUserDto);

        // 基本シフトマスタロジック
        KihonShiftLogic kihonShiftLogic = new KihonShiftLogic();
        // 設定済み基本シフトデータの取得
        Map<String, KihonShiftDto> kihonShiftDataMap = kihonShiftLogic.getKihonShiftData();

        // データを変換する（基本シフト凡例）
        List<KihonShiftHanreiBean> hanreiBeanList = this.hanreiDataToBean(shiftMstMntDto);
        // データを変換する（設定済み基本シフト）
        List<KihonShiftMstMntBean> dateBeanList = this.listDataDtoToBean(kihonShiftDataMap, loginUserDto);

        // フォームにデータをセットする
        kihonShiftMstMntForm.setKihonShiftMstMntBeanList(dateBeanList);
        kihonShiftMstMntForm.setShiftCmbMap(shiftCmbMap);
        kihonShiftMstMntForm.setKihonShiftHanreiBeanList(hanreiBeanList);

        forward = CommonConstant.SUCCESS;

        return mapping.findForward(forward);
    }


    /**
     * dtoデータをBeanのリストへ変換する
     * @param shiftMstMntDtoList 勤務実績マップ key 稼働日, val 勤務実績Dto
     * @return
     * @author nishioka
     * @throws ParseException
     */
    private List<KihonShiftHanreiBean> hanreiDataToBean(
            List<ShiftMstMntDto> shiftMstMntDtoList
    ) throws ParseException {

        // 戻り値
        List<KihonShiftHanreiBean> rtnList = new  ArrayList<KihonShiftHanreiBean>(shiftMstMntDtoList.size());

        for (ShiftMstMntDto shiftMstMntDto: shiftMstMntDtoList) {

            // 勤務実績Bean
            KihonShiftHanreiBean kihonShiftHanreiBean = new KihonShiftHanreiBean();
            kihonShiftHanreiBean.setShiftName(shiftMstMntDto.getShiftName());
            kihonShiftHanreiBean.setSymbol(shiftMstMntDto.getSymbol());
            kihonShiftHanreiBean.setTimeZone(shiftMstMntDto.getStartTime() + "&nbsp;&#xFF5E;&nbsp;" + shiftMstMntDto.getEndTime());
            kihonShiftHanreiBean.setKyukei(shiftMstMntDto.getBreakTime());

            rtnList.add(kihonShiftHanreiBean);
        }

        return rtnList;
    }
    /**
     * dtoデータをBeanのリストへ変換する
     * @param kihonShiftDtoMap 基本シフトマップ key 社員ID, val 基本シフトDto
     * @return
     * @author nishioka
     * @throws ParseException
     */
    private List<KihonShiftMstMntBean> listDataDtoToBean(
            Map<String, KihonShiftDto> kihonShiftDtoMap,
            LoginUserDto loginUserDto) throws ParseException {

        // 戻り値
        List<KihonShiftMstMntBean> rtnList = new  ArrayList<KihonShiftMstMntBean>(kihonShiftDtoMap.size());

        Collection <KihonShiftDto> values = kihonShiftDtoMap.values();
        for (KihonShiftDto kihonShiftDto: values) {

            // 基本シフトBean
            KihonShiftMstMntBean kihonShiftMstMntBean = new KihonShiftMstMntBean();
            kihonShiftMstMntBean.setShainId(kihonShiftDto.getShainId());
            kihonShiftMstMntBean.setShainName(kihonShiftDto.getShainName());

            kihonShiftMstMntBean.setShiftIdOnMonday(kihonShiftDto.getShiftIdOnMonday());
            kihonShiftMstMntBean.setShiftIdOnTuesday(kihonShiftDto.getShiftIdOnTuesday());
            kihonShiftMstMntBean.setShiftIdOnWednesday(kihonShiftDto.getShiftIdOnWednesday());
            kihonShiftMstMntBean.setShiftIdOnThursday(kihonShiftDto.getShiftIdOnThursday());
            kihonShiftMstMntBean.setShiftIdOnFriday(kihonShiftDto.getShiftIdOnFriday());
            kihonShiftMstMntBean.setShiftIdOnSaturday(kihonShiftDto.getShiftIdOnSaturday());
            kihonShiftMstMntBean.setShiftIdOnSunday(kihonShiftDto.getShiftIdOnSunday());

            rtnList.add(kihonShiftMstMntBean);
        }

        return rtnList;
    }
}
