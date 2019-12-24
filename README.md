# Poker

Console Texas Holdem Poker game which allow to play for up to 23 players, with Human and Machine players

## Getting Started

Game entry point to application is placed in `Game.java`

```
public static void main(String[] args) {
  Game game = new Game();
  game.start();
}
```

### Prerequisites

* JDK 11
* Maven


### How to build

**Open source root folder in console**

```
cd <project-path>
```

**Build jar**
```
mvn clean package
```
**You will see build and test execution log**

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------

Results :

Tests run: 0, Failures: 0, Errors: 0, Skipped: 0

[INFO] 
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ poker ---
[INFO] Building jar: /Projects/poker/target/poker-1.0-SNAPSHOT.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.032 s
[INFO] Finished at: 2019-12-24T23:23:42+02:00
[INFO] ------------------------------------------------------------------------

```

Executable jar will be generated under `target` folder

**Run Jar**

```
java -jar /Projects/poker/target/poker-1.0-SNAPSHOT.jar
```

**Game is started!**
```
How many machine players do you want to invite?
1
How many real players do you want to invite?
1
Player0 cards: [{HEART:TEN}, {SPADE:SIX}]$
HumanPlayer1 cards: [{DIAMOND:TWO}, {SPADE:QUEEN}]$
Player: HumanPlayer1 raised 1$
Player: Player0 raised 2$
HumanPlayer1, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE
1
Player: HumanPlayer1 call adding 1$
Flop Cards:
{HEART:EIGHT}
{HEART:TWO}
{DIAMOND:THREE}
```

## Authors

* **Peter Gyschuk** - [GitHub](https://github.com/pgyschuk)

