# BattleShip
a course assignment

**Introduction**  
We are going to show you how to build a  simple  (only because there is no  graphical user interface  - GUI) version of the classic game Battleship.  
Battleship is usually a two-player game, where each player has a  fleet of ships and an ocean (hidden from the other player), and tries to be the  first to sink the other player's  fleet.  
We will be doing just a  one-player vs. computer version, where the computer places the ships, and the human attempts to sink them.  
We'll play this game on a 10x10 “ocean” and will be using the following ships (“the  fleet”):  **![](https://lh7-us.googleusercontent.com/k2mktrEcM79xzavonZfort4NyexfZNXfExSqlgH4KuJYTOSS4Bm4X6lgS1qu4o-5Va4x764-244Qlrjp2KzEoiLmwOFNqKDUspk8nHaPCYZDDP_1pS8km-kZ6Ynu4J4iFpT2-qWEofLVrZbk1fJL7aY)**
**How to Play Battleship**  
Please take a look at these rules  even if  you have played Battleship before in your life.  
Remember this is a  Human vs. Computer  version.  
The computer places the  ten ships  on the ocean in such a way that no ships are immediately adjacent to each other, either horizontally, vertically, or diagonally. Take a look at the following diagrams for examples of legal and illegal placements:  **![](https://lh7-us.googleusercontent.com/j5_0yq440Zi-q3zLgE4GNrnA1WYOggkOROVT4Y6rPa-trty3CCDnkkbS0_TW3LH9nGpFc7KfjQB5CUvDz7IrasvLiaacUwUcSlBfx79aZzciFypQWCWAxdX-XPr7jkfoKAUnqNQtvgOp85d780FHnmI)**
The human player does not know where the ships are. The initial display of the ocean printed  to the console therefore shows a 10 by 10 array of ‘.‘ (see the description of the  Ocean  class’  
print()  method below for more information on what subsequent ocean displays will  look like).  
The human player tries to hit the ships, by indicating a specific row and column number (r,c).  
The computer responds with one bit of information saying, “hit” or “miss”.  
When a ship is hit but not sunk, the program does not provide any information about what kind of a ship was hit. However, when a ship is hit and sinks, the program prints out a message “You just sank a ship -  (type).” After each shot, the computer re-displays the ocean with the new information.  
A ship is “sunk” when every square of the ship has been hit. Thus, it takes four hits (in four different places) to sink a battleship, three to sink a cruiser, two for a destroyer, and one for a submarine.  
The object is to sink the  fleet with as few shots as possible; the best possible score would be 20 (lower scores are better.) When all ships have been sunk, the program prints out a message that the game is over, and tells how many shots were required.
