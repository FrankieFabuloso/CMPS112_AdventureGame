import java.util.Scanner;
import java.util.Random;


public class Adventure {
            //system objects
    public static Scanner in = new Scanner (System.in);
    public static String input;
    public static String prevInput;
    public static String lastValidCommand;

   	public static void main (String[] args)  {
		Random rand = new Random();

        boolean running = true; // game is currently running
        // level booleans set to false when you pass a level
        boolean level1Running = true;
        boolean level2Running = true;
        boolean level3Running = true; 

        boolean gotItem = true;
        boolean win = false;

		/* Game variables to enable battling
		String[] enemies = { "Skeleton", "Zombie", "Rat", "Assasin"};
		String[] adj = {"Angry", "Bloody", "Rotting", "Old", "Decrepid", "Vicious", "Ugly"};
		int maxEnemyHealth = 100;
		int enemyAttackDamage = 25;
        */


        // ==============================  GAME START ============================== 
        System.out.println("Welcome to EK4!");
        System.out.println("What is your name?");
        String name = in.nextLine();
        // constructor: Player (String name, int maxHP, int currentHP, int damage, int potion, int heal) 
        Player player = new Player(name, 100, 100, 50, 0, 30);

        // ==============================  PREFACE =================================
        System.out.println("You currently have " + player.playerCurrentHP() + " HP and " + player.playerCurrentPotions() + " potions.");
        System.out.println("If you wish to view these stats during any point of the game, type in \"Status\".");
        System.out.println("The \"Status\" menu will automatically exit after 1 input, regardless of whether the input was valid, and return to the game.");
        System.out.println("If you wish to drink a potion, first type in \"Status\" and follow the instructions given.");
        System.out.println("In this game, every time you choose to perform an action you will lose HP. When your HP hits 0, you will die and the game will end.");
        System.out.println ("\nNow, let's begin...\n");

        // ==============================  BEGIN GAME =================================

        GAME:
        while(running) {
            // ==============================  BEGIN LEVEL1 =================================
            LEVEL1:
            while (level1Running) {
                System.out.println ("--------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println ("                                                                Level 1");
                System.out.println ("--------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("ZZZzzz...\nYou are sleeping peacefully, dreaming of summer vacation, when suddenly you are awakened by a bright, annoying light flashing through the window, directly at your face.");
                System.out.println("\"Why is is so bright...?!\" you gripe as you pull your covers over your head, trying to ignore the light and fall back asleep.");
                System.out.println("However, your attempts turn out to be futile as you being to hear a series of high-pitched noises.");
                System.out.println("You decide that you've had enough and get out of bed to check the window for the source of the disturbance, only to realize that the light shining through your window is making it too bright to see anything outside.");
                System.out.println("As you stand next to your window clad only in your PJs wondering what you should do, the high-pitched noises begin to fade as you hear a voice.");
                System.out.println("\"" + player.name + "\" a soft, gentle voice calls out.");
                System.out.println("You look around, trying to pinpoint the location of the voice.");
                System.out.println("\"This voice is hauntingly beautiful... and... familiar...?\" you think as you being to feel compelled to track down its owner.");
                System.out.println("As you being to walk out of your room, the light behind you begins to slowly fade away.\n");
                System.out.println("\"Alright\" you think to yourself \"Where should I check out first? Should I check out the KITCHEN, check out the DOG HOUSE, or check out the BACKYARD?\"");
                
                input = in.nextLine();
                prevInput = "nothing yet";
                lastValidCommand = "nothing yet";
                while (validInitInput(input, 1)) {
                    System.out.println("Comand not recognized. Please enter a valid input");
                    input = in.nextLine();
                    prevInput = "nothing yet";
                    lastValidCommand = "nothing yet";
                }

                OPTIONS1:
                while (level1Running) {
                    checkIfDead(player);
                    if (input.equalsIgnoreCase("kitchen")) {
                        lastValidCommand = "kitchen";
                        player.move();
                        System.out.println("You walk into the kitchen.");
                        System.out.println("\"Alright\" you think to yourself \"Do I want to look inside the SINK, check out the DOG HOUSE, or check out the BACKYARD?\"");
                        prevInput = input;
                        input = in.nextLine();

                        while ( checkCheckLevel1(lastValidCommand, input) ){ // used to make sure player doesnt cheat 
                            System.out.println("Comand not recognized. Please enter a valid input");
                            input = in.nextLine(); 
                        } 

                    } else if (input.equalsIgnoreCase("dog house")) {
                        lastValidCommand = "dog house";
                        player.move();
                        System.out.println("\n\"Blue?\" you call out as you walk out the front door towards the dog house.");
                        System.out.println("You hear your dog, Blue, trotting towards you with something in his mouth.");
                        System.out.println("\"Should I take the ITEM, check out the KITCHEN, or check out the BACKYARD?\" you wonder to yourself as you absent-mindedly pet Blue on the head.");
                        System.out.println("");
                        prevInput = input;
                        input = in.nextLine();

                        while ( checkCheckLevel1(lastValidCommand, input) ){ // used to make sure player doesnt cheat 
                            System.out.println("Comand not recognized. Please enter a valid input");
                            input = in.nextLine(); 
                        } 
                    } else if(input.equalsIgnoreCase("item")) {
                        lastValidCommand = "item";
                        player.move();
                        if (gotItem){
                            System.out.println("");
                            System.out.println("You remove the item from Blue's mouth.");
                            System.out.println("\t-> YOU HAVE OBTAINED A POTION! <-");
                            System.out.println("");
                            player.numPotions++;
                            gotItem = false;
                        } else {
                            System.out.println("You remove the item from Blue's mouth only to find out that it is an old tennis ball.\n");
                        }
                        System.out.println("Do you want to check out the KITCHEN or check out the BACKYARD?");   
                        prevInput = input;
                        input = in.nextLine();
                        System.out.println("lastValidCommand: " + lastValidCommand);

                        while ( checkCheckLevel1(lastValidCommand, input) ){ // used to make sure player doesnt cheat 
                            System.out.println("Comand not recognized. Please enter a valid input");
                            input = in.nextLine(); 
                        } 
                    } else if(input.equalsIgnoreCase("backyard")) {
                        lastValidCommand = "backyard";
                        player.move();
                        System.out.println("\nYou walk outside to the backyard where you are immediately caught in a tractor beam.");
                        System.out.println("Your thoughts immediately go into overdrive mode, debating whether it would be better to STRUGGLE against the beam or just COMPLY.");
                        prevInput = input;
                        input = in.nextLine();

                        while ( checkCheckLevel1(lastValidCommand, input) ){ // used to make sure player doesnt cheat 
                            System.out.println("Comand not recognized. Please enter a valid input");
                            input = in.nextLine(); 
                        } 
                    } else if(input.equalsIgnoreCase("sink")) {
                        lastValidCommand = "sink";
                        player.move();
                        System.out.println("\nYou look over to your sink and find a bunch of dirty dishes. \"I should've cleaned those hours ago...\" you think to yourself");
                        System.out.println("\"Do I want to check out the DOG HOUSE, or check out the BACKYARD\"?\n");
                        prevInput = input;
                        input = in.nextLine();

                        while ( checkCheckLevel1(lastValidCommand, input) ){ // used to make sure player doesnt cheat 
                            System.out.println("Comand not recognized. Please enter a valid input");
                            input = in.nextLine(); 
                        } 
                    }else if (input.equalsIgnoreCase("struggle")) {
                        lastValidCommand = "struggle";
                        player.move();
                        System.out.println("Your struggles are useless as you feel yourself being beamed up and everything fades to black.");
                        level1Running = false;
                    } else if (input.equalsIgnoreCase("comply")) {
                        lastValidCommand = "comply";
                        player.move();
                        System.out.println("You relax your body and as you feel yourself being beamed up, everything fades to black.");
                        level1Running = false;
                    } else if (input.equalsIgnoreCase("status")) {
                        printPlayerStatus(player, prevInput, 1);

                        while ( checkCheckLevel1(lastValidCommand, input) ){ // used to make sure player doesnt cheat 
                            System.out.println("");
                            System.out.println("I don't know what you mean by '" + input + "'");
                            System.out.println( "Try doing something usefull, now. What do you want to do next?" );
                            input = in.nextLine(); 
                        } 

                    } else {
                        System.out.println("Why dont you try doing something useful?");
                        input = prevInput;
                    } 
                    continue OPTIONS1;    
                }
            }   

            // reset gotItem bool for this level
            gotItem = true;
            // ==============================  BEGIN LEVEL2 =================================
            LEVEL2:
            while (level2Running) {
                System.out.println ( "\n--------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println ( "                                                                Level 2");
                System.out.println ( "--------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println ( "You wake up to a strong throbbing in your head and no memory of where you are or how you got here. You try to look around at your suroundings for any hints as to where you might be, but all you see are glowing orbs of light floating around in the darkness." );
                System.out.println ( " You get up, and suddenly it hits you. You were abducted by a tractor beam! You realize that you must have been kidnapped by aliens and try your hardest not to freak out. You look around and as your eyes begin to adjust to the darkness, your surroundings begin to come into focus." );
                System.out.println ( "As you force yourself to calm down and carefully observe your surroundings, you notice that there is a glowing light coming from your right side and strange noises coming from your left." );
                System.out.println ( "After your mental struggle with yourself of whether you want to walk towards the GLOWING LIGHT, check out the STRANGE NOISES, or TAKE A STEP AWAY from where you are currently standing so that you can begin to grope around in the darkness, you have come to a decision." );
                System.out.println ( "You want to..." );

                input = in.nextLine();
                prevInput = "nothing yet";
                lastValidCommand = "nothing yet";
                while (validInitInput(input, 2)) {
                    System.out.println("Why don't you try doing something usefull?");
                    input = in.nextLine();
                    prevInput = "nothing yet";
                    lastValidCommand = "nothing yet";
                }
            

                OPTIONS2:
                while (level2Running) {
                    checkIfDead(player);
                    if (input.equalsIgnoreCase("glowing light")) {
                        lastValidCommand = "glowing light";
                        player.move();
                        System.out.println("");
                        System.out.println( "You reach out and feel something slimy and smooth. Creeped out, you jump back and look at your hands to see a green electric slime coating your fingertips. You touch the wall in front of you, only to realize that it isn't actually a wall." ); 
                        System.out.println( "It feels... slimy... and smooth... Your eyes widden in horror as you begin to frantically rub your hands across the \"wall\", only to have it reveal more of the green electric slime. Your horror increases as it finally hits you. You are trapped inside of a giant, green slime bubble." );
                        System.out.println( "You frantically grope around your surroundings in hopes that you can find something that will help you out of this mess. Your hands brush against something that feels like a switch. You brace yourself for the worst and flip it.");
                        System.out.println( "Immediately, the slime bubble around you begins to glow brighter and brighter.");
                        System.out.println( "You look around at your surroundings and see that there are three items stuck to the sides of the bubble:");
                        System.out.println( "a SCREWDRIVER, a pair of SCISSORS, and a CROWBAR.");
                        System.out.println( " What do you want to grab?\n");
                        prevInput = input;
                        input = in.nextLine();

                        while ( checkCheckLevel2(lastValidCommand, input) ){ // used to make sure player doesnt cheat 
                            System.out.println(lastValidCommand);
                            System.out.println("Stop goofing around! Do something before someone gets here!");
                            input = in.nextLine(); 
                        } 
                    } else if (input.equalsIgnoreCase("strange noises")) {
                        lastValidCommand = "strange noises";
                        player.move();
                        System.out.println( "As you walk towards the strange noises, you begin to recognize them. It's the sound that is created when two metal or steel objects are being banged together. You soon find yourself in front of a door with light streaming out of it. You cautiously open the door and peek inside the room." );
                        System.out.println( "You see rows of gigantic cylindrical containers lined against the walls, with their lids opening and closing. You also notice that there are three items laying partially hideen behind one of the containers and beyond one of the containers, a metal door. You walk into the room and try to open the door, only to find out that it's locked. " );
                        System.out.println( "You go back to the items and find: a small BOX, a SCREWDRIVER, a pair of SCISSORS, and a CROWBAR.");
                        System.out.println("What do you want to grab?\n");
                        prevInput = input;
                        input = in.nextLine();
                    } else if(input.equalsIgnoreCase("take a step away")) {
                        lastValidCommand = "take a step away";
                        player.move();
                        System.out.println("Ouch! You bump your head on what feels like a metal bar on the ceiling. You didn't realize that the space you were in was so small. You grope around your surroundings in hopes that you can find something that will help you out of this mess."); 
                        System.out.println("Your hands brush against something that feels like a switch. You brace yourself for the worst and flip it.");
                        System.out.println("Immediately, the room gets flooded with light.");
                        System.out.println("Youlook around at your surroundings and realize that you can only take one step in each direction."); 
                        System.out.println("You start hyperventilating due to your claustrophobia until you notice that there is a tool kit in one of the corners.");
                        System.out.println("You open the tool kit, hoping to find something that will help you escape, and find:");
                        System.out.println( "a SCREWDRIVER, a pair of SCISSORS, and a CROWBAR.");
                        System.out.println("What do you want to grab?\n");
                        prevInput = input;
                        input = in.nextLine();   
                    } else if(input.equalsIgnoreCase("screwdriver")) {
                        lastValidCommand = "screwdriver";
                        player.move();
                        if (prevInput.equalsIgnoreCase("glowing light")){
                        System.out.println("");
                        System.out.println("You start poking the bubble with the screwdriver. However, you soon realize that your poking isn't doing anything to the bubble." );
                        System.out.println("Why is it even here?! ~.~ Oh well... Guess it's time to try something else.\n");
                        input = lastValidCommand;
                        } else {
                            System.out.println( "After struggling with the screwdriver, you finally manage to get out of your entrapment! However, your victory is short lived" );
                            System.out.println( "as you notice that you are inside a maze of a shapeship, filled with winding corridors and thousands of rooms.\n");
                            level2Running = false; // go to level 3
                        }
                        
                    } else if(input.equalsIgnoreCase("scissors")) {
                        lastValidCommand = "scissors";
                        player.move();
                        if (prevInput.equalsIgnoreCase("glowing light")){
                            System.out.println("After struggling with the scissors, you finally manage to get out of your entrapement!" ); 
                            System.out.println("However, your victory is short lived as you notice that you are inside a maze of a shapeship, filled with winding corridors and thousands of rooms.\n" );
                            level2Running = false; // go to level 3
                            break;
                        } else {
                        System.out.println("You start attacking the door hinges with the scissors. However, you soon realize that your attack isn't doing anything to the door. Why is it even here?! " );
                        System.out.println( "~.~ Oh well... Guess it's time to try something else.\n");
                        System.out.println( "Which do you want to use?");
                        System.out.println("a SCREWDRIVER, a pair of SCISSORS, and a CROWBAR.");
                        prevInput = input;
                        input = in.nextLine();
                        }
                        input = in.nextLine();
                    } else if (input.equalsIgnoreCase("crowbar")) {
                        lastValidCommand = "crowbar";
                        player.move();
                        System.out.println( "You start attacking the door with the crowbar. However, you soon realize that your attack isn't doing anything to the bubble." );
                        System.out.println( "Why is it even here?! ~.~ Oh well... Guess it's time to try something else.\n" );
                        System.out.println( "" );
                        System.out.println( "Which do you want to use?");
                        System.out.println("a SCREWDRIVER, a pair of SCISSORS, and a CROWBAR.");
                        prevInput = input;
                        input = in.nextLine();
                    } else if (input.equalsIgnoreCase("box")) {
                        lastValidCommand = "box";
                        player.move();
                        if (gotItem){
                            System.out.println("You open the box and find a potion.");
                            System.out.println("");
                            System.out.println("\t->YOU HAVE OBTAINED A POTION!<-");
                            System.out.println("");
                            player.numPotions++;
                            gotItem = false;
                        } else {
                            System.out.println("Why are you opening this box again?\n");
                        }
                        System.out.println("You look back over at the remaining items. What do you want to grab?\n");
                        prevInput = input;
                        input = in.nextLine();

                    } else if (input.equalsIgnoreCase("status")) {
                        printPlayerStatus(player, prevInput, 2);

                        while ( checkCheckLevel2(lastValidCommand, input) ){ // used to make sure player doesnt cheat 
                            System.out.println(lastValidCommand);
                            System.out.println("");
                            System.out.println("I don't know what you mean by '" + input + "'");
                            System.out.println( "Try doing something usefull, now. What do you want to do next?" );
                            input = in.nextLine(); 
                        } 

                    } else {
                        System.out.println("Why dont you try doing something useful?");
                        input = prevInput;
                    }
                    continue OPTIONS2; 
                }
                System.out.println(level1Running);
                System.out.println(level2Running); 
            } 


            LEVEL3:
            while(level3Running){
                System.out.println ("\n--------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println ("                                                                Level 3");
                System.out.println ("--------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println ("You find yourself in a long hallway. There is a guard standing near a door who seems like he is dozing off." );
                System.out.println ("\"That room must be important if it has a guard in front of it...\" you think to yourself as you approach it.");
                System.out.println ("Do you want to SCREAM and hope that the loud noise wakes you up from what are hoping is a dream,"); 
                System.out.println ("SURPRISE ATTACK the guard in hopes that your attack will render them unconscious,");
                System.out.println ("or SNEAK INSIDE the room?");
        
                input = in.nextLine();
                prevInput = "nothing yet";
                lastValidCommand = "nothing yet";
                while (validInitInput(input, 3)) {
                    System.out.println("Why don't you try doing something usefull?");
                    input = in.nextLine();
                    prevInput = "nothing yet";
                    lastValidCommand = "nothing yet";
                }

                OPTIONS3:
                while(level3Running){
                    checkIfDead(player);
                    if (input.equalsIgnoreCase("scream")) {
                        lastValidCommand = "scream";
                        player.move();
                        System.out.println("");
                        System.out.println("The guard wakes up with a jolt and attacks you. You are quickly overpowered by the guard who proceeds to eleminate the threat, you.");
                        win = false;
                        level3Running = false;  
                        running = false; // you lose
                        break;     
                    } else if (input.equalsIgnoreCase("surpirse attack")) {
                        lastValidCommand = "surpirse attack";
                        player.move();
                        System.out.println("");
                        System.out.println("You quietly sneak over to the guard and whack them over their head with both hands as hard as you can, hoping that it is enough to render them unconscious."); 
                        System.out.println("With a groan, the guard slumps down onto the floor.");
                        System.out.println("You sigh in relief and stare down at the guard. You can see something sticking out of their pocket and debate whether or not you want to GRAB THE ITEM or JUST LEAVE.");
                        System.out.println("You can see the guard beginning to stir and quickly decide that you want to...");         
                    } else if(input.equalsIgnoreCase("sneak inside")) {
                        lastValidCommand = "sneak inside";
                        player.move();
                        System.out.println("");
                        System.out.println("You quietly sneak around the guard and reach your hand out for the door handle. You carefully turn the handle and open the door, only to realize that doing so has created a loud noise." );
                        System.out.println("The guard's eyes quickly shoot open and sees you right away. You are quickly overpowered by the guard who proceeds to eleminate the threat, you.");
                        win = false;
                        level3Running = false;  
                        running = false; // you lose
                        break;
                    } else if(input.equalsIgnoreCase("grab the item")) {
                        lastValidCommand = "grab the item";
                        player.move();
                        System.out.println("");
                        System.out.println("You quickly bend over to grab the item from the guard's pocket. However, upon doing so, your hand brushes against their armor and you feel a jolt of electricity passing throughout your body." );
                        System.out.println("You cry out from the excruciating pain despite your best efforts not to as you stagger through the door to the next room." );
                        System.out.println("You let out a whimper as you lean against the door and you swear that you can feel your body getting weaker by the second." );
                        System.out.println("What feels like hours pass before you feel well enough to get up and move around again." );
                        System.out.println("You take this chance to study your surroundings and notice that you are in a large room containing a huge holographic picture of Earth being projected above a white, circular table." );
                        System.out.println("You quickly snap out of your awestruck state as you hear footsteps and what sounds like voices getting closer to the room. You panic as you quickly hide behind the table." );
                        System.out.println("You rack your brains thinking of what the best course of action is. You see a flashing red BUTTON on the control panel in front of you, a GUN next to you, and a DESK behind you.");
                        System.out.println("The door to the room slides open as you dive for the...");
                        player.numPotions++;
                        input = in.nextLine(); 
                    } else if(input.equalsIgnoreCase("just leave")) {
                        lastValidCommand = "just leave";
                        player.move();
                        System.out.println("");
                        System.out.println( "You find yourself in a large room containing a huge holographic picture of Earth being projected above a white, circular table." );
                        System.out.println( "You quickly snap out of your awestruck state as you hear footsteps and what sounds like voices getting closer to the room. You panic as you quickly hide behind the table." ); 
                        System.out.println( "You rack your brains thinking of what the best course of action is. You see a flashing red BUTTON on the control panel in front of you, a GUN next to you, and a DESK behind you." );
                        System.out.println( "The door to the room slides open as you dive for the..." );
                        input = in.nextLine();
                    }else if (input.equalsIgnoreCase("button")) {
                        lastValidCommand = "button";
                        player.move();
                        System.out.println("");
                        System.out.println("Your hand slaps the button right as the alien guards burst through the door. You are quickly subdued by the guards as the ship begins to give off a piercing noise.");
                        System.out.println("You have activated attack mode! The giant lasers in front of the ship begin to power up, gathering dark energy.");
                        System.out.println("You watch helplessly, unable to do anything, as the ship's lasers finish powering up and a shot of pure, black energy is shot towards Earth, tearing a hole through the middle of the planet.");
                        System.out.println("You let out an inhumane scream filled with pain, loss, and regret as you realize that you just killed everyone you cared about.");
                        System.out.println("You struggle against your captors, screaming out profanity after profanity, swearing to kill them before you hit the floor. Hard.");
                        System.out.println("");
                        System.out.println("THUMP! Your eyes shoot open and you find yourself face to face with... your bed...? You look down at yourself and realize that you are on the floor with your legs tangled in your blankets."); 
                        System.out.println("You let out a sigh of relief as you realize that you must have been having a nightmare. You untangle your legs from your blankets and get back into bed, relieved that it was all a dream...");
                        win = true;
                        level3Running = false;  
                        running = false; // you win!
                        break;
                    }else if (input.equalsIgnoreCase("gun")) {
                        lastValidCommand = "gun";
                        player.move();
                        System.out.println("");
                        System.out.println("Your hand closes around the butt of the gun right as the alien guards burst through the door. You quickly raise your arms and point the gun at the guards and press the trigger, ");
                        System.out.println("effectively blowing yourself backwards against the wall due to the sheer force of the recoil. As you are tackled by the guards, you notice with satisfaction that at the very least the blast from your gun has taken out one of them.");
                        System.out.println("You laugh and give out a victory shout before you are spun around and a fist meets your face. You grunt in pain as you hit the floor and are met with a kick to the stomach." );
                        System.out.println("The aliens seem to be pissed as they take turns punching and kicking you as you curl up into a ball in an attempt to try to protect yourself. The attacks stop and you look up to see a boot coming right at your face. ");
                        System.out.println("You quickly squeeze your eyes shut right before it makes contact with your face and you feel your body flying through the air before hitting the wall behind you. Hard.");
                        System.out.println("");
                        System.out.println("THUMP! Your eyes shoot open and you find yourself face to face with... your bed...? You look down at yourself and realize that you are on the floor with your legs tangled in your blankets." );
                        System.out.println("You let out a sigh of relief as you realize that you must have been having a nightmare. You untangle your legs from your blankets and get back into bed, relieved that it was all a dream...");
                        win = true;
                        level3Running = false;         
                        running = false; // you win!
                        break;
                    } else if (input.equalsIgnoreCase("desk")) {
                        lastValidCommand = "desk";
                        player.move();
                        System.out.println("");
                        System.out.println("You manage to hide next to a desk right as the aliens burst through the door. You shut your eyes and desperately hope that you won't be found." ); 
                        System.out.println(" However, your hope fades as you are grabbed by the neck and roughly hauled to your feet. You open your eyes and are met with a fist flying at your face." );
                        System.out.println("You quickly squeeze your eyes shut before it makes contact with your face and you feel yourself falling onto the floor." );
                        System.out.println("");
                        System.out.println("THUMP! Your eyes shoot open and you find yourself face to face with... your bed...?" );
                        System.out.println("You look down at yourself and realize that you are on the floor with your legs tangled in your blankets." );
                        System.out.println("You let out a sigh of relief as you realize that you must have been having a nightmare." );
                        System.out.println("You untangle your legs from your blankets and get back into bed, relieved that it was all a dream...");
                        win = true;  
                        level3Running = false;     
                        running = false; // you win!     
                        break;
                    } else if (input.equalsIgnoreCase("status")) {
                        printPlayerStatus(player, prevInput, 3);

                        while ( checkCheckLevel3(lastValidCommand, input) ){ // used to make sure player doesnt cheat 
                            System.out.println("");
                            System.out.println("I don't know what you mean by '" + input + "'");
                            System.out.println( "Try doing something usefull, now. What do you want to do next?" );
                            input = in.nextLine(); 
                        } 
                        
                    } else {
                        System.out.println("Why dont you try doing something useful?");
                        input = prevInput;
                    }
                    continue OPTIONS3;    
                }

      
                System.out.println(level1Running);
                System.out.println(level2Running);
                System.out.println(level3Running);
                gameOver(win);

            }        
        }
    }


 /* functions used during the game */

    public static void printPlayerStatus (Player p, String prevCommand, int level){
        System.out.println("");
        System.out.println("\t-> You currently have " + p.playerCurrentHP() + " HP and " + p.playerCurrentPotions() + " potions. <-");
        System.out.println("");
        if (p.playerCurrentPotions() > 0){
            System.out.println("Would you like to drink a potion? YES or NO?");
            input = in.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                p.drink();
            } else if (input.equalsIgnoreCase("no")){
                System.out.println("Ok so where do you want to go next then?");  
                input = in.nextLine(); 
            } else { 
                System.out.println("I don't know what '" + input + "'. Again..." );
                System.out.println("");
                printPlayerStatus( p, prevCommand, level); 
            }
        } else {
            System.out.println("Ok cool, you aren't dead yet.");
            System.out.println("Why dont you try doing something usefull now?"); 
            System.out.println("");
            input = in.nextLine(); 

            if(prevCommand.equalsIgnoreCase("goToOptions" + level)){
                while( validInitInput(input, level) ){
                    System.out.println("");
                    System.out.println("I don't know what '" + input + "' means. Again..." );
                    System.out.println("");
                    System.out.println("Ok cool, you aren't dead yet.");
                    System.out.println("Why dont you try doing something usefull?");
                    System.out.println("");
                    input = in.nextLine(); 
                }
            }
        }
    }

    public static Boolean validInitInput(String input, int level) {
        if (level == 1){
            if (!(input.equalsIgnoreCase("kitchen") || input.equalsIgnoreCase("dog house") || input.equalsIgnoreCase("backyard") || input.equalsIgnoreCase("status"))){
                return true;
            } else {
                lastValidCommand = "goToOptions1";
                return false;
            }
        } else if (level == 2) {
            if (!(input.equalsIgnoreCase("glowing light") || input.equalsIgnoreCase("strange noises") || input.equalsIgnoreCase("take a step away") || input.equalsIgnoreCase("status"))){
                return true;
            } else {
                lastValidCommand = "goToOptions2";
                return false;
            }
        } else if (level == 3) { 
             if (!(input.equalsIgnoreCase("scream") || input.equalsIgnoreCase("surpirse attack") || input.equalsIgnoreCase("sneak inside") || input.equalsIgnoreCase("status"))){
                return true;
            } else {
                lastValidCommand = "goToOptions3";
                return false;
            }
        }else {return true; }
    }

    public static Boolean checkCheckLevel1(String whereUR, String input){
        if( whereUR.equalsIgnoreCase("goToOptions1")){
            if ( input.equalsIgnoreCase("kitchen") ){
                return false;
            } else if (input.equalsIgnoreCase("dog house")){
                return false;
            } else if (input.equalsIgnoreCase("backyard")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }

        }else if ( whereUR.equalsIgnoreCase("kitchen") ){

            if ( input.equalsIgnoreCase("sink") ){
                return false;
            } else if (input.equalsIgnoreCase("dog house")){
                return false;
            } else if (input.equalsIgnoreCase("backyard")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }
        } else if (whereUR.equalsIgnoreCase("dog house")) {
            if ( input.equalsIgnoreCase("item") ){
                return false;
            } else if (input.equalsIgnoreCase("kitchen")){
                return false;
            } else if (input.equalsIgnoreCase("backyard")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }
        } else if (whereUR.equalsIgnoreCase("backyard")) {
            if ( input.equalsIgnoreCase("struggle") ){
                return false;
            } else if (input.equalsIgnoreCase("comply")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }
        } else if (whereUR.equalsIgnoreCase("sink")) {
            if ( input.equalsIgnoreCase("dog house") ){
                return false;
            } else if (input.equalsIgnoreCase("backyard")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }
        } else if (whereUR.equalsIgnoreCase("item")) {
            if ( input.equalsIgnoreCase("kitchen") ){
                return false;
            } else if (input.equalsIgnoreCase("backyard")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }    
        } else { return true; }
    }

    public static Boolean checkCheckLevel2( String whereUR, String input ){
        if( whereUR.equalsIgnoreCase("goToOptions2") ){

            if ( input.equalsIgnoreCase("glowing light") ){
                return false;
            } else if (input.equalsIgnoreCase("strange noises")){
                return false;
            } else if (input.equalsIgnoreCase("take a step away")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }

        } else if ( whereUR.equalsIgnoreCase("glowing light") ){

            if ( input.equalsIgnoreCase("screwdriver") ){
                return false;
            } else if (input.equalsIgnoreCase("scissors")){
                return false;
            } else if (input.equalsIgnoreCase("crowbar")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }
        } else if (whereUR.equalsIgnoreCase("strange noises")) {
            if ( input.equalsIgnoreCase("box") ){
                return false;
            } else if (input.equalsIgnoreCase("screwdriver")){
                return false;
            } else if (input.equalsIgnoreCase("scissors")){
                return false;
            } else if (input.equalsIgnoreCase("crowbar")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }
        } else if (whereUR.equalsIgnoreCase("take a step away")) {
            if ( input.equalsIgnoreCase("struggle") ){
                return false;
            } else if (input.equalsIgnoreCase("comply")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }
        } else if (whereUR.equalsIgnoreCase("sink")) {
            if ( input.equalsIgnoreCase("dog house") ){
                return false;
            } else if (input.equalsIgnoreCase("backyard")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }
        } else if (whereUR.equalsIgnoreCase("item")) {
            if ( input.equalsIgnoreCase("kitchen") ){
                return false;
            } else if (input.equalsIgnoreCase("backyard")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }    
        } else { return true; }
    }

    public static Boolean checkCheckLevel3(String whereUR, String input){
        if( whereUR.equalsIgnoreCase("goToOptions3")){

            if ( input.equalsIgnoreCase("scream") ){ // you lose, dont add opption
                return false;
            } else if (input.equalsIgnoreCase("surpirse attack")){
                return false;
            } else if (input.equalsIgnoreCase("sneak inside")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }

        }else if ( whereUR.equalsIgnoreCase("surpirse attack") ){

            if ( input.equalsIgnoreCase("grab the item") ){
                return false;
            } else if (input.equalsIgnoreCase("just leave")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }
        } else if (whereUR.equalsIgnoreCase("grab the item")) {
            if ( input.equalsIgnoreCase("gun") ){
                return false;
            } else if (input.equalsIgnoreCase("button")){
                return false;
            } else if (input.equalsIgnoreCase("desk")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            }
        } else if (whereUR.equalsIgnoreCase("just leave")) {
            if ( input.equalsIgnoreCase("gun") ){
                return false;
            } else if (input.equalsIgnoreCase("button")){
                return false;
            } else if (input.equalsIgnoreCase("desk")){
                return false;
            } else if (input.equalsIgnoreCase("status")){
                return false;
            } else {
                return true;
            } 
        } else { return true; }
    }
    public static void checkIfDead(Player player){
        if (player.playerCurrentHP() <= 0){
            System.out.println("You ran out of HP. Better luck next time!");
            System.out.println("GAME OVER!");
            System.exit(0);
        }
    }
    public static void gameOver(boolean win) {
        if (win) {
            System.out.println("You did it! You managed to survive!" );
            System.out.println("Achievement Unlocked!");
            System.out.println("SURVIVOR");
        } else {
            System.out.println("You ran out of HP. Better luck next time!");
            System.out.println("GAME OVER!");
        }
    }
}
