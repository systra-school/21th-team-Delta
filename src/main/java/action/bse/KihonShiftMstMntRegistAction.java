/**
 * ファイル名：KinmuJissekiNyuryokuKakuninRegistAction.java
 *
 * 変更履歴
 * 1.0  2010/11/02 Kazuya.nishioka
 */
package action.bse;

import java.util.ArrayList;
import java.util.List;

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
import business.dto.bse.KihonShiftDto;
import business.logic.bse.KihonShiftLogic;
import constant.CommonConstant;
import constant.RequestSessionNameConstant;
import form.bse.KihonShiftMstMntBean;
import form.bse.KihonShiftMstMntForm;

/**
 * 説明：基本シフト入力確認登録処理
 * @author nishioka 西岡
 *
 */
public class KihonShiftMstMntRegistAction extends Action {

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
        log.info(kihonShiftMstMntForm);

        // 基本シフトロジック
        KihonShiftLogic kihonShiftLogic = new KihonShiftLogic();

        // フォームデータをDtoに変換する
        List<KihonShiftDto> kihonShiftDto = this.formToDto(kihonShiftMstMntForm);

        // 基本シフトデータの更新・登録を行う
        kihonShiftLogic.registKihonShift(kihonShiftDto, loginUserDto);

        forward = CommonConstant.SUCCESS;

        return mapping.findForward(forward);
    }

    /**
     * formデータをDtoに変化する
     * @param
     * @return
     * @author nishioka
     */
    private List<KihonShiftDto> formToDto(KihonShiftMstMntForm kihonShiftMstMntForm) {

        // 戻り値のリスト
        List<KihonShiftDto> dtoList = new ArrayList<KihonShiftDto>();
        // 画面の一覧
        List<KihonShiftMstMntBean> kihonShiftMstMntBean = kihonShiftMstMntForm.getKihonShiftMstMntBeanList();

        for (KihonShiftMstMntBean bean : kihonShiftMstMntBean) {
            KihonShiftDto kihonShiftDto = new KihonShiftDto();

            // 計算以外の部分をセットする
            kihonShiftDto.setShainId(bean.getShainId());                         // 社員ID
            kihonShiftDto.setShiftIdOnMonday(bean.getShiftIdOnMonday());         // 月曜シフト
            kihonShiftDto.setShiftIdOnTuesday(bean.getShiftIdOnTuesday());       // 火曜シフト
            kihonShiftDto.setShiftIdOnWednesday(bean.getShiftIdOnWednesday());   // 水曜シフト
            kihonShiftDto.setShiftIdOnThursday(bean.getShiftIdOnThursday());     // 木曜シフト
            kihonShiftDto.setShiftIdOnFriday(bean.getShiftIdOnFriday());         // 金曜シフト
            kihonShiftDto.setShiftIdOnSaturday(bean.getShiftIdOnSaturday());     // 土曜シフト
            kihonShiftDto.setShiftIdOnSunday(bean.getShiftIdOnSunday());         // 日曜シフト
            kihonShiftDto.setShiftIdOnSunday(bean.getShiftIdOnSunday());         // 日曜シフト
            dtoList.add(kihonShiftDto);
        }
        return dtoList;
    }
}
