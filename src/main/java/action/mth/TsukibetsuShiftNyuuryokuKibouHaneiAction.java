/**
 * ファイル名：TsukibetsuShiftNyuuryokuKibouHaneiAction.java
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
import form.mth.TsukibetsuShiftNyuuryokuBean;
import form.mth.TsukibetsuShiftNyuuryokuForm;

/**
 * 説明：月別シフト入力出勤希望反映アクションクラス
 * @author naraki
 */
public class TsukibetsuShiftNyuuryokuKibouHaneiAction extends TsukibetsuShiftNyuuryokuAbstractAction{

    /**
     * 説明：月別シフト入力初期表示アクションクラス
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
        TsukibetsuShiftNyuuryokuForm tsukibetsuShiftForm = (TsukibetsuShiftNyuuryokuForm) form;

        // 対象年月
        String yearMonth = CommonUtils.getFisicalDay(CommonConstant.yearMonthNoSl);

        // ロジック生成
        TsukibetsuShiftLogic tsukibetsuShiftLogic = new TsukibetsuShiftLogic();

        // 対象年月の月情報を取得する。
        List<DateBean> dateBeanList = CommonUtils.getDateBeanList(yearMonth);

        // 希望シフトIDを取得する
        Map<String,List<TsukibetsuShiftDto>> tsukibetsuShiftDtoMap = tsukibetsuShiftLogic.getTsukibetsuShiftDtoMap(yearMonth, false);

        List<TsukibetsuShiftNyuuryokuBean> tsukibetsuShiftBeanList = new ArrayList<TsukibetsuShiftNyuuryokuBean>();

        // セレクトボックスの取得
        ComboListUtilLogic comboListUtils = new ComboListUtilLogic();
        Map<String, String> shiftCmbMap = comboListUtils.getComboShift(true);
        Map<String, String> yearMonthCmbMap = comboListUtils.getComboYearMonth(CommonUtils.getFisicalDay(CommonConstant.yearMonthNoSl), 3, ComboListUtilLogic.KBN_YEARMONTH_NEXT, false);

        if (CheckUtils.isEmpty(tsukibetsuShiftDtoMap)) {
            // データなし
            TsukibetsuShiftNyuuryokuBean tsukibetsuShiftBean = new TsukibetsuShiftNyuuryokuBean();
            tsukibetsuShiftBean.setShainId(loginUserDto.getShainId());
            tsukibetsuShiftBean.setShainName(loginUserDto.getShainName());
            tsukibetsuShiftBean.setRegistFlg(true);

            tsukibetsuShiftBeanList.add(tsukibetsuShiftBean);
        } else {
            // データあり
            tsukibetsuShiftBeanList = dtoToBean(tsukibetsuShiftDtoMap, loginUserDto);
        }

        // フォームにデータをセットする
        tsukibetsuShiftForm.setShiftCmbMap(shiftCmbMap);
        tsukibetsuShiftForm.setYearMonthCmbMap(yearMonthCmbMap);
        tsukibetsuShiftForm.setTsukibetsuShiftNyuuryokuBeanList(tsukibetsuShiftBeanList);
        tsukibetsuShiftForm.setDateBeanList(dateBeanList);
        tsukibetsuShiftForm.setYearMonth(yearMonth);
        // ページング用
        tsukibetsuShiftForm.setMaxPage(CommonUtils.getMaxPage(tsukibetsuShiftDtoMap.size(), SHOW_LENGTH));

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
    private List<TsukibetsuShiftNyuuryokuBean> dtoToBean(Map<String, List<TsukibetsuShiftDto>> tsukibetsuShiftDtoMap
                                                      , LoginUserDto loginUserDto)
                                                                        throws IllegalArgumentException,
                                                                        IllegalAccessException,
                                                                        InvocationTargetException {
        Collection<List<TsukibetsuShiftDto>> collection = tsukibetsuShiftDtoMap.values();

        List<TsukibetsuShiftNyuuryokuBean> tsukibetsuShiftBeanList = new ArrayList<TsukibetsuShiftNyuuryokuBean>();

        for (List<TsukibetsuShiftDto> tsukibetsuShiftDtoList : collection) {

            // 実行するオブジェクトの生成
            TsukibetsuShiftNyuuryokuBean tsukibetsuShiftBean = new TsukibetsuShiftNyuuryokuBean();

            // メソッドの取得
            Method[] methods = tsukibetsuShiftBean.getClass().getMethods();

            // メソッドのソートを行う
            Comparator<Method> asc = new MethodComparator();
            Arrays.sort(methods, asc); // 配列をソート

            int index = 0;
            int listSize = tsukibetsuShiftDtoList.size();

            String shainId = "";
            String shainName = "";

            for (int i = 0; i < methods.length; i++) {
                // "setShiftIdXX" のメソッドを動的に実行する
                if (methods[i].getName().startsWith("setShiftId") && listSize > index) {
                    TsukibetsuShiftDto tsukibetsuShiftDto = tsukibetsuShiftDtoList.get(index);
                    // メソッド実行
                    methods[i].invoke(tsukibetsuShiftBean, tsukibetsuShiftDto.getShiftId());

                    shainId = tsukibetsuShiftDto.getShainId();
                    shainName = tsukibetsuShiftDto.getShainName();

                    index ++;
                }
            }

            tsukibetsuShiftBean.setShainId(shainId);
            tsukibetsuShiftBean.setShainName(shainName);
            tsukibetsuShiftBean.setRegistFlg(false);

            tsukibetsuShiftBeanList.add(tsukibetsuShiftBean);

        }

        return tsukibetsuShiftBeanList;
    }
}
