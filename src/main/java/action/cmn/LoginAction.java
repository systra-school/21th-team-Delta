/**
 * ファイル名：LoginAction.java
 *
 * 変更履歴
 * 1.0  2010/07/19 Kazuya.Naraki
 */
package action.cmn;

import java.util.Enumeration;

import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.*;

import constant.CommonConstant;
import constant.RequestSessionNameConstant;


import form.cmn.LoginForm;

import business.dto.LoginUserDto;
import business.dto.cmn.LoginDto;
import business.logic.cmn.LoginLogic;
import business.logic.utils.CheckUtils;

/**
 * 説明：ログイン処理のアクション
 *
 * @author naraki
 *
 */
public class LoginAction extends Action {

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * ログイン処理のアクション
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

        // 全てのセッションを削除する。
        @SuppressWarnings("unchecked")
        Enumeration<String> sessionEnum = session.getAttributeNames();

        while (sessionEnum.hasMoreElements()) {
            String sessionKey = sessionEnum.nextElement();
            session.removeAttribute(sessionKey);
        }

        // フォワードキー
        String forward = "";

        // フォーム
        LoginForm loginForm = (LoginForm) form;

        // ロジック生成
        LoginLogic loginLogic = new LoginLogic();

        // 社員情報を取得する
        LoginDto loginDto = loginLogic.getShainData(loginForm);

        if (CheckUtils.isEmpty(loginDto)) {
            forward = "error";
        } else {

            // ログインユーザ保持用Dtoを作成する
            this.createLoginUserData(session, loginDto);

            forward = CommonConstant.SUCCESS;
        }

        return mapping.findForward(forward);
    }

    /**
     * ログインユーザ情報をセッションに登録する。
     *
     * @param session セッション
     * @param loginDto 取得したログイン処理Dto
     * @author naraki
     */
    private void createLoginUserData(HttpSession session, LoginDto loginDto) {

        // ログインユーザの社員ID
        session.setAttribute(RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_ID, loginDto.getShainId());
        // ログインユーザの社員名
        session.setAttribute(RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_NAME, loginDto.getShainName());
        // ログインユーザの社員名カナ
        session.setAttribute(RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_NAME_KANA, loginDto.getShainNameKana());
        // ログインユーザの権限ID
        session.setAttribute(RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_KENGEN_ID, loginDto.getKengenId());

        // ログインユーザ情報の設定
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setShainId(loginDto.getShainId());
        loginUserDto.setShainName(loginDto.getShainName());
        loginUserDto.setShainNameKana(loginDto.getShainNameKana());
        loginUserDto.setKengenId(loginDto.getKengenId());

        session.setAttribute(RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_INFO, loginUserDto);


    }

}