import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class Map extends JFrame {

    private boolean[][] maze;
    private int width;
    private int height;
    private JButton shuffleButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton addDroidRedButton;
    private JButton shuffleRedDroidButton;
    private JButton shuffleGreenDroidButton;
    
    private JButton showRedDroidViewButton;
    private JButton showGreenDroidViewButton;
    private int greenDroidRow;
    private int greenDroidCol;
    private int redDroidRow;
    private int redDroidCol;
    private Timer greenDroidTimer;
    private Timer redDroidTimer;
    private boolean isMoving;

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        maze = new boolean[height][width];

        initializeMaze();
        generateMaze();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Map");
        setSize(500, 500);
        setLayout(new BorderLayout());

        // Tombol untuk mengacak maze
        shuffleButton = new JButton("Acak Map");
        shuffleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateMaze();
                repaint();
            }
        });


        // Tombol untuk memulai pergerakan droid
        startButton = new JButton("Mulai");
        startButton.addActionListener(new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                isMoving = true;
                moveDroids();            
            }
        });

        // Tombol untuk menghentikan pergerakan droid
        stopButton = new JButton("Berhenti");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isMoving = false;
            }
        });

        // Tombol untuk menambah droid merah
        addDroidRedButton = new JButton("Tambah Droid Merah");
        addDroidRedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        // Tombol untuk mengacak posisi awal Droid Merah
        shuffleRedDroidButton = new JButton("Acak Posisi Droid Merah");
        shuffleRedDroidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                repaint();
    }
});

        // Tombol untuk mengacak posisi awal Droid Hijau
        shuffleGreenDroidButton = new JButton("Acak Posisi Droid Hijau");
        shuffleGreenDroidButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                repaint();
    }
});


        // Tombol untuk melihat pandangan Droid Merah
        showRedDroidViewButton = new JButton("Lihat Pandangan Droid Merah");
        showRedDroidViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                repaint();
    }
});

        // Tombol untuk melihat pandangan Droid Hijau
        showGreenDroidViewButton = new JButton("Lihat Pandangan Droid Hijau");
        showGreenDroidViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
            repaint();
    }
});
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        
        buttonPanel.add(shuffleButton);
        shuffleButton.setPreferredSize(new Dimension(200, 40)); // Mengatur dimensi tombol
        shuffleButton.setFont(new Font("Komikula", Font.PLAIN, 18)); // Mengatur ukuran font
        shuffleButton.setBackground(Color.PINK); // Menambahkan warna latar belakang merah pada tombol
        shuffleButton.setForeground(Color.WHITE); // Menambahkan warna teks putih pada tombol
        buttonPanel.add(shuffleButton);

        buttonPanel.add(startButton);
        shuffleButton.setPreferredSize(new Dimension(200, 40)); // Mengatur dimensi tombol
        startButton.setFont(new Font("Komikula", Font.PLAIN, 18)); // Mengatur ukuran font
        startButton.setBackground(Color.PINK); // Menambahkan warna latar belakang merah pada tombol
        startButton.setForeground(Color.WHITE); // Menambahkan warna teks putih pada tombol
        buttonPanel.add(startButton);

        buttonPanel.add(stopButton);
        shuffleButton.setPreferredSize(new Dimension(200, 40)); // Mengatur dimensi tombol
        stopButton.setFont(new Font("Komikula", Font.PLAIN, 18)); // Mengatur ukuran font
        stopButton.setBackground(Color.PINK); // Menambahkan warna latar belakang merah pada tombol
        stopButton.setForeground(Color.WHITE); // Menambahkan warna teks putih pada tombol
        buttonPanel.add(stopButton);

        buttonPanel.add(addDroidRedButton);
        shuffleButton.setPreferredSize(new Dimension(200, 40)); // Mengatur dimensi tombol
        addDroidRedButton.setFont(new Font("Komikula", Font.PLAIN, 18)); // Mengatur ukuran font
        addDroidRedButton.setBackground(Color.PINK); // Menambahkan warna latar belakang merah pada tombol
        addDroidRedButton.setForeground(Color.WHITE); // Menambahkan warna teks putih pada tombol
        buttonPanel.add(addDroidRedButton);

        buttonPanel.add(shuffleRedDroidButton);
        shuffleButton.setPreferredSize(new Dimension(200, 40)); // Mengatur dimensi tombol
        shuffleRedDroidButton.setFont(new Font("Komikula", Font.PLAIN, 18)); // Mengatur ukuran font
        shuffleRedDroidButton.setBackground(Color.PINK); // Menambahkan warna latar belakang merah pada tombol
        shuffleRedDroidButton.setForeground(Color.WHITE); // Menambahkan warna teks putih pada tombol
        buttonPanel.add(shuffleRedDroidButton);

        buttonPanel.add(shuffleGreenDroidButton);
        shuffleButton.setPreferredSize(new Dimension(200, 40)); // Mengatur dimensi tombol
        shuffleGreenDroidButton.setFont(new Font("Komikula", Font.PLAIN, 18)); // Mengatur ukuran font
        shuffleGreenDroidButton.setBackground(Color.PINK); // Menambahkan warna latar belakang merah pada tombol
        shuffleGreenDroidButton.setForeground(Color.WHITE); // Menambahkan warna teks putih pada tombol
        buttonPanel.add(shuffleGreenDroidButton);

        buttonPanel.add(showRedDroidViewButton);
        shuffleButton.setPreferredSize(new Dimension(200, 40)); // Mengatur dimensi tombol
        showRedDroidViewButton.setFont(new Font("Komikula", Font.PLAIN, 18)); // Mengatur ukuran font
        showRedDroidViewButton.setBackground(Color.PINK); // Menambahkan warna latar belakang merah pada tombol
        showRedDroidViewButton.setForeground(Color.WHITE); // Menambahkan warna teks putih pada tombol
        buttonPanel.add(showRedDroidViewButton);

        buttonPanel.add(showGreenDroidViewButton);
        shuffleButton.setPreferredSize(new Dimension(200, 40)); // Mengatur dimensi tombol
        showGreenDroidViewButton.setFont(new Font("Komikula", Font.PLAIN, 18)); // Mengatur ukuran font
        showGreenDroidViewButton.setBackground(Color.PINK); // Menambahkan warna latar belakang merah pada tombol
        showGreenDroidViewButton.setForeground(Color.WHITE); // Menambahkan warna teks putih pada tombol
        buttonPanel.add(showGreenDroidViewButton);
        
        buttonPanel.add(shuffleButton);
        buttonPanel.add(Box.createVerticalStrut(30)); // Tambahkan ruang vertikal
        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(30)); // Tambahkan ruang vertikal
        buttonPanel.add(stopButton);
        buttonPanel.add(Box.createVerticalStrut(30)); // Tambahkan ruang vertikal
        buttonPanel.add(addDroidRedButton);
        buttonPanel.add(Box.createVerticalStrut(30)); // Tambahkan ruang vertikal
        buttonPanel.add(shuffleRedDroidButton);
        buttonPanel.add(Box.createVerticalStrut(30)); // Tambahkan ruang vertikal
        buttonPanel.add(shuffleGreenDroidButton);
        buttonPanel.add(Box.createVerticalStrut(30)); // Tambahkan ruang vertikal
        buttonPanel.add(showRedDroidViewButton);
        buttonPanel.add(Box.createVerticalStrut(30)); // Tambahkan ruang vertikal
        buttonPanel.add(showGreenDroidViewButton);
        
        add(buttonPanel, BorderLayout.EAST);


        setVisible(true);



        greenDroidTimer = new Timer(1000, new ActionListener()  {

         @Override
        public void actionPerformed(ActionEvent e) {
            moveGreenDroid();
            }
        });
        greenDroidTimer.start();

        redDroidTimer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveRedDroid();
            }
        });
        redDroidTimer.start();
    }

    public void initializeMaze() {
        // Inisialisasi maze dengan dinding (true)
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                maze[row][col] = true;
            }
        }
    }

    public void generateMaze() {
        initializeMaze();

        Random random = new Random();
        int startRow = random.nextInt(height);
        int startCol = random.nextInt(width);

        generatePath(startRow, startCol);

        // Mengacak ulang posisi droid hijau
        do {
            greenDroidRow = random.nextInt(height);
            greenDroidCol = random.nextInt(width);
        } while (maze[greenDroidRow][greenDroidCol]);

        // Mengacak ulang posisi droid merah
        do {
            redDroidRow = random.nextInt(height);
            redDroidCol = random.nextInt(width);
        } while (maze[redDroidRow][redDroidCol]);
    }

        private void generatePath(int row, int col) {
        maze[row][col] = false;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Random random = new Random();
        int[] randomDirections = random.ints(4, 0, 4).toArray();

        for (int i = 0; i < 4; i++) {
            int[] direction = directions[randomDirections[i]];
            int newRow = row + direction[0] * 2;
            int newCol = col + direction[1] * 2;

            if (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width && maze[newRow][newCol]) {
                maze[row + direction[0]][col + direction[1]] = false;
                generatePath(newRow, newCol);
            }
        }
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int tileSize = Math.min(600 / width, 600 / height); // Ukuran tiap tile dalam piksel
        int offsetX = 50 + (800 - tileSize * width) / 2; // Posisi awal x peta dalam piksel
        int offsetY = 100 + (500 - tileSize * height) / 2; // Posisi awal y peta dalam piksel

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                int tileX = offsetX + col * tileSize;
                int tileY = offsetY + row * tileSize;

                // Menggambar jalan (warna putih)
                if (!maze[row][col]) {
                    g.setColor(Color.WHITE);
                    g.fillRect(tileX, tileY, tileSize, tileSize);
                } else {
                    // Menggambar dinding (warna hitam)
                    g.setColor(Color.PINK);
                    g.fillRect(tileX, tileY, tileSize, tileSize);
                }

                // Menggambar droid hijau
                if (row == greenDroidRow && col == greenDroidCol) {
                    int droidSize = tileSize / 2;
                    int droidX = tileX + (tileSize - droidSize) / 2;
                    int droidY = tileY + (tileSize - droidSize) / 2;
                    g.setColor(Color.GREEN);
                    g.fillOval(droidX, droidY, droidSize, droidSize);
                }

                // Menggambar droid merah
                if (row == redDroidRow && col == redDroidCol) {
                    int droidSize = tileSize / 2;
                    int droidX = tileX + (tileSize - droidSize) / 2;
                    int droidY = tileY + (tileSize - droidSize) / 2;
                    g.setColor(Color.RED);
                    g.fillOval(droidX, droidY, droidSize, droidSize);
                }

                g.setColor(Color.BLACK);
                g.drawRect(tileX, tileY, tileSize, tileSize);
            }
        }
    }

