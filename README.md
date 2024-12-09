# 1. Title
Sky Battle

## 2. Project Description
Sky battle is a 2D arcade game where player can control the plane, and shooting down all the enemies while dodging the incoming projectiles. There are total of 3 levels and the game get harder with each level. The objective is to survive as long as possible to reach to the end of the game.

## 3. Compilation Instructions
1. Clone the repository at Elinayap/CW2024  
2. Open the project in visual studio code

## 4. Game Instructions

### Controls
- **Move:**    Arrow keys
- **Shoot:**   Spacebar

### Score
- Destroy all the enemies in each level to score different points

### Goals
-  Destroy as many enemies as you can without being hit by enemy’s projectiles
-  Defeat the enemies to proceed to next level

## 5. Implemented Features and Working Properly
### 5.1 Features Working Properly

- **Fonts of the texts**
Changing the fonts of the title to matched the overall theme of the game.The new font enhance the consistency with the game style.

- **Colour of the texts**
Changing the colour of texts to make it to match the game aesthetic.

- **Main menu page**
Creating main menu page to provide users with structured starting point, rather than immediately entering the game. It allow users to choose options such as start game, viewing instructions and quit game.

- **UI of the buttons**
The old button style does not match the overall game’s aesthetic. By updating the style to improve the consistency of the game.

- **Custom cursor**
Changing the cursor style to enhances the user experience by providing a more interactive interface. A custom cursor style is added when users enter the game or hover over the buttons.

- **Play button**
Users can click on the play button to start the game.

- **Instruction button**
Users can read the instructions to get a brief of what is going on before playing the game.

- **Quit game button**
Users can click on the quit game button to exit the game.

- **Change the image of hearts**
Previous  heart images may have lacked visual appeal to match the game overall aesthetic. Replacing them makes the design more consistent.

- **Sound effect for shooting**
Sound effect is added when the user shoots. It delivers instant audio feedback to enhance gameplay experience.

- **User plane controls**
Users can control their plane to move forward and backward instead of just going upwards and downwards.

- **Adjust position of user projectile**
Adjusting the position of user projectile to follow the movement of the user plane instead of staying at the same position without following the user plane.

- **Score label**
The score label is added to the screen for LevelOne, LevelTwo, and LevelThree. It updates dynamically as users defeat enemies, reflecting the specific points earned for each level.

- **Pause screen**
When users press on the ESCAPE key, they can pause and choose from the options such as resume, settings, or exit without disrupting the gameplay. This feature enhances the gaming experience by offering more control and flexibility. 

- **Resume button**
Creating resume button allow users to take a break and then return to the game without losing the game process.

- **Settings button**
Creating settings button allow users to adjust the background music as they play , giving them control over their gaming experience.

- **Volume slider**
Adding volume slider in settings for users to adjust the volume of the background music.

- **Collision handling in LevelOne**
The player loses hearts when enemies exit the screen. This feature add a strategic element, requiring users to efficiently handle enemies and stop them from leaving the screen.

- **Win screen**
The win screen provides users with options such as proceeding to the next level, accessing the shop, or returning to the main menu. Additionally, it displays the user's final score, allowing them to track their performance.

- **Go to next level button**
After defeating the enemies, players can take a short break instead of immediately proceeding to the next level.

- **Shop in LevelOne**
Users can purchase hearts by clicking the shop button. However, users are limited to buying a maximum of 2 hearts during LevelOne only. Buying hearts is not allowed in LevelTwo, as it is the final level of the game.

- **Shop in LevelTwo**
In Level 2, the shop is restricted to all users. A popup message notifies players that heart purchases are unavailable, ensuring clarity and maintaining the challenge of the level.

- **Created Game State**
Implemented a game state management system using the GameState class to efficiently track player health (hearts), shop purchases, and level-specific data. This ensures the  smooth transitions between levels.

- **Return to menu button**
Users can return to main menu if they choose not to proceed to the next level or wish to exit the game. This allows players to reset their game process.

- **Game lose screen**
The game lose screen allows users to view their final score, providing a clear end to the gameplay session. After losing, users can choose to return to the main menu and restart the game.

- **Stop background music**
The background music is stopped when users return to the main menu to prevent overlapping audio. To ensures users have smoother user experience and avoids any audio-related distractions when playing the game.

