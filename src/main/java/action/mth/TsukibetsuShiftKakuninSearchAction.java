/**
 * ファイル名：TsukibetsuShiftKakuninSearchAction.java
 *
 * 変更履歴
 * 1.0  2010/09/04 Kazuya.Naraki
 */
package action.mth;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import business.dto.mth.TsukibetsuShiftDto;
import business.logic.comparator.MethodComparator;
import business.logic.mth.TsukibetsuShiftLogic;
import business.logic.utils.CheckUtils;
import business.logic.utils.ComboListUtilLogic;
import business.logic.utils.CommonUtils;
import constant.CommonConstant;
import constant.RequestSessionNameConstant;
import form.common.DateBean;
import form.mth.TsukibetsuShiftKakuninBean;
import form.mth.TsukibetsuShiftKakuninForm;

/**
 * 説明：月別シフト確認検索アクションクラス
 * @author naraki
 */
public class TsukibetsuShiftKakuninSearchAction extends TsukibetsuShiftNyuuryokuAbstractAction{

    /**
     * 説明：月別シフト入力検索アクションクラス
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
        TsukibetsuShiftKakuninForm tsukibetsuShiftForm = (TsukibetsuShiftKakuninForm) form;

        // 対象年月
        String yearMonth = tsukibetsuShiftForm.getYearMonth();

        // ロジック生成
        TsukibetsuShiftLogic tsukibetsuShiftLogic = new TsukibetsuShiftLogic();

        // 対象年月の月情報を取得する。
        List<DateBean> dateBeanList = CommonUtils.getDateBeanList(yearMonth);

        // シフトIDを取得する
        Map<String,List<TsukibetsuShiftDto>> tsukibetsuShiftDtoMap = tsukibetsuShiftLogic.getTsukibetsuShiftDtoMap(yearMonth, true);

        List<TsukibetsuShiftKakuninBean> tsukibetsuShiftKakuninBean = new ArrayList<TsukibetsuShiftKakuninBean>();

        // セレクトボックスの取得
        ComboListUtilLogic comboListUtils = new ComboListUtilLogic();
        Map<String, String> shiftCmbMap = comboListUtils.getComboShift(true);
        Map<String, String> yearMonthCmbMap = comboListUtils.getComboYearMonth(CommonUtils.getFisicalDay(CommonConstant.yearMonthNoSl), 2, ComboListUtilLogic.KBN_YEARMONTH_NEXT, false);
        if (CheckUtils.isEmpty(tsukibetsuShiftDtoMap)) {
            // データなし
            TsukibetsuShiftKakuninBean tsukibetsuShiftBean = new TsukibetsuShiftKakuninBean();
            tsukibetsuShiftBean.setShainId(loginUserDto.getShainId());
            tsukibetsuShiftBean.setShainName(loginUserDto.getShainName());

            tsukibetsuShiftKakuninBean.add(tsukibetsuShiftBean);
        } else {
            // データあり
            tsukibetsuShiftKakuninBean = dtoToBean(tsukibetsuShiftDtoMap, loginUserDto);
        }

        // フォームにデータをセットする
        tsukibetsuShiftForm.setShiftCmbMap(shiftCmbMap);
        tsukibetsuShiftForm.setYearMonthCmbMap(yearMonthCmbMap);
        tsukibetsuShiftForm.setTsukibetsuShiftKakuninBeanList(tsukibetsuShiftKakuninBean);
        tsukibetsuShiftForm.setDateBeanList(dateBeanList);
        tsukibetsuShiftForm.setYearMonth(yearMonth);

        return mapping.findForward(forward);
    }


    /**
     * DtoからBeanへ変換する
     * @param tsukibetsuShiftDtoMap
     * @param loginUserDto
     * @return 一覧に表示するリスト
     * @author naraki
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private List<TsukibetsuShiftKakuninBean> dtoToBean(Map<String, List<TsukibetsuShiftDto>> tsukibetsuShiftDtoMap
                                                      , LoginUserDto loginUserDto)
                                                                        throws IllegalArgumentException,
                                                                        IllegalAccessException,
                                                                        InvocationTargetException {
        Collection<List<TsukibetsuShiftDto>> collection = tsukibetsuShiftDtoMap.values();

        List<TsukibetsuShiftKakuninBean> tsukibetsuShiftKakuninBeanList = new ArrayList<TsukibetsuShiftKakuninBean>();

        for (List<TsukibetsuShiftDto> tsukibetsuShiftDtoList : collection) {

            // 実行するオブジェクトの生成
            TsukibetsuShiftKakuninBean tsukibetsuShiftKakuninBean = new TsukibetsuShiftKakuninBean();

            // メソッドの取得
            Method[] methods = tsukibetsuShiftKakuninBean.getClass().getMethods();

            // メソッドのソートを行う
            Comparator<Method> asc = new MethodComparator();
            Arrays.sort(methods, asc); // 配列をソート

            int index = 0;
            int listSize = tsukibetsuShiftDtoList.size();

            String shainId = "";
            String shainName = "";

            for (int i = 0; i < methods.length; i++) {
                // "setShiftIdXX" のメソッドを動的に実行する
                if (methods[i].getName().startsWith("setSymbol") && listSize > index) {
                    TsukibetsuShiftDto tsukibetsuShiftDto = tsukibetsuShiftDtoList.get(index);
                    // メソッド実行
                    methods[i].invoke(tsukibetsuShiftKakuninBean, tsukibetsuShiftDto.getSymbol());

                    shainId = tsukibetsuShiftDto.getShainId();
                    shainName = tsukibetsuShiftDto.getShainName();

                    index ++;
                }
            }

            tsukibetsuShiftKakuninBean.setShainId(shainId);
            tsukibetsuShiftKakuninBean.setShainName(shainName);

            tsukibetsuShiftKakuninBeanList.add(tsukibetsuShiftKakuninBean);

        }

        return tsukibetsuShiftKakuninBeanList;
    }
}
