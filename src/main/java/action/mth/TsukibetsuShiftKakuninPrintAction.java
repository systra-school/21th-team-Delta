/**
 * ファイル名：TsukibetsuShiftKakuninPrintAction.java
 *
 * 変更履歴
 * 1.0  2010/10/30 Kazuya.Naraki
 */
package action.mth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.bbreak.excella.reports.exporter.ExcelOutputStreamExporter;

import business.logic.mth.TsukibetsuShiftLogic;
import constant.CommonConstant;
import form.mth.TsukibetsuShiftKakuninForm;

/**
 * 説明：出勤希望確認帳票出力
 * @author naraki
 *
 */
public class TsukibetsuShiftKakuninPrintAction extends Action {

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 説明：出勤希望日入力画面表示アクションクラス
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
        TsukibetsuShiftKakuninForm tsukibetsuShiftKakuninForm = (TsukibetsuShiftKakuninForm) form;


        // ロジック生成
        TsukibetsuShiftLogic tsukibetsuShiftLogic = new TsukibetsuShiftLogic();

        // レスポンス設定
        res.setContentType("application/vnd.ms-excel");
        // Excelファイルのダウンロード
        res.setHeader("Content-disposition","attachment; filename=TsukibetsuShift.xls");

        ExcelOutputStreamExporter excelOutePuteStreamExporter =new ExcelOutputStreamExporter(res.getOutputStream());

        // 印刷
        tsukibetsuShiftLogic.print(tsukibetsuShiftKakuninForm, excelOutePuteStreamExporter);

        return mapping.findForward(forward);
    }
}
