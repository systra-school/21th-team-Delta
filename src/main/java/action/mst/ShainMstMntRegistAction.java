/**
 * ファイル名：ShainMstMntRegistAction.java
 *
 * 変更履歴
 * 1.0  2010/09/04 Kazuya.Naraki
 */
package action.mst;


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
import business.dto.mst.ShainMstMntDto;
import business.logic.mst.ShainMstMntLogic;
import business.logic.utils.ComboListUtilLogic;
import constant.CommonConstant;
import constant.RequestSessionNameConstant;
import constant.CommonConstant.BunruiId;
import constant.DbConstant.Mbunrui;
import form.mst.ShainMstMntRegistForm;

/**
 * 説明：社員マスタメンテナンス登録アクションクラス
 * @author naraki
 *
 */
public class ShainMstMntRegistAction extends Action{

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 社員マスタメンテナンス登録アクションクラス
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

        // セッション
        HttpSession session = req.getSession();

        // ログインユーザ情報をセッションより取得
        LoginUserDto loginUserDto = (LoginUserDto) session.getAttribute(RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_INFO);

        // フォーム
        ShainMstMntRegistForm shainMstMntRegistForm = (ShainMstMntRegistForm) form;

        // リクエスト内容をDtoに変換する
        ShainMstMntDto mshainDto = this.formToDto(shainMstMntRegistForm);

        // ロジック生成
        ShainMstMntLogic shainMstMntLogic = new ShainMstMntLogic();

        // 権限セレクトボックスの取得
        ComboListUtilLogic comboListUtils = new ComboListUtilLogic();
        Map<String, String> comboMap = comboListUtils.getCombo(BunruiId.KENGEN.getBunruiId(), Mbunrui.HYOUJI1.getName(), false);

        // 取得したセレクトボックスのマップをフォームへセットする。
        shainMstMntRegistForm.setKengenCmbMap(comboMap);

        // 登録
        shainMstMntLogic.registMshain(mshainDto, loginUserDto);

        return mapping.findForward(forward);
    }

    /**
     * リクエスト情報をDtoのリストにセットする。
     * @param shainMstMntRegistForm 社員マスタ登録フォーム
     * @return 社員マスタDtoリスト
     * @author naraki
     */
    private ShainMstMntDto formToDto(ShainMstMntRegistForm shainMstMntRegistForm) {
        ShainMstMntDto shainMstMntDto = new ShainMstMntDto();

        String password = shainMstMntRegistForm.getPassword();
        String shainName = shainMstMntRegistForm.getShainName();
        String shainNameKana = shainMstMntRegistForm.getShainNameKana();
        String kengenId = shainMstMntRegistForm.getKengenId();

        // Dtoに値をセットする
        shainMstMntDto.setPassword(password);
        shainMstMntDto.setShainName(shainName);
        shainMstMntDto.setShainNameKana(shainNameKana);
        shainMstMntDto.setKengenId(kengenId);

        return shainMstMntDto;
    }

}
