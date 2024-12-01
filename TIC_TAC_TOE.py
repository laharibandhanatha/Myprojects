# Tic Tac Toe Game

# Initialize the board
board = [' ' for _ in range(9)]

# Function to print the board
def print_board():
    for i in range(3):
        print('|'.join(board[i*3:(i+1)*3]))
        if i < 2:
            print('-' * 5)

# Function to check for a win
def check_win(player):
    win_conditions = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8],  # Horizontal
        [0, 3, 6], [1, 4, 7], [2, 5, 8],  # Vertical
        [0, 4, 8], [2, 4, 6]              # Diagonal
    ]
    return any(all(board[cell] == player for cell in condition) for condition in win_conditions)

# Function to check if the board is full
def is_draw():
    return all(cell != ' ' for cell in board)

# Main game loop
def play_game():
    print("Welcome to Tic Tac Toe!")
    print_board()
    current_player = 'X'

    while True:
        try:
            move = int(input(f"Player {current_player}, enter your move (1-9): ")) - 1
            if move < 0 or move >= 9 or board[move] != ' ':
                print("Invalid move. Try again.")
                continue

            # Make the move
            board[move] = current_player
            print_board()

            # Check for win
            if check_win(current_player):
                print(f"Player {current_player} wins!")
                break

            # Check for draw
            if is_draw():
                print("It's a draw!")
                break

            # Switch player
            current_player = 'O' if current_player == 'X' else 'X'
        except ValueError:
            print("Please enter a valid number between 1 and 9.")

# Run the game
play_game()
