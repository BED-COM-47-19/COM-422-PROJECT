@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathematics_form1);

        Button pdfButton = findViewById(R.id.button_pdf);
        Button audioButton = findViewById(R.id.button_audio);
        Button videoButton = findViewById(R.id.button_videos);
        Button testsQuizzesButton = findViewById(R.id.button_tests_quizzes);
        Button backButton = findViewById(R.id.back_button);
        Button chatButton = findViewById(R.id.button_chat);

        pdfButton.setOnClickListener(view -> {
        // Load PDF content
        });

        audioButton.setOnClickListener(view -> {
        // Load Audio content
        });

        videoButton.setOnClickListener(view -> {
        // Load Video content
        });

        testsQuizzesButton.setOnClickListener(view -> {
        // Load Tests and Quizzes content
        });

        backButton.setOnClickListener(view -> {
        // Go back to the previous screen
        onBackPressed();
        });

        chatButton.setOnClickListener(view -> {
        // Open chat functionality
        });
        }
