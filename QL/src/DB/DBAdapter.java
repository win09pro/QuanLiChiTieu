package DB;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.util.Log;

public class DBAdapter {
	String title;
	public Activity activity;
	DatabaseHelpert data = new DatabaseHelpert(activity);
	
	/*---------------------------------Nguoi Dung--------------------------------------*/
	static final String IDnguoidung = "_id";
	static final String username = "username";
	static final String password = "password";
	static final String Tennguoidung = "Tennguoidung";

	private static final String CaNhanTable = "User";
	
	/*---------------------------Khoang Chi--------------------------------------*/
	public static final String IDKhoanChi = "_idKC";
	public static final String idloaiKhoanChi = "loaikhoanchi";
	public static final String soTienKhoanChi = "sotienchi";
	public static final String ngayChi = "ngaychi";
	public static final String ghiChuChi = "ghichuchi";
	private static final String KhoanChiTable = "KhoanChi";

	/*------------------------The Loai Chi------------------------------------*/

	public static final String IDLoaiChi = "_idLoaiChi";
	public static final String tenLoaiChi = "tenloaichi";

	private static final String LoaiChiTable = "LoaiChi";

	/*-------------------------Khoan Thu-------------------------------------------*/

	public static final String IDKhoanThu = "_idKT";
	public static final String idloaiKhoanThu = "loaikhoanthu";
	public static final String soTienKhoanThu = "sotienkt";
	public static final String ngayThu = "ngaythu";
	public static final String ghiChuThu = "ghichuthu";
	private static final String KhoanthuTable = "KhoanThu";
	/*------------------------------The Loai Thu-------------------------------------*/

	public static final String idLoaiThu = "_idLoaiThu";
	public static final String tenLoaiThu = "tenloaithu";
	
	private static final String LoaithuTable = "LoaiThu";
	
//	/*-------------------------Luong-------------------------------------------*/

	public static final String IDLuong = "_idLuong";
	public static final String idNguoiDungLuong = "idNguoiDungLuong";
	public static final String tienLuong= "tienLuong";
	public static final String ngayNhapLuong= "ngayNhapLuong";
	
	private static final String LuongTable = "Luong";


	/*---------------------Create Nguoi Dung---------------------------------*/
	private static final String DATABASE_NguoiDung = "CREATE TABLE "
			+ CaNhanTable + "(" + IDnguoidung
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " 
			+ username + " TEXT NOT NULL, " + password 
			+ " Text NOT NULL, " + Tennguoidung + " Text);";
	/*-----------------------Create Khoang Chi---------------------------------*/
	private static final String DATABASE_KhoanChi = "CREATE TABLE "
			+ KhoanChiTable + "(" + IDKhoanChi
			+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + idloaiKhoanChi
			+ " TEXT, " + soTienKhoanChi + " Integer, " + ngayChi
			+ " Date NOT NULL, " + ghiChuChi + " TEXT, FOREIGN KEY (" + idloaiKhoanChi + ") REFERENCES "
			+ LoaiChiTable + " (" + IDLoaiChi + "));";
	/*------------------------Create The Loai Chi--------------------------------*/
	private static final String DATABASE_TheLoaiChi = "CREATE TABLE "
			+ LoaiChiTable + " (" + IDLoaiChi
			+ " INTEGER PRIMARY KEY autoincrement, " + tenLoaiChi + " TEXT)";
	/*--------------------------Create Khoang Thu------------------------------*/
	private static final String DATABASE_KhoanThu = "CREATE TABLE "
			+ KhoanthuTable + "(" + IDKhoanThu
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + idloaiKhoanThu
			+ " TEXT, " + soTienKhoanThu + " Integer, " + ngayThu
			+ " Date NOT NULL , " + ghiChuThu + " TEXT, FOREIGN KEY (" + idloaiKhoanThu + ") REFERENCES "
			+ LoaithuTable + " (" + idLoaiThu + "));";
	/*---------------------------Create The Loai Thu----------------------------*/
	private static final String DATABASE_TheLoaiThu = "CREATE TABLE " + LoaithuTable + " (" + idLoaiThu + " INTEGER PRIMARY KEY autoincrement ,"
			+ tenLoaiThu + " TEXT)";
///*----------------------------Create Luong--------------------------------*/
	private static final String DATABASE_Luong = "CREATE TABLE " + LuongTable 
			+ "(" 
			+ IDLuong + " INTEGER PRIMARY KEY AUTOINCREMENT," 
			+ idNguoiDungLuong + " TEXT, " 
			+ tienLuong + " INTEGER, "
			+ ngayNhapLuong+ " INTEGER NOT NULL, FOREIGN KEY (" + idNguoiDungLuong + ") REFERENCES "
			+ CaNhanTable + " (" + IDnguoidung + "));";
//	
	
