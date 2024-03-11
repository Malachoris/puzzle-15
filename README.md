# Java Spring-based 15 Puzzle Game Backend API

## Puzzle 15
The 15 Puzzle (also called Gem Puzzle, Boss Puzzle, Game of Fifteen, Mystic Square and more) is a sliding puzzle. It has 15 square tiles numbered 1 to 15 in a frame that is 4 tile positions high and 4 tile positions wide, with one unoccupied position. Tiles in the same row or column of the open position can be moved by sliding them horizontally or vertically, respectively. The goal of the puzzle is to place the tiles in numerical order (from left to right, top to bottom).
Full description: [Wikipedia](https://en.wikipedia.org/wiki/15_Puzzle)

## Project
The project was made for learning purposes. Anyone who is intereste can fork it.
You can run multiple games at the same time, games are given unique IDs. 

## API's
### Currently there are 5 API endpoints:

### POST /api/game
Creates new game and returns game ID.

### GET /api/game/IDs
Returns map with active games and boards.

### GET /api/game/{gameId}
Returns active game and board.

### PUT /api/game/{gameId}/move
Switch positions of integer tile with the 0 value tile.
jason request looks like this:
{ 
"tileValue" : 8 
}

### GET /api/game/{gameId}/complete
Checks game by ID and returns true if game is won.
