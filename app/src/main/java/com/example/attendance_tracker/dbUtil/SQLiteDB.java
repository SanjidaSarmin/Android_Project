package com.example.attendance_tracker.dbUtil;

import static java.security.AccessController.getContext;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


import com.example.attendance_tracker.entity.Category;
import com.example.attendance_tracker.entity.Quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDB extends SQLiteOpenHelper {
    private static final String DB_NAME = "quizDB";
    private static final int DB_VERSION = 1;

    private Context context;

    public SQLiteDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;  // Initialize context
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String categoryQuery = "CREATE TABLE category ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL)";

        // Create quiz table with categoryId as a foreign key
        String quizQuery = "CREATE TABLE quiz ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "question TEXT NOT NULL, option_a TEXT NOT NULL, option_b TEXT NOT NULL, " +
                "option_c TEXT NOT NULL, option_d TEXT NOT NULL, correct_option TEXT NOT NULL, " +
                "categoryId INTEGER NOT NULL, " +
                "FOREIGN KEY (categoryId) REFERENCES category(id) ON DELETE CASCADE )";

        sqLiteDatabase.execSQL(categoryQuery);
        sqLiteDatabase.execSQL(quizQuery);

//        executeSQLFromFile(sqLiteDatabase,  context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS quiz");
        db.execSQL("DROP TABLE IF EXISTS category");
        onCreate(db);
    }

    public void executeSQLFromFile(SQLiteDatabase db, Context context) {
        try {
            InputStream inputStream = context.getAssets().open("data.sql");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sqlQuery = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();  // Remove leading/trailing whitespace

                // Skip empty lines and comments (assuming comments start with '--')
                if (line.isEmpty() || line.startsWith("--")) {
                    continue;
                }

                // Append the line to the SQL query
                sqlQuery.append(line).append(" ");

                // If the line ends with a semicolon, execute the query
                if (line.endsWith(";")) {
                    String query = sqlQuery.toString().trim();
                    if (!query.isEmpty()) {
                        Log.d("DBHelper", "Executing query: " + query);
                        try {
                            db.execSQL(query);
                        } catch (SQLException e) {
                            Log.e("DBHelper", "Error executing query: " + query, e);
                        }
                        sqlQuery.setLength(0);  // Reset StringBuilder for next query
                    }
                }
            }

            reader.close();
        } catch (IOException | SQLException e) {
            Log.e("DBHelper", "Error executing SQL dump", e);
        }
    }


//    public void executeSQLFromFile(SQLiteDatabase db, String fileName, Context context) {
//        try {
//            // Open the SQL file from assets folder
//            InputStream inputStream = context.getAssets().open("data.sql");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuilder sqlQuery = new StringBuilder();
//            String line;
//
//            // Read the entire SQL file
//            while ((line = reader.readLine()) != null) {
//                sqlQuery.append(line).append("\n");
//            }
//            reader.close();
//
//            // Execute all queries from the file
//            String[] queries = sqlQuery.toString().split(";");
//            for (String query : queries) {
//                if (query.trim().length() > 0) {
//                    db.execSQL(query.trim());
//                }
//            }
//        } catch (IOException e) {
//            Log.e("SQLiteDB", "Error reading SQL file", e);
//        }
//    }


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
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM category", null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0); // Category ID (Index 0)
                String name = cursor.getString(1); // Category Name (Index 1)
                categories.add(new Category(id, name));
            }
            cursor.close();
        }
        return categories;
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


    public List<Quiz> getQuizzesByCategory(int categoryId) {
        List<Quiz> quizList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM quiz WHERE categoryId = ?", new String[]{String.valueOf(categoryId)});

        if (cursor != null) {
            while (cursor.moveToNext()) {
                quizList.add(new Quiz(
                        cursor.getInt(0),   // ID
                        cursor.getString(1), // Question
                        cursor.getString(2), // Option A
                        cursor.getString(3), // Option B
                        cursor.getString(4), // Option C
                        cursor.getString(5), // Option D
                        cursor.getString(6), // Correct Option
                        cursor.getInt(7)     // categoryId
                ));
            }
            cursor.close();
        }
        return quizList;
    }


//    public void saveTotalScore(int score) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("total_score", score);
//
//        // Check if a score entry exists, if not, insert; otherwise, update
//        int updatedRows = db.update("score_table", values, null, null);
//        if (updatedRows == 0) {
//            db.insert("score_table", null, values);
//        }
//    }

}

