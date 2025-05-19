#Volfied arcade game (Handle Section Claiming UC) activity diagram

```PlantUML

@startuml

(*) -right-> "Player moves spaceship\noutside of conquered territory"
if "Closed Section" then
-->[true] "New section claimed"
--> "Conquered territory percenage update"
-right-> (*)
else
--> (*)
endif

@enduml