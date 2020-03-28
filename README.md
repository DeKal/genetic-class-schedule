# Genetic Class Scheduling
This source code is inspired by
[zaneacademy](https://www.youtube.com/watch?v=cn1JyZvV5YA).

## Prerequisites
- Using *Intellij*
- Java 8
- JUnit 5

## Input
- Input is stored in `src/main/resources/`
- Consists of `courses.csv`, `departments.csv`, `instructors.csv`, `meeting-times.csv`,`rooms.csv`
- *Note:* Should use input with the same length as in CSV files, more will break output format!! (Sorry, the original
 output printing is shitty :( )
 
## Runners
### Normal Runners
#### com.dekal.scheduling.runner.RunWithDetailProcess
- Using this runner to  see all generations.

#### com.dekal.scheduling.runner.RunWithResult
- Using this runner to get the final schedule result.


### Runners with Junit
- Test cases are store in `src/test/com/dekal/scheduling`
- Test cases are running with JUnit 5
- Test cases input are stored in `src/test/resources/`
 
 
## Sample input
```
Available Department => 
name: MATH, courses: [C1, C3]
name: EE, courses: [C2, C4, C5]
name: PH, courses: [C6, C7]
Available Courses => 
Course #: 325K, name: C1, max student: 25, instructors: [Mr A, Mr B]
Course #: 319K, name: C2, max student: 35, instructors: [Mr A, Mr B, Mr C]
Course #: 462K, name: C3, max student: 25, instructors: [Mr A, Mr B]
Course #: 464K, name: C4, max student: 30, instructors: [Mr C, Mr D]
Course #: 360C, name: C5, max student: 35, instructors: [Mr D]
Course #: 303K, name: C6, max student: 45, instructors: [Mr A, Mr C]
Course #: 303L, name: C7, max student: 45, instructors: [Mr B, Mr D]
Available Rooms =>
rooms# : R1, max seat: 25
rooms# : R2, max seat: 45
rooms# : R3, max seat: 35
Available instructors =>
id: T1 name: Mr A
id: T2 name: Mr B
id: T3 name: Mr C
id: T4 name: Mr D
Available MeetingTimes =>
id: MT1, time: MFW 09:00 - 10:00
id: MT2, time: MFW 10:00 - 11:00
id: MT3, time: TTH 09:00 - 10:30
id: MT4, time: TTH 10:30 - 12:00
```

# Sample Output 
```
------------------------------------------------------------------------------------------------------------------------------

Class # | Dept | Course (number, max # of students) | Room (Capacity) | Instructor (Id) | Meeting Time (Id)                        
------------------------------------------------------------------------------------------------------------------------------
    01  | MATH | C1 (325K, 25)                      | R1 (25)         |     Mr D (T4)   | TTH 09:00 - 10:30 (MT3)                        
    02  | MATH | C3 (462K, 25)                      | R1 (25)         |     Mr C (T3)   | MFW 09:00 - 10:00 (MT1)                        
    03  |   EE | C2 (319K, 35)                      | R2 (35)         |     Mr B (T2)   | MFW 09:00 - 10:00 (MT1)                        
    04  |   EE | C4 (464K, 30)                      | R2 (35)         |     Mr B (T2)   | TTH 09:00 - 10:30 (MT3)                        
    05  |   EE | C5 (360C, 35)                      | R3 (45)         |     Mr A (T1)   | TTH 10:30 - 12:00 (MT4)                        
    06  |   PH | C6 (303K, 45)                      | R3 (45)         |     Mr A (T1)   | MFW 09:00 - 10:00 (MT1)                        
    07  |   PH | C7 (303L, 45)                      | R3 (45)         |     Mr C (T3)   | MFW 10:00 - 11:00 (MT2)                        
------------------------------------------------------------------------------------------------------------------------------
> Solution found in 17 generations!
------------------------------------------------------------------------------------------------------------------------------

```
