# Fabric Multi-Version Mod Template

Ein erweiterbares Fabric-Mod-Template für Minecraft, das als Basis für die Entwicklung von Mods dient, die mehrere Minecraft-Versionen unterstützen. Das Template demonstriert grundlegende Mod-Strukturen, Client-seitige Befehle und Multi-Version-Builds.

Dieses Projekt ist so konfiguriert, dass es auf allen Betriebssystemen (Windows, macOS, Linux) eine einheitliche Entwicklungsumgebung bietet und mehrere Minecraft-Versionen unterstützt.

## 🚀 Schnellstart

1. **Repository klonen:**
   ```bash
   git clone <repository-url>
   cd fabric-multi-version-mod-template
   ```

2. **Java 21 JDK installieren** (falls nicht vorhanden)

3. **Mod für Minecraft 1.21.1 bauen:**
   ```bash
   ./gradlew :mc-1.21.1:build
   ```

4. **Im Spiel testen:**
   - Mod in `mc-1.21.1/build/libs/` finden (Datei ohne `-dev` oder `-sources`)
   - In Minecraft-Launcher als Mod installieren
   - `/hello` im Chat eingeben

## 📋 Unterstützte Versionen

* **Minecraft-Versionen:**
  - 1.21.1 (vollständig konfiguriert)
  - 1.21.2 (Beispiel-Setup, kann angepasst werden)
* **Java:** 21
* **Fabric API:** Aktuelle Version pro Minecraft-Version

## 🏗️ Projektstruktur

```
fabric-multi-version-mod-template/
├── core/                          # Gemeinsamer Quellcode
│   ├── src/main/java/com/multiversion/template/
│   │   ├── ExampleMod.java               # Server-Mod (leer)
│   │   └── ExampleModClient.java         # Client-Mod mit Befehlen
│   └── src/main/resources/
│       └── fabric.mod.json                # Mod-Konfiguration
├── mc-1.21.1/                    # Build-Konfiguration für MC 1.21.1
│   ├── build.gradle              # Gradle-Build-Skript
│   └── gradle.properties         # Versionen und Einstellungen
├── mc-1.21.2/                    # Beispiel für weitere Version
├── gradle/wrapper/               # Gradle Wrapper
├── build.gradle                  # Root-Build-Skript
├── settings.gradle               # Projekt-Konfiguration
└── README.md
```

## ⚙️ Gradle-Befehle Übersicht

### Grundlegende Befehle

| Befehl | Beschreibung | Beispiel |
|--------|-------------|----------|
| `./gradlew --version` | Gradle-Version anzeigen | `./gradlew --version` |
| `./gradlew tasks` | Alle verfügbaren Tasks auflisten | `./gradlew tasks` |
| `./gradlew clean` | Build-Verzeichnisse leeren | `./gradlew clean` |
| `./gradlew build` | Alle Versionen bauen | `./gradlew build` |
| `./gradlew :mc-1.21.1:build` | Spezifische Version bauen | `./gradlew :mc-1.21.1:build` |

### Entwicklung und Test

| Befehl | Beschreibung | Verwendung |
|--------|-------------|-----------|
| `./gradlew :mc-1.21.1:runClient` | Minecraft-Client starten | Für Entwicklung und Test |
| `./gradlew :mc-1.21.1:runServer` | Minecraft-Server starten | Für Server-Tests |
| `./gradlew :mc-1.21.1:genSources` | Minecraft-Quellcode generieren | Für IDE-Unterstützung |

### Multi-Version Builds

* **Spezifische Version bauen:**
  ```bash
  ./gradlew :mc-1.21.1:build
  ```

* **Alle Versionen bauen:**
  ```bash
  ./gradlew build
  ```

* **Parallele Builds (schneller):**
  ```bash
  ./gradlew build --parallel
  ```

## 1. Setup des Entwicklungsumgebung

Stelle sicher, dass du das **Java 21 JDK** installiert hast.

