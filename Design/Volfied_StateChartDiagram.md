#Volfied arcade game (system design) state chart diagram

```PlantUML

@startuml

@startuml
[*] -> NewGame
NewGame --> Login : Succeeded
NewGame --> [*] : Aborted
Login --> State3 : Succeeded
Login --> [*] : Aborted
state State3 {
state "Accumulate Enough Data" as long1
long1 : Just a test
[*] --> long1
long1 --> long1 : New Data
long1 --> ProcessData : Enough Data
Login --> [H]: Resume
}
State3 --> Login : Pause
Login --> State3[H*]: DeepResume
State3 --> State3 : Failed
State3 --> [*] : Succeeded / Save Result
State3 --> [*] : Aborted


@enduml