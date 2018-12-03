package in.sourcecode.testproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import in.sourcecode.testproject.HelperContract.HelperEntry;

public class Helperdb extends SQLiteOpenHelper{

    private String db="profil";
    private int ver=1;
  //  private String sql=" CREATE TABLE "+ HelperEntry.TABLE2 +" ( "+ HelperEntry.COLUMN_ID+" TEXT PRIMARY KEY , "+HelperEntry.COLUMN_NAME+" TEXT ,"+HelperEntry.COLUMN_Contact+" TEXT,"+HelperEntry.COLUMN_EmailId+" TEXT, "+ HelperEntry.COLUMN_PASS+" TEXT , "+HelperEntry.COLUMN_OCCUPATION+" TEXT ,"+HelperEntry.COLUMN_IMAGE+ "  BLOB )";
    private String sql1="CREATE TABLE "+ HelperEntry.TABLE1 + "("+ HelperEntry.COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " + HelperEntry.COLUMN_NAME+ " TEXT ," +HelperEntry.COLUMN_Contact+ " TEXT,"+HelperEntry.COLUMN_EmailId+" TEXT, "+ HelperEntry.COLUMN_PASS+" TEXT , "+HelperEntry.COLUMN_OCCUPATION+" TEXT ,"+HelperEntry.COLUMN_IMAGE+ "  BLOB )";


    //private String CREATE_ENTRY2 = "CREATE TABLE  " + InventoryEntry.TABLE2 + "(" + InventoryEntry.COLUMN_ID + " INTEGER PRIMARY KEY ," + InventoryEntry.COLUMN_PRODUCT_IMAGE + "  BLOB ," + InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT ," + InventoryEntry.COLUMN_PRODUCT_PRICE + " REAL , " + InventoryEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER ," + InventoryEntry.COLUMN_PRODUCT_SUPPLIER + " TEXT )";
    //String sql = "CREATE TABLE " + TABLE_NAME + " (\n" + "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT employees_pk PRIMARY KEY AUTOINCREMENT,\n" + "    " + COLUMN_NAME + " varchar(200) NOT NULL,\n" + "    " + COLUMN_GENDER + " varchar(20) NOT NULL,\n" + "    " + COLUMN_BLOD_GROUP + " varchar(10) NOT NULL,\n" + "    " + COLUMN_MOBILE + " varchar(35) NOT NULL,\n" + "    " + COLUMN_AGE + " double NOT NULL,\n" + "    " + COLUMN_QUANTITY + "  double NOT NULL\n" + ")";

    private String []selectAr={HelperEntry.COLUMN_ID,HelperEntry.COLUMN_NAME,HelperEntry.COLUMN_Contact,HelperEntry.COLUMN_EmailId,HelperEntry.COLUMN_OCCUPATION,HelperEntry.COLUMN_IMAGE};
   // private String updateSql="UPDATE "+HelperEntry.TABLE2 +" set "+HelperEntry.COLUMN_Contact+" where "+HelperEntry.COLUMN_Contact+ " =? ";

    public Helperdb(Context context,String db,String f,int ver){

        super(context,db,null,ver);
        this.db=db;
        this.ver=ver;

    }

    public Helperdb(Context context){
        super(context,"db",null,1);
        this.db=db;
        this.ver=ver;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("TAG", "create entries"+sql1);
      db.execSQL(sql1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     onCreate(db);

    }

    public void selectQuery(SQLiteDatabase db) {
        Cursor c = db.rawQuery(sql1, selectAr);
        c.moveToNext();
        int id = c.getInt(0);
        byte[] bytes = c.getBlob(1);
        String nm = c.getString(2);
        Double pn = c.getDouble(3);
        int qn = c.getInt(4);
        String sn = c.getString(5);
        String sem = c.getString(6);
        String sph = c.getString(7);

        Log.e("TAG", "" + id + bytes + nm + qn + sn + sem + sph);

    }


    public void updateQuery(SQLiteDatabase db) {
       // db.execSQL(updateSql);

        Log.e("TAG", "updat entries");

    }
}
