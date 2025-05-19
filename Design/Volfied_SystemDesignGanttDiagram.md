#Volfied arcade game Development Gantt Diagram

```PlantUML

@startgantt

	title __Volfied Arcade Game Remake Development Gantt Diagram__
	
	header All the units are in working days (5 days a week)

	[Sprint 1] lasts 10 days and is colored in Lavender/LightBlue
	[US01] lasts 3 days
	note bottom
	As a player, I want to control a spaceship using arrow keys, so that I can navigate the game area
	end note	
	then [US02] lasts 7 days
	note bottom
	As a player, I want to draw lines to capture territory, so that I can progress in the game
	end note	
	[US03] lasts 9 days
	note bottom
	As a player, I want enemies to move around the game area, so that I face challenges while capturing territory
	end note
	[US05] lasts 7 days and starts after [US01]'s end
	note bottom
	As a player, I want to earn points for capturing territory, so that I can achieve high scores
	end note
	-- Sprint 02 --
	[Sprint 2] lasts 10 days and starts after [Sprint 1]'s end and is colored in Lavender/LightBlue
	[US04] lasts 10 days and starts 1 days after [US03]'s end
	note bottom
	As a player, I want to lose a life if an enemy touches my line while drawing, so that the game remains challenging
	end note
	
	[Gameplay Mechanics completed] happens at [US04]'s end
	
	[US06] lasts 5 days and starts after [US05]'s end
	note bottom
	As a player, I want different levels with increasing difficulty, so that the game remains engaging
	end note
	[US07] lasts 5 days and starts after [US06]'s end
	note bottom
	As a player, I want to see a progress bar indicating the percentage of territory captured, 
	so that I know how close I am to completing a level
	end note
	[US08] lasts 7 days and starts after [US05]'s end
	note bottom
	As a player, I want to unlock new levels after completing the current one, so that I can continue playing
	end note
	[Levels and Progression completed] happens at [US07]'s end
	-- Sprint 03 --
	[Sprint 3] lasts 10 days and starts after [Sprint 2]'s end and is colored in Lavender/LightBlue
	[US09] lasts 10 days and starts after [US04]'s end
	note bottom
	As a player, I want visual effects when capturing territory, so that the game feels more dynamic
	end note
	[US10] lasts 10 days and starts after [US04]'s end
	note bottom
	As a player, I want sound effects for actions like drawing lines and losing lives, so that the game is more immersive
	end note
	[Visual and Audio Effects completed] happens at [US10]'s end
	[US11] lasts 5 days and starts after [US04]'s end
	note bottom
	As a player, I want a main menu with options to start the game, view high scores, 
	and adjust settings, so that I can navigate the game easily
	end note
	[US12] lasts 5 days and starts after [US11]'s end
	note bottom
	As a player, I want a pause button to temporarily stop the game, 
	so that I can take breaks without losing progress
	end note	
	[User Interface completed] happens at [US12]'s end
	-- Sprint 04 --
	[Sprint 4] lasts 10 days and starts after [Sprint 3]'s end and is colored in Lavender/LightBlue
	[US13] lasts 5 days and starts after [US12]'s end
	note bottom
	As a player, I want to see a high score table, 
	so that I can compare my performance with others
	end note
	[US14] lasts 5 days and starts after [US13]'s end
	note bottom
	As a player, I want to earn achievements for milestones 
	like capturing a certain percentage of territory, 
	so that I feel rewarded for my progress
	end note
	[High Scores and Achievements completed] happens at [US14]'s end
	[US15] lasts 7 days and starts after [US12]'s end
	note bottom
	As a player, I want to adjust the game’s difficulty level, 
	so that I can play at my preferred challenge level
	end note
	[US16] lasts 10 days and starts after [US12]'s end
	note bottom
	As a player, I want to customize the appearance of my spaceship, 
	so that I can personalize my gaming experience
	end note
	[Settings and Customization completed] happens at [US16]'s end
	-- end --

@endgantt