private void moveGreenDroid() {
    Random random = new Random();
    List<int[]> possibleMoves = new ArrayList<>();

    // Cek kemungkinan langkah hijau ke atas
    if (greenDroidRow - 1 >= 0 && !maze[greenDroidRow - 1][greenDroidCol]) {
        possibleMoves.add(new int[]{greenDroidRow - 1, greenDroidCol});
    }

    // Cek kemungkinan langkah hijau ke bawah
    if (greenDroidRow + 1 < height && !maze[greenDroidRow + 1][greenDroidCol]) {
        possibleMoves.add(new int[]{greenDroidRow + 1, greenDroidCol});
    }

    // Cek kemungkinan langkah hijau ke kiri
    if (greenDroidCol - 1 >= 0 && !maze[greenDroidRow][greenDroidCol - 1]) {
        possibleMoves.add(new int[]{greenDroidRow, greenDroidCol - 1});
    }

    // Cek kemungkinan langkah hijau ke kanan
    if (greenDroidCol + 1 < width && !maze[greenDroidRow][greenDroidCol + 1]) {
        possibleMoves.add(new int[]{greenDroidRow, greenDroidCol + 1});
    }

    if (possibleMoves.size() > 0) {
        int randomIndex = random.nextInt(possibleMoves.size());
        int[] move = possibleMoves.get(randomIndex);
        greenDroidRow = move[0];
        greenDroidCol = move[1];
    }

    repaint();
}

