-- Create 'category' table
CREATE TABLE IF NOT EXISTS category (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL);

INSERT Or Ignore INTO category (name) VALUES ('Science'); --1
INSERT INTO category (name) VALUES ('Math'); --2
INSERT INTO category (name) VALUES ('Technology'); --3
INSERT INTO category (name) VALUES ('Sports'); --4
INSERT INTO category (name) VALUES ('GK'); --5
INSERT INTO category (name) VALUES ('ART'); --6

-- Create 'quiz' table
CREATE TABLE IF NOT EXISTS quiz (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    question TEXT NOT NULL,
    option_a TEXT NOT NULL,
    option_b TEXT NOT NULL,
    option_c TEXT NOT NULL,
    option_d TEXT NOT NULL,
    correct_option TEXT NOT NULL,
    categoryId INTEGER NOT NULL,
    FOREIGN KEY (categoryId) REFERENCES category(id) ON DELETE CASCADE
);

-- Insert sample quiz data
INSERT INTO quiz (question, option_a, option_b, option_c, option_d, correct_option, categoryId)
VALUES ('What is the capital of France?', 'Berlin', 'Madrid', 'Paris', 'Rome', 'C', 5);

INSERT INTO quiz (question, option_a, option_b, option_c, option_d, correct_option, categoryId)
VALUES ('What is 5 + 3?', '5', '8', '9', '7', 'B', 2);


--
--private static final String DATABASE_NAME = "MyDatabase.db";
--    private static final int DATABASE_VERSION = 1;
--
--    public DBHelper(Context context) {
--        super(context, DATABASE_NAME, null, DATABASE_VERSION);
--    }
--
--    @Override
--    public void onCreate(SQLiteDatabase db) {
--        // Database creation is handled by executeSQLDump()
--    }
--
--    @Override
--    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
--        // Handle database upgrades if needed
--    }
--
--    // Method to execute the SQL dump from the 'assets/data.sql' file
--    public void executeSQLFromFile(SQLiteDatabase db, Context context) {
--        try {
--            InputStream inputStream = context.getAssets().open("data.sql");
--            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
--            StringBuilder sqlQuery = new StringBuilder();
--            String line;
--
--            while ((line = reader.readLine()) != null) {
--                line = line.trim();  // Remove leading/trailing whitespace
--
--                // Skip empty lines and comments (assuming comments start with '--')
--                if (line.isEmpty() || line.startsWith("--")) {
--                    continue;
--                }
--
--                // Append the line to the SQL query
--                sqlQuery.append(line).append(" ");
--
--                // If the line ends with a semicolon, execute the query
--                if (line.endsWith(";")) {
--                    String query = sqlQuery.toString().trim();
--                    if (!query.isEmpty()) {
--                        Log.d("DBHelper", "Executing query: " + query);
--                        try {
--                            db.execSQL(query);
--                        } catch (SQLException e) {
--                            Log.e("DBHelper", "Error executing query: " + query, e);
--                        }
--                        sqlQuery.setLength(0);  // Reset StringBuilder for next query
--                    }
--                }
--            }
--
--            reader.close();
--        } catch (IOException | SQLException e) {
--            Log.e("DBHelper", "Error executing SQL dump", e);
--        }
--    }
--}

--public class TestActivity extends AppCompatActivity {
--    private DBHelper dbHelper;
--
--    Button btn;
--    TextView tv;
--    @Override
--    protected void onCreate(Bundle savedInstanceState) {
--        super.onCreate(savedInstanceState);
--        EdgeToEdge.enable(this);
--        setContentView(R.layout.activity_test);
--        dbHelper = new DBHelper(this);
--
--        // Get writable database and execute the SQL dump
--        SQLiteDatabase db = dbHelper.getWritableDatabase();
--        dbHelper.executeSQLDump(db, this);
--
--        btn = findViewById(R.id.button);
--        tv = findViewById(R.id.textView2);
--
--
--//        btn.setOnClickListener(new );
--
--        queryData();
--    }
--
--    private void queryData() {
--        SQLiteDatabase db = dbHelper.getReadableDatabase();
--
--        // Query all columns from the 'users' table
--        Cursor cursor = db.query("users", new String[]{"id", "name", "age"},
--                null, null, null, null, null);
--
--        // Loop through the results and log them
--        while (cursor.moveToNext()) {
--            int id = cursor.getInt(0);
--            String name = cursor.getString(1);
--            int age = cursor.getInt(2);
--            Log.d("DB", "User: " + id + ", " + name + ", Age: " + age);
--        }
--        cursor.close();
--    }
--
--    @Override
--    protected void onDestroy() {
--        super.onDestroy();
--        dbHelper.close(); // Always close the DBHelper when done
--    }
--}