- **Created LevelThree**
LevelThree introduces an additional feature with the inclusion of bombs. These bombs appear randomly and users need to avoid the collisions. Additionally, the projectile velocity increases. This added feature increase the difficulty of the level, making it more challenging.

- **Added background image to LevelThree**
A captivating background image has been added into LevelThree to improve the game's visual appeal and create a more engaging gameplay experience.

- **Added bomb sound effect to LevelThree**
A bomb sound effect has been added to LevelThree to enhance the audio experience and providing auditory feedback when a bomb collision occurs.

- **Added bombs to LevelThree**
Bombs have been introduced in Level Three, spawning randomly to increase the challenge for players. These bombs must be avoided to prevent user from taking damage.

- **Achievement Added to LevelOne**
Achievements have been integrated into LevelOne. When the user meets the kill target, they will be awarded the "All Enemies Defeated in Level One" achievement. To rewards the users for completing LevelOne.

- **Achievement Added to LevelTwo**
Achievement is now awarded in Level Two when the user successfully defeats the boss. They will be awarded the "All Enemies Defeated in Level Two" achievement. To rewards the users for completing LevelTwo.

- **Achievements Added to LevelThree**
An achievement will be added to LevelThree when the user successfully defeats the boss. Additionally, if the user completes LevelThree without colliding with any bombs, they will earn the "Bomb Dodger" achievement. 

- **Speed up projectile in LevelThree**
In LevelThree, the projectile speed is increased to enhance the level's difficulty, providing a greater challenge for players and testing their reaction time.

- **Hearts and score reset**
The user's hearts and score points are properly reset when returning to the main menu, ensuring a consistent gameplay experience.

- **Health and damage handling**
Both user and enemy planes have health represented by hearts that decrease upon collisions. When the user's hearts reach zero, the game over screen is triggered, ending the gameplay session.

- **Enemy plane penetration**
Deducted all the user’s hearts after enemy planes exit the screen in Levelone.

### **5.2 Implemented but not working properly**

### **Main menu screen**
After the game win or lose screen is displayed, users can click on the "Return to Menu" button. This action bring them back to the main menu screen, where they can choose to start a new game, view  instructions, or quit game. However, the main menu screen sometimes does not display correctly, showing an incomplete interface which only showing partially of the play button.

#### Possible causes: 
This issue may be caused by incorrect  scene initialization, issues with rendering the background image, or incomplete loading of UI components. This could also result from improper resizing or misalignment of UI elements during screen transitions or a potential logic error in reinitializing the main menu layout.

  #### Possible solution: 
  1. **Correct scene initialization**
     Ensure the main menu scene is fully initialized with all components and layout correctly set before transitioning.
  2. **Ensure all the UI components are loaded**
      Ensure all UI components, including all other buttons are fully loaded and rendered before  displaying the main menu screen.
---
### **Stationary bullet issue**
A single bullet fired by the plane remains fixed on the screen, failing to follow its expected trajectory. While other bullets move and synchronize properly with the plane.

#### **Possible causes**
This issue could caused by improper detachment of the bullet after firing, causing it to stay stationary on the screen. There might also be a synchronization issue where the projectile is tied with the movement of the plane, preventing it from functioning independently. Additionally, the issue might be related to object state conflict as the activeProjectile state might not be properly managed.

#### **Possible solution**
1. **Ensure proper detachment**
Modify the projectile so it will be entirely separated from the plane once fired. Check the activeProjectile variable is cleared immediately after the bullet is fired.

2. **Fix synchronization problem**
Modify the synchronization problem to apply only to projectiles still attached to the plane, ensure that it does not interfere with bullets that have already been fired.

3. **Fix object state conflict**
Modify the activeProjectile lifecycle to ensure the smooth state transitions from initialization to firing and detachment to avoid the conflicts.
---
### 5.3 Features Not Implemented

### **Add extra hearts in LevelThree**
After encounter these challenges, I decided to change the rules for shop function in LevelThree. In this final level, users will no longer be able to purchase extra hearts. 

#### Reasons: 
1. **Conflict with the bomb collision**
The bombCollision() method updates the heart display without considering the dynamically added hearts. When attempting to fix this issue, it caused the bomb collision functionality to stop working, so further adjustments were outside the current scope. 

