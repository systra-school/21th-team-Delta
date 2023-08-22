/**
 * ファイル名：ShainMstMntUpdateAction.java
 *
 * 変更履歴
 * 1.0  2010/09/04 Kazuya.Naraki
 */
package action.mst;

import java.util.ArrayList;
import java.util.Collection;
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
import business.dto.mst.ShainMstMntDto;
import business.logic.mst.ShainMstMntLogic;
import business.logic.utils.CheckUtils;
import business.logic.utils.ComboListUtilLogic;
import constant.CommonConstant;
import constant.RequestSessionNameConstant;
import constant.CommonConstant.BunruiId;
import constant.DbConstant.Mbunrui;
import form.mst.ShainMstMntBean;
import form.mst.ShainMstMntForm;

/**
 * 説明：社員マスタメンテナンス更新系アクションクラス
 * @author naraki
 *
 */
public class ShainMstMntUpdateAction extends Action{

    // ログ出力クラス
    private Log log = LogFactory.getLog(this.getClass());

    /**
     * 社員マスタメンテナンス更新系アクションクラス
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
        ShainMstMntForm shainMstMntForm = (ShainMstMntForm) form;

        // リクエスト内容をDtoに変換する
        List<ShainMstMntDto> mshainDtoList = this.formToDto(shainMstMntForm);

        // ロジック生成
        ShainMstMntLogic shainMstMntLogic = new ShainMstMntLogic();

        // 権限セレクトボックスの取得
        ComboListUtilLogic comboListUtils = new ComboListUtilLogic();
        Map<String, String> comboMap = comboListUtils.getCombo(BunruiId.KENGEN.getBunruiId(), Mbunrui.HYOUJI1.getName(), false);

        // 取得したセレクトボックスのマップをフォームへセットする。
        shainMstMntForm.setKengenCmbMap(comboMap);

        // 更新・削除処理
        shainMstMntLogic.updateMshain(mshainDtoList, loginUserDto);

        // 社員情報を再検索する
        mshainDtoList = shainMstMntLogic.getShainData(loginUserDto);

        if (CheckUtils.isEmpty(mshainDtoList)) {
            // データなし
            forward = CommonConstant.NODATA;
        } else {
            // フォームへ一覧をセットする
            shainMstMntForm.setShainMstMntBeanList(dtoToForm(mshainDtoList));
        }

        return mapping.findForward(forward);
    }

    /**
     * リクエスト情報をDtoのリストにセットする。
     * @param shainMstMntForm 社員マスタフォーム
     * @return 社員マスタDtoリスト
     * @author naraki
     */
    private List<ShainMstMntDto> formToDto(ShainMstMntForm shainMstMntForm) {
        List<ShainMstMntDto> msinDtoList = new ArrayList<ShainMstMntDto>();
        List<ShainMstMntBean> shainMstMntBeanList = shainMstMntForm.getShainMstMntBeanList();

        // 画面一覧のサイズ分処理を繰り返す
        for (ShainMstMntBean shainMstMntBean : shainMstMntBeanList) {
            ShainMstMntDto shainMstMntDto = new ShainMstMntDto();

            // Dtoに値をセットする
            shainMstMntDto.setShainId(shainMstMntBean.getShainId());
            shainMstMntDto.setPassword(shainMstMntBean.getPassword());
            shainMstMntDto.setShainName(shainMstMntBean.getShainName());
            shainMstMntDto.setShainNameKana(shainMstMntBean.getShainNameKana());
            shainMstMntDto.setKengenId(shainMstMntBean.getKengenId());
            shainMstMntDto.setDeleteFlg(shainMstMntBean.getDeleteFlg());

            msinDtoList.add(shainMstMntDto);
        }

        return msinDtoList;
    }

    /**
     * DtoからFormへ変換する
     * @param
     * @return
     * @author naraki
     */
    private List<ShainMstMntBean> dtoToForm(Collection<ShainMstMntDto> colection) {
        List<ShainMstMntBean> shainMstMntBeanList = new ArrayList<ShainMstMntBean>();

        for (ShainMstMntDto dto : colection) {
            ShainMstMntBean shain = new ShainMstMntBean();
            shain.setShainId(dto.getShainId());
            shain.setShainName(dto.getShainName());
            shain.setShainNameKana(dto.getShainNameKana());
            shain.setPassword(dto.getPassword());
            shain.setKengenId(dto.getKengenId());
            shainMstMntBeanList.add(shain);

        }
        return shainMstMntBeanList;
    }
}
