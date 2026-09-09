# Spanline — Minecraft Utility Mods

Fünf kleine **Forge**-Mods für **Minecraft 1.21.11**. Jede Mod ist unabhängig und kann allein oder zusammen installiert werden.

**Repository:** https://github.com/lolalpha00gamma/minecraft-utility-mods

| Mod | Was sie tut |
| --- | --- |
| **Infinite Water** | Volle Wassereimer bleiben voll (unendlich platzieren). Leere Eimer bleiben leer (unendlich Wasser aufnehmen). |
| **Mining Reach** | Erhöht die Block-Reichweite beim Abbauen und Platzieren. Standard: **+8 Blöcke** (Vanilla 4.5 → 12.5). |
| **Beacon Range** | Vergrößert den Wirkungsradius von Beacons. Standard: **×3** (Stufe 4: 50 → 150 Blöcke). |
| **Elytra Speed** | Elytra-Schub anpassbar. Standard: **×2**, Cap 6 Blöcke/Tick. |
| **Villager Lead** | Villager und Wanderhändler an die Leine. Sie steigen über volle Blöcke nach, wenn du sie ziehst. |

---

## Voraussetzungen

- Minecraft **Java Edition 1.21.11**
- [Minecraft Forge](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.21.11.html) **61.2.1** (oder 61.x)
- Java **21** zum Spielen und Bauen

Keine extra API-Mod nötig — Forge reicht.

## Bauen

Java 21 und Gradle 9.3+ (Wrapper 9.5.1).

```bash
# Alle Mods
./gradlew build

# Einzelne Mod
./gradlew :infinite-water:build
./gradlew :mining-reach:build
./gradlew :beacon-range:build
./gradlew :elytra-speed:build
./gradlew :villager-lead:build
```

Die fertigen JARs liegen in `*/build/libs/*-1.1.0.jar`. Nach `.minecraft/mods/` kopieren.

Falls kein `gradlew` existiert:

```bash
gradle wrapper --gradle-version 9.5.1
./gradlew build
```

---

## 1. Infinite Water (`spanline_infinite_water`)

Volle Wassereimer **bleiben voll**, wenn du Wasser platzierst. Leere Eimer **bleiben leer**, wenn du eine Wasserquelle aufnimmst — die Quelle verschwindet trotzdem.

**Config:** `config/spanline_infinite_water.json`

```json
{
  "enabled": true
}
```

---

## 2. Mining Reach (`spanline_mining_reach`)

Addiert extra Blöcke auf die Vanilla-Blockreichweite. Gilt für Abbauen und Platzieren.

**Config:** `config/spanline_mining_reach.json`

```json
{
  "extraBlocks": 8.0,
  "affectCreative": true
}
```

---

## 3. Beacon Range (`spanline_beacon_range`)

Vanilla-Formel: `Stufe × 10 + 10`. Die Mod skaliert diesen Radius.

**Config:** `config/spanline_beacon_range.json`

```json
{
  "rangeMultiplier": 3.0,
  "rangeBonus": 0.0
}
```

---

## 4. Elytra Speed (`spanline_elytra_speed`)

Zusätzlicher Schub in Blickrichtung während des Gleitens. `speedMultiplier` 2.0 verdoppelt den Vanilla-Look-Schub (0.1). Geschwindigkeit wird bei `maxSpeed` gekappt (Blöcke pro Tick).

**Config:** `config/spanline_elytra_speed.json`

```json
{
  "speedMultiplier": 2.0,
  "maxSpeed": 6.0
}
```

---

## 5. Villager Lead (`spanline_villager_lead`)

Vanilla verbietet Leinen an Villagern. Die Mod erlaubt Leinen für Villager und Wanderhändler. An der Leine:

- höhere Schritt-Höhe (Standard 1.25 → volle Blöcke)
- Hop nach oben, wenn der Halter höher steht oder ein Block im Weg ist
- etwas zügigere Leinen-Follow-Geschwindigkeit

**Config:** `config/spanline_villager_lead.json`

```json
{
  "enabled": true,
  "stepHeight": 1.25,
  "climbBoost": 0.42,
  "followSpeed": 1.25
}
```

---

## Lizenz

MIT — siehe `LICENSE`.