	private static final String DATABASE_NAME = "QL1.db";
	private static final int DATABASE_VERSION = 1;
	private static final String TAG = "DBAdapter";
	private final Context context;

	public static  DatabaseHelpert DBHelper;
	private SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
//		DBHelper = new DatabaseHelpert(context);
	}

	public void createDB() {
		DBHelper = new DatabaseHelpert(context);
	}
	private static class DatabaseHelpert extends SQLiteOpenHelper {
		
		DatabaseHelpert(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				Log.e("Create Table", "");	
				db.execSQL(DATABASE_NguoiDung);
				db.execSQL(DATABASE_KhoanChi);
				db.execSQL(DATABASE_TheLoaiChi);
				db.execSQL(DATABASE_KhoanThu);
				db.execSQL(DATABASE_TheLoaiThu);
				db.execSQL(DATABASE_Luong);
//				db.execSQL(DATABASE_KhoanNO);
		          
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w(TAG, oldVersion + " to " + newVersion
					+ ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS CaNhanTable");
			onCreate(db);

		}
		

	}
	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}
	
	/*Hàm đăng nhập với đối số đầu vào là tên acc và mật khẩu*/
	public boolean kiemTraLogin(String acc,String mk){	
		Cursor c = db.rawQuery("select * from "+CaNhanTable+" where "+username+" = ? and "+password+" = ?", new String[] { acc,mk });		
		if(c.getCount()==1){
			c.close();
			return true;			
		}else{
			c.close();
			return false;
		}
	}
	/*----------------------------Thêm Người Dùng------------------------------*/
	public long createUser(String userN, String matKhau) {       
	    ContentValues cv = new ContentValues();
	    cv.put(username, userN);
	    cv.put(password, matKhau);
	    cv.put(Tennguoidung, "nodata");
	    open();
	    return db.insert(CaNhanTable, null, cv);
	}
	
	/*---------------------Kiem Tra xem luong da nhap chua? ------------*/
	public String layIDND(String acc){
		Cursor layIDNguoiDung = db.rawQuery("select * from "+CaNhanTable + " where "+username+ " = ? ", new String[]{acc});
		String idNguoiDung = layIDNguoiDung.getString(0);
		return idNguoiDung;
	}
	public boolean kiemTraNhapLuong(String acc, String ngay){
		//Cursor layIDNguoiDung = db.rawQuery("select * from "+CaNhanTable + " where "+username+ " = ? ", new String[]{acc});
		//String idNguoiDung = layIDNguoiDung.getString(0);
		//String tienLuong="";
		
		Cursor layLuong = db.rawQuery("select * from "+LuongTable + " where "+idNguoiDungLuong+" = ? and "+ngayNhapLuong+" = ?", new String[]{ acc, ngay });
		if(layLuong.getCount() !=0){
			layLuong.close();	
			return true;
		}
		else {
			layLuong.close();
			return false;
		}
		
	}
	/*--------------------Ham Lay luong cua nguoi dung da nhap--------------*/
	
/*------------------------thêm thể loại chi-----------------*/
	public long insertLoaiChi(String tenLC) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(tenLoaiChi, tenLC);
		open();
		return db.insert(LoaiChiTable, null, initialValues);
	}
	/*public void insertSomeTLC() {
		 
		inserttlc("Mua Sắm");
		inserttlc("Cà Phê");
		inserttlc("Du Lịch");
		inserttlc("Chi Gia Đình");
		inserttlc("Đám Tiệc");
		inserttlc("Điện, Nước, Xăng");
		inserttlc("Điện thoại-TH cáp-Internet");
		inserttlc("Giáo Dục");
		inserttlc("Giải Trí");
		inserttlc("Đồ Gia Dụng");
		 
		 }*/
	/*------------------------thêm thể loại thu-----------------*/
	public long insertLoaiThu(String tenLT) 
	{
		ContentValues initialValues = new ContentValues();
		initialValues.put(tenLoaiThu, tenLT);
		
		return db.insert(LoaithuTable, null, initialValues);
	}
	/*public void theloai() {
		 
		inserttlt("Tiền Lương");
		inserttlt("Cổ Phiếu");
		inserttlt("Kinh Doanh");
		inserttlt("Tiền Làm Thêm");
		inserttlt("Tiền Cho Vay");
		inserttlt("Bất Động Sản");
	}*/
	/*-------------------------Thêm Khoản Vay----------------------------*/
