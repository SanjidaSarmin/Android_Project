-- Create 'category' table
CREATE TABLE IF NOT EXISTS category (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL);

--INSERT Or Ignore INTO category (name) VALUES ('Science');
--INSERT Or Ignore INTO category (name) VALUES ('Math');
--INSERT Or Ignore INTO category (name) VALUES ('Technology');
--INSERT Or Ignore INTO category (name) VALUES ('Sports');
--INSERT Or Ignore INTO category (name) VALUES ('GK');
--INSERT Or Ignore INTO category (name) VALUES ('ART');

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

--INSERT INTO quiz (question, option_a, option_b, option_c, option_d, correct_option, categoryId) VALUES
--('What is the chemical symbol for water?', 'O2', 'H2O', 'CO2', 'NaCl', 'B', 1),
--('What planet is known as the Red Planet?', 'Venus', 'Mars', 'Jupiter', 'Saturn', 'B', 1),
--('What gas do plants absorb from the atmosphere?', 'Oxygen', 'Carbon Dioxide', 'Nitrogen', 'Hydrogen', 'B', 1),
--('What is the largest organ in the human body?', 'Heart', 'Liver', 'Skin', 'Brain', 'C', 1),
--('What force keeps objects on the ground?', 'Magnetism', 'Gravity', 'Friction', 'Inertia', 'B', 1),
--('What is the powerhouse of the cell?', 'Ribosome', 'Mitochondria', 'Nucleus', 'Golgi Apparatus', 'B', 1),
--('What is the hardest natural substance on Earth?', 'Gold', 'Iron', 'Diamond', 'Quartz', 'C', 1),
--('Which element has the atomic number 1?', 'Hydrogen', 'Oxygen', 'Nitrogen', 'Helium', 'A', 1),
--('What is the process by which plants make their food?', 'Respiration', 'Photosynthesis', 'Fermentation', 'Digestion', 'B', 1),
--('Who developed the theory of relativity?', 'Isaac Newton', 'Albert Einstein', 'Galileo Galilei', 'Nikola Tesla', 'B', 1),
--('What is the most common element in the Earths crust?', 'Iron', 'Oxygen', 'Silicon', 'Aluminum', 'B', 1),
--('What type of animal is the largest living creature on Earth?', 'Shark', 'Elephant', 'Blue Whale', 'Giraffe', 'C', 1),
--('What is the chemical formula for methane?', 'CH4', 'C2H6', 'C3H8', 'CO2', 'A', 1),
--('Which part of the cell contains the genetic material?', 'Mitochondria', 'Nucleus', 'Ribosome', 'Golgi apparatus', 'B', 1),
--('What is the closest star to Earth?', 'Sirius', 'Proxima Centauri', 'Sun', 'Alpha Centauri', 'C', 1),
--('What is the atomic number of carbon?', '6', '12', '14', '8', 'A', 1),
--('What is the chemical element symbol for gold?', 'Ag', 'Au', 'Pb', 'Fe', 'B', 1),
--('What is the name of the largest moon of Saturn?', 'Titan', 'Europa', 'Ganymede', 'Io', 'A', 1),
--('What is the process by which plants lose water through their leaves?', 'Transpiration', 'Respiration', 'Evaporation', 'Photosynthesis', 'A', 1),
--('Which planet is known as the "Morning Star"?', 'Venus', 'Mars', 'Jupiter', 'Saturn', 'A', 1),
--('How many bones are in the adult human body?', '206', '210', '220', '205', 'A', 1),
--('What is the chemical symbol for Sodium?', 'S', 'Na', 'So', 'Sn', 'B', 1),
--('Which scientist developed the theory of evolution?', 'Isaac Newton', 'Charles Darwin', 'Albert Einstein', 'Nikola Tesla', 'B', 1),
--('What is the center of an atom called?', 'Electron', 'Proton', 'Neutron', 'Nucleus', 'D', 1),
--('Which gas do humans exhale during respiration?', 'Oxygen', 'Carbon Dioxide', 'Nitrogen', 'Hydrogen', 'B', 1);

