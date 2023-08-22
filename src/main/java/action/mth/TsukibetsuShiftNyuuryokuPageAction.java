/**
 * ファイル名：TsukibetsuShiftNyuuryokuPageAction.java
 *
 * 変更履歴
 * 1.0  2010/09/04 Kazuya.Naraki
 */
package action.mth;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import constant.CommonConstant;
import form.mth.TsukibetsuShiftNyuuryokuBean;
import form.mth.TsukibetsuShiftNyuuryokuForm;

/**
 * 説明：月別シフト入力画面ページ変更アクションクラス
 * @author naraki
 *
 */
public class TsukibetsuShiftNyuuryokuPageAction extends TsukibetsuShiftNyuuryokuAbstractAction{

    /**
     * 説明：月別シフト入力画面ページ変更アクションクラス
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

        // フォワードキー
        String forward = CommonConstant.SUCCESS;

        // フォーム
        TsukibetsuShiftNyuuryokuForm tsukibetsuShiftForm = (TsukibetsuShiftNyuuryokuForm) form;

        // 一覧のサイズ
        int listSize = tsukibetsuShiftForm.getTsukibetsuShiftNyuuryokuBeanList().size();

        // ページング
        String paging = tsukibetsuShiftForm.getPaging();
        int offset = tsukibetsuShiftForm.getOffset();
        int cntPage = tsukibetsuShiftForm.getCntPage();

        int nextOffset = 0;

        if (CommonConstant.NEXT.equals(paging)) {
            // 次ページ
            nextOffset = offset + SHOW_LENGTH;

            if (nextOffset < listSize) {
                // 一覧のサイズ未満の場合は
                offset = nextOffset;
                cntPage++;
            }
        } else {
            // 前ページ
            nextOffset = offset - SHOW_LENGTH;

            if (nextOffset < 0) {
                offset = 0;
            } else {
                offset = nextOffset;
                cntPage--;
            }
        }
        tsukibetsuShiftForm.setOffset(offset);
        tsukibetsuShiftForm.setCntPage(cntPage);

        // 登録フラグ初期化
        List<TsukibetsuShiftNyuuryokuBean> tukibetsuShiftBeanList = tsukibetsuShiftForm.getTsukibetsuShiftNyuuryokuBeanList();
        for (TsukibetsuShiftNyuuryokuBean tsukibetsuShiftBean : tukibetsuShiftBeanList) {
            tsukibetsuShiftBean.setRegistFlg(false);
        }

        return mapping.findForward(forward);
    }

}
