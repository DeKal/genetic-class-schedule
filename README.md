# Genetic Class Scheduling
This source code is inspired by
[zaneacademy](https://www.youtube.com/watch?v=cn1JyZvV5YA).

## Prerequisites
- Using *Intellij*
- Java 8

## Input
- Input is stored in `src/main/resources/`
- Consists of `courses.csv`, `departments.csv`, `instructors.csv`, `meeting-times.csv`,`rooms.csv`
- *Note:* Should use input with the same length as in CSV files, more will break output format!! (Sorry, the original
 output printing is shitty :( )
 
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
