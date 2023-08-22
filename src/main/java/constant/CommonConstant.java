/**
 * ファイル名：CommonConstant.java
 *
 * 変更履歴
 * 1.0  2010/07/29 Kazuya.Naraki
 */
package constant;

/**
 * 説明：共通定数定義クラス
 * @author naraki
 *
 */
public class CommonConstant {

    /** 成功 */
    public static final String SUCCESS = "success";
    /** 失敗 */
    public static final String ERROR = "error";
    /** データなし */
    public static final String NODATA = "nodata";

    /** 空白 */
    public static final String BLANK = "";
    /** 空白ID */
    public static final String BLANK_ID = "-1";
    /** ハイフン */
    public static final String Hyphen = "-";

    /** 次 **/
    public static final String NEXT = "next";
    /** 前 **/
    public static final String BACK = "back";

    /** 年月日 yyyy/MM/dd */
    public static final String yearMonthDay = "yyyy/MM/dd";
    /** 年月日 yyyyMMdd */
    public static final String yearMonthDayNoSl = "yyyyMMdd";
    /** 年月 yyyy/MM */
    public static final String yearMonth ="yyyy/MM";
    /** 年月 yyyyMM */
    public static final String yearMonthNoSl ="yyyyMM";


    /**
     * 権限定数
     */
    public enum Kengen {
        KANRISYA("管理者", "01"),
        IPPAN("一般", "02");

        // 名称
        private String name;
        // ID
        private String id;

        // コンストラクタ
        private Kengen(String name, String id) {
            this.name = name;
            this.id = id;
        }

        /**
         * 権限の名称を取得する
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * 権限のIDを取得する
         * @return value
         */
        public String getId() {
            return id;
        }
    }

    /**
     * 分類ＩＤ
     */
    public enum BunruiId {
        KENGEN("BU0001");

        // 分類ＩＤ
        private String bunruiId;

        // コンストラクタ
        private BunruiId(String bunruiId) {
            this.bunruiId = bunruiId;
        }

        /**
         * 分類ＩＤを取得する
         * @return name
         */
        public String getBunruiId() {
            return bunruiId;
        }
    }

    /**
     * 曜日
     */
    public enum DayOfWeek {
        SUNDAY("日曜日", "日"),
        MONDAY("月曜日", "月"),
        TUESDAY("火曜日", "火"),
        WEDNESDAY("水曜日", "水"),
        THURESDAY("木曜日", "木"),
        FRIDAY("金曜日", "金"),
        SATURDAY("土曜日", "土");

        // 曜日名
        String meisyo;
        // 略名
        String ryaku;

        // コンストラクタ
        private DayOfWeek(String meisyo, String ryaku) {
            this.meisyo = meisyo;
            this.ryaku = ryaku;
        }

        /**
         * 名称を取得する
         * @return
         * @author naraki
         */
        public String getMeisyo() {
            return meisyo;
        }

        /**
         * 略名を取得する
         * @return
         * @author naraki
         */
        public String getRyaku() {
            return ryaku;
        }
    }
}
