package app.cpe.mushroom.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app.cpe.mushroom.data.entity.Baked;
import app.cpe.mushroom.data.entity.Plant;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mydb.db";
    /**
     * Customer Table Statement
     */
    private static final String DATABASE_BAKED_CREATE = "CREATE TABLE IF NOT EXISTS "
            + Baked.TABLE
            + "( "
            + Baked.ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Baked.TEMP_LOG + " TEXT,"
            + Baked.HUM_LOG + " TEXT,"
            + Baked.TIME_STAMP + " TEXT"
            + " );";

    private static final String DATABASE_PLANT_CREATE = "CREATE TABLE IF NOT EXISTS "
            + Plant.TABLE
            + "( "
            + Plant.ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Plant.TEMP_LOG + " TEXT,"
            + Plant.HUM_LOG + " TEXT,"
            + Plant.TIME_STAMP + " TEXT"
            + " );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(DATABASE_BAKED_CREATE);
        db.execSQL(DATABASE_PLANT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean existsColumnInTable(SQLiteDatabase inDatabase, String inTable, String columnToCheck) {
        Cursor mCursor = null;
        try {
            // Query 1 row
            mCursor = inDatabase.rawQuery("SELECT * FROM " + inTable + " LIMIT 0", null);
            // getColumnIndex() gives us the index (0 to ...) of the column - otherwise we get a -1
            if (mCursor.getColumnIndex(columnToCheck) != -1)
                return true;
            else
                return false;

        } catch (Exception ex) {
            return false;
        } finally {
            if (mCursor != null) mCursor.close();
        }
    }

    public boolean isTableExists(SQLiteDatabase mDatabase, String tableName, boolean openDb) {
        Cursor cursor = mDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        try {
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.close();
                    return true;
                }
                cursor.close();
            }
        } catch (Exception ex) {
            return false;
        }
        return false;
    }

    private void deleteColum(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL("CREATE TEMPORARY TABLE backup (a, b);");
            db.execSQL("INSERT INTO backup SELECT a, b FROM mytable;");
            db.execSQL("DROP TABLE mytable;");
            db.execSQL("CREATE TABLE mytable (a, b);");
            db.execSQL("INSERT INTO mytable SELECT a, b FROM backup;");
            db.execSQL("DROP TABLE backup;");
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private void alterTable(SQLiteDatabase db, String table, String tableName) {
        db.beginTransaction();
        try {
            db.execSQL("ALTER TABLE " + table + " RENAME TO " + tableName + ";");
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

}