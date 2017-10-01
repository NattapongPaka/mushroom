package app.cpe.mushroom.data.db;

/**
 * Created by DEV on 1/10/2560.
 */

public class Db {
    private static Db instance;
    private DatabaseHelper db;

    private HistoryDataSource historyDataSource;

    private Db() {
    }

    public static Db getInstance() {
        if (instance == null) {
            instance = new Db();
        }
        return instance;
    }

    public void init(DatabaseHelper context){
        this.db = context;
    }

    public void openDbHelper(){
        historyDataSource = new HistoryDataSource(db);
        historyDataSource.open();
    }

    public void closeDbHelper(){
        historyDataSource.close();
    }

    public HistoryDataSource getHistoryDataSource() {
        return historyDataSource;
    }
}
