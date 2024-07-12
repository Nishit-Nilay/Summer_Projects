# Hand Cricket Game

Hand Cricket Game is a graphical application written in Java using Swing. It allows users to play a virtual game of Hand Cricket against the computer. The game features animations to display hand movements and maintains a score to determine the winner.

## Features

- User and computer hand animations.
- Scoring system with user and computer turns.
- Game reset functionality.
- Simple and interactive graphical user interface.

## Prerequisites

- Java Development Kit (JDK) 8 or higher

## Getting Started

1. **Clone the repository:**

    ```bash
    git clone https://github.com/Nishit-Nilay/Summer_Projects.git
    cd Summer_Projects/HandCricketGame
    ```

2. **Compile the Java source file:**

    ```bash
    javac com/example/Game.java
    ```

3. **Run the application:**

    ```bash
    java com.example.Game
    ```

## Usage

1. Run the application.
2. Enter a number between 0 and 6 in the input field and press "Submit".
3. Watch the hand animations for both the user and the computer.
4. The game will automatically manage turns and update the score.
5. To reset the game, press the "Reset Game" button.

## Game Rules

- User and computer take turns batting.
- If the user shows 0 and the computer shows a number between 1 and 6, the user scores that number of runs.
- If the user and the computer show the same number, the current batsman is out, and the turn switches.
- The game continues until the user or the computer is out.
- The final score determines the winner.

## Example

src/
├── com/
│ └── example/
│ └── Game.java
resources/
├── 0.gif
├── 1.gif
├── 2.gif
├── 3.gif
├── 4.gif
├── 5.gif
└── 6.gif


## Dependencies

No external dependencies are required.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- [Java Swing](https://docs.oracle.com/javase/tutorial/uiswing/) for the GUI components
- [GIFs](https://www.example.com/) for the hand animations (Replace this with the actual source of your GIFs if applicable)

## Contact

If you have any questions or suggestions, feel free to contact me at nishitnilay38@gmail.com.




	****DISCLAMER: THIS IS AN ONGOING PROJECT AND SUBSEQUENT CHANGES WILL BE MADE.****