1.  **Klone das Repository:**
    ```bash
    git clone <repository-url>
    cd fabric-multi-version-mod-template
    ```

2.  **IDE einrichten (IntelliJ IDEA empfohlen):**
    *   Öffne das Projektverzeichnis in IntelliJ IDEA.
    *   IntelliJ erkennt das `build.gradle` und konfiguriert das Projekt automatisch. Dies kann einige Minuten dauern, da Gradle die Minecraft-Abhängigkeiten herunterlädt.
    *   Führe den `genSources`-Task aus, um den dekompilierten Minecraft-Quellcode für deine IDE verfügbar zu machen. Du findest ihn im Gradle-Panel auf der rechten Seite unter `Tasks > fabric > genSources`.

## 2. Projekt bauen

Um eine `.jar`-Datei des Mods zu erstellen, die du in einem echten Minecraft-Client oder -Server verwenden kannst, führe den folgenden Befehl im Terminal aus:

*   **Für Windows:**
    ```bash
    .\gradlew.bat build
    ```
*   **Für macOS/Linux:**
    ```bash
    ./gradlew build
    ```

Die fertige Mod-Datei findest du anschließend im Verzeichnis `build/libs/`. Die Datei ohne `-dev` oder `-sources` ist die, die du verteilen kannst.

## 3. Testen in der Entwicklungsumgebung

Du kannst das Spiel direkt aus deiner IDE oder über das Terminal starten, um den Mod zu testen.

*   **Starte den Client:**
    *   Führe den `runClient`-Task im Gradle-Panel aus (`Tasks > fabric > runClient`).
    *   Oder im Terminal: `./gradlew runClient`

*   **Starte den Server:**
    *   Führe den `runServer`-Task im Gradle-Panel aus (`Tasks > fabric > runServer`).
    *   Oder im Terminal: `./gradlew runServer`
    *   Beim ersten Start musst du die `eula.txt` akzeptieren.

## 🎮 Mod-Befehle Übersicht

### Verfügbare Befehle

| Befehl | Beschreibung | Beispiel-Ausgabe |
|--------|-------------|------------------|
| `/hello` | Einfacher Begrüßungsbefehl | `Hello, world!` |

### Wie funktionieren die Befehle?

Die Befehle werden client-seitig registriert und funktionieren sowohl im Singleplayer als auch auf Multiplayer-Servern. Sie nutzen die Fabric Command API v2 für eine robuste Befehlsverarbeitung.

**Code-Beispiel für `/hello`:**
```java
dispatcher.register(literal("hello")
    .executes(context -> {
        context.getSource().sendFeedback(() -> Text.literal("Hello, world!"), false);
        return 1; // Erfolg signalisieren
    }));
```

## 🔧 Template erweitern

### Neue Befehle hinzufügen

1. **Neue Methode in `ExampleModClient.java` erstellen:**
   ```java
   private void registerExampleCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
       dispatcher.register(literal("example")
           .executes(context -> {
               context.getSource().sendFeedback(() ->
                   Text.literal("Beispiel-Befehl ausgeführt!"), false);
               return 1;
           }));
   }
   ```

2. **Befehl in `onInitializeClient()` registrieren:**
   ```java
   CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
       registerHelloCommand(dispatcher);
       registerExampleCommand(dispatcher); // Neuen Befehl hinzufügen
   });
   ```

3. **Mod neu bauen und testen:**
   ```bash
   ./gradlew :mc-1.21.1:build
   ./gradlew :mc-1.21.1:runClient
   ```

### Weitere Befehls-Beispiele

Das Template enthält bereits einen einfachen `/hello`-Befehl als Beispiel. Hier sind weitere Ideen für Befehle, die du implementieren kannst:

**Beispiel: Einfacher Info-Befehl**
```java
private void registerInfoCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
    dispatcher.register(literal("info")
        .executes(context -> {
            context.getSource().sendFeedback(() ->
                Text.literal("Dies ist ein Beispiel-Mod!"), false);
            return 1;
        }));
}
```

