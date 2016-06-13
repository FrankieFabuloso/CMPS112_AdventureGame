module Main where
import Player

-- 
status :: Player -> IO() 
status (Player {hp = h, potions = p}) = do
  let player = Player h p
  if p > 0 
    then do 
      putStrLn ("     --> You currently have " ++ (show h)  ++ " HP and " ++ (show p)  ++ " potions <--")
      putStrLn "Would you like to drink a potion: YES or NO?"
      input <- getLine
      if input == "YES"
        then do 
          status (drink player)
      else do
        putStrLn "Ok then. Why dont you do something usefull now."
  else do
   putStrLn ("     --> You currently have " ++ (show h)  ++ " HP and " ++ (show p)  ++ " potions <--")




-- "global" variable
player :: Player
player = Player 100 0

main = do  
  putStrLn "Welcome to EK4!" 
  putStrLn "What is your name?" 
  n <- getLine
  putStrLn ("\nHello " ++ n ++ "!\n")
   
  --player 
  --putStrLn $ status player 
  putStrLn "If you wish to view these stats during any point of the game, type in \"STATUS\"." 
  putStrLn "If you wish to drink a potion, first type in Status and follow the instructions given." 
  putStrLn "In this game, every time you perform an action you will lose HP. When your HP hits 0, you will die and the game will end." 
  putStrLn "Now, let's begin..." 

  --if (stat True  then 

  putStrLn "--------------------------------------------------------------------------------------------------------------------------------------------" 
  putStrLn "                                                                Level 1" 
  putStrLn "--------------------------------------------------------------------------------------------------------------------------------------------" 

  putStrLn "ZZZzzz...\nYou are sleeping peacefully, dreaming of summer vacation, when suddenly you are awakened by a bright, annoying light flashing through the window, directly at your face." 
  putStrLn "\"Why is it so bright...?!\" you gripe as you pull your covers over your head, trying to ignore the light and fall back asleep." 
  putStrLn "However, your attempts turn out to be futile as you begin to hear a series of high-pitched noises." 
  putStrLn "You decide that you've had enough and get out of bed to check the window for the source of the disturbance, only to realize that the light shining through your window is making it too bright to see anything outside." 
  putStrLn "As you stand next to your window clad only in your PJs wondering what you should do, the high-pitched noises begin to fade as you hear a voice." 
  putStrLn ("\"" ++ n ++ "\" a soft, gentle voice calls out.")
  putStrLn "You look around, trying to pinpoint the location of the voice." 
  putStrLn "\"This voice is hauntingly beautiful... and... familiar...?\" you think as you being to feel compelled to track down its owner." 
  putStrLn "As you begin to walk out of your room, the light behind you begins to slowly fade away.\n" 

--LOOP1
  goToOptions1 player
  --x <- getLine
  --stat x
  --check if input calls status

-- options for first choices
goToOptions1 player = do
  let playerMove = move player 

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn "\"Alright\" you think to yourself \"Where should I check out first? Should I check out the KITCHEN, check out the DOG HOUSE, or check out the BACKYARD?\""
  input <- getLine
  if input == "KITCHEN" --1
    then do 
      goToKitchen playerMove
  else if input == "DOG HOUSE" --1 --2
    then do 
      goToDogHouse "false" playerMove
  else if input == "BACKYARD" --2 --3
    then do 
      goToBackyard playerMove
  else if input == "STATUS" --3 --4
    then do 
      (status playerMove) --if status is called error: *** Exception: <<loop>>
      goToOptions1 player
  else do 
    putStrLn "Command not recognized. Please try again.\n" --4
    goToOptions1 player

-- otions if you go to the kitchen
goToKitchen player = do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn "\nYou walk into the kitchen.\n\"Alright\" you think to yourself \"Do I want to look inside the SINK, check out the DOG HOUSE, or check out the BACKYARD"
  input <- getLine
  if input == "SINK" --1
    then do 
      goToSink playerMove
  else if input == "DOG HOUSE" --1 --2
    then do 
      goToDogHouse "false" playerMove
  else if input == "BACKYARD" --2 --3
    then do 
      goToBackyard playerMove
  else if input == "STATUS" 
    then do 
      (status playerMove)
      goToKitchen player
  else do 
      putStrLn "Command not recognized. Please try again.\n" --3
      goToKitchen player--loop back to fuction

goToSink player = do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn "\nYou look over to your sink and find a bunch of dirty dishes. \"I should've cleaned those hours ago...\" you think to yourself"
  putStrLn "\"Do I want to check out the DOG HOUSE, or check out the BACKYARD\"?\n"      
  input <- getLine
  if input == "DOG HOUSE" --1
    then do
      goToDogHouse "false" playerMove
  else if input == "BACKYARD" --1 --2
    then do 
      goToBackyard playerMove
  else if input == "STATUS" --2 --3
    then do 
      (status playerMove)
      goToSink player
  else do 
    putStrLn "Command not recognized. Please try again.\n" --3
    goToSink player

-- deciding to go to the doghouse
goToDogHouse bool player = do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn "\n\"Blue?\" you call out as you walk out the front door towards the dog house."
  putStrLn "You hear your dog, Blue, trotting towards you with something in his mouth."
  putStrLn "\"Should I take the ITEM, check out the KITCHEN, or check out the BACKYARD?\""
  putStrLn "you wonder to yourself as you absent-mindedly pet Blue on the head."
  input <- getLine
  if input == "ITEM"
    then do 
      goToItem bool playerMove-- TODO: ADD FUCTION FOR GETTING ITEM
  else if input == "KITCHEN"
    then do 
      goToKitchen playerMove
  else if input == "BACKYARD"
    then do 
      goToBackyard playerMove
  else if input == "STATUS"
    then do 
      (status playerMove) 
      goToDogHouse "true" player
  else do 
    putStrLn "Command not recognized. Please try again.\n"
    goToDogHouse bool player

goToItem bool player = do
  let playerMove = getPotion player
  if bool == "false" 
    then do putStrLn "You remove the item from Blue's mouth."
            putStrLn "\t -> YOU GOT A POTION"
            goToDogHouse "true" playerMove
  else do 
    putStrLn "You remove the item from Blue's mouth only to find out that it is an old tennis ball.\n"
    goToDogHouse "true" playerMove

-- deciding to go to the backyard   
goToBackyard player = do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn "\nYou walk outside to the backyard where you are immediately caught in a tractor beam."
  putStrLn "Your thoughts immediately go into overdrive mode, debating whether it would be better to STRUGGLE against the beam or just COMPLY."
  input <- getLine
  if input == "STRUGGLE"
    then do 
      putStrLn "Your struggles are useless as you feel yourself being beamed up and everything fades to black."
      goToLevel2 playerMove
  else if input == "COMPLY"
    then do 
      putStrLn "You relax your body and as you feel yourself being beamed up, everything fades to black."
      goToLevel2 playerMove
  else if input == "STATUS"
    then do
      (status player)
      goToBackyard playerMove
  else do
    putStrLn "Command not recognized. Please try again.\n"
    goToBackyard playerMove
                     
      
goToLevel2 player = do 
  putStrLn "\nYou walk outside to the backyard where you are immediately caught in a tractor beam."
  putStrLn "\n--------------------------------------------------------------------------------------------------------------------------------------------"
  putStrLn "                                                                Level 2"
  putStrLn "--------------------------------------------------------------------------------------------------------------------------------------------"
  goToOptions2 player

goToOptions2 player = do 
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn "You wake up to a strong throbbing in your head and no memory of where you are or how you got here. You try to look around at your suroundings for any hints as to where you might be, but all you see are glowing orbs of light floating around in the darkness." 
  putStrLn " You get up, and suddenly it hits you. You were abducted by a tractor beam! You realize that you must have been kidnapped by aliens and try your hardest not to freak out. You look around and as your eyes begin to adjust to the darkness, your surroundings begin to come into focus." 
  putStrLn "As you force yourself to calm down and carefully observe your surroundings, you notice that there is a glowing light coming from your right side and strange noises coming from your left." 
  putStrLn "After your mental struggle with yourself of whether you want to walk towards the GLOWING LIGHT, check out the STRANGE NOISES, or TAKE A STEP AWAY from where you are currently standing so that you can begin to grope around in the darkness, you have come to a decision." 
  putStrLn "You want to..."  
  input <- getLine
  if input == "GLOWING LIGHT"
    then do 
      goToGlowingLight playerMove
  else if input == "STRANGE NOISES"
    then do 
      goToStrangeNoises playerMove
  else if input == "TAKE A STEP AWAY"
    then do 
      goToTakeAStepAway playerMove
  else if input == "STATUS"
    then do 
      (status playerMove) 
      goToOptions2 player
  else do 
    putStrLn "Command not recognized. Please try again.\n"
    goToOptions2 player

goToGlowingLight player = do 
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn ""
  putStrLn "You reach out and feel something slimy and smooth. Creeped out, you jump back and look at your hands to see a green electric slime coating your fingertips. You touch the wall in front of you, only to realize that it isn't actually a wall." 
  putStrLn "It feels... slimy... and smooth... Your eyes widden in horror as you begin to frantically rub your hands across the \"wall\", only to have it reveal more of the green electric slime. Your horror increases as it finally hits you. You are trapped inside of a giant, green slime bubble." 
  putStrLn "You frantically grope around your surroundings in hopes that you can find something that will help you out of this mess. Your hands brush against something that feels like a switch. You brace yourself for the worst and flip it."
  putStrLn "Immediately, the slime bubble around you begins to glow brighter and brighter."
  putStrLn "You look around at your surroundings and see that there are three items stuck to the sides of the bubble:"
  putStrLn "a SCREWDRIVER, a pair of SCISSORS, and a CROWBAR."
  putStrLn " What do you want to grab?\n"
  input <- getLine
  if input == "SCREWDRIVER"
    then do 
      goToScrewdriver "GLOWING LIGHT" playerMove
  else if input == "SCISSORS"
    then do 
      goToScissors "GLOWING LIGHT" playerMove
  else if input == "CROWBAR"
    then do 
      goToCrowbar "GLOWING LIGHT" playerMove
  else if input == "STATUS"
    then do 
      (status playerMove) 
      goToGlowingLight player
  else do 
    putStrLn "Command not recognized. Please try again.\n"
    goToGlowingLight player


goToStrangeNoises player = do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn "As you walk towards the strange noises, you begin to recognize them. It's the sound that is created when two metal or steel objects are being banged together. You soon find yourself in front of a door with light streaming out of it. You cautiously open the door and peek inside the room." 
  putStrLn "You see rows of gigantic cylindrical containers lined against the walls, with their lids opening and closing. You also notice that there are three items laying partially hideen behind one of the containers and beyond one of the containers, a metal door. You walk into the room and try to open the door, only to find out that it's locked. " 
  putStrLn "You go back to the items and find: a small BOX, a SCREWDRIVER, a pair of SCISSORS, and a CROWBAR."
  putStrLn"What do you want to grab?\n" 
  input <- getLine
  if input == "BOX"
    then do
      goToBox playerMove
  else if input == "SCREWDRIVER"
    then do 
      goToScrewdriver "STRANGE NOISES" playerMove
  else if input == "SCISSORS"
    then do 
      goToScissors "STRANGE NOISES" playerMove
  else if input == "CROWBAR"
    then do 
      goToCrowbar "STRANGE NOISES" playerMove
  else if input == "STATUS"
    then do 
      (status playerMove)
      goToStrangeNoises player
  else do 
    putStrLn "Command not recognized. Please try again.\n"
    goToGlowingLight player

goToTakeAStepAway player = do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn "Ouch! You bump your head on what feels like a metal bar on the ceiling. You didn't realize that the space you were in was so small. You grope around your surroundings in hopes that you can find something that will help you out of this mess." 
  putStrLn "Your hands brush against something that feels like a switch. You brace yourself for the worst and flip it."
  putStrLn "Immediately, the room gets flooded with light."
  putStrLn "Youlook around at your surroundings and realize that you can only take one step in each direction."
  putStrLn "You start hyperventilating due to your claustrophobia until you notice that there is a tool kit in one of the corners."
  putStrLn "You open the tool kit, hoping to find something that will help you escape, and find:"
  putStrLn "a SCREWDRIVER, a pair of SCISSORS, and a CROWBAR."
  putStrLn "What do you want to grab?\n"
  input <- getLine
  if input == "SCREWDRIVER"
    then do 
      goToScrewdriver "TAKE A STEP AWAY" playerMove
  else if input == "SCISSORS"
    then do 
      goToScissors "TAKE A STEP AWAY" playerMove
  else if input == "CROWBAR"
    then do 
      goToCrowbar "TAKE A STEP AWAY" playerMove
  else if input == "STATUS"
    then do 
      (status playerMove) 
      goToTakeAStepAway player
  else do 
    putStrLn "Command not recognized. Please try again.\n"
    goToGlowingLight player

goToBox player = do
  let playerMove = getPotion player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn "You open the box and find a potion."
  putStrLn""
  putStrLn"\t->YOU HAVE OBTAINED A POTION!<-"
  putStrLn""
  goToStrangeNoises playerMove

goToScrewdriver prevLoc player= do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  if prevLoc == "GLOWING LIGHT"
    then do 
      putStrLn ""
      putStrLn "You start poking the bubble with the screwdriver. However, you soon realize that your poking isn't doing anything to the bubble."
      putStrLn "Why is it even here?! ~.~ Oh well... Guess it's time to try something else.\n"
      goToGlowingLight playerMove
  else do
    putStrLn "After struggling with the screwdriver, you finally manage to get out of your entrapment! However, your victory is short lived"
    putStrLn "as you notice that you are inside a maze of a shapeship, filled with winding corridors and thousands of rooms.\n"
    goToLevel3 playerMove


goToScissors prevLoc player = do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  if prevLoc == "GLOWING LIGHT"
    then do
      putStrLn "After struggling with the scissors, you finally manage to get out of your entrapement!" 
      putStrLn "However, your victory is short lived as you notice that you are inside a maze of a shapeship, filled with winding corridors and thousands of rooms.\n"  
      goToLevel3 playerMove
  else if prevLoc == "STRANGE NOISES"
    then do
      putStrLn "You start attacking the door hinges with the scissors. However, you soon realize that your attack isn't doing anything to the door. Why is it even here?! "  
      putStrLn  "~.~ Oh well... Guess it's time to try something else.\n" 
      goToStrangeNoises playerMove
  else do 
    putStrLn "You start attacking the door hinges with the scissors. However, you soon realize that your attack isn't doing anything to the door. Why is it even here?! "  
    putStrLn  "~.~ Oh well... Guess it's time to try something else.\n" 
    goToTakeAStepAway playerMove



goToCrowbar prevLoc player = do 
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  if prevLoc == "GLOWING LIGHT"
    then do
      putStrLn "You start attacking the door hinges with the scissors. However, you soon realize that your attack isn't doing anything to the door. Why is it even here?! "  
      putStrLn  "~.~ Oh well... Guess it's time to try something else.\n" 
      goToGlowingLight playerMove
  else if prevLoc == "STRANGE NOISES"
    then do
      putStrLn "You start attacking the door hinges with the scissors. However, you soon realize that your attack isn't doing anything to the door. Why is it even here?! "  
      putStrLn  "~.~ Oh well... Guess it's time to try something else.\n" 
      goToStrangeNoises playerMove
  else do 
    putStrLn "You start attacking the door hinges with the scissors. However, you soon realize that your attack isn't doing anything to the door. Why is it even here?! "  
    putStrLn  "~.~ Oh well... Guess it's time to try something else.\n" 
    goToTakeAStepAway playerMove

goToLevel3 player = do
  putStrLn "\n--------------------------------------------------------------------------------------------------------------------------------------------" 
  putStrLn "                                                                Level 3"
  putStrLn "--------------------------------------------------------------------------------------------------------------------------------------------" 
  goToOptions3 player

goToOptions3 player = do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn "\nYou find yourself in a long hallway. There is a guard standing near a door who seems like he is dozing off. \"That room must be important if it has a guard in front of it...\" you think to yourself as you approach it." 
  putStrLn "Do you want to SCREAM and hope that the loud noise wakes you up from what are hoping is a dream, SURPRISE ATTACK the guard in hopes that your attack will render them unconscious, or SNEAK INSIDE the room?"
  input <- getLine
  if input == "SCREAM"
    then do 
      putStrLn "\nThe guard wakes up with a jolt and attacks you. You are quickly overpowered by the guard who proceeds to eleminate the threat, you."
      gameOverFail 
  else if input == "ATTACK"
    then do 
      putStrLn "\nYou quietly sneak over to the guard and whack them over their head with both hands as hard as you can, hoping that it is enough to render them unconscious. With a groan, the guard slumps down onto the floor."
      goToGuard playerMove
  else if input == "SNEAK INSIDE"
    then do 
      putStrLn "\nYou quietly sneak around the guard and reach your hand out for the door handle. You carefully turn the handle and open the door, only to realize that doing so has created a loud noise.\nThe guard's eyes quickly shoot open and sees you right away. You are quickly overpowered by the guard who proceeds to eleminate the threat, you."
      gameOverFail 
  else if input == "STATUS"
    then do 
      (status playerMove) 
      goToOptions3 player
  else do 
    putStrLn "Command not recognized. Please try again.\n"
    goToOptions3 player

goToGuard player = do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  putStrLn"You sigh in relief and stare down at the guard. You can see something sticking out of their pocket and debate whether or not you want to grab the ITEM or JUST LEAVE."
  putStrLn"You can see the guard beginning to stir and quickly decide that you want to..."
  input <- getLine
  if input == "ITEM" 
    then do 
      putStrLn "\nYou quickly bend over to grab the item from the guard's pocket. However, upon doing so, your hand brushes against their armor and you feel a jolt of electricity passing throughout your body.\nYou cry out from the excruciating pain despite your best efforts not to as you stagger through the door to the next room.\nYou let out a whimper as you lean against the door and you swear that you can feel your body getting weaker by the second.\nWhat feels like hours pass before you feel well enough to get up and move around again. You take this chance to study your surroundings and notice that you are in a large room containing a huge holographic picture of Earth being projected above a white, circular table.\nYou quickly snap out of your awestruck state as you hear footsteps and what sounds like voices getting closer to the room. You panic as you quickly hide behind the table. You rack your brains thinking of what the best course of action is. You see a flashing red BUTTON on the control panel in front of you, a GUN next to you, and a DESK behind you. The door to the room slides open as you dive for the..."
      goToDeath playerMove
  else if input == "JUST LEAVE"
    then do 
      putStrLn "\nYou find yourself in a large room containing a huge holographic picture of Earth being projected above a white, circular table.\nYou quickly snap out of your awestruck state as you hear footsteps and what sounds like voices getting closer to the room. You panic as you quickly hide behind the table. You rack your brains thinking of what the best course of action is. You see a flashing red BUTTON on the control panel in front of you, a GUN next to you, and a DESK behind you. The door to the room slides open as you dive for the..."
      goToDeath playerMove
  else if input == "STATUS"
    then do 
      (status playerMove)
      goToGuard player
  else do 
    putStrLn "Command not recognized. Please try again.\n"
    goToGuard playerMove

goToDeath player= do
  let playerMove = move player

  if hp playerMove == 0 
    then do
      gameOverFail
  else do
  input <-  getLine
  if input == "BUTTON"
    then do 
      putStrLn "\nYour hand slaps the button right as the alien guards burst through the door. You are quickly subdued by the guards as the ship begins to give off a piercing noise.\nYou have activated attack mode! The giant lasers in front of the ship begin to power up, gathering dark energy.\nYou watch helplessly, unable to do anything, as the ship's lasers finish powering up and a shot of pure, black energy is shot towards Earth, tearing a hole through the middle of the planet."
      putStrLn "You let out an inhumane scream filled with pain, loss, and regret as you realize that you just killed everyone you cared about. You struggle against your captors, screaming out profanity after profanity, swearing to kill them before you hit the floor. Hard."
      putStrLn "\nTHUMP! Your eyes shoot open and you find yourself face to face with... your bed...? You look down at yourself and realize that you are on the floor with your legs tangled in your blankets. You let out a sigh of relief as you realize that you must have been having a nightmare. You untangle your legs from your blankets and get back into bed, relieved that it was all a dream..."
      gameOverWin 
  else if input == "GUN"
    then do 
      putStrLn "\nYour hand closes around the butt of the gun right as the alien guards burst through the door. You quickly raise your arms and point the gun at the guards and press the trigger, effectively blowing yourself backwards against the wall due to the sheer force of the recoil. As you are tackled by the guards, you notice with satisfaction that at the very least the blast from your gun has taken out one of them."
      putStrLn "You laugh and give out a victory shout before you are spun around and a fist meets your face. You grunt in pain as you hit the floor and are met with a kick to the stomach. The aliens seem to be pissed as they take turns punching and kicking you as you curl up into a ball in an attempt to try to protect yourself. The attacks stop and you look up to see a boot coming right at your face. You quickly squeeze your eyes shut right before it makes contact with your face and you feel your body flying through the air before hitting the wall behind you. Hard."
      putStrLn "\nTHUMP! Your eyes shoot open and you find yourself face to face with... your bed...? You look down at yourself and realize that you are on the floor with your legs tangled in your blankets. You let out a sigh of relief as you realize that you must have been having a nightmare. You untangle your legs from your blankets and get back into bed, relieved that it was all a dream..."
      gameOverWin
  else if input == "DESK"
    then do
      putStrLn "\nYou manage to hide next to a desk right as the aliens burst through the door. You shut your eyes and desperately hope that you won't be found. However, your hope fades as you are grabbed by the neck and roughly hauled to your feet. You open your eyes and are met with a fist flying at your face. You quickly squeeze your eyes shut before it makes contact with your face and you feel yourself falling onto the floor."
      putStrLn "\nTHUMP! Your eyes shoot open and you find yourself face to face with... your bed...? You look down at yourself and realize that you are on the floor with your legs tangled in your blankets. You let out a sigh of relief as you realize that you must have been having a nightmare. You untangle your legs from your blankets and get back into bed, relieved that it was all a dream..."
  else if input == "STATUS"
    then do 
      (status playerMove)
      goToDeath player
  else do 
    putStrLn "Command not recognized. Please try again.\n"
    goToDeath player

gameOverFail = do
  putStrLn "You ran out of HP. Better luck next time!\nGAME OVER!"
gameOverWin = do
  putStrLn "You did it! You managed to survive!\nAchievement Unlocked!\nSURVIVOR"