--INSERT INTO quiz (question, option_a, option_b, option_c, option_d, correct_option, categoryId) VALUES
--('What is 12 ÷ 4?', '2', '3', '4', '6', 'B', 2),
--('What is the square root of 49?', '5', '6', '7', '8', 'C', 2),
--('What is 15 × 2?', '25', '20', '35', '30', 'D', 2),
--('What is 100 - 45?', '45', '55', '65', '75', 'B', 2),
--('What is 9²?', '72', '81', '99', '90', 'B', 2),
--('What is 50% of 200?', '50', '100', '200', '150', 'B', 2),
--('What is 7 × 8?', '48', '54', '56', '64', 'C', 2),
--('What is 144 ÷ 12?', '10', '12', '14', '16', 'B', 2),
--('What is the value of π (Pi) rounded to two decimal places?', '3.12', '3.14', '3.16', '3.18', 'B', 2),
--('If a triangle has angles of 60° and 60°, what is the third angle?', '30°', '45°', '60°', '90°', 'A', 2),
--('What is 25 × 4?', '100', '110', '120', '90', 'A', 2),
--('What is the value of π (Pi) to two decimal places?', '3.12', '3.14', '3.16', '3.18', 'B', 2),
--('What is the value of 3³?', '6', '9', '27', '81', 'C', 2),
--('What is the next number in the sequence 2, 4, 8, 16, ___?', '24', '32', '36', '48', 'B', 2),
--('What is the square root of 81?', '7', '8', '9', '10', 'C', 2),
--('What is 12 × 3?', '30', '36', '40', '45', 'B', 2),
--('What is 100 ÷ 25?', '3', '4', '5', '6', 'C', 2),
--('What is the perimeter of a rectangle with a length of 5 and width of 3?', '8', '15', '18', '16', 'D', 2),
--('What is the value of 15% of 200?', '25', '30', '35', '40', 'B', 2),
--('What is the sum of the angles of a triangle?', '90°', '180°', '360°', '270°', 'B', 2),
--('What is 2⁴?', '8', '16', '32', '64', 'B', 2),
--('What is 60 ÷ 5?', '8', '9', '10', '11', 'C', 2),
--('What is the value of 10²?', '100', '200', '1000', '10', 'A', 2),
--('What is the difference between 100 and 45?', '50', '55', '60', '65', 'B', 2),
--('What is the area of a triangle with a base of 6 and height of 4?', '12', '24', '20', '18', 'A', 2);

--INSERT INTO quiz (question, option_a, option_b, option_c, option_d, correct_option, categoryId) VALUES
--('Who is known as the father of computers?', 'Alan Turing', 'Charles Babbage', 'Bill Gates', 'Steve Jobs', 'B', 3),
--('What does CPU stand for?', 'Central Process Unit', 'Central Processing Unit', 'Computer Personal Unit', 'Central Peripheral Unit', 'B', 3),
--('Which company created the Android operating system?', 'Apple', 'Google', 'Microsoft', 'Samsung', 'B', 3),
--('What does HTTP stand for?', 'Hyper Text Transfer Protocol', 'High Tech Transmission Protocol', 'Hyperlink Transfer Process', 'Hyper Text Transfer Process', 'A', 3),
--('Which programming language is primarily used for web development?', 'Python', 'Java', 'JavaScript', 'C++', 'C', 3),
--('What is the most commonly used search engine?', 'Yahoo', 'Bing', 'Google', 'DuckDuckGo', 'C', 3),
--('Which of the following is an open-source operating system?', 'Windows', 'macOS', 'Linux', 'iOS', 'C', 3),
--('What does AI stand for in technology?', 'Artificial Integration', 'Automated Intelligence', 'Artificial Intelligence', 'Advanced Internet', 'C', 3),
--('Which company developed the first iPhone?', 'Samsung', 'Google', 'Microsoft', 'Apple', 'D', 3),
--('What is the full form of USB?', 'Universal System Bus', 'Universal Serial Bus', 'Unified Serial Bus', 'Universal Software Bus', 'B', 3),
--('What does the abbreviation "RAM" stand for in computers?', 'Random Access Memory', 'Read Access Memory', 'Read Average Memory', 'Random Average Memory', 'A', 3),
--('Who is the co-founder of Microsoft?', 'Bill Gates', 'Steve Jobs', 'Larry Page', 'Mark Zuckerberg', 'A', 3),
--('What does the abbreviation "URL" stand for?', 'Uniform Resource Locator', 'Uniform Response Locator', 'Uniform Retrieval Locator', 'Universal Resource Locator', 'A', 3),
--('What does "HTTP" stand for?', 'HyperText Transfer Protocol', 'HyperText Transaction Protocol', 'HyperText Transport Protocol', 'Hyper Transfer Text Protocol', 'A', 3),
--('Which company developed the iPhone?', 'Google', 'Apple', 'Microsoft', 'Samsung', 'B', 3),
--('What does VPN stand for?', 'Virtual Private Network', 'Virtual Public Network', 'Variable Private Network', 'Virtual Protected Network', 'A', 3),
--('Which technology company created the Android operating system?', 'Google', 'Microsoft', 'Apple', 'Samsung', 'A', 3),
--('What is the primary function of a motherboard in a computer?', 'Process data', 'Store data', 'Connect all components', 'Display data', 'C', 3),
--('What is the most popular social media platform in the world?', 'Facebook', 'Instagram', 'Twitter', 'TikTok', 'A', 3),
--('Which programming language is known as the language of the web?', 'Python', 'Java', 'HTML', 'C++', 'C', 3),
--('What does "Wi-Fi" stand for?', 'Wireless Fidelity', 'Wide Fidelity', 'Wireless Free', 'Wi-Fi does not stand for anything', 'A', 3),
--('What does SSD stand for in terms of computer storage?', 'Solid State Drive', 'System Storage Device', 'Secure Storage Drive', 'Super Storage Disk', 'A', 3),
--('Which company created the first personal computer?', 'Apple', 'IBM', 'Microsoft', 'Compaq', 'B', 3),
--('What is the main function of a router?', 'Connect devices to a network', 'Store data', 'Process data', 'Display data', 'A', 3),
--('What does IoT stand for?', 'Internet of Technology', 'Internet of Things', 'Integrated Online Technology', 'Internet Online Technology', 'B', 3);


