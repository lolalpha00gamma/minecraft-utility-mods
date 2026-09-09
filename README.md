# Spanline — Minecraft Utility Mods

Drei kleine **Fabric**-Mods für **Minecraft 1.21.8**. Jede Mod ist unabhängig und kann allein oder zusammen installiert werden.

**Repository:** https://github.com/lolalpha00gamma/minecraft-utility-mods

| Mod | Was sie tut |
| --- | --- |
| **Infinite Water** | Volle Wassereimer bleiben voll (unendlich platzieren). Leere Eimer bleiben leer (unendlich Wasser aufnehmen). |
| **Mining Reach** | Erhöht die Block-Reichweite beim Abbauen und Platzieren. Standard: **+8 Blöcke** (Vanilla 4.5 → 12.5). |
| **Beacon Range** | Vergrößert den Wirkungsradius von Beacons. Standard: **×3** (Stufe 4: 50 → 150 Blöcke). |

---

## Voraussetzungen

- Minecraft **Java Edition 1.21.8**
- [Fabric Loader](https://fabricmc.net/use/) **0.19.5+**
- [Fabric API](https://modrinth.com/mod/fabric-api) für 1.21.8
- Java **21** zum Bauen

## Bauen

Java 21 und Gradle 9.5+ (oder das Wrapper-JAR aus dem Fabric Example Mod) werden benötigt.

```bash
# Alle drei Mods
./gradlew build

# Einzelne Mod
./gradlew :infinite-water:build
./gradlew :mining-reach:build
./gradlew :beacon-range:build
```

Die fertigen JARs liegen in:

- `infinite-water/build/libs/infinite-water-1.0.0.jar`
- `mining-reach/build/libs/mining-reach-1.0.0.jar`
- `beacon-range/build/libs/beacon-range-1.0.0.jar`

JARs nach `.minecraft/mods/` kopieren (zusammen mit Fabric API).

Falls kein `gradlew` existiert:

```bash
gradle wrapper --gradle-version 9.5.1
./gradlew build
```

---

## 1. Infinite Water (`spanline-infinite-water`)

Volle Wassereimer **bleiben voll**, wenn du Wasser platzierst. Leere Eimer **bleiben leer**, wenn du eine Wasserquelle aufnimmst — die Quelle verschwindet trotzdem.

Lava, Pulverschnee und Fisch-/Axolotl-Eimer bleiben unverändert.

**Config:** `config/spanline-infinite-water.json`

```json
{
  "enabled": true
}
```

---

## 2. Mining Reach (`spanline-mining-reach`)

Addiert extra Blöcke auf die Vanilla-Blockreichweite (`blockInteractionRange`, Survival 4.5). Gilt für Abbauen und Platzieren.

**Config:** `config/spanline-mining-reach.json`

```json
{
  "extraBlocks": 8.0,
  "affectCreative": true
}
```

`extraBlocks` wird auf 0–64 begrenzt.

---

## 3. Beacon Range (`spanline-beacon-range`)

Vanilla-Formel: `Stufe × 10 + 10`. Die Mod skaliert diesen Radius:

`neuer Radius = Vanilla-Radius × rangeMultiplier + rangeBonus`

**Config:** `config/spanline-beacon-range.json`

```json
{
  "rangeMultiplier": 3.0,
  "rangeBonus": 0.0
}
```

| Stufe | Vanilla | Mit ×3 |
| --- | --- | --- |
| 1 | 20 | 60 |
| 2 | 30 | 90 |
| 3 | 40 | 120 |
| 4 | 50 | 150 |

Effekte und Dauer bleiben vanilla. Config-Werte: Multiplier 0.1–50, Bonus 0–10000.

---

## Lizenz

MIT — siehe `LICENSE`.
