# CampaignTracking

Background: I am running a DND campaign with some friends. Right now we are playing a less serious practice campaign. However after this campaign I want to play a more serious campaign. My issue right now is that during combat I find myself getting overwhelmed because it is confusing to control multiple enemies. Often times I have to flip through pages to find enemy descriptions and that slows down my ability to handle combat quickly and effectively. I want to make a program that centralizes enemy information and makes combat more simple for myself. 

Rolling dice is important: I started this campaign because I thought it would be a fun way to hangout in person with my friends. I think it can be hard to hangout without people being on their phone however I think DND incentives being present in the moment. For this reason I want this program to be minimal in its use. I still want myself and my players to roll physical dice and have an actual play mat. For that reason the main point of this program is to centralize and help me track information but not to completely automate the combat process. 



Features I want to add: 

- Enemy Creation: Easily be able to create enemies that can be added to combat encounters
    - Defense Attributes: Enemy should have an armor class(AC) and health points(HP). 
    - Other Attributes: Speed, skills, vulnerabilities, immunities, senses, languages, xp reward, abilities should all be visible by clicking on an enemy. 
    - Attacks: this includes the damage dice for the attack and any effects it produces
    - Intiative Bonus: A bonus an enemy gets on their intiative roll at the start of combat


- Encounters: The program should be able to hold multiple different encounters with different enemies
    - Initiative: At the beggining of combat I should be able to input numbers(rolled from dice by players and myself) and get an appopriate action order for the encounter. I shouldn't have to add intiative bonuses for my NPC's it should be done automatically based on the enemy's intiative bonus. 
    - Player Repersentation: Since the players track their on HP and attributes this will be simple. A picture and player name will be needed so they are easily identifiable in the action order. 
    - Enemy Repersentation: Enemies should have a basic name and image. If clicked on a more expansive menu should pop up with the information from the Enemy Creation section.
    - Checking a hit: Should be able to input a score( given by a roll of a player) and return whether or not that player hits the enemy. If there is a hit then there should be a dialog to input the amount of damage (given by the player) the enemy takes.
    - Adjustable HP: HP should be able to be manually overwrited incase a mistake is made. 
    - Loot and XP Reward: Somewhere in the encounter menu there should be a loot and xp reward listed for beating the encounter.
    - Combat Reset: In case I need to reset combat there should be an option to do so. This should be locked behind a dialog box that requires confirmation so it doesn't happen accidently.

    
- Locations: A location should be able to hold multiple encounters.

