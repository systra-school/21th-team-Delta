/**
 * ファイル名：HibetsuShiftInitAction.java
 *
 * 変更履歴
 * 1.0  2010/10/25 Kazuya.Naraki
 */
package action.day;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import business.dto.day.HibetsuShiftDto;
import business.logic.day.HibetsuShiftLogic;
import business.logic.utils.CheckUtils;
import business.logic.utils.CommonUtils;
import constant.CommonConstant;
import constant.RequestSessionNameConstant;
import form.day.HibetsuShiftForm;

/**
 * 説明：日別シフト初期処理のアクション
 * @author naraki
 *
 */
public class HibetsuShiftInitAction extends HibetsuShiftAbstractAction {

    /**
     * 日別シフト初期処理のアクション
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

        // フォーム
        HibetsuShiftForm hibetsuShiftForm = (HibetsuShiftForm) form;

        // フォワードキー
        String forward = CommonConstant.SUCCESS;

        // ロジック生成
        HibetsuShiftLogic hibetsuShiftLogic = new HibetsuShiftLogic();

        // 現在日付
        String yearMonthDay = CommonUtils.getFisicalDay();
        // 現在日付（画面表示用）
        String yearMonthDayDisp = CommonUtils.changeFormat(yearMonthDay, CommonConstant.yearMonthDayNoSl, CommonConstant.yearMonthDay);

        // システム日付のシフトデータを取得する
        List<HibetsuShiftDto> hibetsuShiftDtoList = hibetsuShiftLogic.getHibetsuShiftDtoList(yearMonthDay);

        if (CheckUtils.isEmpty(hibetsuShiftDtoList)) {
            forward = CommonConstant.NODATA;
        }

        // フォームへ一覧をセットする
        hibetsuShiftForm.setHibetsuShiftBeanList(dtoToForm(hibetsuShiftDtoList));
        hibetsuShiftForm.setYearMonthDay(yearMonthDay);
        hibetsuShiftForm.setYearMonthDayDisp(yearMonthDayDisp);

        yearMonthDay = CommonUtils.add(yearMonthDay, 0, 0, -1);


        // 戻り先を保存
        req.setAttribute(RequestSessionNameConstant.REQUEST_BACK_PAGE, "");

        return mapping.findForward(forward);
    }
}
