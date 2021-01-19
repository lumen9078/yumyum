package com.example.mobileproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NyomDB extends SQLiteOpenHelper {
    private static  final  int DB_VERSION = 1;
    public NyomDB(@Nullable Context context, @Nullable String name ) {
        this(context, name, null, DB_VERSION );
    }
    public NyomDB(@Nullable Context context, @Nullable String name,
                  @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table restaurants (name varchar(40)," + "category varchar(40),"
                +"location varchar(100)," + "phone varchar(20)," + "PRIMARY KEY(name))" );
        db.execSQL("create table menu (id int, menuname varchar(40)," + "price varchar(40),"
                + "id_res varchar(40)," + "PRIMARY KEY(id)," + "FOREIGN KEY(`id_res`) REFERENCES restaurants(name))");
        db.execSQL("create table review (id int, context varchar(100)," + "rating int,"
                + "id_res varchar(40)," + "PRIMARY KEY(id)," + "FOREIGN KEY(`id_res`) REFERENCES restaurants(name))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table restaurants");
        db.execSQL("drop table menu");
        db.execSQL("drop table review");
    }

    public void addCreate(SQLiteDatabase db) {
        db.execSQL("delete from restaurants");
        db.execSQL("insert into restaurants values ('보스찜닭', '한식', '36.964908062992436,127.87089483610539', '043-855-3788')");
        db.execSQL("insert into restaurants values ('도란도락', '한식', '36.96357573499525,127.87134003902369', '043-855-7981')");
        db.execSQL("insert into restaurants values ('맘스터치', '기타', '36.96522834530849,127.87176031088835', '043-847-0095')");
        db.execSQL("insert into restaurants values ('이삭토스트', '기타', '36.96529105751086,127.87150275860314', '043-843-7797')");
        db.execSQL("insert into restaurants values ('백봉', '기타', '36.964593331189626,127.87142181713219', '043-855-7897')");
        db.execSQL("insert into restaurants values ('김군파닭', '야식','36.958613437454424,127.87037963589817','043-851-9233')");
        db.execSQL("insert into restaurants values ('이춘봉치킨', '야식','36.958613437454424,127.87037963589817','043-851-9233')");

        db.execSQL("delete from menu");
        db.execSQL("insert into menu values (1, '싸이버거', '3,200원', '맘스터치')");
        db.execSQL("insert into menu values (2, '싸이버거세트', '5,400원', '맘스터치')");
        db.execSQL("insert into menu values (3, '디럭스불고기버거', '3,800원', '맘스터치')");
        db.execSQL("insert into menu values (4, '디럭스불고기버거세트', '6,000원', '맘스터치')");
        db.execSQL("insert into menu values (5, '휠렛버거', '3,400원', '맘스터치')");
        db.execSQL("insert into menu values (6, '휠렛버거세트', '5,600원', '맘스터치')");
        db.execSQL("insert into menu values (7, '햄치즈', '2,600원', '이삭토스트')");
        db.execSQL("insert into menu values (8, '햄 스페셜', '2,900원', '이삭토스트')");
        db.execSQL("insert into menu values (9, '베이컨', '3,200원', '이삭토스트')");
        db.execSQL("insert into menu values (10, '피자', '3,700원', '이삭토스트')");
        db.execSQL("insert into menu values (11, '감자', '3,000원', '이삭토스트')");
        db.execSQL("insert into menu values (12, '짜장면', '4,500원', '백봉')");
        db.execSQL("insert into menu values (13, '짬뽕', '5,500원', '백봉')");
        db.execSQL("insert into menu values (14, '간짜장', '6,000원', '백봉')");
        db.execSQL("insert into menu values (15, '우동', '6,000원', '백봉')");
        db.execSQL("insert into menu values (16, '짬짜면', '6,500원', '백봉')");
        db.execSQL("insert into menu values (17,'소금구이바베큐치킨', '16,000', '이춘봉치킨')");
        db.execSQL("insert into menu values (18,'이춘봉바베큐치킨', '18,000', '이춘봉치킨')");
        db.execSQL("insert into menu values (19,'숯불양념바베큐치킨', '18,000', '이춘봉치킨')");
        db.execSQL("insert into menu values (20,'크림슨이춘봉바베큐치킨', '19,000', '이춘봉치킨')");
        db.execSQL("insert into menu values (21,'갈비바베큐치킨', '18,000', '이춘봉치킨')");
        db.execSQL("insert into menu values (22, '까만찜닭(소, 순살)', '17,000원', '보스찜닭')");
        db.execSQL("insert into menu values (23, '까만찜닭(중, 순살)', '24,000원', '보스찜닭')");
        db.execSQL("insert into menu values (24, '까만찜닭(소, 뼈)', '16,000원', '보스찜닭')");
        db.execSQL("insert into menu values (25, '까만찜닭(중, 뼈)', '23,000원', '보스찜닭')");
        db.execSQL("insert into menu values (26, '빨간찜닭(소, 순살)', '17,000원', '보스찜닭')");
        db.execSQL("insert into menu values (27, '빨간찜닭(중, 순살)', '24,000원', '보스찜닭')");
        db.execSQL("insert into menu values (28, '하얀찜닭(소, 순살)', '17,000원', '보스찜닭')");
        db.execSQL("insert into menu values (29, '하얀찜닭(중, 순살)', '24,000원', '보스찜닭')");
        db.execSQL("insert into menu values (30, '가츠동', '3,800원', '도란도락')");
        db.execSQL("insert into menu values (31, '김치가츠동', '4,300원', '도란도락')");
        db.execSQL("insert into menu values (32, '매운가츠동', '4,300원', '도란도락')");
        db.execSQL("insert into menu values (33, '치킨마요', '3,000원', '도란도락')");
        db.execSQL("insert into menu values (34, '매운치킨마요', '3,500원', '도란도락')");
        db.execSQL("insert into menu values (35, '왕치킨마요', '3,700원', '도란도락')");

        db.execSQL("delete from review");
        db.execSQL("insert into review values (1, '정말 맛있어요', '4.0', '맘스터치')");
        db.execSQL("insert into review values (2, '정말 맛있어요', '3.0', '이삭토스트')");
        db.execSQL("insert into review values (3, '정말 맛있어요', '4.0', '백봉')");
        db.execSQL("insert into review values (8,'정말 맛있어요', '5.0', '이춘봉치킨')");
        db.execSQL("insert into review values (9,'가격대비 맛있어요', '5.0', '김군파닭')");
        db.execSQL("insert into review values (10,'너무 비싸요...', '2.0', '이춘봉치킨')");
        db.execSQL("insert into review values (11, '정말 맛있어요', '4.0', '보스찜닭')");
        db.execSQL("insert into review values (12, '맛있어요', '5.0', '보스찜닭')");
        db.execSQL("insert into review values (13, '..', '4.0', '보스찜닭')");
        db.execSQL("insert into review values (14, '정말 맛있어요', '3.0', '도란도락')");
        db.execSQL("insert into review values (15, '가볍게 먹기 좋아요', '4.0', '도란도락')");
        db.execSQL("insert into review values (16, '.', '1.0', '도란도락')");
        db.execSQL("insert into review values (17, '좋아요', '3.0', '도란도락')");
    }
}
