## SnakeAI
| JAVA | SWING |
|--|--|


  
**SnakeAI** is a standard version of the game Snake equipped with sample algorithms that allow you to get the maximum score during the game.

##
### Interface
The game interface allows you to track the current score, the average score obtained during all games and the number of games.

During each game it is possible to start coloring the snake depending on the obstacles that appear (**C**) and displaying snake vision (**V**)

#### Colors

-  Yellow indicates that the snake is approaching one of the walls.
 - Blue indicates the part of the snake near food
 - The red color indicates that the snake's body is nearby.
 
![snake vision](https://github.com/DKrakowczyk/Snake/blob/master/_screenshots/vision.gif?raw=true)

#### Game speed

  
- Use the **S key** to slow down the game. 
- Use the **F key** to speed up the game.
##
### Game modes

#### Standard game
This is the default game mode activated after pressing the **P** key. To restart the game it is necessary to press the **spacebar**. Use arrows to move the snake.

![standard game](https://github.com/DKrakowczyk/Snake/blob/master/_screenshots/standard.gif?raw=true)

#### Hamilton solver
This game mode uses Hamiltonian path to get the maximum score. 
It is started by pressing the **1** key.

![hamilton game](https://github.com/DKrakowczyk/Snake/blob/master/_screenshots/hamilton.gif?raw=true)

#### Hamilton solver with shortcuts
This game mode uses Hamiltonian path taking shortcuts when they are safe. 
It is started by pressing the **2** key.

![hamilton with shortcuts game](https://github.com/DKrakowczyk/Snake/blob/master/_screenshots/hamilton_short.gif?raw=truehttps://github.com/DKrakowczyk/Snake/blob/master/_screenshots/hamilton_short.gif?raw=true)

#### Simple solver
In this game mode, the snake looks for food, and after finding it determines the optimal path.
It is started by pressing the **3** key.

![simple solver](https://github.com/DKrakowczyk/Snake/blob/master/_screenshots/simple.gif?raw=true)

##
  
In the future, it is planned to expand the possibilities of the snake with search algorithms in order to determine the optimal path to food.
