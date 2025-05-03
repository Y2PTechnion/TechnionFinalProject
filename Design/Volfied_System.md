#Volfied arcade game (system) UCs

```PlantUML

@startuml
left to right direction

rectangle Game as "Volfied Game Remake - System" {
	
	package "Space Pilot" {
		usecase "Handle Space Pilot Movement" as UC1
		usecase "Handle Section Claiming" as UC2
		usecase "Handle Space Pilot Dying" as UC3
	}
	
	package Background {
		usecase "Handle Background Logic" as UC4
	}	
	
	package "Game Logic" {
		usecase "Handle Dashboard"as UC9
		usecase "Handle Game Levels" as UC5
		usecase "Handle Game Over" as UC6
		usecase "Handle Collision" as UC8
	}
	
	package Enemies {
		usecase "Handle Small Enemy Management" as UC7
	}
	
	
}

actor "Player 1" as player
actor "Game DataBase" as database

player --> UC1
UC1 --> UC2
UC1 --> UC4
UC2 ---> UC7
UC2 --> UC5
UC5 --> UC4
UC6 --> database
UC1 --> UC8 #blue;line.dotted;text:black : << include >>
UC7 --> UC8 #blue;line.dotted;text:black : << include >>
UC8 --> UC3
UC3 <-- UC6 #blue;line.dotted;text:black : << extend >>
UC5 --> UC9
UC2 --> UC9

@enduml