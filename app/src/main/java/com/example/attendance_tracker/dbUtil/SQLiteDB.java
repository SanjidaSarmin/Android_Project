package com.example.attendance_tracker.dbUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


import com.example.attendance_tracker.entity.Quiz;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "quizDB";
    private static final int DB_VERSION = 1;

    public SQLiteDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String categoryQuery = "CREATE TABLE category ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL UNIQUE )";

        // Create quiz table with categoryId as a foreign key
        String quizQuery = "CREATE TABLE quiz ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "question TEXT NOT NULL, option_a TEXT NOT NULL, option_b TEXT NOT NULL, " +
                "option_c TEXT NOT NULL, option_d TEXT NOT NULL, correct_option TEXT NOT NULL, " +
                "categoryId INTEGER NOT NULL, " +
                "FOREIGN KEY (categoryId) REFERENCES category(id) ON DELETE CASCADE )";

        sqLiteDatabase.execSQL(categoryQuery);
        sqLiteDatabase.execSQL(quizQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS quiz");
        onCreate(db);
    }

    // Create - Insert Quiz
    public long insertQuiz(Quiz quiz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("question", quiz.getQuestion());
        values.put("option_a", quiz.getOptionA());
        values.put("option_b", quiz.getOptionB());
        values.put("option_c", quiz.getOptionC());
        values.put("option_d", quiz.getOptionD());
        values.put("correct_option", quiz.getCorrectOption());
        values.put("categoryId", quiz.getCategoryId());

        return db.insert("quiz", null, values);
    }



    // Read - Get Quiz by ID
    public Quiz getQuizById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("quiz", null, "id = ?", new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Quiz quiz = new Quiz(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6)
            );
            cursor.close();
            return quiz;
        }
        return null;
    }

    // Read - Get All Quizzes
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM quiz", null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Quiz quiz = new Quiz(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                );
                quizList.add(quiz);
            }
            cursor.close();
        }
        return quizList;
    }

    // Update - Update Quiz
    public int updateQuiz(Quiz quiz) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("question", quiz.getQuestion());
        values.put("option_a", quiz.getOptionA());
        values.put("option_b", quiz.getOptionB());
        values.put("option_c", quiz.getOptionC());
        values.put("option_d", quiz.getOptionD());
        values.put("correct_option", quiz.getCorrectOption());

        return db.update("quiz", values, "id = ?", new String[]{String.valueOf(quiz.getId())});
    }

    // Delete - Delete Quiz
    public void deleteQuiz(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("quiz", "id = ?", new String[]{String.valueOf(id)});
    }

    // Create - Insert Category
    public long insertCategory(String categoryName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", categoryName);
        return db.insert("category", null, values);
    }

    // Read - Get All Categories
    public List<String> getAllCategories() {
        List<String> categoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM category", null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                categoryList.add(cursor.getString(1)); // Index 1 -> category name
            }
            cursor.close();
        }
        return categoryList;
    }

    // Read - Get Category by ID
    public String getCategoryById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("category", new String[]{"name"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String categoryName = cursor.getString(0);
            cursor.close();
            return categoryName;
        }
        return null;
    }

    // Update - Update Category Name
    public int updateCategory(int id, String newName) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", newName);

        return db.update("category", values, "id = ?", new String[]{String.valueOf(id)});
    }

    // Delete - Delete Category
    public void deleteCategory(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("category", "id = ?", new String[]{String.valueOf(id)});
    }



}

