#Volfied arcade game (Handle Small Enemy Management UC) activity diagram

```PlantUML

@startuml

(*) -right-> "Get small enemy location and speed"
-right->[Check possible directions\nnot to collision with\n'walls' or 'conquered'zones] "Get randomally new direction from possible ones" 
-right->Move small enemy to new location
-right-> (*)

@enduml