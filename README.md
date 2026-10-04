# 🎩 Hangman

The classic word guessing game as a desktop app, built with Java Swing. Guess the hidden word one letter at a time before the figure on the gallows is complete.

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/GUI-Swing%20%2F%20Java2D-007396?style=flat-square)
![Dependencies](https://img.shields.io/badge/Dependencies-none-06d6a0?style=flat-square)

## ✨ Features

- 📚 Random word drawn from a dictionary of around 349,000 words
- ⌨️ Play with the on screen A to Z buttons or straight from the keyboard
- 🎨 Gallows and figure drawn by hand with Java2D, building up with every wrong guess
- 💚 Colour feedback: correct letters turn green, wrong ones turn red
- 😀 A lives counter whose face gets more worried as you run out of chances
- 🔁 Win and lose dialogs with instant replay

## 🏗️ How it is built

| Class | Responsibility |
|---|---|
| `Run` | Entry point, creates and shows the window |
| `MainWindow` | Top level `JFrame`: loads the dictionary, wires the panels together, handles keyboard input |
| `HealthPanel` | Tracks lives and paints the gallows by overriding `paintComponent` |
| `WordPanel` | Holds the hidden word, reveals guessed letters and checks for a win |
| `ButtonPanel` | The letter buttons, guess handling, win and lose flow, replay |

Each panel owns one job and exposes a small API (`guess`, `removeLife`, `reset`), so the keyboard and the buttons drive exactly the same game logic.

## ▶️ Run it

From the project root, so `wordsA.txt` is found:

```bash
javac -d out src/*.java
java -cp out Run
```

## 💡 What I practised

Event driven programming with listeners, layout managers, custom painting with `Graphics2D`, and keeping UI state in sync across separate components.
