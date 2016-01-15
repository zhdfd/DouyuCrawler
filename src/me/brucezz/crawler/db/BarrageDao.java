package me.brucezz.crawler.db;

import me.brucezz.crawler.util.DBUtil;
import me.brucezz.crawler.model.Barrage;
import me.brucezz.crawler.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zero on 2016/01/06.
 * Douyu
 */
public class BarrageDao {

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS Barrage(_id INT PRIMARY KEY AUTO_INCREMENT,uid INT NOT NULL,snick VARCHAR(64) NOT NULL,content VARCHAR(256) NOT NULL,date DATETIME NOT NULL, rid INT NOT NULL );";

    private static final String SQL_INSERT_BARRAGE = "INSERT INTO Barrage(uid,snick, content, date,rid) VALUES(%d, '%s', '%s', '%s', %d) ";

    public static void createTable() {
        DBUtil.execSQL(SQL_CREATE_TABLE);
    }

    /**
     * 保存弹幕数据到数据库
     */
    public static boolean saveBarrage(List<Barrage> barrages) {
        List<String> sqlList = new ArrayList<>();
        for (Barrage barrage : barrages) {
            sqlList.add(String.format(
                    SQL_INSERT_BARRAGE,
                    barrage.getUid(),
                    barrage.getSnick(),
                    barrage.getContent(),
                    DateUtil.datetime(barrage.getDate()),
                    barrage.getRid())
            );
        }
        return DBUtil.execSQL(sqlList);
    }

    public static void main(String[] args) {
        //测试
        List<Barrage> barrages = new ArrayList<>();
        barrages.add(new Barrage(99999999, "X", "X", 9999));
        barrages.add(new Barrage(99999999, "XX", "XX", 9999));
        barrages.add(new Barrage(99999999, "XX", "XX", 9999));

        saveBarrage(barrages);
    }
}
