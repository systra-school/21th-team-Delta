/**
 * ファイル名：TsukibetsuShiftLogic.java
 *
 * 変更履歴
 * 1.0  2010/10/06 Kazuya.Naraki
 */
package business.logic.mth;

import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bbreak.excella.reports.exporter.ExcelOutputStreamExporter;
import org.bbreak.excella.reports.model.ReportBook;
import org.bbreak.excella.reports.model.ReportSheet;
import org.bbreak.excella.reports.processor.ReportProcessor;
import org.bbreak.excella.reports.tag.ColRepeatParamParser;
import org.bbreak.excella.reports.tag.RowRepeatParamParser;
import org.bbreak.excella.reports.tag.SingleParamParser;

import form.common.DateBean;
import form.mth.TsukibetsuShiftKakuninBean;
import form.mth.TsukibetsuShiftKakuninForm;

import action.mth.TsukibetsuShiftKakuninPrintAction;
import business.db.dao.mth.TsukibetsuShiftDao;
import business.dto.LoginUserDto;
import business.dto.mth.TsukibetsuBaseShiftDto;
import business.dto.mth.TsukibetsuShiftDto;
import business.logic.utils.CheckUtils;
import business.logic.utils.CommonUtils;

/**
 * 説明：希望出勤日入力処理のロジック
 * @author naraki
 *
 */
public class TsukibetsuShiftLogic {

    /**
     * シフトテーブルより情報を取得する。
     * @param yearMonth 検索対象年月
     * @param shiftFlg true：シフトIDを取得 false：希望シフトIDを取得
     * @return 出勤希望Dtoリスト
     * @author naraki
     */
    public Map<String, List<TsukibetsuShiftDto>> getTsukibetsuShiftDtoMap(String yearMonth, boolean shiftFlg) throws SQLException{

        // 戻り値
        Map<String, List<TsukibetsuShiftDto>> tsukibetsuShiftDtoMap = new LinkedHashMap<String, List<TsukibetsuShiftDto>>();

        // Dao
        TsukibetsuShiftDao dao = new TsukibetsuShiftDao();

        // シフト情報を取得する。
        List<TsukibetsuShiftDto> tsukibetsuShiftDtoList = dao.getShiftTblData(yearMonth, shiftFlg);

        String oldShainId = "";

        // 一時領域
        List<TsukibetsuShiftDto> tmpList = new ArrayList<TsukibetsuShiftDto>();

        // DB取得より取得する値を各社員づつ区切る
        for(TsukibetsuShiftDto dto : tsukibetsuShiftDtoList) {
            if (CheckUtils.isEmpty(oldShainId)) {
                // 社員IDが空のとき（初回）
                oldShainId = dto.getShainId();

                // 取得した値を戻り値のリストにセットする。
                tmpList.add(dto);

            } else {
                if (oldShainId.equals(dto.getShainId())) {
                    // 同一社員のデータ
                    // 取得した値を戻り値のリストにセットする。
                    tmpList.add(dto);
                } else {
                    // 別社員のデータのとき
                    // 前の社員分をマップにつめる
                    tsukibetsuShiftDtoMap.put(oldShainId, tmpList);

                    // oldShainId を入れ替える
                    oldShainId = dto.getShainId();

                    // 新しい社員のデータを追加していく
                    tmpList = new ArrayList<TsukibetsuShiftDto>();
                    tmpList.add(dto);
                }
            }
        }

        if (!CheckUtils.isEmpty(oldShainId)) {
            // 最後分を追加する
            tsukibetsuShiftDtoMap.put(oldShainId, tmpList);
        }

        return tsukibetsuShiftDtoMap;
    }

    /**
     * シフトテーブルのデータを登録・更新する。
     * @param tsukibetsuShiftDtoListList 月別シフト一覧
     * @return 基本シフトマップ
     * @author naraki
     * @throws SQLException
     */
    public void registTsukibetsuShift(List<List<TsukibetsuShiftDto>> tsukibetsuShiftDtoListList, LoginUserDto loginUserDto) throws SQLException {

        // Dao
        TsukibetsuShiftDao dao = new TsukibetsuShiftDao();
        // コネクション
        Connection connection = dao.getConnection();

        // トランザクション処理
        connection.setAutoCommit(false);

        try {
            for (List<TsukibetsuShiftDto> tsukibetsuShiftDtoList : tsukibetsuShiftDtoListList) {
                // 人数分のループ
                for (TsukibetsuShiftDto tsukibetsuShiftDto : tsukibetsuShiftDtoList) {
                    // 日数分ループ

                    // 社員ID
                    String shainId = tsukibetsuShiftDto.getShainId();
                    // 対象年月
                    String yearMonthDay = tsukibetsuShiftDto.getYearMonthDay();

                    // レコードの存在を確認する
                    boolean isData = dao.isData(shainId, yearMonthDay);

                    if (isData) {
                        // 更新
                        dao.updateShiftTbl(tsukibetsuShiftDto, loginUserDto);
                    } else {
                        // 登録
                        dao.registShiftTbl(tsukibetsuShiftDto, loginUserDto);
                    }

                }
            }

        } catch (SQLException e) {
            // ロールバック処理
            connection.rollback();
            // 切断
            connection.close();

            throw e;
        }

        // コミット
        connection.commit();
        // 切断
        connection.close();

    }


