/**
 * ファイル名：DbConstant.java
 *
 * 変更履歴
 * 1.0  2010/09/06 Kazuya.Naraki
 */
package constant;

/**
 * 説明：テーブル定義コンスタント
 * @author naraki
 *
 */
public class DbConstant {

    /**
     * 社員マスタ
     */
    public enum M_shain {

        TABLE_NAME("M_SHAIN"),
        PREFIX("SH"),
        SHAIN_ID("SHAIN_ID"),
        PASSWORD("PASSWORD"),
        SHAIN_NAME("SHAIN_NAME"),
        SHAIN_NAME_KANA("SHAIN_NAME_KANA"),
        KENGEN_ID("KENGEN_ID"),
        CREATE_SHAIN_ID("CREATE_SHAIN_ID"),
        CREATE_DT("CREATE_DT"),
        UPDATE_SHAIN_ID("UPDATE_SHAIN_ID"),
        UPDATE_DT("UPDATE_DT");

        // 名称
        private String name;

        // コンストラクタ
        private M_shain(String name) {
            this.name = name;
        }

        /**
         * 社員マスタの名称を取得する
         * @return value
         */
        public String getName() {
            return name;
        }
    }

    /**
     * シフトマスタ
     */
    public enum M_shift {
        TABLE_NAME("M_SHIFT"),
        PREFIX("SF"),
        SHIFT_ID("SHIFT_ID"),
        SHIFT_NAME("SHIFT_NAME"),
        SYMBOL("SYMBOL"),
        START_TIME("START_TIME"),
        END_TIME("END_TIME"),
        BREAK_TIME("BREAK_TIME"),
        CREATE_SHAIN_ID("CREATE_SHAIN_ID"),
        CREATE_DT("CREATE_DT"),
        UPDATE_SHAIN_ID("UPDATE_SHAIN_ID"),
        UPDATE_DT("UPDATE_DT");

        // 名称
        private String name;

        // コンストラクタ
        private M_shift(String name) {
            this.name = name;
        }

        /**
         * シフトマスタの名称を取得する
         * @return value
         */
        public String getName() {
            return name;
        }
    }

    /**
     * 基本シフトマスタ
     */
    public enum M_base_shift {
        TABLE_NAME("M_BASE_SHIFT"),
        SHAIN_ID("SHAIN_ID"),
        MONDAY("MONDAY"),
        TUESDAY("TUESDAY"),
        WEDNESDAY("WEDNESDAY"),
        THURSDAY("THURSDAY"),
        FRIDAY("FRIDAY"),
        SATURDAY("SATURDAY"),
        SUNDAY("SUNDAY"),
        CREATE_SHAIN_ID("CREATE_SHAIN_ID"),
        CREATE_DT("CREATE_DT"),
        UPDATE_SHAIN_ID("UPDATE_SHAIN_ID"),
        UPDATE_DT("UPDATE_DT");

        // 名称
        private String name;

        // コンストラクタ
        private M_base_shift(String name) {
            this.name = name;
        }

        /**
         * 基本シフトマスタの名称を取得する
         * @return value
         */
        public String getName() {
            return name;
        }
    }

    /**
     * 分類マスタ
     */
    public enum Mbunrui {

        TABLE_NAME("M_BUNRUI"),
        PREFIX("BU"),
        BUNRUI_ID("BUNRUI_ID"),
        CODE("CODE"),
        NAME("NAME"),
        HYOUJI_JYUN("HYOUJI_JYUN"),
        HYOUJI1("HYOUJI1"),
        HYOUJI2("HYOUJI2"),
        HYOUJI3("HYOUJI3"),
        HYOUJI4("HYOUJI4"),
        HYOUJI5("HYOUJI5"),
        CREATE_SHAIN_ID("CREATE_SHAIN_ID"),
        CREATE_DT("CREATE_DT"),
        UPDATE_SHAIN_ID("UPDATE_SHAIN_ID"),
        UPDATE_DT("UPDATE_DT");

        // 名称
        private String name;

        // コンストラクタ
        private Mbunrui(String name) {
            this.name = name;
        }

        /**
         * 社員マスタの名称を取得する
         * @return value
         */
        public String getName() {
            return name;
        }
    }

    /**
     * 祝日マスタ
     */
    public enum Mshukujitsu {

        TABLE_NAME("M_SHUKUJITSU"),
        YEAR_MONTH_DAY("YEAR_MONTH_DAY"),
        SHUKUJITSU_NAME("SHUKUJITSU_NAME"),
        CREATE_SHAIN_ID("CREATE_SHAIN_ID"),
        CREATE_DT("CREATE_DT"),
        UPDATE_SHAIN_ID("UPDATE_SHAIN_ID"),
        UPDATE_DT("UPDATE_DT");

        // 名称
        private String name;

        // コンストラクタ
        private Mshukujitsu(String name) {
            this.name = name;
        }

        /**
         * 祝日マスタの名称を取得する
         * @return value
         */
        public String getName() {
            return name;
        }
    }

    /**
     * シフトテーブル
     */
    public enum T_Shift {

        TABLE_NAME("T_SHIFT"),
        SHAIN_ID("SHAIN_ID"),
        YEAR_MONTH_DAY("YEAR_MONTH_DAY"),
        SHIFT_ID("SHIFT_ID"),
        KIBOU_SHIFT_ID("KIBOU_SHIFT_ID"),
        BIKOU("BIKOU"),
        CREATE_SHAIN_ID("CREATE_SHAIN_ID"),
        CREATE_DT("CREATE_DT"),
        UPDATE_SHAIN_ID("UPDATE_SHAIN_ID"),
        UPDATE_DT("UPDATE_DT");

        // 名称
        private String name;

        // コンストラクタ
        private T_Shift(String name) {
            this.name = name;
        }

        /**
         * シフトテーブルの名称を取得する
         * @return value
         */
        public String getName() {
            return name;
        }
    }

    /**
     * 勤務実績テーブル
     */
    public enum T_Kinmu_Jisseki {

        TABLE_NAME("T_KINMU_JISSEKI"),
        SHAIN_ID("SHAIN_ID"),
        KADOU_DAY("KADOU_DAY"),
        START_TIME("START_TIME"),
        END_TIME("END_TIME"),
        BREAK_TIME("BREAK_TIME"),
        JITSUDOU_TIME("JITSUDOU_TIME"),
        JIKANGAI_TIME("JIKANGAI_TIME"),
        KYUUJITSU_TIME("KYUUJITSU_TIME"),
        BIKOU("BIKOU"),
        CREATE_SHAIN_ID("CREATE_SHAIN_ID"),
        CREATE_DT("CREATE_DT"),
        UPDATE_SHAIN_ID("UPDATE_SHAIN_ID"),
        UPDATE_DT("UPDATE_DT");

        // 名称
        private String name;

        // コンストラクタ
        private T_Kinmu_Jisseki(String name) {
            this.name = name;
        }

        /**
         * シフトテーブルの名称を取得する
         * @return value
         */
        public String getName() {
            return name;
        }
    }
}