//	public long insertkhoanvay(String tenkv, int sotien,int laisuat, String ngayvayv, String ngaytrav) {
//		
//		ContentValues initialValues = new ContentValues();
//		initialValues.put(tenkhoanvay, tenkv);
//		initialValues.put(sotienv, sotien);
//		initialValues.put(laisuatv, laisuat);
//		initialValues.put(ngayvay, ngayvayv);
//		initialValues.put(ngaymuon, ngaytrav);
//		open();
//		return db.insert(KhoanVayTable, null, initialValues);
//	}
	/*public void insertSomekhoanvay() {
		 
		insertkhoanvay("Ngân Hàng",20000,6,"25/6/2013","30/8/2013");
		insertkhoanvay("Nguyễn Hồng Lan",3000000,3,"10/3/2013","10/8/2013");
	}*/
	/*-------------------------Thêm Khoản No----------------------------*/
//	public long insertkhoanno(String tenkn, int sotien,int laisuat, String ngayvayn, String ngaymuonn) {
//		
//		ContentValues initialValues = new ContentValues();
//		initialValues.put(tenkhoanno, tenkn);
//		initialValues.put(sotienn, sotien);
//		initialValues.put(laisuatn, laisuat);
//		initialValues.put(ngayno, ngayvayn);
//		initialValues.put(ngaytrano, ngaymuonn);
//		
//		return db.insert(KhoanNoTable, null, initialValues);
//	}
	/*public void insertSomeno() {
		 
		insertkhoanno("Ngân Hàng",20000,6,"25/6/2013","30/8/2013");
		insertkhoanno("Nguyễn Hồng Lan",3000000,3,"10/3/2013","10/8/2013");
		insertkhoanno("Nguyễn Hùng",4000000,1,"10/4/2013","10/9/2014");
	}*/
	/*-------------------------Thêm Khoản Chi----------------------------*/
public long insertKhoanChi(String idLoaiKC,int soTien, String ngaychi) {
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(idloaiKhoanChi, idLoaiKC);
		initialValues.put(soTienKhoanChi, soTien);
		initialValues.put(ngayChi, ngaychi);
		open();
		return db.insert(KhoanChiTable, null, initialValues);
	}
/*public void insertKC() {
	 
	insertkhoanchi("Ngân Hàng",20000,"25/6/2013");
}*/
/*-------------------------Thêm Khoản Thu---------------------------*/
public long insertKhoanThu(String idLoaiKT,int sotienkt, String ngaythu) {
		
		ContentValues initialValues = new ContentValues();
		initialValues.put(idloaiKhoanThu, idLoaiKT);
		initialValues.put(soTienKhoanThu, sotienkt);
		initialValues.put(ngayThu, ngaythu);
		open();
		return db.insert(KhoanthuTable, null, initialValues);
	}
/*public void insertKT() {
	 
	insertkhoanthu("Chứng Khoán",1000000,"25/6/2013");
}*/


//public void insertND() {
//	 
//	createUser("thangpham","123");
//}

/*----------Xóa tất cả thể loại chi------------------------*/
	public int deleteAllLoaiChi() {

		return db.delete(LoaiChiTable, null, null);

	}
	/*----------Xóa tất cả thể loại thu------------------------*/
	public int deleteAllLoaiThu() {

		return db.delete(LoaithuTable, null, null);

	}
	/*----------Xóa tất cả Khoan vay------------------------*/