    /**
     * 月別シフト確認の印刷を行う
     * @param tsukibetsuShiftKakuninForm 月別シフト確認フォーム
     * @param excelOutePuteStreamExporter
     * @return
     * @author naraki
     */
    public void print(TsukibetsuShiftKakuninForm tsukibetsuShiftKakuninForm
                            , ExcelOutputStreamExporter excelOutePuteStreamExporter) throws Exception {

        // ①読み込むテンプレートファイルのパス(拡張子含)
        // ②出力先のファイルパス(拡張子はExpoterによって自動的に付与されるため、不要。)
        // ③出力ファイルフォーマット(ConvertConfigurationの配列)
        // を指定し、ReportBookインスタンスを生成します。
        //
        String templateFileName;

        templateFileName = "月別シフト確認テンプレート.xls";

        URL templateFileUrl = TsukibetsuShiftKakuninPrintAction.class.getResource(templateFileName);
        String templateFilePath = URLDecoder.decode(templateFileUrl.getPath(), "UTF-8");

        String outputFileName = "月別シフト確認";
        String outputFileDir = "C:/reports_output/";
        String outputFilePath = outputFileDir.concat(outputFileName);
        ReportBook outputBook = new ReportBook(templateFilePath, outputFilePath, ExcelOutputStreamExporter.FORMAT_TYPE);

        //
        // テンプレートファイル内のシート名と出力シート名を指定し、
        // ReportSheetインスタンスを生成して、ReportBookに追加します。
        //
        ReportSheet outputSheet = new ReportSheet("出勤希望確認");
        outputBook.addReportSheet(outputSheet);

        //
        // 置換パラメータをReportSheetオブジェクトに追加します。
        // (反復置換のパラメータには配列を渡します。)
        //

        List<String> hidukeList = new ArrayList<String>(31);
        List<String> youbiList = new ArrayList<String>(31);

        String yearMonth = "";

        List<DateBean> dateBeanList = tsukibetsuShiftKakuninForm.getDateBeanList();
        int listSize = dateBeanList.size();
        for (int i = 0; i < 31; i++) {
            DateBean dateBean = null;

            if (i < listSize) {
                dateBean= dateBeanList.get(i);
            }

            if (CheckUtils.isEmpty(dateBean)) {
                hidukeList.add("");
                youbiList.add("");
            } else {
                hidukeList.add(CommonUtils.changeFormat(dateBean.getYearMonthDay(), "yyyyMMdd", "dd"));
                youbiList.add(dateBean.getYoubi());
                yearMonth = CommonUtils.changeFormat(dateBean.getYearMonthDay(), "yyyyMMdd", "yyyy/MM");
            }
        }

        outputSheet.addParam(SingleParamParser.DEFAULT_TAG, "年月", yearMonth);
        outputSheet.addParam(ColRepeatParamParser.DEFAULT_TAG, "日付", hidukeList.toArray());
        outputSheet.addParam(ColRepeatParamParser.DEFAULT_TAG, "曜日", youbiList.toArray());

        List<TsukibetsuShiftKakuninBean> tsukibetsuShiftKakuninBeanList = tsukibetsuShiftKakuninForm.getTsukibetsuShiftKakuninBeanList();
        List<String> shainNameList = new ArrayList<String>();

        // 変数宣言 日毎（列毎）のリストを作成します。
        List<String> shiftIdList01 = new ArrayList<String>();
        List<String> shiftIdList02 = new ArrayList<String>();
        List<String> shiftIdList03 = new ArrayList<String>();
        List<String> shiftIdList04 = new ArrayList<String>();
        List<String> shiftIdList05 = new ArrayList<String>();
        List<String> shiftIdList06 = new ArrayList<String>();
        List<String> shiftIdList07 = new ArrayList<String>();
        List<String> shiftIdList08 = new ArrayList<String>();
        List<String> shiftIdList09 = new ArrayList<String>();
        List<String> shiftIdList10 = new ArrayList<String>();
        List<String> shiftIdList11 = new ArrayList<String>();
        List<String> shiftIdList12 = new ArrayList<String>();
        List<String> shiftIdList13 = new ArrayList<String>();
        List<String> shiftIdList14 = new ArrayList<String>();
        List<String> shiftIdList15 = new ArrayList<String>();
        List<String> shiftIdList16 = new ArrayList<String>();
        List<String> shiftIdList17 = new ArrayList<String>();
        List<String> shiftIdList18 = new ArrayList<String>();
        List<String> shiftIdList19 = new ArrayList<String>();
        List<String> shiftIdList20 = new ArrayList<String>();
        List<String> shiftIdList21 = new ArrayList<String>();
        List<String> shiftIdList22 = new ArrayList<String>();
        List<String> shiftIdList23 = new ArrayList<String>();
        List<String> shiftIdList24 = new ArrayList<String>();
        List<String> shiftIdList25 = new ArrayList<String>();
        List<String> shiftIdList26 = new ArrayList<String>();
        List<String> shiftIdList27 = new ArrayList<String>();
        List<String> shiftIdList28 = new ArrayList<String>();
        List<String> shiftIdList29 = new ArrayList<String>();
        List<String> shiftIdList30 = new ArrayList<String>();
        List<String> shiftIdList31 = new ArrayList<String>();

        // 日付ごとにリストに加えていく
        for (int i = 0; i < tsukibetsuShiftKakuninBeanList.size() - 1; i++) {
            // 人毎（行ごと）のデータを追加していく

            TsukibetsuShiftKakuninBean shukkinKibouKakuninBean = tsukibetsuShiftKakuninBeanList.get(i);
            shiftIdList01.add(shukkinKibouKakuninBean.getSymbol01());
            shiftIdList02.add(shukkinKibouKakuninBean.getSymbol02());
            shiftIdList03.add(shukkinKibouKakuninBean.getSymbol03());
            shiftIdList04.add(shukkinKibouKakuninBean.getSymbol04());
            shiftIdList05.add(shukkinKibouKakuninBean.getSymbol05());
            shiftIdList06.add(shukkinKibouKakuninBean.getSymbol06());
            shiftIdList07.add(shukkinKibouKakuninBean.getSymbol07());
            shiftIdList08.add(shukkinKibouKakuninBean.getSymbol08());
            shiftIdList09.add(shukkinKibouKakuninBean.getSymbol09());
            shiftIdList10.add(shukkinKibouKakuninBean.getSymbol10());
            shiftIdList11.add(shukkinKibouKakuninBean.getSymbol11());
            shiftIdList12.add(shukkinKibouKakuninBean.getSymbol12());
            shiftIdList13.add(shukkinKibouKakuninBean.getSymbol13());
            shiftIdList14.add(shukkinKibouKakuninBean.getSymbol14());
            shiftIdList15.add(shukkinKibouKakuninBean.getSymbol15());
            shiftIdList16.add(shukkinKibouKakuninBean.getSymbol16());
            shiftIdList17.add(shukkinKibouKakuninBean.getSymbol17());
            shiftIdList18.add(shukkinKibouKakuninBean.getSymbol18());
            shiftIdList19.add(shukkinKibouKakuninBean.getSymbol19());
            shiftIdList20.add(shukkinKibouKakuninBean.getSymbol20());
            shiftIdList21.add(shukkinKibouKakuninBean.getSymbol21());
            shiftIdList22.add(shukkinKibouKakuninBean.getSymbol22());
            shiftIdList23.add(shukkinKibouKakuninBean.getSymbol23());
            shiftIdList24.add(shukkinKibouKakuninBean.getSymbol24());
            shiftIdList25.add(shukkinKibouKakuninBean.getSymbol25());
            shiftIdList26.add(shukkinKibouKakuninBean.getSymbol26());
            shiftIdList27.add(shukkinKibouKakuninBean.getSymbol27());
            shiftIdList28.add(shukkinKibouKakuninBean.getSymbol28());
            shiftIdList29.add(shukkinKibouKakuninBean.getSymbol29());
            shiftIdList30.add(shukkinKibouKakuninBean.getSymbol30());
            shiftIdList31.add(shukkinKibouKakuninBean.getSymbol31());

            shainNameList.add(shukkinKibouKakuninBean.getShainName());
        }

        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "社員名", shainNameList.toArray());