--INSERT INTO quiz (question, option_a, option_b, option_c, option_d, correct_option, categoryId) VALUES
--('How many players are there in a soccer team?', '9', '10', '11', '12', 'C', 4),
--('Which country won the FIFA World Cup in 2018?', 'Germany', 'Brazil', 'France', 'Argentina', 'C', 4),
--('What is the national sport of Japan?', 'Sumo Wrestling', 'Baseball', 'Judo', 'Karate', 'A', 4),
--('Which sport uses a shuttlecock?', 'Tennis', 'Badminton', 'Squash', 'Volleyball', 'B', 4),
--('How many points is a touchdown worth in American football?', '3', '6', '7', '5', 'B', 4),
--('Which athlete holds the record for the fastest 100m sprint?', 'Usain Bolt', 'Carl Lewis', 'Michael Johnson', 'Tyson Gay', 'A', 4),
--('In which sport would you perform a slam dunk?', 'Baseball', 'Football', 'Basketball', 'Tennis', 'C', 4),
--('Which Grand Slam tennis tournament is played on a grass court?', 'US Open', 'French Open', 'Australian Open', 'Wimbledon', 'D', 4),
--('Which country is famous for cricket and has won multiple ICC Cricket World Cups?', 'England', 'India', 'South Africa', 'New Zealand', 'B', 4),
--('What is the maximum score a player can achieve with a single throw in darts?', '20', '50', '60', '100', 'C', 4),
--('How long is a marathon?', '26.2 miles', '24.5 miles', '30 miles', '28 miles', 'A', 4),
--('In which sport would you find the term "hat trick"?', 'Baseball', 'Soccer', 'Tennis', 'Cricket', 'B', 4),
--('Who holds the record for the most Olympic gold medals?', 'Michael Phelps', 'Usain Bolt', 'Carl Lewis', 'Mark Spitz', 'A', 4),
--('Which country won the 2014 FIFA World Cup?', 'Brazil', 'Germany', 'Argentina', 'France', 'B', 4),
--('How many players are there on a standard rugby team?', '13', '14', '15', '16', 'C', 4),
--('Which NBA team does LeBron James play for?', 'Los Angeles Lakers', 'Miami Heat', 'Cleveland Cavaliers', 'Golden State Warriors', 'A', 4),
--('What is the maximum break in snooker?', '147', '160', '170', '180', 'A', 4),
--('Which country invented the sport of baseball?', 'England', 'United States', 'Australia', 'Canada', 'B', 4),
--('In which sport is the Davis Cup awarded?', 'Tennis', 'Badminton', 'Football', 'Golf', 'A', 4),
--('What sport does Cristiano Ronaldo play?', 'Basketball', 'Tennis', 'Football (Soccer)', 'Baseball', 'C', 4),
--('Which country won the Rugby World Cup in 2019?', 'New Zealand', 'South Africa', 'England', 'Australia', 'B', 4),
--('Who holds the record for the most goals in the history of the World Cup?', 'Pele', 'Ronaldo Nazário', 'Marta', 'Miroslav Klose', 'D', 4),
--('What is the maximum score you can get in 10-pin bowling?', '300', '200', '100', '150', 'A', 4),
--('In which sport is the Ryder Cup contested?', 'Golf', 'Tennis', 'Football', 'Basketball', 'A', 4),
--('What is the national sport of Canada?', 'Hockey', 'Baseball', 'Football', 'Cricket', 'A', 4);


