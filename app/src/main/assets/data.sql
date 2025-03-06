-- Create 'users' table
CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    age INTEGER NOT NULL
);

-- Insert data into 'users' table
INSERT INTO users (name, age) VALUES ('John Doe', 28);
INSERT INTO users (name, age) VALUES ('Jane Smith', 34);
INSERT INTO users (name, age) VALUES ('Michael Brown', 25);
INSERT INTO users (name, age) VALUES ('Sara Wilson', 29);
INSERT INTO users (name, age) VALUES ('David Lee', 40);

-- Create 'orders' table
CREATE TABLE IF NOT EXISTS orders (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER,
    product_name TEXT NOT NULL,
    quantity INTEGER NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id)
);

-- Insert data into 'orders' table
INSERT INTO orders (user_id, product_name, quantity) VALUES (1, 'Laptop', 1);
INSERT INTO orders (user_id, product_name, quantity) VALUES (2, 'Smartphone', 2);
INSERT INTO orders (user_id, product_name, quantity) VALUES (3, 'Tablet', 1);
INSERT INTO orders (user_id, product_name, quantity) VALUES (4, 'Headphones', 3);
INSERT INTO orders (user_id, product_name, quantity) VALUES (5, 'Smartwatch', 1);




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
--    public void executeSQLDump(SQLiteDatabase db, Context context) {
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