        /*
         * 日毎（列毎）にパラメタに値をセットしていきます。
         */

        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト1", shiftIdList01.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト2", shiftIdList02.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト3", shiftIdList03.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト4", shiftIdList04.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト5", shiftIdList05.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト6", shiftIdList06.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト7", shiftIdList07.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト8", shiftIdList08.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト9", shiftIdList09.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト10", shiftIdList10.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト11", shiftIdList11.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト12", shiftIdList12.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト13", shiftIdList13.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト14", shiftIdList14.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト15", shiftIdList15.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト16", shiftIdList16.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト17", shiftIdList17.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト18", shiftIdList18.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト19", shiftIdList19.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト20", shiftIdList20.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト21", shiftIdList21.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト22", shiftIdList22.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト23", shiftIdList23.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト24", shiftIdList24.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト25", shiftIdList25.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト26", shiftIdList26.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト27", shiftIdList27.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト28", shiftIdList28.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト29", shiftIdList29.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト30", shiftIdList30.toArray());
        outputSheet.addParam(RowRepeatParamParser.DEFAULT_TAG, "シフト31", shiftIdList31.toArray());


        // ReportProcessorインスタンスを生成し、
        // ReportBookを元にレポート処理を実行します。
        //
        ReportProcessor reportProcessor = new ReportProcessor();

        reportProcessor.addReportBookExporter(excelOutePuteStreamExporter);
        reportProcessor.process(outputBook);
    }
}