--INSERT INTO quiz (question, option_a, option_b, option_c, option_d, correct_option, categoryId) VALUES
--('What is the capital of Japan?', 'Beijing', 'Seoul', 'Tokyo', 'Bangkok', 'C', 5),
--('Who wrote the play "Romeo and Juliet"?', 'William Wordsworth', 'William Shakespeare', 'Mark Twain', 'Jane Austen', 'B', 5),
--('Which is the largest ocean on Earth?', 'Atlantic Ocean', 'Indian Ocean', 'Arctic Ocean', 'Pacific Ocean', 'D', 5),
--('Who was the first person to step on the moon?', 'Neil Armstrong', 'Buzz Aldrin', 'Yuri Gagarin', 'Michael Collins', 'A', 5),
--('Which is the longest river in the world?', 'Amazon River', 'Nile River', 'Yangtze River', 'Mississippi River', 'B', 5),
--('What is the national animal of India?', 'Elephant', 'Bengal Tiger', 'Lion', 'Peacock', 'B', 5),
--('How many continents are there on Earth?', '5', '6', '7', '8', 'C', 5),
--('What is the hardest natural substance on Earth?', 'Iron', 'Gold', 'Diamond', 'Platinum', 'C', 5),
--('Who invented the telephone?', 'Alexander Graham Bell', 'Thomas Edison', 'Nikola Tesla', 'Albert Einstein', 'A', 5),
--('Which planet is known as the "Morning Star" or "Evening Star"?', 'Mars', 'Jupiter', 'Venus', 'Saturn', 'C', 5),
--('Which country is known as the Land of the Rising Sun?', 'China', 'Japan', 'South Korea', 'India', 'B', 5),
--('What is the capital of Canada?', 'Ottawa', 'Toronto', 'Vancouver', 'Montreal', 'A', 5),
--('Who invented the telephone?', 'Nikola Tesla', 'Alexander Graham Bell', 'Thomas Edison', 'Albert Einstein', 'B', 5),
--('What is the largest continent by area?', 'Africa', 'Asia', 'Europe', 'North America', 'B', 5),
--('In which year did the Titanic sink?', '1910', '1912', '1905', '1915', 'B', 5),
--('Which planet is closest to the Sun?', 'Earth', 'Mercury', 'Venus', 'Mars', 'B', 5),
--('Who was the first president of the United States?', 'George Washington', 'Abraham Lincoln', 'Thomas Jefferson', 'John Adams', 'A', 5),
--('What is the main ingredient in guacamole?', 'Tomato', 'Avocado', 'Onion', 'Lemon', 'B', 5),
--('Who was the first woman to win a Nobel Prize?', 'Marie Curie', 'Helen Keller', 'Dorothy Hodgkin', 'Rosalind Franklin', 'A', 5),
--('Which element is represented by the symbol "O" on the periodic table?', 'Osmium', 'Oxygen', 'Ozone', 'Oxygene', 'B', 5),
--('What is the smallest country in the world?', 'Monaco', 'Vatican City', 'Liechtenstein', 'Malta', 'B', 5),
--('Which famous scientist developed the theory of relativity?', 'Isaac Newton', 'Nikola Tesla', 'Albert Einstein', 'Galileo Galilei', 'C', 5),
--('Who wrote the novel "1984"?', 'Aldous Huxley', 'J.K. Rowling', 'George Orwell', 'Mark Twain', 'C', 5),
--('What is the longest river in the world?', 'Amazon River', 'Nile River', 'Yangtze River', 'Mississippi River', 'B', 5),
--('Which country has the largest population in the world?', 'India', 'United States', 'China', 'Brazil', 'C', 5);