//	public int deleteAllkv() {
//		return db.delete(KhoanVayTable, null, null);
//
//	}
//	/*----------Xóa tất cả Khoan No------------------------*/
//	public int deleteAllkn() {
//
//		return db.delete(KhoanNoTable, null, null);
//
//	}
	/*----------Xóa tất cả Khoan Thu------------------------*/
	public int deleteAllKhoanThu() {

		return db.delete(KhoanthuTable, null, null);

	}
	/*----------------------Xóa id Khoan Chi----------------------*/

	public boolean deletekc(long rowId) {
		return db.delete(KhoanChiTable, IDKhoanChi + "=" + rowId, null) > 0;
	}
	/*----------------------Xóa id Khoan Thu----------------------*/

	public boolean deletekt(long rowId) {
		return db.delete(KhoanthuTable, IDKhoanThu + "=" + rowId, null) > 0;
	}
	/*----------------------Xóa id the loai chi----------------------*/

	public boolean deleteLoaiChi(long rowId) {
		return db.delete(LoaiChiTable, IDLoaiChi + "=" + rowId, null) > 0;
	}
	/*----------------------Xóa id the loai thu----------------------*/

	public boolean deleteLoaiThu(long rowId) {
		return db.delete(LoaithuTable, idLoaiThu + "=" + rowId, null) > 0;
	}
	/*----------------------Xóa id khoan vay----------------------*/

//	public boolean deletetkv(long rowId) {
//		return db.delete(KhoanVayTable, colkvID + "=" + rowId, null) > 0;
//	}
//	/*----------------------Xóa id khoan No----------------------*/
//
//	public boolean deletetkn(long rowId) {
//		return db.delete(KhoanNoTable, colknID + "=" + rowId, null) > 0;
//	}
//	
/*-----------------------------liệt kê tất cả người dung----------------------*/
	 public String getDatandung() {
	        String[] columns = new String[] {IDnguoidung,username,password,Tennguoidung};
	        Cursor c = db.query(CaNhanTable, columns, null, null, null, null, null);
	        /*if(c==null)
	            Log.v("Cursor", "C is NULL");*/
	        String result="";
	        int iRow = c.getColumnIndex(IDnguoidung);
	        int iN = c.getColumnIndex(username);
	        int iMK = c.getColumnIndex(password);
	        int iHoTen = c.getColumnIndex(Tennguoidung);
	        for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){           
	            result = result +" "+ c.getString(iRow)
	                    + " - id:" + c.getString(iN)
	                    + " - pw:" + c.getString(iMK)
	                    + " - ten:" + c.getString(iHoTen) + "\n";
	        }
	        c.close();
	        //Log.v("Result", result);
	        return result;
	    }
/*----------------------------liệt kê tất cả thể loại chi----------------------*/
	public Cursor getAllLoaiChi() {
		return db.query(LoaiChiTable, new String[] { IDLoaiChi,
				tenLoaiChi }, null, null, null, null, null);
	}
	/*----------------------------liệt kê tất cả Khoan Chi----------------------*/
	public Cursor getAllKhoanChi() {
		return db.query(KhoanChiTable, new String[] { IDKhoanChi,idloaiKhoanChi,soTienKhoanChi,
				ngayChi, ghiChuChi }, null, null, null, null, null);
	}
	
    public List<String> getAllLabelsKhoanChi(){
        List<String> labels = new ArrayList<String>();
         
        // Select All Query
        String selectQuery = "SELECT  * FROM " + LoaiChiTable;
      
       
        Cursor cursor = db.rawQuery(selectQuery, null);
      
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
         
        // closing connection
        cursor.close();
        db.close();
         
        // returning lables
        return labels;
    }
	/*----------------------------liệt kê tất cả Khoan Thu---------------------*/
	public Cursor getAllKhoanThu() {
		return db.query(KhoanthuTable, new String[] { IDKhoanThu,idloaiKhoanThu,soTienKhoanThu,
				ngayThu, ghiChuThu }, null, null, null, null, null);
	}
	
	public List<String> getAllLabelsKhoanThu(){
        List<String> labels = new ArrayList<String>();
         
        // Select All Query
        String selectQuery = "SELECT  * FROM " + LoaithuTable;
      
       
        Cursor cursor = db.rawQuery(selectQuery, null);
      
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
         
        // closing connection
        cursor.close();
        db.close();
         
        // returning lables
        return labels;
    }
	/*----------------------------liệt kê tất cả thể loại thu----------------------*/
	public Cursor getAllLoaiThu() {
		
		return db.query(LoaithuTable, new String[] { 
				idLoaiThu, tenLoaiThu}, null, null, null, null, null);
	}
	/*----------------------------liệt kê tất cả khoản vay----------------------*/
