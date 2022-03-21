package com.sungkyul.osan.map;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sungkyul.osan.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter {
    protected static final String TAG = "DataAdapter";

    // TODO : TABLE 이름을 명시해야함
    protected static final String TABLE_NAME = "facility";

    private final Context mContext;
    private SQLiteDatabase mDb;
    private DBHelper mDbHelper;

    public DataAdapter(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(mContext);
    }

    /*public DataAdapter createDatabase() throws SQLException
    {
        try
        {
            mDbHelper.createDataBase();
        }
        catch (IOException mIOException)
        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }*/

    public DataAdapter open() throws SQLException {
        try {
            mDbHelper.openDataBase();
            mDb = mDbHelper.getReadableDatabase();
        } catch (SQLException mSQLException) {
            Log.e(TAG, "open >>" + mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public List getTableData(String query) {

        int count = 0;

        try {
            // 모델 넣을 리스트 생성
            List items = new ArrayList();

            // TODO : 모델 선언
            Item item = null;

            Cursor mCur = mDb.rawQuery(query, null);

            if (mCur != null) {
                // 칼럼의 마지막까지
                while (mCur.moveToNext()) {

                    // TODO : 커스텀 모델 생성
                    item = new Item();

                    // TODO : Record 기술
                    // name, latitude, longitude
                    item.setName(mCur.getString(0));
                    item.setLatitude(mCur.getDouble(1));
                    item.setLongitude(mCur.getDouble(2));

                    // 리스트에 넣기
                    items.add(item);

                    count++;
                }

            }

            Log.v("알림", "총 " + count + "개의 값이 리스트에 들어갔습니다.");

            return items;
        } catch (SQLException mSQLException) {
            Log.e(TAG, "getTestData >>" + mSQLException.toString());
            throw mSQLException;
        }
    }
}
