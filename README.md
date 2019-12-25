# Poker

Console Texas Holdem Poker game which allow to play for up to 22 players, with Human and Machine players

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
* Docker


## How to build

### Manually

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

## Bild with Docker

**Open source root folder in console**

```
cd <project-path>/docker
```

**Run script**

```
./rundocker.sh
```

###Output Example

```
MacBook-Pro poker % java -jar /Projects/poker/target/poker-1.0-SNAPSHOT.jar
How many machine players do you want to invite?
3
How many real players do you want to invite?
1
MachinePlayer-0 cards: [{CLUB:TWO}, {HEART:NINE}]$
MachinePlayer-1 cards: [{HEART:ACE}, {HEART:THREE}]$
MachinePlayer-2 cards: [{DIAMOND:FOUR}, {CLUB:SEVEN}]$
HumanPlayer-3 cards: [{HEART:SEVEN}, {DIAMOND:SEVEN}]$
*** PreFlop ***
Player: MachinePlayer-1 raised 1$
Player: MachinePlayer-2 raised 2$
HumanPlayer-3, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE
1
Player: HumanPlayer-3 call adding 2$
Player: MachinePlayer-0 call adding 2$
Player: MachinePlayer-1 call adding 1$
Player: MachinePlayer-2 call adding 0$
*** Flop ***
{SPADE:SEVEN}
{HEART:TEN}
{DIAMOND:TWO}
Player: MachinePlayer-1 call adding 0$
Player: MachinePlayer-2 call adding 0$
HumanPlayer-3, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE
2
What is your raise amount? Enter integer number
100
Player: HumanPlayer-3 raised 100$
Player: MachinePlayer-0 call adding 100$
Player: MachinePlayer-1 call adding 100$
Player: MachinePlayer-2 call adding 100$
*** Turm ***
{SPADE:SEVEN}
{HEART:TEN}
{DIAMOND:TWO}
{HEART:TWO}
Player: MachinePlayer-1 call adding 0$
Player: MachinePlayer-2 call adding 0$
HumanPlayer-3, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE
1
Player: HumanPlayer-3 call adding 0$
Player: MachinePlayer-0 call adding 0$
*** River ***
{SPADE:SEVEN}
{HEART:TEN}
{DIAMOND:TWO}
{HEART:TWO}
{DIAMOND:TEN}
Player: MachinePlayer-1 call adding 0$
Player: MachinePlayer-2 call adding 0$
HumanPlayer-3, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE
1
Player: HumanPlayer-3 call adding 0$
Player: MachinePlayer-0 call adding 0$
Winners:
1. {HumanPlayer-3: account=10306} - with {FULL_HOUSE: highCardRank=SEVEN}
Others:
2. {MachinePlayer-0: account=9898} - with {FULL_HOUSE: highCardRank=TWO}
3. {MachinePlayer-1: account=9898} - with {TWO_PAIR: highCardRank=TEN}
4. {MachinePlayer-2: account=9898} - with {TWO_PAIR: highCardRank=TEN}
Do you want to continue Game?
y
MachinePlayer-0 cards: [{SPADE:FOUR}, {CLUB:KING}]$
MachinePlayer-1 cards: [{SPADE:SIX}, {CLUB:ACE}]$
MachinePlayer-2 cards: [{DIAMOND:JACK}, {SPADE:ACE}]$
HumanPlayer-3 cards: [{CLUB:SIX}, {CLUB:QUEEN}]$
*** PreFlop ***
Player: MachinePlayer-2 raised 1$
Player: HumanPlayer-3 raised 2$
Player: MachinePlayer-0 call adding 2$
Player: MachinePlayer-1 call adding 2$
Player: MachinePlayer-2 call adding 1$
HumanPlayer-3, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE
2
What is your raise amount? Enter integer number
200
Player: HumanPlayer-3 raised 200$
Player: MachinePlayer-0 call adding 200$
Player: MachinePlayer-1 call adding 200$
Player: MachinePlayer-2 call adding 200$
*** Flop ***
{DIAMOND:FIVE}
{HEART:SIX}
{SPADE:NINE}
Player: MachinePlayer-2 call adding 0$
HumanPlayer-3, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE
1
Player: HumanPlayer-3 call adding 0$
Player: MachinePlayer-0 call adding 0$
Player: MachinePlayer-1 call adding 0$
*** Turm ***
{DIAMOND:FIVE}
{HEART:SIX}
{SPADE:NINE}
{SPADE:KING}
Player: MachinePlayer-2 call adding 0$
HumanPlayer-3, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE
1
Player: HumanPlayer-3 call adding 0$
Player: MachinePlayer-0 call adding 0$
Player: MachinePlayer-1 call adding 0$
*** River ***
{DIAMOND:FIVE}
{HEART:SIX}
{SPADE:NINE}
{SPADE:KING}
{HEART:EIGHT}
Player: MachinePlayer-2 call adding 0$
HumanPlayer-3, What is your next step, enter number? (0)FOLD, (1)CHECK_OR_CALL, (2)RAISE
1
Player: HumanPlayer-3 call adding 0$
Player: MachinePlayer-0 call adding 0$
Player: MachinePlayer-1 call adding 0$
Winners:
1. {MachinePlayer-0: account=10504} - with {ONE_PAIR: highCardRank=KING}
Others:
2. {HumanPlayer-3: account=10104} - with {ONE_PAIR: highCardRank=SIX}
3. {MachinePlayer-1: account=9696} - with {ONE_PAIR: highCardRank=SIX}
4. {MachinePlayer-2: account=9696} - with {HIGH_CARD: highCardRank=ACE}
Do you want to continue Game?
n
Good bye!
```

## Authors

* **Peter Gyschuk** - [GitHub](https://github.com/pgyschuk)

