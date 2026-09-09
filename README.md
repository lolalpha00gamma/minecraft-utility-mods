# Spanline — Minecraft Utility Mods

Fünf kleine **Forge**-Mods für **Minecraft 1.21.11**. Fertige **`.jar`-Dateien** liegen im Ordner [`jars/`](jars/) — direkt nach `.minecraft/mods/` kopieren.

**Repository:** https://github.com/lolalpha00gamma/minecraft-utility-mods

| Mod | JAR | Was sie tut |
| --- | --- | --- |
| **Infinite Water** | [`infinite-water-1.1.0.jar`](jars/infinite-water-1.1.0.jar) | Volle Wassereimer bleiben voll. Leere Eimer bleiben leer. |
| **Mining Reach** | [`mining-reach-1.1.0.jar`](jars/mining-reach-1.1.0.jar) | Block-Reichweite +8 (4.5 → 12.5). |
| **Beacon Range** | [`beacon-range-1.1.0.jar`](jars/beacon-range-1.1.0.jar) | Beacon-Radius ×3. |
| **Elytra Speed** | [`elytra-speed-1.1.0.jar`](jars/elytra-speed-1.1.0.jar) | Elytra-Schub ×2, Cap 6 Blöcke/Tick. |
| **Villager Lead** | [`villager-lead-1.1.0.jar`](jars/villager-lead-1.1.0.jar) | Villager an die Leine, auch über Blöcke nach oben. |

## Installieren

1. [Minecraft Forge 61.2.1](https://files.minecraftforge.net/net/minecraftforge/forge/index_1.21.11.html) für **1.21.11**
2. Die `.jar`-Dateien aus [`jars/`](jars/) nach `.minecraft/mods/` kopieren
3. Launcher mit dem Forge-Profil starten

Keine extra API-Mod nötig.

## Bauen (optional)

Java 21, Gradle Wrapper liegt im Repo:

```bash
./gradlew build
```

JARs: `*/build/libs/*-1.1.0.jar`

---

## Config

Nach dem ersten Start in `.minecraft/config/`:

- `spanline_infinite_water.json` — `{ "enabled": true }`
- `spanline_mining_reach.json` — `{ "extraBlocks": 8.0, "affectCreative": true }`
- `spanline_beacon_range.json` — `{ "rangeMultiplier": 3.0, "rangeBonus": 0.0 }`
- `spanline_elytra_speed.json` — `{ "speedMultiplier": 2.0, "maxSpeed": 6.0 }`
- `spanline_villager_lead.json` — `{ "enabled": true, "stepHeight": 1.25, "climbBoost": 0.42, "followSpeed": 1.25 }`

Minecraft neu starten, damit Änderungen greifen.

## Lizenz

MIT — siehe `LICENSE`.
