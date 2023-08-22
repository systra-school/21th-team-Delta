/**
 * ファイル名：ShukkinKibouKakuninInitAction.java
 *
 * 変更履歴
 * 1.0  2010/09/04 Kazuya.Naraki
 */
package action.shk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import business.dto.LoginUserDto;
import business.dto.shk.ShukkinKibouKakuninDto;
import business.logic.comparator.MethodComparator;
import business.logic.shk.ShukkinKibouLogic;
import business.logic.utils.CheckUtils;
import business.logic.utils.ComboListUtilLogic;
import business.logic.utils.CommonUtils;
import constant.CommonConstant;
import constant.RequestSessionNameConstant;
import form.common.DateBean;
import form.shk.ShukkinKibouKakuninBean;
import form.shk.ShukkinKibouKakuninForm;

/**
 * 説明：出勤希望日確認画面初期表示アクションクラス
 * @author naraki
 *
 */
public class ShukkinKibouKakuninInitAction extends ShukkinKibouAbstractAction{

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

        // セッション
        HttpSession session = req.getSession();

        // ログインユーザ情報をセッションより取得
        LoginUserDto loginUserDto = (LoginUserDto) session.getAttribute(RequestSessionNameConstant.SESSION_CMN_LOGIN_USER_INFO);

        // フォーム
        ShukkinKibouKakuninForm shukkinKibouKakuninForm = (ShukkinKibouKakuninForm) form;

        // 対象年月
        String yearMonth = CommonUtils.getFisicalDay(CommonConstant.yearMonthNoSl);

        // ロジック生成
        ShukkinKibouLogic shukkinKibouLogic = new ShukkinKibouLogic();

        // 対象年月の月情報を取得する。
        List<DateBean> dateBeanList = CommonUtils.getDateBeanList(yearMonth);

        // 出勤希望日を取得する
        List<List<ShukkinKibouKakuninDto>> kakuninDtoListList = shukkinKibouLogic.getShukkinKibouKakuninDtoList(yearMonth);

        List<ShukkinKibouKakuninBean> shukkinKibouNyuuryokuBeanList = new ArrayList<ShukkinKibouKakuninBean>();

        // セレクトボックスの取得
        ComboListUtilLogic comboListUtils = new ComboListUtilLogic();
        Map<String, String> shiftCmbMap = comboListUtils.getComboShift(false);
        Map<String, String> yearMonthCmbMap = comboListUtils.getComboYearMonth(CommonUtils.getFisicalDay(CommonConstant.yearMonthNoSl), 2, ComboListUtilLogic.KBN_YEARMONTH_NEXT, false);

        if (CheckUtils.isEmpty(kakuninDtoListList)) {
            // データなし
            forward = CommonConstant.NODATA;
        } else {
            // データあり
            shukkinKibouNyuuryokuBeanList = dtoToBean(kakuninDtoListList, loginUserDto);

            // フォームにデータをセットする
            shukkinKibouKakuninForm.setShiftCmbMap(shiftCmbMap);
            shukkinKibouKakuninForm.setYearMonthCmbMap(yearMonthCmbMap);
            shukkinKibouKakuninForm.setShukkinKibouKakuninBeanList(shukkinKibouNyuuryokuBeanList);
            shukkinKibouKakuninForm.setDateBeanList(dateBeanList);
            shukkinKibouKakuninForm.setYearMonth(yearMonth);
            shukkinKibouKakuninForm.setOffset(0);
            shukkinKibouKakuninForm.setCntPage(1);
            shukkinKibouKakuninForm.setMaxPage(CommonUtils.getMaxPage(kakuninDtoListList.size(), SHOW_LENGTH));
        }

        return mapping.findForward(forward);
    }

    /**
     * DtoからBeanへ変換する
     * @param shukkinKibouNyuuryokuDtoList
     * @param loginUserDto
     * @return 一覧に表示するリスト
     * @author naraki
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private List<ShukkinKibouKakuninBean> dtoToBean(List<List<ShukkinKibouKakuninDto>> kakuninDtoListList
                                                      , LoginUserDto loginUserDto)
                                                                        throws IllegalArgumentException,
                                                                        IllegalAccessException,
                                                                        InvocationTargetException {
        List<ShukkinKibouKakuninBean> shukkinKibouKakuninBeanList = new ArrayList<ShukkinKibouKakuninBean>();

        // 社員分のループ
        for (List<ShukkinKibouKakuninDto> kakuninDtoList :  kakuninDtoListList) {

            // 実行するオブジェクトの生成
            ShukkinKibouKakuninBean shukkinKibouKakuninBean = new ShukkinKibouKakuninBean();

            // メソッドの取得
            Method[] methods = shukkinKibouKakuninBean.getClass().getMethods();

            // ソートを行う
            Comparator<Method> asc = new MethodComparator();
            Arrays.sort(methods, asc); // 配列をソート

            // 社員名
            String shainId = "";
            String shainName = "";

            int index = 0;
            for (int i = 0; i < methods.length; i++) {
                // "setShiftIdXX" のメソッドを動的に実行する
                if (methods[i].getName().startsWith("setSymbol")) {
                    if (index < kakuninDtoList.size()) {
                        // Dtoのリストのサイズ以上のとき
                        ShukkinKibouKakuninDto kibouKakuninDto = kakuninDtoList.get(index);

                        shainId = kibouKakuninDto.getShainId();
                        shainName = kibouKakuninDto.getShainName();

                        methods[i].invoke(shukkinKibouKakuninBean, kibouKakuninDto.getKibouShiftSymbol());

                    } else {
                        // データなしの場合はハイフン
                        methods[i].invoke(shukkinKibouKakuninBean, "-");
                    }

                    index ++;
                }
            }

            // 社員ID、名前をセット
            shukkinKibouKakuninBean.setShainId(shainId);
            shukkinKibouKakuninBean.setShainName(shainName);

            shukkinKibouKakuninBeanList.add(shukkinKibouKakuninBean);
        }

        return shukkinKibouKakuninBeanList;
    }
}