2. **Complexity of shield and bombs**
Level 3 includes features such as a shield and random bomb spawning, which increase the complexity of UI updates, making it challenging to synchronize dynamic heart additions.
---
### **Restart level button**
I planned to include a restart level button in every level, allowing users to retry after losing the game. But, I replaced this button with a "Return to Menu" button, allowing users to go back to the main menu and restart the game from there.

#### Reason:
1.	**Time constraints**
This feature was not implemented due to time constraints and the focus on ensuring other key features were working properly. Leaving it insufficient time to implement and test the feature across all the levels.
---
**Additional boss in LevelThree**
The addition of a second boss in Level 3 was initially planned but ultimately left out. 

#### Reason:
1.	**Too difficult for users**
Having two bosses along with randomly bomb spawns and speed up projectiles created an overly chaotic environment, making it difficult for users to focus on both bosses and bombs at the same time.
---
### **5.4 New Java Classes**

### **MainMenu class**
The MainMenu class serves as the entry point for the game. It provides the main menu where users can start the game, view instructions, or quit game. Additionally, it manages the display of custom elements, including background images, fonts, and cursors. It is located in the com.example.demo.UI package.

#### Sub-components:
1.	**startGame()**
Uses Controller class to launch the game.

2.	**showInstructions()**
Displays a screen to show the instructions to users.

3.	####**createStyledButton()**
Create the style of the buttons including the hover effects and default cursors.

4.	**show()**
Displays the main menu screen with custom background, title, and buttons.
---
### **PauseScreen class**
Displaying pause screen, allow users to pause the game and access options such as resuming the game, adjusting settings, or exiting the game. Additionally, it also provides a settings menu to adjust the volume. It is located in the com.example.demo.UI package.

#### Sub-components:
1.	**show()**
Displays the pause screen with custom background, title, and buttons.

2.	**createStyledButton()**
Create the style of the buttons including the hover effects and default cursors.

3.	**showSettings()**
Display the settings screen with a volume slider to adjust the volume.

4.	**resumeButton Action**
Allows the users to resume the game by closing the pause screen and running the provided resume action.

5.	**settingsButton Action**
Opens the settings menu to allow users to adjust the volume for the background music.

6.	**exitButton Action**
Closes the pause screen and the game, ending the session.
---
### **GameWinScreen class**
The GameWinScreen class is responsible for displaying the “You Win” screen when a user successfully completes a level. It also displays the achievements earned by the user upon successfully completing the level. It provides option to go to the next level, shop, or return to main menu. It is located in the com.example.demo.UI package.

#### Sub-components:
1.	**showGameWinScreen(Stage displayStage, int score, LevelParent currentLevel, Runnable onNextLevel)**
Displays the “You Win” screen for Level 1 and Level 2, featuring a “You Win” message, the user’s score, and buttons for proceeding to the next level, accessing the shop, or returning to the main menu. Includes the functionality to prevent users from accessing shop in Level 2 by showing a pop-up message.

2.	**Showlvl3WinScreen(Stage displayStage, int score)**
Displays the “You Win” screen for Level 3, featuring a “You Win” message, the user’s score, achievements earned, and a button to return to main menu.

3.	**createStyledButton(String text, Font font)**
Create the style of the buttons including the hover effects and default cursors.

4.	**loadCustomFont(String fontPath, int size)**
Load custom fonts from the specified file path, and if the custom fonts fail to load, fall back to the default font.

5. **Achievements Display**
Displays the achievements earned during the level. When the boss or all enemies are defeated, the "All Enemies Defeated" achievement is unlocked. If the user avoids colliding with any bombs in Level Three, the "Bomb Dodger" achievement is unlocked.
---
### **GameEndScreen class**
The GameEndScreen class is responsible for displaying the “Game Over” screen when the user loses the game. It also displays there is no achievements earned by the user as they failed to complete the level and provides an option to return to main menu. It is located in the com.example.demo.UI package.

### Sub-components:
1.	**showGameEndScreen(Stage displayStage, int score)**
Displays the “Game Over” screen for all levels, showing the user’s score and a label indicating that no achievements were unlocked due to failing to complete the game. It also includes a button to return to the main menu.

2.	**loadCustomFont(String fontPath, int size)**
Load custom fonts from the specified file path, and if the custom fonts fail to load, fall back to the default font.