--INSERT INTO quiz (question, option_a, option_b, option_c, option_d, correct_option, categoryId) VALUES
--('Who painted the Mona Lisa?', 'Vincent van Gogh', 'Pablo Picasso', 'Leonardo da Vinci', 'Claude Monet', 'C', 6),
--('Which famous artist is known for cutting off his own ear?', 'Leonardo da Vinci', 'Vincent van Gogh', 'Michelangelo', 'Salvador Dalí', 'B', 6),
--('The statue of David was created by which artist?', 'Raphael', 'Donatello', 'Michelangelo', 'Caravaggio', 'C', 6),
--('Which art movement is Salvador Dalí associated with?', 'Cubism', 'Surrealism', 'Impressionism', 'Baroque', 'B', 6),
--('What are the primary colors in painting?', 'Red, Green, Blue', 'Red, Yellow, Blue', 'Red, Blue, Black', 'Yellow, Green, Purple', 'B', 6),
--('Which country is famous for the Renaissance art movement?', 'France', 'Germany', 'Italy', 'Spain', 'C', 6),
--('Which artist is famous for his "Campbell\'s Soup Cans" artwork?', 'Andy Warhol', 'Jackson Pollock', 'Claude Monet', 'Henri Matisse', 'A', 6),
--('What is the term for a painting made on wet plaster?', 'Acrylic', 'Fresco', 'Mosaic', 'Pastel', 'B', 6),
--('Which of the following is a famous sculpture by Auguste Rodin?', 'The Thinker', 'The Kiss', 'Pieta', 'David', 'A', 6),
--('What is the technique of using tiny dots to create an image called?', 'Cubism', 'Pointillism', 'Surrealism', 'Realism', 'B', 6),
--('Who painted the famous artwork "The Starry Night"?', 'Pablo Picasso', 'Vincent van Gogh', 'Claude Monet', 'Leonardo da Vinci', 'B', 6),
--('What is the name of the painting of a woman with a mysterious smile?', 'The Kiss', 'The Starry Night', 'Mona Lisa', 'The Scream', 'C', 6),
--('Which artist is famous for his abstract works and use of primary colors?', 'Jackson Pollock', 'Pablo Picasso', 'Wassily Kandinsky', 'Piet Mondrian', 'D', 6),
--('Which style of art is Salvador Dalí known for?', 'Cubism', 'Surrealism', 'Realism', 'Impressionism', 'B', 6),
--('Who sculpted "The Thinker"?', 'Auguste Rodin', 'Michelangelo', 'Donatello', 'Leonardo da Vinci', 'A', 6),
--('What does the term "Impressionism" refer to in art?', 'Art based on realism', 'Art based on light and color', 'Art that depicts abstract concepts', 'Art that uses geometric shapes', 'B', 6),
--('Which artist is known for his work "Guernica"?', 'Vincent van Gogh', 'Pablo Picasso', 'Claude Monet', 'Salvador Dalí', 'B', 6),
--('What type of paint is typically used in oil painting?', 'Water-based paint', 'Oil-based paint', 'Acrylic paint', 'Ink', 'B', 6),
--('Which artist is associated with the Pop Art movement?', 'Andy Warhol', 'Claude Monet', 'Jackson Pollock', 'Pablo Picasso', 'A', 6),
--('Which famous painting was created by Leonardo da Vinci?', 'The Persistence of Memory', 'The Birth of Venus', 'The Scream', 'Mona Lisa', 'D', 6),
--('Who is the famous artist behind the "Water Lilies" series?', 'Vincent van Gogh', 'Claude Monet', 'Pablo Picasso', 'Andy Warhol', 'B', 6),
--('What is the artistic technique of using small, distinct dots of color to create an image?', 'Pointillism', 'Cubism', 'Surrealism', 'Impressionism', 'A', 6),
--('Who painted the "Last Supper"?', 'Michelangelo', 'Vincent van Gogh', 'Leonardo da Vinci', 'Raphael', 'C', 6),
--('Which art movement is associated with geometric shapes and abstract forms?', 'Expressionism', 'Cubism', 'Surrealism', 'Dadaism', 'B', 6),
--('Which artist painted "The Girl with a Pearl Earring"?', 'Johannes Vermeer', 'Rembrandt', 'Claude Monet', 'Pablo Picasso', 'A', 6);





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