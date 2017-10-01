package app.cpe.mushroom.data.db;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import rx.Subscription;

/**
 * Created by nattapongpaka on 3/30/2017 AD.
 */

public abstract class BaseDataSource {

    public Subscription subscription;
    private DatabaseHelper dbHelper;
    protected SQLiteDatabase database;
    protected Cursor cursor;

    BaseDataSource(DatabaseHelper db) {
        synchronized (this) {
            if (dbHelper == null) {
                dbHelper = db;
            }
        }
    }

    public synchronized void open() throws SQLException {
        if (database == null || !database.isOpen()) {
            database = dbHelper.getWritableDatabase();
        }
    }

    public synchronized void openTransaction() {
        open();
        if (database != null) {
            database.beginTransaction();
        }
    }

    public synchronized void transactionSuccess() {
        if (database != null) {
            database.setTransactionSuccessful();
        }
    }

    public synchronized void closeTransaction() {
        if (database != null) {
            database.endTransaction();
        }
        close();
    }

    public synchronized void close() {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
//        if (database != null && database.isOpen()) {
//            database.close();
//            database = null;
//        }
        unSubscription();
    }

    public synchronized void closeDB() {
        if (database != null && database.isOpen()) {
            database.close();
            database = null;
        }
    }

    public synchronized void unSubscription() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    protected void deleteTable(String table){
        try {
            database.delete(table, null, null);
        }catch (Exception e ){
            e.printStackTrace();
        }
    }



}
