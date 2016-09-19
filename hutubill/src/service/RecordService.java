package service;

import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.Date;

/**
 * Created by 11981 on 2016/9/11.
 */
public class RecordService {
    RecordDAO recordDao = new RecordDAO();
    public void add(int spend, Category c, String comment, Date date){
        Record r = new Record();
        r.spend = spend;
        r.cid = c.id;
        r.comment = comment;
        r.date = date;
        recordDao.add(r);
    }

}