//	public Cursor getAllkv() {
//		return db.query(KhoanVayTable, new String[] { colkvID,
//				tenkhoanvay,sotienv,laisuatv,ngayvay,ngaymuon}, null, null, null, null, null);
//	}
//	/*----------------------------liệt kê tất cả khoản No----------------------*/
//	public Cursor getAllkn() {
//		if(db !=null)
//		return db.query(KhoanNoTable, new String[] { colknID,
//				tenkhoanno,sotienn,laisuatn,ngayno,ngaytrano}, null, null, null, null, null);
//		return null;
//	}
	/*----------------------------liệt kê _id Khoan Chi----------------------*/
	public Cursor laysotien (long sType)
    {
       return db.rawQuery("select coltentheloaic from KhoanChiTable where mathloaichi =\""+sType+"\"", null);
    }
	/*--------------Liệt kê _id thể loại thu----------------------------*/
	public Cursor gettlt (long rowId) throws SQLException {
		Cursor mCursor = db.query(true, LoaithuTable, new String[] {
				idLoaiThu, tenLoaiThu }, idLoaiThu + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	/*--------------Liệt kê _id thể loại chi----------------------------*/
	public Cursor gettlc(long rowId) throws SQLException {
		Cursor mCursor = db.query(true, LoaiChiTable, new String[] {
				IDLoaiChi, tenLoaiChi }, IDLoaiChi + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
//	/*--------------Liệt kê _id Khoản Vay----------------------------*/
//	public Cursor getkhoanvay(long rowId) throws SQLException {
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
//		String ngayvay = sdf.format(new Date());
//		String ngaymuon = sdf.format(new Date());
//		Cursor mCursor = db.query(true, KhoanVayTable, new String[] {
//				colkvID, tenkhoanvay,sotienv,laisuatv,ngayvay,ngaymuon }, colkvID + "=" + rowId, null, null,
//				null, null, null);
//		if (mCursor != null) {
//			mCursor.moveToFirst();
//		}
//		return mCursor;
//	
//	}
//	/*--------------Liệt kê _id Khoản No----------------------------*/
//	public Cursor getkhoanno(long rowId) throws SQLException {
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
//		String ngayno = sdf.format(new Date());
//		String ngaytrano = sdf.format(new Date());
//		Cursor mCursor = db.query(true, KhoanNoTable, new String[] {
//				colknID, tenkhoanno,sotienn,laisuatn,ngayno,ngaytrano }, colknID + "=" + rowId, null, null,
//				null, null, null);
//		if (mCursor != null) {
//			mCursor.moveToFirst();
//		}
//		return mCursor;
//	
//	}
	/*--------------Liệt kê _id Khoản Chi----------------------------*/
	public Cursor getKhoanChi(long rowId) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		String colngaychi = sdf.format(new Date());
		Cursor mCursor = db.query(true, KhoanChiTable, new String[] {
				IDKhoanChi, idloaiKhoanChi,soTienKhoanChi,colngaychi }, IDKhoanChi + "=" + rowId, null, null,
				null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	
	}
/*--------------------Updater thể Loại chi----------------------------*/
	public boolean updatetlc(long rowId, String name) {
		ContentValues args = new ContentValues();
		args.put(tenLoaiChi, name);
		return db.update(LoaiChiTable, args, IDLoaiChi + "=" + rowId, null) > 0;
	}
	/*--------------------Updater thể Loại thu----------------------------*/
	public boolean updatetlt (long rowId, String name) {
		ContentValues args = new ContentValues();
		args.put(tenLoaiThu, name);
		return db.update(LoaithuTable, args, idLoaiThu + "=" + rowId, null) > 0;
	}
	/*--------------------Updater Khoan Vay----------------------------*/
//	public boolean updatekhoanvay(long rowId, String name,int sotien,int laisuat,String ngayvayv, String ngaymuonv) {
//		ContentValues args = new ContentValues();
//		args.put(tenkhoanvay, name);
//		args.put(sotienv, sotien);
//		args.put(laisuatv, laisuat);
//		args.put(ngayvay, ngayvayv);
//		args.put(ngaymuon, ngaymuonv);
//		return db.update(KhoanVayTable, args, colkvID + "=" + rowId, null) > 0;
//	}
//	/*--------------------Updater Khoan No----------------------------*/
//	public boolean updatekhoanno(long rowId, String name,int sotien,int laisuat,String ngaynon, String ngaytra) {
//		
//		
//		ContentValues args = new ContentValues();
//		args.put(tenkhoanno, name);
//		args.put(sotienn, sotien);
//		args.put(laisuatn, laisuat);
//		args.put(ngayno, ngaynon);
//		args.put(ngaytrano, ngaytra);
//		return db.update(KhoanNoTable, args, colknID + "=" + rowId, null) > 0;
//	}
	/*--------------------Updater Khoan Chi----------------------------*/
	public boolean updatekhoanchi(long rowId, String name,int sotien,String ngaychi) {
		
		
		ContentValues args = new ContentValues();
		args.put(idloaiKhoanChi, name);
		args.put(soTienKhoanChi, sotien);
		args.put(ngayChi, ngaychi);
		return db.update(KhoanChiTable, args, IDKhoanChi + "=" + rowId, null) > 0;
	}
	/*--------------------Updater Khoan Thu----------------------------*/
	public boolean updatekhoanthu(long rowId, String name,int sotienkt,String ngaythu) {
		
		
		ContentValues args = new ContentValues();
		args.put(idloaiKhoanThu, name);
		args.put(soTienKhoanThu, sotienkt);
		args.put(ngayThu, ngaythu);
		return db.update(KhoanthuTable, args, IDKhoanThu + "=" + rowId, null) > 0;
	}
	
/*-------------------------Seach Khoản Vay----------------------------*/	
//	public Cursor fetchCountriesByName(String inputText) throws SQLException {
//		  Log.w(TAG, inputText);
//		  Cursor mCursor = null;
//		  if (inputText == null  ||  inputText.length () == 0)  {
//		   mCursor = db.query(KhoanVayTable, new String[] {colkvID,
//		     tenkhoanvay, sotienv, laisuatv, ngayvay,ngaymuon}, 
//		     null, null, null, null, null);
//		 
//		  }
//		  else {
//		   mCursor = db.query(true, KhoanVayTable, new String[] {colkvID,
//				   tenkhoanvay, sotienv, laisuatv, ngayvay,ngaymuon}, 
//				   tenkhoanvay + " like '%" + inputText + "%'", null,
//		     null, null, null, null);
//		  }
//		  if (mCursor != null) {
//		   mCursor.moveToFirst();
//		  }
//		  return mCursor;
//		 
//		 }
///*------------------------------Seach Khoản Nợ----------------------------*/
//	public Cursor seachkhoanno(String inputText) throws SQLException {
//		  Log.w(TAG, inputText);
//		  Cursor mCursor = null;
//		  if (inputText == null  ||  inputText.length () == 0)  {
//		   mCursor = db.query(KhoanNoTable, new String[] {colknID,
//		     tenkhoanno, sotienn, laisuatn, ngayno,ngaytrano}, 
//		     null, null, null, null, null);
//		 
//		  }
//		  else {
//		   mCursor = db.query(true, KhoanNoTable, new String[] {colknID,
//				   tenkhoanno, sotienn, laisuatn, ngayno,ngaytrano}, 
//				   tenkhoanno + " like '%" + inputText + "%'", null,
//		     null, null, null, null);
//		  }
//		  if (mCursor != null) {
//		   mCursor.moveToFirst();
//		  }
//		  return mCursor;
//		 
//		 }
	/*-------------------------------Seach Khoản Thu---------------------------*/
//	public Cursor seachkhoanthu(String inputText) throws SQLException {
//		  Log.w(TAG, inputText);
//		  Cursor mCursor = null;
//		  if (inputText == null  ||  inputText.length () == 0)  {
//		   mCursor = db.query(KhoanthuTable, new String[] {colktID,
//		     mathloaithu, colsotienkt, colngaythu}, 
//		     null, null, null, null, null);
//		 
//		  }
//		  else {
//		   mCursor = db.query(true, KhoanthuTable, new String[] {colktID,
//				   mathloaithu, colsotienkt, colngaythu}, 
//				   mathloaithu + " like '%" + inputText + "%'", null,
//		     null, null, null, null);
//		  }
//		  if (mCursor != null) {
//		   mCursor.moveToFirst();
//		  }
//		  return mCursor;
//		 
//	}
//	/*--------------------------------Seach Khoản Chi---------------------------------*/
//	public Cursor seachkhoanchi(String inputText) throws SQLException {
//		  Log.w(TAG, inputText);
//		  Cursor mCursor = null;
//		  if (inputText == null  ||  inputText.length () == 0)  {
//		   mCursor = db.query(KhoanChiTable, new String[] {colkcID,
//		     mathloaichi, colsotienkc, colngaychi}, 
//		     null, null, null, null, null);
//		 
//		  }
//		  else {
//		   mCursor = db.query(true, KhoanChiTable, new String[] {colkcID,
//				   mathloaichi, colsotienkc, colngaychi}, 
//				   mathloaichi + " like '%" + inputText + "%'", null,
//		     null, null, null, null);
//		  }
//		  if (mCursor != null) {
//		   mCursor.moveToFirst();
//		  }
//		  return mCursor;
//		 
//	}
//    public String getBMIDataData(){
//
//        String[] column =
//                    new String[]{ tenkhoanvay };
//            Cursor c = 
//            		DBHelper.getWritableDatabase().query( KhoanVayTable, column, null, null, null, null, null );
//
//            String result = "";
//            int iData = c.getColumnIndex( tenkhoanvay );
//
//            for ( c.moveToFirst(); ! c.isAfterLast(); c.moveToNext() ){
//                result = result + c.getString( iData );
//            }
//
//
//        return result;
//    }
//    
//    public Cursor getcount(String ten){
//    	
//    	return db.rawQuery("Select tenkhoanvay, from KhoanVay",new String[] {ten});
//    
//    }
//    
//    
//    
//    
//    public String getBMIsoiem(){
//
//        String[] column =
//                    new String[]{ sotienv };
//            Cursor c = 
//            		DBHelper.getWritableDatabase().query( KhoanVayTable, column, null, null, null, null, null );
//
//            String result = "";
//            int iData = c.getColumnIndex( sotienv );
//
//            for ( c.moveToFirst(); ! c.isAfterLast(); c.moveToNext() ){
//                result = result + c.getString( iData );
//            }
//
//
//        return result;
//    }
//    
//   public XYMultipleSeriesDataset getDemoDataset(String title) {
//
//	    String[] column = new String[]{ tenkhoanvay };
//	    Cursor c = DBHelper.getWritableDatabase().query( KhoanVayTable, column, null, null, null, null, null );
//
//	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//
//	    XYSeries firstSeries = new XYSeries("Sample series One");
//	    TimeSeries series2 = new TimeSeries(title);
//
//	    
//
//	    while (!c.isAfterLast()) {
//	        int date = c.getColumnIndex(DBAdapter.tenkhoanvay);
//	        int weight = c.getColumnIndex(DBAdapter.sotienv);
//	        firstSeries.add(weight, date);
//	        c.moveToNext();
//	    }
//
//	    c.close();
//
//	    dataset.addSeries(firstSeries);
//	    dataset.addSeries(series2);
//
//	    return dataset;
//	}


//	public Intent getIntent(Context context) {
//
//		 //Lager TimeSeries for den første linja
//	    XYMultipleSeriesDataset dataset = getDemoDataset("BA đồ thị");
//
//	    //Kode for render
//	    XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
//
//	    //Optimalisering linje1
//	    XYSeriesRenderer renderer = new XYSeriesRenderer();
//	    renderer.setColor(Color.YELLOW);
//	    renderer.setPointStyle(PointStyle.CIRCLE);
//	    renderer.setFillPoints(true);
//
//	    // Optimalisering linje2 husk rekke følgen
//	    XYSeriesRenderer renderer2 = new XYSeriesRenderer();
//	    renderer2.setColor(Color.BLUE);
//	    renderer2.setPointStyle(PointStyle.SQUARE);
//	    renderer2.setFillPoints(true);
//
//	    //Legger til render seriene
//	    mRenderer.addSeriesRenderer(renderer);
//
//	    //Optimalisering grafen
//	    mRenderer.setChartTitle("Test");
//	    mRenderer.setZoomEnabled(true);
//	    mRenderer.setZoomButtonsVisible(true);
//	    mRenderer.setBackgroundColor(Color.BLACK);
//	    mRenderer.setApplyBackgroundColor(true);
//	    mRenderer.setXTitle("Dager");
//	    mRenderer.setShowGrid(true);
//
//	    mRenderer.addSeriesRenderer(renderer2);
//
//
//	    Intent intent = ChartFactory.getLineChartIntent(context, dataset, 
//	            mRenderer, "Line Graph Title");
//
//	    return intent;
//	}
    
}