**Beispiel: Spieler-Position-Befehl**
```java
private void registerPositionCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
    dispatcher.register(literal("position")
        .executes(context -> {
            PlayerEntity player = (PlayerEntity) context.getSource().getEntity();
            if (player != null) {
                BlockPos pos = player.getBlockPos();
                context.getSource().sendFeedback(() ->
                    Text.literal(String.format("Position: %d, %d, %d", pos.getX(), pos.getY(), pos.getZ())), false);
            }
            return 1;
        }));
}
```

**Beispiel: Welt-Zeit-Befehl**
```java
private void registerTimeCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
    dispatcher.register(literal("worldtime")
        .executes(context -> {
            long time = context.getSource().getWorld().getTime();
            context.getSource().sendFeedback(() ->
                Text.literal("Welt-Zeit: " + time), false);
            return 1;
        }));
}
```

### Neue Minecraft-Version hinzufügen

1. **Neues Verzeichnis erstellen:**
   ```bash
   mkdir mc-1.21.11
   ```

2. **Konfigurationsdateien kopieren:**
   ```bash
   cp mc-1.21.1/build.gradle mc-1.21.11/
   cp mc-1.21.1/gradle.properties mc-1.21.11/
   ```

3. **Versionen aktualisieren in `mc-1.21.11/gradle.properties`:**
   ```properties
    minecraft_version=1.21.11
    yarn_mappings=1.21.11+build.4
    loader_version=0.18.4
    loom_version=1.14-SNAPSHOT
    
    # Fabric API
    fabric_api_version=0.141.1+1.21.11
   ```

4. **Projekt in `settings.gradle` hinzufügen:**
   ```gradle
   include 'mc-1.21.11'
   ```

5. **Neue Version bauen:**
   ```bash
   ./gradlew :mc-1.21.11:build
   ```

### Mod-Konfiguration anpassen

**`fabric.mod.json` bearbeiten:**
- Mod-ID, Name, Beschreibung ändern
- Autoren und Kontaktinformationen aktualisieren
- Neue Abhängigkeiten hinzufügen
- Entry-Points für Server-Mod hinzufügen (falls benötigt)

**Beispiel für Server-Mod Entry-Point:**
```json
{
  "entrypoints": {
    "client": ["com.multiversion.template.ExampleModClient"],
    "server": ["com.multiversion.template.ExampleMod"]
  }
}
```

## 🛠️ Erweiterte Gradle-Optionen

### Build-Optimierungen

* **Parallele Ausführung:**
  ```bash
  ./gradlew build --parallel
  ```

* **Build-Cache verwenden:**
  ```bash
  ./gradlew build --build-cache
  ```

* **Nur bestimmte Tasks ausführen:**
  ```bash
  ./gradlew :mc-1.21.1:compileJava :mc-1.21.1:processResources
  ```

### Debugging und Troubleshooting

* **Verbose Output:**
  ```bash
  ./gradlew build --info
  ```

* **Stacktrace bei Fehlern:**
  ```bash
  ./gradlew build --stacktrace
  ```

* **Dependency-Baum anzeigen:**
  ```bash
  ./gradlew :mc-1.21.1:dependencies
  ```

* **Cache leeren:**
  ```bash
  ./gradlew clean
  rm -rf ~/.gradle/caches/
  ```

## 📚 Weiterführende Ressourcen

- [Fabric Modding Wiki](https://fabricmc.net/wiki/start)
- [Fabric Command API Dokumentation](https://fabricmc.net/wiki/tutorial:commands)
- [Minecraft Forge vs Fabric](https://fabricmc.net/wiki/faq#fabric_vs_forge)
- [Gradle Build Tool](https://gradle.org/documentation/)

---

<div align="center">Danke fürs Durchlesen :3</div>

---
