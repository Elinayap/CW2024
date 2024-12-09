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

### 5.2 Implemented but not working properly

- **Main menu screen**
After the game win or lose screen is displayed, users can click on the "Return to Menu" button. This action bring them back to the main menu screen, where they can choose to start a new game, view  instructions, or quit game. However, the main menu screen sometimes does not display correctly, showing an incomplete interface which only showing partially of the play button.
#### Possible causes: 
This issue may be caused by incorrect  scene initialization, issues with rendering the background image, or incomplete loading of UI components. This could also result from improper resizing or misalignment of UI elements during screen transitions or a potential logic error in reinitializing the main menu layout.

#### Possible solution: 
1. **Correct scene initialization**
Ensure the main menu scene is fully initialized with all components and layout correctly set before transitioning.

