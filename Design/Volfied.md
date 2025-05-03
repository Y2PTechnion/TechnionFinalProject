#Volfied arcade game UC

```PlantUML
`	actor "Main Enemy" as mainEnemy
` usecase "Main Enemy movement" as UC3

@startuml
left to right direction

rectangle Game {
usecase "Small Enemy movement" as UC1

usecase "Small Enemy destroys Space Pilot" as UC2
usecase "Space Pilot claims an area" as UC3
usecase "Space Pilot collects a Grey Box" as UC4
usecase "Space Pilot destroys Small Enemy" as UC5
usecase "Space Pilot completes a level" as UC6
usecase "Game Over" as UC7
}
actor "Space Pilot" as spacePilot
actor "Database" as db

package Enemies {

	actor "Small Enemy" as smallerEnemy
}

actor "Grey Box" as greyBox



UC1 <-down- smallerEnemy

UC2 <-down- smallerEnemy
spacePilot <-- UC2

spacePilot --> UC3

UC4 <-- spacePilot
UC4 -up-> greyBox

UC5 --> smallerEnemy
spacePilot --> UC5

spacePilot --> UC6

UC7 --> db

@enduml