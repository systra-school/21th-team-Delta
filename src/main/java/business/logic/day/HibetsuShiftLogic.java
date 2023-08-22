package business.logic.day;

import java.sql.SQLException;
import java.util.List;

import business.db.dao.day.HibetsuShiftDao;
import business.dto.day.HibetsuShiftDto;

public class HibetsuShiftLogic {
    /**
     * 日別シフトDtoリストを取得する。
     * @return 社員マスタリスト
     * @author naraki
     */
    public  List<HibetsuShiftDto> getHibetsuShiftDtoList(String yearMonthDay) throws SQLException{

        // 日別シフトDao
        HibetsuShiftDao hibetsuShiftDao = new HibetsuShiftDao();

        // 日別シフトDtoリストを取得s
        List<HibetsuShiftDto> hibetsuShiftDtoList = hibetsuShiftDao.getHibetsuShiftDtoList(yearMonthDay);

        return hibetsuShiftDtoList;
    }
}