private void moveRedDroid() {
    if (redDroidRow == greenDroidRow && redDroidCol == greenDroidCol) {
        redDroidTimer.stop();
        JOptionPane.showMessageDialog(this, "Red Droid caught Green Droid!");
        return;
    }

    boolean[][] visited = new boolean[height][width];
    int[][] distance = new int[height][width];
    int[][] parentRow = new int[height][width];
    int[][] parentCol = new int[height][width];

    recursiveBFS(redDroidRow, redDroidCol, visited, distance, parentRow, parentCol);

    // Mencari langkah terpendek ke posisi droid hijau
    int currentRow = greenDroidRow;
    int currentCol = greenDroidCol;
    while (parentRow[currentRow][currentCol] != redDroidRow || parentCol[currentRow][currentCol] != redDroidCol) {
        int nextRow = parentRow[currentRow][currentCol];
        int nextCol = parentCol[currentRow][currentCol];
        currentRow = nextRow;
        currentCol = nextCol;
    }

    redDroidRow = currentRow;
    redDroidCol = currentCol;

    repaint();
}

private void recursiveBFS(int row, int col, boolean[][] visited, int[][] distance, int[][] parentRow, int[][] parentCol) {
        visited[row][col] = true;

    // Periksa tetangga atas
    if (row - 1 >= 0 && !maze[row - 1][col] && !visited[row - 1][col]) {
        visited[row - 1][col] = true;
        distance[row - 1][col] = distance[row][col] + 1;
        parentRow[row - 1][col] = row;
        parentCol[row - 1][col] = col;
        recursiveBFS(row - 1, col, visited, distance, parentRow, parentCol);
    }

    // Periksa tetangga bawah
    if (row + 1 < height && !maze[row + 1][col] && !visited[row + 1][col]) {
        visited[row + 1][col] = true;
        distance[row + 1][col] = distance[row][col] + 1;
        parentRow[row + 1][col] = row;
        parentCol[row + 1][col] = col;
        recursiveBFS(row + 1, col, visited, distance, parentRow, parentCol);
    }

    // Periksa tetangga kiri
    if (col - 1 >= 0 && !maze[row][col - 1] && !visited[row][col - 1]) {
        visited[row][col - 1] = true;
        distance[row][col - 1] = distance[row][col] + 1;
        parentRow[row][col - 1] = row;
        parentCol[row][col - 1] = col;
        recursiveBFS(row, col - 1, visited, distance, parentRow, parentCol);
    }

    // Periksa tetangga kanan
    if (col + 1 < width && !maze[row][col + 1] && !visited[row][col + 1]) {
        visited[row][col + 1] = true;
        distance[row][col + 1] = distance[row][col] + 1;
        parentRow[row][col + 1] = row;
        parentCol[row][col + 1] = col;
        recursiveBFS(row, col + 1, visited, distance, parentRow, parentCol);
    }
}

        public void moveDroids() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isMoving) {
                    moveGreenDroid();
                    moveRedDroid();

                    try {
                        Thread.sleep(1000); // Delay antar pergerakan droid
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Map(15, 15);
            }
        });
    }
}
