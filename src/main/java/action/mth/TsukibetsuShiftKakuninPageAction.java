/**
 * ファイル名：TsukibetsuShiftKakuninPageAction.java
 *
 * 変更履歴
 * 1.0  2010/09/04 Kazuya.Naraki
 */
package action.mth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import constant.CommonConstant;
import form.mth.TsukibetsuShiftKakuninForm;

/**
 * 説明：月別シフト確認画面ページ変更アクションクラス
 * @author naraki
 *
 */
public class TsukibetsuShiftKakuninPageAction extends TsukibetsuShiftNyuuryokuAbstractAction{

    /**
     * 説明：月別シフト確認画面ページ変更アクションクラス
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
        TsukibetsuShiftKakuninForm TsukibetsuShiftKakuninForm = (TsukibetsuShiftKakuninForm) form;

        // 一覧のサイズ
        int listSize = TsukibetsuShiftKakuninForm.getTsukibetsuShiftKakuninBeanList().size();

        // ページング
        String paging = TsukibetsuShiftKakuninForm.getPaging();
        int offset = TsukibetsuShiftKakuninForm.getOffset();
        int cntPage = TsukibetsuShiftKakuninForm.getCntPage();

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
        TsukibetsuShiftKakuninForm.setOffset(offset);
        TsukibetsuShiftKakuninForm.setCntPage(cntPage);

        return mapping.findForward(forward);
    }

}
