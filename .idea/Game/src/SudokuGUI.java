import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuGUI {

    private JFrame frame;
    private JTextField[][] boardFields;
    private Sudoku sudoku;
    private JButton validateButton;
    private JButton hintButton;
    private JButton newGameButton;
    private JLabel statusLabel;

    public SudokuGUI(Sudoku initialSudoku) {
        this.sudoku = initialSudoku;
        initialize();
    }

    /** the boxes in grid */
    private void initialize() {
        frame = new JFrame("Sudoku Game");
        frame.setBounds(100, 100, 450, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        int size = sudoku.getSize();
        boardFields = new JTextField[size][size];
        JPanel boardPanel = new JPanel(new GridLayout(size, size));
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JTextField field = new JTextField();
                boardFields[i][j] = field;
                boardPanel.add(field);
            }
        }
        frame.add(boardPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        validateButton = new JButton("Validate");
        hintButton = new JButton("Hint");
        newGameButton = new JButton("New Game");
        controlPanel.add(validateButton);
        controlPanel.add(hintButton);
        controlPanel.add(newGameButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

        statusLabel = new JLabel(" ");
        frame.add(statusLabel, BorderLayout.NORTH);

        // Event listeners for buttons
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateSudoku();
            }
        });

        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement hint logic
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: Implement new game logic
            }
        });

        frame.pack();
        frame.setVisible(true);
    }

    private void validateSudoku() {
        // TODO: Get values from JTextField components and validate using Sudoku class


        // Update statusLabel based on the result
    }

    public static void main(String[] args) {
        // For demo purposes, initializing with an empty 9x9 Sudoku
        int[][] initialGrid = new int[][] {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {4, 5, 6, 7, 8, 9, 1, 2, 3},
                {7, 8, 9, 1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7, 8, 9, 1},
                {5, 6, 7, 8, 9, 1, 2, 3, 4},
                {8, 9, 1, 2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8, 9, 1, 2},
                {6, 7, 8, 9, 1, 2, 3, 4, 5},
                {9, 1, 2, 3, 4, 5, 6, 7, 8}
        };
        Sudoku initialSudoku = new Sudoku(initialGrid);
        new SudokuGUI(initialSudoku);
    }
}