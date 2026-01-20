# JavaFX Desktop App Modernization Plan

## Current State Analysis

The existing app is a CLI tool that generates randomized workouts:
- **Strength Training**: Upper/Lower body workouts with 4 training cycles (varying sets/reps)
- **EMOM**: Picks 5 random exercises from a pool of 21, with swap functionality
- **Tabata**: Placeholder only (not implemented)

All exercise data is hardcoded in `src/com/company/getWorkout.java`.

---

## Target Architecture

```
UI Layer (FXML Views)
    │
    ├── MainView.fxml
    ├── StrengthView.fxml
    ├── EmomView.fxml
    ├── TabataView.fxml
    └── styles.css
         │
         ▼
Controllers
    │
    ├── MainController
    ├── StrengthController
    ├── EmomController
    └── TabataController
         │
         ▼
Service Layer
    │
    └── WorkoutService
         │
         ▼
Model Layer
    │
    ├── Exercise
    ├── Workout
    └── WorkoutSet
```

---

## Implementation Steps

### Phase 1: Project Setup

- [x] **1. Initialize Gradle Build**
  - Create `build.gradle` with JavaFX plugin and dependencies (JavaFX 21)
  - Create `settings.gradle` with project name
  - Create `gradle.properties` for JavaFX version

- [x] **2. Create Directory Structure**
  ```
  src/
    main/
      java/
        com/company/
          WorkoutGeneratorApp.java  (entry point)
          controller/
          model/
          service/
      resources/
        com/company/
          view/
          css/
  ```

- [x] **3. Create .gitignore**
  - Ignore `.idea/`, `out/`, `build/`, `*.iml`, `.gradle/`

---

### Phase 2: Model Layer

- [x] **4. Create Exercise Model** (`model/Exercise.java`)
  - Fields: `name`, `cycleReps` (int array for cycles 1-4)
  - Constructor, getters
  - `getRepsForCycle(int cycle)` method

- [x] **5. Create WorkoutSet Model** (`model/WorkoutSet.java`)
  - Fields: `setNumber`, `roundCount`, `exercises` (List of Exercise + rep pairs)

- [x] **6. Create Workout Model** (`model/Workout.java`)
  - Fields: `type` (enum: STRENGTH_UPPER, STRENGTH_LOWER, EMOM, TABATA), `cycle`, `sets` (List of WorkoutSet)

---

### Phase 3: Service Layer

- [x] **7. Create ExerciseRepository** (`service/ExerciseRepository.java`)
  - Static lists of all exercises (extracted from legacy `getWorkout.java`)
  - Methods: `getUpperBodyCompoundPush()`, `getUpperBodyCompoundPull()`, `getUpperBodySecondary()`, etc.

- [x] **8. Create WorkoutService** (`service/WorkoutService.java`)
  - `generateUpperBodyWorkout(int cycle)` - returns `Workout`
  - `generateLowerBodyWorkout(int cycle)` - returns `Workout`
  - `generateEmomWorkout()` - returns list of 5 exercises
  - `swapEmomExercise(List current, int index, Exercise replacement)` - returns updated list

---

### Phase 4: UI Layer (FXML + Controllers)

- [x] **9. Create Main Application Entry Point** (`WorkoutGeneratorApp.java`)
  - Extends `Application`
  - Loads `MainView.fxml`
  - Sets stage title, size, and stylesheet

- [x] **10. Create MainView** (`view/MainView.fxml` + `controller/MainController.java`)
  - Three large buttons: "Strength", "EMOM", "Tabata"
  - Navigation to sub-views

- [x] **11. Create StrengthView** (`view/StrengthView.fxml` + `controller/StrengthController.java`)
  - Upper/Lower body toggle buttons
  - Cycle selector (1-4 buttons or slider)
  - "Generate" button
  - Workout display area (styled cards for each set)
  - "New Workout" / "Back" buttons

- [x] **12. Create EmomView** (`view/EmomView.fxml` + `controller/EmomController.java`)
  - Display 5 exercises as a numbered list (cards)
  - "Swap" button on each exercise card
  - Modal/popup to select replacement
  - "New Workout" / "Back" buttons

- [x] **13. Create TabataView** (placeholder for future)
  - Simple "Coming Soon" message with back button

---

### Phase 5: Styling

- [x] **14. Create CSS Stylesheet** (`css/styles.css`)
  - Dark theme with accent colors
  - Card-based layout for exercises
  - Smooth transitions and hover effects
  - Modern typography (use system fonts or bundle a web font)
  - Responsive padding and spacing

---

### Phase 6: Cleanup and Launch

- [x] **15. Delete Legacy Files**
  - Remove old `.java` files from `src/com/company/`
  - Remove `.idea/`, `out/`, `*.iml` files
  - Keep only new `src/main/` structure

- [x] **16. Test and Package**
  - Run `./gradlew run` to test
  - Optionally create distributable with `./gradlew jlink` or `jpackage`

---

## Key Files to Create

| File | Purpose |
|------|---------|
| `build.gradle` | Gradle build with JavaFX plugin |
| `src/main/java/com/company/WorkoutGeneratorApp.java` | Entry point |
| `src/main/java/com/company/model/Exercise.java` | Exercise data model |
| `src/main/java/com/company/model/Workout.java` | Workout data model |
| `src/main/java/com/company/model/WorkoutSet.java` | Set data model |
| `src/main/java/com/company/service/ExerciseRepository.java` | Exercise data storage |
| `src/main/java/com/company/service/WorkoutService.java` | Workout generation logic |
| `src/main/java/com/company/controller/MainController.java` | Main screen controller |
| `src/main/java/com/company/controller/StrengthController.java` | Strength workout controller |
| `src/main/java/com/company/controller/EmomController.java` | EMOM workout controller |
| `src/main/resources/com/company/view/MainView.fxml` | Main screen layout |
| `src/main/resources/com/company/view/StrengthView.fxml` | Strength screen layout |
| `src/main/resources/com/company/view/EmomView.fxml` | EMOM screen layout |
| `src/main/resources/com/company/css/styles.css` | Application styling |

---

## Prerequisites

- **JDK 17+** installed (JavaFX 21 requires JDK 17 or higher)
- Gradle will download JavaFX automatically
