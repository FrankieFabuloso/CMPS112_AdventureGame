public class Player{
    // Player Variables
    public static String name;
    public static int maxHealth;
    public static int currentHealth;
    public static int attackDamage;
    public static int numPotions;

    // Player's constructor
    public Player (String name, int maxHP, int currentHP, int damage, int potion, int heal) {
        name = this.name;
        maxHealth = maxHP;
        currentHealth = currentHP;
        attackDamage = damage;
        numPotions = potion;
    }

    // Method to view the player's max HP
    public static int playerMaxHP() {
        return maxHealth;
    }
    // Method to view the player's current HP
    public static int playerCurrentHP() {
        if (currentHealth > maxHealth) {
            currentHealth = maxHealth;
        }
        return currentHealth;
    }
    // Method to view the player's current number of potions
    public static int playerCurrentPotions() {
        return numPotions;
    }
    
    // Method to state what happens every time the player makes a move
    public static void move() {
        currentHealth -= 5;
    }
    
    // Method to state what happens when the player gets hit (by the boss)
    public static int hit() {
        return (currentHealth - 20);
    }
    
    // Method to increase HP using a potion
    public static void drink() {
        numPotions--;
        if ((currentHealth + 20) > maxHealth) {
            currentHealth = maxHealth;
        } else { currentHealth += 20; }
    }
}